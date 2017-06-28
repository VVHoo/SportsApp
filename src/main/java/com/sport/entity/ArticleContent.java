package com.sport.entity;

import java.util.Date;

/**
 * Created by EKO-LKB on 2017/4/5.
 */
public class ArticleContent {
    private long articleId;
    private Date date;
    private String articleContent;

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}
