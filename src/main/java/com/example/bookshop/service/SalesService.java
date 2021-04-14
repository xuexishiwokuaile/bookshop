package com.example.bookshop.service;

import com.example.bookshop.domain.Book;
import com.example.bookshop.domain.Sales;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alu
 * @date 2020/4/8 21:22
 */
public interface SalesService extends BaseService<Sales> {
    /**
     * 添加书籍库存
     * @param sales 待添加的对象
     * @throws AddException 各类添加异常
     */
    void add(Sales sales) throws AddException;

    void delete(Serializable book) throws DeleteException;

    void update(Sales sales) throws UpdateException;

    Sales findOneById(Serializable book);

    List<Sales> findAll();

    int findStockById(Serializable book);

    List<Book> findHot();


}
