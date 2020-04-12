package com.alone.hotel.config.controller;

import com.alone.hotel.interceptor.AuthenticationInterceptor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.config.controller
 * @Author: Alone
 * @CreateTime: 2020-04-09 17:12
 * @Description:
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    /**
     * 配置静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源处理
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            registry.addResourceHandler("/upload/**").addResourceLocations("file:E:/proresources/images/hotel/upload/");
        } else {
            registry.addResourceHandler("/upload/**").addResourceLocations("file:/root/hotel/upload/");
        }
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册AuthenticationInterceptor
        InterceptorRegistration registration = registry.addInterceptor(authenticationInterceptor());
        //所有路径都被拦截
        registration.addPathPatterns("/**");
        //添加不拦截的路径
        //registration.excludePathPatterns();
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }
}
