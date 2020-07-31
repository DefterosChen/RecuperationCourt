package com.kyy.recuperationcourt.common.view.fragment.mine.setting;


import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.edittext.PasswordEditText;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@Page(name = "修改密码", anim = CoreAnim.none)
public class PasswordChangingFragment extends MyBaseFragment {

    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.et_password_reset_password1)
    PasswordEditText stv_pw1;
    @BindView(R.id.et_password_reset_password2)
    PasswordEditText stv_pw2;

    public PasswordChangingFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_password_changing;
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
