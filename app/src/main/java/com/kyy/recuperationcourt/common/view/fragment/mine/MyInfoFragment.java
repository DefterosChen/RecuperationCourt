package com.kyy.recuperationcourt.common.view.fragment.mine;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.MyApplication;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.SexType;
import com.kyy.recuperationcourt.common.model.dto.SysUserTokenDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.ResponseObject;
import com.kyy.recuperationcourt.common.model.entity.city.ProvinceInfo;
import com.kyy.recuperationcourt.common.model.entity.message.inter.MessageData;
import com.kyy.recuperationcourt.common.model.entity.message.inter.UserInfoData;
import com.kyy.recuperationcourt.common.model.entity.user.SysUser;
import com.kyy.recuperationcourt.common.other.widget.dialog.BaseDialog;
import com.kyy.recuperationcourt.common.other.widget.dialog.InputDialog;
import com.kyy.recuperationcourt.common.other.xui.common.utils.Utils;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.kyy.recuperationcourt.common.util.BaseUtils;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.xuexiang.xaop.annotation.IOThread;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.dialog.DialogLoader;
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
import com.xuexiang.xutil.net.JsonUtil;
import com.xuexiang.xutil.resource.ResourceUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * CXA
 * <p>
 * 2019/12/03
 */
@Page(name = "个人信息", anim = CoreAnim.none)
public class MyInfoFragment extends MyBaseFragment {


    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_save)
    TextView tv_save;

    @BindView(R.id.menu_headicon)
    SuperTextView stv_icon;
    @BindView(R.id.menu_username)
    SuperTextView stv_name;
    @BindView(R.id.menu_sex)
    SuperTextView stv_sex;
    @BindView(R.id.menu_phone)
    SuperTextView stv_phone;
    @BindView(R.id.menu_birth)
    SuperTextView stv_birth;
    @BindView(R.id.menu_address)
    SuperTextView stv_address;
    @BindView(R.id.menu_height)
    SuperTextView stv_height;
    @BindView(R.id.menu_weight)
    SuperTextView stv_weight;
    @BindView(R.id.menu_realname)
    SuperTextView stv_realname;
    @BindView(R.id.menu_residenceaddress)
    SuperTextView stv_residenceaddress;

    //头像选择
    private List<LocalMedia> mSelectList = new ArrayList<>();

    //日期选择
    private TimePickerView mDatePicker;

    //城市选择
    boolean isLoaded;
    private List<ProvinceInfo> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<List<List<String>>> options3Items = new ArrayList<>();

    //性别选择
    private String[] mSexOption;
    private int sexSelectOption = 0;


    UserInfoData userInfoData; //待删除
    String returnBody;
    String accessToken; //查询用户信息所需的TOKEN
    ResponseObject<SysUser> userInfo; //返回的个人信息

    Handler handler = null;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_info;
    }


    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    showData();
                } else if (msg.what == 2) {
                    System.out.println("接口返回用户数据ok为false");
                }
                return false;
            }


            /**
             * 显示用户信息数据
             */
            @SuppressLint("NewApi")
            private void showData() {

                SysUser sysUser = userInfo.getData();

                System.out.println("userinfo+sysUser:::" + sysUser.toString());

                stv_name.setRightString(sysUser.getUsername());

                stv_sex.setRightString(SexType.getName(sysUser.getSex()));


                stv_phone.setRightString(sysUser.getMobile());
                stv_birth.setRightString(sysUser.getCreateTime());

//                String str_address = userInfoData.getData().getRecords().get(0).getProvince() + "省"
//                        + userInfoData.getData().getRecords().get(0).getCity() + "市"
//                        + userInfoData.getData().getRecords().get(0).getArea() + "区";
//
//                stv_address.setRightString(str_address);
//                stv_weight.setRightString(String.valueOf(userInfoData.getData().getRecords().get(0).getWeight()) + "kg");
//                stv_height.setRightString(String.valueOf(userInfoData.getData().getRecords().get(0).getHeight()) + "cm");
//                stv_realname.setRightString(userInfoData.getData().getRecords().get(0).getRealName());
//                stv_residenceaddress.setRightString(userInfoData.getData().getRecords().get(0).getResidenceAddress());


            }
        });

    }

    @Override
    protected void initViews() {
        // 创建消息管理器
        createHandlerManage();

        mSexOption = ResUtils.getStringArray(R.array.sex_option);

        //加载城市数据
        loadData();

        initDatePicker();

        getInfo();

    }

    /**
     * 日期选择器
     */
    private void initDatePicker() {
        mDatePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                stv_birth.setRightString(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
//                userInfoData.getData().getRecords().get(0).setBirthday(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
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

    /**
     * 个人信息数据展示
     */
    private void getInfo() {
        accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }

        //获取用户信息显示
        String userString = BaseUtils.getLoginUser(MyApplication.getInstance());
        ResponseObject<SysUserTokenDto> user = new Gson().fromJson(userString, new TypeToken<ResponseObject<SysUserTokenDto>>() {
        }.getType());
        System.out.println("登录账号信息:" + user.toString());

        SysUser sysUser = new SysUser();
        sysUser.setId(user.getData().getSysUser().getId());
        RequestObject<SysUser> requestBodyDto = new RequestObject();
        requestBodyDto.setData(sysUser);

        String dataStr = new Gson().toJson(requestBodyDto);
        RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);

        String url = ServiceCall.getServerURL() + "/action/sysUser/findById";
        System.out.println(url);

        final Request request = new Request.Builder()
                .url(url)//请求的url
                .addHeader("Authorization", accessToken)
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
                System.out.println("获取失败");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                returnBody = response.body().string();
                System.out.println("获取成功！！！！ returnBody:" + returnBody);   //response.code = 401

//                //当使用过期token时 data字段返回值为字符串，需要进行忽略
//                Gson gson = new GsonBuilder()
//                        .setExclusionStrategies(new ExclusionStrategy() {
//                            @Override
//                            public boolean shouldSkipField(FieldAttributes f) {
//                                return f.getName().equals("data");
//                            }
//
//                            @Override
//                            public boolean shouldSkipClass(Class<?> clazz) {
//                                return false;
//                            }
//                        }).create();

                //转换返回数据格式
                Type type = new TypeToken<ResponseObject<SysUser>>() {
                }.getType();
                userInfo = new Gson().fromJson(returnBody, type);

                Message msg = new Message();
                if (userInfo.getCode().equals(Consts.REPONSE_200)) {
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else {
                    //暂时用不到
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }

        });


    }

    @Override
    protected void initListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });


        stv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getPictureSelector(MyInfoFragment.this)
                        .selectionMedia(mSelectList)
                        .maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });


        stv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_name_hint))
                        .setContent(stv_name.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!stv_name.getRightString().equals(content)) {
                                    stv_name.setRightString(content);
                                    userInfoData.getData().getRecords().get(0).setNickname(content);
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

        stv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_phone))
                        .setContent(stv_phone.getRightString())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (!stv_name.getRightString().equals(content)) {
                                    stv_name.setRightString(content);
                                    userInfoData.getData().getRecords().get(0).setPhone(content);

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


        stv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexPickerView();
            }

            /**
             * 性别选择
             */
            private void showSexPickerView() {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        stv_sex.setRightString(mSexOption[options1]);
                        sexSelectOption = options1;
                        userInfoData.getData().getRecords().get(0).setSex(sexSelectOption);
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


        stv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView(false);
            }

            private void showPickerView(boolean isDialog) {// 弹出选择器
                if (!isLoaded) {
                    XToastUtils.toast("请先加载数据！");
                    return;
                }

                int[] defaultSelectOptions = getDefaultCity();

                OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText() + "-" +
                                options2Items.get(options1).get(options2) + "-" +
                                options3Items.get(options1).get(options2).get(options3);

                        stv_address.setRightString(tx);
                    }
                })

                        .setTitleText("城市选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .isDialog(isDialog)
                        .setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1], defaultSelectOptions[2])
                        .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
                pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                pvOptions.show();
            }

            /**
             * @return 获取默认城市的索引
             */
            private int[] getDefaultCity() {
                int[] res = new int[3];
                ProvinceInfo provinceInfo;
                List<ProvinceInfo.City> cities;
                ProvinceInfo.City city;
                List<String> ares;
                for (int i = 0; i < options1Items.size(); i++) {
                    provinceInfo = options1Items.get(i);
                    if ("江苏省".equals(provinceInfo.getName())) {
                        res[0] = i;
                        cities = provinceInfo.getCityList();
                        for (int j = 0; j < cities.size(); j++) {
                            city = cities.get(j);
                            if ("南京市".equals(city.getName())) {
                                res[1] = j;
                                ares = city.getArea();
                                for (int k = 0; k < ares.size(); k++) {
                                    if ("雨花台区".equals(ares.get(k))) {
                                        res[2] = k;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                return res;
            }
        });


        stv_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.show();
            }
        });


        stv_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetListDialog(1);
            }
        });

        stv_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetListDialog(0);
            }
        });


        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoader.getInstance().showConfirmDialog(
                        getContext(),
                        getString(R.string.kky_savesure),
                        getString(R.string.lab_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                saveInfo();


                                dialog.dismiss();
                            }

                            /**
                             * 保存编辑后的用户数据到服务端
                             *
                             */
                            private void saveInfo() {

                                String accessToken = ServiceCall.getUserInfoToken();
                                String url = ServiceCall.getServerURL() + "/kang/tappuser";
                                System.out.println(url);

                                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                                String json = new Gson().toJson(userInfoData.getData().getRecords().get(0));
                                RequestBody body = RequestBody.create(JSON, json);

                                final Request request = new Request.Builder()
                                        .url(url)//请求的url
                                        .header("Content-Type", "application/json")
                                        .addHeader("Authorization", ServiceCall.AUTHORIZATION_HEADER_BEARER + accessToken)
                                        .put(body)
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

                                        System.out.println("修改个人信息后返回数据:" + returnBody);


                                        //转换返回数据格式
                                        Type type = new TypeToken<MessageData>() {
                                        }.getType();
                                        MessageData msg = new Gson().fromJson(returnBody, type);


                                        if (msg.isData()) {
                                            System.out.println("修改成功");
                                            popToBack();
                                        } else {
                                            DialogLoader.getInstance().showTipDialog(
                                                    getContext(),
                                                    getString(R.string.tip_infos),
                                                    getString(R.string.kky_savesure_fail),
                                                    getString(R.string.lab_submit));
                                        }

                                    }

                                });


                            }
                        },
                        getString(R.string.lab_no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }
                );
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:

                    System.out.println("头像有返回值了");
                    showIcon(data);

                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 显示头像
     *
     * @param data
     */
    private void showIcon(Intent data) {
        // 图片选择
        mSelectList = PictureSelector.obtainMultipleResult(data);


        LocalMedia media = mSelectList.get(0);

        System.out.println("获取的新头像：" + media.toString());
        int mimeType = media.getMimeType();
        String path;
        if (media.isCut() && !media.isCompressed()) {
            // 裁剪过
            path = media.getCutPath();
        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
            path = media.getCompressPath();
        } else {
            // 原图
            path = media.getPath();
        }

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.color.color_f4)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(stv_icon.getContext())
                .load(path)
                .apply(options)
                .into(stv_icon.getRightIconIV());

        userInfoData.getData().getRecords().get(0).setHeadImg(path);

    }


    @IOThread
    private void loadData() {//加载数据
        if (isLoaded) {
            XToastUtils.toast("已加载！");
            return;
        }

        String JsonData = ResourceUtils.readStringFromAssert("province.json");
        List<ProvinceInfo> provinceInfos = JsonUtil.fromJson(JsonData, new TypeToken<List<ProvinceInfo>>() {
        }.getType());
        if (provinceInfos == null) {
            isLoaded = false;
            XToastUtils.toast("加载失败！");
            return;
        }
        /**
         * 添加省份数据
         */
        options1Items = provinceInfos;

        for (ProvinceInfo provinceInfo : provinceInfos) { //遍历省份
            List<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            List<List<String>> areaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (ProvinceInfo.City city : provinceInfo.getCityList()) {
                String CityName = city.getName();
                cityList.add(CityName);//添加城市

                List<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (city.getArea() == null || city.getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(city.getArea());
                }
                areaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(areaList);
        }
        isLoaded = true;
        XToastUtils.toast("加载成功！");
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

                        stv_weight.setRightString(rulerView_w.getCurrentValue() + unit);
                        userInfoData.getData().getRecords().get(0).setWeight(rulerView_w.getCurrentValue());

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

                        stv_height.setRightString(rulerView_h.getCurrentValue() + unit);
                        userInfoData.getData().getRecords().get(0).setHeight(rulerView_h.getCurrentValue());

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
    public void onDestroyView() {
        options1Items.clear();
        options2Items.clear();
        options3Items.clear();
        super.onDestroyView();
    }

}
