package com.sport.web;

import com.sport.dto.AppResult;
import com.sport.dto.Avatar;
import com.sport.service.LoginService;
import com.sport.util.ConstantClass;
import com.sport.util.TokenCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
//upload files
    /*
    * how to get?-->cors跨域-->http://192.168.1.35:8080/img/xxx.jpg
    * upload to where?--->webapp下
    * */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/uploadAvatar/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Avatar> uploadImg(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("token") String token, HttpServletRequest request) {
        //System.out.println(request.getSession().getAttribute(token));
        //System.out.println(file);
        AppResult<Avatar> uploadResult = null;

        String path;
        String originalPath;
        String avatarPath;
        int avatarStatus;
        Avatar avatar = new Avatar();

        int status = TokenCheckUtil.tokenCheck(token, request);
        //System.out.println(status);
        switch (status) {
            case ConstantClass.OFFLINE:
                uploadResult = new AppResult<Avatar>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    if (!file.isEmpty()) {
                        try {

                            //删除原来头像
                            String originalAvatar = loginService.getAvatar(userId);
                            //System.out.println(originalAvatar);
                            if (originalAvatar != null || !originalAvatar.equals("")) {
                                File originalFile = new File(originalAvatar);
                                originalPath = servletContext.getRealPath("/img/avatar/") + originalFile.getName();
                                //System.out.println(originalPath);
                                File originalAvatarFile = new File(originalPath);
                                originalAvatarFile.delete();
                            }
                            //更新用户头像
                            path = servletContext.getRealPath("/img/avatar/") + File.separator + file.getOriginalFilename();
                            //String path = request.getSession().getServletContext().getRealPath("/img");//拒绝访问
                            //System.out.println(path);
                            //System.out.println(servletContext.getRealPath("/img/avatar")+"---"+originalFile.getName());
                            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(path));
                            //写入数据库，绑定用户
                            avatarPath = ConstantClass.AVATARPATH + file.getOriginalFilename();
                            avatarStatus = loginService.addAvatar(userId, avatarPath);
                            if (avatarStatus == ConstantClass.INSERTAVATAR) {
                                avatar.setUserId(userId);
                                avatar.setAvatarPath(avatarPath);
                                uploadResult = new AppResult<Avatar>(ConstantClass.UPLOADSUCCESS, avatar);
                            } else {
                                uploadResult = new AppResult<Avatar>(ConstantClass.UPLOADFAIL, "更新头像列失败");
                            }
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                            uploadResult = new AppResult<Avatar>(ConstantClass.UPLOADFAIL, "上传失败");
                        }
                    } else {
                        uploadResult = new AppResult<Avatar>(ConstantClass.UPLOADFAIL, "文件为空");
                    }
                } else {
                    uploadResult = new AppResult<Avatar>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }


        return uploadResult;
    }

}
