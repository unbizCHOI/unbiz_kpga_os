package com.unbiz.api.mapper;

import com.unbiz.api.model.GameVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.mapper
 * fileName       : KpgaMapper
 * author         : UNBIZ
 * date           : 2024-10-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-18        UNBIZ              최초 생성
 */
@Mapper
public interface  KpgaMapper {
    public List<Map> selectLeaderboard(Map parameterMap);
    public List<Map> selectStandPoint(Map parameterMap);
    public List<Map> selectPlayerPoint(Map parameterMap);


}
