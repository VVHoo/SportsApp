package com.sport.web;

import com.sport.dto.AppResult;
import com.sport.entity.Page;
import com.sport.entity.Video;
import com.sport.service.VideoService;
import com.sport.util.ConstantClass;
import com.sport.util.TokenCheckUtil;
import org.apache.commons.collections.functors.ExceptionPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    //loadVideoList, loadVideo
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/getVideoType/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Map<String, Object>>> getVideoType(@PathVariable("token") String token, HttpServletRequest request) {
        //System.out.println(token);
        //System.out.println(request.getSession().getServletContext().getAttribute("currentSessionId")+"----"+request.getSession().getId());

        AppResult<List<Map<String, Object>>> typeResult = null;

        int status = TokenCheckUtil.tokenCheck(token, request);
        switch (status) {
            case ConstantClass.OFFLINE:
                typeResult = new AppResult<List<Map<String, Object>>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        List<Map<String, Object>> videoMap = videoService.getVideoType();
                        typeResult = new AppResult<List<Map<String, Object>>>(ConstantClass.DATASUCCESS, videoMap);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                } else {
                    typeResult = new AppResult<List<Map<String, Object>>>(ConstantClass.HASLOGINED, "已在别的地方登陆");
                }
                break;
        }


        return typeResult;
    }


    @RequestMapping(value = "/getVideoList/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Video>> getList(@PathVariable("token") String token, @RequestBody Page page, HttpServletRequest request) {
        //System.out.println(TokenCheckUtil.tokenCheck(token,request));
        AppResult<List<Video>> result = null;
        int currentPage = page.getCurrentPage() - 1;
        int pageSize = page.getPageSize();
        int beginning = currentPage * pageSize;

        String searchType = page.getSearchType();

        //System.out.println(searchType+"=="+beginning+"--"+pageSize);
        //判断是否登录，token是否已到有效期
        //最好使用aop
        int status = TokenCheckUtil.tokenCheck(token, request);
        switch (status) {
            case ConstantClass.OFFLINE:
                result = new AppResult<List<Video>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        List<Video> list = videoService.getVideoList(searchType, beginning, pageSize);
                        result = new AppResult<List<Video>>(ConstantClass.DATASUCCESS, list);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                } else {
                    result = new AppResult<List<Video>>(ConstantClass.HASLOGINED, "已在别的地方登陆");
                }

                break;
        }
        return result;
    }

    @RequestMapping(value = "/{videoId}/detail/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Map<String, Object>>> detail(@PathVariable("videoId") long videoId, @PathVariable("token") String token, HttpServletRequest request) {
        /*
        * videoId, videoPath,   coverImg
        * 1001     video/video1   ../coverImg/img1
        * 1002     video/video2   ...
        * */
        AppResult<List<Map<String, Object>>> result = null;
        int status = TokenCheckUtil.tokenCheck(token, request);

        switch (status) {
            case ConstantClass.OFFLINE:
                result = new AppResult<List<Map<String, Object>>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        List<Map<String, Object>> subVideo = videoService.getByVideoId(videoId);
                        result = new AppResult<List<Map<String, Object>>>(ConstantClass.DATASUCCESS, subVideo);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        result = new AppResult<List<Map<String, Object>>>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    result = new AppResult<List<Map<String, Object>>>(ConstantClass.HASLOGINED, "已在别的地方登陆");
                }

                break;
        }
        return result;
    }


    /*@RequestMapping(value = "/search/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Video>> searchVideo(@PathVariable("token") String token, HttpServletRequest request){
        AppResult<List<Video>> searchResult;
        int searchStatus = TokenCheckUtil.tokenCheck(token, request);
        switch (searchStatus){
            case ConstantClass.OFFLINE:
                searchResult = new AppResult<List<Video>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                try {

                }catch (Exception e){
                    logger.error(e.getMessage(), e);
                    searchResult = new AppResult<List<Video>>(ConstantClass.DATAFAIL, "获取数据失败");
                }
                break;
        }
        return null;
    }*/


}
