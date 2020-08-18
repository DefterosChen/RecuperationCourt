

package com.kyy.recuperationcourt.common.util;


import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.kyy.recuperationcourt.common.other.wheel.waterfall.base.config.GlobalConfig;


public class ToastUtil {
    public static void showTips(String tips) {
        Toast.makeText(GlobalConfig.getAppContext(), tips, Toast.LENGTH_SHORT).show();
    }

    public static void showTips(@StringRes int resID) {
        Toast.makeText(GlobalConfig.getAppContext(), ResUtil.getStr(resID), Toast.LENGTH_SHORT).show();
    }

    public static void showMsg(final String msg) {
        if (GlobalConfig.getAppContext() != null) {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(GlobalConfig.getAppContext(), msg, Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();
        }
    }

}
