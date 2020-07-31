package com.kyy.recuperationcourt.common.model.constant.serviceitem;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public enum ServiceItemType {

    ITEM_HOUR(0, "按小时"),
    ITEM_NUM(1, "按次数");

    private Integer code;
    private String desc;

    private static Map<Integer, String> codeToDesc;

    static {
        codeToDesc = Arrays.stream(ServiceItemType.values()).collect(Collectors.toMap(item -> item.code, item -> item.desc));
    }


    private ServiceItemType(Integer code, String desc) {
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

    public static String getDesc(Integer code) {
        return codeToDesc.get(code);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map enumToMap() {
        ServiceItemType[] statuses = ServiceItemType.class.getEnumConstants();
        Map map = new HashMap();
        for (ServiceItemType status: statuses){
            map.put(status.getCode(),status.getDesc());
        }
        return map;
    }

    public static List<String> enumToList() {
        ServiceItemType[] statuses = ServiceItemType.class.getEnumConstants();

        List<String> list = new ArrayList<>();
        for (ServiceItemType status: statuses){
            list.add(status.getDesc());
        }
        return list;
    }

    public static List<Integer> enumToListCode() {
        ServiceItemType[] statuses = ServiceItemType.class.getEnumConstants();

        List<Integer> list = new ArrayList<>();
        for (ServiceItemType status: statuses){
            list.add(status.getCode());
        }
        return list;
    }
}
