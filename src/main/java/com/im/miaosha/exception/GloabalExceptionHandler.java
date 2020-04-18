package com.im.miaosha.exception;

import com.im.miaosha.result.MsgCode;
import com.im.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: miaosha
 * @description: 全局异常
 * @author: liu.wei
 * @create: 2020-04-18 14:39
 **/
@ControllerAdvice
@ResponseBody
public class GloabalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        //业务类异常拦截
        if (e instanceof BusinessException) {
            MsgCode msgCode = ((BusinessException) e).getMsgCode();
            return Result.fail(msgCode);
        }
        //如果异常错误类绑定异常进行拦截
        else if (e instanceof BindException) {
            List<ObjectError> allErrors = ((BindException) e).getAllErrors();
            ObjectError objectError = allErrors.get(0);
            return Result.fail(MsgCode.ABNORMALPARAMETERBINDING.fillArgs(objectError.getDefaultMessage()));
        } else {
            return Result.fail(MsgCode.SERVER_ERROR);
        }
    }
}
