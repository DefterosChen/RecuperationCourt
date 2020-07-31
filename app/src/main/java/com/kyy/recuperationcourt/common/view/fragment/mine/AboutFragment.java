package com.kyy.recuperationcourt.common.view.fragment.mine;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;
import com.xuexiang.xutil.app.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;

/**
 *  CXA
 *
 *  2019/12/03
 */
@Page(name = "联系我们", anim = CoreAnim.none)
public class AboutFragment extends MyBaseFragment {

    @BindView(R.id.version)
    TextView mVersionTextView;
    @BindView(R.id.about_list)
    XUIGroupListView mAboutGroupListView;
    @BindView(R.id.copyright)
    TextView mCopyrightTextView;

    @BindView(R.id.iv_back)
    ImageView iv_back;
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initViews() {

        mVersionTextView.setText(String.format("康养苑：%s", AppUtils.getAppVersionName()));

        XUIGroupListView.newSection(getActivity())
                .addItemView(mAboutGroupListView.createItemView(getResources().getString(R.string.kky_about_customer_service_phone)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .addItemView(mAboutGroupListView.createItemView(getResources().getString(R.string.kky_about_enterprise_wechat)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .addItemView(mAboutGroupListView.createItemView(getResources().getString(R.string.kky_about_enterprise_email)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .addItemView(mAboutGroupListView.createItemView(getResources().getString(R.string.kky_about_customer_service_online)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .addTo(mAboutGroupListView);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.CHINA);
        String currentYear = dateFormat.format(new Date());
        mCopyrightTextView.setText(String.format(getResources().getString(R.string.kky_about_copyright), currentYear));

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
