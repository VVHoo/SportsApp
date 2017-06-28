package com.sport.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sport.util.DateSerializer;

import java.util.Date;

/**
 * Created by EKO-LKB on 2017/3/15.
 */
public class Messages {
    private long messageId;
    private long articleId;
    private String userName;
    private String avatarUrl;
    private String comment;
    private Date sendTime;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
