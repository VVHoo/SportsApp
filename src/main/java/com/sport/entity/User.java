package com.sport.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sport.util.BirthdaySerializer;
import com.sport.util.DateSerializer;

import java.util.Date;

/**
 * Created by EKO-LKB on 2017/2/14.
 */
public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private boolean admitSign;
    private int signNum;
    private String avatarUrl;
    private int age;
    private String hometown;
    private Date birthday;
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmitSign() {
        return admitSign;
    }

    public void setAdmitSign(boolean admitSign) {
        this.admitSign = admitSign;
    }

    public int getSignNum() {
        return signNum;
    }

    public void setSignNum(int signNum) {
        this.signNum = signNum;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonSerialize(using = BirthdaySerializer.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
