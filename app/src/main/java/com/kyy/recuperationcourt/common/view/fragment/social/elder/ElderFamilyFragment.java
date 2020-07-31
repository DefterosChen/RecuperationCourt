package com.kyy.recuperationcourt.common.view.fragment.social.elder;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.kyy.recuperationcourt.common.model.constant.elder.ElderHealthConsts;
import com.kyy.recuperationcourt.common.model.constant.elder.UserElderConst;
import com.kyy.recuperationcourt.common.model.dto.QueryMemberFamilyDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.elder.MemberFamily;
import com.kyy.recuperationcourt.common.model.entity.order.DataList;
import com.kyy.recuperationcourt.common.model.entity.user.Family;
import com.kyy.recuperationcourt.common.other.widget.dialog.BaseDialog;
import com.kyy.recuperationcourt.common.other.widget.dialog.InputDialog;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.core.PageOption;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

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
 * 长者家庭信息
 * 2020/07/31
 * DefterosChen
 */
@Page(name = "长者家庭信息", anim = CoreAnim.none)
public class ElderFamilyFragment extends MyBaseFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_add)
    TextView textViewAdd;
    @BindView(R.id.tv_familyinfo)
    TextView textViewFamily;

    public ElderFamilyFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_elder_family;
    }


    Handler handler = null;
    String returnBody;


    List<MemberFamily> mList = new ArrayList<>();
    int pageNum = 0;//加载页数

    MyAdapter myAdapter;

    int elderId;


    View view;
    SuperTextView stvName;
    SuperTextView stvCount;
    EditText etAddress;

    Family family = new Family();

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
                } else if (msg.what == 4) {
                    System.out.println("展示家庭信息");
                    showData();
                } else if (msg.what == 5) {
                    ToastUtil.showTips("信息提交成功");
                    getFamilyData();
                }
                return false;
            }

            /**
             * 显示家庭信息
             */
            private void showData() {

                //转换返回数据格式
                Type type = new TypeToken<ResponseObject<Family>>() {
                }.getType();

                ResponseObject<Family> responseObject = new ResponseObject<>();
                responseObject = new Gson().fromJson(returnBody, type);

                if (responseObject.getData() == null) {
                    return;
                }

                family = responseObject.getData();
                stvName.setRightString(family.getAppellation());
                stvCount.setRightString(family.getNumberPeople().toString());
                etAddress.setText(family.getHomeAddress());

            }


            /**
             *
             */
            @SuppressLint("NewApi")
            private void addData() {
                try {

                    //转换返回数据格式
                    Type type = new TypeToken<ResponseObject<DataList<MemberFamily>>>() {
                    }.getType();

                    ResponseObject<DataList<MemberFamily>> responseObject = new ResponseObject<>();
                    responseObject = new Gson().fromJson(returnBody, type);

                    for (int i = 0; i < responseObject.getData().getRecords().size(); i++) {
                        MemberFamily memberFamily = responseObject.getData().getRecords().get(i);
                        mList.add(memberFamily);
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
        view = getLayoutInflater().inflate(R.layout.dialog_kyy_content_elderfamily, null);
        stvName = view.findViewById(R.id.menu_name);
        stvCount = view.findViewById(R.id.menu_familycount);
        etAddress = view.findViewById(R.id.et_item_address);

        elderId = getArguments().getInt(ElderHealthConsts.PASS_ID);

        // 创建消息管理器
        createHandlerManage();
        WidgetUtils.initRecyclerView(mRecyclerView);


        //初始化列表
        getList();

        getData();
    }

    private void getList() {

        myAdapter = new MyAdapter<MemberFamily>(mList, R.layout.item_kyy_list_elderfamily) {
            @SuppressLint("NewApi")
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, MemberFamily item, int position) {
                item = mList.get(position);

                holder.text(R.id.tv_item_name, String.valueOf(position + 1) + UserElderConst.TEXT_NAME + item.getName());
                holder.text(R.id.tv_item_sex, UserElderConst.TEXT_SEX + SexType.getName(item.getSex()));
                holder.text(R.id.tv_item_relation, UserElderConst.ELDERFAMILY_RELATIONSHIP + item.getRelationship());
                holder.text(R.id.tv_item_mobile, UserElderConst.TEXT_MOBILE + item.getMobile());
                holder.text(R.id.tv_item_address, UserElderConst.TEXT_ADDRESS + item.getAddress());
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

        QueryMemberFamilyDto queryMemberFamilyDto = new QueryMemberFamilyDto();
        queryMemberFamilyDto.setCurrent(pageNum);
        queryMemberFamilyDto.setSize(10);
        queryMemberFamilyDto.setUserElderId(elderId);

        RequestObject<QueryMemberFamilyDto> requestObject = new RequestObject<>();
        requestObject.setData(queryMemberFamilyDto);

        String dataStr = new Gson().toJson(requestObject);
        System.out.println(dataStr);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


        String url = ServiceCall.getServerURL() + "/action/memberFamily/listPage";

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

        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PageOption.to(AddElderMemberFragment.class)
                        .setRequestCode(100)
                        .putInt(UserElderConst.USERELDER_ID, elderId)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_ADD)
                        .open(ElderFamilyFragment.this);
            }

        });

        textViewFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFamilyInfo();
            }

            /**
             * 展示家庭信息
             */
            private void showFamilyInfo() {

                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                getFamilyData();

                AlertDialog mDialog = new AlertDialog.Builder(getContext())
                        .setTitle("家庭信息")
                        .setView(view)
                        .setPositiveButton("提交", null)
                        .setNegativeButton("返回", null)
                        .create();

                mDialog.setCancelable(false);

                mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        //确定按键
                        Button positiveButton = mDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!isEmptyInfo()) {
                                    ToastUtil.showTips(Messages.ISFILLCOMPLETED);
                                    return;
                                }
                                mDialog.dismiss();
                                sentData();

                            }

                            /**
                             * 提交数据
                             */
                            private void sentData() {
                                String accessToken = ServiceCall.getUserInfoToken();

                                RequestObject<Family> requestObject = new RequestObject<>();
                                requestObject.setData(family);

                                String dataStr = new Gson().toJson(requestObject);
                                System.out.println(dataStr);
                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                                String url = ServiceCall.getServerURL() + "/action/family/updateOrInsert";

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
                                            msg.what = 5;
                                            handler.sendMessage(msg);
                                        } else {
                                            msg.what = 2;
                                            handler.sendMessage(msg);
                                        }
                                    }

                                });


                            }

                            private boolean isEmptyInfo() {

                                if (stvName.getRightString() == null
                                        || stvCount.getRightString() == null) {
                                    return false;
                                }


                                if (etAddress.getText().toString().trim().isEmpty()) {
                                    return false;
                                }


                                family.setAppellation(stvName.getRightString());
                                family.setNumberPeople(Integer.valueOf(stvCount.getRightString()));
                                family.setHomeAddress(etAddress.getText().toString());
                                family.setUserElderId(elderId);

                                return true;
                            }
                        });

                        //取消按键
                        Button negativeButton = mDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        negativeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();

                            }
                        });
                    }
                });

                mDialog.show();


                stvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new InputDialog.Builder(getActivity())
                                // 标题可以不用填写
                                .setTitle(getString(R.string.kyy_elder_family_fill_appellation))
                                .setContent(stvName.getRightString())
                                //.setHint(getString(R.string.personal_data_name_hint))
                                //.setConfirm("确定")
                                // 设置 null 表示不显示取消按钮
                                //.setCancel("取消")
                                // 设置点击按钮后不关闭对话框
                                //.setAutoDismiss(false)
                                .setListener(new InputDialog.OnListener() {

                                    @Override
                                    public void onConfirm(BaseDialog dialog, String content) {
                                        if (!stvName.getRightString().equals(content)) {
                                            stvName.setRightString(content);
                                        }

                                        BaseUtils.hideKeyboard(getActivity());
                                    }

                                    @Override
                                    public void onCancel(BaseDialog dialog) {
                                        BaseUtils.hideKeyboard(getActivity());

                                    }
                                })
                                .show();
                    }
                });

                stvCount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new InputDialog.Builder(getActivity())
                                // 标题可以不用填写
                                .setTitle(getString(R.string.kyy_elder_family_fill_familycount))
                                .setContent(stvCount.getRightString())
                                //.setHint(getString(R.string.personal_data_name_hint))
                                //.setConfirm("确定")
                                // 设置 null 表示不显示取消按钮
                                //.setCancel("取消")
                                // 设置点击按钮后不关闭对话框
                                //.setAutoDismiss(false)
                                .setListener(new InputDialog.OnListener() {

                                    @Override
                                    public void onConfirm(BaseDialog dialog, String content) {
                                        if (!stvCount.getRightString().equals(content)) {
                                            stvCount.setRightString(content);
                                        }

                                        BaseUtils.hideKeyboard(getActivity());
                                    }

                                    @Override
                                    public void onCancel(BaseDialog dialog) {
                                        BaseUtils.hideKeyboard(getActivity());

                                    }
                                })
                                .show();
                    }
                });


            }

        });

        myAdapter.setOnItemClickListener(new SmartViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(UserElderConst.ELDERFAMILY_PASSOBJECT, mList.get(position));

                PageOption.to(AddElderMemberFragment.class)
                        .setRequestCode(100)
                        .setBundle(bundle)
                        .putInt(UserElderConst.USERELDER_ID, elderId)
                        .putInt(Consts.FORMPURPOSE, Consts.PURPOSE_MODIFY)
                        .open(ElderFamilyFragment.this);
            }
        });

        /**
         * 长按删除
         */
        myAdapter.setOnItemLongClickListener(new SmartViewHolder.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int position) {

                deleteInfo(position);
            }

            private void deleteInfo(int position) {


                new AlertDialog.Builder(getContext())
                        .setMessage("是否要删除该项家庭成员信息")
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

                                                MemberFamily memberFamily = new MemberFamily();
                                                memberFamily.setId(mList.get(position).getId());
                                                memberFamily.setUserElderId(mList.get(position).getUserElderId());
                                                RequestObject<MemberFamily> requestObject = new RequestObject<>();
                                                requestObject.setData(memberFamily);

                                                String dataStr = new Gson().toJson(requestObject);
                                                System.out.println(dataStr);
                                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                                                String url = ServiceCall.getServerURL() + "/action/memberFamily/deleted";

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
    }


    /**
     * 显示家庭信息
     */
    private void getFamilyData() {
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

        String url = ServiceCall.getServerURL() + "/action/family/getByUserElderId";

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
                    msg.what = 4;
                    handler.sendMessage(msg);
                } else {
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }

        });


    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        mRefreshLayout.autoRefresh();
    }
}
