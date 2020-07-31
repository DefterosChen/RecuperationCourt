package com.kyy.recuperationcourt.common.view.fragment.social.elder;

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
import com.kyy.recuperationcourt.common.model.constant.SexType;
import com.kyy.recuperationcourt.common.model.constant.elder.UserElderConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.dto.QueryUserElderDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.order.ServiceItem;
import com.kyy.recuperationcourt.common.model.entity.user.SysUser;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.SocialFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.serviceitem.AddServiceItemFragment;
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
 * 长者列表
 * 2020/07/23
 * DefterosChen
 */
@Page(name = "长者列表", anim = CoreAnim.none)
public class ElderListFragment extends MyBaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_add)
    TextView textViewAdd;

    Handler handler = null;
    String returnBody;


    List<UserElder> mList = new ArrayList<>();
    int pageNum = 0;//加载页数

    MyAdapter myAdapter;

    String strFrom;
    Boolean isClick;


    public ElderListFragment() {

        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_elder_list;
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
                    Type type = new TypeToken<ResponseObject<DataList<UserElder>>>() {
                    }.getType();

                    ResponseObject<DataList<UserElder>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().getRecords().size(); i++) {
                        UserElder userElder = responseObject.getData().getRecords().get(i);
                        mList.add(userElder);
                    }

                    System.out.println("数据接收后的list：" + mList.size());
                    ToastUtil.showMsg(Messages.LOADINGFINISHED);
                    myAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("导入长者数据出错！" + e.getMessage());
                }
            }
        });

    }


    @Override
    protected void initViews() {

        strFrom = getArguments().getString(Messages.FRAGMENTFROMWHERE);

        switch (strFrom) {
            case SocialFragment
                    .FRAGMENT_NAME:
                isClick = false;
                break;
            case AddElderHealthFragment
                    .FRAGMENT_NAME:
            case AddWorkOrderFragment
                    .FRAGMENT_NAME:
                isClick = true;
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

        myAdapter = new MyAdapter<UserElder>(mList, R.layout.item_kyy_list_elder) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, UserElder item, int position) {
                item = mList.get(position);

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + item.getName());
                holder.text(R.id.tv_item_sex, SexType.getName(item.getSex()));
                holder.text(R.id.tv_item_phone, UserElderConst.TEXT_MOBILE + item.getMobile());
                holder.text(R.id.tv_item_birth, UserElderConst.TEXT_BIRTH + item.getBirth());
                holder.text(R.id.tv_item_addressbirth, UserElderConst.TEXT_RESADDRESS + item.getResidenceAddress());
                holder.text(R.id.tv_item_addressnow, UserElderConst.TEXT_ADDRESS + item.getAddress());
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

        UserElder userElder = new UserElder();
        userElder.setIsDelete(Consts.DELETED_NO);

        QueryUserElderDto queryUserElderDto = new QueryUserElderDto();
        queryUserElderDto.setSize(10);
        queryUserElderDto.setCurrent(pageNum);
        queryUserElderDto.setUserElder(userElder);

        RequestObject<QueryUserElderDto> requestObject = new RequestObject<>();
        requestObject.setData(queryUserElderDto);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/userElder/selectList"; // current :页数   size :单次刷新条数 固定100条

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

                System.out.println("获取到的长者数据：" + returnBody);
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

                if (!isClick) {
                    editItemData(position);
                    return;
                }

                Intent intent = new Intent()
                        .putExtra(UserElderConst.USERELDER_ID, mList.get(position).getId())
                        .putExtra(UserElderConst.USERELDER_NAME, mList.get(position).getName());
                setFragmentResult(UserElderConst.USERELDER_RESPONSE_CODE, intent);
                //回到指定的页面
                popToBack();

            }

            //对该项数据进行操作
            private void editItemData(int position) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(UserElderConst.PASSOBJECT, mList.get(position));

                PageOption.to(AddElderFragment.class)
                        .setRequestCode(100)
                        .setBundle(bundle)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_MODIFY)
                        .open(ElderListFragment.this);



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
                        .setMessage("是否要删除这位长者信息")
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


                                                UserElder userElder = new UserElder();
                                                userElder.setId(mList.get(position).getId());
                                                userElder.setIsDelete(Consts.DELETED_YES);
                                                RequestObject<UserElder> requestObject = new RequestObject<>();
                                                requestObject.setData(userElder);

                                                String dataStr = new Gson().toJson(requestObject);
                                                System.out.println(dataStr);
                                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


                                                String url = ServiceCall.getServerURL() + "/action/userElder/isDelete";

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

                mList = new ArrayList<>();
                pageNum = 0;

                getData();

                myAdapter.notifyDataSetChanged();

                mRefreshLayout.finishRefresh(500);
            }
        });

        /**
         * 新增长者信息
         */
        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("新增长者信息");
                PageOption.to(AddElderFragment.class)
                        .setRequestCode(100)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_ADD)
                        .open(ElderListFragment.this);

            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        mRefreshLayout.autoRefresh();
    }

}
