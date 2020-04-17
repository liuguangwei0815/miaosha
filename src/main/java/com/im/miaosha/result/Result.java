package com.im.miaosha.result;

import lombok.Data;

/**
 * @program: miaosha
 * @description: 返回
 * @author: liu.wei
 * @create: 2020-04-17 10:34
 **/
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;


    /**
     * 获得成功
     *
     * @param data 数据
     * @return {@link Result<T>}
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }


    public static <T> Result<T> fail(MsgCode msgCode) {
        return new Result<>(msgCode);
    }

    private Result() {
    }

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(MsgCode msgCode) {
        if (msgCode == null) {
            return;
        }
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
    }
}
