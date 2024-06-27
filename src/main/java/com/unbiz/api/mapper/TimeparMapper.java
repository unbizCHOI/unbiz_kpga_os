package com.unbiz.api.mapper;

import com.unbiz.api.model.TimeparGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.mapper
 * fileName       : TimeparMapper
 * author         : UNBIZ
 * date           : 2023-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-22        UNBIZ              최초 생성
 */
@Mapper
public interface TimeparMapper {

    List<Map> selectTimeparStamp(Map parameterMap);

    List<Map> selectTimeparStand(Map parameterMap);
    Map selectTimeparHole(Map parameterMap);

    Integer insertTimeparHole(Map parameterMap);

    Integer insertTimeparGame(Map parameterMap);
    Map selectTimeparGame(Map parameterMap);
    Integer insertTimeparStamp(Map parameterMap);
    Integer updateTimeparStamp(Map parameterMap);
    List<TimeparGroup> selectTimeparGroup(Map parameterMap);
    List<Map> selectFullswingDataTime();
    List<Map> selectHoleParCnt(Map parameterMap);
    Integer updateAccuSum(Map parameterMap);
    List<Map> selectHoleInTime(Map parameterMap);

}
