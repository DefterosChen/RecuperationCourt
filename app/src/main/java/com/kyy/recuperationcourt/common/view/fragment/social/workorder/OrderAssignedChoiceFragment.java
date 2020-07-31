package com.kyy.recuperationcourt.common.view.fragment.social.workorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.adapter.MyAdapter;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.CompanyType;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.dto.QueryServiceItemDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.compay.Company;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.order.ServiceItem;
import com.kyy.recuperationcourt.common.model.entity.order.WorkOrder;
import com.kyy.recuperationcourt.common.model.entity.user.SysUser;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.commonsdk.debug.W;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 分配工单选择
 * 2020/07/23
 * DefterosChen
 */
@Page(name = "分配工单选择", anim = CoreAnim.none)
public class OrderAssignedChoiceFragment extends MyBaseFragment {

    public final static String ASSIGNED_TYPE = "assignedType";
    public final static String WORKORDERID = "workorderId";
    public final static String KEY_ASSIGNED_TYPE_USER = "1";
    public final static String KEY_ASSIGNED_TYPE_COMPANY = "2";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_submit)
    TextView textViewSubmit;

    Handler handler = null;
    String returnBody;

    List<SysUser> mListU = new ArrayList<>();
    List<Company> mListC = new ArrayList<>();



    String ChoiceType;

    MyAdapter myAdapter;

    public OrderAssignedChoiceFragment() {

        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_singlechoice;
    }

    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    showDataUser();
                } else if (msg.what == 2) {
                    System.out.println("接口返回数据ok为false");
                } else if (msg.what == 3) {
                    System.out.println("工单分配成功，返回工单列表");
                    //回到指定的页面
                    popToBack();
                } else if (msg.what == 4) {
                    showDataCompany();
                }
                return false;
            }


            private void showDataUser() {

                //转换返回数据格式
                Type type = new TypeToken<ResponseObject<List<SysUser>>>() {
                }.getType();

                ResponseObject<List<SysUser>> responseObject = new ResponseObject<>();
                responseObject = new Gson().fromJson(returnBody, type);

                for (int i = 0; i < responseObject.getData().size(); i++) {
                    mListU.add(responseObject.getData().get(i));
                }

                System.out.println("数据接收后的list：" + mListU.size());
                ToastUtil.showMsg(Messages.LOADINGFINISHED);
                myAdapter.notifyDataSetChanged();

            }

            private void showDataCompany() {

                //转换返回数据格式
                Type type = new TypeToken<ResponseObject<List<Company>>>() {
                }.getType();

                ResponseObject<List<Company>> responseObject = new ResponseObject<>();
                responseObject = new Gson().fromJson(returnBody, type);

                for (int i = 0; i < responseObject.getData().size(); i++) {
                    mListC.add(responseObject.getData().get(i));
                }

                System.out.println("数据接收后的list：" + mListU.size());
                ToastUtil.showMsg(Messages.LOADINGFINISHED);
                myAdapter.notifyDataSetChanged();

            }
        });
    }


    @Override
    protected void initViews() {
        ChoiceType = getArguments().getString(ASSIGNED_TYPE);
        System.out.println("ChoiceType:" + ChoiceType);

        textViewSubmit.setVisibility(View.GONE);
        // 创建消息管理器
        createHandlerManage();
        WidgetUtils.initRecyclerView(mRecyclerView);

        switch (ChoiceType){
            case KEY_ASSIGNED_TYPE_USER:
                getListUser();
                getDataUser();
                break;
            case  KEY_ASSIGNED_TYPE_COMPANY:
                getListCompany();
                getDataCompany();
                break;
        }
    }

    private void getListCompany() {
        myAdapter = new MyAdapter<Company>(mListC, R.layout.item_kyy_list_elder) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Company item, int position) {
                item = mListC.get(position);

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + item.getName());
            }
        };
        mRecyclerView.setAdapter(myAdapter);
    }

    private void getDataCompany() {

        String accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }

        Company company = new Company();
        company.setType(CompanyType.HEADQUARTERS.getCode());
        RequestObject<Company> requestObject = new RequestObject<>();
        requestObject.setData(company);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/company/selectByHeadquarters";

        final Request request = new Request.Builder()
                .url(url)//请求的url
                .addHeader("Authorization"
                        , accessToken)
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
                System.out.println("连接失败");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                returnBody = response.body().string();

                System.out.println("分配获取到的服务人员数据：" + returnBody);
//                //转换返回数据格式
//                Type type = new TypeToken<NewsTypeData>() {
//                }.getType();
//                NewsTypeData data = new Gson().fromJson(returnBody, type);

                Message msg = new Message();
                if (response.isSuccessful()) {
                    msg.what = 4;
                    handler.sendMessage(msg);
                } else {
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }

        });

    }

    private void getListUser() {

        myAdapter = new MyAdapter<SysUser>(mListU, R.layout.item_kyy_list_elder) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, SysUser item, int position) {
                item = mListU.get(position);

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + item.getUsername());
            }
        };
        mRecyclerView.setAdapter(myAdapter);
    }

    private void getDataUser() {



        String accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }

        int userCompanyId = ServiceCall.getUserInfo().getCompanyId();

        SysUser sysUser = new SysUser();
        sysUser.setCompanyId(userCompanyId);
        RequestObject<SysUser> requestObject = new RequestObject<>();
        requestObject.setData(sysUser);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/sysUser/selectUserListByCompany";

        final Request request = new Request.Builder()
                .url(url)//请求的url
                .addHeader("Authorization"
                        , accessToken)
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
                System.out.println("连接失败");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                returnBody = response.body().string();

                System.out.println("分配获取到的服务人员数据：" + returnBody);
//                //转换返回数据格式
//                Type type = new TypeToken<NewsTypeData>() {
//                }.getType();
//                NewsTypeData data = new Gson().fromJson(returnBody, type);

                Message msg = new Message();
                if (response.isSuccessful()) {
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
        super.initListeners();

        myAdapter.setOnItemClickListener(new SmartViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                switch (ChoiceType){
                    case KEY_ASSIGNED_TYPE_USER:
                        submitOrderAssignedU(position);
                        break;
                    case  KEY_ASSIGNED_TYPE_COMPANY:
                        submitOrderAssignedC(position);
                        break;
                }


            }

            private void submitOrderAssignedC(int position) {
                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }

                WorkOrder workOrder = new WorkOrder();
                workOrder.setCompanyId(mListC.get(position).getId());
                workOrder.setId(getArguments().getInt(WORKORDERID));

                RequestObject<WorkOrder> requestObject = new RequestObject<>();
                requestObject.setData(workOrder);

                String dataStr = new Gson().toJson(requestObject);
                System.out.println(dataStr);
                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


                String url = ServiceCall.getServerURL() + "/action/workOrder/dispatch";

                final Request request = new Request.Builder()
                        .url(url)//请求的url
                        .addHeader("Authorization"
                                , accessToken)
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
                        System.out.println("连接失败");
                    }

                    //异步请求(非主线程)
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        returnBody = response.body().string();

                        System.out.println("获取到的服务项数据：" + returnBody);
//                //转换返回数据格式
//                Type type = new TypeToken<NewsTypeData>() {
//                }.getType();
//                NewsTypeData data = new Gson().fromJson(returnBody, type);

                        Message msg = new Message();
                        if (response.isSuccessful()) {
                            msg.what = 3;
                            handler.sendMessage(msg);
                        } else {
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                    }


                });
            }

            private void submitOrderAssignedU(int position) {

                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }

                WorkOrder workOrder = new WorkOrder();
                workOrder.setServiceUserId(mListU.get(position).getId());
                workOrder.setId(getArguments().getInt(WORKORDERID));

                RequestObject<WorkOrder> requestObject = new RequestObject<>();
                requestObject.setData(workOrder);

                String dataStr = new Gson().toJson(requestObject);
                System.out.println(dataStr);
                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


                String url = ServiceCall.getServerURL() + "/action/workOrder/assign";

                final Request request = new Request.Builder()
                        .url(url)//请求的url
                        .addHeader("Authorization"
                                , accessToken)
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
                        System.out.println("连接失败");
                    }

                    //异步请求(非主线程)
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        returnBody = response.body().string();

                        System.out.println("获取到的服务项数据：" + returnBody);
//                //转换返回数据格式
//                Type type = new TypeToken<NewsTypeData>() {
//                }.getType();
//                NewsTypeData data = new Gson().fromJson(returnBody, type);

                        Message msg = new Message();
                        if (response.isSuccessful()) {
                            msg.what = 3;
                            handler.sendMessage(msg);
                        } else {
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                    }


                });

            }

        });
    }
}