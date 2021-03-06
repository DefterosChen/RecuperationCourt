package com.kyy.recuperationcourt.common.view.fragment.mine;


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
@Page(name = "我的积分", anim = CoreAnim.none)
public class MyCreditFragment extends MyBaseFragment {

    @BindView(R.id.iv_back)
    ImageView iv_back;

    public MyCreditFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_credit;
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
