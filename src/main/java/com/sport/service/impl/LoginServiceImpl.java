package com.sport.service.impl;

import com.sport.dao.LoginDao;
import com.sport.entity.User;
import com.sport.exception.SportsException;
import com.sport.service.LoginService;
import com.sport.util.ConstantClass;
import com.sport.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginDao loginDao;
    public User getByName(String userName) {
        return loginDao.queryByName(userName);
    }

    public String getAvatar(int userId) {
        return loginDao.queryAvatar(userId);
    }

    /**
     * 存放“用户名：token”键值对
     */
    public static Map<String,String> tokenMap=new HashMap<String,String>();
    /**
     * 存放“token:User”键值对
     */
    public static Map<String,User> loginUserMap=new HashMap<String,User>();
    public String loginToken(String userName, String password) {
        //System.out.println(userName+"-----"+password);
        String token = tokenMap.get(userName);
        User user = null;
        if(token == null){
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);
           // System.out.println("新用户登录");
        }else{
            user = loginUserMap.get(token);
            loginUserMap.remove(token);
            //System.out.println("更新用户登录token");
        }
        token = MD5Util.MD5(userName + password + new Date().getTime());
        //{token:user},{userName:token}存session
        loginUserMap.put(token, user);
        tokenMap.put(userName, token);
       // System.out.println("目前有"+tokenMap.size()+"个用户");
        /*for(User u:loginUserMap.values()){
            System.out.println(u.getUserName()+":"+u.getPassword());
        }*/
        return token;
    }

    @Transactional
    public int addAvatar(int userId, String avatarPath) {
        int addAvatarStatus;
        try {
            loginDao.insertAvatar(userId, avatarPath);
            addAvatarStatus = ConstantClass.INSERTAVATAR;
        }catch (Exception e){
            //e.getMessage();
            logger.error(e.getMessage(), e);
            addAvatarStatus = ConstantClass.UPLOADFAIL;
            throw new SportsException("添加头像出错");
        }
        return addAvatarStatus;
    }

    @Transactional
    public Map<String,Object> admitSign(int userId) {
        return loginDao.getSign(userId);
    }

    @Transactional
    public int changeSign(int userId) {
        int signNum;
        try {
            signNum =  loginDao.updateSign(userId);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new SportsException("改变签到状态出错");
        }
        return signNum;
    }

    @Transactional
    public boolean editUserInfo(int userId, String userName, String sex, int age, Date birthday) {
        boolean editStatus;
        try {
            loginDao.updateUser(userId, userName, sex, age, birthday);
            editStatus = true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            editStatus = false;
            throw new SportsException("更新个人信息出错");
        }
        return editStatus;
    }

    @Transactional
    public boolean editUserPassword(int userId, String password) {
        boolean editStatus;
        try {
            loginDao.editPassword(userId, password);
            editStatus = true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            editStatus = false;
            throw new SportsException("修改密码出错");
        }
        return editStatus;
    }




    /*public void setStatus(int status, String token, int userId) {
        loginDao.updateStatus(status, token, userId);
    }*/


}
