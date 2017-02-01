package com.example.linxi.qzone.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.SimpleTimeZone;

/**
 * Created by linxi on 2017/2/1.
 */

public class UnityCommon {
    /**
     * 将日期换成指定格式,默认为yyyy-MM-dd hh:mm:ss
     */
    public static String dateFormat(Date date){
        return dateFormat(date,"");
    }

    private static String dateFormat(Date date, String s) {
        String result="";
        if (date!=null){
            String format="yyyy-MM-dd hh:mm:ss";
            if (s!=null&&s.length()>0){
                format=s;
            }
            SimpleDateFormat oDateFormat=new SimpleDateFormat(format);
            result=oDateFormat.format(date);
        }
        return result;
    }
    /**
     * 将指定字符串转为Date类型
     */
    public static Date stringParseDate(String date,String s){
        Date result=null;
        if (!TextUtils.isEmpty(date)){
            String format="yyyy-MM-dd hh:mm:ss";
            if (s!=null&&s.length()>0){
                format=s;
            }
            SimpleDateFormat oDateFormat=new SimpleDateFormat(format);
            try {
                result=oDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static Date stringParseDate(String date){
        return stringParseDate(date,"");
    }
    /**
     * 获取时间格式的文件名
     */
    public static String getDateFileName(){
        int randomNum= (int) (Math.random()*1000000);
        return dateFormat(new Date(),"yyyyMMddhhmmss")+String.valueOf(randomNum);
    }

    /**
     * 将String类型转为int
     * @param value
     *              要转的值
     * @param defaultValue
     *              默认值，转换失败返回原值
     * @return
     *          num
     */
    public static int getIntOf(String value,int defaultValue){

        int num=defaultValue;
        if (value!=null&&!value.equals("")&&value.length()>0){
            try{
                num=Integer.parseInt(value);
            }catch (Exception e){
                num=defaultValue;
            }

        }
        return num;
    }
    public static int getIntOf(String value){
        return getIntOf(value,0);
    }

    /**
     * 将Object类型转换为int
     * @param value
     * @param defaultValue
     *
     * @return result
     *
     */
    public static int getIntOf(Object value,int defaultValue){
        int num=defaultValue;
        if (value!=null){
            num=getIntOf(value.toString(),defaultValue);
        }
        return num;
    }
    public static int getIntOf(Object value){
        return getIntOf(value.toString(),0);
    }
    public static boolean isConnectionWorked(Context context){
        boolean result=false;
        try {
            ConnectivityManager oManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (oManager!=null){
                NetworkInfo oNetworkInfo=oManager.getActiveNetworkInfo();
                if (oNetworkInfo.isConnected()){
                    if (oNetworkInfo.getState()==NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            //// TODO: 2017/2/1 handle exception
        }
        return result;
    }
    public static String urlEncode(String value){
        String result=value;
        try{
            result= URLEncoder.encode(value,"UTF-8");
        }catch (Exception e){
            result=value;
        }
        return result;
    }

}
