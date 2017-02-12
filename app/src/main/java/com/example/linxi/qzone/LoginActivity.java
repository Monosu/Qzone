package com.example.linxi.qzone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linxi.qzone.biz.UserInfoBiz;
import com.example.linxi.qzone.model.UserInfo;

/**
 * Created by linxi on 2017/1/9.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText username,password;
    private Button loginBtn;
    private TextView returnPsd,register;
    private ProgressDialog oDialog;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        loginBtn= (Button) findViewById(R.id.login);
        returnPsd= (TextView) findViewById(R.id.returnpsd);
        register= (TextView) findViewById(R.id.register);
        loginBtn.setOnClickListener(this);
        returnPsd.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String usernameStr=username.getText().toString();
                String passwordStr=password.getText().toString();
                if (!TextUtils.isEmpty(usernameStr)&&usernameStr.length()<3){
                    Toast.makeText(this,"用户名错误，请重新输入",Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.isEmpty(passwordStr)&&passwordStr.length()<3){
                    Toast.makeText(this,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }else {
                    UserInfo oInfo=new UserInfo();
                    oInfo.setLoginName(usernameStr);
                    oInfo.setPassWord(passwordStr);
                    oDialog=new ProgressDialog(LoginActivity.this);
                    oDialog.setMessage("正在登录中...");
                    oDialog.setCancelable(false);
                    oDialog.show();
                    new UserLoginAsy().execute(oInfo);
                }
                break;
            case R.id.register:
                Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.returnpsd:
                Intent intent1=new Intent(LoginActivity.this,ReturnpsdActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }

    }

    private class UserLoginAsy extends AsyncTask<UserInfo,Void,Boolean>{

        @Override
        protected Boolean doInBackground(UserInfo... params) {
            //return UserInfoBiz.userLogin(params[0]);
            return true;
        }
        //上步执行的结果返回至aBoolean
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean){
                AppApplication.toastMessage("登录成功!");
                shared=getSharedPreferences("qq",MODE_PRIVATE);
                editor=shared.edit();
                editor.putBoolean("islogin",true);
                editor.commit();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            oDialog.dismiss();
            super.onPostExecute(aBoolean);
        }
    }
}
