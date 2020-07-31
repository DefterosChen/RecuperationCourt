package com.kyy.recuperationcourt.common.view.fragment.social.elder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.SexType;
import com.kyy.recuperationcourt.common.model.constant.elder.ElderHealthConsts;
import com.kyy.recuperationcourt.common.model.constant.elder.UserElderConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;
import com.kyy.recuperationcourt.common.other.widget.dialog.BaseDialog;
import com.kyy.recuperationcourt.common.other.widget.dialog.InputDialog;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.serviceitem.AddServiceItemFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.serviceitem.ServiceItemListFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.core.PageOption;
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
 * 长者信息
 * 2020/07/22
 * DefterosChen
 */
@Page(name = "长者信息", anim = CoreAnim.none)
public class AddElderFragment extends MyBaseFragment {

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_family)
    TextView mTvFamily;
    @BindView(R.id.tv_elderservice)
    TextView mTvElderService;

    @BindView(R.id.menu_eldername)
    SuperTextView sTvElderName;
    @BindView(R.id.menu_sex)
    SuperTextView sTvSex;
    @BindView(R.id.menu_mobile)
    SuperTextView sTvMobile;
    @BindView(R.id.menu_birth)
    SuperTextView sTvBirth;
    @BindView(R.id.menu_idcard)
    SuperTextView sTvIdCard;

    @BindView(R.id.et_item_address)
    EditText etAddress;
    @BindView(R.id.et_item_resaddress)
    EditText etAddressres;
    @BindView(R.id.et_item_remarks)
    MultiLineEditText etRemark;

    public AddElderFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_elder;
    }


    Handler handler = null;
    String returnBody;


    UserElder userElder = new UserElder();

    //性别选择
    private String[] mSexOption;
    private int sexSelectOption = 0;

    //日期选择
    private TimePickerView mDatePicker;

    int strPurpose;

    String sIdentityCard;


    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    System.out.println("新的长者信息编辑成功，返回列表界面");
                    setFragmentResult(100, null);
                    popToBack();

                } else if (msg.what == 2) {
                    System.out.println("接口返回用户数据ok为false");
                } else if (msg.what == 4) {
                    sTvIdCard.setRightString(sIdentityCard);
                    userElder.setIdentityCard(sIdentityCard);
                    System.out.println("身份证验证通过！");
                } else if (msg.what == 5) {
                    System.out.println("该身份证已注册！");
                    ToastUtil.showTips("该身份证已注册!!! 请重新输入");
                }
                return false;
            }

        });

    }

    @Override
    protected void initViews() {
        // 创建消息管理器
        createHandlerManage();
        mSexOption = ResUtils.getStringArray(R.array.sex_option);
        initDatePicker();

        strPurpose = getArguments().getInt(Consts.FORMPURPOSE);


        switch (strPurpose) {
            case Consts
                    .PURPOSE_ADD:

                break;
            case Consts
                    .PURPOSE_MODIFY:
                Bundle bundle = getArguments();
                userElder = bundle.getParcelable(UserElderConst.PASSOBJECT);
                showDataModify();
                break;
        }
    }

    @SuppressLint("NewApi")
    private void showDataModify() {

        sTvElderName.setRightString(userElder.getName());
        sTvSex.setRightString(SexType.getName(userElder.getSex()));
        sTvMobile.setRightString(userElder.getMobile());
        sTvBirth.setRightString(userElder.getBirth());
        sTvIdCard.setRightString(userElder.getIdentityCard());

        etAddress.setText(userElder.getAddress());
        etAddressres.setText(userElder.getResidenceAddress());
        etRemark.setContentText(userElder.getRemarks());

        mTvFamily.setVisibility(View.VISIBLE);
        mTvElderService.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        //提交新增工单数据
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
                        sentData(ServiceCall.getServerURL() + "/action/userElder/add");
                        break;
                    case Consts
                            .PURPOSE_MODIFY:
                        sentData(ServiceCall.getServerURL() + "/action/userElder/modify");
                        break;
                }

            }

            /**
             * 发送请求新增工单数据
             */
            private void sentData(String url) {

                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }

                RequestObject<UserElder> requestObject = new RequestObject<>();
                requestObject.setData(userElder);

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
            private boolean isEmptyInfo() {

                System.out.println("长者信息:" + userElder.toString());

                if (sTvElderName.getRightString() == null
                        || sTvSex.getRightString() == null
                        || sTvBirth.getRightString() == null
                        || sTvIdCard.getRightString() == null
                        || sTvMobile.getRightString() == null) {
                    return false;
                }


                if (etAddress.getText().toString().trim().isEmpty() || etAddressres.getText().toString().trim().isEmpty() || etRemark.getContentText().trim().isEmpty()) {
                    return false;
                }

                userElder.setAddress(etAddress.getText().toString());
                userElder.setResidenceAddress(etAddressres.getText().toString());
                userElder.setRemarks(etRemark.getContentText());
                userElder.setCompanyId(ServiceCall.getUserInfo().getCompanyId());

                return true;
            }
        });


        sTvElderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_realname_hint))
                        .setContent(sTvElderName.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!sTvElderName.getRightString().equals(content)) {
                                    sTvElderName.setRightString(content);
                                    userElder.setName(content);
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

        sTvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        sTvSex.setRightString(mSexOption[options1]);
                        sexSelectOption = options1;
                        userElder.setSex(sexSelectOption);
                        System.out.println("性别选择了：" + sexSelectOption);
                    }
                })
                        .setTitleText(getString(R.string.title_sex_select))
                        .setSelectOptions(sexSelectOption)
                        .build();
                pvOptions.setPicker(mSexOption);
                pvOptions.show();
            }
        });

        sTvMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_phone))
                        .setContent(sTvMobile.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!sTvMobile.getRightString().equals(content)) {
                                    sTvMobile.setRightString(content);
                                    userElder.setMobile(content);
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

        sTvBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.show();
            }
        });

        sTvIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_idcard))
                        .setContent(sTvIdCard.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                BaseUtils.hideKeyboard(getActivity());

                                if (!sTvIdCard.getRightString().equals(content)) {
                                    if (content.length() != 18) {
                                        ToastUtil.showTips("请输入完整的身份证号");
                                        return;
                                    }
                                    sIdentityCard = content;
                                    isCheckedExistId(content);
                                }

                            }

                            /**
                             * 校验是否是已存在的身份证id信息
                             * @param content
                             * @return
                             */
                            private void isCheckedExistId(String content) {

                                String accessToken = ServiceCall.getUserInfoToken();

                                RequestObject<String> requestObject = new RequestObject<>();
                                requestObject.setData(content);

                                String dataStr = new Gson().toJson(requestObject);
                                System.out.println(dataStr);
                                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

                                String url = ServiceCall.getServerURL() + "/action/userElder/checkElderId";

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
                                            msg.what = 5;
                                            handler.sendMessage(msg);
                                        }
                                    }

                                });
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                BaseUtils.hideKeyboard(getActivity());

                            }
                        })
                        .show();
            }
        });


        /**
         * 查看编辑长者的家庭信息
         */
        mTvFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageOption.to(ElderFamilyFragment.class)
                        .setRequestCode(100)
                        .putInt(ElderHealthConsts.PASS_ID, userElder.getId())
                        .open(AddElderFragment.this);
            }
        });

        /**
         * 查看长者的过往服务项信息
         */
        mTvElderService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageOption.to(ElderServiceItemsFragment.class)
                        .setRequestCode(100)
                        .putInt(ElderHealthConsts.PASS_ID, userElder.getId())
                        .open(AddElderFragment.this);
            }
        });

    }


    /**
     * 日期选择器
     */
    private void initDatePicker() {
        mDatePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                sTvBirth.setRightString(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
                userElder.setBirth(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setTitleText("日期选择")
                .build();
    }

}