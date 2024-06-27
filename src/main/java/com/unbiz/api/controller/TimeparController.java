package com.unbiz.api.controller;

import com.unbiz.api.comm.controller.BaseController;
import com.unbiz.api.comm.vo.ResultVO;
import com.unbiz.api.model.PlayerVO;
import com.unbiz.api.service.ApiService;
import com.unbiz.api.service.TimeparService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : TimeparController
 * author         : UNBIZ
 * date           : 2023-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-22        UNBIZ              최초 생성
 */
@RestController
@RequestMapping(value="/timepar")
public class TimeparController  extends BaseController {
    @Autowired
    private TimeparService timeparService;
    private static final String basePath = "timepar/";

    @RequestMapping(value = "/game.tf")
    public ResultVO Game(@RequestBody Map parameterMap) {
        return success(timeparService.selectTimeparGame(parameterMap));
    }

    @RequestMapping(value = "/game/save.tf")
    public ResultVO GameSave(@RequestBody Map parameterMap) {
        return success(timeparService.insertTimeparGame(parameterMap));
    }
    @RequestMapping(value = "/excel/kpga/save.tf")
    public ResultVO excelKpgaSave(@RequestBody Map parameterMap) {
        timeparService.saveKpgaTimePar(parameterMap);
        return success();
    }

    @RequestMapping(value = "/excel/save.tf")
    public ResultVO excelSave(@RequestBody Map parameterMap) {
        return success(timeparService.insertTimeparHole(parameterMap));
    }

    @RequestMapping(value = "/stamp", method = {RequestMethod.GET})
    public ModelAndView page(Model model) {
        return new ModelAndView( basePath + "stamp" );
    }

    @RequestMapping(value = "/stampEn", method = {RequestMethod.GET})
    public ModelAndView pageEn(Model model) {
        return new ModelAndView( basePath + "stampEn" );
    }


    @RequestMapping(value = "/before", method = {RequestMethod.GET})
    public ModelAndView pageBefore(Model model) {
        return new ModelAndView( basePath + "before" );
    }

    @RequestMapping(value = "/time", method = {RequestMethod.GET})
    public ModelAndView pageTime(Model model) {
        return new ModelAndView( basePath + "time" );
    }


    @RequestMapping(value = "/hole.tf")
    public ResultVO Hole(@RequestBody Map parameterMap) {
        return success(timeparService.selectTimeparHole(parameterMap));
    }

    @RequestMapping(value = "/stamp.tf")
    public ResultVO Stamp(@RequestBody Map parameterMap) {
        return success(timeparService.selectTimeparStamp(parameterMap));
    }

    @RequestMapping(value = "/stand.tf")
    public ResultVO Stand(@RequestBody Map parameterMap) {
        return success(timeparService.selectTimeparStand(parameterMap));
    }

    @RequestMapping(value = "/stamp/save.tf")
    public ResultVO updateTimeparStamp(@RequestBody Map parameterMap) {
        return success(timeparService.updateTimeparStamp(parameterMap));
    }
    @RequestMapping(value = "/shot.tf")
    public ResultVO selectTimeparShot(@RequestBody Map parameterMap) {
        return success(timeparService.selectTimeparGroup(parameterMap));
    }
    @RequestMapping(value = "/fs.tf")
    public ResultVO selectfs(@RequestBody Map parameterMap) {
        return success(timeparService.selectFullswingDataTime());
}

    @RequestMapping(value = "/ime", method = {RequestMethod.GET})
    public ModelAndView pagess(Model model) {
        return new ModelAndView( basePath + "ime" );
    }

    @RequestMapping(value = "/gun", method = {RequestMethod.GET})
    public ModelAndView Gun(Model model) {
        return new ModelAndView( basePath + "gun" );

    }

    @RequestMapping(value = "/gunpar.tf")
    public ResultVO GunPar(@RequestBody Map parameterMap) {
        return success(timeparService.selectHoleParCnt(parameterMap));
    }

    @RequestMapping(value = "/accusum.tf")
    public ResultVO accusum(@RequestBody Map parameterMap) {
        return success(timeparService.updateAccuSum(parameterMap));
    }

    @RequestMapping(value = "/holein.tf")
    public ResultVO holein(@RequestBody Map parameterMap) {
        return success(timeparService.selectHoleInTime(parameterMap));
    }

}
