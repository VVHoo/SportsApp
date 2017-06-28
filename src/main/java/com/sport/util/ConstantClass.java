package com.sport.util;

/**
 * Created by EKO-LKB on 2017/2/24.
 */
public class ConstantClass {
    public static final int INLINE = 1;//已登录
    public static final int OFFLINE = 2;//重登陆或超时未操作
    public static final int HASLOGINED = -2;//重复登录
    public static final int REGISTERFAIL = 3;//注册失败
    public static final int REGISTERSUCCESS = 201;//注册成功
    public static final int EXISTUSER = 4;//用户已注册
    public static final int DATASUCCESS = 200;//成功返回数据
    public static final int DATAFAIL = 201;//返回数据失败
    public static final int NOTFOUND = 404;//不存在
    public static final int UNAUTHORIZED = 401;//无权限，登录失败
    public static final int INSERTAVATAR = 7;//更新头像列
    public static final int UPLOADSUCCESS = 5;//成功上传
    public static final int UPLOADFAIL = 6;//上传失败
    public static final int ADDTOCOLLECTSUCCESS = 8;//收藏成功
    public static final int ADDTOCOLLECTFAIL = 9;//收藏失败
    public static final int ISCOLLECTED = 10;//已收藏
    public static final int NOTCOLLECT = 11;//未收藏
    public static final int CANCELSUCCESS = 12;//取消收藏成功
    public static final int CANCELFAIL = 13;//取消收藏失败
    public static final int SENDCOMMENTSUCCESS = 14;//成功发送评论
    public static final int SENDCOMMENTFAIL = 15;//
    public static final int SIGNSUCCESS = 16;//签到成功
    public static final int HASSIGNED = 17;
    public static final int SIGNFAIL = 18;
    public static final int EDITSUCCESS = 19;//更新成功
    public static final int EDITFAIL = 20;
    public static final int EDITPWDSUCCESS = 21;//修改密码成功
    public static final int EDITPWDFAIL = 22;
    public static final int LOGOUT = 0;//退出
    public static final int LOGOUTFAIL = -1;//退出失败
    public static final String AVATARPATH = "http://192.168.199.119:8080/img/avatar/";
}
