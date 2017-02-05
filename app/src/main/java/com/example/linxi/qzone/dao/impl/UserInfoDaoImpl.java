package com.example.linxi.qzone.dao.impl;

import android.text.TextUtils;

import com.example.linxi.qzone.AppApplication;
import com.example.linxi.qzone.common.DomXml;
import com.example.linxi.qzone.common.HttpCommon;
import com.example.linxi.qzone.common.SysConfig;
import com.example.linxi.qzone.common.UnityCommon;
import com.example.linxi.qzone.dao.UserInfoDao;
import com.example.linxi.qzone.model.UserInfo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linxi on 2017/2/2.
 */

public class UserInfoDaoImpl implements UserInfoDao {
    private String url="UserServer";

    /**
     *
     * @param oInfo  用户登录方法
     * @return
     */
    @Override
    public boolean userLogin(UserInfo oInfo) {
        boolean result=false;
        int errCode=0;
        String errMsg="";
        UserInfo userInfo=null;
        String errAlert="登录失败";
        String loginUrl= SysConfig.serverUrl+url+"?action=1";
        loginUrl+="&username="+oInfo.getLoginName()+"&password="+oInfo.getPassWord();
        String resultString= HttpCommon.doGet(loginUrl);
        Document oDocument= DomXml.loadXml(resultString);
        if (oDocument!=null){
            //获取根对象
            Element oElemnt=oDocument.getDocumentElement();
            //判断根对象是否为空并且是否为有效节点类型
            if (oElemnt!=null&&oElemnt.getNodeType()==Document.ELEMENT_NODE){
                //获取错误编码，0则是正常编码，默认值为-1
                errCode= UnityCommon.getIntOf(oElemnt.getAttribute("errcount"),-1);
                //获取错误信息
                errMsg=oElemnt.getAttribute("errmsg");
                //如果编码为0代表请求成功，解析xml并把用户完整信息写入数据库
                if (errCode==0){
                    //获取userinfo节点oList
                    NodeList oList=oElemnt.getChildNodes();
                    if (oList!=null&&oList.getLength()>0){
                        for (int i=0;i<oList.getLength();i++) {
                            //将Node节点读取成Userinfo对象
                            userInfo = getUserInfoByXml(oList.item(i));
                            if (userInfo!=null){
                                //清空错误提示码
                                errAlert="";
                                //设置返回登录成功
                                result=true;
                                //设置用户为自动登录用户
                                userInfo.setIsLogin(1);
                                //将用户信息写入到sqlite数据库中，该用户为自动登录用户
                                insertUserInfo(userInfo);
                                //将当前用户赋值为系统配置中loginUserInfo并存储起来
                                SysConfig.loginUserInfo=userInfo;
                            }
                        }
                    }
                }else {
                    //否则判断是否有返回的异常信息，有则弹出
                    if (!TextUtils.isEmpty(errMsg)){
                        errAlert=errMsg;
                    }
                }
            }
        }
        //如果有错误消息则通过toast弹出
        if (!TextUtils.isEmpty(errAlert)){
            AppApplication.toastMessage(errAlert);
        }
        return result;
    }

    private void insertUserInfo(UserInfo userInfo) {
        boolean result=false;
        if (userInfo!=null){
            SysConfig.dbHelper.deleteModel("USERINFO","id=?",new String[]{String.valueOf(userInfo)});
            result=SysConfig.dbHelper.insertModel("USERINFO",getDBMap(userInfo));
        }
    }

    private Map<String,String> getDBMap(UserInfo oInfo) {
        Map<String,String>oMap=null;
        if (oInfo!=null){
            oMap=new HashMap<String,String>();
            oMap.put("id",String.valueOf(oInfo.getId()));
            oMap.put("loginname",String.valueOf(oInfo.getLoginName()));
            oMap.put("password",String.valueOf(oInfo.getPassWord()));
            oMap.put("lasttime",UnityCommon.dateFormat(oInfo.getLastTime()));
            oMap.put("resistertime",UnityCommon.dateFormat(oInfo.getRegistTime()));
            oMap.put("sex",oInfo.getSex());
            oMap.put("nikeName",oInfo.getNikeName());
            oMap.put("islogin",String.valueOf(oInfo.getIsLogin()));
        }
        return oMap;
    }

    private UserInfo getUserInfoByXml(Node oNode) {
        UserInfo oInfo=null;
        if (oNode!=null&&oNode.getNodeType()==Document.ELEMENT_NODE){
            NodeList userNodeList=oNode.getChildNodes();
            if (userNodeList!=null&&userNodeList.getLength()>0){
                oInfo=new UserInfo();
                for (int i=0;i<userNodeList.getLength();i++){
                    Node userNode=userNodeList.item(i);
                    if (userNode.getNodeName().equals("id")){
                        oInfo.setId(UnityCommon.getIntOf(userNode.getFirstChild().getNodeValue()));
                    }
                    if (userNode.getNodeName().equals("loginname")){
                        oInfo.setLoginName(userNode.getFirstChild().getNodeValue());
                    }
                    if (userNode.getNodeName().equals("password")){
                        oInfo.setPassWord(userNode.getFirstChild().getNodeValue());
                    }
                    if (userNode.getNodeName().equals("lasttime")){
                        oInfo.setLastTime(UnityCommon.stringParseDate(userNode.getFirstChild().getNodeValue()));
                    }
                    if (userNode.getNodeName().equals("registertime")){
                        oInfo.setRegistTime(UnityCommon.stringParseDate(userNode.getFirstChild().getNodeValue()));
                    }
                    if (userNode.getNodeName().equals("sex")){
                        oInfo.setSex(userNode.getFirstChild().getNodeValue());
                    }
                    if (userNode.getNodeName().equals("nikename")){
                        oInfo.setNikeName(userNode.getFirstChild().getNodeValue());
                    }
                }
            }
        }
        return oInfo;
    }

    @Override
    public String updatepsd(UserInfo oInfo) {
        return null;
    }

    @Override
    public UserInfo getLoginUserInfo() {
        return null;
    }

    @Override
    public boolean userRegister(UserInfo oInfo) {
        return false;
    }

    @Override
    public List<UserInfo> getUserInfos(String selectString, int page, int pageSize) {
        return null;
    }
}
