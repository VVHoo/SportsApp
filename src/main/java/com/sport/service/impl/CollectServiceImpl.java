package com.sport.service.impl;

import com.sport.dao.CollectDao;
import com.sport.entity.Video;
import com.sport.exception.SportsException;
import com.sport.service.CollectService;
import com.sport.util.ConstantClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Service
public class CollectServiceImpl implements CollectService{
    @Autowired
    private CollectDao collectDao;

    public int isCollected(int userId, long videoId) {
        return collectDao.checkCollection(userId, videoId);
    }

    public List<Video> getCollectedVideoList(int userId) {
        return collectDao.queryCollectedVideo(userId);
    }
    @Transactional
    public boolean addToCollectionList(int userId, long videoId) throws TransactionException {
        boolean addStatus;
        try {
            collectDao.addToCollection(userId, videoId);
            addStatus = true;
        }catch (Exception e){
            addStatus = false;
            throw new SportsException("添加收藏失败");
        }
        return addStatus;
    }
    @Transactional
    public boolean cancelCollection(int userId, long videoId) {
        boolean cancelStatus;
        try {
            collectDao.deleteCollection(userId, videoId);
            cancelStatus = true;
        }catch (Exception e){
            cancelStatus = false;
            throw new SportsException("取消收藏失败");
        }
        return cancelStatus;
    }
}

