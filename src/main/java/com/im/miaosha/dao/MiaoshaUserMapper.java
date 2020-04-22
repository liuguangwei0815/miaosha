package com.im.miaosha.dao;

import com.im.miaosha.model.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-18 12:35
 **/
@Mapper
public interface MiaoshaUserMapper {

    /**
     * 通过id得到用户
     *
     * @param id id
     * @return {@link MiaoshaUser}
     */
    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getUserById(@Param("id") Long id);


    /**
     * 让用户通过用户电话
     *
     * @param userPhone 用户的电话
     * @return {@link MiaoshaUser}
     */
    @Select("select * from miaosha_user where user_phone = #{userPhone}")
    public MiaoshaUser getUserByUserPhone(@Param("userPhone") String userPhone);


    /**
     * 更新通过id
     *
     * @param user 用户
     * @return boolean
     */
    @Update("update miaosha_user set password = #{password}  where id = #{id}")
    void updateById(MiaoshaUser user);
}
