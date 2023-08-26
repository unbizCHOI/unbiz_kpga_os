package com.unbiz.api.mapper;

import com.unbiz.api.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.mapper
 * fileName       : SchedulerMapper
 * author         : UNBIZ
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        UNBIZ              최초 생성
 */
@Mapper
public interface SchedulerMapper {
    public List<Map> selectSchedulerAll(Map parameterMap);

    void updateTimepar();
}
