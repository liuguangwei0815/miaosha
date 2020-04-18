package com.im.miaosha.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @program: miaosha
 * @description: 参数校验
 * @author: liu.wei
 * @create: 2020-04-18 11:35
 **/
public class ValidatorUtil {

    /**
     * 手机验证器
     *
     * @param phone 电话
     * @return boolean
     */
    public static boolean validatorPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        return PATTERN.matcher(phone).matches();
    }

    /**
     * 配置简单的正则，需要1开头后面根10个数据即可
     */
    private static final Pattern PATTERN = Pattern.compile("1\\d{10}");

    public static void main(String[] args) {
        System.out.println(validatorPhone("123123"));
        System.out.println(validatorPhone("13294762381"));

    }





}
