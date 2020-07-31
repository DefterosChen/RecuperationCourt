package com.kyy.recuperationcourt.common.model.dto;

import com.kyy.recuperationcourt.common.model.entity.order.ServiceItem;

public class QueryServiceItemDto {

    //当前页
    private long current;
    //每页显示条数
    private long size;

    private ServiceItem serviceItem;


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


    public ServiceItem getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

    @Override
    public String toString() {
        return "QueryServiceItemDto{" +
                "current=" + current +
                ", size=" + size +
                ", serviceItem=" + serviceItem +
                '}';
    }
}
