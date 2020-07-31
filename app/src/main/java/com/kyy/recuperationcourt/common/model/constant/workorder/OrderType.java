package com.kyy.recuperationcourt.common.model.constant.workorder;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public enum  OrderType {

    ORDER_NORMAL(1, "普通工单"),
    ORDER_SOS(2, "紧急工单");

    private Integer code;
    private String desc;

    private static Map<Integer, String> codeToDesc;

    static {
        codeToDesc = Arrays.stream(OrderType.values()).collect(Collectors.toMap(item -> item.code, item -> item.desc));
    }

    private OrderType(Integer code, String desc) {
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

    public static Map enumToMap() {
        OrderType[] statuses = OrderType.class.getEnumConstants();
        Map map = new HashMap();
        for (OrderType status: statuses){
            map.put(status.getCode(),status.getDesc());
        }
        return map;
    }

    public static List<String> enumToList() {
        OrderType[] statuses = OrderType.class.getEnumConstants();

        List<String> list = new ArrayList<>();
        for (OrderType status: statuses){
            list.add(status.getDesc());
        }
        return list;
    }

    public static List<Integer> enumToListCode() {
        OrderType[] statuses = OrderType.class.getEnumConstants();

        List<Integer> list = new ArrayList<>();
        for (OrderType status: statuses){
            list.add(status.getCode());
        }
        return list;
    }
}
