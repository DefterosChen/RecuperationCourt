package com.kyy.recuperationcourt.common.view.fragment.mine;


import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.entity.user.UserInfo;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * CXA
 * <p>
 * 2019/12/03
 */
@Page(name = "公司介绍", anim = CoreAnim.none)
public class CompanyProfileFragment extends MyBaseFragment {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.text_content)
    TextView textView;

    String accessToken;
    String returnBody;

    Handler handler = null;


    public CompanyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_profile;
    }

    @Override
    protected void initViews() {
        // 创建消息管理器
        createHandlerManage();

        showInfo();

    }

    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    showData();  //直接获取用户信息数据显示
                } else if (msg.what == 2) {
                    System.out.println("返回数据OK为false");
                }
                return false;
            }

            /**
             * 显示用户信息数据
             */
            private void showData() {
                //转换返回数据格式
                Type type = new TypeToken<UserInfo>() {
                }.getType();
                UserInfo userInfo = new Gson().fromJson(returnBody, type);
                System.out.println("userInfo:::" + userInfo.toString());

                String str = Html.fromHtml(userInfo.getData().getContent()).toString();

                textView.setText(str);
            }

        });

    }


    private void showInfo() {

        accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }

        String url = ServiceCall.getServerURL() + "/kang/tarticle/1";
        System.out.println(url);

        final Request request = new Request.Builder()
                .url(url)//请求的url
                .addHeader("Authorization", ServiceCall.AUTHORIZATION_HEADER_BEARER + accessToken)
                .get()
                .build(); //构建一个请求Request对象

        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("连接失败");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                returnBody = response.body().string();
                System.out.println("连接成功！！！！ returnBody1:" + returnBody);   //response.code = 401

                //当使用过期token时 data字段返回值为字符串，需要进行忽略
                Gson gson = new GsonBuilder()
                        .setExclusionStrategies(new ExclusionStrategy() {
                            @Override
                            public boolean shouldSkipField(FieldAttributes f) {
                                return f.getName().equals("data");
                            }

                            @Override
                            public boolean shouldSkipClass(Class<?> clazz) {
                                return false;
                            }
                        }).create();

                //转换返回数据格式
                Type type = new TypeToken<UserInfo>() {
                }.getType();
                UserInfo userInfo = gson.fromJson(returnBody, type);

                Message msg = new Message();
                if (userInfo.isOk()) {
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else {
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }

        });
    }

    @Override
    protected void initListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });
    }


}
