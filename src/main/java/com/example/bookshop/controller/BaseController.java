package com.example.bookshop.controller;

import com.example.bookshop.util.ReturnMsgUtil;

import java.io.Serializable;
import java.util.List;

public interface BaseController<T> {
    int successCode = 0; //返回码，成功：0
    int failCode = 1;    //返回码，失败：1

    /**
     * 添加对象
     * @param t 待添加的对象
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    ReturnMsgUtil add(T t);

    /**
     * 删除对象
     * @param id 待删除对象的id
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    ReturnMsgUtil delete(Serializable id);

    /**
     * 更新对象
     * @param t 待更新的对象
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    ReturnMsgUtil update(T t);

    /**
     * 通过id查找对象
     * @param id 待查找对象的id
     * @return 待查找的对象
     */
    T findOneById(Serializable id);

    /**
     * 查找所有的对象
     * @return 对象集合
     */
    List<T> findAll();
}
