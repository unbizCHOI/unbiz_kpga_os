package com.unbiz.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.mapper
 * fileName       : AdmMapper
 * author         : UNBIZ
 * date           : 2023-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-27        UNBIZ              최초 생성
 */
@Mapper
public interface AdmSubMapper {
    public int deletePrepare(Map parameterMap);
    public int insertTrack3dCourse(Map parameterMap);
    public int insertGroupMarker(Map parameterMap);
    public int insertTrack3dScore(Map parameterMap);
    public int updateCallSpPutScore(Map parameterMap);
    public List<Map> selectSubGroupMarker(Map parameterMap);
    public List<Map> selectSubTrack3dCourse(Map parameterMap);


    public int deleteGroupMarker(Map parameterMap);
    public int deleteCourse(Map parameterMap);
    public int insertKPGACourse(Map parameterMap);


}
