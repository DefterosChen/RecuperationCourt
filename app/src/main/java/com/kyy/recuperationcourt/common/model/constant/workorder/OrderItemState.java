package com.kyy.recuperationcourt.common.model.constant.workorder;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public enum  OrderItemState {

    STATE_TOBEGIN(0,"待开工"),
    STATE_EXCUTING(1,"进行中"),
    STATE_FINISH(2,"已完工"),

    STATE_UNFINISH(9,"尚未完工");


    private Integer code;
    private String desc;

    private static Map<Integer, String> codeToDesc;

    static {
        codeToDesc = Arrays.stream(OrderItemState.values()).collect(Collectors.toMap(item -> item.code, item -> item.desc));
    }

    private OrderItemState(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(Integer code) {
        return codeToDesc.get(code);
    }

}
