package com.upu.classbrand.callback;

import com.upu.classbrand.bean.CheckBean;
import com.upu.classbrand.bean.Demobean;
import com.upu.classbrand.bean.DutyBean;
import com.upu.classbrand.bean.GetbroadcastBean;
import com.upu.classbrand.bean.GetcheckinfoBean;
import com.upu.classbrand.bean.GetchildBean;
import com.upu.classbrand.bean.GethomeworkBean;
import com.upu.classbrand.bean.GethomeworkoutlineBean;
import com.upu.classbrand.bean.GetnoticeBean;
import com.upu.classbrand.bean.GetversionBean;
import com.upu.classbrand.bean.LeaverBean;
import com.upu.classbrand.bean.LoginBean;

public class ModelCallBack {
    public interface DemoCallBack{
        public void success(Demobean demobean);
        public void failed(Throwable code);
    }
    public interface GetVersionCallBack{
        public void success(GetversionBean getversionBean);
        public void failed(Throwable code);
    }

    public interface LoginCallBack{
        public void success(LoginBean loginBean);
        public void failed(Throwable code);
    }

    public interface CheckinCallBack{
        public void success(CheckBean checkBean);
        public void failed(Throwable code);
    }

    public interface GetcheckinfoCallBack{
        public void success(GetcheckinfoBean getcheckinfoBean);
        public void failed(Throwable code);
    }
    public interface GethomeworkCallBack{
        public void success(GethomeworkBean gethomeworkBean);
        public void failed(Throwable code);
    }
    public interface GethomeworkoutlineCallBack{
        public void success(GethomeworkoutlineBean gethomeworkoutlineBean);
        public void failed(Throwable code);
    }
    public interface GetnoticeCallBack{
        public void success(GetnoticeBean getnoticeBean);
        public void failed(Throwable code);
    }
    public interface GetchildCallBack{
        public void success(GetchildBean getchildBean);
        public void failed(Throwable code);
    }
    public interface GetleaverCallBack{
        public void success(LeaverBean leaverBean);
        public void failed(Throwable code);
    }
    public interface GetdutyCallBack{
        public void success(DutyBean dutyBean);
        public void failed(Throwable code);
    }
    public interface GetbroadcastBack{
        public void success(GetbroadcastBean getbroadcastBean);
        public void failed(Throwable code);
    }
}
