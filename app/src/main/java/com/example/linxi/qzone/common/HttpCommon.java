package com.example.linxi.qzone.common;


import com.example.linxi.qzone.AppApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import static com.example.linxi.qzone.common.SysConfig.serverUrl;

/**
 * Created by linxi on 2017/2/1.
 */

public class HttpCommon {
    private static final int REQUEST_TIMEOUT=5*1000;//请求超时5秒
    private static final int SO_TIMEOUT=5*1000;//设置等待数据超时5秒
    private static String serviceUrl="";

    /**
     * doGet请求数据
     * @param url
     * @param parm
     * @return
     */
    public static String doGet(String url, HashMap<String,String>parm){
        if (!AppApplication.isWork()){
            return "";
        }
        //http://localhost:8080/QzoneServer/UserServer?action=1&username=213&password=123456
        //传递参数为：action=1 username=213 password=123456
        StringBuffer getUrl=new StringBuffer(serviceUrl+url);
        //判断是否需要传递参数
        if (parm!=null){
            //如果url没有？需要先添加？
            //indexOf()返回字符在字符串中首次出现的位置，从0开始
            //返回字符中indexof（string）中子串string在父串中首次出现的位置，从0开始！没有返回-1;
            if (getUrl.indexOf("?")<0){
                getUrl.append("?");
            }
            //判断URL如果有参数，并且最后一个不是&则追加一个&
            //lastIndexOf()返回一个字符串中最后出现的位置从0开始
            int lastIndex=getUrl.lastIndexOf("&");
            if (lastIndex>0&&lastIndex!=getUrl.length()-1){
                getUrl.append("&");
            }
            //循环拼接url
            for (String key:parm.keySet()){
                getUrl.append(key+"="+parm.get(key));
                getUrl.append("&");
            }
            //删掉最后个&
            getUrl.delete(getUrl.length()-1,getUrl.length());

        }
        String result="";
        /**
         * 1.如果操作少量数据用String
         * 2.单线程操作字符串缓冲区下操作大量数据使用StringBuilder
         * 3.多线程操作字符串缓冲区下操作大量数据使用StringBuffer
         */
        StringBuffer oBuffer=new StringBuffer();
        //通过url创建httpGet对象
        HttpURLConnection connection;
        URL oUrl;
        try {
            oUrl=new URL(getUrl.toString());
            connection= (HttpURLConnection) oUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(8000);
            //要断开连接
            connection.connect();

            if (connection.getResponseCode()==200){
                InputStream inputStream=connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line= reader.readLine())!=null){
                    oBuffer.append(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return oBuffer.toString();
    }
    public static String doGet(String url){
        return doGet(url,null);
    }

    public static String doPost(String url){
        return doGet(url,null);
    }

    public static String doPost(String url,HashMap<String,String>parm){



        return null;
    }

}
