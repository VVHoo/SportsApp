package com.sport.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by EKO-LKB on 2017/2/23.
 */
public class TokenCheckUtil {
    public final static int tokenCheck(String token, HttpServletRequest request){
        int status;
        HttpSession session = request.getSession();
        /*String userId = (String) session.getAttribute(token);
        System.out.println(userId);*/
        if(token == null || token == ""){
            status = ConstantClass.OFFLINE;
        }else if(session.getAttribute(token) == null){
            status = ConstantClass.OFFLINE;
        }else {
            status = ConstantClass.INLINE;
        }
        return status;
    }
}
