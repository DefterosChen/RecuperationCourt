package com.kyy.recuperationcourt.common.other.xui;

import android.net.Uri;

import androidx.viewpager.widget.ViewPager;

import com.kyy.recuperationcourt.MyApplication;
import com.kyy.recuperationcourt.R;
import com.kyy.recuperationcourt.common.model.entity.KkyItem;
import com.kyy.recuperationcourt.common.other.xui.common.entity.NewInfo;
import com.kyy.recuperationcourt.common.other.widget.imageview.preview.ImageViewInfo;
import com.kyy.recuperationcourt.common.other.widget.imageview.preview.NineGridInfo;
import com.kyy.recuperationcourt.common.util.DateTimeUtil;
import com.xuexiang.xaop.annotation.MemoryCache;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.widget.banner.transform.DepthTransformer;
import com.xuexiang.xui.widget.banner.transform.FadeSlideTransformer;
import com.xuexiang.xui.widget.banner.transform.FlowTransformer;
import com.xuexiang.xui.widget.banner.transform.RotateDownTransformer;
import com.xuexiang.xui.widget.banner.transform.RotateUpTransformer;
import com.xuexiang.xui.widget.banner.transform.ZoomOutSlideTransformer;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.imageview.nine.NineGridImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 演示数据
 *
 * @author xuexiang
 * @since 2018/11/23 下午5:52
 */
public class DemoDataProvider {


    public static String[] list_bannerpic = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
    };


    public static String[] titles = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
    };

    public static String[] urls = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
    };


    public static String[] urls_3 = new String[]{
            "1",
            "2",
            "3",

    };

    public static String[] urls_6 = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
    };

    public static String[] urls_7 = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
    };

    public static String[] urls_8 = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
    };

    public static String[] urls_9 = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
    };


    /**
     * 轮播图片资源
     *
     * @return
     */
    public static List<BannerItem> getBannerList() {
        ArrayList<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < getBannerPicList().size(); i++) {
            BannerItem item = new BannerItem();
            Uri uri = Uri.parse("android.resource://" + MyApplication.getContextObject().getPackageName() + "/" + getBannerPicList().get(i));
            item.imgUrl = String.valueOf(uri);
            list.add(item);
        }
        return list;
    }


    public static List<Object> getBannerPicList() {
        List<Object> list = new ArrayList<>();
        list.add(R.drawable.kky_banner_1);
        list.add(R.drawable.kky_banner_2);
        list.add(R.drawable.kky_banner_3);
        list.add(R.drawable.kky_banner_4);
        list.add(R.drawable.kky_banner_5);
        return list;
    }




    public static List<KkyItem> getServiceItemList() {
        ArrayList<KkyItem> list = new ArrayList<>();
        List<Object> listpic = getBannerPicList();
        for (int i = 0; i < service_Itemtitles.length; i++) {
            KkyItem item = new KkyItem();
            item.setTitle(service_Itemtitles[i]);
            item.setDetails(service_Itemdetails[i]);
//            int uri = (int) getServicePicList().get(i);
            item.setUrls_res(service_Itempic[i]);
            list.add(item);
        }

        return list;
    }

    public static String[] service_Itemtitles = new String[]{
            "健康咨询",
            "AI皮肤检测",
            "医生电话咨询",
            "睡眠帮助",
            "呼吸训练降压",
            "更多"
    };

    public static String[] service_Itemdetails = new String[]{
            "提供相关问题解答",
            "解锁您的肌肤状况",
            "全年24小时电话服务",
            "四周养成终生和睡眠",
            "非药物辅助控制高血压",
            "更多详情"
    };

    public static int[] service_Itempic = new int[]{
            R.drawable.kky_service_1,
            R.drawable.kky_service_2,
            R.drawable.kky_service_3,
            R.drawable.kky_service_4,
            R.drawable.kky_service_5,
            R.drawable.kky_service_more,

    };


    public static List<KkyItem> getAssessItemList() {
        ArrayList<KkyItem> list = new ArrayList<>();
        for (int i = 0; i < assess_Itemtitles.length; i++) {
            KkyItem item = new KkyItem();
            item.setTitle(assess_Itemtitles[i]);
            item.setDetails("点击进入");
//            int uri = (int) getServicePicList().get(i);
            item.setUrls_res(assess_Itempic[i]);
            list.add(item);
        }

        return list;
    }


    public static String[] assess_Itemtitles = new String[]{
            "糖尿病评估",
            "高血脂评估",
            "高血压评估",
            "心理评估",
            "血管年龄评估",
            "更多"
    };

    public static int[] assess_Itempic = new int[]{
            R.drawable.kky_assess_item1,
            R.drawable.kky_assess_item2,
            R.drawable.kky_assess_item3,
            R.drawable.kky_assess_item4,
            R.drawable.kky_assess_item5,
            R.drawable.kky_assess_more,
    };

    public static List<KkyItem> getCourseItemList() {
        ArrayList<KkyItem> list = new ArrayList<>();
        List<Object> listpic = getBannerPicList();
        for (int i = 0; i < course_Itemtitles.length; i++) {
            KkyItem item = new KkyItem();
            item.setTitle(course_Itemtitles[i]);
            item.setDetails(course_ItemNum[i]);
//            int uri = (int) getServicePicList().get(i);
            item.setUrls_res(course_Itempic[i]);
            list.add(item);
        }

        return list;
    }


    public static String[] course_Itemtitles = new String[]{
            "孕婴",
            "中医",
            "运动康复",
            "养生",
            "慢性病",
            "心理",
            "更多"
    };

    public static String[] course_ItemNum = new String[]{
            "11门课程",
            "30门课程",
            "49门课程",
            "8门课程",
            "10门课程",
            "19门课程",
            "全部127门课程"
    };

    public static int[] course_Itempic = new int[]{
            R.drawable.kky_course_item1,
            R.drawable.kky_course_item2,
            R.drawable.kky_course_item3,
            R.drawable.kky_course_item4,
            R.drawable.kky_course_item5,
            R.drawable.kky_course_item6,
            R.drawable.kky_course_more
    };


    public static List<KkyItem> getRecommendItemList() {
        ArrayList<KkyItem> list = new ArrayList<>();
        List<Object> listpic = getBannerPicList();
        for (int i = 0; i < recommend_Itemtitles.length; i++) {
            KkyItem item = new KkyItem();
            item.setTitle(recommend_Itemtitles[i]);
            item.setDetails(recommend_ItemDetails[i]);
            item.setPrice(recommend_ItemPrice[i]);
            item.setStatus(recommend_ItemStatus[i]);
//            int uri = (int) getServicePicList().get(i);
            item.setUrls_res(R.drawable.icon_pro_darkblue_person);
            list.add(item);
        }

        return list;
    }


    public static String[] recommend_Itemtitles = new String[]{
            "随时随地，告别心理亚健康",
            "10节课让你真正了解脊柱侧弯的治疗与康复",
            "协和专家的糖尿病应对方案",

    };

    public static String[] recommend_ItemDetails = new String[]{
            "杰克·康菲尔德·临床心理学博士",
            "徐彬·脊椎康复师",
            "刘燕萍·协和医院临床营养科 主任医师",
    };

    public static String[] recommend_ItemPrice = new String[]{
            "40讲/限时免费",
            "10讲/￥68.00",
            "3讲/限时免费",
    };

    public static String[] recommend_ItemStatus = new String[]{
            "1298人加入学习",
            "694人加入学习",
            "1862人加入学习",
    };



    public static List<KkyItem> getAdviceItemList() {
        ArrayList<KkyItem> list = new ArrayList<>();
        List<Object> listpic = getBannerPicList();
        for (int i = 0; i < advice_Itemtitles.length; i++) {
            KkyItem item = new KkyItem();
            item.setTitle(advice_Itemtitles[i]);
            item.setTime(advice_ItemTime[i]);
            item.setUrls_res(R.drawable.icon_blue_default_avatar);
            list.add(item);
        }

        return list;
    }


    public static String[] advice_Itemtitles = new String[]{
            "卫检委：不献血不会影响个人信用，不会有惩戒措施",
            "疲劳，身体健康的隐形杀手，教你几招科学抗疲劳",
            "高以翔不幸逝世！远离猝死最不该忽视的6个身体信号",
            "如何从感冒药的药名辨别成分",
            "隔夜水不致癌，蜂蜜水不通便！关于喝水的谣言帮你一次澄清",
            "为什么尿毒症越来越多？身体如果出现4个征兆，快去检查肾",
            "疲劳，身体健康的隐形杀手，教你几招科学抗疲劳",
            "高以翔不幸逝世！远离猝死最不该忽视的6个身体信号",
            "如何从感冒药的药名辨别成分",
            "卫检委：不献血不会影响个人信用，不会有惩戒措施",
            "为什么尿毒症越来越多？身体如果出现4个征兆，快去检查肾",

    };

    public static String[] advice_ItemTime = new String[]{
            "2019年11月29日",
            "2019年11月29日",
            "2019年11月29日",
            "2019年11月28日",
            "2019年11月28日",
            "2019年11月27日",
            "2019年11月29日",
            "2019年11月29日",
            "2019年11月28日",
            "2019年11月29日",
            "2019年11月29日",
            "2019年11月28日",
            "2019年11月27日",
            "2019年11月29日",
    };



    /**
     * 用于数据页面的信息
     *
     * @return
     */
    @MemoryCache
    public static List<KkyItem> getDataNewInfos() {
        List<KkyItem> list = new ArrayList<>();
        for(int i =0 ;i<11 ;i++){
            KkyItem item = new KkyItem();
            item.setTitle(data_itemtitles[i]);
            item.setTime(data_ItemTime[i]);
            item.setData(data_itemdata[i]);
            list.add(item);
        }



        return list;
    }

    public static String[] data_itemtitles = new String[]{
            "步数",
            "体重",
            "血压",
            "睡眠",
            "体温",
            "尿酸",
            "总胆固醇",
            "无创血糖",
            "脉率",
            "血健康指数",
            "心率",
    };


    public static String[] data_itemdata = new String[]{
            "10052",
            "60.0kg",
            "190/180mmHg",
            "8小时43分",
            "37.1°",
            "18.3mmol/L",
            "233μmol/L",
            "5.5mmol/L",
            "18mmol/L",
            "77",
            "66次/分"
    };

    public static String[] data_ItemTime = new String[]{
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),
            DateTimeUtil.getCurrentFormatDateTime(),

    };


    /**
     * 用于设备页面的信息
     *
     * @return
     */
    @MemoryCache
    public static List<KkyItem> getDeviceNewInfos() {
        List<KkyItem> list = new ArrayList<>();
        for(int i =0 ;i<4 ;i++){
            KkyItem item = new KkyItem();
            item.setTitle(device_itemtitles[i]);
            item.setUrls_res(device_Itempic[i]);
            list.add(item);
        }


        return list;
    }

    public static String[] device_itemtitles = new String[]{
            "健康智能手环",
            "吸氧机",
            "理疗仪",
            "睡眠仪",
    };

    public static int[] device_Itempic = new int[]{
            R.drawable.icon_kky_watch,
            R.drawable.icon_kky_heart,
            R.drawable.icon_kky_sleep,
            R.drawable.icon_kky_more,

    };



















































    public static List<Object> getUsertGuides() {
        List<Object> list = new ArrayList<>();
        list.add(R.drawable.guide_img_1);
        list.add(R.drawable.guide_img_2);
        list.add(R.drawable.guide_img_3);
        list.add(R.drawable.guide_img_4);
        return list;
    }

    public static Class<? extends ViewPager.PageTransformer> transformers[] = new Class[]{
            DepthTransformer.class,
            FadeSlideTransformer.class,
            FlowTransformer.class,
            RotateDownTransformer.class,
            RotateUpTransformer.class,
            ZoomOutSlideTransformer.class,
    };


    public static String[] dpiItems = new String[]{
            "480 × 800",
            "1080 × 1920",
            "720 × 1280",
    };

    public static AdapterItem[] menuItems = new AdapterItem[]{
            new AdapterItem("登陆", R.drawable.icon_password_login),
            new AdapterItem("筛选", R.drawable.icon_filter),
            new AdapterItem("设置", R.drawable.icon_setting),
    };

    public static List<List<ImageViewInfo>> sPics;
    public static List<List<ImageViewInfo>> sVideos;

    public static List<ImageViewInfo> imgs;
    public static List<ImageViewInfo> videos;

    public static List<List<NineGridInfo>> sNineGridPics;
    public static List<List<NineGridInfo>> sNineGridVideos;


    static {
        imgs = new ArrayList<>();
        List<String> list = getUrls();
        for (int i = 0; i < list.size(); i++) {
            imgs.add(new ImageViewInfo(list.get(i)));
        }

        videos = getVideos();

        sPics = split(imgs, 10);
        sVideos = split(videos, 10);

        sNineGridPics = split(getMediaDemos(40, 0), 10);
        sNineGridVideos = split(getMediaDemos(20, 1), 10);

    }

    private static List<NineGridInfo> getMediaDemos(int length, int type) {
        List<NineGridInfo> list = new ArrayList<>();
        NineGridInfo info;
        for (int i = 0; i < length; i++) {
            info = new NineGridInfo("我是一只喵，快乐的星猫～～～", getRandomMedias((int) (Math.random() * 10 + 0.5), type))
                    .setShowType(NineGridImageView.STYLE_FILL);
            list.add(info);
        }
        return list;
    }

    private static List<ImageViewInfo> getRandomMedias(int length, int type) {
        List<ImageViewInfo> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (type == 0) {
                list.add(imgs.get(i));
            } else {
                list.add(videos.get(i));
            }
        }
        return list;
    }


    private static List<ImageViewInfo> getVideos() {
        List<ImageViewInfo> videos = new ArrayList<>();
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-05-25/b146e104069c2bd0590bb919269193c4/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-05-07/d0bbfc4ac4dd173cc93873ed4eb0be53.mp4",
                "http://pic.vjshi.com/2017-05-07/d0bbfc4ac4dd173cc93873ed4eb0be53/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));

        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-07-18/80d08ce1a84adfbaed5c7067b73d19ed.mp4",
                "http://pic.vjshi.com/2017-07-18/80d08ce1a84adfbaed5c7067b73d19ed/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2018-06-07/cf673556cce54ab9cf4633fd7d9d0d46.mp4",
                "http://pic.vjshi.com/2018-06-06/caa296729c8e6e41e6aff2aadf4feff3/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2018-01-27/5169bb7bdd7386ce7bd4ce1739229424.mp4",
                "http://pic.vjshi.com/2018-01-27/5169bb7bdd7386ce7bd4ce1739229424/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png"));
        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-27/9a6e69f7c257ff7b7832e8bac6fddf82.mp4",
                "http://pic.vjshi.com/2017-09-27/9a6e69f7c257ff7b7832e8bac6fddf82/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
        videos.add(new ImageViewInfo("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png"));
        return videos;
    }

    private static List<String> getUrls() {
        List<String> urls = new ArrayList<>();
        urls.add("http://img4.duitang.com/uploads/item/201307/02/20130702113059_UEGL2.jpeg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=985035006,79865976&fm=21&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1774291582,2563335167&fm=21&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1511364704,3337189105&fm=21&gp=0.jpg");
        urls.add("http://pic.qiantucdn.com/58pic/11/90/83/96a58PICrRx.jpg");
        urls.add("http://pic.qiantucdn.com/58pic/13/09/97/26W58PICKNk_1024.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3272030875,860665188&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2237658959,3726297486&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3016675040,1510439865&fm=21&gp=0.jpg");
        urls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");

        urls.add("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3272030875,860665188&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2237658959,3726297486&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3016675040,1510439865&fm=21&gp=0.jpg");
        urls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");
        urls.add("http://d040779c2cd49.scdn.itc.cn/s_big/pic/20161213/184474627873966848.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/07915a0154ac4a64.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
        urls.add("ttp://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/ad99de83e1e3f7d4.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/15c5c50e941ba6b0.jpg");
        urls.add("http://ac-QYgvX1CC.clouddn.com/eaf1c9d55c5f9afd.jpg");
        urls.add("http://pic44.photophoto.cn/20170802/0017030376585114_b.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085702814554_s.jpg");
        urls.add("http://img44.photophoto.cn/20170802/0017030319134956_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084023987260_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084009134421_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");

        urls.add("http://img44.photophoto.cn/20170731/0847085207211178_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0017030319740534_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085969586424_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0014105802293676_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085242661101_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0886088744582079_s.jpg");
        urls.add("http://img44.photophoto.cn/20170801/0017029514287804_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085848134910_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085581124963_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");

        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085200668628_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085246003750_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085012707934_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0005018303330857_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085231427344_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085236829578_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085729490157_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085751995287_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085729043617_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085786392651_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085761440022_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085275244570_s.jpg");


        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");

        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");

        urls.add("http://img44.photophoto.cn/20170731/0847085207211178_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0017030319740534_s.jpg");
        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");
        urls.add("http://img44.photophoto.cn/20170728/0847085969586424_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0014105802293676_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0847085242661101_s.jpg");
        urls.add("http://img44.photophoto.cn/20170727/0886088744582079_s.jpg");
        urls.add("http://img44.photophoto.cn/20170801/0017029514287804_s.jpg");
        urls.add("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085848134910_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085581124963_s.jpg");
        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");

        return urls;
    }


    public static List<ImageViewInfo> getGifUrls() {
        List<ImageViewInfo> userViewInfos = new ArrayList<>();
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/8Q8Vy8jh6wEYCT4bYiEAOZdmzIf7GrLQ.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/yCPIVl3icfbIhZ1rjKKU6Kl6lCKkC8Wq.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/mQK3vlOYVOIpnhNYKg6XuWqpc3yAg9hR.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/mESQBeZn5V8Xzke0XPsnEEXUF9MaU3sA.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/HFuVvydFj7dgIEcbEBMA9ccGcGOFdEsx.gif_s400x0"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/SH0FB6FnTNgoCsVtxcAMtSNfV7XxXmo8.gif"));
        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/KkB9WARG3PFrz9EEX4DJdiy6Vyg95fGl.gif"));
        return userViewInfos;
    }

    /**
     * 拆分集合
     *
     * @param <T>
     * @param resList 要拆分的集合
     * @param count   每个集合的元素个数
     * @return 返回拆分后的各个集合
     */
    public static <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1)
            return null;
        List<List<T>> ret = new ArrayList<>();
        int size = resList.size();
        if (size <= count) { //数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            //前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }


    @MemoryCache
    public static Collection<String> getDemoData() {
        return Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    @MemoryCache
    public static Collection<String> getDemoData1() {
        return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18");
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
    @MemoryCache
    public static List<NewInfo> getEmptyNewInfo() {
        List<NewInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new NewInfo());
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
    @MemoryCache
    public static List<NewInfo> getDemoNewInfos() {
        List<NewInfo> list = new ArrayList<>();
        list.add(new NewInfo("源码", "Android源码分析--Android系统启动")
                .setSummary("其实Android系统的启动最主要的内容无非是init、Zygote、SystemServer这三个进程的启动，他们一起构成的铁三角是Android系统的基础。")
                .setDetailUrl("https://juejin.im/post/5c6fc0cdf265da2dda694f05")
                .setImageUrl("https://user-gold-cdn.xitu.io/2019/2/22/16914891cd8a950a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new NewInfo("Android UI", "XUI 一个简洁而优雅的Android原生UI框架，解放你的双手")
                .setSummary("涵盖绝大部分的UI组件：TextView、Button、EditText、ImageView、Spinner、Picker、Dialog、PopupWindow、ProgressBar、LoadingView、StateLayout、FlowLayout、Switch、Actionbar、TabBar、Banner、GuideView、BadgeView、MarqueeView、WebView、SearchView等一系列的组件和丰富多彩的样式主题。\n")
                .setDetailUrl("https://juejin.im/post/5c3ed1dae51d4543805ea48d")
                .setImageUrl("https://user-gold-cdn.xitu.io/2019/1/16/1685563ae5456408?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new NewInfo("面试", "写给即将面试的你")
                .setSummary("最近由于公司业务发展，需要招聘技术方面的人才，由于我在技术方面比较熟悉，技术面的任务就交给我了。今天我要分享的就和面试有关，主要包含技术面的流程、经验和建议，避免大家在今后的面试过程中走一些弯路，帮助即将需要跳槽面试的人。")
                .setDetailUrl("https://juejin.im/post/5ca4df966fb9a05e4e58320c")
                .setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554629219186&di=6cdab5cfceaae1f7e6d78dbe79104c9f&imgtype=0&src=http%3A%2F%2Fimg.qinxue365.com%2Fuploads%2Fallimg%2F1902%2F4158-1Z22FZ64E00.jpg"));

        list.add(new NewInfo("Android", "XUpdate 一个轻量级、高可用性的Android版本更新框架")
                .setSummary("XUpdate 一个轻量级、高可用性的Android版本更新框架。本框架借鉴了AppUpdate中的部分思想和UI界面，将版本更新中的各部分环节抽离出来，形成了如下几个部分：")
                .setDetailUrl("https://juejin.im/post/5b480b79e51d45190905ef44")
                .setImageUrl("https://user-gold-cdn.xitu.io/2018/7/13/16492d9b7877dc21?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));


        list.add(new NewInfo("Android/HTTP", "XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp进行组装")
                .setSummary("一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。还不赶紧点击使用说明文档，体验一下吧！")
                .setDetailUrl("https://juejin.im/post/5b6b9b49e51d4576b828978d")
                .setImageUrl("https://user-gold-cdn.xitu.io/2018/8/9/1651c568a7e30e02?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
        return list;
    }

}
