package com.kyy.recuperationcourt.common.view.fragment.social.elder;

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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.elder.ElderHealthConsts;
import com.kyy.recuperationcourt.common.model.constant.elder.UserElderConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.elder.HealthManagement;
import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;
import com.kyy.recuperationcourt.common.other.widget.dialog.BaseDialog;
import com.kyy.recuperationcourt.common.other.widget.dialog.InputDialog;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.SocialFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.workorder.AddWorkOrderFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.core.PageOption;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.RulerView;
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
 * 新增长者健康
 * 2020/07/29
 * DefterosChen
 */
@Page(name = "新增长者健康", anim = CoreAnim.none)
public class AddElderHealthFragment extends MyBaseFragment {
    public final static String FRAGMENT_NAME = "AddElderHealthFragment";

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.menu_eldername)
    SuperTextView sTvElderName;
    @BindView(R.id.menu_height)
    SuperTextView sTvHeight;
    @BindView(R.id.menu_weight)
    SuperTextView sTvWeight;
    @BindView(R.id.et_item_des)
    MultiLineEditText etDes;

    public AddElderHealthFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_elder_health;
    }


    Handler handler = null;
    String returnBody;


    HealthManagement healthManagement = new HealthManagement();

    int elderId = 0;
    String elderName;

    int strPurpose;


    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    System.out.println("新增长者健康信息成功，返回列表界面");
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


        switch (strPurpose) {
            case Consts
                    .PURPOSE_ADD:

                break;
            case Consts
                    .PURPOSE_MODIFY:
                Bundle bundle = getArguments();
                healthManagement = bundle.getParcelable(ElderHealthConsts.PASSOBJECT);
                showDataModify();
                break;
        }
    }

    private void showDataModify() {

        sTvElderName.setFocusable(false);
        sTvElderName.setEnabled(false);
        sTvElderName.setRightString(healthManagement.getElderName());
        sTvHeight.setRightString(healthManagement.getHeight());
        sTvWeight.setRightString(healthManagement.getWeight());
        etDes.setContentText(healthManagement.getDescription());

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
                        sentData(ServiceCall.getServerURL() + "/action/healthManagement/add");
                        break;
                    case Consts
                            .PURPOSE_MODIFY:
                        sentData(ServiceCall.getServerURL() + "/action/healthManagement/modify");
                        break;
                }

            }


            /**
             * 发送请求 操作工单数据
             */
            private void sentData(String url) {


                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }

                RequestObject<HealthManagement> requestObject = new RequestObject<>();
                requestObject.setData(healthManagement);

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

                System.out.println("长者健康信息:" + healthManagement.toString());

                if (sTvElderName.getRightString() == null
                        || sTvHeight.getRightString() == null
                        || sTvWeight.getRightString() == null) {
                    return false;
                }


                if (etDes.getContentText().trim().isEmpty()) {
                    return false;
                }

                healthManagement.setDescription(etDes.getContentText());


                return true;
            }
        });


        sTvElderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageOption.to(ElderListFragment.class)
                        .setRequestCode(100)
                        .putString(Messages.FRAGMENTFROMWHERE, FRAGMENT_NAME)
                        .open(AddElderHealthFragment.this);
            }
        });

        sTvHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetListDialog(1);
            }
        });

        sTvWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetListDialog(0);
            }
        });


    }


    /**
     * 获取体重身高
     *
     * @param type 0-体重 1-身高
     */
    private void showBottomSheetListDialog(int type) {
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bottom_ruleview, null);

        TextView tv = view.findViewById(R.id.textView_title);
        RulerView rulerView_w = view.findViewById(R.id.rulerView_weight);
        RulerView rulerView_h = view.findViewById(R.id.rulerView_height);
        XUIAlphaButton bt = view.findViewById(R.id.btn_get);

        String unit; //尺度单位


        switch (type) {
            case 0:
                tv.setText(R.string.kky_user_weight);
                unit = "kg";
                rulerView_w.setVisibility(View.VISIBLE);
                rulerView_h.setVisibility(View.GONE);

                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sTvWeight.setRightString(rulerView_w.getCurrentValue() + unit);
                        healthManagement.setWeight(String.valueOf(rulerView_w.getCurrentValue()));
                        dialog.dismiss();

                    }
                });

                break;
            case 1:
                tv.setText(R.string.kky_user_height);
                unit = "cm";
                rulerView_w.setVisibility(View.GONE);
                rulerView_h.setVisibility(View.VISIBLE);

                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sTvHeight.setRightString(rulerView_h.getCurrentValue() + unit);
                        healthManagement.setHeight(String.valueOf(rulerView_h.getCurrentValue()));
                        dialog.dismiss();

                    }
                });

                break;
            default:
                break;
        }


        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();


    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        System.out.println("返回到添加界面" + resultCode);
        if (data != null) {
            Bundle extras = data.getExtras();

            elderId = extras.getInt(UserElderConst.USERELDER_ID);
            elderName = extras.getString(UserElderConst.USERELDER_NAME);
            sTvElderName.setRightString(elderName);
            healthManagement.setElderId(elderId);
        }
    }
}
