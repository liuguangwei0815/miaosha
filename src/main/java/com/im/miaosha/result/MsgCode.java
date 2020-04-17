package com.im.miaosha.result;

import lombok.Data;

/**
 * @program: miaosha
 * @description: 错误代码和提示
 * @author: liu.wei
 * @create: 2020-04-17 10:37
 **/
@Data
public class MsgCode {

    private int code;
    private String msg;

    /**
     * 成功
     */
    public static MsgCode SUCCESS = new MsgCode(0, "sucess");
    /**
     * 服务器错误
     */
    public static MsgCode SERVER_ERROR = new MsgCode(500100, "服务端异常");
    //登陆模块异常 5002xx
    //商品模块异常 5003xx
    //订单模块异常 5004xx
    //秒杀模块异常 5005xx

    /**
     * 味精的代码
     *
     * @param code 代码
     * @param msg  味精
     */
    private MsgCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private MsgCode() {
    }

}
