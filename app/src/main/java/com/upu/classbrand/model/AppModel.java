package com.upu.classbrand.model;

import android.graphics.Bitmap;

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
import com.upu.classbrand.callback.ModelCallBack;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.presenter.GetDataInterface;
import com.upu.classbrand.tools.getTime;
import com.upu.classbrand.ui.Base64util;
import com.upu.classbrand.util.RetrofitUnitl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class AppModel {
    public void Test(String param1,String param2,final ModelCallBack.DemoCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid","6");
        map.put("signId","0014178477");
        map.put("signTime", "2019-04-19 15:10:11");
        map.put("macid","11223344");
        map.put("picurl","");

        map.put("i","2");
        map.put("c","entry");
        map.put("do","checkxz");
        map.put("m","fm_jiaoyu");
        map.put("mactype","other");
        map.put("op","check");

        RetrofitUnitl.getInstance(publicParam.ZENKA_URL,ok)
                .setCreate(GetDataInterface.class)
                .testdemo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Demobean>(){
                    @Override
                    public void accept(Demobean demobean) throws Exception {
                        callBack.success(demobean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }
    public void getVersionInfo(final ModelCallBack.GetVersionCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("appname",publicParam.appname);

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .getlatestversion(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetversionBean>(){
                    @Override
                    public void accept(GetversionBean getversionBean) throws Exception {
                        callBack.success(getversionBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void login(String user,String psw,final ModelCallBack.LoginCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("user",user);
        map.put("psw",psw);

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>(){
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        callBack.success(loginBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void gethomeworkoutline(final ModelCallBack.GethomeworkoutlineCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",publicParam.schoolid);
        map.put("classid",publicParam.classid);
        map.put("date",getTime.getData());


        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .gethomeworkoutline(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GethomeworkoutlineBean>(){
                    @Override
                    public void accept(GethomeworkoutlineBean gethomeworkoutlineBean) throws Exception {
                        callBack.success(gethomeworkoutlineBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void gethomework(String date,String studentid,final ModelCallBack.GethomeworkCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",publicParam.schoolid);
        map.put("classid",publicParam.classid);
        map.put("date",date);
        map.put("childid",studentid);

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .gethomework(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GethomeworkBean>(){
                    @Override
                    public void accept(GethomeworkBean gethomeworkBean) throws Exception {
                        callBack.success(gethomeworkBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void getnotice(final ModelCallBack.GetnoticeCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",publicParam.schoolid);
        map.put("classid",publicParam.classid);
        map.put("date",getTime.getData());

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .getnotice(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetnoticeBean>(){
                    @Override
                    public void accept(GetnoticeBean getnoticeBean) throws Exception {
                        callBack.success(getnoticeBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void getstudentlist(String schoolid,String classid,final ModelCallBack.GetchildCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",schoolid);
        map.put("classid",classid);

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .getstudentlist(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetchildBean>(){
                    @Override
                    public void accept(GetchildBean getchildBean) throws Exception {
                        callBack.success(getchildBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void getleaverlist(final ModelCallBack.GetleaverCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",publicParam.schoolid);
        map.put("classid",publicParam.classid);
        map.put("date",getTime.getData());

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .getleaverlist(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LeaverBean>(){
                    @Override
                    public void accept(LeaverBean leaverBean) throws Exception {
                        callBack.success(leaverBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void getdutylist(final ModelCallBack.GetdutyCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",publicParam.schoolid);
        map.put("classid",publicParam.classid);
        map.put("date",getTime.getData());

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .getdutylist(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DutyBean>(){
                    @Override
                    public void accept(DutyBean dutyBean) throws Exception {
                        callBack.success(dutyBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void getbroadcast(final ModelCallBack.GetbroadcastBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",publicParam.schoolid);
        if (!publicParam.classid.equals("")){
            map.put("classid",publicParam.classid);
        }
        map.put("date",getTime.getData());

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .getBroadcast(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetbroadcastBean>(){
                    @Override
                    public void accept(GetbroadcastBean getbroadcastBean) throws Exception {
                        callBack.success(getbroadcastBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void checkin(Bitmap img, boolean type, String addr, final ModelCallBack.CheckinCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("image", Base64util.BitmapToString(img));
        map.put("groupid",publicParam.schoolid);
        if (type){
            map.put("type","1");
        }else{
            map.put("type","0");
        }

        map.put("checktime", getTime.time());
        map.put("way","0");
        map.put("addr",addr);

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .checkin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CheckBean>(){
                    @Override
                    public void accept(CheckBean checkBean) throws Exception {
                        callBack.success(checkBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }

    public void getcheckinfoall(final ModelCallBack.GetcheckinfoCallBack callBack){
        OkHttpClient ok=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Map<String,String> map=new HashMap<>();
        map.put("schoolid",publicParam.schoolid);
        map.put("type","0");
        map.put("classid",publicParam.classid);
        map.put("date",getTime.getData());

        RetrofitUnitl.getInstance(publicParam.BASE_URL,ok)
                .setCreate(GetDataInterface.class)
                .getcheckinfoall(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetcheckinfoBean>(){
                    @Override
                    public void accept(GetcheckinfoBean getcheckinfoBean) throws Exception {
                        callBack.success(getcheckinfoBean);
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        callBack.failed(throwable);
                    }
                });
    }
}
