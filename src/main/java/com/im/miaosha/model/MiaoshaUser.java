package com.im.miaosha.model;

import java.util.Date;

/**
 * 实体类
 *
 * @author liu.wei
 * @since 1.0  2020-4-18 12:27:50
 */
public class MiaoshaUser extends BaseModel {

    private Long id; //

    private String nickname; //

    private String password; // MD5(MD5(pw明文+salt)+salt)

    private String salt; //

    private String head; // 头像 云存储id

    private Date registerDate; //

    private Date lastLoginDate; // 上次登录时间

    private Long loginCount; // 登录次数

    private String userPhone;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public final void setId(Long value) {
        this.id = value;
    }

    public final Long getId() {
        return this.id;
    }

    public final void setNickname(String value) {
        this.nickname = value;
    }

    public final String getNickname() {
        if (this.nickname == null) {
            this.nickname = "";
        }
        return this.nickname;
    }

    public final void setPassword(String value) {
        this.password = value;
    }

    public final String getPassword() {
        if (this.password == null) {
            this.password = "";
        }
        return this.password;
    }

    public final void setSalt(String value) {
        this.salt = value;
    }

    public final String getSalt() {
        if (this.salt == null) {
            this.salt = "";
        }
        return this.salt;
    }

    public final void setHead(String value) {
        this.head = value;
    }

    public final String getHead() {
        if (this.head == null) {
            this.head = "";
        }
        return this.head;
    }

    public final void setRegisterDate(Date value) {
        this.registerDate = value;
    }

    public final Date getRegisterDate() {
        return this.registerDate;
    }

    public final void setLastLoginDate(Date value) {
        this.lastLoginDate = value;
    }

    public final Date getLastLoginDate() {
        return this.lastLoginDate;
    }

    public final void setLoginCount(Long value) {
        this.loginCount = value;
    }

    public final Long getLoginCount() {
        return this.loginCount;
    }

}
