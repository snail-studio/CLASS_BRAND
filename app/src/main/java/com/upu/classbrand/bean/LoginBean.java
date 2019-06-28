package com.upu.classbrand.bean;

import java.util.List;

public class LoginBean {
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
        public List<ClassinfoBean> getClassinfo() {
            return classinfo;
        }

        public void setClassinfo(List<ClassinfoBean> classinfo) {
            this.classinfo = classinfo;
        }

        private List<ClassinfoBean> classinfo;

        public String getSchoolid() {
            return schoolid;
        }

        public void setSchoolid(String schoolid) {
            this.schoolid = schoolid;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        private String schoolid;
        private String schoolname;

        private SdefaultruleBean sdefaultrule;
        private TdefaultruleBean tdefaultrule;

        public SdefaultruleBean getSdefaultrule() {
            return sdefaultrule;
        }

        public void setSdefaultrule(SdefaultruleBean sdefaultrule) {
            this.sdefaultrule = sdefaultrule;
        }

        public TdefaultruleBean getTdefaultrule() {
            return tdefaultrule;
        }

        public void setTdefaultrule(TdefaultruleBean tdefaultrule) {
            this.tdefaultrule = tdefaultrule;
        }

        public List<CheckruleBean> getCheckrule() {
            return checkrule;
        }

        public void setCheckrule(List<CheckruleBean> checkrule) {
            this.checkrule = checkrule;
        }

        private List<CheckruleBean> checkrule;
    }
}
