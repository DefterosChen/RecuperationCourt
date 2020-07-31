package com.kyy.recuperationcourt.common.model.constant;

public enum  CompanyType {

    HEADQUARTERS(0, "总公司"),
    DISTRICTAGENT(1, "区级代理"),
    GENERALAGENT(2,"普通代理"),
    ;

    private Integer code;
    private String desc;

    private CompanyType(Integer code, String desc) {
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
