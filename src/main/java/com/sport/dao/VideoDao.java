package com.sport.dao;


import com.sport.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by EKO-LKB on 2017/1/14.
 */
public interface VideoDao {
    List<Map<String, Object>> queryVideoType();
    List<Map<String, Object>> queryByVideoId(long videoId);
    List<Video> queryVideoByPage(@Param("searchType") String searchType, @Param("beginning")int beginning, @Param("pageSize")int pageSize);
}
