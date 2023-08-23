package com.unbiz.api.comm.config;

import com.unbiz.api.model.GameVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * packageName    : com.unbiz.gms.comm.config
 * fileName       : LoginInterceptor
 * author         : UNBIZ
 * date           : 2023-03-25
 * description    : 인증 인터셉터 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-25        UNBIZ              최초 생성
 */
@Component
@Slf4j
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        Boolean isGo = true;
        Object userId = null;
        GameVO gameInfo = null;
        Object gameInfoObj = null;
        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect("/adm/sub/login.do");
            isGo = false;
        } else {
            userId = session.getAttribute("userId");
            gameInfoObj = session.getAttribute("gameInfo");

            if(userId == null || gameInfoObj == null || ((GameVO)gameInfoObj).getGameId() == null ){
                response.sendRedirect("/adm/sub/login.do");
                isGo =  false;
            }

            gameInfo = (GameVO)gameInfoObj;
        }
        log.info("[preHandle] path : {}, session : {}, userId : {} , gameInfo : {}" ,path ,(session == null) ,userId, (gameInfo == null));
        return isGo;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // log.info("[postHandle]");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
        // log.info("[afterCompletion]");
    }
}