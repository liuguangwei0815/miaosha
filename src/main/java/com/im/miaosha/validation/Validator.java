package com.im.miaosha.validation;

import com.im.miaosha.utils.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-18 13:58
 **/
public class Validator implements ConstraintValidator<IsPhone,String> {

    private boolean require;

    /**
     * 初始化 通过这个可以获取这个注解的任何信息
     *
     * @param constraintAnnotation 约束注释
     */
    @Override
    public void initialize(IsPhone constraintAnnotation) {
        require = constraintAnnotation.require();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(require){
            return ValidatorUtil.validatorPhone(value);
        }else{
            return true;
        }
    }
}
