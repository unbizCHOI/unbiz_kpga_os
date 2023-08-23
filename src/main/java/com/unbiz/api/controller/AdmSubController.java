package com.unbiz.api.controller;

import com.unbiz.api.comm.controller.BaseController;
import com.unbiz.api.model.GameVO;
import com.unbiz.api.service.AdmService;
import com.unbiz.api.service.AdmSubService;
import com.unbiz.api.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * packageName    : com.unbiz.api.controller
 * fileName       : AdmController
 * author         : UNBIZ
 * date           : 2023-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-27        UNBIZ              최초 생성
 */
@RestController
@Api(tags = {" 관리자 컨트롤러 "},description =" 관리자 화면 컨트롤러")
@RequestMapping(value="/adm/sub")
public class AdmSubController extends BaseController {
    @Autowired
    private AdmSubService admSubService;

    @Autowired
    private ApiService apiService;
    private static final String basePath = "adm/";

    @RequestMapping(value = "/logout.do", method = {RequestMethod.GET})
    @ApiOperation(value = "page move GET", notes = "페이지 이동 GET")
    public ModelAndView logOut(HttpSession session) {
        session.removeAttribute("gameInfo");
        return new ModelAndView( basePath+ "sub/login" );
    }

    @RequestMapping(value = "/login.do", method = {RequestMethod.GET})
    @ApiOperation(value = "page move GET", notes = "페이지 이동 GET")
    public ModelAndView pageSubLogin() {
        return new ModelAndView( basePath+ "sub/login" );
    }

    @RequestMapping(value = "/{aUrl}.do")
    @ApiOperation(value = "page move GET", notes = "페이지 이동 GET")
    public ModelAndView pageSubScore(HttpSession session, Model model ,@PathVariable String aUrl) {
        GameVO gameInfo = (GameVO)session.getAttribute("gameInfo");
        model.addAttribute("gameInfo", gameInfo);
        model.addAttribute("userId", session.getAttribute("userId"));
        return new ModelAndView( basePath+ "sub/" + aUrl );
    }

    @RequestMapping(value="/{aUrl}/{tUrl}.tf", method=RequestMethod.POST)
    @ResponseBody
    public HashMap ajaxList(@PathVariable String aUrl, @PathVariable String tUrl, @RequestBody HashMap parameter) throws Exception{
        if("list".equals(tUrl)){
            return admSubService.selectList(aUrl, parameter);
        } else if("info".equals(tUrl)){
            //return gosService.selectInfo(aUrl, bUrl, parameter);
        } else if("save".equals(tUrl)){
            return admSubService.saveInfo(aUrl, parameter);
        } else if("del".equals(tUrl)){
            return admSubService.deleteInfo(aUrl, parameter);
        }
        HashMap map = new HashMap();
        map.put("isOkay", "no");
        return map;
    }

}
