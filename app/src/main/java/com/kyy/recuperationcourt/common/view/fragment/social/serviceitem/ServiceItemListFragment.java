package com.kyy.recuperationcourt.common.view.fragment.social.serviceitem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.adapter.MyAdapter;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.elder.ElderHealthConsts;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemType;
import com.kyy.recuperationcourt.common.model.dto.QueryServiceItemDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.elder.HealthManagement;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.order.ServiceItem;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.SocialFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.elder.AddElderFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.elder.AddElderHealthFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.elder.ElderHealthListFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.elder.ElderListFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.workorder.AddWorkOrderFragment;
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
 * 服务项列表
 * 2020/07/23
 * DefterosChen
 */
@Page(name = "服务项列表", anim = CoreAnim.none)
public class ServiceItemListFragment extends MyBaseFragment {
    public final static String FRAGMENT_NAME = "ServiceItemListFragment";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_add)
    TextView textViewAdd;

    Handler handler = null;
    String returnBody;

    List<ServiceItem> mList = new ArrayList<>();
    int pageNum = 0;//加载页数

    int mServiceItemType;

    String strFrom;

    MyAdapter myAdapter;

    public ServiceItemListFragment() {

        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service_item_list;
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
                    System.out.println("删除操作成功，重新刷新页面");

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
                    Type type = new TypeToken<ResponseObject<DataList<ServiceItem>>>() {
                    }.getType();

                    ResponseObject<DataList<ServiceItem>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().getRecords().size(); i++) {
                        ServiceItem serviceItem = responseObject.getData().getRecords().get(i);
                        mList.add(serviceItem);
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
        mServiceItemType = getArguments().getInt(ServiceItemConst.SERVICEITEM_CATEGORY);
        strFrom = getArguments().getString(Messages.FRAGMENTFROMWHERE);


        switch (strFrom) {
            case SocialFragment
                    .FRAGMENT_NAME:
                break;

            case AddWorkOrderFragment
                    .FRAGMENT_NAME:
                textViewAdd.setVisibility(View.GONE);
                break;
        }

        // 创建消息管理器
        createHandlerManage();
        WidgetUtils.initRecyclerView(mRecyclerView);

        //初始化列表
        getList();

        getData();
    }

    private void getList() {

        myAdapter = new MyAdapter<ServiceItem>(mList, R.layout.item_kyy_list_serviceitem) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, ServiceItem item, int position) {
                item = mList.get(position);

                if (item.getCycle() == 0) {
                    holder.visible(R.id.tv_item_num, View.GONE);
                }

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + item.getServiceName());
                holder.text(R.id.tv_item_type, ServiceItemConst.TEXT_TYPE + ServiceItemType.getDesc(item.getType()));
                holder.text(R.id.tv_item_num, ServiceItemConst.TEXT_NUM + item.getCycle());

                holder.text(R.id.tv_item_des, ServiceItemConst.TEXT_DES + item.getServiceDes());
                holder.text(R.id.tv_item_price, (item.getCycle() == 0 ? ServiceItemConst.TEXT_PRICE_T : ServiceItemConst.TEXT_PRICE_N)
                        + item.getPrice() + ServiceItemConst.TEXT_PRICE);
                holder.text(R.id.tv_item_creattime, ServiceItemConst.TEXT_CREATTIME + item.getCreatTime());
                holder.text(R.id.tv_item_updatetime, ServiceItemConst.TEXT_UPDATETIME + item.getUpdateTime());
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

        ServiceItem serviceItem = new ServiceItem();
        serviceItem.setIsDelete(Consts.DELETED_NO);
        serviceItem.setServiceCategory(mServiceItemType);

        QueryServiceItemDto queryServiceItemDto = new QueryServiceItemDto();
        queryServiceItemDto.setSize(10);
        queryServiceItemDto.setCurrent(pageNum);
        queryServiceItemDto.setServiceItem(serviceItem);

        RequestObject<QueryServiceItemDto> requestObject = new RequestObject<>();
        requestObject.setData(queryServiceItemDto);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/serviceItem/selectList";

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

                switch (strFrom) {
                    case SocialFragment
                            .FRAGMENT_NAME:
                        editItem(position);
                        break;
                    case AddWorkOrderFragment
                            .FRAGMENT_NAME:
                        jumpItem(position);
                        break;
                }
            }

            // 新增选项时跳转
            private void jumpItem(int position) {
                Intent intent = new Intent()
                        .putExtra(ServiceItemConst.SERVICEITEM_ID, mList.get(position).getServiceId())
                        .putExtra(ServiceItemConst.SERVICEITEM_NAME, mList.get(position).getServiceName());
                setFragmentResult(ServiceItemConst.SERVICEITEM_RESPONSE_CODE, intent);
                //回到指定的页面
                popToBack();
            }

            //服务项列表查看时使用
            private void editItem(int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ServiceItemConst.PASSOBJECT, mList.get(position));

                PageOption.to(AddServiceItemFragment.class)
                        .setRequestCode(100)
                        .setBundle(bundle)
                        .putInt(ServiceItemConst.SERVICEITEM_CATEGORY, mServiceItemType)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_MODIFY)
                        .open(ServiceItemListFragment.this);
            }
        });

        /**
         * 长按 删除
         */
        myAdapter.setOnItemLongClickListener(new SmartViewHolder.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int position) {

                deleteInfo(position);
            }

            private void deleteInfo(int position) {


                new AlertDialog.Builder(getContext())
                        .setMessage("是否要删除该服务项信息")
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                new AlertDialog.Builder(getContext())
                                        .setMessage("删除后不可恢复，请进行确认")
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                doDelete(position);
                                            }

                                            //删除操作
                                            private void doDelete(int position) {


                                                String accessToken = ServiceCall.getUserInfoToken();

                                                if (accessToken == null || accessToken.equals("")) {
                                                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                                                    return;
                                                }


                                                ServiceItem serviceItem = new ServiceItem();
                                                serviceItem.setServiceId(mList.get(position).getServiceId());
                                                serviceItem.setIsDelete(Consts.DELETED_YES);
                                                RequestObject<ServiceItem> requestObject = new RequestObject<>();
                                                requestObject.setData(serviceItem);

                                                String dataStr = new Gson().toJson(requestObject);
                                                System.out.println(dataStr);
                                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


                                                String url = ServiceCall.getServerURL() + "/action/serviceItem/isDelete";

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
                                        })
                                        .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();

                            }


                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();


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


        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PageOption.to(AddServiceItemFragment.class)
                        .setRequestCode(100)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_ADD)
                        .putInt(ServiceItemConst.SERVICEITEM_CATEGORY, mServiceItemType)
                        .open(ServiceItemListFragment.this);

            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        mRefreshLayout.autoRefresh();
    }
}

