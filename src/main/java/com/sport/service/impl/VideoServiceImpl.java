package com.sport.service.impl;

import com.sport.dao.VideoDao;
import com.sport.entity.Video;
import com.sport.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
@Service
public class VideoServiceImpl implements VideoService{
    @Autowired
    private VideoDao videoDao;

    public List<Map<String , Object>> getVideoType() {
        return videoDao.queryVideoType();
    }

    public List<Video> getVideoList(String searchType, int beginning, int pageSize) {
        return videoDao.queryVideoByPage(searchType, beginning, pageSize);
    }

    public List<Map<String, Object>> getByVideoId(long videoId) {
        return videoDao.queryByVideoId(videoId);
    }
}
