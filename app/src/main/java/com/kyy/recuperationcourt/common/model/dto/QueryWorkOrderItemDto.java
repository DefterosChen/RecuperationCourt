package com.kyy.recuperationcourt.common.model.dto;

public class QueryWorkOrderItemDto {


    //当前页
    private long current;
    //每页显示条数
    private long size;

    private String order_id;

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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "QueryWorkOrderItemDto{" +
                "current=" + current +
                ", size=" + size +
                ", order_id='" + order_id + '\'' +
                '}';
    }
}
