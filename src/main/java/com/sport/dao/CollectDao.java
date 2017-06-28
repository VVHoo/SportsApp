package com.sport.dao;


import com.sport.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by EKO-LKB on 2017/1/14.
 */
public interface CollectDao {
    //int addCollect(@Param("userId") String userId, @Param("videoId") String videoId);
    List<Video> queryCollectedVideo(int userId);

    int checkCollection(@Param("userId") int userId, @Param("videoId") long videoId);

    boolean addToCollection(@Param("userId") int userId, @Param("videoId") long videoId);

    boolean deleteCollection(@Param("userId") int userId, @Param("videoId") long videoId);
}
