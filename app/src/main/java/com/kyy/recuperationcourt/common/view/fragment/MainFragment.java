package com.kyy.recuperationcourt.common.view.fragment;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.entity.KkyItem;
import com.kyy.recuperationcourt.common.other.wheel.view.CustomViewPager;
import com.kyy.recuperationcourt.common.other.xui.DemoDataProvider;
import com.kyy.recuperationcourt.common.other.xui.common.adapter.WidgetItemAdapter;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.kyy.recuperationcourt.common.view.fragment.device.DeviceBindFragment;
import com.kyy.recuperationcourt.common.view.fragment.device.smartwatch.MainWatchFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xutil.common.CollectionUtils;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */

@Page(name = "主界面", anim = CoreAnim.none)
public class MainFragment extends MyBaseFragment implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    //    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }


    //    int[] menuItemIds = new int[]{R.id.item_dashboard, R.id.item_photo, R.id.item_music, R.id.item_movie};
    String[] titles = new String[]{ "社交", "数据", "首页","咨询", "我的"};

    @Override
    protected void initViews() {
        FragmentAdapter<XPageFragment> adapter = new FragmentAdapter<>(getChildFragmentManager());
        adapter.addFragment(SocialFragment.newInstance(), null);    //DataFragment
        adapter.addFragment(DataDeviceFragment.newInstance(), null);  //null
        adapter.addFragment(HomeFragment.newInstance(), null);
        adapter.addFragment(ConsultFragment.newInstance(), null);   //null
        adapter.addFragment(MineFragment.newInstance(), null);


//        viewPager.setOffscreenPageLimit(titles.length - 1);
        viewPager.setAdapter(adapter);

    }




    @SuppressLint("ResourceType")
    @Override
    protected void initListeners() {
        viewPager.addOnPageChangeListener(this);
        viewPager.setScanScroll(false);

        //默认显示 首页
        viewPager.setCurrentItem(2, true);

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
//            case 2:
//                showBottomSheetListDialog(false);   //设备用
//                return true;
//            case 3:
//                Utils.goWeb(getContext(), "https://shop18910112.youzan.com"); //商城用
//                return true;
            default:
                return false;

        }
    }


    private void showBottomSheetListDialog(boolean isList) {
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bottom_sheet, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        initDialogGrid(recyclerView, dialog);

    }

    /**
     *  弹出底部选择框
     * @param recyclerView
     * @param dialog
     */
    private void initDialogGrid(RecyclerView recyclerView, BottomSheetDialog dialog) {
        WidgetUtils.initGridRecyclerView(recyclerView, 3, DensityUtils.dp2px(2));

        WidgetItemAdapter widgetItemAdapter = new WidgetItemAdapter(DemoDataProvider.getDeviceNewInfos());
        recyclerView.setAdapter(widgetItemAdapter);

        widgetItemAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<KkyItem>() {
            @Override
            public void onItemClick(View itemView, KkyItem item, int position) {
                if(DemoDataProvider.getDeviceNewInfos().get(position).getTitle().equals("健康智能手环") ){
                    openPage(MainWatchFragment.class);
                    dialog.dismiss();
                    return;
                }
                openPage(DeviceBindFragment.class);
                dialog.dismiss();
            }
        });
    }

}
