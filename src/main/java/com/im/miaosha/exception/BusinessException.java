package com.im.miaosha.exception;

import com.im.miaosha.result.MsgCode;
import lombok.Data;

/**
 * @program: miaosha
 * @description: 业务异常
 * @author: liu.wei
 * @create: 2020-04-18 15:09
 **/
@Data
public class BusinessException extends RuntimeException {
    private MsgCode msgCode;

    public BusinessException(MsgCode msgCode) {
        this.msgCode = msgCode;
    }
}
