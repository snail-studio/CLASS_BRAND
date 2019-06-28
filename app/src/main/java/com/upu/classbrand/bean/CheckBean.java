package com.upu.classbrand.bean;

public class CheckBean {
    private String msg;
    private int code;

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

    public String getServerTime() {
        return ServerTime;
    }

    public void setServerTime(String serverTime) {
        ServerTime = serverTime;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private String ServerTime;
    private Data data;
    public class Data{
        private String username;
        private String schoolname;
        private String classname;
        private String childicon;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getChildicon() {
            return childicon;
        }

        public void setChildicon(String childicon) {
            this.childicon = childicon;
        }

        public String getChildid() {
            return childid;
        }

        public void setChildid(String childid) {
            this.childid = childid;
        }

        private String childid;
    }
}
