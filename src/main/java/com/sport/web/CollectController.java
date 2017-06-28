package com.sport.web;


import com.sport.dto.AppResult;
import com.sport.entity.Video;
import com.sport.service.CollectService;
import com.sport.util.ConstantClass;
import com.sport.util.TokenCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Controller
@RequestMapping(value = "/videoCollection")
public class CollectController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CollectService collectService;

    /*
    *收藏操作(增删查)
    *collectId,  userId, articleId
    * */
    @RequestMapping(value = "/getCollectionList/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Video>> getCollectionList(@PathVariable("token") String token, HttpServletRequest request) {
        AppResult<List<Video>> collectionListResult = null;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                collectionListResult = new AppResult<List<Video>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        //t_video:        videoId coverPath videoTitle
                        //t_collection:   userId videoId collectId
                        //select t_video.videoId, t_video.coverPath, t_video.videoTitle from t_video, t_collection where userId = #{userId} and t_video.videoId = t_collection.videoId
                        List<Video> collectionList = collectService.getCollectedVideoList(userId);
                        collectionListResult = new AppResult<List<Video>>(ConstantClass.DATASUCCESS, collectionList);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        collectionListResult = new AppResult<List<Video>>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    collectionListResult = new AppResult<List<Video>>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return collectionListResult;
    }


    @RequestMapping(value = "/{videoId}/addCollection/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Object> addCollection(@PathVariable("token") String token, @PathVariable("videoId") long videoId, HttpServletRequest request) {
        AppResult<Object> addCollectionResult = null;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        boolean addStatus;

        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                addCollectionResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    //videoId userId
                    try {
                        addStatus = collectService.addToCollectionList(userId, videoId);
                        if (addStatus) {
                            addCollectionResult = new AppResult<Object>(ConstantClass.ADDTOCOLLECTSUCCESS);
                        } else {
                            addCollectionResult = new AppResult<Object>(ConstantClass.ADDTOCOLLECTFAIL, "添加过程中失败");
                        }
                    } catch (Exception e) {
                        addCollectionResult = new AppResult<Object>(ConstantClass.ADDTOCOLLECTFAIL, "失败");
                        logger.error(e.getMessage(), e);
                    }
                } else {
                    addCollectionResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return addCollectionResult;
    }

    @RequestMapping(value = "/{videoId}/isCollection/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Object> isCollection(@PathVariable("token") String token, @PathVariable("videoId") long videoId, HttpServletRequest request) {
        AppResult<Object> collectionResult = null;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        switch (loginStatus) {
            case ConstantClass.UNAUTHORIZED:
                collectionResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        int isCollected = collectService.isCollected(userId, videoId);
                        if (isCollected > 0) {
                            collectionResult = new AppResult<Object>(ConstantClass.ISCOLLECTED);
                        } else {
                            collectionResult = new AppResult<Object>(ConstantClass.NOTCOLLECT);
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        collectionResult = new AppResult<Object>(ConstantClass.DATAFAIL);
                    }
                } else {
                    collectionResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return collectionResult;
    }


    @RequestMapping("/{videoId}/cancelCollect/token/{token}")
    @ResponseBody
    public AppResult<Object> cancleCollection(@PathVariable("token") String token, @PathVariable("videoId") long videoId, HttpServletRequest request) {
        AppResult<Object> cancelCollectionResult = null;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        boolean cancelStatus;

        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                cancelCollectionResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        cancelStatus = collectService.cancelCollection(userId, videoId);
                        if (cancelStatus) {
                            cancelCollectionResult = new AppResult<Object>(ConstantClass.CANCELSUCCESS);
                        } else {
                            cancelCollectionResult = new AppResult<Object>(ConstantClass.CANCELFAIL, "取消收藏失败");
                        }
                    } catch (Exception e) {
                        cancelCollectionResult = new AppResult<Object>(ConstantClass.CANCELFAIL, "取消收藏失败");
                    }
                } else {
                    cancelCollectionResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return cancelCollectionResult;
    }
}
