package com.kyy.recuperationcourt.common.view.fragment.social.serviceitem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.elder.ElderHealthConsts;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemCategory;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemType;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;
import com.kyy.recuperationcourt.common.model.entity.order.ServiceItem;
import com.kyy.recuperationcourt.common.other.widget.dialog.BaseDialog;
import com.kyy.recuperationcourt.common.other.widget.dialog.InputDialog;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
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
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 新增服务项
 * 2020/07/28
 * DefterosChen
 */
@Page(name = "新增服务项", anim = CoreAnim.none)
public class AddServiceItemFragment extends MyBaseFragment {

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.menu_name)
    SuperTextView sTvName;
    @BindView(R.id.menu_type)
    SuperTextView sTvType;
    @BindView(R.id.menu_cycle)
    SuperTextView sTvCycle;
    @BindView(R.id.menu_price)
    SuperTextView sTvPrice;

    @BindView(R.id.et_item_content)
    MultiLineEditText etContent;

    public AddServiceItemFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_service_item;
    }


    Handler handler = null;
    String returnBody;


    ServiceItem serviceItem = new ServiceItem();

    int strPurpose;

    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    System.out.println("新增服务项成功，返回列表界面");
                    setFragmentResult(100, null);
                    popToBack();

                } else if (msg.what == 2) {
                    System.out.println("接口返回用户数据ok为false");
                }
                return false;
            }

        });

    }

    @Override
    protected void initViews() {
        // 创建消息管理器
        createHandlerManage();

        strPurpose = getArguments().getInt(Consts.FORMPURPOSE);
        System.out.println("str目的是"+ strPurpose);


        switch (strPurpose) {
            case Consts
                    .PURPOSE_ADD:
                break;
            case Consts
                    .PURPOSE_MODIFY:
                Bundle bundle = getArguments();
                serviceItem = bundle.getParcelable(ServiceItemConst.PASSOBJECT);
                showDataModify();
                break;
        }
    }

    @SuppressLint("NewApi")
    private void showDataModify() {

        sTvName.setRightString(serviceItem.getServiceName());
        sTvPrice.setRightString(serviceItem.getPrice().toString());
        etContent.setContentText(serviceItem.getServiceDes());
        sTvType.setVisibility(View.GONE);

        if (serviceItem.getType() == ServiceItemType.ITEM_NUM.getCode()) {
            sTvCycle.setVisibility(View.VISIBLE);
            sTvCycle.setRightString(serviceItem.getCycle().toString());
        }
    }


    @Override
    protected void initListeners() {
        super.initListeners();


        //提交服务项数据
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isEmptyInfo()) {
                    ToastUtil.showTips(Messages.ISFILLCOMPLETED);
                    return;
                }

                switch (strPurpose) {
                    case Consts
                            .PURPOSE_ADD:
                        sentData(ServiceCall.getServerURL() + "/action/serviceItem/add");
                        break;
                    case Consts
                            .PURPOSE_MODIFY:
                        sentData(ServiceCall.getServerURL() + "/action/serviceItem/modify");
                        break;
                }

            }

            /**获取到的数据
             * 发送请求新增工单数据
             */
            private void sentData(String url) {

                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }

                RequestObject<ServiceItem> requestObject = new RequestObject<>();
                requestObject.setData(serviceItem);

                String dataStr = new Gson().toJson(requestObject);
                System.out.println(dataStr);
                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

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
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } else {
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                    }

                });

            }

            /**
             * 判断数据是否填写完整
             * @return
             */
            @SuppressLint("NewApi")
            private boolean isEmptyInfo() {

                System.out.println("长者信息:" + serviceItem.toString());

                if (sTvName.getRightString() == null
                        || sTvPrice.getRightString() == null
                        || sTvType.getRightString() == null) {
                    return false;
                }

                if (serviceItem.getType().equals(ServiceItemType.ITEM_NUM) && sTvCycle.getRightString() == null) {
                    return false;
                }


                if (etContent.getContentText().trim().isEmpty()) {
                    return false;
                }

                serviceItem.setCompanyId(ServiceCall.getUserInfo().getCompanyId());
                serviceItem.setServiceCategory(getArguments().getInt(ServiceItemConst.SERVICEITEM_CATEGORY));
                serviceItem.setServiceDes(etContent.getContentText().toString());

                return true;
            }
        });

        sTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.kyy_serviceitem_name))
                        .setContent(sTvName.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!sTvName.getRightString().equals(content)) {
                                    sTvName.setRightString(content);
                                    serviceItem.setServiceName(content);
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

        sTvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexPickerView();
            }

            @SuppressLint("NewApi")
            private void showSexPickerView() {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {

                        sTvType.setRightString(ServiceItemType.enumToList().get(options1));
                        serviceItem.setType(ServiceItemType.enumToListCode().get(options1));
                        if (serviceItem.getType().equals(ServiceItemType.ITEM_NUM.getCode())) {
                            sTvCycle.setVisibility(View.VISIBLE);
                        }

                    }
                })
                        .setTitleText(getString(R.string.kyy_workorder_servicepricetype))
                        .build();
                pvOptions.setPicker(ServiceItemType.enumToList());
                pvOptions.show();
            }
        });

        sTvCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.kyy_serviceitem_cycle))
                        .setContent(sTvCycle.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!sTvCycle.getRightString().equals(content)) {
                                    sTvCycle.setRightString(content);
                                    serviceItem.setCycle(Integer.valueOf(content));
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

        sTvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.kyy_serviceitem_price))
                        .setContent(sTvPrice.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!sTvPrice.getRightString().equals(content)) {
                                    sTvPrice.setRightString(content);
                                    serviceItem.setPrice(Integer.valueOf(content));
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


}