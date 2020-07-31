package com.kyy.recuperationcourt.common.view.fragment.device.smartwatch;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xutil.common.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
@Page(name = "智能手表-定位", anim = CoreAnim.none)
public class MapWatchFragment extends MyBaseFragment implements LocationSource, AMapLocationListener {

    MapView mMapView;

    MyLocationStyle myLocationStyle;
    AMap aMap;
    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;


    public MapWatchFragment() {
        // Required empty public constructor
    }

    public static MapWatchFragment newInstance() {
        MapWatchFragment fragment = new MapWatchFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        SDKInitializer.initialize(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.fragment_map_watch, container, false);

        mMapView = view.findViewById(R.id.nav_mapView);

        mMapView.onCreate(savedInstanceState);

        initViews();
        initListeners();

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    protected void initViews() {


        //初始化地图控制器对象
        if (aMap != null) {
            aMap.clear();
        }

        aMap = mMapView.getMap();

        //默认显示图层比例 zoomlevel in [3, 19)
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);


    }

    @Override
    protected void initListeners() {
        super.initListeners();


    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            //定位成功回调信息，设置相关消息
            aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
            aMapLocation.getLatitude();//获取纬度
            aMapLocation.getLongitude();//获取经度
            aMapLocation.getAccuracy();//获取精度信息
            String adCode = aMapLocation.getAdCode();
            String cityCode = aMapLocation.getCityCode();
            String city = aMapLocation.getCity();
            String address = aMapLocation.getAddress();
            String aoiName = aMapLocation.getAoiName();
            String poiName = aMapLocation.getPoiName();
            String coordType = aMapLocation.getCoordType();
            String country = aMapLocation.getCountry();
            Logger.d("adCode=" + adCode + " city=" + city + " address=" + address + " aoiName=" + aoiName + " poiName=" + poiName + " coordType=" + coordType + " cityCode=" + cityCode);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(aMapLocation.getTime());
            df.format(date);//定位时间
            mListener.onLocationChanged(aMapLocation);
        } else {
            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
            Logger.e("AmapError----location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        fixLocation(onLocationChangedListener);
    }

    @Override
    public void deactivate() {

    }

    private void fixLocation(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            // 设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(false);

            // 设置定位间隔,单位毫秒,默认为2000ms 此处自设为2秒
            mLocationOption.setInterval(2 * 1000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

}
