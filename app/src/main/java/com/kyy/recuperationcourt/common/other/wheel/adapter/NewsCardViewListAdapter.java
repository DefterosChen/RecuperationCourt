package com.kyy.recuperationcourt.common.other.wheel.adapter;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

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
import com.kyy.recuperationcourt.MyApplication;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.entity.KkyItem;
import com.kyy.recuperationcourt.common.other.wheel.view.CustomMarkerView;
import com.kyy.recuperationcourt.common.other.xui.util.XToastUtils;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XUE
 * @since 2019/5/9 10:41
 */
public class NewsCardViewListAdapter extends BaseRecyclerAdapter<KkyItem> implements OnChartValueSelectedListener {



    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.adapter_news_card_view_list_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, KkyItem model) {
        if (model != null) {
            holder.text(R.id.tv_titile_name, model.getTitle());
//            holder.text(R.id.iv_binddevice, model.getTag());
            holder.text(R.id.tv_datashow, model.getData());
//            holder.text(R.id.tv_praise, model.getPraise() == 0 ? "点赞" : String.valueOf(model.getPraise()));
//            holder.text(R.id.tv_comment, model.getComment() == 0 ? "评论" : String.valueOf(model.getComment()));
            holder.text(R.id.tv_record_time,  model.getTime());

            holder.viewClick(R.id.iv_binddevice, new RecyclerViewHolder.OnViewItemClickListener<Object>() {
                @Override
                public void onViewItemClick(View view, Object item, int position) {
                    XToastUtils.info("绑定设备");

                }
            },getItem(position),position);


            holder.viewClick(R.id.iv_recorddata, new RecyclerViewHolder.OnViewItemClickListener<Object>() {
                @Override
                public void onViewItemClick(View view, Object item, int position) {
                    XToastUtils.info("记录数据");
                }
            },getItem(position),position);


            LineChart chart = holder.findViewById(R.id.chart);
            initChart(chart);

        }
    }

    private void initChart(LineChart chart) {
        initChartStyle(chart);
        initChartLabel(chart);
        setChartData(30, 180,chart);

        chart.animateX(1500);
        chart.setOnChartValueSelectedListener(this);
    }

    /**
     * 初始化图表的样式
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
     * @param chart
     */
    protected void initChartLabel(LineChart chart) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
    }

    /**
     * 设置图表数据
     *  @param count 一组数据的数量
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

}
