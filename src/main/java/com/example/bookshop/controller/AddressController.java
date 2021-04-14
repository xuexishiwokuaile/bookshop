package com.example.bookshop.controller;

import com.example.bookshop.domain.*;
import com.example.bookshop.util.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.AddressService;
import com.example.bookshop.util.ReturnMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/18
 */

@RestController
@RequestMapping("/address")
public class AddressController implements BaseController<Address> {
    @Autowired
    private AddressService addressService;

    /**
     * 添加地址 URL: /address/add
     * @param address 待添加的地址
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ReturnMsgUtil add(@RequestBody Address address)
    {
        //生成5位随机id
        address.setId(StringFormatUtil.randomString());
        try
        {
            addressService.add(address);
            //找出该用户的所有地址
            List<Address> addressList = addressService.findUserAddress(address.getUser());
            //如果只有这一个地址，则设置为默认地址
            if(addressList.size() == 1)
            {
                addressService.updateIsDefaultTrue(address.getId());
            }
            //如果不只有这一个地址
            else
            {
                addressService.updateIsDefaultFalse(address.getId());
            }
            return new ReturnMsgUtil(successCode,address.getId());
        }catch (AddException | UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 删除地址 URL: /address/delete
     * @param id 待删除的地址id
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ReturnMsgUtil delete(@RequestParam(value = "id")Serializable id)
    {
        try
        {
            addressService.delete(id);
            return new ReturnMsgUtil(successCode,"success");
        }catch (DeleteException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 更新地址 URL: /address/update
     * @param address 待更新的地址
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ReturnMsgUtil update(@RequestBody Address address)
    {
        try
        {
            addressService.update(address);
            return new ReturnMsgUtil(successCode,"success");
        }catch (UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 根据id查找地址 URL: /address/findOneById
     * @param id 待查找的地址id
     * @return 待查找的地址
     */
    @RequestMapping(value = "/findOneById",method = RequestMethod.GET)
    public Address findOneById(@RequestParam(value = "id")Serializable id)
    {
        return addressService.findOneById(id);
    }

    /**
     * 查找所有地址 URL: /address/findAll
     * @return 待查找的地址集合
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Address> findAll()
    {
        return addressService.findAll();
    }

    /**
     * 查找某一用户所有的地址 URL: /address/findUserAddress
     * @param user 用户id
     * @return 地址集合
     */
    @RequestMapping(value = "/findUserAddress",method = RequestMethod.GET)
    public List<Address> findUserAddress(@RequestParam(value = "user")Serializable user)
    {
        return addressService.findUserAddress(user);
    }

    /**
     * 设置默认地址 URL: /address/updateDefault
     * @param id 待设置的地址id
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/updateDefault",method = RequestMethod.PUT)
    public ReturnMsgUtil updateDefault(@RequestParam(value = "id")Serializable id)
    {
        try
        {
            //找出该地址对应的用户
            String user = addressService.findOneById(id).getUser();
            //找出该用户所有的地址
            List<Address> addressList = addressService.findUserAddress(user);

            //仅将当前地址设置为默认地址，该用户的其他地址全部设置为非默认地址
            for (Address address : addressList)
            {
                addressService.updateIsDefaultFalse(address.getId());
            }
            addressService.updateIsDefaultTrue(id);

            return new ReturnMsgUtil(successCode,"success");
        }catch (UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }
}