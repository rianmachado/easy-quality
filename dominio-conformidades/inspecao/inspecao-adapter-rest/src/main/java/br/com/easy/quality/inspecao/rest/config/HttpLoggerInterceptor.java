package br.com.easy.quality.inspecao.rest.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpLoggerInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (logger.isInfoEnabled()) {
            logger.info("REQUEST::{}:{}",
                request.getMethod(),
                request.getRequestURI());
        }

        return true;
    }
}
