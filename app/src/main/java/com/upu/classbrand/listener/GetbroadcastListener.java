package com.upu.classbrand.listener;

import com.upu.classbrand.bean.GetbroadcastBean;

public interface GetbroadcastListener {
    void onGetbroadcastResult(GetbroadcastBean getbroadcastBean);
    void onFailure(int code);
}
