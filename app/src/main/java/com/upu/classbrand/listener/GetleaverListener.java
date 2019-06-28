package com.upu.classbrand.listener;

import com.upu.classbrand.bean.LeaverBean;

public interface GetleaverListener {
    void onGetleaverResult(LeaverBean leaverBean);
    void onFailure(int code);
}
