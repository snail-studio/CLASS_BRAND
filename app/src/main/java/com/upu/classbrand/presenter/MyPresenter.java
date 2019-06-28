package com.upu.classbrand.presenter;

import android.graphics.Bitmap;

import com.upu.classbrand.bean.CheckBean;
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
import com.upu.classbrand.callback.ModelCallBack;
import com.upu.classbrand.listener.GetbroadcastListener;
import com.upu.classbrand.listener.GetchildListener;
import com.upu.classbrand.listener.GetdutyListener;
import com.upu.classbrand.listener.GethomeworkListener;
import com.upu.classbrand.listener.GethomeworkoutlineListener;
import com.upu.classbrand.listener.GetleaverListener;
import com.upu.classbrand.listener.GetnoticeListener;
import com.upu.classbrand.model.AppModel;
import com.upu.classbrand.view.ModelView;

public class MyPresenter {
    private static final String TAG="MyPresenter";
    AppModel appModel=new AppModel();
    ModelView.MyView myView;

    public void setGetchildListener(GetchildListener getchildListener) {
        this.getchildListener = getchildListener;
    }

    public void setGetdutyListener(GetdutyListener getdutyListener) {
        this.getdutyListener = getdutyListener;
    }

    public void setGethomeworkListener(GethomeworkListener gethomeworkListener) {
        this.gethomeworkListener = gethomeworkListener;
    }
    public void setGethomeworkoutlineListener(GethomeworkoutlineListener gethomeworkoutlineListener) {
        this.gethomeworkoutlineListener = gethomeworkoutlineListener;
    }
    public void setGetleaverListener(GetleaverListener getleaverListener) {
        this.getleaverListener = getleaverListener;
    }

    public void setGetnoticeListener(GetnoticeListener getnoticeListener) {
        this.getnoticeListener = getnoticeListener;
    }
    public void setGetbroadcastListener(GetbroadcastListener getbroadcastListener) {
        this.getbroadcastListener = getbroadcastListener;
    }

    private GetchildListener getchildListener;
    private GetdutyListener getdutyListener;
    private GethomeworkListener gethomeworkListener;
    private GethomeworkoutlineListener gethomeworkoutlineListener;
    private GetleaverListener getleaverListener;
    private GetnoticeListener getnoticeListener;
    private GetbroadcastListener getbroadcastListener;


    public MyPresenter(ModelView.MyView myView){
        this.myView=myView;
    }
    public void getVersionInfo(){
        appModel.getVersionInfo(new ModelCallBack.GetVersionCallBack(){
            @Override
            public void success(GetversionBean getversionBean){
                myView.success(getversionBean);
            }
            @Override
            public void failed(Throwable code){
                if (code.getMessage().contains("Use JsonReader.setLenient(true) to accept malformed JSON at")){
                    myView.failed(11000);
                    System.out.println("登录错误："+code);
                }else{
                    myView.failed(10001);
                    System.out.println("登录错误："+code);
                }

            }
        });
    }

    public void Login(String user,String psw){
        appModel.login(user,psw,new ModelCallBack.LoginCallBack(){
            @Override
            public void success(LoginBean loginBean){
                myView.login(loginBean);
            }
            @Override
            public void failed(Throwable code){
                if (code.getMessage().contains("Use JsonReader.setLenient(true) to accept malformed JSON at")){
                    myView.failed(11001);
                    System.out.println("登录错误："+code);
                }else{
                    myView.failed(10002);
                    System.out.println("登录错误："+code);
                }

            }
        });
    }

    public void Checkin(Bitmap img, boolean type, String addr){
        appModel.checkin(img,type,addr,new ModelCallBack.CheckinCallBack(){
            @Override
            public void success(CheckBean checkBean){
                myView.checkin(checkBean);
            }
            @Override
            public void failed(Throwable code){
                    myView.failed(10003);
                    System.out.println("登录错误："+code);
            }
        });
    }

    public void Getcheckinfo(){
        appModel.getcheckinfoall(new ModelCallBack.GetcheckinfoCallBack(){
            @Override
            public void success(GetcheckinfoBean getcheckinfoBean){
                myView.getcheckinfo(getcheckinfoBean);
            }
            @Override
            public void failed(Throwable code){
                myView.failed(10004);
                System.out.println("登录错误："+code);
            }
        });
    }

    public void Gethomeworkoutline(){
        appModel.gethomeworkoutline(new ModelCallBack.GethomeworkoutlineCallBack(){
            @Override
            public void success(GethomeworkoutlineBean gethomeworkBean){
                gethomeworkoutlineListener.onHomeworkoutlineResult(gethomeworkBean);
            }
            @Override
            public void failed(Throwable code){
                gethomeworkListener.onFailure(10012);
            }
        });
    }
    public void Gethomework(String date,String studentid){
        appModel.gethomework(date,studentid,new ModelCallBack.GethomeworkCallBack(){
            @Override
            public void success(GethomeworkBean gethomeworkBean){
                gethomeworkListener.onHomeworkResult(gethomeworkBean);
            }
            @Override
            public void failed(Throwable code){
                gethomeworkListener.onFailure(10007);
            }
        });
    }

    public void Getnotice(){
        appModel.getnotice(new ModelCallBack.GetnoticeCallBack(){
            @Override
            public void success(GetnoticeBean getnoticeBean){
                getnoticeListener.onGetnoticeResult(getnoticeBean);
            }
            @Override
            public void failed(Throwable code){
                getnoticeListener.onFailure(10008);
            }
        });
    }

    public void Getstudentlist(String schoolid,String classid){
        appModel.getstudentlist(schoolid,classid,new ModelCallBack.GetchildCallBack(){
            @Override
            public void success(GetchildBean getchildBean){
                getchildListener.onGetchildResult(getchildBean);
            }
            @Override
            public void failed(Throwable code){
                getchildListener.onFailure(10009);
            }
        });
    }

    public void Getleaverlist(){
        appModel.getleaverlist(new ModelCallBack.GetleaverCallBack(){
            @Override
            public void success(LeaverBean leaverBean){
                getleaverListener.onGetleaverResult(leaverBean);
            }
            @Override
            public void failed(Throwable code){
                getleaverListener.onFailure(10010);
            }
        });
    }

    public void Getdutylist(){
        appModel.getdutylist(new ModelCallBack.GetdutyCallBack(){
            @Override
            public void success(DutyBean dutyBean){
                getdutyListener.onGetdutyResult(dutyBean);
            }
            @Override
            public void failed(Throwable code){
                getdutyListener.onFailure(10011);
            }
        });
    }

    public void Getbroadcast(){
        appModel.getbroadcast(new ModelCallBack.GetbroadcastBack(){
            @Override
            public void success(GetbroadcastBean getbroadcastBean){
                getbroadcastListener.onGetbroadcastResult(getbroadcastBean);
            }
            @Override
            public void failed(Throwable code){
                getbroadcastListener.onFailure(10017);
            }
        });
    }
}
