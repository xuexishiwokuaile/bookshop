package com.example.bookshop.service;

import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
    /**
     * 添加对象
     * @param t 待添加的对象
     * @throws AddException 各类添加异常
     */
    void add(T t) throws AddException;

    /**
     * 删除对象
     * @param id 待删除的对象的id
     * @throws DeleteException 各类删除异常
     */
    void delete(Serializable id) throws DeleteException;

    /**
     * 更新对象
     * @param t 待更新的对象
     * @throws UpdateException 各类更新异常
     */
    void update(T t) throws UpdateException;

    /**
     * 根据id查找对象
     * @param id 待查找对象的id
     * @return 待查找的对象
     */
    T findOneById(Serializable id);

    /**
     * 查找所有对象
     * @return 所有对象的集合
     */
    List<T> findAll();
}
