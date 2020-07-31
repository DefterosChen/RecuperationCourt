package com.kyy.recuperationcourt.common.view.fragment.device;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.other.widget.imageview.viewpager.VerticalViewPager;
import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.device.smartwatch.MainWatchFragment;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.flowlayout.FlowLayout;
import com.xuexiang.xui.widget.tabbar.VerticalTabLayout;
import com.xuexiang.xui.widget.tabbar.vertical.TabAdapter;
import com.xuexiang.xui.widget.tabbar.vertical.TabView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 *  CXA
 *
 *  2019/12/03
 */
@Page(name = "设备管理", anim = CoreAnim.none)
public class ManageDevicesFragment extends MyBaseFragment implements TabLayout.OnTabSelectedListener, SmartViewHolder.OnItemClickListener {

    @BindView(R.id.tablayout_alladvice)
    VerticalTabLayout mTabLayout;
    @BindView(R.id.viewpager_all_advice)
    VerticalViewPager mViewPager;

    @BindView(R.id.iv_back)
    ImageView iv_back;

    /**
     * 悬停标题栏
     */
    private void initTablayout() {

        initTab0();
//        for (String page : DevicePage.getPageNames()) {
//            mTabLayout.addTab(mTabLayout.newTab().setText(page));
//        }
//        mTabLayout.setTabMode(MODE_SCROLLABLE);
//        mTabLayout.addOnTabSelectedListener(this);
//
//        mViewPager.setAdapter(mPagerAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initTab0() {
        mViewPager.setAdapter(new MyPagerAdapter());
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                ToastUtil.showTips("选中 " + tab.getTitle().getContent());
            }

            @Override
            public void onTabUnselected(final TabView tab, int position) {
//                XUtil.getMainHandler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        XToastUtils.toast("选择取消 " + tab.getTitle().getContent());
//                    }
//                }, 500);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
//                XToastUtils.toast("重复选择 " + tab.getTitle().getContent());
            }
        });
    }



    public ManageDevicesFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_devices;
    }

    @Override
    protected void initViews() {
        //编辑tablayout
        initTablayout();
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

    @Override
    public void onItemClick(View itemView, int position) {

    }


    private class MyPagerAdapter extends PagerAdapter implements TabAdapter {
        List<String> titles;


        private LayoutInflater layoutInflater = LayoutInflater.from(getActivity());


        public MyPagerAdapter() {
            titles = new ArrayList<>();
            Collections.addAll(titles, "全部设备", "我的设备", "最近使用", "健康监测", "口腔健康", "追梦睡眠", "爱心母婴",
                    "活力运动", "养生保健");
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public TabView.TabBadge getBadge(int position) {
//            if (position == 5) {
//                return new TabView.TabBadge.Builder().setBadgeNumber(666)
//                        .setExactMode(true)
//                        .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                            @Override
//                            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//                            }
//                        }).build();

//            }
            return null;
        }

        @Override
        public TabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public TabView.TabTitle getTitle(int position) {
            return new TabView.TabTitle.Builder()
                    .setContent(titles.get(position))
                    .setTextColor(R.color.icon_blue, R.color.black)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            MyPagerAdapter.ViewHolder holder = null;
            View view = layoutInflater.inflate(R.layout.item_kky_flowtaglayout, container, false);
            holder = new MyPagerAdapter.ViewHolder();
            holder.tv_title = view.findViewById(R.id.tv_item_title);
            holder.flowLayout = view.findViewById(R.id.fl_layout);
            holder.linearLayout1 = view.findViewById(R.id.device_1);
            holder.linearLayout2 = view.findViewById(R.id.device_2);
            holder.linearLayout3 = view.findViewById(R.id.device_3);
            holder.linearLayout4 = view.findViewById(R.id.device_4);

            holder.tv_title.setText(titles.get(position));
            holder.linearLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPage(MainWatchFragment.class);
                }
            });
            holder.linearLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPage(DeviceBindFragment.class);
                }
            });
            holder.linearLayout3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPage(DeviceBindFragment.class);
                }
            });
            holder.linearLayout4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPage(DeviceBindFragment.class);
                }
            });

            container.addView(view);
            return view;

//            TextView textView = new TextView(getApplicationContext());
////            textView.setTextAppearance(getApplicationContext(), R.style.TextStyle_Content_Match);
//            textView.setGravity(Gravity.CENTER);
//            textView.setText(String.format("这个是%s页面的内容", titles.get(position)));
//            container.addView(textView);
        }


        private final class ViewHolder {
            TextView tv_title;
            FlowLayout flowLayout;
            LinearLayout linearLayout1;
            LinearLayout linearLayout2;
            LinearLayout linearLayout3;
            LinearLayout linearLayout4;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
