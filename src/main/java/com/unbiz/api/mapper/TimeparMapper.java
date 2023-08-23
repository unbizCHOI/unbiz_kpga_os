package com.unbiz.api.mapper;

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
}
