package com.kyy.recuperationcourt.common.view.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.entity.KkyItem;
import com.kyy.recuperationcourt.common.other.wheel.adapter.NewsCardViewListAdapter;
import com.kyy.recuperationcourt.common.other.widget.loadview.MaterialLoadMoreView;
import com.kyy.recuperationcourt.common.other.xui.DemoDataProvider;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.data.DataChartFragment;
import com.kyy.recuperationcourt.common.view.fragment.data.DataManageFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.recyclerview.XLinearLayoutManager;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import butterknife.BindView;

/**
 * 数据
 * 2019/11/12
 * cxa
 */
@Page(name = "数据", anim = CoreAnim.none)
public class DataFragment extends MyBaseFragment {


    @BindView(R.id.tv_data_edit)
    TextView textView_edit;
    @BindView(R.id.recyclerView)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;




    private NewsCardViewListAdapter mAdapter;
    private Handler mHandler = new Handler();
    private boolean mEnableLoadMore;


    public DataFragment() {
        // Required empty public constructor
    }


    public static DataFragment newInstance() {
        DataFragment fragment = new DataFragment();
        return fragment;
    }

//        @Override
//        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        View view = super.onCreateView(inflater, container, savedInstanceState);
//
//                View  view =inflater.inflate(R.layout.fragment_data,container,false);
//
//                return view;
//        }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initViews() {

        //设置渐变监听
//        mCollapsingToolbarLayout.setOnScrimsListener(this);

        recyclerView.setLayoutManager(new XLinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter = new NewsCardViewListAdapter());
        swipeRefreshLayout.setColorSchemeColors(0xff0099cc, 0xffff4444, 0xff669900, 0xffaa66cc, 0xffff8800);
    }

    @Override
    protected void initListeners() {
        /**
         *  我的数据管理编辑
         */
        textView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(DataManageFragment.class);
            }
        });


        mAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<KkyItem>() {
            @Override
            public void onItemClick(View itemView, KkyItem item, int position) {
//                                Utils.goWeb(getContext(), item.getDetailUrl());
                openPage(DataChartFragment.class);
            }
        });

        // 刷新监听。
        swipeRefreshLayout.setOnRefreshListener(mRefreshListener);
        refresh();
    }





    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadData();
        }
    };

    private void refresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    private void loadData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.refresh(DemoDataProvider.getDataNewInfos());
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
//                                enableLoadMore();
            }
        }, 1000);
    }


    /**
     * 确保有数据加载了才开启加载更多
     */
    private void enableLoadMore() {
        if (recyclerView != null && !mEnableLoadMore) {
            mEnableLoadMore = true;
            useMaterialLoadMore();
            // 加载更多的监听。
            recyclerView.setLoadMoreListener(mLoadMoreListener);
            recyclerView.loadMoreFinish(false, true);
        }
    }

    private void useMaterialLoadMore() {
        MaterialLoadMoreView loadMoreView = new MaterialLoadMoreView(getContext());
        recyclerView.addFooterView(loadMoreView);
        recyclerView.setLoadMoreView(loadMoreView);
    }


    /**
     * 加载更多。
     */
    private SwipeRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.loadMore(DemoDataProvider.getDataNewInfos());
                    if (recyclerView != null) {
                        recyclerView.loadMoreFinish(false, true);
                    }
                }
            }, 1000);
        }
    };


    @Override
    public void onDestroyView() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroyView();
    }


}
