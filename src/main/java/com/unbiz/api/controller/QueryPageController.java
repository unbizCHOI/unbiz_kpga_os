package com.unbiz.api.controller;

import com.unbiz.api.service.QueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : QueryPageController
 * author         : UNBIZ
 * date           : 2023-09-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-22        UNBIZ              최초 생성
 */
@RestController
@RequestMapping(value="/query")
public class QueryPageController {

    @Autowired
    private QueryPageService queryPageService;


    @RequestMapping(value = "/page", method = {RequestMethod.GET})
    public ModelAndView page(Model model) {
        return new ModelAndView( "query/page" );
    }

    @RequestMapping(value="/try.tf", method=RequestMethod.POST)
    @ResponseBody
    public Object ajaxList(@RequestBody HashMap parameter) throws Exception{
        return queryPageService.selectQuery(parameter);
    }

}
