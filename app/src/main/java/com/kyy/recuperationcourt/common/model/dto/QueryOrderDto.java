package com.kyy.recuperationcourt.common.model.dto;

public class QueryOrderDto {


    //当前页
    private long current;
    //每页显示条数
    private long size;

    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QueryOrderDto{" +
                "current=" + current +
                ", size=" + size +
                ", userId=" + userId +
                '}';
    }
}
