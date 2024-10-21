package com.unbiz.api.controller;

import com.unbiz.api.comm.vo.ResultVO;
import com.unbiz.api.service.KpgaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : KpgaController
 * author         : UNBIZ
 * date           : 2024-10-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-18        UNBIZ              최초 생성
 */
@RestController
@Api(tags = {"KPGA 컨트롤러 "},description =" KPGA 화면 컨트롤러")
@RequestMapping(value="/kpga")
public class KpgaController {

    private static final String basePath = "kpga/";

    @Autowired
    private KpgaService kpgaService;

    @RequestMapping(value = "/point")
    public ModelAndView point(Model model) {
        return new ModelAndView( basePath + "point" );
    }

    @RequestMapping(value = "/leaderboard.tf")
    public List leaderboard(@RequestBody Map parameterMap) {
        return kpgaService.selectLeaderboard(parameterMap);
    }

    @RequestMapping(value = "/stand/point.tf")
    public Map standPoint(@RequestBody Map parameterMap) {
        parameterMap.put("stand", kpgaService.selectStandPoint(parameterMap));
        parameterMap.put("player", kpgaService.selectPlayerPoint(parameterMap));
        return parameterMap;
    }
}
