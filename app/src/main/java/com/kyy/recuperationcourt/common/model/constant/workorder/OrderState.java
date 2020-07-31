package com.kyy.recuperationcourt.common.model.constant.workorder;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public enum OrderState {

    WAITING_ASSIGN(0, "待分派"),
    WAITING_COMFIRM(1, "待确认"),
    WAITING_START(2, "待开工"),
    ORDER_EXECUTING(3, "开工中"),
    WAITING_VISIT(4,"待回访"),
    ORDER_COMPLETED(5,"已结单"),

    DELETE(9,"作废");

    private Integer code;
    private String desc;

    private static Map<Integer, String> codeToDesc;

    static {
        codeToDesc = Arrays.stream(OrderState.values()).collect(Collectors.toMap(item -> item.code, item -> item.desc));
    }

    private OrderState(Integer code, String desc) {
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
        return this.desc = desc;
    }
    public static String getDesc(Integer code) {
        return codeToDesc.get(code);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
