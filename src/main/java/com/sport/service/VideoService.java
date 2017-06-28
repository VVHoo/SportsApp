package com.sport.service;

import com.sport.entity.Video;

import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/2/4.
 */
public interface VideoService {
    /*
    * 查询video类型
    * */
    List<Map<String, Object>> getVideoType();
    /*
    * 分页查询所有记录
    * */
    List<Video> getVideoList(String searchType, int beginning, int pageSize);
    /*
    * 查询单个记录
    * */
    List<Map<String, Object>> getByVideoId(long videoId);
}
