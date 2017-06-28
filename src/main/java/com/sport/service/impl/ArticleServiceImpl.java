package com.sport.service.impl;

import com.sport.dao.ArticleDao;
import com.sport.entity.Article;
import com.sport.entity.ArticleContent;
import com.sport.entity.Messages;
import com.sport.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;


    public List<Article> getNewestArticle() {
        return articleDao.queryNewest();
    }

    public int getArticleCount(String searchType) {
        return articleDao.queryArticleCount(searchType);
    }

    public List<Article> getArticleList(String searchType, int beginning, int pageSize) {
        return articleDao.queryByPage(searchType, beginning, pageSize);
    }

    public ArticleContent getById(long articleId) {
        return articleDao.queryById(articleId);
    }

    public int getTotalComments(long articleId) {
        return articleDao.queryTotalComments(articleId);
    }

    public List<Messages> getArticleComment(long articleId, int beginning, int pageSize) {
        return articleDao.queryComments(articleId, beginning, pageSize);
    }
    public boolean addArticleComment(int userId, long articleId, String comment, Timestamp dateTime) {
        boolean addStatus;
        try {
            articleDao.addComments(userId, articleId, comment, dateTime);
            addStatus = true;
        }catch (Exception e){
            addStatus = false;
        }
        return addStatus;
    }

    public List<Article> getClassifyArticle() {
        return articleDao.queryByClassify();
    }
}
