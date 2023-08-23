package com.unbiz.api.controller;

import com.unbiz.api.service.AdmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping(value="/leaderboard")
public class LeaderboardController {

    @Autowired
    private AdmService admService;
    private static final String basePath = "leaderboard/";

    @RequestMapping(value = "/{articleUrl}/{gameId}/{roundNo}", method = {RequestMethod.GET})
    @ApiOperation(value = "LeaderboardPage", notes = "리더보드 페이지 이동")
    public ModelAndView LeaderboardPage(Model model, @PathVariable String articleUrl, @PathVariable String gameId, @PathVariable String roundNo) {
        model.addAttribute("gameId",gameId);
        model.addAttribute("roundNo",roundNo);
        return new ModelAndView( basePath + articleUrl );
    }
}
