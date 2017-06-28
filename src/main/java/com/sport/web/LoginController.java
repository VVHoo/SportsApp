package com.sport.web;

import com.sport.dto.AppResult;
import com.sport.dto.Avatar;
import com.sport.dto.UserToken;
import com.sport.entity.Sign;
import com.sport.entity.User;
import com.sport.listener.SessionListener;
import com.sport.service.LoginService;
import com.sport.service.RegisterService;
import com.sport.util.ConstantClass;
import com.sport.util.TokenCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Controller
@RequestMapping("/Auth")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    private static Map<String, String> tokenMap = new HashMap<String, String>();

    //登录身份验证，返回{token,username}
    //@CrossOrigin(origins = "http://localhost:63342", maxAge = 3600)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<UserToken> login(@RequestBody User user, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {//获取前台json数据格式
        AppResult<UserToken> result = null;
        User checkUser = loginService.getByName(user.getUserName());
        //added on 2.21
        HttpSession session = servletRequest.getSession();
        //System.out.println(session.getId());
        //System.out.println("前台user" + user.getUserName() + "--" + user.getPassword());
        if (checkUser == null) {
            result = new AppResult<UserToken>(ConstantClass.NOTFOUND, "not exist the user");
        } else if (user.getUserName().equals(checkUser.getUserName()) && user.getPassword().equals(checkUser.getPassword())) {
            //返回token+username
             /*login-->create token-->session.put-->return
            * if relogin --> session.remove --> create token --> session.put --> return
            * */
            //TODO 跨浏览器登录不同session的问题
            /*if(SessionListener.isAlreadyLogin(session, user.getUserName())){
                //System.out.println("islogin");
                //System.out.println(session.getId()+"---" +user.getUserName());
                //session.removeAttribute(token);
                //System.out.println(session.getAttribute(tokenMap.get("currentToken")));
                session.removeAttribute(tokenMap.get("currentToken"+checkUser.getId()));
                //System.out.println(session.getAttribute(tokenMap.get("currentToken"+checkUser.getId())));
            }*/

            UserToken userToken = new UserToken();
            String token = loginService.loginToken(user.getUserName(), user.getPassword());
            userToken.setUserId(checkUser.getId());
            userToken.setUserName(checkUser.getUserName());
            userToken.setToken(token);
            userToken.setLoginStatus("登录成功");
            userToken.setPassword(checkUser.getPassword());
            userToken.setAvatarUrl(checkUser.getAvatarUrl());
            userToken.setAge(checkUser.getAge());
            userToken.setEmail(checkUser.getEmail());
            //userToken.setHometown(checkUser.getHometown());
            userToken.setBirthday(checkUser.getBirthday());
            userToken.setSex(checkUser.getSex());
            //修改登录状态,存入token
            //int status = 1;
            //将userName:token放进session,设置超时操作时间
            session.setAttribute(token, checkUser.getId());
            /*//能否签到放入session
            session.setAttribute("admitSign", checkUser.isAdmitSign());*/
            //System.out.println(session.getAttribute("admitSign"));
            session.setMaxInactiveInterval(3600);
            result = new AppResult<UserToken>(ConstantClass.DATASUCCESS, userToken);
            tokenMap.put("currentToken" + checkUser.getId(), token);
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            servletResponse.addCookie(cookie);
            //绑定userId和sessionId
            ServletContext application = servletRequest.getSession().getServletContext();
            application.setAttribute(String.valueOf(checkUser.getId()), session.getId());
            //System.out.println(application.getAttribute("currentSessionId"));
            //System.out.println(session.getAttribute(token));
           /* String message = (String) session.getAttribute(token);
            System.out.println("loginmessage:" + message);*/
            /*try {
                loginService.setStatus(status, token, checkUser.getId());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }*/
        } else {
            result = new AppResult<UserToken>(ConstantClass.UNAUTHORIZED, "error userName or password");
        }
        return result;
    }

    /*
   * 注册
   * */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<User> regedit(@RequestBody User user) {
        AppResult<User> result = null;
        user.setAdmitSign(true);
        user.setSignNum(0);
        int registerStatus = registerService.registerUser(user.getUserName(), user.getPassword(), user.getEmail(), true, 0);
        if (registerStatus == ConstantClass.EXISTUSER) {
            result = new AppResult<User>(ConstantClass.EXISTUSER, "用户已注册");
        } else if (registerStatus == ConstantClass.REGISTERFAIL) {
            result = new AppResult<User>(ConstantClass.REGISTERFAIL, "注册失败");
        } else if (registerStatus == ConstantClass.REGISTERSUCCESS) {
            result = new AppResult<User>(ConstantClass.REGISTERSUCCESS, "注册成功");
        }
        return result;
    }


    /*
    * 退出登录
    * */
   /* @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<UserToken> logout(@RequestBody UserToken userToken, HttpServletRequest request){
        AppResult<UserToken> logoutResult;
        UserToken logoutToken = new UserToken();
        if(userToken.getUserName() == null || userToken.getToken() == null){
            logoutResult = new AppResult<UserToken>(ConstantClass.UNAUTHORIZED, "请登录");
        }else {
            try {
                request.getSession().removeAttribute(userToken.getToken());
                logoutToken.setUserName(userToken.getUserName());
                logoutToken.setLoginStatus("已退出");
                logoutResult = new AppResult<UserToken>(ConstantClass.LOGOUT, logoutToken);
            }catch (Exception e){
                logger.error(e.getMessage(), e);
                logoutResult = new AppResult<UserToken>(ConstantClass.LOGOUTFAIL, "退出失败");
            }
            //System.out.println(request.getSession().getAttribute("logoutMessage:"+userToken.getToken()));
        }
        return logoutResult;
    }*/

    /*@RequestMapping(value = "/getUserAvatar/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Avatar> getUserAvatar(@PathVariable("token") String token, HttpServletRequest request){
        AppResult<Avatar> getResult = null;
        int userId = (Integer)request.getSession().getAttribute(token);
        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        Avatar avatar = new Avatar();
        switch (loginStatus){
            case ConstantClass.OFFLINE:
                getResult = new AppResult<Avatar>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                try {
                    String avatarUrl = loginService.getAvatar(userId);
                    avatar.setAvatarPath(avatarUrl);
                    avatar.setUserId(userId);
                    getResult = new AppResult<Avatar>(ConstantClass.DATASUCCESS, avatar);
                }catch (Exception e){
                    logger.error(e.getMessage(), e);
                    getResult = new AppResult<Avatar>(ConstantClass.DATAFAIL, "获取头像失败");
                }
                break;
        }
        return getResult;
    }*/


    /*
    * 每天打卡
    * */
    @RequestMapping(value = "/sign/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Sign> sign(@PathVariable("token") String token, HttpServletRequest request) {
        /*
        * t_user加一个字段admit(默认为1)判断是否允许签到,写个事件每天12点更新这个字段为允许签到,签到完再将该字段改为不允许
        * select admit from t_user where userId = ?
         * 0:1
         * if(1){update signNum from t_user where userId = ?   set admit = 0,  select signNum from t_user where userId = ?}else{}
        * */
        AppResult<Sign> signResult = null;

        //System.out.println("userId: " + userId);
        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        boolean permitSign;
        Sign sign = new Sign();

        int signNum;
        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                signResult = new AppResult<Sign>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        signNum = loginService.changeSign(userId);
                        //System.out.println(signNum);
                        sign.setSignNum(signNum);
                        sign.setSignSuccess("签到成功");
                        signResult = new AppResult<Sign>(ConstantClass.SIGNSUCCESS, sign);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        signResult = new AppResult<Sign>(ConstantClass.SIGNFAIL, "签到过程出错");
                    }
                } else {
                    signResult = new AppResult<Sign>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return signResult;
    }


    @RequestMapping(value = "/getSign/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Map<String, Object>> getSign(@PathVariable("token") String token, HttpServletRequest request) {
        AppResult<Map<String, Object>> signResult = null;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);

        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                signResult = new AppResult<Map<String, Object>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        Map<String, Object> signInfo = loginService.admitSign(userId);
                        signResult = new AppResult<Map<String, Object>>(ConstantClass.DATASUCCESS, signInfo);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        signResult = new AppResult<Map<String, Object>>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    signResult = new AppResult<Map<String, Object>>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return signResult;
    }


    @RequestMapping(value = "editUserInfo/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Object> editUserInfo(@PathVariable("token") String token, @RequestBody UserToken userToken, HttpServletRequest request) {
        //code
        AppResult<Object> editResult = null;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        String userName = userToken.getUserName();
        String sex = userToken.getSex();
        int age = userToken.getAge();
        Date birthday = userToken.getBirthday();

        //String hometown = userToken.getHometown();
        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                editResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    boolean editStatus = loginService.editUserInfo(userId, userName, sex, age, birthday);
                    if (editStatus) {
                        editResult = new AppResult<Object>(ConstantClass.EDITSUCCESS);
                    } else {
                        editResult = new AppResult<Object>(ConstantClass.EDITFAIL, "更新信息失败");
                    }
                } else {
                    editResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }

        return editResult;
    }

    @RequestMapping(value = "editPassWord/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Object> editPassWord(@PathVariable("token") String token, @RequestBody User user, HttpServletRequest request) {
        AppResult<Object> editPwdResult = null;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        String password = user.getPassword();

        //System.out.println(password);
        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                editPwdResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    boolean editPwdStatus = loginService.editUserPassword(userId, password);
                    if (editPwdStatus) {
                        editPwdResult = new AppResult<Object>(ConstantClass.EDITPWDSUCCESS);
                    } else {
                        editPwdResult = new AppResult<Object>(ConstantClass.EDITPWDFAIL, "修改密码失败");
                    }
                } else {
                    editPwdResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }

        return editPwdResult;
    }

}
