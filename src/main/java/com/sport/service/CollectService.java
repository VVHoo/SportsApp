package com.sport.service;

import com.sport.entity.Video;

import java.util.List;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
public interface CollectService {
    int isCollected(int userId, long videoId);
    /*
    * 查询用户所有收藏
    * */
    List<Video> getCollectedVideoList(int userId);
    /*
    * 查询单个记录
    * */
   /* Article getById(long articleId);*/
    boolean addToCollectionList(int userId, long videoId);

    boolean cancelCollection(int userId, long videoId);
}
