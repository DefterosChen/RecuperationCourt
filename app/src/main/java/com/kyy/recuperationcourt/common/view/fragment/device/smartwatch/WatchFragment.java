package com.kyy.recuperationcourt.common.view.fragment.device.smartwatch;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import butterknife.BindView;


@Page(name = "智能手表-主页", anim = CoreAnim.none)
public class WatchFragment extends MyBaseFragment {


    @BindView(R.id.iv_back)
    ImageView iv_back;


    @BindView(R.id.rd_my_device_1)
    RadioButton rb_mydevice_1;
    @BindView(R.id.rd_my_device_2)
    RadioButton rb_mydevice_2;
    @BindView(R.id.rd_my_device_3)
    RadioButton rb_mydevice_3;
    @BindView(R.id.rd_my_device_4)
    RadioButton rb_mydevice_4;


    @BindView(R.id.include_step)
    View sblayout_stepnum;
    @BindView(R.id.include_heartrate)
    View sblayout_heartrate;
    @BindView(R.id.include_sleep)
    View sblayout_sleep;
    @BindView(R.id.include_bloodpressure)
    View sblayout_bloodpressure;


    public WatchFragment() {
        // Required empty public constructor
    }


    public static WatchFragment newInstance() {
        WatchFragment fragment = new WatchFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_watch;
    }

    @Override
    protected void initViews() {
        editButton();
        editLayout();
    }

    private void editLayout() {
        ImageView iv1 = sblayout_stepnum.findViewById(R.id.imageView_title);
        TextView tv1 = sblayout_stepnum.findViewById(R.id.textView_title);
        iv1.setImageResource(R.drawable.icon_kky_stepnum);
        tv1.setText(R.string.kky_device_watch_stepnum);


        ImageView iv2 = sblayout_heartrate.findViewById(R.id.imageView_title);
        TextView tv2 = sblayout_heartrate.findViewById(R.id.textView_title);
        iv2.setImageResource(R.drawable.icon_gray_heartbeat);
        tv2.setText(R.string.kky_device_watch_heartrate);

        ImageView iv3 = sblayout_sleep.findViewById(R.id.imageView_title);
        TextView tv3 = sblayout_sleep.findViewById(R.id.textView_title);
        iv3.setImageResource(R.drawable.icon_kky_sleeping);
        tv3.setText(R.string.kky_device_watch_sleep);

        ImageView iv4 = sblayout_bloodpressure.findViewById(R.id.imageView_title);
        TextView tv4 = sblayout_bloodpressure.findViewById(R.id.textView_title);
        iv4.setImageResource(R.drawable.icon_kky_bloodpressure);
        tv4.setText(R.string.kky_device_watch_bloodpressure);

    }


    private void editButton() {
        RadioButton[] rbs = new RadioButton[4];
        rbs[0] = rb_mydevice_1;
        rbs[1] = rb_mydevice_2;
        rbs[2] = rb_mydevice_3;
        rbs[3] = rb_mydevice_4;




        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drawables = rb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 1 / 3, drawables[1].getMinimumHeight() * 1 / 3);
            //定义一个Rect边界
            drawables[1].setBounds(r);
//            //给指定的radiobutton设置drawable边界
//            if (rb.getId() == R.id.rd_my_device_more) {
//                r = new Rect(0, 0, drawables[1].getMinimumWidth()*1/2, drawables[1].getMinimumHeight());
//                drawables[1].setBounds(r);
//            }

            //添加限制给控件
            rb.setCompoundDrawables(null, drawables[1], null, null);
        }
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