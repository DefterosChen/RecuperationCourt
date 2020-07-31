package com.kyy.recuperationcourt.common.view.base;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.core.graphics.ColorUtils;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.other.wheel.immerses.StatusBarUtil;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xui.utils.StatusBarUtils;


/**
 * 基础activity
 * <p>
 * By DefterosChen
 */
public class MyBaseActivity extends XPageActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.translucent(this);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);


        //状态栏着色
        Window window = getWindow();
        int color = getResources().getColor(R.color.white);
        window.setStatusBarColor(color);
        if (ColorUtils.calculateLuminance(color) >= 0.5) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

//        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
//        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
//        }

    }


}
