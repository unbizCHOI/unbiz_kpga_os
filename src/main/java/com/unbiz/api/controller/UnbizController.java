package com.unbiz.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : UnbizController
 * author         : UNBIZ
 * date           : 2024-01-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-11        UNBIZ              최초 생성
 */
@RestController
@Api(tags = {"UNBIZ 컨트롤러 "},description =" UNBIZ 화면 컨트롤러")
@RequestMapping(value="/unbiz")
public class UnbizController {
    private static final String basePath = "unbiz/";

    @RequestMapping(value = "/{group1}/{group2}")
    public ModelAndView pageGet(Model model, @RequestParam HashMap parameter, @PathVariable String group1, @PathVariable String group2) {
        model.addAttribute("parameter",parameter);
        return new ModelAndView( basePath + group1 + "/" + group2 );
    }

}
