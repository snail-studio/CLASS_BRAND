package com.upu.classbrand.model;

import com.upu.classbrand.tools.getTime;

public class TdefaultRuleE {
    private long day=86400000;
    public String getExecuTime() {
        return execuTime;
    }

    public void setExecuTime(String execuTime) {
        this.execuTime = execuTime;
    }

    private String execuTime;
    private long amtimeinS;
    private long amtimeinT;

    public TdefaultRuleE(long amtimeinS, long amtimeinT, long amtimeleaveS, long amtimeleaveT, long midinS, long midinT, long midleaveS, long midleaveT, long afterinS, long afterinT, long afterleaveS, long afterleaveT,String execuTime) {
        this.execuTime=execuTime;
        this.amtimeinS = amtimeinS;
        this.amtimeinT = amtimeinT;
        this.amtimeleaveS = amtimeleaveS;
        this.amtimeleaveT = amtimeleaveT;
        this.midinS = midinS;
        this.midinT = midinT;
        this.midleaveS = midleaveS;
        this.midleaveT = midleaveT;
        this.afterinS = afterinS;
        this.afterinT = afterinT;
        this.afterleaveS = afterleaveS;
        this.afterleaveT = afterleaveT;
    }

    private long amtimeleaveS;
    private long amtimeleaveT;

    private long midinS;
    private long midinT;

    private long midleaveS;
    private long midleaveT;


    private long afterinS;
    private long afterinT;

    private long afterleaveS;

    public long getAmtimeinS() {
        return amtimeinS;
    }

    public void setAmtimeinS(long amtimeinS) {
        this.amtimeinS = amtimeinS;
    }

    public long getAmtimeinT() {
        return amtimeinT;
    }

    public void setAmtimeinT(long amtimeinT) {
        this.amtimeinT = amtimeinT;
    }

    public long getAmtimeleaveS() {
        return amtimeleaveS;
    }

    public void setAmtimeleaveS(long amtimeleaveS) {
        this.amtimeleaveS = amtimeleaveS;
    }

    public long getAmtimeleaveT() {
        return amtimeleaveT;
    }

    public void setAmtimeleaveT(long amtimeleaveT) {
        this.amtimeleaveT = amtimeleaveT;
    }

    public long getMidinS() {
        return midinS;
    }

    public void setMidinS(long midinS) {
        this.midinS = midinS;
    }

    public long getMidinT() {
        return midinT;
    }

    public void setMidinT(long midinT) {
        this.midinT = midinT;
    }

    public long getMidleaveS() {
        return midleaveS;
    }

    public void setMidleaveS(long midleaveS) {
        this.midleaveS = midleaveS;
    }

    public long getMidleaveT() {
        return midleaveT;
    }

    public void setMidleaveT(long midleaveT) {
        this.midleaveT = midleaveT;
    }

    public long getAfterinS() {
        return afterinS;
    }

    public void setAfterinS(long afterinS) {
        this.afterinS = afterinS;
    }

    public long getAfterinT() {
        return afterinT;
    }

    public void setAfterinT(long afterinT) {
        this.afterinT = afterinT;
    }

    public long getAfterleaveS() {
        return afterleaveS;
    }

    public void setAfterleaveS(long afterleaveS) {
        this.afterleaveS = afterleaveS;
    }

    public long getAfterleaveT() {
        return afterleaveT;
    }

    public void setAfterleaveT(long afterleaveT) {
        this.afterleaveT = afterleaveT;
    }

    private long afterleaveT;
    public void updateTime(){
        this.amtimeinS += day;
        this.amtimeinT += day;
        this.amtimeleaveS += day;
        this.amtimeleaveT += day;
        this.midinS += day;
        this.midinT += day;
        this.midleaveS += day;
        this.midleaveT += day;
        this.afterinS += day;
        this.afterinT += day;
        this.afterleaveS += day;
        this.afterleaveT += day;
    }
    public boolean isTimeOk(){
        if (execuTime.contains(getTime.getWeek())){
            if(System.currentTimeMillis()>=amtimeinS&&amtimeinT>=System.currentTimeMillis()){
                return true;
            }else{
                if (System.currentTimeMillis()>=amtimeleaveS&&amtimeleaveT>=System.currentTimeMillis()){
                    return true;
                }else{
                    if (System.currentTimeMillis()>=midinS&&midinT>=System.currentTimeMillis()){
                        return true;
                    }else{
                        if (System.currentTimeMillis()>=midleaveS&&midleaveT>=System.currentTimeMillis()){
                            return true;
                        }else{
                            if (System.currentTimeMillis()>=afterinS&&afterinT>=System.currentTimeMillis()){
                                return true;
                            }else{
                                if (System.currentTimeMillis()>=afterleaveS&&afterleaveT>=System.currentTimeMillis()){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }else{
            return false;
        }
    }
}
