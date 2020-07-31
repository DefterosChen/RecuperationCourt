package com.kyy.recuperationcourt.common.other.widget.click;


import android.view.View;

import com.kyy.recuperationcourt.common.util.ToastUtil;

import java.util.Calendar;

/**
 *
 * 防止多次点击的单击事件
 */
public abstract class NoMoreClickListener implements View.OnClickListener {
    private int MIN_CLICK_DELAY_TIME = 2500;//多少秒点击一次 默认2.5秒
    private long lastClickTime = 0;

    public NoMoreClickListener() {
    }

    /**
     * 设置多少秒之内
     *
     * @param time
     */
    public NoMoreClickListener(int time) {
        this.MIN_CLICK_DELAY_TIME = time;
    }

    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            OnMoreClick(view);
        } else {
            ToastUtil.showTips("点击太快，请稍后...");
            OnMoreErrorClick();
        }
    }

    /**
     * 在N秒之内的 ==1 次点击回调次方法
     *
     * @param view
     */
    protected abstract void OnMoreClick(View view);

    /**
     * 在N秒之内的 >= 2次点击回调次方法
     */
    protected abstract void OnMoreErrorClick();
}
