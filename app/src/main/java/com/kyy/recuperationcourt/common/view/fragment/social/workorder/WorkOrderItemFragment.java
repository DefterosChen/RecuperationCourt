package com.kyy.recuperationcourt.common.view.fragment.social.workorder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.adapter.MyAdapter;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemType;
import com.kyy.recuperationcourt.common.model.constant.workorder.OrderConsts;
import com.kyy.recuperationcourt.common.model.constant.workorder.OrderItemState;
import com.kyy.recuperationcourt.common.model.dto.QueryListDto;
import com.kyy.recuperationcourt.common.model.dto.QueryServiceItemDto;
import com.kyy.recuperationcourt.common.model.dto.QueryWorkOrderItemDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.order.ServiceItem;
import com.kyy.recuperationcourt.common.model.entity.order.WorkOrderItem;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.SocialFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.serviceitem.AddServiceItemFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.serviceitem.ServiceItemListFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.core.PageOption;
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
 * 工单服务子项列表
 * 2020/05/12
 * DefterosChen
 */
@Page(name = "工单服务子项列表", anim = CoreAnim.none)
public class WorkOrderItemFragment extends MyBaseFragment {
    public final static String FRAGMENT_NAME = "ServiceItemListFragment";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    Handler handler = null;
    String returnBody;

    List<WorkOrderItem> mList = new ArrayList<>();
    int pageNum = 0;//加载页数

    String workOrderId;


    MyAdapter myAdapter;

    public WorkOrderItemFragment() {

        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work_order_item;
    }

    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    addData();
                } else if (msg.what == 2) {
                    System.out.println("接口返回用户数据ok为false");
                } else if (msg.what == 3) {
                    System.out.println("操作成功，重新刷新页面");

                    mList.clear();
                    pageNum = 0;
                    getData();
                    myAdapter.notifyDataSetChanged();
                    mRefreshLayout.autoRefresh();
                }
                return false;
            }

            /**
             *
             */
            @SuppressLint("NewApi")
            private void addData() {
                try {

                    //转换返回数据格式
                    Type type = new TypeToken<ResponseObject<DataList<WorkOrderItem>>>() {
                    }.getType();

                    ResponseObject<DataList<WorkOrderItem>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().getRecords().size(); i++) {
                        WorkOrderItem workOrderItem = responseObject.getData().getRecords().get(i);
                        mList.add(workOrderItem);
                    }

                    System.out.println("数据接收后的list：" + mList.size());
                    ToastUtil.showMsg(Messages.LOADINGFINISHED);
                    myAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("导入服务项数据出错！" + e.getMessage());
                }
            }
        });
    }


    @Override
    protected void initViews() {
        workOrderId = getArguments().getString(OrderConsts.ORDERID);

        // 创建消息管理器
        createHandlerManage();
        WidgetUtils.initRecyclerView(mRecyclerView);

        //初始化列表
        getList();

        getData();
    }

    private void getList() {

        myAdapter = new MyAdapter<WorkOrderItem>(mList, R.layout.item_kyy_list_workorderitem) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, WorkOrderItem item, int position) {
                item = mList.get(position);

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + item.getName());
                holder.text(R.id.tv_item_state, OrderItemState.getDesc(item.getState()));
                holder.text(R.id.tv_item_finishtime, item.getEndTime() == null ? OrderItemState.STATE_UNFINISH.getDesc() : item.getEndTime());

            }
        };
        mRecyclerView.setAdapter(myAdapter);
    }

    private void getData() {


        pageNum++;

        String accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }


        QueryWorkOrderItemDto queryWorkOrderItemDto = new QueryWorkOrderItemDto();
        queryWorkOrderItemDto.setSize(10);
        queryWorkOrderItemDto.setCurrent(pageNum);
        queryWorkOrderItemDto.setOrder_id(workOrderId);

        RequestObject<QueryWorkOrderItemDto> requestObject = new RequestObject<>();
        requestObject.setData(queryWorkOrderItemDto);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/workOrderItem/selectList";

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

                System.out.println("获取到的服务子项数据：" + returnBody);
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
            @SuppressLint("NewApi")
            @Override
            public void onItemClick(View itemView, int position) {

                int state = mList.get(position).getState();

                if (state == OrderItemState.STATE_FINISH.getCode()){
                    return;
                }

                String str = OrderConsts.ORDERITEM_BUTTON_STATE_0;

                if (state == OrderItemState.STATE_EXCUTING.getCode()) {
                    str = OrderConsts.ORDERITEM_BUTTON_STATE_1;
                }

                new AlertDialog.Builder(getContext())
                        .setMessage("请进行工单服务项操作")
                        .setPositiveButton(str, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updateItemInfo(position);
                            }
                        })
                        .show();
            }

            private void updateItemInfo(int position) {

                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }


                WorkOrderItem workOrderItem = new WorkOrderItem();
                workOrderItem.setId(mList.get(position).getId());

                RequestObject<WorkOrderItem> requestObject = new RequestObject<>();
                requestObject.setData(workOrderItem);

                String dataStr = new Gson().toJson(requestObject);
                System.out.println(dataStr);
                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


                String url = ServiceCall.getServerURL() + "/action/workOrderItem/updateState";

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

                        System.out.println("获取到的服务子项数据：" + returnBody);
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


        /**
         * 下拉加载
         */
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                System.out.println("加载更多");
                getData();
                mRefreshLayout.finishLoadMore(500);
            }
        });

        /**
         * 上拉刷新
         */
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                System.out.println("重新加载");

                mList.clear();
                pageNum = 0;
                getData();

                myAdapter.notifyDataSetChanged();

                mRefreshLayout.finishRefresh(500);
            }
        });

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        mRefreshLayout.autoRefresh();
    }
}
