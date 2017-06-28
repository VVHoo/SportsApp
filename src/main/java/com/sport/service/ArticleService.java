package com.sport.service;

import com.sport.entity.Article;
import com.sport.entity.ArticleContent;
import com.sport.entity.Messages;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
public interface ArticleService {
    /*
    * 获取最新三条记录
    * */
    List<Article> getNewestArticle();
    /*
    * 查询该类型记录数
    * */
    int getArticleCount(String searchType);
    /*
    * 分页查询记录
    * */
    List<Article> getArticleList(String searchType, int beginning, int pageSize);
    /*
    * 查询单个记录
    * */
    ArticleContent getById(long articleId);
    /*
    * 查询评论记录数
    * */
    int getTotalComments(long articleId);
    /*
    * 查询评论
    * */
    List<Messages> getArticleComment(long articleId, int beginning, int pageSize);
    /*
    * 增加评论
    * */
    boolean addArticleComment(int userId, long articleId, String comment, Timestamp dateTime);
    /*
    * 根据分类查询
    * */
    List<Article> getClassifyArticle();
}
