package com.kyy.recuperationcourt.common.view.fragment.social.elder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.SexType;
import com.kyy.recuperationcourt.common.model.constant.elder.ElderHealthConsts;
import com.kyy.recuperationcourt.common.model.constant.elder.UserElderConst;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.elder.HealthManagement;
import com.kyy.recuperationcourt.common.model.entity.elder.MemberFamily;
import com.kyy.recuperationcourt.common.other.widget.dialog.BaseDialog;
import com.kyy.recuperationcourt.common.other.widget.dialog.InputDialog;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.core.PageOption;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.RulerView;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 新增长者家庭成员
 * 2020/07/31
 * DefterosChen
 */
@Page(name = "新增长者家庭成员", anim = CoreAnim.none)
public class AddElderMemberFragment extends MyBaseFragment {

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.menu_member_name)
    SuperTextView stvName;
    @BindView(R.id.menu_member_sex)
    SuperTextView stvSex;
    @BindView(R.id.menu_member_relationship)
    SuperTextView stvRelation;
    @BindView(R.id.menu_member_mobile)
    SuperTextView stvMobile;
    @BindView(R.id.et_item_address)
    EditText etAddress;


    public AddElderMemberFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_elder_member;
    }


    Handler handler = null;
    String returnBody;

    MemberFamily memberFamily = new MemberFamily();

    int elderId ;
    int strPurpose;

    //性别选择
    String[] mSexOption = ResUtils.getStringArray(R.array.sex_option);
    int sexSelectOption = 0;


    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    System.out.println("编辑长者家庭成员信息成功，返回列表界面");
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
        elderId = getArguments().getInt(UserElderConst.USERELDER_ID);

        switch (strPurpose) {
            case Consts
                    .PURPOSE_ADD:
                break;
            case Consts
                    .PURPOSE_MODIFY:
                Bundle bundle = getArguments();
                memberFamily = bundle.getParcelable(UserElderConst.ELDERFAMILY_PASSOBJECT);
                showDataModify();
                break;
        }
    }

    @SuppressLint("NewApi")
    private void showDataModify() {

        stvName.setRightString(memberFamily.getName());
        stvSex.setRightString(SexType.getName(memberFamily.getSex()));
        stvRelation.setRightString(memberFamily.getRelationship());
        stvMobile.setRightString(memberFamily.getMobile());
        etAddress.setText(memberFamily.getAddress());

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
                        sentData(ServiceCall.getServerURL() + "/action/memberFamily/insert");
                        break;
                    case Consts
                            .PURPOSE_MODIFY:
                        sentData(ServiceCall.getServerURL() + "/action/memberFamily/updateById");
                        break;
                }

            }


            /**
             * 发送请求
             */
            private void sentData(String url) {


                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }

                RequestObject<MemberFamily> requestObject = new RequestObject<>();
                requestObject.setData(memberFamily);

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


                if (stvName.getRightString() == null
                        || stvSex.getRightString() == null
                        || stvRelation.getRightString() == null
                        || stvMobile.getRightString() == null) {
                    return false;
                }


                if (etAddress.getText().toString().trim().isEmpty()) {
                    return false;
                }

                memberFamily.setName(stvName.getRightString());
                memberFamily.setSex(sexSelectOption);
                memberFamily.setRelationship(stvRelation.getRightString());
                memberFamily.setMobile(stvMobile.getRightString());
                memberFamily.setAddress(etAddress.getText().toString());
                memberFamily.setUserElderId(elderId);


                return true;
            }
        });

        stvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        stvSex.setRightString(mSexOption[options1]);
                        sexSelectOption = options1;
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

        stvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_realname_hint))
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

        stvRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.kyy_elder_family_fill_relationship))
                        .setContent(stvRelation.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!stvRelation.getRightString().equals(content)) {
                                    stvRelation.setRightString(content);
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

        stvMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_phone))
                        .setContent(stvMobile.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!stvMobile.getRightString().equals(content)) {
                                    stvMobile.setRightString(content);
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
