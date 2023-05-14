//package com.improve.shell.config;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.constraints.NotNull;
//import java.io.IOException;
//
///**
// * @Author: fengxin
// * @CreateTime: 2023-05-12  21:55
// * @Description: TODO
// */
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Component
//@Slf4j
//public class RequestReplaceFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//
//        String contentType = request.getContentType();
//
//        request = this.addTokenForWebSocket(request, response);
//
//        filterChain.doFilter(request, response);
//    }
//
//    private HttpServletRequest addTokenForWebSocket(HttpServletRequest request, HttpServletResponse response) { ;
//        String token = request.getHeader("Authorization");
//        if(StrUtil.isNotBlank(token)) {
//            return request;
//        }
//        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
//        token = request.getHeader("sec-websocket-protocol");
//        if(StrUtil.isBlank(token)) {
//            return request;
//        }
//        requestWrapper.addHeader("Authorization", token);
//        response.addHeader("sec-websocket-protocol", token);
//        return  requestWrapper;
//    }
//}

