package com.unbiz.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.mapper
 * fileName       : QueryPageMapper
 * author         : UNBIZ
 * date           : 2023-09-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-22        UNBIZ              최초 생성
 */
@Mapper
public interface QueryPageMapper {
    public List selectQuery(Map map);
    public String selectQueryText(Map map);

}

