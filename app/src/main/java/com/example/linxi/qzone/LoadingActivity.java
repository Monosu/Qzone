package com.example.linxi.qzone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.linxi.qzone.common.SysConfig;

/**
 * Created by linxi on 2017/1/9.
 */

public class LoadingActivity extends Activity {
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private Handler oHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x12){
                Intent oIntent=new Intent(LoadingActivity.this,MainActivity.class);
                startActivity(oIntent);
            }else if (msg.what==0x13){
                Intent oIntent=new Intent(LoadingActivity.this,LoginActivity.class);
                startActivity(oIntent);
            }
            finish();
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    shared=getSharedPreferences("qq",MODE_PRIVATE);

                    if (SysConfig.loginUserInfo==null||!(shared.getBoolean("islogin",true))){
                        oHandler.sendEmptyMessage(0x13);
                    }else {
                        oHandler.sendEmptyMessage(0x12);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
