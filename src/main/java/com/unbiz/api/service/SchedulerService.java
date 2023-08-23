package com.unbiz.api.service;

import com.unbiz.api.mapper.SchedulerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.service
 * fileName       : SchedulerService
 * author         : UNBIZ
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        UNBIZ              최초 생성
 */
@Service
public class SchedulerService {

    @Autowired
    private SchedulerMapper schedulerMapper;
    public List<Map> selectSchedulerAll(Map parameterMap){
        return schedulerMapper.selectSchedulerAll(parameterMap);
    }

}
