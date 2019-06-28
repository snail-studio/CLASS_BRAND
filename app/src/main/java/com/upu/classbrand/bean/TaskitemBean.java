package com.upu.classbrand.bean;

import java.util.List;

public class TaskitemBean {
    public TaskitemBean(String childname, List<TaskBean> tasklist) {
        this.childname = childname;
        this.tasklist = tasklist;
    }

    private String childname;

    public String getChildname() {
        return childname;
    }

    public void setChildname(String classname) {
        this.childname = classname;
    }

    public List<TaskBean> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<TaskBean> tasklist) {
        this.tasklist = tasklist;
    }

    private List<TaskBean> tasklist;

}
