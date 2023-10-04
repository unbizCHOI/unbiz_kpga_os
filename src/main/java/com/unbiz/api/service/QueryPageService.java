package com.unbiz.api.service;

import com.unbiz.api.mapper.QueryPageMapper;
import com.unbiz.api.mapper.SchedulerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.service
 * fileName       : QueryPageService
 * author         : UNBIZ
 * date           : 2023-09-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-22        UNBIZ              최초 생성
 */
@Service
public class QueryPageService {
    @Autowired
    private QueryPageMapper queryPageMapper;
    public List selectQuery(Map map){
        if(!ObjectUtils.isEmpty(map.get("queryName"))){
            String queryStr = queryPageMapper.selectQueryText(map);
            map.put("queryStr", queryStr);
        }
        return ObjectUtils.isEmpty(map.get("queryStr")) ? new ArrayList() : queryPageMapper.selectQuery(map);
    }
}
