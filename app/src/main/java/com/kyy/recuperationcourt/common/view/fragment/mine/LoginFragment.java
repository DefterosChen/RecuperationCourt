package com.kyy.recuperationcourt.common.view.fragment.mine;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.dto.LoginDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.other.wheel.InputTextHelper;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.MainFragment;
import com.kyy.recuperationcourt.common.view.fragment.WebPageFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.button.CountDownButton;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;
import com.xuexiang.xui.widget.toast.XToast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Page(name = "登录", anim = CoreAnim.none)
public class LoginFragment extends MyBaseFragment {


    @BindView(R.id.titlebar_login)
    TitleBar titleBar;


    @BindView(R.id.iv_login_logo)
    ImageView mLogoView;

    @BindView(R.id.ll_login_body)
    LinearLayout mBodyLayout;
    @BindView(R.id.ll_login_edit1)
    LinearLayout mlinearLayout1;
    @BindView(R.id.ll_login_edit2)
    LinearLayout mlinearLayout2;


    @BindView(R.id.et_login_phone)
    EditText mPhoneView;
    @BindView(R.id.et_login_code)
    EditText mCodeView;
    @BindView(R.id.et_login_password)
    EditText mPasswordView;
    @BindView(R.id.et_login_pwcode)
    EditText mPicCodeView;
    @BindView(R.id.cv_login_countdown)
    CountDownButton mCountdownView;

    @BindView(R.id.tv_login_way)
    TextView mTextView_way;


    @BindView(R.id.btn_login_commit)
    SuperButton mCommitView;

    @BindView(R.id.iv_imagecode)
    ImageView mcodeView;

    @BindView(R.id.ll_login_other)
    View mOtherView;
    @BindView(R.id.iv_login_qq)
    View mQQView;
    @BindView(R.id.iv_login_wx)
    View mWeChatView;

    String tel = null;
    int loginWay = Consts.LOGIN_WAY_BY_VCODE;
    String timeStr; //获取图片验证码时所添加的时间戳

    String sessionId = "";


    /**
     * logo 缩放比例
     */
    private final float mLogoScale = 0.8f;
    /**
     * 动画时间
     */
    private final int mAnimTime = 300;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initViews() {

        getPermission(getContext(), true, true);

    }


    /**
     * getPermission 动态获取权限方法
     *
     * @param context    上下文
     * @param isAsk      是否开启权限询问      (Android6.0以下用户可以不开启,所有权限自动可以获得；6.0以上用户若不开启，获取不到某权限时，若你没做相应处理，可能会崩溃)
     * @param isHandOpen 是否询问用户被引导手动开启权限界面   (用户永久禁用某权限时是否引导让用户手动授权权限)
     */
    private void getPermission(Context context, boolean isAsk, final boolean isHandOpen) {
        if (!isAsk) return;

        if (XXPermissions.isHasPermission(getActivity(),
                //所需危险权限可以在此处添加：
                Permission.READ_PHONE_STATE,
                Permission.READ_SMS,
                Permission.READ_PHONE_NUMBERS
        )) {
            getPhoneNum();
            Toast.makeText(getActivity(), "已经获得所需所有权限", Toast.LENGTH_SHORT).show();

        } else {
            XXPermissions.with((Activity) context).permission(
                    //同时在此处添加：
                    Permission.READ_PHONE_STATE,
                    Permission.READ_SMS,
                    Permission.READ_PHONE_NUMBERS
            ).request(new OnPermission() {
                @Override
                public void noPermission(List<String> denied, boolean quick) {
                    if (quick) {
                        Toast.makeText(getActivity(), "被永久拒绝授权，请手动授予权限", Toast.LENGTH_SHORT).show();
                        //如果是被永久拒绝就跳转到应用权限系统设置页面
                        if (isHandOpen) {

                            Toast.makeText(getActivity(), "获取权限失败,请至手机设置中开启相应权限以确保应用正常使用！", Toast.LENGTH_SHORT).show();
//                            final AlertDialog.Builder normalDialog =
//                                    new AlertDialog.Builder(context);
//                            normalDialog.setTitle("开启权限引导");
//                            normalDialog.setMessage("被您永久禁用的权限为应用必要权限，是否需要引导您去手动开启权限呢？");
//                            normalDialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface arg0, int arg1) {
//                                    XXPermissions.gotoPermissionSettings(context);
//                                }
//                            });
//                            normalDialog.setNegativeButton("下一次", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface arg0, int arg1) {
//
//                                }
//                            });
//                            normalDialog.show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void hasPermission(List<String> granted, boolean isAll) {
                    if (isAll) {
                        Toast.makeText(getActivity(), "获取权限成功", Toast.LENGTH_SHORT).show();
                        getPhoneNum();
                    } else {
                        Toast.makeText(getActivity(), "获取权限成功，部分权限未正常授予", Toast.LENGTH_SHORT).show();
                        getPhoneNum();
                    }
                }
            });


        }
    }


    /**
     * 获取手机号码
     * getPermission中获取权限后调用
     */
    private void getPhoneNum() {

        tel = BaseUtils.getTelephoneNumber(getActivity().getApplication());
        if (tel.length() > 11) {
            tel = tel.substring(3, 14);

        }

        System.out.println("获取本机号码为：" + tel);
        mPhoneView.setText(tel);

    }


    @Override
    protected void initListeners() {
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {

            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                openPage(RegisterFragment.class);
            }
        });


        mPasswordView.setText("1"); //InputTextHelper在判断时 隐藏的textview内容不能为空
        mPicCodeView.setText("1"); //InputTextHelper在判断时 隐藏的textview内容不能为空

        InputTextHelper.with(getActivity())
                .addView(mPhoneView)
                .addView(mPasswordView)
                .addView(mPicCodeView)
                .addView(mCodeView)
                .setMain(mCommitView)
                .setListener(new InputTextHelper.OnInputTextListener() {
                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
//                        return mPhoneView.getText().toString().length() == 11 &&
//                                (mCodeView.getText().toString().length() == 4 ||
//                                (mPasswordView.getText().toString().length() >= 6 && mPicCodeView.getText().toString().length() == 4));

                        return mPhoneView.getText().toString().length() > 0 &&
                                mPasswordView.getText().toString().length() >= 6;
                    }
                })
                .build();

        /**
         * 点击 图片验证码 更换验证图片
         */
        mcodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicCode();
            }
        });


//        // 判断用户当前有没有安装 QQ
//        if (!UmengClient.isAppInstalled(this, Platform.QQ)) {
//            mQQView.setVisibility(View.GONE);
//        }
//
//        // 判断用户当前有没有安装微信
//        if (!UmengClient.isAppInstalled(this, Platform.WECHAT)) {
//            mWeChatView.setVisibility(View.GONE);
//        }

        // 如果这两个都没有安装就隐藏提示
        if (mQQView.getVisibility() == View.GONE && mWeChatView.getVisibility() == View.GONE) {
            mOtherView.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.cv_login_countdown, R.id.tv_login_way, R.id.tv_login_forget, R.id.btn_login_commit, R.id.iv_login_qq, R.id.iv_login_wx})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_login_countdown:
                // 获取验证码
                if (mPhoneView.getText().toString().length() != 11) {
                    XToastUtils.info(R.string.common_phone_input_error);
                } else {
                    getVCode();
                }
                break;
            case R.id.tv_login_way:
                if (mlinearLayout1.isShown()) {
                    //切换到账号密码登录
                    mlinearLayout2.setVisibility(View.VISIBLE);
                    mlinearLayout1.setVisibility(View.GONE);
                    mTextView_way.setText(R.string.login_vcode);
                    loginWay = Consts.LOGIN_WAY_BY_PASSWORD;
                    mCodeView.setText("1");
                    mPasswordView.setText("");
                    mPicCodeView.setText("");
                    getPicCode(); //获取4位数验证码图片
                } else {
                    //切换到短信验证码登录
                    mlinearLayout1.setVisibility(View.VISIBLE);
                    mlinearLayout2.setVisibility(View.GONE);
                    mTextView_way.setText(R.string.login_password);
                    loginWay = Consts.LOGIN_WAY_BY_VCODE;
                    mCodeView.setText("");
                    mPasswordView.setText("1");
                    mPicCodeView.setText("1");

                }
                break;
            case R.id.tv_login_forget:
                //暂无
                openPage(WebPageFragment.class);
                break;
            case R.id.btn_login_commit:
                if (loginWay == Consts.LOGIN_WAY_BY_VCODE) {
                    loginByVCode();
                } else {
                    loginByPassword();
                }
                break;
            case R.id.iv_login_qq:
            case R.id.iv_login_wx:
                XToast.info(getActivity(), R.string.common_permission_fail);
                break;
            default:
                break;
        }
    }


    /**
     * 账号密码登录
     */
    private void loginByPassword() {

        LoginDto loginDto = new LoginDto();
        loginDto.setUserName(mPhoneView.getText().toString());
        loginDto.setPassword(mPasswordView.getText().toString());
        loginDto.setCheckCode(mPicCodeView.getText().toString());

        RequestObject<LoginDto> requestBodyDto = new RequestObject();
        requestBodyDto.setData(loginDto);

        String dataStr = new Gson().toJson(requestBodyDto);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

        String url = ServiceCall.getServerURL() + "/action/sysUser/login";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Request request = new Request.Builder()
                .url(url)//请求的url
                .header("Content-Type", "application/json")
                .addHeader("Cookie", "JSESSIONID=" + sessionId)
                .post(requestBody)
                .build();

        //创建/Call
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

                if (!response.isSuccessful()) {
                    // 验证失败 返回弹出消息原因
                    ResponseObject data = new Gson().fromJson(returnBody, new TypeToken<ResponseObject>() {
                    }.getType());
                    Looper.prepare();
                    ToastUtil.showTips(data.getMsg());
                    Looper.loop();
                    return;
                }

                System.out.println("登录成功");
                getUserInfo(returnBody);
            }
        });

    }


    /**
     * 手机短信验证码登录
     */
    private void loginByVCode() {

        String url = ServiceCall.getServerURL() + "/auth/mobile/token/sms?mobile=" + mPhoneView.getText().toString() + "&code=" + mCodeView.getText().toString() + "&grant_type=mobile";

        System.out.println("url == " + url);


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

        //创建/Call
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

                if (returnBody.indexOf("access_token") != -1) {
                    System.out.println("登录成功");
                    getUserInfo(returnBody);
                } else {
                    System.out.println("登录失败");
                }
            }
        });

    }



    /**
     * 登录成功后获取用户信息并实现界面跳转
     *
     * @param userJson
     */
    private void getUserInfo(String userJson) {
        //保存用户登录获取的数据
        BaseUtils.saveLogin(getActivity(), userJson);
        //跳转到首页
        openPage(MainFragment.class);
    }

    /**
     * 获取手机验证码
     */
    private void getVCode() {

        String url = ServiceCall.getServerURL() + "/admin/mobile/" + mPhoneView.getText().toString();
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
                System.out.println("连接失败");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("连接成功");
                if (response.code() == 200) {
                    System.out.println(response.body().string());
                }
            }
        });

    }

    /**
     * 获取图片验证码
     */
    private void getPicCode() {


        String url = ServiceCall.getServerURL() + "/action/sysUser/randomChars";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        System.out.println("sessionId+:" + sessionId);

        Request request = new Request.Builder()
                .url(url)//请求的url
                .addHeader("Cookie", "JSESSIONID=" + sessionId)
                .get()
                .build();


        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("成功!!4" + response.headers().toString());

                if (sessionId.length() == 0) {
                    String str = response.headers().toString();
                    int index = str.indexOf("JSESSIONID");
                    int index2 = str.indexOf("; Path=/action; HttpOnly");
                    sessionId = str.substring(index + 11, index2);
                }

                InputStream inputStream = response.body().byteStream();
                //使用BitmapFactory工厂，把字节数组转化为bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mcodeView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }
}
