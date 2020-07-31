package com.kyy.recuperationcourt.common.model.entity.user;

import java.util.List;

public class UserInfo {


    /**
     * code : 0
     * msg : null
     * data : {"sysUser":{"id":"8838651ff6abe886ecf610b7c2b00df1","username":"13627494495","email":"chen@kangyangyuan.cn","password":"$2a$10$5Dl4w10Q4AoxUY7zMNRWPuQghrHCWLfQf8bSd2L648PXNbkZz0owG","createTime":"2019-12-13 10:55:38","updateTime":"2019-12-18 12:19:34","delFlag":"0","lockFlag":"0","phone":"13627494495","avatar":null,"organId":"3","tenantId":"1","wxOpenid":null,"qqOpenid":null,"giteeLogin":null,"oscId":null},"permissions":["kang_tarticle_edit","mall_goodsappraises_get","kang_twatchdevice_del","kang_texternaldevice_del","kang_tcollectdata_del","kang_tchilduser_add","kang_twatchcollectdata_get","kang_texternaldevice_index","mall_goodsspec_add","kang_tsos_add","mall_goodscategory_add","mall_orderinfo_get","kang_tchilduser_edit","kang_tcollectdata_add","kang_twatchcollectdata_del","kang_tarticle_add","kang_twatchhealthdata_get","mall_goodsappraises_index","kang_tchilduser_get","mall_goodsspu_add","kang_tchilduser_index","kang_tsos_del","mall_goodsspu_edit","kang_tappuser_del","kang_tappuser_index","kang_twatchdevice_edit","kang_tsos_get","mall_goodsappraises_edit","kang_texternaldevice_get","mall_orderinfo_add","kang_thealthreport_index","kang_tcollectdata_index","mall_goodsspec_edit","kang_twatchhealthdata_index","mall_goodscategory_edit","mall_goodsappraises_add","kang_tarticle_del","kang_twatchhealthdata_del","mall_orderinfo_edit","kang_tcollectdata_edit","mall_goodscategory_get","mall_goodsspu_del","kang_twatchdevice_get","mall_goodsappraises_del","kang_texternaldevice_edit","kang_twatchcollectdata_index","mall_orderinfo_index","mall_goodsspec_del","kang_tsos_edit","kang_twatchdevice_add","mall_goodscategory_del","mall_goodsspu_get","kang_tdevice_edit","admin_tsmscode_index","kang_tdevice_index","kang_tsos_index","mall_goodsspu_index","mall_orderinfo_del","kang_tappuser_edit","mall_goodsspec_index","kang_tdevice_get","kang_twatchdevice_index","kang_tcollectdata_get","kang_tarticle_get","kang_texternaldevice_add","kang_tchilduser_del","kang_tappuser_get","mall_goodscategory_index","kang_tarticle_index"],"roles":["3"]}
     * ok : true
     */

    private int code;
    private Object msg;
    private DataBean data;
    private boolean ok;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class DataBean {
        /**
         * sysUser : {"id":"8838651ff6abe886ecf610b7c2b00df1","username":"13627494495","email":"chen@kangyangyuan.cn","password":"$2a$10$5Dl4w10Q4AoxUY7zMNRWPuQghrHCWLfQf8bSd2L648PXNbkZz0owG","createTime":"2019-12-13 10:55:38","updateTime":"2019-12-18 12:19:34","delFlag":"0","lockFlag":"0","phone":"13627494495","avatar":null,"organId":"3","tenantId":"1","wxOpenid":null,"qqOpenid":null,"giteeLogin":null,"oscId":null}
         * permissions : ["kang_tarticle_edit","mall_goodsappraises_get","kang_twatchdevice_del","kang_texternaldevice_del","kang_tcollectdata_del","kang_tchilduser_add","kang_twatchcollectdata_get","kang_texternaldevice_index","mall_goodsspec_add","kang_tsos_add","mall_goodscategory_add","mall_orderinfo_get","kang_tchilduser_edit","kang_tcollectdata_add","kang_twatchcollectdata_del","kang_tarticle_add","kang_twatchhealthdata_get","mall_goodsappraises_index","kang_tchilduser_get","mall_goodsspu_add","kang_tchilduser_index","kang_tsos_del","mall_goodsspu_edit","kang_tappuser_del","kang_tappuser_index","kang_twatchdevice_edit","kang_tsos_get","mall_goodsappraises_edit","kang_texternaldevice_get","mall_orderinfo_add","kang_thealthreport_index","kang_tcollectdata_index","mall_goodsspec_edit","kang_twatchhealthdata_index","mall_goodscategory_edit","mall_goodsappraises_add","kang_tarticle_del","kang_twatchhealthdata_del","mall_orderinfo_edit","kang_tcollectdata_edit","mall_goodscategory_get","mall_goodsspu_del","kang_twatchdevice_get","mall_goodsappraises_del","kang_texternaldevice_edit","kang_twatchcollectdata_index","mall_orderinfo_index","mall_goodsspec_del","kang_tsos_edit","kang_twatchdevice_add","mall_goodscategory_del","mall_goodsspu_get","kang_tdevice_edit","admin_tsmscode_index","kang_tdevice_index","kang_tsos_index","mall_goodsspu_index","mall_orderinfo_del","kang_tappuser_edit","mall_goodsspec_index","kang_tdevice_get","kang_twatchdevice_index","kang_tcollectdata_get","kang_tarticle_get","kang_texternaldevice_add","kang_tchilduser_del","kang_tappuser_get","mall_goodscategory_index","kang_tarticle_index"]
         * roles : ["3"]
         */

        private SysUserBean sysUser;
        private List<String> permissions;
        private List<String> roles;


        /**
         *
         */

        private String id;
        private int type;
        private String title;
        private String content;
        private Object image;
        private int sort;
        private int count;
        private int status;
        private String updateTime;
        private String createTime;



        /**
         * records : [{"id":"539e8685a37347599bc0b6c2a52ff5cc","categoryId":"6","title":"吃晚餐\u201c三大忌\u201d，天天吃等同于\u201c活腻\u201d了！看过的人切记","author":"天天养生","fromurl":"http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456.html","picUrls":"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/material/6eec03b3-d846-4935-9cd0-7d8946d32477.jpg","keywords":"养生 健康","description":null,"type":1,"allowcomments":1,"status":1,"content":"<p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>晚餐要怎么吃呢？我们的生活是越来越繁忙了，很多人都是早午餐比较随便的吃，晚餐往往会吃的比较有营养，其实这都是不对的，这样做可能会给我们的生活造成很多困扰。下面天天养生小编就为大家盘点晚餐的常见误。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　一禁<a href=\"http://www.ttys5.com/yundong/yujia/2015-07-02/106850.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\"><span style=\"color: rgb(0, 0, 255);\"><strong>晚餐<\/strong><\/span><\/a>吃太多：晚餐早吃少患结石<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　晚餐早吃是医学专家向人们推荐的保健良策。有关研究表明，晚餐早吃可大大降低尿路结石病的发病率。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><a href=\"http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456_2.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\" target=\"_self\"><br><img src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/ec4238db-1563-4e76-badc-aa5e647012f5.png\" alt=\"[URL]10007a6ed633ee5519366190a7a771a2.jpg\" width=\"500\" height=\"300\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/a><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　人的排钙高峰期常在进餐后4-5小时，若晚餐过晚，当排钙高峰期到来时，人已上床入睡，尿液便潴留在输尿管、膀胱、尿道等尿路中，不能及时排出体外，致使尿中钙不断增加，容易沉积下来形成小晶体，久而久之，逐渐扩大形成结石。所以，傍晚6点左右进晚餐较合适。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>二禁晚餐吃太荤：晚餐吃素防癌<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　晚餐一定要偏素，以富含碳水化合物的食物为主，而蛋白质、脂肪类吃得越少越好。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　但在现实生活中，由于大多数家庭晚餐准备时间充裕，吃得丰富，这样对健康不利。据科学研究报告，晚餐时吃大量的肉、蛋、奶等高蛋白食品，会使尿中的钙量增加，一方面降低了体内的钙贮存，诱发儿童佝偻病、青少年近视和中它年骨质疏松症；另一方面尿中钙浓度高，罹患尿路结石病的可能性就会大大提高。另外，摄入蛋白质过多，人体吸收不了就会滞留于肠道中，会变质，产生氨、硫化氢等毒质，刺激肠壁诱发癌症。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><a href=\"http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456_3.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\" target=\"_self\"><img alt=\"晚餐.jpg\" width=\"500\" height=\"300\" src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/41296358-5a2d-49c8-847d-e0cda8fd5ba2.png\" style=\"border: 0px; max-width: 610px; text-align: center;\" class=\"fr-fic fr-dii\"><\/a><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　若脂肪吃得太多，可使血脂升高。研究资料表明，晚餐经常吃荤食的人比吃素者的血脂要高2-3倍。碳水化合物可在人体内生成更多的血清素，发挥镇静安神作用，对失眠者尤为有益。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　三禁晚餐吃太饱：晚餐吃少睡眠好<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　与早餐、中餐相比，晚餐宜少吃晚间无其他活动，或进食时间较晚，如果晚餐吃得过多，可引起胆固醇升高，刺激肝脏制造更多的低密度与极低密度脂蛋白，诱发动脉硬化；长期晚餐过饱，反覆刺激胰岛素大量分泌，往往造成胰岛素&beta;细胞提前衰竭，从而埋下糖尿病的祸根。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><a href=\"http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456_4.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\" target=\"_self\"><img src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/8a8bb951-4b88-441d-b47c-175b2bb2be90.png\" alt=\"[URL]00d632aa7b59c171278c77c47253b186.jpg\" width=\"500\" height=\"300\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/a><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　此外，晚餐过饱可使胃鼓胀，对周围器官造成压迫，胃、肠、肝、胆、胰等器官在餐后的紧张工作会传送信息给大脑，引起大脑活跃，并扩散到大脑皮层其他部位，诱发失眠。<\/p>","userId":null,"readnumber":0,"top":0,"systemId":null,"orders":0,"createTime":"2019-12-24 10:42:48"},{"id":"5a445e6600cf46359407c62fdadaa8b6","categoryId":"5","title":"脚底养生功效多按摩脚底有哪些好处","author":"天天养生","fromurl":"http://www.ttys5.com/zhongyi/tizhi/2017-02-13/134361.html","picUrls":"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/user/64c9ffb9-de4e-449b-840b-aab63e17fc59.png","keywords":"养生 健康","description":null,"type":1,"allowcomments":1,"status":1,"content":"<p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　中医认为，在我们的脚底有很多养生穴位，而经常按摩这些穴位，对于养生保健来说，是很有好处的。小编我要提醒你，按摩脚底是要讲究方式和方法的。在我们按摩脚底时，有一些注意事项，快跟天天养生网小编一起去看看吧！<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　脚底按摩功效<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　脚底按摩也是第三医学的一种，目前愈来愈受到医界的重视，脚底按摩有以下的功能；<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　1、首先的脚底按摩是可以促进我们的血液循环，是我们血液顺畅流通。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　2、刺激细胞产生活力，防止老化。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　3、排泄体内毒素杂物，维护健康。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　4、疏通人体能源循环管道之障碍。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　5、脚底按摩还可以加强新我们的陈代谢功能，这样就可以我们很好的排毒，可以保持青春活力。<\/p><p align=\"center\" style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><a href=\"http://www.ttys5.com/d/file/xinwen/xinwenhangye/2017-02-13/b8a8aeaced35d2cdfe77b2e1300259e3.jpg\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\" target=\"_blank\"><img border=\"0\" alt=\"按摩脚底养生好但有事项要小心\" src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/0b1b7ed7-f4b5-4f0e-8b2c-a1dc6dec7e84.png\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/a><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　6、恢复退化的器官机能，预防生病。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　7、增进内分泌之平衡，缓和趋於紧张之系统。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　8、促进器官部位功能之正常与各器官系统间的协调。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　<a href=\"http://www.ttys5.com/\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\"><strong>天天养生<\/strong><\/a>提供&ldquo;按摩脚底养生好但有事项要小心&rdquo;阅读，如果你很喜欢这些分享的&ldquo;按摩脚底养生好但有事项要小心&rdquo;内容，希望你通过&ldquo;按摩脚底养生好但有事项要小心&rdquo;,找到通往健康之路的金钥匙。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　<strong>按摩脚底养生好但有事项要小心的相关内容介绍：<\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><strong>　　<a href=\"http://www.ttys5.com/zhongyi/jingluo/2014-02-14/89594.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\">中医教你按摩5穴位帮你补气血<\/a><\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><strong>　　<a href=\"http://www.ttys5.com/zhongyi/jingluo/2014-12-12/95605.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\">推拿和按摩有什么区别<\/a><\/strong><\/p>","userId":null,"readnumber":0,"top":0,"systemId":null,"orders":0,"createTime":"2019-12-24 10:40:52"},{"id":"820927c9d489498da4df9cc4939493d3","categoryId":"10","title":"长寿秘方：吃花生降低胆固醇","author":"天天养生","fromurl":"http://www.ttys5.com/shanshi/yingyang/2013-12-03/86878.html","picUrls":"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/user/abd480e2-a202-458d-886d-c67da00a63be.jpg","keywords":"养生 健康","description":null,"type":1,"allowcomments":1,"status":1,"content":"<p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>花生是一种老少皆宜的食品，不仅是烹调美食的配料，同时也是非常可口的零食，其养生的功效也不容小觑。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　花生营养丰富，是价廉易得的保健佳品。据科学家研究，花生含大量的植物固醇，对人的健康十分有益。尤其是&beta;-谷固醇有预防大肠癌、前列腺癌、乳腺癌及心血管病的作用。美国科学家进一步研究发现，花生中还含有&ldquo;白藜芦醇&rdquo;.这种物质有很强的生物活性，不仅能抵御癌症，还能抑制血小板凝聚，防止心肌梗死与脑梗塞。每100克花生还含有锌8.48毫克，能增强免疫功能，延缓衰老。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'>　　<a href=\"http://www.ttys5.com/shanshi/yingyang/2013-12-03/86878_2.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\" target=\"_self\"><img alt=\"长寿秘方：吃花生降低胆固醇\" width=\"400\" height=\"300\" src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/c1f11901-d550-45fb-bd82-00708fe74656.png\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/a><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　因此，花生被人们称誉为&ldquo;长生果&rdquo;。<\/p>","userId":null,"readnumber":0,"top":0,"systemId":null,"orders":0,"createTime":"2019-12-24 10:46:44"},{"id":"cc0e41f042e9464e8883a205801f52a0","categoryId":"6","title":"益于血管修复鱼头豆腐煲","author":"天天养生","fromurl":"http://www.ttys5.com/shanshi/shipu/2018-10-19/160389.html","picUrls":"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/user/a68ca247-472d-4423-9bb3-de0b18868849.png","keywords":"养生 健康 鱼","description":null,"type":1,"allowcomments":1,"status":1,"content":"<p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>&nbsp;　　在深秋时，老年人警惕血管疾病，吃些豆腐，跟鱼头做汤，营养高，含钙量也高，对于身体的帮助很大，非常适合全家人食用哦。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　<strong>【益于<a href=\"http://www.ttys5.com/xinwen/xinwenhangye/2017-04-19/137687_6.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\"><span style=\"color: rgb(0, 0, 255);\">血管<\/span><\/a>修复鱼头豆腐煲】<\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><strong>　　主料<\/strong>：鱼头1个、豆腐100g、<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　<strong>辅料<\/strong>：油适量、盐适量、姜胡椒粉、<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　<strong>步骤：<\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤1.准备好鱼头 鱼尾<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤2.豆腐切成小块。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤3.将鱼头清洗干净。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤4.锅中注入少量的油，煸香姜片，下入鱼头 鱼尾煸至变色。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><img src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/e7360ff4-7315-426f-9440-ffdc30dafba3.png\" alt=\"益于血管修复鱼头豆腐煲\" width=\"434\" height=\"324\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'>益于血管修复鱼头豆腐煲<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤5. 倒入冷水，烧开撇去血沫。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤6. 加入豆腐，继续烧开。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤7. 大火煮至汤色奶白。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　鱼头豆腐煲的做法步骤8.加入盐 胡椒调味即可<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><img src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/98669539-3823-4164-ad15-6ff71b047dcd.png\" alt=\"益于血管修复鱼头豆腐煲\" width=\"434\" height=\"319\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'>益于血管修复鱼头豆腐煲<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　<strong>　豆腐的营养价值<\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　1.大豆的蛋白质生物学价值可与鱼肉相媲美，是植物蛋白中的佼佼者。大豆蛋白属于完全蛋白质，其氨基酸组成比较好，人体所必需的氨基酸它几乎都有。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　2.制作豆腐的大豆中含有18%左右的油脂，大部分能转移到豆腐中去。大豆油中的亚油酸比例较大，且不含胆固醇，有益于人体神经、血管、大脑的生长发育。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><img src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/1fea096b-2c2a-404e-b421-13783c0b2005.png\" alt=\"益于血管修复鱼头豆腐煲\" width=\"498\" height=\"353\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'>益于血管修复鱼头豆腐煲<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　3.大豆可以直接烹调食用，人体对其蛋白质的消化吸收率只有65%,而制成豆腐消化率可以提升到92%~95%.<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　谁应该阅读《益于血管修复鱼头豆腐煲 》？答案是：几乎所有人都应该阅读。《益于血管修复鱼头豆腐煲 》是当代的热点话题，<a href=\"http://www.ttys5.com/\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\"><span style=\"color: rgb(0, 128, 128);\"><strong>天天养生<\/strong><\/span><\/a>提供了许多实用的养生指南，通过阅读《益于血管修复鱼头豆腐煲 》不仅能够了解健康的秘密，进而掌握自己的命运，拥有健康美丽的人<\/p>","userId":null,"readnumber":0,"top":0,"systemId":null,"orders":0,"createTime":"2019-12-24 10:38:55"},{"id":"e3122cfe14834985ab80e5b0eeab79af","categoryId":"7","title":"经常练的瑜伽种类有哪些 哈他瑜伽怎么练","author":"天天养生","fromurl":"http://www.ttys5.com/yundong/yujia/2017-05-11/138794.html","picUrls":"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/user/cd368b14-a8f5-4c4e-84e5-c79c90f6f455.jpg","keywords":"养生 健康","description":null,"type":1,"allowcomments":1,"status":1,"content":"<p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>经常练的<a href=\"http://www.ttys5.com/e/tags/?tagid=125\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\" target=\"_blank\">瑜伽<\/a>种类有哪些？瑜伽并不是只有一种风格，每一种瑜伽都有它自己的特色有效果，那么经常练的瑜伽种类有哪些呢？天天养生网小编给大家讲讲经常练的瑜伽种类有哪些？<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　哈他瑜伽<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　在哈他这个词中，&ldquo;哈&rdquo;的意思是太阳，&ldquo;他&rdquo;的意思是月亮。&ldquo;哈他&rdquo;代表男与女，日与夜、阴与阳、冷与热、柔与刚，以及其他任何相辅相成的两个对立面的平衡。哈他瑜伽认为，人体包括两个体系，一为精神体系。一为肌体体系。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　人的平常思想活动大部分是无序骚乱的，是能力的浪费比如：疲劳、兴奋、哀伤、激动，人体只有一小部分用于维持生命。在通常情况下，如果这种失调现象不太严重时，通过休息便可自然恢复平衡，但是如果不能主动的自我克制和调节，这种失调会日益加剧导致精神和肌体上的疾病。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　体位法可以打破原有的骚乱，消除肌体不安定的因素，停止恶性循环的运动。通过调息来清除体内神经系统的滞障，通过庞达控制身体的能量并加以利用。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　王瑜伽<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　如果说哈他瑜伽是打开瑜伽之门的钥匙，那王瑜伽就是通往精神世界的必由之路。哈他瑜伽重在体式和制气，王瑜伽偏于意念和调息。通常使用莲花坐等一些体位法日进行冥想，摒弃了大多数严格的体位法。王瑜伽积极提倡瑜伽的八支分法，即禁制、尊行、坐法、调息、制感、内醒、静虑、三摩地。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　瑜伽冥想方法很多，但体位姿势大都采用莲花坐，练习冥想时通过意念来感受实体的运动，控制气脉在体内流通，产生不同的神通力。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　一点凝视法是瑜伽者常常喜爱的一种冥想练习，这通常是在环境幽静的地方，或在山林湖海边将注意力集中在某一固定的实体中比如克里希那神像或是蜡烛、树叶、野花或是瀑布、流水等等。使自己的精神完全沉浸在无限深邃的寂静中。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　智瑜伽<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　提倡培养知识理念，从无明中解脱出来，达到神圣知识，以期待与梵合一。智瑜伽认为，知识有低等和高等之别。寻常人所说的知识仅仅局限于生命和物质的外在表现。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　这种低等知识可以通过直接或间接的途径获得。然而智瑜伽所寻求的的知识，则要求瑜伽者转眼内向，透过一切外在事物的本质，去体验和理解创造万物之神-梵。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　通过朗读古老的、被认为是天启的经典，理解书中那些真正的奥义，获得神圣的真谛。瑜伽师凭借瑜伽实践提升生命之气，打开头顶的梵穴轮，让梵进入身体获得无上智慧。<\/p><p align=\"center\" style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><a href=\"http://www.ttys5.com/d/file/yundong/yujia/2017-05-11/e98dbafc7d66b0025e876e43bb6b8657.jpg\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\" target=\"_blank\"><img border=\"0\" alt=\"经常练的瑜伽种类有哪些 哈他瑜伽怎么练\" src=\"https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/f9d6b3c8-28b3-4dd7-ada2-0251af1a067b.png\" style=\"border: 0px; max-width: 610px;\" class=\"fr-fic fr-dii\"><\/a><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　业瑜伽<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　业是行为的意思。业瑜伽认为，行为是生命的第一表现，比如衣食、起居、言谈、举止等等。业瑜伽倡导将精力集中于内心的世界，通过内性的精神活动，引导更加完善的的行为。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　瑜伽师通常采取极度克制的苦行，历尽善行，崇神律己，执着苦行，净心寡欲。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　他们认为人最好的朋友和最坏的敌人都是他本身，这全由他自己的行为决定。只有完全的奉献和皈依，才能使自己的精神、情操、行为达到与梵合一的最终境界。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　艾扬格瑜伽<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　88岁的艾扬格大师是艾扬格瑜伽的创立者。有人说，这一体系的瑜伽是初学者、病人、中老年人的福音。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　艾扬格从小体弱多病，最初练习哈他瑜伽是为了强身健体，并且一度大到很高的境界。60岁左右时，艾扬格经历了一场车祸，严重的伤害使他连最简单的体位姿势都不能做了。经过9年时间，凭借超乎常人的毅力和努力，艾扬格终于恢复了健康。艾扬格深刻体会到身有疾病的痛苦，以及瑜伽所带来的神奇恢复功效，由此创建了着名的、具有治疗效果的艾扬格瑜伽体系。艾扬格瑜伽被公认为最讲究体位练习方法，它可以协调身体平衡，对疾病治疗效果很好。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　练习艾扬格瑜伽，需要特别关注身体各部位的细节，善于利用各种辅助道具。这种课程的设置缓慢而有节制。姿势的稳定能够促进呼吸的深长，意识的专注集中可以提升精神力量。国内尚未开始传授这一体系，相信在不久的将来就会传入中国。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　适合：<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　身体较硬的人，患者，术后产后恢复的人。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　很多人通过练习艾扬格瑜伽恢复了健康，但如果性格急躁会觉得过于沉闷。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　特点：<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　各种各样令人眼花缭乱的辅助道具，是艾扬格瑜伽与传统瑜伽最大的不同。其中的很多姿势都要用木块、长凳、沙袋、毯子、垫枕、布带等辅助工具来完成，这样还可以加大动作幅度。也让很多看似遥不可及的动作不再复杂，从而使不同身体程度的学员同样受益。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　昆达利尼瑜伽<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　又称为蛇王瑜伽。昆达利尼证明了人体周身存在72,000条气脉，七大梵穴轮，一根主通道和一条尚未唤醒而处在休眠状态的圣蛇。通过打通气脉，使生命之气唤醒那条蛇，使他穿过所有的梵穴轮而到达体外，一旦昆达利尼蛇冲出头顶的梵穴轮，即可获得出神入化的三摩地。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　练习昆达利尼瑜伽的人是相当少的，因为昆达利尼对人的要求很高，经常练习数十年至久的瑜伽者并没有获得任何神通力或是三摩地境界。昆达利尼瑜伽是瑜伽中较为难以练习的方法，只有持之以恒方可获得力量。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　阿斯汤嘎瑜伽<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　在帕坦伽利的《瑜伽经》里，八分支法瑜伽也被称做阿斯汤嘎瑜伽，是最古老的瑜伽练习体系。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　阿斯汤嘎瑜伽是一项严格的练习。世界流行的练习方式是由印度瑜伽师PattabbiJois创立的。阿斯汤嘎瑜伽分为基础级、中级、高级3种级别。每种级别的动作编排是固定不变的，都以5遍太阳祈祷式A和B开始，中间有大量的体位姿势练习，最后以倒立和休息术作为结束。这样连续不断动作练习的目的，在于消耗大量热量，以清洁身体，排出毒素。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　阿斯汤嘎瑜伽均衡地锻炼了身体的力量、柔韧度和耐力。欧美国家很多健身爱好者都热衷于此。在西方，这种瑜伽也被称作&ldquo;力量瑜伽&rdquo;.国内一些瑜伽馆已经开设阿斯汤嘎瑜伽课程，受到年轻人的欢迎，但大多以初级为主。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　<a href=\"http://www.ttys5.com/\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\"><strong>天天养生<\/strong><\/a>提供&ldquo;你必知的早餐的四种错误吃法&rdquo;阅读，如果你很喜欢这些分享的&ldquo;你必知的早餐的四种错误吃法&rdquo;内容，希望你通过&ldquo;你必知的早餐的四种错误吃法&rdquo;,找到通往健康之路的金钥匙。<\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><strong>　　你必知的早餐的四种错误吃法的相关内容介绍：<\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><strong>　　<a href=\"http://www.ttys5.com/yundong/yujia/2015-07-05/106935.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\">在家如何练哈达瑜伽呢<\/a><\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><strong>　　<a href=\"http://www.ttys5.com/yundong/yujia/2015-06-25/106419.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\">瑜伽呼吸怎样排出体内浊气<\/a><\/strong><\/p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: \"Microsoft YaHei\"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'><strong>　　<a href=\"http://www.ttys5.com/yundong/yujia/2015-11-18/112944.html\" style=\"color: rgb(81, 140, 255); text-decoration: underline; outline: none;\">公室人的颈椎瑜珈<\/a><\/strong><\/p>","userId":null,"readnumber":0,"top":0,"systemId":null,"orders":0,"createTime":"2019-12-24 10:44:15"}]
         * total : 5
         * size : 20
         * current : 1
         * orders : []
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * id : 539e8685a37347599bc0b6c2a52ff5cc
             * categoryId : 6
             * title : 吃晚餐“三大忌”，天天吃等同于“活腻”了！看过的人切记
             * author : 天天养生
             * fromurl : http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456.html
             * picUrls : https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/material/6eec03b3-d846-4935-9cd0-7d8946d32477.jpg
             * keywords : 养生 健康
             * description : null
             * type : 1
             * allowcomments : 1
             * status : 1
             * content : <p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>晚餐要怎么吃呢？我们的生活是越来越繁忙了，很多人都是早午餐比较随便的吃，晚餐往往会吃的比较有营养，其实这都是不对的，这样做可能会给我们的生活造成很多困扰。下面天天养生小编就为大家盘点晚餐的常见误。</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　一禁<a href="http://www.ttys5.com/yundong/yujia/2015-07-02/106850.html" style="color: rgb(81, 140, 255); text-decoration: underline; outline: none;"><span style="color: rgb(0, 0, 255);"><strong>晚餐</strong></span></a>吃太多：晚餐早吃少患结石</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　晚餐早吃是医学专家向人们推荐的保健良策。有关研究表明，晚餐早吃可大大降低尿路结石病的发病率。</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><a href="http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456_2.html" style="color: rgb(81, 140, 255); text-decoration: underline; outline: none;" target="_self"><br><img src="https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/ec4238db-1563-4e76-badc-aa5e647012f5.png" alt="[URL]10007a6ed633ee5519366190a7a771a2.jpg" width="500" height="300" style="border: 0px; max-width: 610px;" class="fr-fic fr-dii"></a></p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　人的排钙高峰期常在进餐后4-5小时，若晚餐过晚，当排钙高峰期到来时，人已上床入睡，尿液便潴留在输尿管、膀胱、尿道等尿路中，不能及时排出体外，致使尿中钙不断增加，容易沉积下来形成小晶体，久而久之，逐渐扩大形成结石。所以，傍晚6点左右进晚餐较合适。</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>二禁晚餐吃太荤：晚餐吃素防癌</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　晚餐一定要偏素，以富含碳水化合物的食物为主，而蛋白质、脂肪类吃得越少越好。</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　但在现实生活中，由于大多数家庭晚餐准备时间充裕，吃得丰富，这样对健康不利。据科学研究报告，晚餐时吃大量的肉、蛋、奶等高蛋白食品，会使尿中的钙量增加，一方面降低了体内的钙贮存，诱发儿童佝偻病、青少年近视和中它年骨质疏松症；另一方面尿中钙浓度高，罹患尿路结石病的可能性就会大大提高。另外，摄入蛋白质过多，人体吸收不了就会滞留于肠道中，会变质，产生氨、硫化氢等毒质，刺激肠壁诱发癌症。</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><a href="http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456_3.html" style="color: rgb(81, 140, 255); text-decoration: underline; outline: none;" target="_self"><img alt="晚餐.jpg" width="500" height="300" src="https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/41296358-5a2d-49c8-847d-e0cda8fd5ba2.png" style="border: 0px; max-width: 610px; text-align: center;" class="fr-fic fr-dii"></a></p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　若脂肪吃得太多，可使血脂升高。研究资料表明，晚餐经常吃荤食的人比吃素者的血脂要高2-3倍。碳水化合物可在人体内生成更多的血清素，发挥镇静安神作用，对失眠者尤为有益。</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　三禁晚餐吃太饱：晚餐吃少睡眠好</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　与早餐、中餐相比，晚餐宜少吃晚间无其他活动，或进食时间较晚，如果晚餐吃得过多，可引起胆固醇升高，刺激肝脏制造更多的低密度与极低密度脂蛋白，诱发动脉硬化；长期晚餐过饱，反覆刺激胰岛素大量分泌，往往造成胰岛素&beta;细胞提前衰竭，从而埋下糖尿病的祸根。</p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial; text-align: center;'><a href="http://www.ttys5.com/xinwen/xinwenhangye/2015-11-11/112456_4.html" style="color: rgb(81, 140, 255); text-decoration: underline; outline: none;" target="_self"><img src="https://kangfuzhineng.oss-cn-shenzhen.aliyuncs.com/1/editor/8a8bb951-4b88-441d-b47c-175b2bb2be90.png" alt="[URL]00d632aa7b59c171278c77c47253b186.jpg" width="500" height="300" style="border: 0px; max-width: 610px;" class="fr-fic fr-dii"></a></p><p style='margin: 0px 0px 10px; padding: 0px 15px; font-size: 14px; line-height: 2; color: rgb(68, 68, 68); font-family: "Microsoft YaHei"; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;'>　　此外，晚餐过饱可使胃鼓胀，对周围器官造成压迫，胃、肠、肝、胆、胰等器官在餐后的紧张工作会传送信息给大脑，引起大脑活跃，并扩散到大脑皮层其他部位，诱发失眠。</p>
             * userId : null
             * readnumber : 0
             * top : 0
             * systemId : null
             * orders : 0
             * createTime : 2019-12-24 10:42:48
             */

            private String id;
            private String categoryId;
            private String title;
            private String author;
            private String fromurl;
            private String picUrls;
            private String keywords;
            private String description;
            private int type;
            private int allowcomments;
            private int status;
            private String content;
            private Object userId;
            private int readnumber;
            private int top;
            private int systemId;
            private int orders;
            private String createTime;
            /**
             * pid : 6
             * level : 2
             * name : 热点
             * description : 热点
             * icon : icon
             * alias : hot
             * systemId : 1
             * createTime : null
             */

            private String pid;
            private int level;
            private String name;
            private String icon;
            private String alias;



            private String deviceId;
            private String sysUserId;
            private String headImg;
            private String nickname;
            private int age;
            private String birthday;
            private int sex;
            private String phone;
            private int height;
            private int weight;
            private int latelyUse;
            private String realName;
            private String residenceAddress;
            private String userName;
            private String telephone;
            private String provinceId;
            private String province;
            private String cityId;
            private String city;
            private String areaId;
            private String area;
            private String detailAddress;
            private String isScan;
            private String isDel;

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getSysUserId() {
                return sysUserId;
            }

            public void setSysUserId(String sysUserId) {
                this.sysUserId = sysUserId;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getLatelyUse() {
                return latelyUse;
            }

            public void setLatelyUse(int latelyUse) {
                this.latelyUse = latelyUse;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getResidenceAddress() {
                return residenceAddress;
            }

            public void setResidenceAddress(String residenceAddress) {
                this.residenceAddress = residenceAddress;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public String getIsScan() {
                return isScan;
            }

            public void setIsScan(String isScan) {
                this.isScan = isScan;
            }

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }



            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getFromurl() {
                return fromurl;
            }

            public void setFromurl(String fromurl) {
                this.fromurl = fromurl;
            }

            public String getPicUrls() {
                return picUrls;
            }

            public void setPicUrls(String picUrls) {
                this.picUrls = picUrls;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getAllowcomments() {
                return allowcomments;
            }

            public void setAllowcomments(int allowcomments) {
                this.allowcomments = allowcomments;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public int getReadnumber() {
                return readnumber;
            }

            public void setReadnumber(int readnumber) {
                this.readnumber = readnumber;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getSystemId() {
                return systemId;
            }

            public void setSystemId(int systemId) {
                this.systemId = systemId;
            }

            public int getOrders() {
                return orders;
            }

            public void setOrders(int orders) {
                this.orders = orders;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }



            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }


        public SysUserBean getSysUser() {
            return sysUser;
        }

        public void setSysUser(SysUserBean sysUser) {
            this.sysUser = sysUser;
        }

        public List<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "sysUser=" + sysUser +
                    ", permissions=" + permissions +
                    ", roles=" + roles +
                    '}';
        }

        public static class SysUserBean {
            /**
             * id : 8838651ff6abe886ecf610b7c2b00df1
             * username : 13627494495
             * email : chen@kangyangyuan.cn
             * password : $2a$10$5Dl4w10Q4AoxUY7zMNRWPuQghrHCWLfQf8bSd2L648PXNbkZz0owG
             * createTime : 2019-12-13 10:55:38
             * updateTime : 2019-12-18 12:19:34
             * delFlag : 0
             * lockFlag : 0
             * phone : 13627494495
             * avatar : null
             * organId : 3
             * tenantId : 1
             * wxOpenid : null
             * qqOpenid : null
             * giteeLogin : null
             * oscId : null
             */

            private String id;
            private String username;
            private String email;
            private String password;
            private String createTime;
            private String updateTime;
            private String delFlag;
            private String lockFlag;
            private String phone;
            private Object avatar;
            private String organId;
            private String tenantId;
            private Object wxOpenid;
            private Object qqOpenid;
            private Object giteeLogin;
            private Object oscId;




            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getLockFlag() {
                return lockFlag;
            }

            public void setLockFlag(String lockFlag) {
                this.lockFlag = lockFlag;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }

            public String getOrganId() {
                return organId;
            }

            public void setOrganId(String organId) {
                this.organId = organId;
            }

            public String getTenantId() {
                return tenantId;
            }

            public void setTenantId(String tenantId) {
                this.tenantId = tenantId;
            }

            public Object getWxOpenid() {
                return wxOpenid;
            }

            public void setWxOpenid(Object wxOpenid) {
                this.wxOpenid = wxOpenid;
            }

            public Object getQqOpenid() {
                return qqOpenid;
            }

            public void setQqOpenid(Object qqOpenid) {
                this.qqOpenid = qqOpenid;
            }

            public Object getGiteeLogin() {
                return giteeLogin;
            }

            public void setGiteeLogin(Object giteeLogin) {
                this.giteeLogin = giteeLogin;
            }

            public Object getOscId() {
                return oscId;
            }

            public void setOscId(Object oscId) {
                this.oscId = oscId;
            }

            @Override
            public String toString() {
                return "SysUserBean{" +
                        "id='" + id + '\'' +
                        ", username='" + username + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", updateTime='" + updateTime + '\'' +
                        ", delFlag='" + delFlag + '\'' +
                        ", lockFlag='" + lockFlag + '\'' +
                        ", phone='" + phone + '\'' +
                        ", avatar=" + avatar +
                        ", organId='" + organId + '\'' +
                        ", tenantId='" + tenantId + '\'' +
                        ", wxOpenid=" + wxOpenid +
                        ", qqOpenid=" + qqOpenid +
                        ", giteeLogin=" + giteeLogin +
                        ", oscId=" + oscId +
                        '}';
            }
        }
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "code=" + code +
                ", msg=" + msg +
                ", data=" + data +
                ", ok=" + ok +
                '}';
    }
}
