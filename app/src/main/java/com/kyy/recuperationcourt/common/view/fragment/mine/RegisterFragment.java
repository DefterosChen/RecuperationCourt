package com.kyy.recuperationcourt.common.view.fragment.mine;


import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.entity.user.RegisterInfo;
import com.kyy.recuperationcourt.common.other.wheel.InputTextHelper;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.button.CountDownButton;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
@Page(name = "注册", anim = CoreAnim.none)
public class RegisterFragment extends MyBaseFragment {


    @BindView(R.id.titlebar_reg)
    TitleBar titleBar;

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountDownButton mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mCodeView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    SuperButton mCommitView;

    String tel;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                openPage(LoginFragment.class);
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
            }
        });

    }

    @Override
    protected void initViews() {

        getPhoneNum();

        isCommit();

    }

    /**
     * 提交验证
     */
    private void isCommit() {
        InputTextHelper.with(getActivity())
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(new InputTextHelper.OnInputTextListener() {
                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
                        return mPhoneView.getText().toString().length() == 11 &&
                                mCodeView.getText().toString().length() == 4 &&
                                mPasswordView1.getText().toString().length() >= 8 &&
                                mPasswordView2.getText().toString().equals(mPasswordView1.getText().toString());
                    }
                })
                .build();


//        if(mCodeView.getText().toString().length() ==4){
//            mPhoneView.setEnabled(false);
//        }

    }

    /**
     * 获取本机手机号码
     */
    private void getPhoneNum() {
        tel = BaseUtils.getTelephoneNumber(getActivity().getApplication());
        if (tel.length() > 11) {
            tel = tel.substring(3, 14);

        }
        System.out.println("获取本机号码为：" + tel);
        mPhoneView.setText(tel);

    }


    @OnClick({R.id.cv_register_countdown, R.id.btn_register_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown:
                getVCode();
                break;
            case R.id.btn_register_commit:
                // 提交注册
                commitRegister();
                break;
            default:
                break;
        }
    }

    /**
     * 提交注册信息
     */
    private void commitRegister() {
        String url = ServiceCall.getServerURL() + "/admin/user/register";

        System.out.println("url == " + url);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        //把参数传进Map中
        HashMap<String,String> paramsMap=new HashMap<>();
        paramsMap.put("grant_type","mobile");
        paramsMap.put("mobile",mPhoneView.getText().toString());
        paramsMap.put("code",mCodeView.getText().toString());
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : paramsMap.keySet()) {
            //追加表单信息
            builder.add(key, paramsMap.get(key));
        }

        RegisterInfo registerInfo = new RegisterInfo();
        List<String> list = new ArrayList<>();
        list.add("b91d0c8aee9ee32e50da9291a4baecd5");

        registerInfo.setOrganId("3");
        registerInfo.setRole(list);
        registerInfo.setPhone(mPhoneView.getText().toString());
        registerInfo.setCode(mCodeView.getText().toString());
        registerInfo.setPassword(mPasswordView1.getText().toString());

        String dataStr = new Gson().toJson(registerInfo);
        RequestBody requestBody = RequestBody.create(JSON, dataStr);

        Request request = new Request.Builder()
                .url(url)//请求的url
                .header("tenant-id", "1")
                .addHeader("Content-Type", "application/json")
                .post(requestBody)  // 空参：okhttp3.internal.Util.EMPTY_REQUEST
                .build();



        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("注册失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println("注册成功");
                System.out.println(response.body().string());
                //跳转到登录
                openPage(LoginFragment.class);
            }
        });
    }

    /**
     * 注册接口获取验证码
     */
    private void getVCode() {

        // 获取验证码
        if (mPhoneView.getText().toString().length() != 11) {
            XToastUtils.info(R.string.common_phone_input_error);
            return;
        }


        String url = ServiceCall.getServerURL() + "/admin/register/" + mPhoneView.getText().toString();
        System.out.println(url);
        System.out.println(mPhoneView.getText().toString());

        final Request request = new Request.Builder()
                .url(url)//请求的url
                .header("tenant-id", "1")
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
                System.out.println("验证码发送失败");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("验证码已发送");
                if (response.code() == 200) {
                    System.out.println(response.body().string());
                }
            }
        });


    }

}