package com.unbiz.api.mapper;

import com.unbiz.api.model.ScoreVO;
import com.unbiz.api.model.ShotVO;
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
public interface AdmMapper {
    public Map selectTimeparStamp(Map parameterMap);
    public List<Map> selectTimeparHole(Map parameterMap);
}
