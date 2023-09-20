package com.unbiz.api.service;

import com.unbiz.api.comm.config.ExceptionHandler;
import com.unbiz.api.mapper.ShinHanMapper;
import com.unbiz.api.mapper.TimeparMapper;
import com.unbiz.api.model.TimeparGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.service
 * fileName       : TimeparService
 * author         : UNBIZ
 * date           : 2023-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-22        UNBIZ              최초 생성
 */
@Service
public class ShinhanService {
    @Autowired
    private ShinHanMapper shinHanMapper;
    private static final Logger logger = LoggerFactory.getLogger(ShinhanService.class);

    @Transactional
    public Integer updateShinhan(Map parameterMap){
        shinHanMapper.updateShinhan(parameterMap);
        shinHanMapper.updateAccuScore(parameterMap);
        shinHanMapper.updateRoundEndYn(parameterMap);
        shinHanMapper.updateRank(parameterMap);

        logger.info("==========================================================================================================");

        return 1;
    }


}
