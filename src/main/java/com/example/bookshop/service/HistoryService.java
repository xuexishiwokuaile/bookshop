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
public interface HistoryService extends BaseService<History>{
    void add(History history) throws AddException;

    void delete(Serializable id) throws DeleteException;

    void update(History history) throws UpdateException;

    History findOneById(Serializable id);

    List<History> findAll();

    List<History> findUserHistory(Serializable user);

    Book findHistoryBookDetails(Serializable id);

    List<HistoryDetail> findHistoryDetailsByUser(Serializable user);

}
