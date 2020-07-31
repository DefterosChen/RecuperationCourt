package com.kyy.recuperationcourt.common.view.fragment.consult;


import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.server.ServiceCall;
import com.kyy.recuperationcourt.common.model.entity.consult.NewsInfo;
import com.kyy.recuperationcourt.common.model.entity.message.inter.NewsInfoData;
import com.kyy.recuperationcourt.common.other.wheel.systemstatusbar.StatusBarCompat;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.widget.progress.materialprogressbar.MaterialProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  新闻列表
 */

public class NewsFragment extends NewsBaseFragment {

    String typeId = "0";

    int pageNum = 0;

    public static String BUNDLE_TITLE = "title";

    public static NewsAdapter adapter;

    ArrayList<NewsInfo> list_news = new ArrayList<>();

    RecyclerView mRecyclerView;
    SmartRefreshLayout mRefreshLayout;
    MaterialProgressBar progressBar;


    int mCount;//单类新闻总数

    //获取最后一个可见view的坐标
    int lastItemPosition;
    //获取第一个可见view的坐标
    int firstItemPosition;


    /**
     * handler
     * 通过线程获取数据并加载adapter
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1 == 10012) {
                Toast.makeText(getActivity(), R.string.kky_loadfalse, Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.GONE);

            if (pageNum == 1) {
                //设置RecyclerView的数据加载
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new NewsAdapter(getActivity(), list_news);
                mRecyclerView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }


            adapter.setLinster(new NewsAdapter.ItemOnClickLinster() {
                @Override
                public void textItemOnClick(View view, int position) {
                    //进入新闻详情页面

                    System.out.println("查看详情");

                    NewsInfo newsInfo = list_news.get(position);
                    Bundle params = new Bundle();


                    params.putString(NewsDetailFragment.TITLE,newsInfo.getTitle());
                    params.putString(NewsDetailFragment.AUTHOR,newsInfo.getAuthor());
                    params.putString(NewsDetailFragment.TIME,newsInfo.getCreateTime());
                    params.putString(NewsDetailFragment.DES,newsInfo.getDescription());
                    params.putString(NewsDetailFragment.CONTENT,newsInfo.getContent());
                    params.putString(NewsDetailFragment.PIC,newsInfo.getPicUrls());

                    openPage(NewsDetailFragment.class,params);
                }
            });


        }
    };


    @Override
    protected int setContentView() {
        return R.layout.fragment_refresh_status_layout;
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            typeId = bundle.getString(BUNDLE_TITLE);
        }
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        progressBar = rootView.findViewById(R.id.progressbar_circle);

        refreshData();

    }


    /**
     * 数据下拉刷新、上拉加载
     */
    private void refreshData() {

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        list_news = new ArrayList<>();
                        pageNum = 0;
                        getNewList(typeId);
                    }
                }, 1000);

            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (list_news.size() >= mCount) {
                    Toast.makeText(getActivity(), "已加载到最后一条！", Toast.LENGTH_SHORT).show();
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                refreshLayout.finishLoadMore();
                getNewList(typeId);
            }
        });
    }


    @Override
    protected void lazyLoad() {


        handler.post(getDatas);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //这里滑动停止，开始加载可见项
                    System.out.println(firstItemPosition + "   " + lastItemPosition);
                    adapter.setScrolling(false);
                    adapter.notifyDataSetChanged();
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //这里做处理（停止加载一切事情）
                    adapter.setScrolling(true);
                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    firstItemPosition = linearManager.findFirstVisibleItemPosition();
                }
            }
        });

        //如果确定每个item的内容不会改变RecyclerView的大小，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    /**
     * 获取数据
     */
    Runnable getDatas = new Runnable() {
        @Override
        public void run() {

            progressBar.setVisibility(View.VISIBLE);

            getNewList(typeId);

        }
    };

    /**
     * 解析json数据
     *
     * @param reponse
     */
    private void JsonDta(String reponse) {
        //创建对象接收返回的数据
        NewsInfoData data = null;
        data = new Gson().fromJson(reponse, NewsInfoData.class);
        if (!data.isOk()) {
            //模仿网络请求返回的参数
            Message message = handler.obtainMessage();
//            message.arg1 = data.getError_code();
            handler.sendMessage(message);
        } else {

            mCount = data.getData().getTotal();

            for (int i = 0; i < data.getData().getRecords().size(); i++) {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.setTitle(data.getData().getRecords().get(i).getTitle());
                newsInfo.setCreateTime(data.getData().getRecords().get(i).getCreateTime());
                newsInfo.setPicUrls(data.getData().getRecords().get(i).getPicUrls());
                newsInfo.setDescription(data.getData().getRecords().get(i).getDescription());
                newsInfo.setContent(data.getData().getRecords().get(i).getContent());
                newsInfo.setAuthor(data.getData().getRecords().get(i).getAuthor());
                newsInfo.setCategoryId(data.getData().getRecords().get(i).getCategoryId());
                list_news.add(newsInfo);
            }
            //模仿网络请求返回的参数
            Message message = handler.obtainMessage();
            handler.sendMessage(message);
        }
    }

    /**
     * 传值
     *
     * @param typeId
     * @return
     */
    public static NewsFragment getInstance(String typeId) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, typeId);
        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.cyan));
        }
    }


    /**
     * 获取新闻列表详情信息
     *
     * @param typeId
     */
    private void getNewList(String typeId) {

        pageNum++;
        String url = ServiceCall.getServerURL() + "/cms/article/page?current=" + pageNum + "&size=10&descs=create_time&categoryId=" + typeId; // current :页数   size :单次刷新条数 固定10条
        System.out.println(url);

        final Request request = new Request.Builder()
                .url(url)//请求的url
                .header("tenant-id", "1")
//                .addHeader("Authorization", ServiceCall.AUTHORIZATION_HEADER_BEARER + accessToken)
                .get()
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
                System.out.println("连接加载失败！");
            }

            //异步请求(非主线程)
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String str = response.body().string();

                JsonDta(str);

            }
        });

    }


    public static class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        Activity context;

        List<NewsInfo> data;

        LayoutInflater inflater;

        View view;

        /**
         * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
         */
        LruCache<String, BitmapDrawable> mMemoryCache;


        public NewsAdapter(Activity activity, List<NewsInfo> datas) {
            this.context = activity;
            this.data = datas;
            inflater = LayoutInflater.from(activity);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kky_infos_pic, parent, false);
            return new ViewHolder(view);
        }

        /**
         * 标记是否正在滑动，如果为true，就暂停加载图片
         */
        protected boolean isScrolling = false;

        /**
         * 赋值
         *
         * @param scrolling
         */
        public void setScrolling(boolean scrolling) {
            isScrolling = scrolling;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int position) {

            viewHolder.tv_title.setText(data.get(position).getTitle());
            viewHolder.tv_time.setText(data.get(position).getCreateTime());

            //图片加载框架
            Glide.with(context)
                    .load(data.get(position).getPicUrls())
                    .into(viewHolder.iv);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        /**
         * //自定义的ViewHolder，持有每个Item的的所有界面元素
         */
        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView iv;
            public TextView tv_title;
            public TextView tv_time;
            public RelativeLayout relativeLayout;

            public ViewHolder(View itemView) {
                super(itemView);
                //适配器构造时只会用到实体类的get方法，用以获取相应的属性
                iv = (ImageView) itemView.findViewById(R.id.iv_item_pic);
                tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
                tv_time = (TextView) itemView.findViewById(R.id.tv_item_details);
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_kky_infos_pic_content);


                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Linster.textItemOnClick(v, getPosition());
                    }
                });
            }

        }

        public ItemOnClickLinster Linster;

        public void setLinster(ItemOnClickLinster linster) {
            Linster = linster;
        }

        public interface ItemOnClickLinster {
            void textItemOnClick(View view, int position);
        }

    }

}
