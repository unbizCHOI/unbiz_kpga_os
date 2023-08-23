package com.unbiz.api.comm.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
/**
 * packageName    : com.unbiz.coda.comm.config
 * fileName       : InitFilter
 * author         : UNBIZ
 * date           : 2022-09-16
 * description    : Filter 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        UNBIZ              최초 생성
 */
@Slf4j
public class InitFilter implements Filter {

    private List excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("InitFilter init");
        excludedUrls = Arrays.asList("/libs");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) req).getServletPath();
        //log.info("doFilter before: {}", path);
        chain.doFilter(req, resp);
        //log.info("doFilter after: {}", path);
    }

    @Override
    public void destroy() {
        log.info("InitFilter destroy");
    }
}