package com.example.linxi.qzone.dao;

import com.example.linxi.qzone.model.UserInfo;

import java.util.List;

/**
 * Created by linxi on 2017/2/1.
 */

public interface UserInfoDao {
    public boolean userLogin(UserInfo oInfo);
    public String updatepsd(UserInfo oInfo);
    public UserInfo getLoginUserInfo();
    public boolean userRegister(UserInfo oInfo);

    /**
     *
     * @param selectString
     *              搜索的用户昵称如果是第一次加载则为""
     * @param page
     *              要请求第几页
     * @param pageSize
     *              每页显示几条信息
     * @return
     */
    public List<UserInfo>getUserInfos(String selectString,int page,int pageSize);
}
