package com.unbiz.api.controller;

import com.unbiz.api.service.AdmService;
import com.unbiz.api.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : LeaderboardController
 * author         : UNBIZ
 * date           : 2023-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-27        UNBIZ              최초 생성
 */
@RestController
@Api(tags = {" 리더보드 컨트롤러 "},description =" 리더보드  컨트롤러")
@RequestMapping(value="/tester")
public class TesterController {


    @Autowired
    private ApiService apiService;


    private static final String basePath = "tester/";

    @RequestMapping(value = "/{articleUrl}", method = {RequestMethod.GET})
    @ApiOperation(value = "LeaderboardPage", notes = "리더보드 페이지 이동")
    public ModelAndView Tester(Model model, @PathVariable String articleUrl) {
        return new ModelAndView( basePath + articleUrl );
    }

    @RequestMapping(value = "/log", method = {RequestMethod.GET})
    @ApiOperation(value = "page move GET", notes = "페이지 이동 GET")
    public HashMap logOut(@RequestBody HashMap parameter) {
        int i = apiService.insertLog(parameter);
        parameter.put("cnt", i);
        return parameter;
    }

}
