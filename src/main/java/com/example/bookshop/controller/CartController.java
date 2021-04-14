package com.example.bookshop.controller;

import com.example.bookshop.domain.*;
import com.example.bookshop.util.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.CartService;
import com.example.bookshop.util.ReturnMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Anran
 * @date 2020/4/10
 */

@RestController
@RequestMapping("/cart")
public class CartController implements BaseController<Cart>{
    @Autowired
    private CartService cartService;

    /**
     * 添加购物车项目 URL: /cart/add
     * @param cart 待添加的购物车项目
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ReturnMsgUtil add(@RequestBody Cart cart)
    {
        //生成5位随机id
        cart.setId(StringFormatUtil.randomString());
        //查找该用户的所有购物车项目
        List<Cart> cartList = cartService.findUserCart(cart.getUser());
        //获取这些购物车项目包含的书籍和对应的数目
        Map<Integer,Integer> bookMap = new HashMap<>();
        Map<Integer,String> bookMap1 = new HashMap<>();
        for (Cart value : cartList)
        {
            bookMap.put(value.getBook(), value.getCount());
            bookMap1.put(value.getBook(),value.getId());
        }

        try
        {
            //购物车中已经包含了该书籍
            if(bookMap.containsKey(cart.getBook()))
            {
                //将数目设置为新添加的和之前已经存在的和
                cart.setCount(bookMap.get(cart.getBook())+cart.getCount());
                //将id设置为以前存在的id
                cart.setId(bookMap1.get(cart.getBook()));
                cartService.update(cart);
            }
            //购物车中未包含该书籍
            else
            {
                cartService.add(cart);
            }
            return new ReturnMsgUtil(successCode,cart.getId());
        }catch (AddException | UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 删除购物车项目 URL: /cart/delete
     * @param id 待删除的购物车项目
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ReturnMsgUtil delete(@RequestParam(value = "id")Serializable id)
    {
        try
        {
            cartService.delete(id);
            return new ReturnMsgUtil(successCode,"success");
        }catch (DeleteException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 更新购物车项目 URL: /cart/update
     * @param cart 待更新的购物车项目
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ReturnMsgUtil update(@RequestBody Cart cart)
    {
        try
        {
            cartService.update(cart);
            return new ReturnMsgUtil(successCode,"success");
        }catch (UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 根据id查找购物车项目 URL: /cart/findOneById
     * @param id 待查找的购物车项目id
     * @return 待查找的购物车项目
     */
    @RequestMapping(value = "/findOneById",method = RequestMethod.GET)
    public Cart findOneById(@RequestParam(value = "id")Serializable id)
    {
        return cartService.findOneById(id);
    }

    /**
     * 查找所有购物车项目 URL: /cart/findAll
     * @return 待查找的所有购物车项目集合
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Cart> findAll()
    {
        return cartService.findAll();
    }

    /**
     * 查找某一用户的购物车项目 URL: /cart/findUserCart
     * @param user 该用户的id
     * @return 待查找的购物车项目集合
     */
    @RequestMapping(value = "/findUserCart",method = RequestMethod.GET)
    public List<Cart> findUserCart(@RequestParam(value = "user")Serializable user)
    {
        return cartService.findUserCart(user);
    }

    /**
     * 查找购物车项目详情 URL: /cart/findCartBookDetails
     * @param id 待查找的购物车项目id
     * @return 待查找的购物车项目集合
     */
    @RequestMapping(value = "/findCartBookDetails",method = RequestMethod.GET)
    public Book findCartBookDetails(@RequestParam(value = "id")Serializable id)
    {
        return cartService.findCartBookDetails(id);
    }

    /**
     * 查找某一用户的所有购物车项目详情 URL: /cart/findCartDetailsByUser
     * @param user 待查找的用户id
     * @return 该用户的所有的购物车项目集合
     */
    @RequestMapping(value = "/findCartDetailsByUser",method = RequestMethod.GET)
    public List<CartDetail> findCartDetailsByUser(@RequestParam(value = "user")Serializable user)
    {
        return cartService.findCartDetailsByUser(user);
    }
}