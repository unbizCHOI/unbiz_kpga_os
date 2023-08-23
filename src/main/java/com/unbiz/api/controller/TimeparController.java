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

    @RequestMapping(value = "/stamp/{gameId}/{roundNo}", method = {RequestMethod.GET})
    public ModelAndView page(Model model, @PathVariable String gameId, @PathVariable String roundNo) {
        model.addAttribute("gameId",gameId);
        model.addAttribute("roundNo",roundNo);
        return new ModelAndView( basePath + "stamp" );
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

}
