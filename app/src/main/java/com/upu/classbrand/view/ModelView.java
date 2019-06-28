package com.upu.classbrand.view;

import com.upu.classbrand.bean.CheckBean;
import com.upu.classbrand.bean.Demobean;
import com.upu.classbrand.bean.GetcheckinfoBean;
import com.upu.classbrand.bean.GetversionBean;
import com.upu.classbrand.bean.LoginBean;

public class ModelView {
    public interface DemoView {
        void success(Demobean demobean);
        void failed(int code);
    }
    public interface MyView{
        void success(GetversionBean getversionBean);
        void login(LoginBean loginBean);
        void checkin(CheckBean checkBean);
        void getcheckinfo(GetcheckinfoBean getcheckinfoBean);
        void failed(int code);
    }
}
