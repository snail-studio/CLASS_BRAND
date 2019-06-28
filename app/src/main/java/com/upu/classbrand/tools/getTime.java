package com.upu.classbrand.tools;

import android.text.format.DateFormat;

import com.upu.classbrand.common.publicParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getTime {
    private static long curtime=0;
    public static String time(){
        long sysTime = getCurtime();
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss", sysTime);
        return sysTimeStr.toString();
    }
    private static String getTimeStr(int index){
        String time="";
        long sysTime = getCurtime();
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss", sysTime);
        time=sysTimeStr.toString();
        time=time.substring(0,11)+index+":00:00";
//        switch (index){
//            case 10:
//                time=time.substring(0,11)+"10"+":00:00";
//                break;
//            case 15:
//                time=time.substring(0,11)+"15"+":00:00";
//                break;
//            case 19:
//                time=time.substring(0,11)+"19"+":00:00";
//                break;
//            default:
//                break;
//        }
        return time;
    }
    public static String getData(){
        long sysTime = getCurtime();
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd", sysTime);
        String time=sysTimeStr.toString();
        return time;
    }
    private static String getTimeStr(String str){
        String time="";
        time=getData()+" "+str;
        return time;
    }
    public static long calTime(String strTime){
        try {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(strTime);
            return date.getTime();
        }catch (Exception e) {
            return 0;
        }
    }

    public static long getLongtime(int hours){
        return  calTime(getTimeStr(hours));
    }
    public static long getLongtime(String time){
        return  calTime(getTimeStr(time));
    }
    public static boolean isAmSettime(long time){
        if (getLongtime(publicParam.amtimeIn)<time&&time<getLongtime(publicParam.amtimeLeave)){
            return  true;
        }else{
            return false;
        }
    }
    public static boolean isPmSettime(long time){
        if (getLongtime(publicParam.pmtimeIn)<time&&time<getLongtime(publicParam.pmtimeLeave)){
            return  true;
        }else{
            return false;
        }
    }
    public static long getCurtime(){
        curtime=System.currentTimeMillis()+publicParam.OFFSET;
        return curtime;
    }
    public static int getHour(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }
    public static long getStime(String time){
        if(time!=null){
            String[] strarray=time.split("-");
            if (strarray.length==2){
                return getLongtime(strarray[0]);
            }else{
                return getLongtime("00:00:00");
            }
        }else{
            return getLongtime("00:00:00");
        }


    }
    public static long getTtime(String time){
        if(time!=null){
            String[] strarray=time.split("-");
            if (strarray.length==2){
                return getLongtime(strarray[1]);
            }else{
                return getLongtime("00:00:00");
            }
        }else{
            return getLongtime("00:00:00");
        }
    }
    public static String getWeek(){
        Calendar c = Calendar.getInstance();
        int week=c.get(Calendar.DAY_OF_WEEK);
        switch (week){
            case 1:
                return "0";
            case 2:
                return "1";
            case 3:
                return "2";
            case 4:
                return "3";
            case 5:
                return "4";
            case 6:
                return "5";
            default:
                return "6";
        }
    }
    public static String getstrTime(long milSecond){
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
