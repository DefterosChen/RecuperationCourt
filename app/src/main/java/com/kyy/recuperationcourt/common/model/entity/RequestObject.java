package com.kyy.recuperationcourt.common.model.entity;

public class RequestObject<T> {

    private String appKey = "string";

    private String version = "string";

    private T data;

    public RequestObject() {
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestObject{" +
                "appKey='" + appKey + '\'' +
                ", version='" + version + '\'' +
                ", data=" + data +
                '}';
    }
}
