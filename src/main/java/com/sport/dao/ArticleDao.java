package com.sport.dao;

import com.sport.entity.Article;
import com.sport.entity.ArticleContent;
import com.sport.entity.Messages;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/1/14.
 */
public interface ArticleDao {
    List<Article> queryNewest();
    ArticleContent queryById(long articleId);
    int queryArticleCount(String searchType);
    List<Article> queryByPage(@Param("searchType")String searchType, @Param("beginning")int beginning, @Param("pageSize")int pageSize);
    int queryTotalComments(long articleId);
    List<Messages> queryComments(@Param("articleId") long articleId, @Param("beginning") int beginning, @Param("pageSize")int pageSize);
    boolean addComments(@Param("userId") int userId, @Param("articleId") long articleId, @Param("comment")String comment, @Param("dateTime")Timestamp dateTime);
    List<Article> queryByClassify();
}
