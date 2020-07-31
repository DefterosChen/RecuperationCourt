package com.kyy.recuperationcourt.common.view.fragment.mine;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.setting.AccountSafeFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.setting.PasswordChangingFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.setting.SetingAboutFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.setting.UseFeelbackFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

/**
 * CXA
 * <p>
 * 2019/12/03
 */
@Page(name = "设置", anim = CoreAnim.none)
public class SettingFragment extends MyBaseFragment {

    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.menu_pwchange)
    SuperTextView stv_pwchange;
    @BindView(R.id.menu_userwechat)
    SuperTextView stv_tyingwechat;
    @BindView(R.id.menu_useradvice)
    SuperTextView stv_advice;
    @BindView(R.id.menu_about)
    SuperTextView stv_about;
    @BindView(R.id.menu_accountsafe)
    SuperTextView stv_safe;
    @BindView(R.id.menu_logout)
    SuperTextView stv_logout;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });

        /**
         * 修改密码
         */
        stv_pwchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(PasswordChangingFragment.class);
            }
        });

        /**
         * 微信绑定/解绑
         */
        stv_tyingwechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                tying();

                untying();

            }

            /**
             * 解绑
             */
            private void untying() {

                final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("解绑微信")
                        .setIcon(R.drawable.logo_kky)
                        .setMessage(R.string.kky_unbind_wechat_confirm)
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                XToastUtils.toast("解绑成功！");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();


            }

            /**
             * 绑定
             */
            private void tying() {
            }
        });


        /**
         * 使用反馈
         */
        stv_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(UseFeelbackFragment.class);
            }
        });


        /**
         * 关于
         */
        stv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(SetingAboutFragment.class);
            }
        });


        /**
         * 账户安全
         */
        stv_safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(AccountSafeFragment.class);
            }
        });


        /**
         * 退出登录
         */
        stv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }

            private void logout() {

                String token = ServiceCall.getUserInfoToken();


                final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("确定退出账号？")
                        .setIcon(R.drawable.logo_kky)
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                logoff();

                                XToastUtils.toast("已退出！");
                            }


                            /**
                             * 注销登录用户
                             */
                            private void logoff() {
                                BaseUtils.removeLogin(getContext());
                                System.out.println("注销成功");
                                openPage(LoginFragment.class);
                            }



//                            private void logoff() {
//
//                                String url = ServiceCall.getServerURL() + "/auth/token/logout";
//
//                                System.out.println("url == " + url);
//
//
//                                OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                                        .connectTimeout(10, TimeUnit.SECONDS)
//                                        .writeTimeout(10, TimeUnit.SECONDS)
//                                        .readTimeout(20, TimeUnit.SECONDS)
//                                        .retryOnConnectionFailure(true)
//                                        .build();
//
//
//
//                                Request request = new Request.Builder()
//                                        .url(url)//请求的url
//                                        .addHeader("Authorization", ServiceCall.AUTHORIZATION_HEADER_BEARER + token)
//                                        .delete()
//                                        .build();
//
//                                //创建/Call
//                                Call call = okHttpClient.newCall(request);
//                                //加入队列 异步操作
//                                call.enqueue(new Callback() {
//                                    //请求错误回调方法
//                                    @Override
//                                    public void onFailure(Call call, IOException e) {
//                                        System.out.println("登出失败");
//                                    }
//
//                                    @Override
//                                    public void onResponse(Call call, Response response) throws IOException {
//
//                                        String returnBody = response.body().string();
//
//                                        System.out.println("returnBody::"+ returnBody);
//
//                                        //转换返回数据格式
//                                        Type type = new TypeToken<UserInfo>() {
//                                        }.getType();
////                                        UserInfo userInfo = new Gson().fromJson(returnBody, type);
////                                        System.out.println("返回的数据：" + userInfo.toString());
//
//
//                                        if (returnBody.indexOf("data") != -1) {
//                                            //注销缓存的用户信息
//                                            BaseUtils.removeLogin(getActivity());
//                                            System.out.println("注销成功");
//                                            openPage(LoginFragment.class);
//                                        } else {
//                                            System.out.println("注销失败");
//                                        }
//
//                                    }
//                                });
//
//
//                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }


}
