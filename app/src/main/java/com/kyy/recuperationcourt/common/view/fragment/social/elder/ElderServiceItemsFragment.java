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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemType;
import com.kyy.recuperationcourt.common.model.dto.OrderByItmeUserDto;
import com.kyy.recuperationcourt.common.model.dto.QueryUserElderDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.order.ServiceItem;
import com.kyy.recuperationcourt.common.other.widget.dialog.BaseDialog;
import com.kyy.recuperationcourt.common.other.widget.dialog.InputDialog;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.SocialFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.workorder.AddWorkOrderFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.core.PageOption;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xutil.data.DateUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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
 * 长者过往服务项目
 * 2020/07/22
 * DefterosChen
 */
@Page(name = "长者过往服务项目", anim = CoreAnim.none)
public class ElderServiceItemsFragment extends MyBaseFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    public ElderServiceItemsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_elder_service_items;
    }


    Handler handler = null;
    String returnBody;


    List<OrderByItmeUserDto> mList = new ArrayList<>();
    int pageNum = 0;//加载页数

    MyAdapter myAdapter;

    int elderId;

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
                    Type type = new TypeToken<ResponseObject<List<OrderByItmeUserDto>>>() {
                    }.getType();

                    ResponseObject<List<OrderByItmeUserDto>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().size(); i++) {
                        OrderByItmeUserDto orderByItmeUserDto = responseObject.getData().get(i);
                        mList.add(orderByItmeUserDto);
                    }

                    System.out.println("数据接收后的list：" + mList.size());
                    ToastUtil.showMsg(Messages.LOADINGFINISHED);
                    myAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("导入数据出错！" + e.getMessage());
                }
            }
        });

    }


    @Override
    protected void initViews() {

        elderId = getArguments().getInt(ElderHealthConsts.PASS_ID);

        // 创建消息管理器
        createHandlerManage();
        WidgetUtils.initRecyclerView(mRecyclerView);


        //初始化列表
        getList();

        getData();


    }


    private void getList() {

        myAdapter = new MyAdapter<OrderByItmeUserDto>(mList, R.layout.item_kyy_list_elderservice) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, OrderByItmeUserDto item, int position) {
                item = mList.get(position);

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + UserElderConst.ELDERSERVICE_NAME + item.getServiceName());
                holder.text(R.id.tv_item_type, UserElderConst.ELDERSERVICE_TYPE + ServiceItemType.getDesc(item.getType()));
                holder.text(R.id.tv_item_price, UserElderConst.ELDERSERVICE_PRICE + item.getPrice() + UserElderConst.ELDERSERVICE_PRICE_UNIT);
                holder.text(R.id.tv_item_count, UserElderConst.ELDERSERVICE_COUNT + item.getCount());
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


        RequestObject<Integer> requestObject = new RequestObject<>();
        requestObject.setData(elderId);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/workOrder/elderItmes"; // current :页数   size :单次刷新条数 固定100条

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

    }


}