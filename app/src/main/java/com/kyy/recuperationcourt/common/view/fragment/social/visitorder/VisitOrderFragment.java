package com.kyy.recuperationcourt.common.view.fragment.social.visitorder;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.adapter.MyAdapter;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.visitorder.VisitOrderConsts;
import com.kyy.recuperationcourt.common.model.dto.QueryListDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.order.VisitOrder;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
 * 回访单列表
 * 2020/07/29
 * DefterosChen
 */
@Page(name = "回访单列表", anim = CoreAnim.none)
public class VisitOrderFragment extends MyBaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    Handler handler = null;
    String returnBody;


    List<VisitOrder> mList = new ArrayList<>();
    int pageNum = 0;//加载页数

    MyAdapter myAdapter;


    public VisitOrderFragment() {

        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_visit_order;
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
                    Type type = new TypeToken<ResponseObject<DataList<VisitOrder>>>() {
                    }.getType();

                    ResponseObject<DataList<VisitOrder>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().getRecords().size(); i++) {
                        VisitOrder visitOrder = responseObject.getData().getRecords().get(i);
                        mList.add(visitOrder);
                    }

                    System.out.println("数据接收后的list：" + mList.size());
                    ToastUtil.showMsg(Messages.LOADINGFINISHED);
                    myAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("导入回访单数据出错！" + e.getMessage());
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

        myAdapter = new MyAdapter<VisitOrder>(mList, R.layout.item_kyy_list_visitorder) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, VisitOrder item, int position) {
                item = mList.get(position);

                holder.text(R.id.tv_item_workorderid, String.valueOf(position + 1)+
                        VisitOrderConsts.ORDERID+ item.getOrderId());
                holder.text(R.id.tv_item_servicename,VisitOrderConsts.SERVICENAME+ item.getServiceName());
                holder.text(R.id.tv_item_visitman,VisitOrderConsts.VISITMAN+ item.getVisitManName());
                holder.text(R.id.tv_item_creattime,VisitOrderConsts.CREATTIME+ item.getCreatTime());
                holder.text(R.id.tv_item_content, VisitOrderConsts.VISITCONTENT+item.getContent());
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


        String url = ServiceCall.getServerURL() + "/action/visitOrder/selectList";

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

    }



}
