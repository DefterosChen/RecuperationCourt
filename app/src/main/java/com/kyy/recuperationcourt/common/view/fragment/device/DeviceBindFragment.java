package com.kyy.recuperationcourt.common.view.fragment.device;


import android.view.View;
import android.widget.ImageView;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import butterknife.BindView;

/**
 *  CXA
 *
 *  2019/12/03
 */
@Page(name = "设备绑定", anim = CoreAnim.none)
public class DeviceBindFragment extends MyBaseFragment {

    @BindView(R.id.iv_back)
    ImageView iv_back;


    public DeviceBindFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device_bind;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });
    }



}
