package com.kyy.recuperationcourt.common.view.fragment;


import android.view.View;
import android.widget.ImageView;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import butterknife.BindView;


@Page(name = "H5页面框", anim = CoreAnim.none)
public class WebPageFragment extends MyBaseFragment {


    @BindView(R.id.iv_back)
    ImageView iv_back;

    public WebPageFragment() {
        // Required empty public constructor
    }

    public static WebPageFragment newInstance() {
        WebPageFragment fragment = new WebPageFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_web_page;
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
