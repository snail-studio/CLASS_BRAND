package com.upu.classbrand.bean;

public class ChildlistBean {
    private String iconpath;

    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid;
    }

    private String childid;

    public ChildlistBean(String childid,String iconpath, String childname,String type) {
        this.childid=childid;
        this.iconpath = iconpath;
        this.childname = childname;
        this.type=type;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    private String childname;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
}
