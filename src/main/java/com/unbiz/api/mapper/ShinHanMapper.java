package com.unbiz.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ShinHanMapper {
    Integer updateShinhan(Map parameterMap);
    Integer updateAccuScore(Map parameterMap);
    Integer updateRank(Map parameterMap);

    Integer updateRoundEndYn(Map parameterMap);



}
