package com.kyy.recuperationcourt.common.model.entity.order;

import java.util.ArrayList;

public class DataList<T> {

    ArrayList<T> records;

    String total;

    Integer size;
    Integer current;

    ArrayList<T> orders;

    Boolean searchCount;

    Integer pages;

    public ArrayList<T> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<T> records) {
        this.records = records;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public ArrayList<T> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<T> orders) {
        this.orders = orders;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "records=" + records +
                ", total='" + total + '\'' +
                ", size=" + size +
                ", current=" + current +
                ", orders=" + orders +
                ", searchCount=" + searchCount +
                ", pages=" + pages +
                '}';
    }
}
