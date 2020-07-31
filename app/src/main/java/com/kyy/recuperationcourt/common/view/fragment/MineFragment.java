package com.kyy.recuperationcourt.common.view.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.MyApplication;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.dto.SysUserTokenDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.user.LoginUser;
import com.kyy.recuperationcourt.common.model.entity.user.SysUser;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.device.smartwatch.WatchFragment;
import com.kyy.recuperationcourt.common.view.fragment.home.MessageFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.AboutFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.CompanyProfileFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.HealthRecordFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.LoginFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.MyCreditFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.MyInfoFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.MyOrderFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.SettingFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 我的
 * 2019/11/12
 * cxa
 */
@Page(name = "我的", anim = CoreAnim.none)
public class MineFragment extends MyBaseFragment {

    @BindView(R.id.iv_setting)
    ImageView iv_setting;
    @BindView(R.id.iv_system_messages)
    ImageView iv_message;


    @BindView(R.id.menu_myinfo)
    SuperTextView stv_myinfo;

    @BindView(R.id.menu_mydevice)
    SuperTextView stv_mydevice;
    @BindView(R.id.menu_mycredit)
    SuperTextView stv_mycredit;
    @BindView(R.id.menu_healthrecord)
    SuperTextView stv_healthrecord;

    @BindView(R.id.menu_myservice)
    SuperTextView stv_myservice;
    @BindView(R.id.menu_mycourse)
    SuperTextView stv_mycourse;
    @BindView(R.id.menu_message)
    SuperTextView stv_message;

    @BindView(R.id.menu_contactus)
    SuperTextView stv_contactus;
    @BindView(R.id.menu_companyprofile)
    SuperTextView stv_companyprofile;

    String returnBody; //返回的信息字符串
    String accessToken; //查询用户信息所需的TOKEN
    ResponseObject<SysUser> userInfo; //返回的个人信息

    Handler handler = null;

    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
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
                    //2020/07/17 暂时无用
                    getNewToken();     //token过期 ,重新获取
                }
                return false;
            }

            /**
             * 获取token
             */
            private void getNewToken() {

                //再登录

                //获取用户信息显示
                String userString = BaseUtils.getLoginUser(getActivity());
                System.out.println("登录的账号信息————————:" + userString);

                if (userString.isEmpty()) {
                    System.out.println("无登录账号信息");
                    return;
                }
                ResponseObject<SysUserTokenDto> user = new Gson().fromJson(userString, new TypeToken<ResponseObject<SysUserTokenDto>>() {
                }.getType());
                String phoneNum = user.getData().getSysUser().getMobile();


                String url = ServiceCall.getServerURL() + "/auth/mobile/token/code?grant_type=refresh_token&mobile=" + phoneNum;


                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();


                Request request = new Request.Builder()
                        .url(url)//请求的url
                        .header("tenant-id", "1")
                        .addHeader("Authorization", ServiceCall.AUTHORIZATION_HEADER_BASIC + ServiceCall.AUTHORIZATION_CODE_FORLOGIN)
                        .post(okhttp3.internal.Util.EMPTY_REQUEST)  // 空参：okhttp3.internal.Util.EMPTY_REQUEST
                        .build();

                //创建/Callqa
                Call call = okHttpClient.newCall(request);
                //加入队列 异步操作
                call.enqueue(new Callback() {
                    //请求错误回调方法
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("登录失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String returnBody = response.body().string();
                        System.out.println("response.body():" + returnBody);
                        //转换返回数据格式
                        Type type = new TypeToken<LoginUser>() {
                        }.getType();
                        LoginUser loginUser = new Gson().fromJson(returnBody, type);
                        accessToken = loginUser.getAccess_token();

                        //保存最新token 到用户信息中
                        String userString = BaseUtils.getLoginUser(MyApplication.getInstance());
                        System.out.println("登录账号信息—:" + userString);
                        LoginUser user = new Gson().fromJson(userString, LoginUser.class);
                        user.setAccess_token(accessToken);

                        //保存用户登录获取的数据
                        BaseUtils.saveLogin(getActivity(), new Gson().toJson(user));

                        showInfo();

                    }
                });


            }

            /**
             * 显示用户信息数据
             */
            private void showData() {
                System.out.println("userInfo:::" + userInfo.toString());
                stv_myinfo.setLeftString(userInfo.getData().getUsername());
            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        // 创建消息管理器
        createHandlerManage();

        System.out.println("我的界面信息查看！");

        showInfo();
    }

//    /**
//     * 获取存储的用户信息
//     */
//    private void getUserInfo() {
//        //获取用户信息显示
//        String userString = BaseUtils.getLoginUser(getActivity());
//        System.out.println("登录账号信息—:" + userString);
//
//        if (userString.isEmpty()) {
//            System.out.println("无登录账号信息");
//            return;
//        }
//        LoginUser user = new Gson().fromJson(userString, LoginUser.class);
//        accessToken = user.getAccess_token();
//        System.out.println(accessToken);
//    }


    /**
     * 显示用户信息
     */
    private void showInfo() {
        accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }

        //获取用户信息显示
        String userString = BaseUtils.getLoginUser(MyApplication.getInstance());
        ResponseObject<SysUserTokenDto> user = new Gson().fromJson(userString, new TypeToken<ResponseObject<SysUserTokenDto>>() {
        }.getType());
        System.out.println("登录账号信息:" + user.toString());

        SysUser sysUser = new SysUser();
        sysUser.setId(user.getData().getSysUser().getId());
        RequestObject<SysUser> requestBodyDto = new RequestObject();
        requestBodyDto.setData(sysUser);

        String dataStr = new Gson().toJson(requestBodyDto);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

        String url = ServiceCall.getServerURL() + "/action/sysUser/findById";
        System.out.println(url);

        final Request request = new Request.Builder()
                .url(url)//请求的url
                .addHeader("Authorization",  accessToken)
                .post(requestBody)
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
                System.out.println("获取失败");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                returnBody = response.body().string();
                System.out.println("获取成功！！！！ returnBody:" + returnBody);   //response.code = 401

//                //当使用过期token时 data字段返回值为字符串，需要进行忽略
//                Gson gson = new GsonBuilder()
//                        .setExclusionStrategies(new ExclusionStrategy() {
//                            @Override
//                            public boolean shouldSkipField(FieldAttributes f) {
//                                return f.getName().equals("data");
//                            }
//
//                            @Override
//                            public boolean shouldSkipClass(Class<?> clazz) {
//                                return false;
//                            }
//                        }).create();

                //转换返回数据格式
                Type type = new TypeToken<ResponseObject<SysUser>>() {
                }.getType();
                userInfo = new Gson().fromJson(returnBody, type);
                System.out.println("获取成功！！！！ userInfo:" + userInfo);

                Message msg = new Message();
                if (userInfo.getCode().equals(Consts.REPONSE_200)) {
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else {
                    //暂时用不到
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }

        });
    }


    @Override
    protected void initListeners() {

        /**
         * 设置
         */
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(SettingFragment.class);
            }
        });


        /**
         * 消息通知
         */
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(MessageFragment.class);
            }
        });

        /**
         * 个人信息
         */
        stv_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已登录过
                if (!BaseUtils.isLogin(MyApplication.getInstance())) {
                    System.out.println("尚未登录，请登录！");
                    openPage(LoginFragment.class);
                    return;
                }

                openPage(MyInfoFragment.class);
            }
        });


        stv_mydevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(WatchFragment.class);
            }
        });


        /**
         * 我的积分
         */
        stv_mycredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(MyCreditFragment.class);
            }
        });


        /**
         * 健康档案
         */
        stv_healthrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(HealthRecordFragment.class);
            }
        });


        /**
         * 我的服务
         */
        stv_myservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(MyOrderFragment.class);
            }
        });

        /**
         * 我的课程
         */
        stv_mycourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(MyOrderFragment.class);
            }
        });

        /**
         * 我的资讯
         */
        stv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(MyOrderFragment.class);
            }
        });


        /**
         * 联系我们
         */
        stv_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(AboutFragment.class);
            }
        });


        /**
         * 公司介绍
         */
        stv_companyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(CompanyProfileFragment.class);
            }
        });
    }


}
