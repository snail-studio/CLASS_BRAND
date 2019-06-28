package com.upu.classbrand.presenter;

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

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDataInterface {
    @FormUrlEncoded
    @POST("/app/index.php?")
    Observable<Demobean> testdemo(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//versioncontrol/getlatestversion")
    Observable<GetversionBean> getlatestversion(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/login")
    Observable<LoginBean> login(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/checkin")
    Observable<CheckBean> checkin(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/getcheckinfoall")
    Observable<GetcheckinfoBean> getcheckinfoall(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/gethomework")
    Observable<GethomeworkBean> gethomework(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/getnotice")
    Observable<GetnoticeBean> getnotice(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/getstudentlist")
    Observable<GetchildBean> getstudentlist(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/getleaverlist")
    Observable<LeaverBean> getleaverlist(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/getdutylist")
    Observable<DutyBean> getdutylist(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/gethomeworkoutline")
    Observable<GethomeworkoutlineBean> gethomeworkoutline(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/index.php/api//classbrand/getBroadcast")
    Observable<GetbroadcastBean> getBroadcast(@FieldMap Map<String,String> map);

}