package com.example.bookshop.service;

import com.example.bookshop.domain.Address;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/18
 */
public interface AddressService extends BaseService<Address> {
    void add(Address address) throws AddException;

    void delete(Serializable id) throws DeleteException;

    void update(Address address) throws UpdateException;

    Address findOneById(Serializable id);

    List<Address> findAll();

    void updateIsDefaultFalse(Serializable id) throws UpdateException;

    void updateIsDefaultTrue(Serializable id) throws UpdateException;

    List<Address> findUserAddress(Serializable user);
}
