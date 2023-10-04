package com.unbiz.api.service;

import com.unbiz.api.mapper.SchedulerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
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
    @Transactional
    public void doKpsaToGms(){
        Map parameterMap = new HashMap();
        Integer vodSeq = schedulerMapper.selectKpsaToGms();
        parameterMap.put("vodSeq" , vodSeq);
        if(!ObjectUtils.isEmpty(vodSeq)){
            schedulerMapper.insertKpsaToGms(parameterMap);
            schedulerMapper.insertKpsaToGmsFile(parameterMap);
            schedulerMapper.updateKpsaToGms(parameterMap);
        }
    }

    public List<Map> selectSchedulerAll(Map parameterMap){
        return schedulerMapper.selectSchedulerAll(parameterMap);
    }

    public void updateTimepar(){schedulerMapper.updateTimepar();}

}
