package com.unbiz.api.controller;

import com.unbiz.api.comm.controller.BaseController;
import com.unbiz.api.comm.vo.ResultVO;
import com.unbiz.api.model.*;
import com.unbiz.api.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : BaseController
 * author         : UNBIZ
 * date           : 2022-11-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-01        UNBIZ              최초 생성
 */
@RestController
@Api(tags = {" 기본 정보 컨트롤러 "},description ="기본적인 정보 제공 API ")
@RequestMapping(value="/api")
public class ApiController extends BaseController {
    @Autowired
    private ApiService apiService;

    @ApiOperation(value = "대회정보 ", notes = "대회 정보 리스트를 조회한다.", response = PlayerVO.class , responseContainer = "list")
    @ApiImplicitParam( name="apcYear", value="연도", dataType="int", required = true, example="2022")
    @RequestMapping(value = "/game.tf")
    public ResultVO game(@RequestBody Map parameterMap) {
        return success(apiService.selectMst(parameterMap));
    }

    @ApiOperation(value = "선수 정보", notes = "참가 선수 정보 리스트를 조회한다.", response = PlayerVO.class , responseContainer = "list")
    @ApiImplicitParam( name="gameId", value="대회코드", dataType="String", required = true, example="202211000004M")
    @RequestMapping(value = "/player.tf")
    public ResultVO player(@RequestBody Map parameterMap) {
        return success(apiService.selectPlayer(parameterMap));
    }

    @ApiOperation(value = "코스 정보", notes = "코스 정보를 반환한다.", response = CourseVO.class , responseContainer = "list")
    @ApiImplicitParam( name="gameId", value="대회코드", dataType="String", required = true, example="202211000004M")
    @RequestMapping(value = "/course.tf")
    public ResultVO course(@RequestBody Map parameterMap) {
        return success(apiService.selectCourse(parameterMap));
    }

    @ApiOperation(value = "조편성 정보", notes = "라운드별 조편성 정보를 반환한다.", response = GroupVO.class , responseContainer = "list")
    @ApiImplicitParams({
        @ApiImplicitParam( name="gameId", value="대회코드", dataType="String", required = true, example="202211000004M")
        ,@ApiImplicitParam( name="roundNo", value="라운드", dataType="int", required = true, example="1")
    })
    @RequestMapping(value = "/group.tf")
    public ResultVO group(@RequestBody Map parameterMap) {
        return success(apiService.selectGroup(parameterMap));
    }

    @ApiOperation(value = "스코어 정보", notes = "라운드별 스코어 정보를 반환한다.", response = ScoreVO.class , responseContainer = "list" )
    @ApiImplicitParams({
        @ApiImplicitParam( name="gameId", value="대회코드", dataType="String", required = true, example="202211000004M")
        ,@ApiImplicitParam( name="roundNo", value="라운드", dataType="int", required = true, example="1")
    })
    @RequestMapping(value = "/score.tf")
    public ResultVO score(@RequestBody Map parameterMap) {
        return success(apiService.selectScore(parameterMap));
    }


    @RequestMapping(value = "/allstar.tf")
    public ResultVO allset() {
        return success(apiService.selectAllstar());
    }

}
