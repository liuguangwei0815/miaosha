package com.im.miaosha.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @program: miaosha
 * @description: MD5工具类
 * @author: liu.wei
 * @create: 2020-04-18 08:56
 **/
public class MD5Utils {

    /**
     * md5加密 基础
     *
     * @param str str
     * @return {@link String}
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 盐
     */
    private static String salt = "1a2b3c4d";

    /**
     * 第一次MD5加密
     * 进行拼装和固定盐值,因为如果截取了获取的MD5反推的职位我们这个最终拼装的str 所以他反查出来的和我们实际的不准
     * ，但是js是可以被查看的 其实也是不安全的，可以做浏览器的插件等方法
     *
     * @param inputPw 前端输入密码
     * @return {@link String}
     */
    public static String inputPasswordToFormPass(String inputPw) {
        String finalString = "" + salt.charAt(0) + salt.charAt(2) + inputPw + salt.charAt(5) + salt.charAt(4);
        return md5(finalString);
    }

    /**
     * 转换为数据库密码
     *
     * @param formPw pw形式
     * @param salt   盐
     * @return {@link String}
     */
    public static String fromPassToDbPass(String formPw, String salt) {
        String finalString = "" + salt.charAt(0) + salt.charAt(2) + formPw + salt.charAt(5) + salt.charAt(4);
        return md5(finalString);
    }

    /**
     * 将前端密码转换为DB存储密码
     *
     * @param inputPw 输入pw
     * @param dbSalt  db盐
     * @return {@link String}
     */
    public static String inputPwToDbPw(String inputPw, String dbSalt) {
        //前端密码转为为formpw 其实就是通过固定严重拼装而已
        String formPw = inputPasswordToFormPass(inputPw);
        return fromPassToDbPass(formPw, dbSalt);
    }
    public static void main(String[] args) {
        System.out.println(inputPwToDbPw("123456","1a2b3c4d"));
    }
}
