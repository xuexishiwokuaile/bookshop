package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.*;
import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.AddressService;
import com.example.bookshop.util.StringFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/18
 */

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressDao addressDao;

    /**
     * 添加地址
     * @param address 待添加的地址
     * @throws AddException 添加异常
     */
    @Override
    public void add(Address address) throws AddException
    {
        if(address == null)
        {
            throw new AddException("添加地址错误，地址为空");
        }
        else if(StringFormatUtil.hasEmpty(address.getNation())||StringFormatUtil.hasEmpty(address.getProvince())
                ||StringFormatUtil.hasEmpty(address.getCity())||StringFormatUtil.hasEmpty(address.getDetails()))
        {
            throw new AddException("添加地址错误，相关信息为空");
        }
        else if(StringFormatUtil.hasEmpty(address.getUser())||StringFormatUtil.hasEmpty(address.getReceiver())
                ||StringFormatUtil.hasEmpty(address.getTel()))
        {
            throw new AddException("添加地址错误，用户/收货人/收货人电话为空");
        }

        int result;
        try
        {
            result = addressDao.add(address);
        }catch (Exception e){
            System.out.println("添加地址失败");
            e.printStackTrace();
            throw new AddException("添加地址失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("添加地址成功");
    }

    /**
     * 删除地址
     * @param id 待删除的地址id
     * @throws DeleteException 添加异常
     */
    @Override
    public void delete(Serializable id) throws DeleteException
    {
        if(id == null)
        {
            throw new DeleteException("删除地址错误，地址为空");
        }

        int result;
        try
        {
            result = addressDao.delete(id);
        }catch (Exception e){
            System.out.println("删除地址失败");
            e.printStackTrace();
            throw new DeleteException("删除地址失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("删除地址成功");
    }


    /**
     * 更新地址
     * @param address 待更新的地址
     * @throws UpdateException 添加异常
     */
    @Override
    public void update(Address address) throws UpdateException
    {
        if(address == null)
        {
            throw new UpdateException("更新地址错误，地址为空");
        }
        else if(StringFormatUtil.hasEmpty(address.getNation())||StringFormatUtil.hasEmpty(address.getProvince())
                ||StringFormatUtil.hasEmpty(address.getCity())||StringFormatUtil.hasEmpty(address.getDetails()))
        {
            throw new UpdateException("更新地址错误，相关信息为空");
        }
        else if(StringFormatUtil.hasEmpty(address.getUser())||StringFormatUtil.hasEmpty(address.getReceiver())
                ||StringFormatUtil.hasEmpty(address.getTel()))
        {
            throw new UpdateException("添加地址错误，用户/收货人/收货人电话为空");
        }

        int result;
        try
        {
            result = addressDao.update(address);
        }catch (Exception e){
            System.out.println("更新地址失败");
            e.printStackTrace();
            throw new UpdateException("更新地址失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新地址成功");
    }

    /**
     * 根据id查找地址
     * @param id 待查找的地址id
     * @return 待查找的地址
     */
    @Override
    public Address findOneById(Serializable id)
    {
        return addressDao.findOneById(id);
    }

    /**
     * 查找所有地址
     * @return 待查找的地址集合
     */
    @Override
    public List<Address> findAll()
    {
        return addressDao.findAll();
    }

    /**
     * 设置为非默认地址
     * @param id 地址id
     * @throws UpdateException 添加异常
     */
    public void updateIsDefaultFalse(Serializable id) throws UpdateException
    {
        if(id == null)
        {
            throw new UpdateException("更新地址失败");
        }

        int result;
        try
        {
            result = addressDao.updateIsDefaultFalse(id);
        }catch (Exception e){
            System.out.println("更新地址失败");
            e.printStackTrace();
            throw new UpdateException("更新地址失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新地址成功");
    }

    /**
     * 设置为默认地址
     * @param id 地址id
     * @throws UpdateException 添加异常
     */
    public void updateIsDefaultTrue(Serializable id) throws UpdateException
    {
        if(id == null)
        {
            throw new UpdateException("更新地址失败");
        }

        int result;
        try
        {
            result = addressDao.updateIsDefaultTrue(id);
        }catch (Exception e){
            System.out.println("更新地址失败");
            e.printStackTrace();
            throw new UpdateException("更新地址失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新地址成功");
    }

    /**
     * 查找某一用户所有的地址
     * @param user 用户id
     * @return 地址集合
     */
    public List<Address> findUserAddress(Serializable user)
    {
        return addressDao.findUserAddress(user);
    }

}