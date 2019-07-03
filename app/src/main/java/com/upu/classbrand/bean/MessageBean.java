package com.upu.classbrand.bean;

public class MessageBean {
    private int messageid;
    private int isreply;
    private String messagetime;

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public int getIsreply() {
        return isreply;
    }

    public void setIsreply(int isreply) {
        this.isreply = isreply;
    }

    public String getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(String messagetime) {
        this.messagetime = messagetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

}
