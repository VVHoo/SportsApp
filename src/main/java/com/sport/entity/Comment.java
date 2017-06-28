package com.sport.entity;

/**
 * Created by EKO-LKB on 2017/3/16.
 */
public class Comment {
    private int userId;
    private long articleId;
    private String comment;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
