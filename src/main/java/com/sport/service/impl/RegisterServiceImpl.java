package com.sport.service.impl;

import com.sport.dao.LoginDao;
import com.sport.dao.RegisterDao;
import com.sport.service.RegisterService;
import com.sport.util.ConstantClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private RegisterDao registerDao;

    public int registerUser(String userName, String password, String email, boolean admitSign, int signNum) {
        int registerStatus = 0;
        if(loginDao.queryByName(userName) == null){
            //System.out.println("没有注册的用户名");
            try {
                registerDao.saveUser(userName, password, email, admitSign, signNum);
                registerStatus = ConstantClass.REGISTERSUCCESS;//注册成功
            }catch (Exception e){
                registerStatus = ConstantClass.REGISTERFAIL;//注册失败
                System.out.println(e.getMessage());
            }
        }else {
            //System.out.println("已注册的用户名");
            registerStatus = ConstantClass.EXISTUSER;//用户已注册
        }
        return registerStatus;
    }
}
