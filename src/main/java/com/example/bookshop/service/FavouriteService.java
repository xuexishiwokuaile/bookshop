package com.example.bookshop.service;

import com.example.bookshop.domain.Book;
import com.example.bookshop.domain.Favourite;
import com.example.bookshop.domain.FavouriteDetail;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/13
 */
public interface FavouriteService extends BaseService<Favourite> {
    void add(Favourite favourite) throws AddException;

    void delete(Serializable id) throws DeleteException;

    void update(Favourite favourite) throws UpdateException;

    void updateFavouriteTrue(Serializable id) throws UpdateException;

    void updateFavouriteFalse(Serializable id) throws UpdateException;

    Favourite findOneById(Serializable id);

    List<Favourite> findAll();

    List<Favourite> findUserFavourite(Serializable user);

    Book findFavouriteBookDetails(Serializable id);

    List<FavouriteDetail> findFavouriteDetailsByUser(Serializable user);

    void find(String imageUrl,String image);
}
