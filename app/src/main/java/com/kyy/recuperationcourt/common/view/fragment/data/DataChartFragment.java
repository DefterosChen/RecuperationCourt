package com.kyy.recuperationcourt.common.view.fragment.data;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.charts.Chart;
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
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.tabs.TabLayout;
import com.kyy.recuperationcourt.MyApplication;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.other.wheel.view.CustomMarkerView;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 *  CXA
 *
 *  2019/12/03
 */
@Page(name = "数据详细", anim = CoreAnim.none)
public class DataChartFragment extends MyBaseFragment implements TabLayout.OnTabSelectedListener, OnChartValueSelectedListener {


    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.iv_back)
    ImageView iv_back;


    private Map<ContentPage, View> mPageMap = new HashMap<>();

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return ContentPage.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            ContentPage page = ContentPage.getPage(position);
            View view = getPageView(page);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return ContentPage.getPageNames()[position];
        }
    };

    private View getPageView(ContentPage page) {
        View view = mPageMap.get(page);
        if (view == null) {
            switch (page.name()) {
                case "图示":

                    view = initChart();
                    mPageMap.put(page, view);
                    return view;

                case "列表":

                    view = initChart();
                    mPageMap.put(page, view);
                    return view;

                default:
                    return view;

            }
        }

        return view;
    }


    public DataChartFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data_chart;
    }

    @Override
    protected void initViews() {
        mTabLayout.addOnTabSelectedListener(this);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });
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


    public enum ContentPage {

        图示(0),
        列表(1);

        private final int position;

        ContentPage(int pos) {
            position = pos;
        }

        public static ContentPage getPage(int position) {
            return ContentPage.values()[position];
        }

        public static int size() {
            return ContentPage.values().length;
        }

        public static String[] getPageNames() {
            ContentPage[] pages = ContentPage.values();
            String[] pageNames = new String[pages.length];
            for (int i = 0; i < pages.length; i++) {
                pageNames[i] = pages[i].name();
            }
            return pageNames;
        }

        public int getPosition() {
            return position;
        }
    }




    /**
     * 图示
     *
     */
    private Chart initChart() {

        LineChart chart = new LineChart(getActivity());

        initChartStyle(chart);
        initChartLabel(chart);
        setChartData(30, 180, chart);

        chart.animateX(1500);
        chart.setOnChartValueSelectedListener(this);

        return chart;
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
            if (Utils.getSDKInt() >= 18) {
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


    /**
     * 列表
     */
    private View initForm() {


        return null;
    }


}
