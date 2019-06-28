package com.upu.classbrand.bean;

import java.util.List;

public class GetchildBean {
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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

    private Data data;
    private int code;
    private String msg;
    private String ServerTime;
    public class Data{
        public List<ChildBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildBean> children) {
            this.children = children;
        }

        private List<ChildBean> children;

    }
}
