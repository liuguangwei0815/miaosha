package com.im.miaosha.dao;

import com.im.miaosha.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 用户Dao
 *
 * @author liuwei
 * @date 2020/04/17
 */
@Mapper
public interface UserMapper {

    /**
     * 通过id获取发行者
     *
     * @param id id
     * @return {@link User}
     */
    @Select("select * from user where id = #{id}")
    public User getUerById(@Param("id")Integer id);
}
