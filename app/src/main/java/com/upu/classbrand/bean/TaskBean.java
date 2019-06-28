package com.upu.classbrand.bean;

public class TaskBean {
    private String content;

    public TaskBean(String content, String imgpath1, String imapath2) {
        this.content = content;
        this.imgpath1 = imgpath1;
        this.imapath2 = imapath2;
    }

    private String imgpath1;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgpath1() {
        return imgpath1;
    }

    public void setImgpath1(String imgpath1) {
        this.imgpath1 = imgpath1;
    }

    public String getImapath2() {
        return imapath2;
    }

    public void setImapath2(String imapath2) {
        this.imapath2 = imapath2;
    }

    private String imapath2;
}
