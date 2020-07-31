package com.kyy.recuperationcourt.common.view.fragment.social.elder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.kyy.recuperationcourt.common.model.constant.SexType;
import com.kyy.recuperationcourt.common.model.constant.elder.ElderHealthConsts;
import com.kyy.recuperationcourt.common.model.constant.elder.UserElderConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemCategory;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.dto.QueryListDto;
import com.kyy.recuperationcourt.common.model.dto.QueryUserElderDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.elder.HealthManagement;
import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.SocialFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.serviceitem.ServiceItemListFragment;
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
 * 长者健康列表
 * 2020/07/29
 * DefterosChen
 */
@Page(name = "长者健康列表", anim = CoreAnim.none)
public class ElderHealthListFragment extends MyBaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_add)
    TextView textViewAdd;

    Handler handler = null;
    String returnBody;


    List<HealthManagement> mList = new ArrayList<>();
    int pageNum = 0;//加载页数

    MyAdapter myAdapter;


    public ElderHealthListFragment() {

        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_elder_health_list;
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
                    Type type = new TypeToken<ResponseObject<DataList<HealthManagement>>>() {
                    }.getType();

                    ResponseObject<DataList<HealthManagement>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().getRecords().size(); i++) {
                        HealthManagement healthManagement = responseObject.getData().getRecords().get(i);
                        mList.add(healthManagement);
                    }

                    System.out.println("数据接收后的list：" + mList.size());
                    ToastUtil.showMsg(Messages.LOADINGFINISHED);
                    myAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("导入长者健康数据出错！" + e.getMessage());
                }
            }
        });

    }


    @Override
    protected void initViews() {

        // 创建消息管理器
        createHandlerManage();
        WidgetUtils.initRecyclerView(mRecyclerView);


        //初始化列表
        getList();

        getData();


    }


    private void getList() {

        myAdapter = new MyAdapter<HealthManagement>(mList, R.layout.item_kyy_list_elderhealth) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, HealthManagement item, int position) {

                item = mList.get(position);

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + item.getElderName());
                holder.text(R.id.tv_item_height, ElderHealthConsts.TEXT_HEIGHT + item.getHeight() + ElderHealthConsts.TEXT_HEIGHT_UNIT);
                holder.text(R.id.tv_item_weight, ElderHealthConsts.TEXT_WEIGHT + item.getWeight() + ElderHealthConsts.TEXT_WEIGHT_UNIT);
                holder.text(R.id.tv_item_creattime, ElderHealthConsts.TEXT_CREATTIME + item.getCreatTime());
                holder.text(R.id.tv_item_updatetime, ElderHealthConsts.TEXT_UPDATETIME + item.getUpdateTime());
                holder.text(R.id.tv_item_des, ElderHealthConsts.TEXT_DES + item.getDescription());
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

        QueryListDto queryListDto = new QueryListDto();
        queryListDto.setSize(10);
        queryListDto.setCurrent(pageNum);

        RequestObject<QueryListDto> requestObject = new RequestObject<>();
        requestObject.setData(queryListDto);


        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/healthManagement/selectList"; // current :页数   size :单次刷新条数 固定100条

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

        /**
         *  点击编辑修改
         */
        myAdapter.setOnItemClickListener(new SmartViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(ElderHealthConsts.PASSOBJECT, mList.get(position));

                PageOption.to(AddElderHealthFragment.class)
                        .setRequestCode(100)
                        .setBundle(bundle)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_MODIFY)
                        .open(ElderHealthListFragment.this);

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
                        .setMessage("是否要删除该项长者健康信息")
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


                                                HealthManagement healthManagement = new HealthManagement();
                                                healthManagement.setId(mList.get(position).getId());


                                                RequestObject<HealthManagement> requestObject = new RequestObject<>();
                                                requestObject.setData(healthManagement);


                                                String dataStr = new Gson().toJson(requestObject);
                                                System.out.println(dataStr);
                                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


                                                String url = ServiceCall.getServerURL() + "/action/healthManagement/isDelete";

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

                mRefreshLayout.finishRefresh();
            }
        });

        /**
         * 新增长者信息
         */
        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("新增长者健康信息");
                PageOption.to(AddElderHealthFragment.class)
                        .setRequestCode(100)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_ADD)
                        .open(ElderHealthListFragment.this);
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        mRefreshLayout.autoRefresh();
    }

}
