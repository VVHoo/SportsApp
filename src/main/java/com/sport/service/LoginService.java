package com.sport.service;


import com.sport.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
public interface LoginService {
    /*
    * return User
    * */
    User getByName(String userName);
    /*
    * return avatar
    * */
    String getAvatar(int userId);
    /*
    *return token
    * */
    String loginToken(String userName, String password);

    /*
    * set User status
    * */
    //void setStatus(int status, String token, int userId);

    /*
    * addAvatar
    * */
    int addAvatar(int userId, String avatarPath);

    /*
    * sign
    * */
    Map<String ,Object> admitSign(int userId);
    /*
    * update admitSign
    * */
    int changeSign(int userId);
    /*
    * update userInfo
    * */
    boolean editUserInfo(int userId, String userName, String sex, int age, Date birthday);
    /*
    * edit userPassword
    * */
    boolean editUserPassword(int userId, String password);
}
