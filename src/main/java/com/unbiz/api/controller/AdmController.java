package com.unbiz.api.controller;

import com.unbiz.api.comm.controller.BaseController;
import com.unbiz.api.model.GameVO;
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
import java.util.List;
import java.util.Map;

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
@RequestMapping(value="/adm")
public class AdmController extends BaseController {
    @Autowired
    private AdmService admService;
    @Autowired
    private ApiService apiService;
    private static final String basePath = "adm/";

    @RequestMapping(value="/pick.tf", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> ajaxGamePick(@RequestBody HashMap parameter, HttpSession session) throws Exception{
        List list = apiService.selectMst(parameter);
        if(list.size()>0){
            GameVO gameInfo = (GameVO)list.get(0);
            gameInfo.setRoundNo("" + parameter.get("roundNo"));
            session.setAttribute("gameInfo", gameInfo);
            session.setAttribute("userId", parameter.get("userId"));
            session.setMaxInactiveInterval(60*60*12);
        }
        parameter.put("total" , list.size());
        return parameter;
    }

    @RequestMapping(value="/{aUrl}/{tUrl}.tf", method=RequestMethod.POST)
    @ResponseBody
    public HashMap ajaxList(@PathVariable String aUrl, @PathVariable String tUrl, @RequestBody HashMap parameter) throws Exception{
        if("list".equals(tUrl)){
            //return gosService.selectList(aUrl, bUrl, parameter);
        } else if("info".equals(tUrl)){
            //return gosService.selectInfo(aUrl, bUrl, parameter);
        } else if("save".equals(tUrl)){
            //return admService.saveInfo(aUrl, parameter);
        } else if("del".equals(tUrl)){
            //return gosService.deleteInfo(aUrl, bUrl, parameter);
        }
        HashMap map = new HashMap();
        map.put("isOkay", "no");
        return map;
    }

}
