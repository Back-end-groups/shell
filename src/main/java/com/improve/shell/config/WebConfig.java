package com.improve.shell.config;

import com.improve.shell.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 处理CORS报错
 * 解决前后端交互的跨域问题
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry
//                //允许访问的路径
//                .addMapping("/**")
//                //配置请求来源
//                .allowedOrigins("allowedOriginPatterns")
//                //允许跨域访问的方法
//                .allowedMethods("GET","POST","DELETE","PUT","OPTION")
//                //允许存在请求头
//                .allowCredentials(true)
//                //最大效应时间
//                .maxAge(3600);
//    }

    //配置OrderedHiddenHttpMethodFilter
    @Bean
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new OrderedHiddenHttpMethodFilter();
    }


    @Autowired
    private LoginHandler loginHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandler);
    }
}
