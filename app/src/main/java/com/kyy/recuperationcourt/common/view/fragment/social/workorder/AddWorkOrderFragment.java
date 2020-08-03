package com.kyy.recuperationcourt.common.view.fragment.social.workorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.Messages;
import com.kyy.recuperationcourt.common.model.constant.elder.UserElderConst;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemConst;
import com.kyy.recuperationcourt.common.model.constant.workorder.OrderType;
import com.kyy.recuperationcourt.common.model.constant.serviceitem.ServiceItemCategory;
import com.kyy.recuperationcourt.common.model.dto.QueryUserElderDto;
import com.kyy.recuperationcourt.common.model.entity.RequestObject;
import com.kyy.recuperationcourt.common.model.entity.city.ProvinceInfo;
import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;
import com.kyy.recuperationcourt.common.model.entity.order.WorkOrder;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.elder.ElderListFragment;
import com.kyy.recuperationcourt.common.view.fragment.social.serviceitem.ServiceItemListFragment;
import com.xuexiang.xaop.annotation.IOThread;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.core.PageOption;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xutil.net.JsonUtil;
import com.xuexiang.xutil.resource.ResourceUtils;

import java.io.IOException;
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
 * 新增工单
 * 2020/07/22
 * DefterosChen
 */
@Page(name = "新增工单", anim = CoreAnim.none)
public class AddWorkOrderFragment extends MyBaseFragment {
    public final static String FRAGMENT_NAME = "AddWorkOrderFragment";



    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.menu_ordertype)
    SuperTextView sTvOrdertype;
    @BindView(R.id.menu_elderman)
    SuperTextView sTvElderman;
    @BindView(R.id.menu_servicetype)
    SuperTextView sTvServictype;
    @BindView(R.id.menu_servicename)
    SuperTextView sTvServicname;
    @BindView(R.id.menu_city)
    SuperTextView sTvCity;

    @BindView(R.id.et_item_address)
    EditText etAddress;
    @BindView(R.id.et_item_des)
    MultiLineEditText etDes;

    public AddWorkOrderFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_work_order;
    }

    //城市选择
    boolean isLoaded;
    private List<ProvinceInfo> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<List<List<String>>> options3Items = new ArrayList<>();

    Handler handler = null;
    String returnBody;

    WorkOrder workOrder = new WorkOrder();

    int elderId = 0;
    String elderName;

    int serviceItemId = 0;
    String serviceItemName;

    /**
     * 创建消息管理器
     */
    private void createHandlerManage() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    ToastUtil.showTips("新增工单成功，返回列表界面");
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

//        getElderData();

        //加载城市数据
        loadData();

    }

    /**
     * 获取长者信息数据
     */
    private void getElderData() {

        String accessToken = ServiceCall.getUserInfoToken();

        if (accessToken == null || accessToken.equals("")) {
            System.out.println("暂未登录,没有获取到显示用户信息所需的token");
            return;
        }

        System.out.println("token::::" + accessToken);

        int userId = ServiceCall.getUserInfo().getId();

        UserElder userElder = new UserElder();
        userElder.setIsDelete(Consts.DELETED_NO);

        QueryUserElderDto queryUserElderDto = new QueryUserElderDto();
        queryUserElderDto.setSize(10);
        queryUserElderDto.setCurrent(1);
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

                System.out.println("获取到的工单数据：" + returnBody);

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

        //提交新增工单数据
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isEmptyInfo()) {
                    ToastUtil.showTips(Messages.ISFILLCOMPLETED);
                    return;
                }

                addData();

            }

            /**
             * 发送请求新增工单数据
             */
            private void addData() {


                String accessToken = ServiceCall.getUserInfoToken();

                if (accessToken == null || accessToken.equals("")) {
                    System.out.println("暂未登录,没有获取到显示用户信息所需的token");
                    return;
                }


                RequestObject<WorkOrder> requestObject = new RequestObject<>();
                requestObject.setData(workOrder);

                String dataStr = new Gson().toJson(requestObject);
                System.out.println(dataStr);
                RequestBody requestBody = RequestBody.create(ServiceCall.JSON, dataStr);


                String url = ServiceCall.getServerURL() + "/action/workOrder/add"; // current :页数   size :单次刷新条数 固定100条

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

            /**
             * 判断数据是否填写完整
             * @return
             */
            private boolean isEmptyInfo() {

                System.out.println("workorderinfo:" + workOrder.toString());

                if (workOrder.getServiceItemCategory() == null
                        || workOrder.getServiceItemId() == null
                        || workOrder.getType() == null
                        || workOrder.getElderUserId() == null
                        || workOrder.getProvince() == null) {
                    return false;
                }


                if (etAddress.getText().toString().trim().isEmpty() || etDes.getContentText().trim().isEmpty()) {
                    return false;
                }

                workOrder.setAddress(etAddress.getText().toString());
                workOrder.setOrderDes(etDes.getContentText());

                return true;
            }
        });


        sTvOrdertype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexPickerView();
            }

            @SuppressLint("NewApi")
            private void showSexPickerView() {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        sTvOrdertype.setRightString(OrderType.enumToList().get(options1));
                        workOrder.setType(OrderType.enumToListCode().get(options1));

                    }
                })
                        .setTitleText(getString(R.string.kyy_workorder_type))
                        .build();
                pvOptions.setPicker(OrderType.enumToList());
                pvOptions.show();
            }
        });


        sTvElderman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PageOption.to(ElderListFragment.class)
                        .setRequestCode(100)
                        .putString(Messages.FRAGMENTFROMWHERE,FRAGMENT_NAME)
                        .open(AddWorkOrderFragment.this);

            }
        });


        sTvServictype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexPickerView();
            }

            private void showSexPickerView() {
                OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {

                        sTvServictype.setRightString(ServiceItemCategory.enumToList().get(options1));
                        workOrder.setServiceItemCategory(ServiceItemCategory.enumToListCode().get(options1));
                        sTvServicname.setVisibility(View.VISIBLE);
                    }
                })
                        .setTitleText(getString(R.string.kyy_workorder_servicetype))
                        .build();
                pvOptions.setPicker(ServiceItemCategory.enumToList());
                pvOptions.show();
            }
        });


        sTvServicname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                PageOption.to(ServiceItemListFragment.class)
//                        .putInt(ServiceItemListFragment.SERVICEITEM_TYPE, workOrder.getServiceItemCategory())
//                        .setRequestCode(100)
//                        .open(AddWorkOrderFragment.this);

                Bundle params = new Bundle();
                params.putInt(ServiceItemConst.SERVICEITEM_CATEGORY, workOrder.getServiceItemCategory());
                params.putString(Messages.FRAGMENTFROMWHERE, FRAGMENT_NAME);
                openPageForResult(ServiceItemListFragment.class, params, 100);

            }
        });


        sTvCity.setOnClickListener(new View.OnClickListener() {
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

                        workOrder.setProvince(options1Items.get(options1).getPickerViewText());
                        workOrder.setCity(options2Items.get(options1).get(options2));
                        workOrder.setArea(options3Items.get(options1).get(options2).get(options3));

                        sTvCity.setRightString(tx);
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
                    if ("广东省".equals(provinceInfo.getName())) {
                        res[0] = i;
                        cities = provinceInfo.getCityList();
                        for (int j = 0; j < cities.size(); j++) {
                            city = cities.get(j);
                            if ("深圳市".equals(city.getName())) {
                                res[1] = j;
                                ares = city.getArea();
                                for (int k = 0; k < ares.size(); k++) {
                                    if ("福田区".equals(ares.get(k))) {
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
        ToastUtil.showTips("加载成功！");
    }

    @Override
    public void onDestroyView() {
        options1Items.clear();
        options2Items.clear();
        options3Items.clear();
        super.onDestroyView();
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        System.out.println("返回到添加界面" + resultCode);
        if (data != null) {
            Bundle extras = data.getExtras();

            switch (resultCode) {
                case ServiceItemConst.SERVICEITEM_RESPONSE_CODE:
                    getServiceChoiced(extras);
                    break;
                case UserElderConst.USERELDER_RESPONSE_CODE:
                    getElderChoiced(extras);
                    break;
                default:
                    break;
            }
        }
    }

    private void getServiceChoiced(Bundle extras) {

        serviceItemId = extras.getInt(ServiceItemConst.SERVICEITEM_ID);
        serviceItemName = extras.getString(ServiceItemConst.SERVICEITEM_NAME);
        sTvServicname.setRightString(serviceItemName);
        workOrder.setServiceItemId(serviceItemId);

    }

    private void getElderChoiced(Bundle extras) {
        elderId = extras.getInt(UserElderConst.USERELDER_ID);
        elderName = extras.getString(UserElderConst.USERELDER_NAME);
        sTvElderman.setRightString(elderName);
        workOrder.setElderUserId(elderId);
    }


}