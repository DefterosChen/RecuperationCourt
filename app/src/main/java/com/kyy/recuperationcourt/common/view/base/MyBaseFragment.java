package com.kyy.recuperationcourt.common.view.base;


import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import androidx.fragment.app.Fragment;

import com.kyy.recuperationcourt.R;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.TitleBar;

/**
 * A simple {@link Fragment} subclass.
 */
@Page(name = "基础Fragment", anim = CoreAnim.none)
public class MyBaseFragment extends XPageFragment {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public final Object mHandlerToken = hashCode();
//    @BindView(R.id.iv_back)
//    ImageView iv_back;

    public MyBaseFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_base;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
//        iv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popToBack();
//            }
//        });
    }



    /**
     * 去掉标题栏
     * @return
     */
    @Override
    protected TitleBar initTitleBar() {
        return null;
    }


    /**
     * 延迟执行
     */
    public final boolean post(Runnable r) {
        return postDelayed(r, 0);
    }

    /**
     * 延迟一段时间执行
     */
    public final boolean postDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * 在指定的时间执行
     */
    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        // 发送和这个 Activity 相关的消息回调
        return HANDLER.postAtTime(r, mHandlerToken, uptimeMillis);
    }

}
