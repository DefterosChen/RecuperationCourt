package com.kyy.recuperationcourt.common.view.fragment.device.smartwatch;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.other.wheel.view.CustomViewPager;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xutil.common.CollectionUtils;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@Page(name = "智能手表", anim = CoreAnim.none)
public class MainWatchFragment extends MyBaseFragment implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.view_pager)
    CustomViewPager viewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    public MainWatchFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_watch;
    }

    //    int[] menuItemIds = new int[]{R.id.item_dashboard, R.id.item_photo, R.id.item_music, R.id.item_movie};
    String[] titles = new String[]{"主页", "健康", "定位", "我的", "发现"};

    @Override
    protected void initViews() {
        FragmentAdapter<XPageFragment> adapter = new FragmentAdapter<>(getChildFragmentManager());
        adapter.addFragment(WatchFragment.newInstance(), null);
        adapter.addFragment(DataWatchFragment.newInstance(), null);
        adapter.addFragment(MapWatchFragment.newInstance(), null);
        adapter.addFragment(WatchFragment.newInstance(), null);
        adapter.addFragment(WatchFragment.newInstance(), null);


//        viewPager.setOffscreenPageLimit(titles.length - 1);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initListeners() {
        viewPager.addOnPageChangeListener(this);
        viewPager.setScanScroll(false);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(titles, menuItem.getTitle());

        switch (index) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                viewPager.setCurrentItem(index, true);
                return true;
            default:
                return false;


        }
    }


}
