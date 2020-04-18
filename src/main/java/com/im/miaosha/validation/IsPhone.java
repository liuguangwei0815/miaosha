package com.im.miaosha.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @program: miaosha
 * @description: 校验手机号码
 * @author: liu.wei
 * @create: 2020-04-18 13:49
 **/
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(value = IsPhone.List.class)
@Documented
@Constraint(validatedBy = {Validator.class})
public @interface IsPhone {
    //默认他必须得有
    boolean require() default true;
    String message() default "手机号码格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IsPhone[] value();
    }
}
