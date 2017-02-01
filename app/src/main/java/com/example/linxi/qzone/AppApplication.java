package com.example.linxi.qzone;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.linxi.qzone.common.DBHelper;
import com.example.linxi.qzone.common.SysConfig;
import com.example.linxi.qzone.common.UnityCommon;

/**
 * Created by linxi on 2017/1/9.
 */

public class AppApplication extends Application {
    private static Handler oHandler=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x123){
                Toast.makeText(context,msg.obj.toString(),Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    private  static Context context=null;
    @Override
    public void onCreate() {
        context=getApplicationContext();
        SysConfig.dbHelper=new DBHelper(context);
        super.onCreate();
    }
    public static Context getContext(){
        return context;
    }
    //定义全局静态的toastmassage方法，所有类都可以通过此方法弹出消息提示，而不需要通过传递activity来实现
    public static void toastMessage(String message){
        if (!TextUtils.isEmpty(message)){
            Message oMessage=new Message();
            oMessage.what=0x123;
            oMessage.obj=message;
            oHandler.sendMessage(oMessage);

        }
    }

    public static boolean isWork(){
        if (UnityCommon.isConnectionWorked(context)){
            return true;
        }else{
            toastMessage("网络连接失败，请检查网络");
            return false;
        }

    }
}
