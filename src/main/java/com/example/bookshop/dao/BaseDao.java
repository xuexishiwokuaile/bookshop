package com.example.bookshop.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    /**
     * 添加对象
     * @param t 待添加的对象
     * @return 返回受影响的行数
     */
    int add(T t);

    /**
     * 删除对象
     * @param id 待删除对象的id
     * @return 受影响的行数
     */
    int delete(Serializable id);

    /**
     * 更新对象
     * @param t 待更新对象
     * @return 受影响行数
     */
    int update(T t);

    /**
     * 根据id查找对象
     * @param id 待查找对象的id
     * @return 待查找对象
     */
    T findOneById(Serializable id);

    /**
     * 查找所有的对象
     * @return 所有对象的集合
     */
    List<T> findAll();
}
