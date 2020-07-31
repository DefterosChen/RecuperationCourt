package com.kyy.recuperationcourt.common.model.dto;

import com.kyy.recuperationcourt.common.model.entity.elder.UserElder;

public class QueryUserElderDto {

    //当前页
    private long current;
    //每页显示条数
    private long size;

    private UserElder userElder;


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

    public UserElder getUserElder() {
        return userElder;
    }

    public void setUserElder(UserElder userElder) {
        this.userElder = userElder;
    }

    @Override
    public String toString() {
        return "QueryUserElderDto{" +
                "current=" + current +
                ", size=" + size +
                ", userElder=" + userElder +
                '}';
    }
}
