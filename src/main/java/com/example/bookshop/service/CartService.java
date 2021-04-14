package com.example.bookshop.service;

import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/10
 */
public interface CartService extends BaseService<Cart>{
    void add(Cart cart) throws AddException;

    void delete(Serializable id) throws DeleteException;

    void update(Cart cart) throws UpdateException;

    Cart findOneById(Serializable id);

    List<Cart> findAll();

    List<Cart> findUserCart(Serializable user);

    Book findCartBookDetails(Serializable id);

    List<CartDetail> findCartDetailsByUser(Serializable user);
}
