package com.kyy.recuperationcourt.common.util;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.xuexiang.xutil.app.ActivityLifecycleHelper;
import com.xuexiang.xutil.common.logger.Logger;


/**
 * <pre>
 *     desc   : 全局工具管理
 *     author : xuexiang
 *     time   : 2018/4/27 下午8:38
 * </pre>
 */
public class DefChenUtil {

    private static Context sContext;
    private static DefChenUtil sInstance;

    private ActivityLifecycleHelper mActivityLifecycleHelper;
    /**
     * 主线程Handler
     */
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());

    private DefChenUtil() {
        mActivityLifecycleHelper = new ActivityLifecycleHelper();
    }

    /**
     * 初始化工具
     * @param application
     */
    public static void init(Application application) {
        sContext = application.getApplicationContext();
        application.registerActivityLifecycleCallbacks(DefChenUtil.get().getActivityLifecycleHelper());
    }

    /**
     * 初始化工具
     * @param context
     */
    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    /**
     * 注册activity的生命回调
     * @param application
     * @param lifecycleHelper
     * @return
     */
    public DefChenUtil registerLifecycleCallbacks(Application application, ActivityLifecycleHelper lifecycleHelper) {
        mActivityLifecycleHelper = lifecycleHelper;
        application.registerActivityLifecycleCallbacks(mActivityLifecycleHelper);
        return this;
    }

    /**
     * 获取全局上下文
     * @return
     */
    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 CxaUtil.init() 初始化！");
        }
    }

    /**
     * 设置日志记录
     */
    public static void debug(boolean isDebug) {
        if (isDebug) {
            debug(Logger.DEFAULT_LOG_TAG);
        } else {
            debug("");
        }
    }

    /**
     * 设置日志记录
     * @param tag
     */
    public static void debug(String tag) {
        Logger.debug(tag);
    }

    /**
     * 获取主线程的Handler
     * @return
     */
    public static Handler getMainHandler() {
        return sMainHandler;
    }

    /**
     * 在主线程中执行
     * @param runnable
     * @return
     */
    public static boolean runOnUiThread(Runnable runnable) {
        return getMainHandler().post(runnable);
    }


    /**
     * 获取实例
     * @return
     */
    public static DefChenUtil get() {
        if (sInstance == null) {
            synchronized (DefChenUtil.class) {
                if (sInstance == null) {
                    sInstance = new DefChenUtil();
                }
            }
        }
        return sInstance;
    }

    /**
     * 退出程序
     */
    public void exitApp() {
        if (mActivityLifecycleHelper != null) {
            mActivityLifecycleHelper.exit();
        }
        ServiceUtils.stopAllRunningService(getContext());
        ProcessUtils.killBackgroundProcesses(getContext().getPackageName());
        System.exit(0);
    }


}
