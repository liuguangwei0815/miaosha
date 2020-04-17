package com.im.miaosha.model;

import lombok.Data;

/**
 * 用户
 *
 * @author liuwei
 * @date 2020/04/17
 */
@Data
public class User {

    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 爱好
     */
    private String hobby;
}
