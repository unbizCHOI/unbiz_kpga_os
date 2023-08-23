package com.unbiz.api.comm.controller;

import com.unbiz.api.comm.vo.ResultVO;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.unbiz.gms.comm.controller
 * fileName       : BaseController
 * author         : UNBIZ
 * date           : 2022-09-16
 * description    : 컨트롤러 기본
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        UNBIZ              최초 생성
 */
public class BaseController {

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){ }

    /**
     * Success result vo.
     *
     * @return the result vo
     */
    public ResultVO success()
    {
        return ResultVO.success();
    }

    /**
     * 성공 메시지
     *
     * @param message the message
     * @return the result vo
     */
    public ResultVO success(String message)
    {
        return ResultVO.success(message);
    }

    /**
     * 성공 data
     *
     * @param data the data
     * @return the result vo
     */
    public ResultVO success(Object data)
    {
        return ResultVO.success(data);
    }

    /**
     * 실패
     *
     * @return the result vo
     */
    public ResultVO error()
    {
        return ResultVO.error();
    }

    /**
     * 실패 메시지
     *
     * @param message the message
     * @return the result vo
     */
    public ResultVO error(String message)
    {
        return ResultVO.error(message);
    }

    /**
     * 실패 data
     *
     * @param data the data
     * @return the result vo
     */
    public ResultVO error(Object data)
    {
        return ResultVO.error(data);
    }


    /**
     * 검색용 맵 생성
     *
     * @param gameId String
     * @return the map
     */
    public Map makeParameterMap(String gameId){
        Map parameterMap = new HashMap();
        parameterMap.put("gameId", gameId);
        return parameterMap;
    }

    /**
     * 검색용 맵 생성
     *
     * @param gameId String
     * @param roundNo Integer
     * @return the map
     */
    public Map makeParameterMap(String gameId, Integer roundNo){
        Map parameterMap = makeParameterMap(gameId);
        parameterMap.put("roundNo", roundNo);
        return parameterMap;
    }

    /**
     * 검색용 맵 생성
     *
     * @param gameId String
     * @param roundNo Integer
     * @param memberId String
     * @return the map
     */
    public Map makeParameterMap(String gameId, Integer roundNo, String memberId){
        Map parameterMap = makeParameterMap(gameId, roundNo);
        parameterMap.put("memberId", memberId);
        return parameterMap;
    }
}
