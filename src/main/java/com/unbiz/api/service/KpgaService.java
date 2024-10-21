package com.unbiz.api.service;

import com.unbiz.api.mapper.KpgaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.service
 * fileName       : KpgaService
 * author         : UNBIZ
 * date           : 2024-10-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-18        UNBIZ              최초 생성
 */
@Service
public class KpgaService {
    @Autowired
    private KpgaMapper kpgaMapper;

    public List<Map> selectLeaderboard(Map parameterMap){
        return kpgaMapper.selectLeaderboard(parameterMap);
    }

    public List<Map> selectStandPoint(Map parameterMap){
        return kpgaMapper.selectStandPoint(parameterMap);
    }

    public List<Map> selectPlayerPoint(Map parameterMap){
        return kpgaMapper.selectPlayerPoint(parameterMap);
    }
}
