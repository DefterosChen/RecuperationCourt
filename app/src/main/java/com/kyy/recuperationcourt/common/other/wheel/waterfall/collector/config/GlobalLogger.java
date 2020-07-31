package com.kyy.recuperationcourt.common.other.wheel.waterfall.collector.config;

import com.cxa.base.BuildConfig;
import com.horizon.lightkv.LightKV;
import com.horizon.task.base.TaskLogger;
import com.kyy.recuperationcourt.common.util.LogUtil;

import org.jetbrains.annotations.NotNull;

public class GlobalLogger implements TaskLogger, LightKV.Logger {
    private static final  GlobalLogger INSTANCE = new GlobalLogger();
    private GlobalLogger(){
    }

    public static GlobalLogger getInstance(){
        return INSTANCE;
    }
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void e(@NotNull String tag, @NotNull Throwable e) {
        LogUtil.e(tag, e);
    }
}
