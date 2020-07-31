package com.kyy.recuperationcourt.common.view.fragment.consult;


import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.view.base.MyBaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;

import butterknife.BindView;


@Page(name = "新闻详情", anim = CoreAnim.none, params = {NewsDetailFragment.TITLE, NewsDetailFragment.DES, NewsDetailFragment.CONTENT, NewsDetailFragment.AUTHOR, NewsDetailFragment.TIME, NewsDetailFragment.PIC})
public class NewsDetailFragment extends MyBaseFragment {


    public final static String TITLE = "title";
    public final static String DES = "des";
    public final static String CONTENT = "content";
    public final static String AUTHOR = "author";
    public final static String TIME = "time";
    public final static String PIC = "pic";

    @AutoWired(name = TITLE)
    String str_title;
    @AutoWired(name = DES)
    String str_des;
    @AutoWired(name = CONTENT)
    String str_content;
    @AutoWired(name = AUTHOR)
    String str_author;
    @AutoWired(name = TIME)
    String str_time;
    @AutoWired(name = PIC)
    String str_pic;

    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.news_title)
    TextView tv_title;
    @BindView(R.id.news_author)
    TextView tv_author;
    @BindView(R.id.news_time)
    TextView tv_time;
    @BindView(R.id.news_des)
    TextView tv_des;
    @BindView(R.id.news_content)
    TextView tv_content;
    @BindView(R.id.new_pic)
    ImageView imageView;


    public NewsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @Override
    protected void initViews() {

        tv_title.setText(str_title);
        tv_author.setText(str_author);
        tv_time.setText(str_time);
        tv_des.setText(str_des);

        String str = Html.fromHtml(str_content).toString();
        tv_content.setText(str);

        //图片加载框架
        Glide.with(getActivity())
                .load(str_pic)
                .into(imageView);
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
