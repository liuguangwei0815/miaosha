package com.im.miaosha.dto;

import com.im.miaosha.validation.IsPhone;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @program: miaosha
 * @description: 登录dto
 * @author: liu.wei
 * @create: 2020-04-18 11:26
 **/
@Data
public class LoginDto {
    @NotNull(message = "手机号码不能为空")
    @IsPhone
    private String mobile;
    @NotNull(message = "密码不能为空")
    @Length(max = 32, message = "密码最长长度不能超过32")
    private String password;
}
