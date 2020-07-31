package com.kyy.recuperationcourt.common.model.constant;

public class Consts {


    public final static String KEY_POP_BACK_NAME = "key_pop_back_name";


    public final static int ITEM_TYPE_ADAPTER_TDPT = 0;

    public final static int ITEM_TYPE_ADAPTER_TDPB = 1;

    public final static int ITEM_TYPE_ADAPTER_TDPR = 2;

    public final static int ITEM_TYPE_ADAPTER_COURSE = 3;

    //登录方式
    public final static int LOGIN_WAY_BY_VCODE = 0; //短信验证码登录
    public final static int LOGIN_WAY_BY_PASSWORD = 1;  //账号密码登录


    //帐号或密码错误
    public final static String REPONSE_101 = "101";
    //重复的账号名，已注册
    public final static String REPONSE_102 = "102";
    //该手机号码已注册
    public final static String REPONSE_103 = "103";
    //该身份证已注册
    public final static String REPONSE_104 = "104";
    //当前用户已不是该工单所分配的服务人员
    public final static String REPONSE_105 = "105";
    //帐号或密码错误
    public final static String REPONSE_106 = "106";
    //当前工单已不是“未分配”状态
    public final static String REPONSE_107 = "107";
    //未登录
    public final static String REPONSE_505 = "505";

    //返回成功
    public final static String REPONSE_200 = "200";

    // 逻辑删除状态  1：已删除 0：未删除
    public final static int DELETED_YES = 1;
    public final static int DELETED_NO = 0;

    //工单状态
    public final static String WORKORDER_BEGIN = "开工";
    public final static String WORKORDER_EXCUTING = "完工";
    public final static String WORKORDER_VISIT = "生成回访单";
    public final static String WORKORDER_FINISH = "直接终结工单";

    public  final  static  String FORMPURPOSE = "页面跳转目的";
    public  final  static  int PURPOSE_ADD = 1;
    public  final  static  int PURPOSE_MODIFY = 2;

}
