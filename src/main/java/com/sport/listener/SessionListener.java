package com.sport.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/3/20.
 */
public class SessionListener implements HttpSessionListener {
    private static HashMap userNameMap = new HashMap();
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        userNameMap.remove(httpSessionEvent.getSession().getId());
    }

    public static boolean isAlreadyLogin(HttpSession session,String sUserName){
        boolean isLogin = false;
        //System.out.println(hUserName.containsValue(sUserName));
        if(userNameMap.containsValue(sUserName)){
            isLogin = true;
            Iterator iter = userNameMap.entrySet().iterator();
            while (iter.hasNext()){
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                if(((String)val).equals(sUserName)){
                    userNameMap.remove(key);
                }
                userNameMap.put(session.getId(), sUserName);
                //System.out.println("second:hUserName = " + hUserName + "session = " + session);
            }
        }else {
            isLogin = false;
            userNameMap.put(session.getId(), sUserName);
            //System.out.println("hUserName = " + hUserName + "session = " + session);
        }
        return isLogin;
    }
}
