package com.upu.classbrand.bean;

import java.util.List;

public class GethomeworkBean {
    private List<HomeworkBean> data;
    private int code;
    private String msg;

    public List<HomeworkBean> getData() {
        return data;
    }

    public void setData(List<HomeworkBean> data) {
        this.data = data;
    }

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

    public String getServerTime() {
        return ServerTime;
    }

    public void setServerTime(String serverTime) {
        ServerTime = serverTime;
    }

    private String ServerTime;
}
