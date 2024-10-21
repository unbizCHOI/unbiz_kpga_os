package com.unbiz.api.service;

import com.unbiz.api.mapper.TimeparMapper;
import com.unbiz.api.model.TimeparGroup;
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
public class TimeparService {
    @Autowired
    private TimeparMapper timeparMapper;
    public Integer insertTimeparGame(Map parameterMap){ return timeparMapper.insertTimeparGame(parameterMap); }
    public Map selectTimeparHole(Map parameterMap){ return timeparMapper.selectTimeparHole(parameterMap); }
    public List<Map> selectTimeparStand(Map parameterMap){ return timeparMapper.selectTimeparStand(parameterMap); }
    public List<Map> selectTimeparStamp(Map parameterMap){
        return timeparMapper.selectTimeparStamp(parameterMap);
    }
    public Integer insertTimeparHole(Map parameterMap){ return timeparMapper.insertTimeparHole(parameterMap); }
    public Map selectTimeparGame( Map parameterMap ){ return timeparMapper.selectTimeparGame(parameterMap); }
    public Integer insertTimeparStamp(Map parameterMap){return timeparMapper.insertTimeparStamp(parameterMap); }
    public Integer updateTimeparStamp(Map parameterMap){return timeparMapper.updateTimeparStamp(parameterMap); }

    public List<TimeparGroup> selectTimeparGroup(Map parameterMap){ return timeparMapper.selectTimeparGroup(parameterMap); }
    public List<Map> selectFullswingDataTime(){ return timeparMapper.selectFullswingDataTime(); }
    public List<Map> selectHoleParCnt(Map parameterMap){return timeparMapper.selectHoleParCnt(parameterMap); }
    @Transactional
    public void saveKpgaTimePar(Map parameterMap){
        insertTimeparGame(parameterMap);
        insertTimeparHole(parameterMap);
        insertTimeparStamp(parameterMap);
    }
    public Integer updateAccuSum(Map parameterMap){ return timeparMapper.updateAccuSum(parameterMap); }

    public List<Map> selectHoleInTime(Map parameterMap){ return timeparMapper.selectHoleInTime(parameterMap); }

    public Integer updateTimeparHole(Map parameterMap){ return timeparMapper.updateTimeparHole(parameterMap); }
}
