package com.im.miaosha.mvcconofig;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: miaosha
 * @description: 配置静态资源 这样子我们的classpath下的static呀才能访问
 * @author: liu.wei
 * @create: 2020-04-18 10:52
 **/
@Configuration
public class WebMvcSupport extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
        registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        super.addResourceHandlers(registry);
    }
}
