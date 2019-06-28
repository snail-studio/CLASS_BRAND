package com.upu.classbrand.listener;

import com.upu.classbrand.bean.DutyBean;

public interface GetdutyListener {
    void onGetdutyResult(DutyBean dutyBean);
    void onFailure(int code);
}
