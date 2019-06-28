package com.upu.classbrand.bean;

import java.util.List;

public class GethomeworkoutlineBean {
    private String ServerTime;
    private List<HomeworkoutlineBean> data;

    public String getServerTime() {
        return ServerTime;
    }

    public void setServerTime(String serverTime) {
        ServerTime = serverTime;
    }

    public List<HomeworkoutlineBean> getData() {
        return data;
    }

    public void setData(List<HomeworkoutlineBean> data) {
        this.data = data;
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

    private String msg;
    private int code;
}
