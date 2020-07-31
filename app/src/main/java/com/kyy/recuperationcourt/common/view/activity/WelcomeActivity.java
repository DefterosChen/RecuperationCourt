package com.kyy.recuperationcourt.common.view.activity;

import android.view.KeyEvent;

import com.kyy.recuperationcourt.R;
import com.xuexiang.xui.utils.KeyboardUtils;
import com.xuexiang.xui.widget.activity.BaseSplashActivity;
import com.xuexiang.xutil.app.ActivityUtils;

public class WelcomeActivity extends BaseSplashActivity {

    public final static String KEY_IS_DISPLAY = "key_is_display";
    public final static String KEY_ENABLE_ALPHA_ANIM = "key_enable_alpha_anim";

    private boolean isDisplay = false;


    @Override
    protected long getSplashDurationMillis() {
        return 500;
    }


    @Override
    protected void onCreateActivity() {
        isDisplay = getIntent().getBooleanExtra(KEY_IS_DISPLAY, isDisplay);
        boolean enableAlphaAnim = getIntent().getBooleanExtra(KEY_ENABLE_ALPHA_ANIM, false);
//        SettingSPUtils spUtil = SettingSPUtils.getInstance();
//        if (spUtil.isFirstOpen()) {
//            spUtil.setIsFirstOpen(false);
//            ActivityUtils.startActivity(UserGuideActivity.class);
//            finish();
//
//        }  else {



        //覆盖在背景上的画布
            if (enableAlphaAnim) {
                initSplashView(R.drawable.logo_kky);
            } else {
                initSplashView(R.drawable.logo_kky);
            }
            startSplash(enableAlphaAnim);
        }
//    }

    @Override
    protected void onSplashFinished() {
        if (!isDisplay) {
            ActivityUtils.startActivity(MainActivity.class);
        }
        finish();
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }



}

