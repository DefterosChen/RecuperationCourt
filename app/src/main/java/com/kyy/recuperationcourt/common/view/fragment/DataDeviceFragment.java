package com.kyy.recuperationcourt.common.view.fragment;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.tabs.TabLayout;
import com.kyy.recuperationcourt.MyApplication;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.other.wheel.adapter.MyRecyclerViewAdapter;
import com.kyy.recuperationcourt.common.other.wheel.adapter.NewsCardViewListAdapter;
import com.kyy.recuperationcourt.common.other.wheel.view.CustomMarkerView;
import com.kyy.recuperationcourt.common.other.widget.layout.ItemInfoLinearLayout;
import com.kyy.recuperationcourt.common.other.xui.DemoDataProvider;
import com.kyy.recuperationcourt.common.other.xui.common.utils.Utils;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.device.DeviceBindFragment;
import com.kyy.recuperationcourt.common.view.fragment.device.ManageDevicesFragment;
import com.kyy.recuperationcourt.common.view.fragment.device.smartwatch.MainWatchFragment;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.shehuan.niv.NiceImageView;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@Page(name = "数据", anim = CoreAnim.none)
public class DataDeviceFragment extends MyBaseFragment implements TabLayout.OnTabSelectedListener, BannerLayout.OnBannerItemClickListener
        , SmartViewHolder.OnItemClickListener, OnChartValueSelectedListener {

    @BindView(R.id.iill_serviceassess)
    ItemInfoLinearLayout iill_service_assess;

    private MyRecyclerViewAdapter myAdapterAssess;

    @BindView(R.id.rd_my_device_1st)
    RadioButton rb_mydevice_1;
    @BindView(R.id.rd_my_device_2nd)
    RadioButton rb_mydevice_2;
    @BindView(R.id.rd_my_device_3rd)
    RadioButton rb_mydevice_3;
    @BindView(R.id.rd_my_device_more)
    RadioButton rb_mydevice_4;

    @BindView(R.id.iv_shop_advert)
    ImageView iv_shop_advert;

    @BindView(R.id.tv_titile_name)
    TextView view_data_title;
    @BindView(R.id.tv_datashow)
    TextView view_data_show;
    @BindView(R.id.tv_record_time)
    TextView view_data_time;
    @BindView(R.id.iv_binddevice)
    NiceImageView view_data_bind;
    @BindView(R.id.iv_recorddata)
    NiceImageView view_data_record;
    @BindView(R.id.chart)
    LineChart lineChart;

    private NewsCardViewListAdapter mAdapter;

    public DataDeviceFragment() {
        // Required empty public constructor
    }

    public static DataDeviceFragment newInstance() {
        DataDeviceFragment fragment = new DataDeviceFragment();
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data_device;
    }


    @Override
    protected void initViews() {
        iill_service_assess.setAdapter(myAdapterAssess = new MyRecyclerViewAdapter(DemoDataProvider.urls_6, Consts.ITEM_TYPE_ADAPTER_TDPB));
        iill_service_assess.setOrientation(LinearLayoutManager.HORIZONTAL);

        //编辑设备按钮
        editButton();

        dataShow();
    }

    /**
     * 常用数据展示 （正式版本用 步数 数据）
     */
    private void dataShow() {

//        view_data_title.setText(model.getTitle());
//        view_data_show.setText(model.getData());
//        view_data_time.setText(model.getTime());
        view_data_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToastUtils.info("绑定设备");
            }
        });

        view_data_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToastUtils.info("记录数据");
            }
        });


        initChart(lineChart);


    }

    private void initChart(LineChart chart) {
        initChartStyle(chart);
        initChartLabel(chart);
        setChartData(30, 180, chart);

        chart.animateX(1500);
        chart.setOnChartValueSelectedListener(this);
    }

    /**
     * 初始化图表的样式
     *
     * @param chart
     */
    protected void initChartStyle(LineChart chart) {
        chart.setBackgroundColor(Color.WHITE);
        //关闭描述
        chart.getDescription().setEnabled(false);
        //设置不画背景网格
        chart.setDrawGridBackground(false);

        CustomMarkerView mv = new CustomMarkerView(MyApplication.getContextObject(), R.layout.marker_view_xy);
        mv.setChartView(chart);
        chart.setMarker(mv);

        //开启所有触摸手势
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setPinchZoom(false);

        initXYAxisStyle(chart);
    }

    /**
     * 初始化图表X、Y轴的样式
     *
     * @param chart
     */
    private void initXYAxisStyle(LineChart chart) {
        XAxis xAxis = chart.getXAxis();
        // 设置垂直的网格线
        xAxis.enableGridDashedLine(10f, 10f, 0f);

        YAxis yAxis = chart.getAxisLeft();
        // 设置水平的网格线
        yAxis.enableGridDashedLine(10f, 10f, 0f);
        // axis range
        yAxis.setAxisMaximum(200f);
        yAxis.setAxisMinimum(-50f);
        // 关闭Y轴右侧
        chart.getAxisRight().setEnabled(false);

        LimitLine upperLimit = new LimitLine(150f, "Upper Limit");
        upperLimit.setLineWidth(4f);
        upperLimit.enableDashedLine(10f, 10f, 0f);
        upperLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upperLimit.setTextSize(10f);

        LimitLine lowerLimit = new LimitLine(-30f, "Lower Limit");
        lowerLimit.setLineWidth(4f);
        lowerLimit.enableDashedLine(10f, 10f, 0f);
        lowerLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lowerLimit.setTextSize(10f);

        // draw limit lines behind data instead of on top
        xAxis.setDrawLimitLinesBehindData(true);
        yAxis.setDrawLimitLinesBehindData(true);

        // add limit lines
        yAxis.addLimitLine(upperLimit);
        yAxis.addLimitLine(lowerLimit);
    }

    /**
     * 初始化图表的 标题 样式
     *
     * @param chart
     */
    protected void initChartLabel(LineChart chart) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
    }

    /**
     * 设置图表数据
     *
     * @param count 一组数据的数量
     * @param range
     * @param chart
     */
    protected void setChartData(int count, float range, LineChart chart) {
        List<Entry> values = new ArrayList<>();
        //设置数据源
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) - 30;
            values.add(new Entry(i, val, MyApplication.getContextObject().getResources().getDrawable(R.drawable.icon_kky_star_full)));
        }
        LineDataSet set1;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setDrawIcons(false);
            // 设置画虚线
            set1.enableDashedLine(10f, 5f, 0f);
            // 设置线的样式
            set1.setColor(Color.BLACK);
            set1.setLineWidth(1f);
            //设置点的样式
            set1.setCircleColor(Color.BLACK);
            set1.setCircleRadius(3f);
            // 设置不画空心圆
            set1.setDrawCircleHole(false);

            // 设置数据组 标题的样式
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);
            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // 设置折线图的填充区域
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    if (chart == null) return 0;
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // 设置折线图的填充颜色
            if (com.github.mikephil.charting.utils.Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(MyApplication.getContextObject(), R.color.icon_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            List<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            chart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
//        XToastUtils.toast("选中了,  x轴值:" + e.getX() + ", y轴值:" + e.getY());
    }

    @Override
    public void onNothingSelected() {

    }


    @Override
    protected void initListeners() {
        myAdapterAssess.setOnBannerItemClickListener(this);

        /**
         * 健康评估
         */
        myAdapterAssess.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, String item, int position) {
                Utils.goWeb(getContext(), "https://mr.baidu.com/ldspene?f=cp&u=dc9890ca5dc4be7c");
            }
        });


        /**
         * 我的设备（1）
         */
        rb_mydevice_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(MainWatchFragment.class);
            }
        });
        /**
         * 我的设备（2）
         */
        rb_mydevice_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(DeviceBindFragment.class);
            }
        });
        /**
         * 我的设备（3）
         */
        rb_mydevice_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(DeviceBindFragment.class);
            }
        });
        /**
         * 我的设备（更多）
         */
        rb_mydevice_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(ManageDevicesFragment.class);
            }
        });


        /**
         * 广告栏
         */
        iv_shop_advert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.goWeb(getContext(), "https://shop18910112.youzan.com");
            }
        });
    }


    private void editButton() {
        RadioButton[] rbs = new RadioButton[4];
        rbs[0] = rb_mydevice_1;
        rbs[1] = rb_mydevice_2;
        rbs[2] = rb_mydevice_3;
        rbs[3] = rb_mydevice_4;

        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drawables = rb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 1 / 3, drawables[1].getMinimumHeight() * 1 / 3);
            //定义一个Rect边界
            drawables[1].setBounds(r);
//            //给指定的radiobutton设置drawable边界
//            if (rb.getId() == R.id.rd_my_device_more) {
//                r = new Rect(0, 0, drawables[1].getMinimumWidth()*1/2, drawables[1].getMinimumHeight());
//                drawables[1].setBounds(r);
//            }
            //添加限制给控件
            rb.setCompoundDrawables(null, drawables[1], null, null);
        }
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

    @Override
    public void onItemClick(View itemView, int position) {

    }

    @Override
    public void onItemClick(int position) {

    }
}
