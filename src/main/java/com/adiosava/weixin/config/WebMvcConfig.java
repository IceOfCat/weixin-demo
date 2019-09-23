package com.adiosava.weixin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * mvc配置
 */
@Configuration
@ComponentScan(WebMvcConfig.BASE_PACKAGE)
@Slf4j
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	static final String BASE_PACKAGE = "com.adiosava.weixin.controller";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
