package com.example.bookshop.service;

import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/11
 */
public interface OrderService extends BaseService<Order> {
    void add(Order order) throws AddException;

    void addItem(Orderitem orderitem) throws AddException;

    void delete(Serializable id) throws DeleteException;

    void deleteItem(Serializable id) throws DeleteException;

    void update(Order order) throws UpdateException;

    void updateItem(Orderitem orderitem) throws UpdateException;

    Order findOneById(Serializable id);

    Orderitem findOneItemById(Serializable id);

    List<Order> findAll();

    List<Orderitem> findAllItem();

    List<Orderitem> findOrderItem(Serializable order);

    List<Order> findBuyerOrder(Serializable buyer);

    Book findItemBookDetails(Serializable id);

    Address findOrderAddressDetails(Serializable id);

    User findOrderBuyerDetails(Serializable id);

    List<OrderDetail> findOrderDetailsByBuyer(Serializable buyer);

    List<OrderDetail> findItemDetailsByOrder(Serializable order);

    int findOrderTotalCount(Serializable order);

    int findYearTotalCount(String year);

    int findMonthTotalCount(String month);

    int findDayTotalCount(String day);

    double findYearTotalPrice(String year);

    double findMonthTotalPrice(String month);

    double findDayTotalPrice(String day);

    List<String> findRecentYear();

    List<String> findRecentMonth();

    List<String> findRecentDay();

    List<List<String>> findRecentYearCount();

    List<List<String>> findRecentMonthCount();

    List<List<String>> findRecentDayCount();

    List<List<String>> findRecentYearPrice();

    List<List<String>> findRecentMonthPrice();

    List<List<String>> findRecentDayPrice();

    int findTotalCount();

    double findTotalPrice();
}
