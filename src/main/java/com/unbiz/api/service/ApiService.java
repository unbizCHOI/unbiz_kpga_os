package com.unbiz.api.service;


import com.unbiz.api.mapper.ApiMapper;
import com.unbiz.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.service
 * fileName       : ApiService
 * author         : UNBIZ
 * date           : 2022-12-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-14        UNBIZ              최초 생성
 */
@Service
public class ApiService {

    @Autowired
    private ApiMapper apiMapper;
    public List<GameVO> selectMst(Map parameterMap){ return apiMapper.selectMst(parameterMap); }
    public List<CourseVO> selectCourse(Map parameterMap){ return apiMapper.selectCourse(parameterMap); }
    public List<GroupVO> selectGroup(Map parameterMap){
        return apiMapper.selectGroup(parameterMap);
    }
    public List<PlayerVO> selectPlayer(Map parameterMap){
        return apiMapper.selectPlayer(parameterMap);
    }
    public List<ScoreVO> selectScore(Map parameterMap){
        return apiMapper.selectScore(parameterMap);
    }
    public List<ShotVO> selectShot(Map parameterMap){ return apiMapper.selectShot(parameterMap);}
}
