package com.upu.classbrand.bean;

public class BroadcastBean {
    public BroadcastBean(int times, String classid, String content) {
        this.times = times;
        this.classid = classid;
        this.content = content;
    }

    private int times;
    private String classid;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public void broad(){
        times--;
    }
}
