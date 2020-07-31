package com.kyy.recuperationcourt.common.other.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kyy.recuperationcourt.R;


/**
 * 自定义LinearLayout
 *
 * @author cxa
 * @since 2019/11/15
 */
public class ItemInfoLinearLayout extends FrameLayout {


    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;


    public ItemInfoLinearLayout(Context context) {
        this(context, null);
    }

    public ItemInfoLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.ItemInfoBannerLayoutStyle);
    }

    public ItemInfoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        //轮播图部分
        mRecyclerView = new RecyclerView(context);
        LayoutParams vpLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mRecyclerView, vpLayoutParams);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    /**
     * 设置轮播数据集
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }



    public ItemInfoLinearLayout setOrientation(int orientation) {
        mLayoutManager.setOrientation(orientation);
        return this;
    }

    public interface OnItemInfoLinearItemClickListener {
        void onItemClick(int position);
    }


}
