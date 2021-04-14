package com.example.bookshop.dao;

import com.example.bookshop.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

//@Mapper
@Repository
public interface UserDao extends BaseDao<User> {
    /**
     * 添加用户
     * @param user 待添加的对象
     * @return  返回受影响的行数
     */
    @Insert("INSERT INTO user (`id`,`name`,`password`,`tel`,`authority`) " +
            "VALUES (#{id}, #{name}, #{password}, #{tel}, 1)")
    int add(User user);

    /**
     * 删除用户
     * @param id 待删除对象的id
     * @return 返回受影响的行数
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int delete(Serializable id);

    /**
     * 更新用户
     * @param user 待更新对象
     * @return 返回受影响的行数
     */
    @Update("UPDATE user SET " +
            "name = #{name}, password = #{password}, tel = #{tel}, authority = #{authority} " +
            "WHERE id = #{id}")
    int update(User user);

    /**
     * 通过ID查找一个用户
     * @param id    待查询的对象的ID
     * @return  返回该ID对应的对象
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findOneById(Serializable id);

    /**
     * 查找全部用户
     * @return  返回对象集合
     */
    @Select("SELECT * FROM user")
    List<User> findAll();

    /**
     * 通过id获取用户权限
     * @param id id
     * @return 用户权限
     */
    @Select("SELECT authority FROM user WHERE id = #{id}")
    int getAuthorityById(Serializable id);

    /**
     * 通过name获取用户权限
     * @param name name
     * @return 用户权限
     */
    @Select("SELECT authority FROM user WHERE name=#{name}")
    int getAuthorityByName(String name);

    /**
     * 通过name查找一个用户
     * @param name 待查询的对象的ID
     * @return  返回该name对应的对象
     */
    @Select("SELECT * FROM `user` WHERE name=#{name}")
    User findOneByName(String name);
}
