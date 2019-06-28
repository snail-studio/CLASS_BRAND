package com.upu.classbrand.listener;

import com.upu.classbrand.bean.GetchildBean;

public interface GetchildListener {
    void onGetchildResult(GetchildBean getchildBean);
    void onFailure(int code);
}
