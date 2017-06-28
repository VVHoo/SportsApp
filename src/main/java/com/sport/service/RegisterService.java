package com.sport.service;


/**
 * Created by EKO-LKB on 2017/2/4.
 */
public interface RegisterService {
    int registerUser(String userName, String password, String email, boolean admitSign, int signNum);
}
