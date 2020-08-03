package com.kyy.recuperationcourt.common.view.fragment.social.workorder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.adapter.WorkOrderAdapter;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.constant.workorder.OrderConsts;
import com.kyy.recuperationcourt.common.model.constant.workorder.OrderState;
import com.kyy.recuperationcourt.common.model.constant.workorder.OrderType;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemType;
import com.kyy.recuperationcourt.common.model.constant.visitorder.VisitOrderState;
import com.kyy.recuperationcourt.common.model.dto.QueryOrderDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.order.VisitOrder;
import com.kyy.recuperationcourt.common.model.entity.order.WorkOrder;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
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
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.hutool.db.sql.Order;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 我的工单列表
 * 2020/05/12
 * DefterosChen
 */
@Page(name = "我的工单列表", anim = CoreAnim.none)
public class MyWorkOrderFragment extends MyBaseFragment implements SmartViewHolder.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    Handler handler = null;
    String returnBody;
    WorkOrderAdapter wadapter;

    List<WorkOrder> mList = new ArrayList<>();
    int pageNum = 0;//加载页数


    boolean isClick = false;

    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                if (msg.what == 1) {
                    getInfo();
                } else if (msg.what == 2) {
                    System.out.println("返回数据OK为false");
                } else if (msg.what == 3) {
                    System.out.println("操作成功");
                } else if (msg.what == 4) {
                    //自动刷新界面 重新加载获取数据
                    mRefreshLayout.autoRefresh();
                } else if (msg.what == 5) {
                    //显示回访单内容
                    getVisitInfo();
                }
                return false;
            }

            /**
             * 显示回访单内容
             */
            private void getVisitInfo() {
                try {
                    //转换返回数据格式
                    Type type = new TypeToken<ResponseObject<VisitOrder>>() {
                    }.getType();

                    ResponseObject<VisitOrder> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);
                    VisitOrder visitOrder = responseObject.getData();

                    View view = getLayoutInflater().inflate(R.layout.dialog_kyy_content_visitorder, null);
                    SuperTextView sTvWorkOrder = view.findViewById(R.id.menu_workordernum);
                    SuperTextView sTvServiceName = view.findViewById(R.id.menu_servicename);
                    SuperTextView sTvCreatMan = view.findViewById(R.id.menu_creatman);
                    SuperTextView sTvCreatTime = view.findViewById(R.id.menu_creattime);
                    TextView editText = view.findViewById(R.id.et_item_des);
                    editText.setEnabled(false);

                    sTvWorkOrder.setRightString(visitOrder.getOrderId());
                    sTvServiceName.setRightString(visitOrder.getServiceName());
                    sTvCreatMan.setRightString(visitOrder.getVisitManName());
                    sTvCreatTime.setRightString(visitOrder.getCreatTime());
                    editText.setText(visitOrder.getContent());


                    AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setMessage("回访单")
                            .setView(view)
                            .show();

                    if (isClick) {
                        sTvServiceName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                                //按次数的服务项应进入服务项页面进行查看
                                PageOption.to(WorkOrderItemFragment.class)
                                        .setRequestCode(100)
                                        .putString(OrderConsts.ORDERID, visitOrder.getOrderId())
                                        .open(MyWorkOrderFragment.this);
                            }
                        });
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("导入数据出错！" + e.getMessage());
                }
            }

            /**
             *  获取数据
             */
            private void getInfo() {
                try {

                    //转换返回数据格式
                    Type type = new TypeToken<ResponseObject<DataList<WorkOrder>>>() {
                    }.getType();

                    ResponseObject<DataList<WorkOrder>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().getRecords().size(); i++) {
                        WorkOrder workOrder = responseObject.getData().getRecords().get(i);
                        mList.add(workOrder);
                    }

                    System.out.println("数据接收后的list：" + mList.size());
                    ToastUtil.showMsg(Messages.LOADINGFINISHED);
                    wadapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("导入工单数据出错！" + e.getMessage());
                }

            }

        });
    }

    public MyWorkOrderFragment() {
        // Required empty public constructor
    }

    public static WorkOrderFragment newInstance() {
        WorkOrderFragment fragment = new WorkOrderFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_work_order;
    }

    @Override
    protected void initViews() {

        createHandlerManage();
        WidgetUtils.initRecyclerView(mRecyclerView);

        //初始化列表
        getList();

        getData();

    }

    public void getList() {

        wadapter = new WorkOrderAdapter(mList, R.layout.item_kyy_list_workorder) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, WorkOrder item, int position) {

                if (mList.size() == 0) {
                    return;
                }

                item = mList.get(position);

                holder.text(R.id.tv_item_num, String.valueOf(position + 1) + " ---id: " + item.getId());

                holder.text(R.id.tv_item_orderid, OrderConsts.WORKORDERID
                        + item.getWorkOrderId());

                holder.text(R.id.tv_item_orderstate, OrderState.getDesc(item.getState()));

                holder.text(R.id.tv_item_ordertype, OrderType.getDesc(item.getType()));

                holder.text(R.id.tv_item_orderprice, item.getPriceTotal() == null
                        ? OrderConsts.ORDERUNFINISHED : OrderConsts.PRICETOTAL + item.getPriceTotal());

                holder.text(R.id.tv_item_address, OrderConsts.ADDRESS
                        + item.getAddress());
                holder.text(R.id.tv_item_servicename, OrderConsts.SERVICENAME
                        + item.getServiceItemName());
                holder.text(R.id.tv_item_servicetype, OrderConsts.SERVICETYPE
                        + ServiceItemType.getDesc(item.getServiceItemType()));
                holder.text(R.id.tv_item_serviceprice, OrderConsts.SERVICEPRICE
                        + item.getServicePrice().toString());
                holder.text(R.id.tv_item_serviceman, item.getPriceTotal() == null
                        ? OrderConsts.SERVICEMAN_UNPOINTED : OrderConsts.SERVICEMAN + item.getServiceUserName());
                holder.text(R.id.tv_item_serviceelder, OrderConsts.ELDERMAN
                        + item.getElderUserName());
                holder.text(R.id.tv_item_des, OrderConsts.DES
                        + item.getOrderDes());
                holder.text(R.id.tv_item_creattime, OrderConsts.CREATTIME
                        + item.getCreateTime());
                holder.text(R.id.tv_item_consumingtime, item.getTimeConsuming() == null
                        ? "" : OrderConsts.COSUMINGTIME + item.getTimeConsuming() + OrderConsts.COSUMINGTIMEEND);
            }
        };

        mRecyclerView.setAdapter(wadapter);

    }

    /**
     * 获取本用户公司工单数据
     */
    private void getData() {

        pageNum++;

        String accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }

        int userId = ServiceCall.getUserInfo().getId();
        System.out.println("userID::" + userId);

        QueryOrderDto queryOrderDto = new QueryOrderDto();
        queryOrderDto.setCurrent(pageNum);
        queryOrderDto.setSize(10);
        queryOrderDto.setUserId(userId);

        RequestObject<QueryOrderDto> requestObject = new RequestObject<>();
        requestObject.setData(queryOrderDto);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/workOrder/selectList"; // current :页数   size :单次刷新条数 固定100条

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

                System.out.println("获取到的工单数据：" + returnBody);


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
    public void onItemClick(View itemView, int position) {

    }

    @Override
    protected void initListeners() {

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

                mList = new ArrayList<>();
                pageNum = 0;

                getData();

                wadapter.notifyDataSetChanged();

                mRefreshLayout.finishRefresh(500);
            }
        });


        wadapter.setOnItemClickListener(new SmartViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                System.out.println("进入详情页面窗口");
                doWork(position);
//                showDialog(position);
            }

            //执行工单操作
            @SuppressLint("NewApi")
            private void doWork(int position) {

                int state = mList.get(position).getState();

                switch (state) {
                    case 1:
                        showDialogAccept(position, state);
                        break;
                    case 2:
                        if (mList.get(position).getServiceItemType().equals(ServiceItemType.ITEM_NUM.getCode())) {
                            //按次数的服务项应进入服务项页面进行开工选择
                            getServiceItemList(position);
                            return;
                        }
                        showDialogState(position, state, Consts.WORKORDER_BEGIN);
                        break;
                    case 3:
                        if (mList.get(position).getServiceItemType().equals(ServiceItemType.ITEM_NUM.getCode())) {
                            //按次数的服务项应进入服务项页面进行开工选择
                            getServiceItemList(position);
                            return;
                        }
                        showDialogState(position, state, Consts.WORKORDER_EXCUTING);
                        break;
                    case 4:
                        showDialogVisit(position, state);
                        break;
                    case 5:


                        if (mList.get(position).getIsVisited() == VisitOrderState.NONORDER.getCode()) {

                            if (mList.get(position).getServiceItemType().equals(ServiceItemType.ITEM_NUM.getCode())) {
                                //按次数的服务项应进入服务项页面进行查看
                                getServiceItemList(position);
                                return;
                            }

                            return;
                        }

                        if (mList.get(position).getServiceItemType().equals(ServiceItemType.ITEM_NUM.getCode())) {
                            isClick = true;
                        } else {
                            isClick = false;
                        }

                        getVisitOrderData(position);


                        break;
                    default:
                        break;
                }


            }

            /**
             *  进入到工单生成的服务项列表页面
             * @param position
             */
            private void getServiceItemList(int position) {


                PageOption.to(WorkOrderItemFragment.class)
                        .setRequestCode(100)
                        .putString(OrderConsts.ORDERID, mList.get(position).getWorkOrderId())
                        .open(MyWorkOrderFragment.this);


            }

            //已结单状态下获取回访单数据
            private void getVisitOrderData(int position) {

                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }

                RequestObject<String> requestObject = new RequestObject<>();
                requestObject.setData(mList.get(position).getWorkOrderId());

                String dataStr = new Gson().toJson(requestObject);
                System.out.println(dataStr);
                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                String url = ServiceCall.getServerURL() + "/action/visitOrder/findById"; // current :页数   size :单次刷新条数 固定100条

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

                        System.out.println("获取到的数据：" + returnBody);

                        Message msg = new Message();
                        if (response.isSuccessful()) {
                            msg.what = 5;
                            handler.sendMessage(msg);
                        } else {
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                    }
                });


            }

            private void showDialogVisit(int position, int state) {

                new AlertDialog.Builder(getContext())
                        .setMessage("工单操作")
                        .setPositiveButton(Consts.WORKORDER_VISIT, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                addVisitOrder();

                            }

                            //生成回访单
                            private void addVisitOrder() {

                                View view = getLayoutInflater().inflate(R.layout.dialog_input_content, null);
                                MultiLineEditText editText = view.findViewById(R.id.et_item_des);


                                new AlertDialog.Builder(getContext())
                                        .setMessage("生成回访单")
                                        .setView(view)
                                        .setPositiveButton(R.string.kyy_workorder_submit, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                if (editText.getContentText().toString().isEmpty()) {
                                                    ToastUtil.showTips(R.string.kyy_content_isempty);
                                                    return;
                                                }

                                                submitOrder();

                                            }

                                            private void submitOrder() {

                                                String accessToken = ServiceCall.getUserInfoToken();

                                                if (accessToken == null || accessToken.equals("")) {
                                                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                                                    return;
                                                }

                                                WorkOrder workOrder = new WorkOrder();
                                                workOrder.setId(mList.get(position).getId());

                                                VisitOrder visitOrder = new VisitOrder();
                                                visitOrder.setOrderId(mList.get(position).getWorkOrderId());
                                                visitOrder.setContent(editText.getContentText().toString());

                                                RequestObject<VisitOrder> requestObject = new RequestObject<>();
                                                requestObject.setData(visitOrder);

                                                String dataStr = new Gson().toJson(requestObject);
                                                System.out.println(dataStr);
                                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                                                String url = ServiceCall.getServerURL() + "/action/visitOrder/add";

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

                                                        System.out.println("获取到的数据：" + returnBody);

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
                                        })
                                        .show();
                            }
                        })
                        .setNegativeButton(Consts.WORKORDER_FINISH, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishOrder();
                            }

                            //直接终结工单
                            @SuppressLint("NewApi")
                            private void finishOrder() {

                                String accessToken = ServiceCall.getUserInfoToken();

                                if (accessToken == null || accessToken.equals("")) {
                                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                                    return;
                                }

                                WorkOrder workOrder = new WorkOrder();
                                workOrder.setId(mList.get(position).getId());
                                workOrder.setState(OrderState.ORDER_COMPLETED.getCode());

                                RequestObject<WorkOrder> requestObject = new RequestObject<>();
                                requestObject.setData(workOrder);

                                String dataStr = new Gson().toJson(requestObject);
                                System.out.println(dataStr);
                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                                String url = ServiceCall.getServerURL() + "/action/workOrder/updateState"; // current :页数   size :单次刷新条数 固定100条

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

                                        System.out.println("获取到的数据：" + returnBody);

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
                        })
                        .show();

            }

            //待开工、 待完工 状态下的操作
            private void showDialogState(int position, int state, String str) {


                new AlertDialog.Builder(getContext())
                        .setMessage("工单操作")
                        .setPositiveButton(str, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                beginOrder();

                            }

                            //修改状态
                            @SuppressLint("NewApi")
                            private void beginOrder() {
                                String accessToken = ServiceCall.getUserInfoToken();

                                if (accessToken == null || accessToken.equals("")) {
                                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                                    return;
                                }

                                WorkOrder workOrder = new WorkOrder();
                                workOrder.setId(mList.get(position).getId());
                                workOrder.setState(state + 1);

                                RequestObject<WorkOrder> requestObject = new RequestObject<>();
                                requestObject.setData(workOrder);

                                String dataStr = new Gson().toJson(requestObject);
                                System.out.println(dataStr);
                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                                String url = ServiceCall.getServerURL() + "/action/workOrder/updateState"; // current :页数   size :单次刷新条数 固定100条

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

                                        System.out.println("获取到的数据：" + returnBody);

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
                        })
                        .show();
            }

            private void showDialogAccept(int position, int state) {

                new AlertDialog.Builder(getContext())
                        .setMessage("工单操作")
                        .setPositiveButton(R.string.kyy_social_acceptOrder, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                orderAccept();

                            }

                            /**
                             * 确认接受工单
                             */
                            private void orderAccept() {
                                String accessToken = ServiceCall.getUserInfoToken();

                                if (accessToken == null || accessToken.equals("")) {
                                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                                    return;
                                }


                                RequestObject<String> requestObject = new RequestObject<>();
                                requestObject.setData(mList.get(position).getWorkOrderId());

                                String dataStr = new Gson().toJson(requestObject);
                                System.out.println(dataStr);
                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                                String url = ServiceCall.getServerURL() + "/action/workOrder/confirm";

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

                                        System.out.println("获取到的数据：" + returnBody);

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

                        })
                        .show();

            }


        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        mRefreshLayout.autoRefresh();
    }
}
