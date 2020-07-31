package com.kyy.recuperationcourt.common.model.entity;

import java.util.Arrays;

public class ResponseObject<T>{

    private String code = "";
    private String msg = "";
    private T data = null;
    private String[] display = null;

    public ResponseObject() {
    }

    public ResponseObject(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseObject(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public ResponseObject(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String[] getDisplay() {
        return this.display;
    }

    public void setDisplay(String[] display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", display=" + Arrays.toString(display) +
                '}';
    }
}
