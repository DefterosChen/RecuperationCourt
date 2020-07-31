package com.kyy.recuperationcourt.common.view.fragment.market;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.other.xui.view.XPageWebViewFragment;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

/**
 *  CXA
 *
 *  2019/12/03
 */
@Page(name = "商城", anim = CoreAnim.none)
public class ShopFragment  extends MyBaseFragment {


    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        return new ShopFragment();


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        XPageWebViewFragment.openUrl(this, "https://shop18910112.youzan.com");
    }




}