package com.upu.classbrand.listener;

import com.upu.classbrand.bean.GethomeworkBean;

public interface GethomeworkListener {
    void onHomeworkResult(GethomeworkBean gethomeworkBean);
    void onFailure(int code);
}
