package com.im.miaosha.utils;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-18 20:03
 **/
public class UUIDUtils {

    public static String getUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

}
