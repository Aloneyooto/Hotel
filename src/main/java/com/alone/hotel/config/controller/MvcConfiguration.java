package com.alone.hotel.config.controller;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
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
}
