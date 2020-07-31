package com.kyy.recuperationcourt.common.model.dto;

public class QueryMemberFamilyDto {

    //当前页
    private long current;
    //每页显示条数
    private long size;

    private Integer userElderId;

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Integer getUserElderId() {
        return userElderId;
    }

    public void setUserElderId(Integer userElderId) {
        this.userElderId = userElderId;
    }
}
