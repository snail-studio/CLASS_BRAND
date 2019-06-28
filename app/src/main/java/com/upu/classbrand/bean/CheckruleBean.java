package com.upu.classbrand.bean;

public class CheckruleBean {
    private String rulename;
    private String ruleid;
    private String executime;

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public String getRuleid() {
        return ruleid;
    }

    public void setRuleid(String ruleid) {
        this.ruleid = ruleid;
    }

    public String getExecutime() {
        return executime;
    }

    public void setExecutime(String executime) {
        this.executime = executime;
    }

    public RuleBean getRule() {
        return rule;
    }

    public void setRule(RuleBean rule) {
        this.rule = rule;
    }

    private RuleBean rule;
}
