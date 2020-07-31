package com.kyy.recuperationcourt.common.model.constant.visitorder;

public enum  VisitOrderState {

    NONORDER(0, "无回访单"),
    HASORDER(1, "有回访单");

    private Integer code;
    private String desc;


    private VisitOrderState(Integer code, String desc) {
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

}
