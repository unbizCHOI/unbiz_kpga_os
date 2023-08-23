package com.unbiz.api.service;

import com.unbiz.api.mapper.AdmMapper;
import com.unbiz.api.mapper.AdmSubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * packageName    : com.unbiz.api.service
 * fileName       : AdmService
 * author         : UNBIZ
 * date           : 2023-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-27        UNBIZ              최초 생성
 */
@Service
public class AdmSubService {

    @Autowired
    private AdmSubMapper admSubMapper;

    public HashMap selectList(String aUrl, HashMap parameter) throws Exception {
        HashMap resultMap = new HashMap();
        resultMap.put("parameter", parameter);
        if("prepare".equals(aUrl)){
            resultMap.put("groupMarker", admSubMapper.selectSubGroupMarker(parameter));
            resultMap.put("course", admSubMapper.selectSubTrack3dCourse(parameter));
        }
        return resultMap;
    }

    public HashMap saveInfo(String aUrl, HashMap parameter) throws Exception {
        HashMap resultMap = new HashMap();
        resultMap.put("parameter", parameter);
        int cnt = 0;

        if("score".equals(aUrl)){
            // 스코어 삽입 후 프로시저
            cnt += admSubMapper.insertTrack3dScore(parameter);
            cnt += admSubMapper.updateCallSpPutScore(parameter);
        } else if("prepare".equals(aUrl)){
            cnt += admSubMapper.deletePrepare(parameter);
            cnt += admSubMapper.insertTrack3dCourse(parameter);
            cnt += admSubMapper.insertGroupMarker(parameter);
        }

        resultMap.put("cnt", cnt);
        return resultMap;
    }

    public HashMap deleteInfo(String aUrl, HashMap parameter) throws Exception {
        HashMap resultMap = new HashMap();
        resultMap.put("parameter", parameter);
        int cnt = 0;

        if("prepare".equals(aUrl)){
            cnt += admSubMapper.deletePrepare(parameter);
        }

        resultMap.put("cnt", cnt);
        return resultMap;
    }



}
