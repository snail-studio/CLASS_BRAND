package com.upu.classbrand.bean;

public class GettaskBean {
    private int code;
    private String msg;
    private TaskitemBean data;

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

    public TaskitemBean getData() {
        return data;
    }

    public void setData(TaskitemBean data) {
        this.data = data;
    }

    public String getServerTime() {
        return ServerTime;
    }

    public void setServerTime(String serverTime) {
        ServerTime = serverTime;
    }

    private String ServerTime;
}
