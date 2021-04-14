package com.example.bookshop.service;

import com.example.bookshop.domain.User;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.exception.UserException;

import java.io.Serializable;
import java.util.List;

public interface UserService extends BaseService<User> {
    /**
     * 添加用户
     * @param user 待添加的对象
     * @throws AddException 各类添加异常
     */
    void add(User user) throws AddException;

    /**
     * 删除用户
     * @param id 待删除对象的id
     * @throws DeleteException id不存在导致的删除异常或其他异常
     */
    void delete(Serializable id) throws DeleteException;

    /**
     * 更新用户
     * @param user 待更新对象
     * @throws UpdateException 各类更新异常
     */
    void update(User user) throws UpdateException;

    /**
     * 通过ID查找一个用户
     * @param id 待查询的对象的ID
     * @return 返回该ID对应的对象
     */
    User findOneById(Serializable id);

    User findOneByName(String name);

    /**
     * 查找全部用户
     * @return  返回对象集合
     */
    List<User> findAll();


    int getAuthorityById(Serializable id);

    int getAuthorityByName(String name);

    void login(String name, String password) throws UserException;
}
