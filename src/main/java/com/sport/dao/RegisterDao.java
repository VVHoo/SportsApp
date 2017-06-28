package com.sport.dao;


import org.apache.ibatis.annotations.Param;

/**
 * Created by EKO-LKB on 2017/1/14.
 */
public interface RegisterDao {
    /*
    * 注册，插入数据
    * */
    int saveUser(@Param("userName") String userName, @Param("password") String password, @Param("email") String email, @Param("admitSign")boolean admitSign, @Param("signNum") int signNum);

}
