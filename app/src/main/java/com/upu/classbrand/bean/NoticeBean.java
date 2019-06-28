package com.upu.classbrand.bean;

import java.util.List;

public class NoticeBean {
    private int noticeid;
    private String noticetitle;
    private String noticecontent;

    public int getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(int noticeid) {
        this.noticeid = noticeid;
    }

    public String getNoticetitle() {
        return noticetitle;
    }

    public void setNoticetitle(String noticetitle) {
        this.noticetitle = noticetitle;
    }

    public String getNoticecontent() {
        return noticecontent;
    }

    public void setNoticecontent(String noticecontent) {
        this.noticecontent = noticecontent;
    }

    public List<String> getNoticeimage() {
        return noticeimage;
    }

    public void setNoticeimage(List<String> noticeimage) {
        this.noticeimage = noticeimage;
    }

    private List<String> noticeimage;

}
