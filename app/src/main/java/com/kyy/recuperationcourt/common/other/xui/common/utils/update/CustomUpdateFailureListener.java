package com.kyy.recuperationcourt.common.other.xui.common.utils.update;

import com.kyy.recuperationcourt.common.util.ToastUtil;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;

/**
 * 自定义版本更新提示
 *
 * @author xuexiang
 * @since 2019/4/15 上午12:01
 */
public class CustomUpdateFailureListener implements OnUpdateFailureListener {

    /**
     * 是否需要错误提示
     */
    private boolean mNeedErrorTip;

    public CustomUpdateFailureListener() {
        this(true);
    }

    public CustomUpdateFailureListener(boolean needErrorTip) {
        mNeedErrorTip = needErrorTip;
    }

    /**
     * 更新失败
     *
     * @param error 错误
     */
    @Override
    public void onFailure(UpdateError error) {
        if (mNeedErrorTip) {
            ToastUtil.showTips(error.toString());
        }
    }
}
