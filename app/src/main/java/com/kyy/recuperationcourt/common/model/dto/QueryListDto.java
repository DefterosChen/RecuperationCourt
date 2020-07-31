package com.kyy.recuperationcourt.common.model.dto;

public class QueryListDto {

    //当前页
    private long current;
    //每页显示条数
    private long size;

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

    @Override
    public String toString() {
        return "QueryListDto{" +
                "current=" + current +
                ", size=" + size +
                '}';
    }
}
