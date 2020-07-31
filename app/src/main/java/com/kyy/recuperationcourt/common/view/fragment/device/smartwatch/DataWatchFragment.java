package com.kyy.recuperationcourt.common.view.fragment.device.smartwatch;


import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@Page(name = "智能手表-健康", anim = CoreAnim.none)
public class DataWatchFragment extends MyBaseFragment {

    @BindView(R.id.iv_back)
    ImageView iv_back;

    public DataWatchFragment() {
        // Required empty public constructor
    }

    public static DataWatchFragment newInstance() {
        DataWatchFragment fragment = new DataWatchFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data_watch;
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
