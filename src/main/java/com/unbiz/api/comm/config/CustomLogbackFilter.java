package com.unbiz.api.comm.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * packageName    : com.unbiz.coda.comm.config
 * fileName       : CustomLogbackFilter
 * author         : UNBIZ
 * date           : 2022-10-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-28        UNBIZ              최초 생성
 */
public class CustomLogbackFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
       if(event.getMessage().indexOf("==IGNORE_LOG==") > -1){
           return FilterReply.DENY;
       }
        return FilterReply.ACCEPT;
    }
}
