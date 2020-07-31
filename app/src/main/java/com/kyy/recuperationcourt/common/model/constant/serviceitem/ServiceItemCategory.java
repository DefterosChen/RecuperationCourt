package com.kyy.recuperationcourt.common.model.constant.serviceitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ServiceItemCategory {

    HOUSEKEEPING(1, "居家服务项"),
    RESTAURANT(2, "餐饮服务项");

    private Integer code;
    private String desc;



    private ServiceItemCategory(Integer code, String desc) {
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

    public static Map enumToMap() {
        ServiceItemCategory[] statuses = ServiceItemCategory.class.getEnumConstants();
        Map map = new HashMap();
        for (ServiceItemCategory status : statuses) {
            map.put(status.getCode(), status.getDesc());
        }
        return map;
    }


    public static List<String> enumToList() {
        ServiceItemCategory[] statuses = ServiceItemCategory.class.getEnumConstants();

        List<String> list = new ArrayList<>();
        for (ServiceItemCategory status : statuses) {
            list.add(status.getDesc());
        }
        return list;
    }

    public static List<Integer> enumToListCode() {
        ServiceItemCategory[] statuses = ServiceItemCategory.class.getEnumConstants();

        List<Integer> list = new ArrayList<>();
        for (ServiceItemCategory status : statuses) {
            list.add(status.getCode());
        }
        return list;
    }
}
