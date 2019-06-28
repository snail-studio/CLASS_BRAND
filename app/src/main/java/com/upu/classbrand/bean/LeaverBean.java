package com.upu.classbrand.bean;

import java.util.List;

public class LeaverBean {
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
        public List<Leavers> getLeavers() {
            return leavers;
        }

        public void setLeavers(List<Leavers> leavers) {
            this.leavers = leavers;
        }

        private List<Leavers> leavers;
    }
    public class Leavers{
        private String leaver;

        public String getLeaver() {
            return leaver;
        }

        public void setLeaver(String leaver) {
            this.leaver = leaver;
        }

        public String getLeaverid() {
            return leaverid;
        }

        public void setLeaverid(String leaverid) {
            this.leaverid = leaverid;
        }

        private String leaverid;

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        private String cause;

    }
}
