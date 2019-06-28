package com.upu.classbrand.common;

import com.upu.classbrand.bean.ChildlistBean;
import com.upu.classbrand.bean.ClassinfoBean;
import com.upu.classbrand.model.RuleTableE;
import com.upu.classbrand.model.SdefaultRuleE;
import com.upu.classbrand.model.TdefaultRuleE;

import java.util.ArrayList;
import java.util.List;

public class publicParam {
    public static String ZENKA_URL="http://weixin.appzenka.com/";
    public static String BASE_URL="http://jytest.upuai.com/";
    public static String amtimeIn;
    public static String amtimeLeave;
    public static String pmtimeIn;
    public static String pmtimeLeave;
    public static long OFFSET=0;
    public static String deviceid;
    public static String schoolid;
    public static String schoolname;
    public static String classid="0";
    public static String classname;
    public static String schoolidW;
    public static String suffix;
    public static String user;
    public static String psw;
    public static int itemindex=-1;
    public static boolean recordpsw=false;
    public static boolean isdebug=true;
    public static List<ClassinfoBean> classes=new ArrayList<>();
    public static String appname="classbrand";
    public static List<RuleTableE> ruleTableEs=new ArrayList<>();
    public static SdefaultRuleE sdefaultRuleE;
    public static TdefaultRuleE tdefaultRuleE;
    public static List<ChildlistBean> children=new ArrayList<>();
    public static String Version="V0.1.2019305";
}
