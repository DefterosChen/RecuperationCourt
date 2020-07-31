package com.kyy.recuperationcourt.common.model.constant;



import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RequiresApi(api = Build.VERSION_CODES.N)
public enum SexType {
        SERCRET(0,"保密"),
        MALE(1,"男"),
        FEMALE(2,"女"),
        OTHER(3,"其他") // 其他
        ;
        Integer code;
        String name;

        private static Map<Integer, String> codeToName;

        static {
            codeToName = Arrays.stream(SexType.values()).collect(Collectors.toMap(item -> item.code, item -> item.name));
        }

        SexType(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getName(Integer code) {
            return codeToName.get(code);
        }

        public static Map enumToMap() {
            SexType[] statuses = SexType.class.getEnumConstants();
            Map map = new HashMap();
            for (SexType status: statuses){
                map.put(status.getCode(),status.getName());
            }
            return map;
        }
    }