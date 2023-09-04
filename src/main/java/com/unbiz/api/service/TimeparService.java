package com.unbiz.api.service;

import com.unbiz.api.mapper.SchedulerMapper;
import com.unbiz.api.mapper.TimeparMapper;
import com.unbiz.api.model.TimeparGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map selectTimeparHole(Map parameterMap){ return timeparMapper.selectTimeparHole(parameterMap); }
    public List<Map> selectTimeparStand(Map parameterMap){ return timeparMapper.selectTimeparStand(parameterMap); }
    public List<Map> selectTimeparStamp(Map parameterMap){
        return timeparMapper.selectTimeparStamp(parameterMap);
    }
    public Integer insertTimeparHole(Map parameterMap){ return timeparMapper.insertTimeparHole(parameterMap); }
    public Integer insertTimeparGame(Map parameterMap){ return timeparMapper.insertTimeparGame(parameterMap); }
    public Map selectTimeparGame(){ return timeparMapper.selectTimeparGame(); }
    public Integer insertTimeparStamp(Map parameterMap){return timeparMapper.insertTimeparStamp(parameterMap); }
    public Integer updateTimeparStamp(Map parameterMap){return timeparMapper.updateTimeparStamp(parameterMap); }

    public List<TimeparGroup> selectTimeparGroup(Map parameterMap){ return timeparMapper.selectTimeparGroup(parameterMap); }
    public List<Map> selectFullswingDataTime(){ return timeparMapper.selectFullswingDataTime(); }


}
