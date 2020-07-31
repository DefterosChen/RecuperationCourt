package com.kyy.recuperationcourt.common.model.entity.message.inter;

public class MessageData {


    /**
     * code : 0
     * msg : null
     * data : true
     * ok : true
     */

    private int code;
    private String msg;
    private boolean data;
    private boolean ok;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
