package com.sport.web;

import com.sport.dto.AppResult;
import com.sport.entity.*;
import com.sport.service.ArticleService;
import com.sport.util.ConstantClass;
import com.sport.util.TokenCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    //loadArticleClassify-->loadArticle & comment/loadArticleList-->loadArticle & comment
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleService articleService;


    @RequestMapping(value = "/getNewestArticle/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Article>> getNewest(@PathVariable("token") String token, HttpServletRequest request) {
        AppResult<List<Article>> newestResult = null;

        int status = TokenCheckUtil.tokenCheck(token, request);
        switch (status) {
            case ConstantClass.OFFLINE:
                newestResult = new AppResult<List<Article>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        List<Article> newestList = articleService.getNewestArticle();
                        newestResult = new AppResult<List<Article>>(ConstantClass.DATASUCCESS, newestList);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        newestResult = new AppResult<List<Article>>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    newestResult = new AppResult<List<Article>>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return newestResult;
    }

    /*获取该类型文章总记录数*/
    @RequestMapping(value = "/getNum/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Object> getNum(@PathVariable("token") String token, @RequestBody Page page, HttpServletRequest request) {
        AppResult<Object> numResult = null;
        String searchType = page.getSearchType();

        int status = TokenCheckUtil.tokenCheck(token, request);
        switch (status) {
            case ConstantClass.OFFLINE:
                numResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    int articleCount = articleService.getArticleCount(searchType);
                    numResult = new AppResult<Object>(ConstantClass.DATASUCCESS, articleCount);
                } else {
                    numResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }
                break;
        }
        return numResult;
    }


    @RequestMapping(value = "/getList/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Article>> getList(@PathVariable("token") String token, @RequestBody Page page, HttpServletRequest request) {
        //System.out.println(TokenCheckUtil.tokenCheck(token,request));
        //System.out.println(token + "---" +request.getSession().getAttribute(token));
        //分页 SELECT * FROM product WHERE ID >=(select id from product limit currentPage*pageSize, 1) limit 20  page.getCurrentPage, page.getPageSize
        AppResult<List<Article>> result = null;
        int currentPage = page.getCurrentPage() - 1;//totalPage = totalNum/pageSize
        int pageSize = page.getPageSize();
        int beginning = currentPage * pageSize;
        String searchType = page.getSearchType();

        //System.out.println(searchType);
        //判断是否登录，token是否已到有效期
        //TODO aop
        //System.out.println(request.getSession().getAttribute(token));

        int status = TokenCheckUtil.tokenCheck(token, request);
        switch (status) {
            case ConstantClass.OFFLINE:
                result = new AppResult<List<Article>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        List<Article> list = articleService.getArticleList(searchType, beginning, pageSize);
                        result = new AppResult<List<Article>>(ConstantClass.DATASUCCESS, list);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        result = new AppResult<List<Article>>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    result = new AppResult<List<Article>>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return result;
    }

    //获取每种分类前两条记录
    @RequestMapping(value = "/getClassifyArticle/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Article>> classifyArticle(@PathVariable("token") String token, HttpServletRequest request) {
        AppResult<List<Article>> classifyResult = null;
        int getStatus = TokenCheckUtil.tokenCheck(token, request);

        switch (getStatus) {
            case ConstantClass.OFFLINE:
                classifyResult = new AppResult<List<Article>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        List<Article> classifyList = articleService.getClassifyArticle();
                        classifyResult = new AppResult<List<Article>>(ConstantClass.DATASUCCESS, classifyList);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        classifyResult = new AppResult<List<Article>>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    classifyResult = new AppResult<List<Article>>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return classifyResult;
    }


    @RequestMapping(value = "/{articleId}/detail/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<ArticleContent> detail(@PathVariable("articleId") long articleId, @PathVariable("token") String token, HttpServletRequest request) {
        AppResult<ArticleContent> result = null;

        int status = TokenCheckUtil.tokenCheck(token, request);
        switch (status) {
            case ConstantClass.OFFLINE:
                result = new AppResult<ArticleContent>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        ArticleContent articleContent = articleService.getById(articleId);
                        result = new AppResult<ArticleContent>(ConstantClass.DATASUCCESS, articleContent);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        result = new AppResult<ArticleContent>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    result = new AppResult<ArticleContent>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }


                break;
        }
        return result;
    }

    @RequestMapping(value = "/{articleId}/getTotalComment/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Object> getTotalComment(@PathVariable("articleId") long articleId, @PathVariable("token") String token, HttpServletRequest request) {
        AppResult<Object> totalResult = null;

        int status = TokenCheckUtil.tokenCheck(token, request);
        switch (status) {
            case ConstantClass.OFFLINE:
                totalResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        int total = articleService.getTotalComments(articleId);
                        totalResult = new AppResult<Object>(ConstantClass.DATASUCCESS, total);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        totalResult = new AppResult<Object>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    totalResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return totalResult;
    }

    @RequestMapping(value = "/{articleId}/getMessages/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<List<Messages>> getMessages(@PathVariable("articleId") long articleId, @PathVariable("token") String token, @RequestBody Page page, HttpServletRequest request) {
        //userId: session.getAttribute(token);
        //t_user: userId, userName ....
        //t_messages:messageId, articleId, userId, comment
        //SELECT a.messageId,b.userName,a.comment FROM t_message as a,t_user as b WHERE a.userId=b.id AND a.articleId=?
        //System.out.println(articleId);
        AppResult<List<Messages>> getMessagesResult = null;

        int getStatus = TokenCheckUtil.tokenCheck(token, request);
        int currentPage = page.getCurrentPage() - 1;//totalPage = totalNum/pageSize
        int pageSize = page.getPageSize();
        int beginning = currentPage * pageSize;

        switch (getStatus) {
            case ConstantClass.OFFLINE:
                getMessagesResult = new AppResult<List<Messages>>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    try {
                        List<Messages> messages = articleService.getArticleComment(articleId, beginning, pageSize);
                        getMessagesResult = new AppResult<List<Messages>>(ConstantClass.DATASUCCESS, messages);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        getMessagesResult = new AppResult<List<Messages>>(ConstantClass.DATAFAIL, "获取数据失败");
                    }
                } else {
                    getMessagesResult = new AppResult<List<Messages>>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }

                break;
        }
        return getMessagesResult;
    }

    @RequestMapping(value = "/addComment/token/{token}", method = RequestMethod.POST)
    @ResponseBody
    public AppResult<Object> addComment(@RequestBody Comment comment, @PathVariable("token") String token, HttpServletRequest request) {
        AppResult<Object> addCommentResult = null;
        boolean addStatus;

        int loginStatus = TokenCheckUtil.tokenCheck(token, request);
        switch (loginStatus) {
            case ConstantClass.OFFLINE:
                addCommentResult = new AppResult<Object>(ConstantClass.UNAUTHORIZED, "请登录");
                break;
            case ConstantClass.INLINE:
                int userId = (Integer) request.getSession().getAttribute(token);
                String currentSessionId = (String) request.getSession().getServletContext().getAttribute(String.valueOf(userId));
                if (currentSessionId.equals(request.getSession().getId())) {
                    Date date = new Date();
                    Timestamp dateTime = new Timestamp(date.getTime());
                    //System.out.println(tt);
                    addStatus = articleService.addArticleComment(userId, comment.getArticleId(), comment.getComment(), dateTime);
                    if (addStatus) {
                        addCommentResult = new AppResult<Object>(ConstantClass.SENDCOMMENTSUCCESS);
                    } else {
                        addCommentResult = new AppResult<Object>(ConstantClass.SENDCOMMENTFAIL, "发送评论失败");
                    }
                } else {
                    addCommentResult = new AppResult<Object>(ConstantClass.HASLOGINED, "已在别的地方登录");
                }
                break;
        }
        return addCommentResult;
    }


}
