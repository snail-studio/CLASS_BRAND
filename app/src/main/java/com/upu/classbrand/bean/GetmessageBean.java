package com.upu.classbrand.bean;

import java.util.List;

public class GetmessageBean {
    private List<MessageBean> data;
    private String ServerTime;
    private String msg;

    public List<MessageBean> getData() {
        return data;
    }

    public void setData(List<MessageBean> data) {
        this.data = data;
    }

    public String getServerTime() {
        return ServerTime;
    }

    public void setServerTime(String serverTime) {
        ServerTime = serverTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
}
