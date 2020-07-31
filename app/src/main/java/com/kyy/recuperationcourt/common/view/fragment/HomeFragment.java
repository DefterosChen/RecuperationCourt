package com.kyy.recuperationcourt.common.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.android.material.tabs.TabLayout;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.other.wheel.adapter.MyRecyclerViewAdapter;
import com.kyy.recuperationcourt.common.other.widget.banner.RadiusImageBanner;
import com.kyy.recuperationcourt.common.other.widget.click.NoMoreClickListener;
import com.kyy.recuperationcourt.common.other.widget.layout.ItemInfoLinearLayout;
import com.kyy.recuperationcourt.common.other.widget.searchview.KylinSearchView;
import com.kyy.recuperationcourt.common.other.widget.searchview.OnSearchListener;
import com.kyy.recuperationcourt.common.other.xui.DemoDataProvider;
import com.kyy.recuperationcourt.common.other.xui.common.utils.Utils;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.home.MessageFragment;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.xuexiang.citypicker.CityPicker;
import com.xuexiang.citypicker.adapter.OnLocationListener;
import com.xuexiang.citypicker.adapter.OnPickListener;
import com.xuexiang.citypicker.model.City;
import com.xuexiang.citypicker.model.HotCity;
import com.xuexiang.citypicker.model.LocateState;
import com.xuexiang.citypicker.model.LocatedCity;
import com.xuexiang.xaop.annotation.IOThread;
import com.xuexiang.xaop.annotation.Permission;
import com.xuexiang.xaop.enums.ThreadType;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xqrcode.XQRCode;
import com.xuexiang.xqrcode.util.QRCodeAnalyzeUtils;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.banner.widget.banner.base.BaseBanner;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xutil.app.PathUtils;

import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static com.shuyu.gsyvideoplayer.GSYVideoBaseManager.TAG;
import static com.xuexiang.xaop.consts.PermissionConsts.CAMERA;

/**
 * 首页
 * 2019/11/12
 * cxa
 */
@Page(name = "首页", anim = CoreAnim.none)
public final class HomeFragment extends MyBaseFragment
        implements OnSearchListener, TabLayout.OnTabSelectedListener, BannerLayout.OnBannerItemClickListener
        , ItemInfoLinearLayout.OnItemInfoLinearItemClickListener, SmartViewHolder.OnItemClickListener {

    private List<BannerItem> mData;

    @BindView(R.id.rib_simple_usage)
    RadiusImageBanner rib_simple_usage;


    @BindView(R.id.iill_servicecourse)
    ItemInfoLinearLayout iill_service_course;
    @BindView(R.id.iill_recommendtoday)
    ItemInfoLinearLayout iill_recommendtoday;

    private MyRecyclerViewAdapter myAdapterCourse;
    private MyRecyclerViewAdapter myAdapterRecommend;


    @BindView(R.id.tv_citychoice)
    TextView textView_city;
    @BindView(R.id.sv_default)
    KylinSearchView searchViewDemo;

    private LocatedCity mLocatedCity;
    private int locateState;
    private List<HotCity> mHotCities;
    private int mAnim;
    private int mTheme;
    private boolean mEnableAnimation = true;


    @BindView(R.id.iv_qrcode)
    ImageView iv_qrcode;
    @BindView(R.id.iv_system_messages)
    ImageView iv_message;


    //医疗服务
    @BindView(R.id.stv_medicalservice1)
    SuperTextView superTextView_m1;
    @BindView(R.id.stv_medicalservice2)
    SuperTextView superTextView_m2;
    @BindView(R.id.stv_medicalservice3)
    SuperTextView superTextView_m3;
    @BindView(R.id.stv_medicalservice4)
    SuperTextView superTextView_m4;
    @BindView(R.id.stv_medicalservice5)
    SuperTextView superTextView_m5;


    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;
    /**
     * 定制化扫描界面Request Code
     */
    public static final int REQUEST_CUSTOM_SCAN = 113;


    /**
     * 高德地图
     */
    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    /**
     * 定位城市
     */
    private List<HotCity> hotCities;
    private boolean enable = true;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {

//        getPermission(getContext(), true, true);

        //初始化定位城市，默认为空时会自动回调定位
        if (mLocatedCity == null) {
            mLocatedCity = new LocatedCity(getString(R.string.cp_locating), "未知", "0");
            locateState = LocateState.LOCATING;
        } else {
            locateState = LocateState.SUCCESS;
        }
        textView_city.setText(mLocatedCity.getSection());


        //高德
        startLocaion();

//        //百度定位
//        LocationService.get().init(MyApplication.getInstance());



        showSearch();


        sib_simple_usage();

        initWidget();

    }

    /**
     * 顶部搜索框显示
     */
    private void showSearch() {

        searchViewDemo.setOnSearchListener(this);

    }


    @Override
    public void search(String content) {
        // 测试AS中git提交代码
        Toast.makeText(getActivity(), "搜索内容： " + content, Toast.LENGTH_SHORT).show();
    }

    /**
     * getPermission 动态获取权限方法
     *
     * @param context    上下文
     * @param isAsk      是否开启权限询问      (Android6.0以下用户可以不开启,所有权限自动可以获得；6.0以上用户若不开启，获取不到某权限时，若你没做相应处理，可能会崩溃)
     * @param isHandOpen 是否询问用户被引导手动开启权限界面   (用户永久禁用某权限时是否引导让用户手动授权权限)
     */
    private void getPermission(Context context, boolean isAsk, final boolean isHandOpen) {
        if (!isAsk) return;

        if (XXPermissions.isHasPermission(getActivity(),
                //所需危险权限可以在此处添加：
                com.hjq.permissions.Permission.ACCESS_COARSE_LOCATION,
                com.hjq.permissions.Permission.ACCESS_FINE_LOCATION
//                , com.hjq.permissions.Permission.READ_EXTERNAL_STORAGE,
//                com.hjq.permissions.Permission.WRITE_EXTERNAL_STORAGE,
//                "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"

        )) {
            Toast.makeText(getActivity(), "已经获得所需所有权限", Toast.LENGTH_SHORT).show();

        } else {
            XXPermissions.with((Activity) context).permission(
                    //同时在此处添加：
                    com.hjq.permissions.Permission.ACCESS_COARSE_LOCATION,
                    com.hjq.permissions.Permission.ACCESS_FINE_LOCATION
//                    , com.hjq.permissions.Permission.READ_EXTERNAL_STORAGE,
//                    com.hjq.permissions.Permission.WRITE_EXTERNAL_STORAGE,
//                    "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"
            ).request(new OnPermission() {
                @Override
                public void noPermission(List<String> denied, boolean quick) {
                    if (quick) {
                        Toast.makeText(getActivity(), "被永久拒绝授权，请手动授予权限", Toast.LENGTH_SHORT).show();
                        //如果是被永久拒绝就跳转到应用权限系统设置页面
                        if (isHandOpen) {

                            Toast.makeText(getActivity(), "获取权限失败,请至手机设置中开启相应权限以确保应用正常使用！", Toast.LENGTH_SHORT).show();
//                            final AlertDialog.Builder normalDialog =
//                                    new AlertDialog.Builder(context);
//                            normalDialog.setTitle("开启权限引导");
//                            normalDialog.setMessage("被您永久禁用的权限为应用必要权限，是否需要引导您去手动开启权限呢？");
//                            normalDialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface arg0, int arg1) {
//                                    XXPermissions.gotoPermissionSettings(context);
//                                }
//                            });
//                            normalDialog.setNegativeButton("下一次", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface arg0, int arg1) {
//
//                                }
//                            });
//                            normalDialog.show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void hasPermission(List<String> granted, boolean isAll) {
                    if (isAll) {
                        Toast.makeText(getActivity(), "获取权限成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "获取权限成功，部分权限未正常授予", Toast.LENGTH_SHORT).show();
                    }

//                    //创建程序初始化目录
//                    SystemUtils.createInitDir();
//
//                    //初始化数据库
//                    SystemUtils.createDatabase(MyApplication.getInstance());
                }
            });


        }
    }

    @Override
    protected void initListeners() {
        //监听事件注册
        registerListener();
    }


    /**
     * 监听事件
     */
    private void registerListener() {
        //        mAdapterHorizontal1.setOnBannerItemClickListener(this);
//        mAdapter.setOnBannerItemClickListener(this);

//        mSpaAdapter.setOnBannerItemClickListener(this);
//        mSpcAdapter.setOnBannerItemClickListener(this);

        myAdapterCourse.setOnBannerItemClickListener(this);
        myAdapterRecommend.setOnBannerItemClickListener(this);

//        mmultiInfoAdapter.setOnBannerItemClickListener(this);


        textView_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCityChoice();
            }

            /**
             * 城市选择
             */
            private void doCityChoice() {

                CityPicker.from(getActivity())
                        .enableAnimation(mEnableAnimation)
                        .setAnimationStyle(mAnim)
                        .setLocatedCity(mLocatedCity)
                        .setHotCities(mHotCities)
                        .setOnPickListener(new OnPickListener() {


                            @Override
                            public void onPick(int position, City data) {

                                textView_city.setText(data.getName());
                                System.out.println("点击的数据：%s，%s" + data.getName() + ":" + data.getCode());

                            }

                            @Override
                            public void onCancel() {
                                XToastUtils.toast("取消选择");
                            }

                            @Override
                            public void onLocate(final OnLocationListener locationListener) {
                                //开始定位

                            }

                        })
                        .show();
            }

        });


        /**
         * 二维码扫描
         */
        iv_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan(ScanType.DEFAULT);
            }
        });

        /**
         * 消息通知
         */
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(MessageFragment.class);
            }
        });

        /**
         * 轮播图片广告
         */
        rib_simple_usage.setOnItemClickL(new BaseBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {

                Utils.goWeb(getContext(), "https://shop18910112.youzan.com");
            }
        });

        /**
         * 健康课程
         */
        myAdapterCourse.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, String item, int position) {
                Utils.goWeb(getContext(), "https://m.ximalaya.com/jiankang/16421114/98924586");
            }
        });

        /**
         * 今日推荐
         */
        myAdapterRecommend.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, String item, int position) {
                Utils.goWeb(getContext(), "https://m.ximalaya.com/jiankang/16421114/98924586");
            }
        });


        /**
         *  医疗服务 ---- 基因检测
         */
        superTextView_m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goWeb(getContext(), "https://ra.mbd.baidu.com/eiwm946?f=cp&u=ee339f186ed1f461");
            }
        });
        superTextView_m1.setOnClickListener(new NoMoreClickListener() {
            @Override
            protected void OnMoreClick(View view) {
                Utils.goWeb(getContext(), "https://ra.mbd.baidu.com/eiwm946?f=cp&u=ee339f186ed1f461");

            }

            @Override
            protected void OnMoreErrorClick() {

            }
        });


        /**
         *  医疗服务 ---- 体检服务
         */
        superTextView_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goWeb(getContext(), "https://ra.mbd.baidu.com/eiwm946?f=cp&u=ee339f186ed1f461");
            }
        });
        /**
         *  医疗服务 ---- 在线问诊
         */
        superTextView_m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goWeb(getContext(), "https://ra.mbd.baidu.com/eiwm946?f=cp&u=ee339f186ed1f461");
            }
        });
        /**
         *  医疗服务 ---- 康养就医
         */
        superTextView_m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goWeb(getContext(), "https://ra.mbd.baidu.com/eiwm946?f=cp&u=ee339f186ed1f461");
            }
        });
        /**
         *  医疗服务 ---- 预约挂号
         */
        superTextView_m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goWeb(getContext(), "https://ra.mbd.baidu.com/eiwm946?f=cp&u=ee339f186ed1f461");
            }
        });

    }


    /**
     * 开启二维码扫描
     */
    @Permission(CAMERA)
    @IOThread(ThreadType.Single)
    private void startScan(ScanType scanType) {
        XQRCode.startScan(this, REQUEST_CODE);
    }

    @Override
    public void onItemClick(View itemView, int position) {

    }

    /**
     * 二维码扫描类型
     */
    public enum ScanType {
        /**
         * 默认
         */
        DEFAULT,
        /**
         * 默认(修改主题）
         */
        DEFAULT_Custom,
        /**
         * 远程
         */
        REMOTE,
        /**
         * 自定义
         */
        CUSTOM,
    }

    @Permission(CAMERA)
    private void initPermission() {
        XToastUtils.toast("相机权限已获取！");
        XQRCode.setAutoFocusInterval(800);
    }


    /**
     * 图片轮播的简单使用
     */
    private void sib_simple_usage() {
        mData = DemoDataProvider.getBannerList();
        rib_simple_usage.setSource(mData).startScroll();
    }

    /**
     * 控件的编辑设置
     */
    private void initWidget() {
        //编辑
        editRecycleView();
    }




    private void editRecycleView() {

        iill_service_course.setAdapter(myAdapterCourse = new MyRecyclerViewAdapter(DemoDataProvider.urls_7, Consts.ITEM_TYPE_ADAPTER_TDPR));
        iill_service_course.setOrientation(LinearLayoutManager.HORIZONTAL);

        iill_recommendtoday.setAdapter(myAdapterRecommend = new MyRecyclerViewAdapter(DemoDataProvider.urls_3, Consts.ITEM_TYPE_ADAPTER_COURSE));
        iill_recommendtoday.setOrientation(LinearLayoutManager.VERTICAL);


    }

    /**
     * BannerLayout的监听
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    /**
     * 二维码处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CUSTOM_SCAN && resultCode == RESULT_OK) {
            handleScanResult(data);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //处理二维码扫描结果
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //处理扫描结果（在界面上显示）
            handleScanResult(data);
        }

        //选择系统图片并解析
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                getAnalyzeQRCodeResult(uri);
            }
        }

    }

    @SuppressLint("MissingPermission")
    private void getAnalyzeQRCodeResult(Uri uri) {
        XQRCode.analyzeQRCode(PathUtils.getFilePathByUri(getContext(), uri), new QRCodeAnalyzeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                XToastUtils.toast("解析结果:" + result, Toast.LENGTH_LONG);
            }

            @Override
            public void onAnalyzeFailed() {
                XToastUtils.toast("解析二维码失败", Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 处理二维码扫描结果
     *
     * @param data
     */
    private void handleScanResult(Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                if (bundle.getInt(XQRCode.RESULT_TYPE) == XQRCode.RESULT_SUCCESS) {
                    String result = bundle.getString(XQRCode.RESULT_DATA);
                    XToastUtils.toast("解析结果:" + result, Toast.LENGTH_LONG);
                } else if (bundle.getInt(XQRCode.RESULT_TYPE) == XQRCode.RESULT_FAILED) {
                    XToastUtils.toast("解析二维码失败", Toast.LENGTH_LONG);
                }
            }
        }
    }



    /**
     * 百度定位
     */
//    public static class OnBDLocationListener extends BDAbstractLocationListener {
//
//        private OnLocationListener mOnLocationListener;
//
//        public OnBDLocationListener setOnLocationListener(OnLocationListener onLocationListener) {
//            mOnLocationListener = onLocationListener;
//            return this;
//        }
//
//        @Override
//        public void onReceiveLocation(BDLocation bdLocation) {
//            if (mOnLocationListener != null) {
//                mOnLocationListener.onLocationChanged(LocationService.onReceiveLocation(bdLocation), LocateState.SUCCESS);
//                LocationService.get().unregisterListener(this);
//            }
//        }
//    }

    public void startLocaion(){


        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        mLocationClient.setLocationListener(mLocationListener);

        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }


    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation !=null ) {
                if (amapLocation.getErrorCode() == 0) {

                    textView_city.setText(amapLocation.getCity());
                    mLocatedCity = new LocatedCity(amapLocation.getCity(), amapLocation.getProvince(), amapLocation.getCityCode());

                    //定位成功回调信息，设置相关消息
                    Log.i(TAG,"当前定位结果来源-----"+amapLocation.getLocationType());//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    Log.i(TAG,"纬度 ----------------"+amapLocation.getLatitude());//获取纬度
                    Log.i(TAG,"经度-----------------"+amapLocation.getLongitude());//获取经度
                    Log.i(TAG,"精度信息-------------"+amapLocation.getAccuracy());//获取精度信息
                    Log.i(TAG,"地址-----------------"+amapLocation.getAddress());//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    Log.i(TAG,"国家信息-------------"+amapLocation.getCountry());//国家信息
                    Log.i(TAG,"省信息---------------"+amapLocation.getProvince());//省信息
                    Log.i(TAG,"城市信息-------------"+amapLocation.getCity());//城市信息
                    Log.i(TAG,"城区信息-------------"+amapLocation.getDistrict());//城区信息
                    Log.i(TAG,"街道信息-------------"+amapLocation.getStreet());//街道信息
                    Log.i(TAG,"街道门牌号信息-------"+amapLocation.getStreetNum());//街道门牌号信息
                    Log.i(TAG,"城市编码-------------"+amapLocation.getCityCode());//城市编码
                    Log.i(TAG,"地区编码-------------"+amapLocation.getAdCode());//地区编码
                    Log.i(TAG,"当前定位点的信息-----"+amapLocation.getAoiName());//获取当前定位点的AOI信息
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

}
