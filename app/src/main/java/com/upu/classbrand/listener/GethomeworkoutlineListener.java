package com.upu.classbrand.listener;

import com.upu.classbrand.bean.GethomeworkoutlineBean;

public interface GethomeworkoutlineListener {
    void onHomeworkoutlineResult(GethomeworkoutlineBean gethomeworkoutlineBean);
    void onFailure(int code);
}
