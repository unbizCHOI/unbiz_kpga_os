package com.unbiz.api.mapper;

import com.unbiz.api.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.mapper
 * fileName       : HoldMapper
 * author         : UNBIZ
 * date           : 2022-12-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-14        UNBIZ              최초 생성
 * 2022-12-21        UNBIZ              대회정보리스트 추가
 */
@Mapper
public interface ApiMapper {
    public List<GameVO> selectMst(Map parameterMap);
    public List<CourseVO> selectCourse(Map parameterMap);
    public List<GroupVO> selectGroup(Map parameterMap);
    public List<PlayerVO> selectPlayer(Map parameterMap);
    public List<ScoreVO> selectScore(Map parameterMap);
    public List<ShotVO> selectShot(Map parameterMap);
}
