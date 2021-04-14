package com.example.bookshop.service;

import com.example.bookshop.domain.Type;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alu
 * @date 2020/4/17 0:29
 */
public interface TypeService extends BaseService<Type> {
    @Override
    void add(Type type) throws AddException;

    @Override
    void delete(Serializable id) throws DeleteException;

    @Override
    void update(Type type) throws UpdateException;

    @Override
    Type findOneById(Serializable id);

    @Override
    List<Type> findAll();

    int getTypeCount();

    Type findOneByName(String name);

    int[] getTypeIds();

    String getENameById(Serializable id);

    String getCNameById(Serializable id);
}
