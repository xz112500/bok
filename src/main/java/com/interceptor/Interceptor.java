package com.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip= (String) request.getSession().getAttribute("ip");
        if (!StringUtils.isNotBlank(ip)){
                return true;
        }
        response.setContentType(
                "text/html;charset=UTF-8");
        response.getWriter().println("权限不足!");
        return false;
    }
}
