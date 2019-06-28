package com.upu.classbrand.bean;

import java.util.List;

public class GetcheckinfoBean {
    private Data data;
    private int code;
    private String msg;

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

    private String ServerTime;
    public class Data{
        public List<CheckinfoBean> getCheckdetail() {
            return checkdetail;
        }

        public void setCheckdetail(List<CheckinfoBean> checkdetail) {
            this.checkdetail = checkdetail;
        }

        private List<CheckinfoBean> checkdetail;
    }
}
