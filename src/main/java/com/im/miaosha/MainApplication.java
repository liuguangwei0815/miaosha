package com.im.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

/**
 * @author liuwei
 */
@SpringBootApplication
@MapperScan("com.im.miaosha.dao")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MainApplication.class);
        springApplication.setBanner(new ResourceBanner(new ClassPathResource("banner_bak.txt")));
        springApplication.run(args);
        //SpringApplication.run(MainApplication.class,args);
    }
}
