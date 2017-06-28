package com.sport.dao;


import com.sport.entity.User;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/14.
 */
public interface LoginDao {
    /*
    * return User
    * */
    User queryByName(String userName);
    /*
    * return avatar
    * */
    String queryAvatar(int userId);
    /*
    * return status
    * */
    //void updateStatus(@Param("status") int status, @Param("token") String token, @Param("userId") int userId);
    /*
    * return status
    * */
    int insertAvatar(@Param("userId") int userId, @Param("avatarPath") String avatarPath);
    /*
    * get sign
    * */
    Map<String, Object> getSign(int userId);
    /*
    * update admit and plus 1
    * */
    int updateSign(int userId);
    /*
    * update userInfo
    * */
    boolean updateUser(@Param("userId")int userId, @Param("userName") String userName, @Param("sex")String sex, @Param("age") int age, @Param("birthday")Date birthday);
    /*
    * edit password
    * */
    boolean editPassword(@Param("userId") int userId, @Param("password")String password);
}
