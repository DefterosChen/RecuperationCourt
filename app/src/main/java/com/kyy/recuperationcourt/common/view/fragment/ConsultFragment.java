package com.kyy.recuperationcourt.common.view.fragment;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.base.dao.SysDicDao;
import com.kyy.recuperationcourt.common.model.constant.Consts;
import com.kyy.recuperationcourt.common.model.constant.MultiPage;
import com.kyy.recuperationcourt.common.model.entity.consult.NewsType;
import com.kyy.recuperationcourt.common.other.wheel.adapter.MyRecyclerViewAdapter;
import com.kyy.recuperationcourt.common.other.widget.layout.ItemInfoLinearLayout;
import com.kyy.recuperationcourt.common.other.xui.DemoDataProvider;
import com.kyy.recuperationcourt.common.other.xui.common.utils.Utils;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.consult.NewsFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartRecyclerAdapter;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

@Page(name = "咨询", anim = CoreAnim.none)
public class ConsultFragment extends MyBaseFragment implements TabLayout.OnTabSelectedListener
        , SmartViewHolder.OnItemClickListener {

    @BindView(R.id.iill_servicehealth)
    ItemInfoLinearLayout iill_service_health;
    private MyRecyclerViewAdapter myAdapterService;

    @BindView(R.id.tablayout_healthadvice)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_health_advice)
    ViewPager mViewPager;

    ArrayList<String> listTitleCode = new ArrayList<>();
    ArrayList<String> listTitle = new ArrayList<>();

    FragmentPagerAdapter mPagerAdapter;

    //当前所在的fragment的值，默认为0
    public static int DEFAULTFRAGMENT = 0;

    //每一个头部item所对应一个item
    static List<NewsFragment> list_fragment_news = new ArrayList<>();


    public ConsultFragment() {
        // Required empty public constructor
    }

    public static ConsultFragment newInstance() {
        ConsultFragment fragment = new ConsultFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_consult;
    }

    @Override
    protected void initViews() {

        System.out.println("咨询界面信息查看");
        iill_service_health.setAdapter(myAdapterService = new MyRecyclerViewAdapter(DemoDataProvider.urls_6, Consts.ITEM_TYPE_ADAPTER_TDPT));
        iill_service_health.setOrientation(LinearLayoutManager.HORIZONTAL);


        //编辑tablayout（假数据，待删除）    //待接口完成后删除 2020/05/23
        initTablayout();


//        //从数据库中获取标题列表
//        getTitleList();
//
//        //初始化数据
//        initData();
//
//
//        //通过适配器加载
//        mViewPager.setAdapter(mPagerAdapter);
//        //设置viewpager的缓存个数，全部页面都缓存
//        mViewPager.setOffscreenPageLimit(6);
//
//        mTabLayout.setupWithViewPager(mViewPager);
//
//        //设置tablayout的属性
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    @Override
    protected void initListeners() {
        /**
         * 健康服务
         */
        myAdapterService.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, String item, int position) {
                Utils.goWeb(getContext(), "https://m.rxys.com/");
            }
        });


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                DEFAULTFRAGMENT = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * 获取新闻标题类别数据
     */
    private void getTitleList() {
        List<NewsType> list = new SysDicDao().queryNewsDataList();

        listTitleCode = new ArrayList<>();
        listTitle = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            String titleName = list.get(i).getName();
            listTitle.add(titleName);
            listTitleCode.add(list.get(i).getId());

            mTabLayout.addTab(mTabLayout.newTab().setText(titleName), i);
        }

        mTabLayout.setTabMode(MODE_SCROLLABLE);

    }

    /**
     * 初始化数据
     */
    private void initData() {

        //循环加载titles数组内容到fragment（每一个tablayout的显示界面都是一个frag）
        for (String title : listTitleCode) {
            //通过frag对象加载title，并完成实例化
            NewsFragment instance = NewsFragment.getInstance(title);
            list_fragment_news.add(instance);
        }


        //用适配器完成每一个fragment的加载完成
        mPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //返回指定的fragment
                return list_fragment_news.get(position);
            }

            @Override
            public int getCount() {
                //fragment的个数
                return listTitleCode.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                //将titles的内容加载进每一个（position为指定的frag）
                return listTitle.get(position);
            }
        };

    }












///////////////////////////////////////////////////

    //待删除 2020/05/23

    /**
     * 悬停标题栏
     */
    private void initTablayout() {
        for (String page : MultiPage.getPageNames()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(page));
        }
        mTabLayout.setTabMode(MODE_SCROLLABLE);
        mTabLayout.addOnTabSelectedListener(this);

        mViewPager.setAdapter(mPagerAdapter1);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    private PagerAdapter mPagerAdapter1 = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return MultiPage.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            MultiPage page = MultiPage.getPage(position);
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
            return MultiPage.getPageNames()[position];
        }
    };

    private View getPageView(MultiPage page) {


        View view = getLayoutInflater().inflate(R.layout.fragment_refresh_status_layout, mViewPager, false);
        RecyclerView mRecyclerView;
        SmartRefreshLayout mRefreshLayout;

        //初始化
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);



        if (mRecyclerView instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) mRecyclerView;
            WidgetUtils.initRecyclerView(recyclerView);

            SmartRecyclerAdapter sadapter = new SmartRecyclerAdapter(Arrays.asList(Item.values()), R.layout.item_kky_infos_pic, this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Object model, int position) {
                    holder.text(R.id.tv_item_title, DemoDataProvider.getAdviceItemList().get(position).getTitle());
                    holder.text(R.id.tv_item_details, DemoDataProvider.getAdviceItemList().get(position).getTime());
                    holder.image(R.id.iv_item_pic, R.drawable.icon_pro_blue_nophoto);
                }


            };


            recyclerView.setAdapter(sadapter);

            sadapter.setOnItemClickListener(new SmartViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
//                    startActivity(new Intent(getActivity(), WebPageActivity.class));
                }
            });

            //触发自动刷新
            mRefreshLayout.autoRefresh();
        }
        return view;
    }

    // 健康资讯   模拟数据//
    private enum Item {
        尺寸拉伸(R.string.srl_footer_pulling),
        位置平移(R.string.xui_preview_count_string),
        背后固定(R.string.xui_picker_view_submit),
        显示时间(R.string.xui_float_behavior),
        隐藏时间(R.string.xui_bottom_behavior),
        默认主题(R.string.xui_count_down_finish),
        橙色主题(R.string.xui_met_not_allow_empty),
        红色主题(R.string.item_example_number_abstract),
        绿色主题(R.string.xui_tip_empty_message),
        蓝色主题(R.string.xui_lab_button_cancel),
        蓝色的题(R.string.xui_lab_button_cancel);
        //        加载更多(R.string.item_style_load_more);
        public int nameId;

        Item(@StringRes int nameId) {
            this.nameId = nameId;
        }
    }

    // 待删除 2020/05/23
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

///////////////////////////////////////////////////

}
