package com.unbiz.api.controller;

import com.unbiz.api.comm.controller.BaseController;
import com.unbiz.api.comm.vo.ResultVO;
import com.unbiz.api.service.ShinhanService;
import com.unbiz.api.service.TimeparService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : ShinhanController
 * author         : UNBIZ
 * date           : 2023-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-22        UNBIZ              최초 생성
 */
@RestController
@RequestMapping(value="/shinhan")
public class ShinhanController extends BaseController {
    @Autowired
    private ShinhanService shinhanServices;
    private static final String basePath = "shinhan/";

    @RequestMapping(value = "/holeByHole", method = {RequestMethod.GET})
    public ModelAndView page(Model model) {
        return new ModelAndView( basePath + "holeByHole" );
    }

    @RequestMapping(value = "/save.tf")
    public ResultVO updateTimeparStamp(@RequestBody Map parameterMap) {
        return success(shinhanServices.updateShinhan(parameterMap));
    }
}
