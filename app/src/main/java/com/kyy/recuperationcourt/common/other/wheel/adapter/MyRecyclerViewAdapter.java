package com.kyy.recuperationcourt.common.other.wheel.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.other.xui.DemoDataProvider;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;

import java.util.Arrays;
import java.util.List;

public class MyRecyclerViewAdapter extends BaseRecyclerAdapter<String> {


    int vType;

    /**
     * 默认加载图片
     */
    private ColorDrawable mColorDrawable;

    /**
     * 是否允许进行缓存
     */
    private boolean mEnableCache = true;

    private BannerLayout.OnBannerItemClickListener mOnBannerItemClickListener;


    public MyRecyclerViewAdapter() {
        super();
//        mColorDrawable = new ColorDrawable(R.drawable.icon_blue_more);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    public MyRecyclerViewAdapter(List<String> list) {
        super(list);
//        mColorDrawable = new ColorDrawable(R.drawable.icon_blue_more);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    public MyRecyclerViewAdapter(String[] list) {
        super(Arrays.asList(list));
//        mColorDrawable = new ColorDrawable(R.drawable.icon_blue_more);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }


    public MyRecyclerViewAdapter(String[] list, int viewType) {
        super(Arrays.asList(list));
        vType = viewType;
//        mColorDrawable = new ColorDrawable(R.drawable.icon_blue_more);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }


//    @Override
//    public int getItemCount() {
//        return DemoDataProvider.getAssessItemList().size();
//    }


    /**
     * 适配的布局
     *
     * @param viewType
     * @return
     */
    @Override
    public int getItemLayoutId(int viewType) {

        switch (vType) {
            case Consts
                    .ITEM_TYPE_ADAPTER_TDPT:

                return R.layout.item_kyy_pic_text_detail;

            case Consts
                    .ITEM_TYPE_ADAPTER_TDPB:

                return R.layout.item_text_detail_pic;

            case Consts
                    .ITEM_TYPE_ADAPTER_TDPR:

                return R.layout.item_title_detail_picright;

            case Consts
                    .ITEM_TYPE_ADAPTER_COURSE:

                return R.layout.item_kky_pic_infos;

            default:
                return R.layout.item_singlepic;
        }

    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     * @param imgUrl
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, final int position, String imgUrl) {

        switch (vType) {
            case Consts
                    .ITEM_TYPE_ADAPTER_TDPT:

                initItemDataTDPT(holder, position);

                break;

            case Consts
                    .ITEM_TYPE_ADAPTER_TDPB:

                initItemDataTDPB(holder, position);
                break;
            case Consts
                    .ITEM_TYPE_ADAPTER_TDPR:

                initItemDataTDPR(holder, position);
                break;
            case Consts
                    .ITEM_TYPE_ADAPTER_COURSE:

                initItemDataCOURSE(holder, position);
                break;

            default:
                break;
        }
    }

    private void initItemDataCOURSE(RecyclerViewHolder holder, int position) {

        ImageView imageView = holder.findViewById(R.id.iv_item_pic);
        TextView textView_title = holder.findViewById(R.id.tv_item_title);
        TextView textView_details = holder.findViewById(R.id.tv_item_details);
        TextView textView_price = holder.findViewById(R.id.tv_item_price);
        TextView textView_status = holder.findViewById(R.id.tv_item_status);

        imageView.setImageResource(DemoDataProvider.getRecommendItemList().get(position).getUrls_res());
        textView_title.setText(DemoDataProvider.getRecommendItemList().get(position).getTitle());
        textView_details.setText(DemoDataProvider.getRecommendItemList().get(position).getDetails());
        textView_price.setText(DemoDataProvider.getRecommendItemList().get(position).getPrice());
        textView_status.setText(DemoDataProvider.getRecommendItemList().get(position).getStatus());

    }

    private void initItemDataTDPR(RecyclerViewHolder holder, int position) {

        ImageView imageView = holder.findViewById(R.id.iv_item_pic);
        TextView textView_title = holder.findViewById(R.id.tv_item_title);
        TextView textView_details = holder.findViewById(R.id.tv_item_details);

        imageView.setImageResource(DemoDataProvider.getCourseItemList().get(position).getUrls_res());
        textView_title.setText(DemoDataProvider.getCourseItemList().get(position).getTitle());
        textView_details.setText(DemoDataProvider.getCourseItemList().get(position).getDetails());

    }

    private void initItemDataTDPB(RecyclerViewHolder holder, int position) {


        ImageView imageView = holder.findViewById(R.id.iv_item_pic);
        TextView textView_title = holder.findViewById(R.id.tv_item_title);
        TextView textView_details = holder.findViewById(R.id.tv_item_details);

        imageView.setImageResource(DemoDataProvider.getAssessItemList().get(position).getUrls_res());
        textView_title.setText(DemoDataProvider.getAssessItemList().get(position).getTitle());
        textView_details.setText(DemoDataProvider.getAssessItemList().get(position).getDetails());

    }

    private void initItemDataTDPT(RecyclerViewHolder holder, int position) {
        ImageView imageView = holder.findViewById(R.id.iv_item_pic);
        TextView textView_title = holder.findViewById(R.id.tv_item_title);
        TextView textView_details = holder.findViewById(R.id.tv_item_details);

        imageView.setImageResource(DemoDataProvider.getServiceItemList().get(position).getUrls_res());
        textView_title.setText(DemoDataProvider.getServiceItemList().get(position).getTitle());
        textView_details.setText(DemoDataProvider.getServiceItemList().get(position).getDetails());
    }


    /**
     * 设置是否允许缓存
     *
     * @param enableCache
     * @return
     */
    public MyRecyclerViewAdapter enableCache(boolean enableCache) {
        mEnableCache = enableCache;
        return this;
    }

    /**
     * 获取是否允许缓存
     *
     * @return
     */
    public boolean getEnableCache() {
        return mEnableCache;
    }

    public ColorDrawable getColorDrawable() {
        return mColorDrawable;
    }

    public MyRecyclerViewAdapter setColorDrawable(ColorDrawable colorDrawable) {
        mColorDrawable = colorDrawable;
        return this;
    }

    public MyRecyclerViewAdapter setOnBannerItemClickListener(BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {
        mOnBannerItemClickListener = onBannerItemClickListener;
        return this;
    }
}