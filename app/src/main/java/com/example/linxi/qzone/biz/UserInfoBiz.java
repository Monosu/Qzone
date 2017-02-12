package com.example.linxi.qzone.biz;

import com.example.linxi.qzone.dao.UserInfoDao;
import com.example.linxi.qzone.dao.impl.UserInfoDaoImpl;
import com.example.linxi.qzone.model.UserInfo;

/**
 * Created by linxi on 2017/2/12.
 */

public class UserInfoBiz  {
    private static UserInfoDao oDao=new UserInfoDaoImpl();
    public static boolean userLogin(UserInfo oInfo){
        return oDao.userLogin(oInfo);
    }
}
