package com.huang.filter;

import com.alibaba.fastjson.JSON;
import com.huang.common.BaseContest;
import com.huang.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//登录拦截器
/**
 * filterName 拦截器名称
 * urlPatterns 指定拦截请求路径
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object employee = request.getSession().getAttribute("employee");
        Object user = request.getSession().getAttribute("user");

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };
        boolean check = check(urls, request.getRequestURI());
        if (check) {
            filterChain.doFilter(request,response);
            return;
        }
        if (Objects.nonNull(employee)) {
            BaseContest.setCurrentId((Long) employee);
            filterChain.doFilter(request,response);
            return;
        }
        if (Objects.nonNull(user)) {
            BaseContest.setCurrentId((Long) user);
            filterChain.doFilter(request,response);
            return;
        }

        log.info("拦截到请求:{}",request.getRequestURI());
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    public boolean check(String[] urls,String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }

}

