package com.kyy.recuperationcourt.common.other.xui.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.entity.KkyItem;
import com.kyy.recuperationcourt.common.other.xui.DemoDataProvider;
import com.kyy.recuperationcourt.common.other.xui.common.adapter.WidgetItemAdapter;
import com.kyy.recuperationcourt.common.view.fragment.device.DeviceBindFragment;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.WidgetUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class TableActivity extends XPageActivity {



//    @BindView(R.id.vp_home_pager)
//    ViewPager mViewPager;
//    @BindView(R.id.bv_home_navigation)
//    BottomNavigationView mBottomNavigationView;


    /**
     * ViewPager 适配器
     */
//    private BaseFragmentAdapter<MyLazyFragment> mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        PageOption.to(MainFragment.class).open(this);


//        initView();
//        initData();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_table;
    }




//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_home:
//                mViewPager.setCurrentItem(0);
//                return true;
//            case R.id.menu_data:
//                mViewPager.setCurrentItem(1);
//                return true;
//            case R.id.menu_shop:
////                startActivity(new Intent(getApplicationContext(), MarketActivity.class));
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://shop18910112.youzan.com"));
//                startActivity(intent);
////                mViewPager.setCurrentItem(2);
//                return true;
//            case R.id.menu_mine:
//                mViewPager.setCurrentItem(3);
//                return true;
//            case R.id.menu_add:
////                startActivity(new Intent(getApplicationContext(), ManageDevicesActivity.class));
//
//                showBottomSheetListDialog(false);
//
//                return true;
//            default:
//                break;
//        }
//        return false;
//    }


    private void showBottomSheetListDialog(boolean isList) {
        BottomSheetDialog dialog = new BottomSheetDialog(TableActivity.this);
        View view = LayoutInflater.from(TableActivity.this).inflate(R.layout.dialog_bottom_sheet, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);


        initDialogGrid(recyclerView);


        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void initDialogGrid(RecyclerView recyclerView) {
        WidgetUtils.initGridRecyclerView(recyclerView, 3, DensityUtils.dp2px(2));

        WidgetItemAdapter widgetItemAdapter = new WidgetItemAdapter(DemoDataProvider.getDeviceNewInfos());
        recyclerView.setAdapter(widgetItemAdapter);

        widgetItemAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<KkyItem>() {
            @Override
            public void onItemClick(View itemView, KkyItem item, int position) {
                openPage(DeviceBindFragment.class);
            }
        });
    }

    /**
     * 进行排序
     *
     * @param pageInfoList
     * @return
     */
    private List<PageInfo> sortPageInfo(List<PageInfo> pageInfoList) {
        Collections.sort(pageInfoList, new Comparator<PageInfo>() {
            @Override
            public int compare(PageInfo o1, PageInfo o2) {
                return o1.getClassPath().compareTo(o2.getClassPath());
            }
        });
        return pageInfoList;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // 回调当前 Fragment 的 onKeyDown 方法
//        if (mPagerAdapter.getCurrentFragment().onKeyDown(keyCode, event)) {
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

    }


//    @Override
//    protected void onDestroy() {
////        mViewPager.removeOnPageChangeListener(this);
////        mViewPager.setAdapter(null);
//        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
//        super.onDestroy();
//    }


}