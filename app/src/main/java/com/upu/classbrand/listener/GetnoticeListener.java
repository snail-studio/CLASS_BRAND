package com.upu.classbrand.listener;

import com.upu.classbrand.bean.GetnoticeBean;

public interface GetnoticeListener {
    void onGetnoticeResult(GetnoticeBean getnoticeBean);
    void onFailure(int code);
}
