package com.kyy.recuperationcourt.common.base.server;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cxa.base.net.NetWorkUtils;
import com.cxa.base.utils.EncryptMD5;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.MyApplication;
import com.kyy.recuperationcourt.common.base.net.GsonRequest;
import com.kyy.recuperationcourt.common.base.net.Json;
import com.kyy.recuperationcourt.common.base.net.VolleyUtil;
import com.kyy.recuperationcourt.common.model.dto.SysUserTokenDto;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.user.SysUser;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.view.fragment.MainFragment;
import com.kyy.recuperationcourt.common.view.fragment.mine.LoginFragment;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * 基础服务调用
 * Created by DeftrosChen on 2016-06-17.
 */
public class ServiceCall {
    //web服务地址，正式发布时为：  //192.168.0.8:8080     //18810230889.qicp.vip
//    public static final String SERVER_IP = "192.168.0.8:8081";
//    public static final String SERVER_IP = "192.168.0.139:8081";
    public static final String SERVER_IP = "action.kangyangyuan.cn";
//    public static final String SERVER_IP = "18810230889.qicp.vip"


    public static final String AES_KEY = "1234567891234567";
    public static final String AUTHORIZATION_HEADER_BASIC = "Basic ";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";
    public static final String AUTHORIZATION_CODE_FORLOGIN = "YXBwOmFwcA==";

    public static final String TOKEN = "YXBwOmFwcA==";


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * 得到服务器url
     *
     * @return
     * @throws Exception
     */
    public static String getServerURL() {
        String url = SERVER_IP;
        //获取服务器地址，先从缓存中查询，如果不存在则取默认值
        String ip = BaseUtils.getIP(MyApplication.getInstance());
        if (ip != null && !ip.trim().equals("")) {
            url = ip;
        }
        url = "http://" + url;
        //System.out.println("得到服务器url ==  "+url);
        return url;
    }

    /**
     * 得到 系统编码 + 手机唯一序列号，用于注册认证
     *
     * @return
     */
    public static String getRegisterSerialNumber() {
        String imei = BaseUtils.getIMEI_NUMBER(MyApplication.getInstance());
        try {
            return EncryptMD5.Encrypt("cshf_xczygk" + imei);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    private static Context context;

    /**
     * 重新自动登录
     *
     * @param ctx
     */
    public static void loginAgain(Context ctx) {
        context = ctx;
        if (!NetWorkUtils.isNetworkConnected(context)) {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(context, "请开启您的网络连接！", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();
            return;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "正在连接服务...", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        // if(true)
        //return;
        //自动登录，从本地缓存中取得上次的用户信息
        String userString = BaseUtils.getLoginUser(context);
        if (userString == null || userString.trim().equals("")) {
            //没有缓存，切换到登录界面
            Intent intent = new Intent();
            intent.setClass(context, MainFragment.class);
            context.startActivity(intent);
            return;
        }
        ResponseObject<SysUserTokenDto> user = new Gson().fromJson(userString, new TypeToken<ResponseObject<SysUserTokenDto>>() {
        }.getType());
        if (user.getData().getSysUser().getUsername() == null || user.getData().getSysUser().getPassword()  == null) {
            //缓存数据有丢失，切换到登录界面
            Intent intent = new Intent();
            intent.setClass(context, MainFragment.class);
            context.startActivity(intent);
            return;
        }
        //验证登录
        login(user.getData().getSysUser().getUsername().trim(),  user.getData().getSysUser().getPassword().trim());

    }

    /**
     * 登录
     *
     * @param accountId
     * @param password
     */
    private static void login(final String accountId, final String password) {
        Map<String, String> param = new HashMap<String, String>();
        try {
            param.put("userId", accountId);
            param.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = ServiceCall.getServerURL() +  "/action/sysUser/login";
        GsonRequest<Json> req = new GsonRequest<Json>(url, Json.class,
                new Response.Listener<Json>() {
                    @Override
                    public void onResponse(Json json) {
                        if (json.getResult() == 0) {
                            new Thread() {
                                @Override
                                public void run() {
                                    Looper.prepare();
                                    Toast.makeText(context, "连接已失效，请重新登录！", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            }.start();
                            //未通过验证，切换到登录界面
                            Intent intent = new Intent();
                            intent.setClass(context, LoginFragment.class);
                            context.startActivity(intent);
                        } else {
                            new Thread() {
                                @Override
                                public void run() {
                                    Looper.prepare();
                                    Toast.makeText(context, "连接成功，请继续操作！", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            }.start();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                new Thread() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(context, "连接失败，请确定ip地址是否正确或者重新登录！", Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }
                }.start();
            }
        }, param);
        //网络调用
        VolleyUtil.addToRequestQueue(req);
    }



    /**
     * 获取存储的用户信息token
     */
    public static String getUserInfoToken() {
        //获取用户信息显示
        String userString = BaseUtils.getLoginUser(MyApplication.getInstance());
        System.out.println("登录账号信息—:" + userString);

        if (userString.isEmpty()) {
            System.out.println("无登录账号信息");
            return null;
        }

        ResponseObject<SysUserTokenDto> user = new Gson().fromJson(userString, new TypeToken<ResponseObject<SysUserTokenDto>>() {
        }.getType());
        String accessToken = user.getData().getToken();
        System.out.println(accessToken);
        return accessToken;
    }

    /**
     * 获取存储的用户信息
     */
    public static SysUser getUserInfo() {
        //获取用户信息显示
        String userString = BaseUtils.getLoginUser(MyApplication.getInstance());
        System.out.println("登录账号信息—:" + userString);

        if (userString.isEmpty()) {
            System.out.println("无登录账号信息");
            return null;
        }

        ResponseObject<SysUserTokenDto> user = new Gson().fromJson(userString, new TypeToken<ResponseObject<SysUserTokenDto>>() {
        }.getType());
        SysUser sysUser = user.getData().getSysUser();

        return sysUser;
    }

}
