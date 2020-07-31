package com.kyy.recuperationcourt.common.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.kyy.recuperationcourt.common.base.dao.SysDicDao;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.entity.consult.NewsType;
import com.kyy.recuperationcourt.common.model.entity.message.inter.NewsTypeData;
import com.kyy.recuperationcourt.common.util.SystemUtils;
import com.kyy.recuperationcourt.common.view.base.MyBaseActivity;
import com.kyy.recuperationcourt.common.view.fragment.MainFragment;
import com.xuexiang.xpage.core.PageOption;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends MyBaseActivity {


    String returnBody;

    Handler handler = null;


    private NotificationManager notificationManager;
    private Notification notification;
    RemoteViews contentView;


    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    showNewsTitle();  //展示新闻标题导航栏列表
                } else if (msg.what == 2) {
                    System.out.println("返回数据OK为false");
                }
                return false;
            }

            /**
             * 显示新闻标题导航栏
             */
            private void showNewsTitle() {
                try {


                    //转换返回数据格式
                    Type type = new TypeToken<NewsTypeData>() {
                    }.getType();
                    System.out.println("连接成功！！！！ 新闻标题导航栏返回数据:" + returnBody);   //response.code = 401

                    SysDicDao dicDao = new SysDicDao();
                    List<NewsType> list = new ArrayList<>();

                    NewsTypeData data = new Gson().fromJson(returnBody, type);
                    for (int i = 0; i < data.getData().getTotal(); i++) {

                        NewsType newsType = new NewsType();
                        newsType.setId(data.getData().getRecords().get(i).getId());
                        newsType.setPid(data.getData().getRecords().get(i).getPid());
                        newsType.setLevel(data.getData().getRecords().get(i).getLevel());
                        newsType.setName(data.getData().getRecords().get(i).getName());
                        newsType.setDescription(data.getData().getRecords().get(i).getDescription());
                        newsType.setIcon(data.getData().getRecords().get(i).getIcon());
                        newsType.setType(data.getData().getRecords().get(i).getType());
                        newsType.setAlias(data.getData().getRecords().get(i).getAlias());
                        newsType.setSystemId(data.getData().getRecords().get(i).getSystemId());
                        newsType.setOrders(data.getData().getRecords().get(i).getOrders());
                        newsType.setCreateTime(data.getData().getRecords().get(i).getCreateTime());

                        list.add(newsType);
                    }

                    dicDao.bathAddSysDicType(list);
                    System.out.println("新闻类别数据导入数据库成功！");

                } catch (Exception e) {
                    e.printStackTrace();
                    showMsg("导入新闻类别数据字典出错！" + e.getMessage());
                }

            }

        });

    }


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建消息管理器
        createHandlerManage();

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }



        getPremissions();


        PageOption.to(MainFragment.class).open(this);
    }

    /**
     * 获取一些基本数据
     */
    private void getBaseData() {

//        getNewsTitle();

    }

    /**
     * 获取新闻列表的标题类别数据
     */
    private void getNewsTitle() {

        String url = ServiceCall.getServerURL() + "/cms/category/page?current=1&size=100"; // current :页数   size :单次刷新条数 固定100条

        final Request request = new Request.Builder()
                .url(url)//请求的url
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

                returnBody = response.body().string();       //response.code = 401

                System.out.println("新闻类别获取到的数据是："+returnBody);

                //转换返回数据格式
                Type type = new TypeToken<NewsTypeData>() {
                }.getType();
                NewsTypeData data = new Gson().fromJson(returnBody, type);

                Message msg = new Message();
                if (data.isOk()) {
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else {
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 获取权限
     */
    private void getPremissions() {


        XXPermissions.with(this)
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.REQUEST_INSTALL_PACKAGES, Permission.SYSTEM_ALERT_WINDOW) //支持请求安装权限和悬浮窗权限
//                .permission(Permission.Group.STORAGE) //支持多个权限组进行请求，不指定则默以清单文件中的危险权限进行请求
                .permission(Permission.READ_PHONE_STATE
                        , Permission.READ_SMS
                        , Permission.READ_PHONE_NUMBERS
                        , Permission.ACCESS_COARSE_LOCATION
                        , Permission.ACCESS_FINE_LOCATION
                        , Permission.WRITE_EXTERNAL_STORAGE
                        , Permission.CAMERA
                        , Permission.READ_EXTERNAL_STORAGE)
                .request(new OnPermission() {


                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "获取权限成功，部分权限未正常授予", Toast.LENGTH_SHORT).show();
                        }

                        //创建程序初始化目录
                        SystemUtils.createInitDir();

                        //初始化数据库
                        SystemUtils.createDatabase(getApplicationContext());

                        //获取基础数据存入数据库
                        getBaseData();
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            Toast.makeText(MainActivity.this, "被永久拒绝授权，请手动授予权限", Toast.LENGTH_SHORT).show();
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(MainActivity.this);
                        } else {
                            Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showMsg(final String msg) {
        if (getApplicationContext() != null) {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();
        }
    }
}
