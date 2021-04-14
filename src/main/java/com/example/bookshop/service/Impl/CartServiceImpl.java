package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.*;
import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.CartService;
import com.example.bookshop.util.StringFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/10
 */

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;

    /**
     * 添加购物车项目
     * @param cart 待添加的购物车项目
     * @throws AddException 添加异常
     */
    @Override
    public void add(Cart cart) throws AddException
    {
        if(cart == null)
        {
            throw new AddException("添加购物车错误，购物车项目为空");
        }
        else if(StringFormatUtil.hasEmpty(cart.getUser()))
        {
            throw new AddException("添加购物车项目错误，书籍为空/用户为空");
        }
        else if(cart.getCount()<=0)
        {
            throw new AddException("添加购物车项目错误，数量错误");
        }

        int result;
        try
        {
            result = cartDao.add(cart);
        }catch (Exception e)
        {
            System.out.println("添加购物车项目失败");
            e.printStackTrace();
            throw new AddException("添加购物车项目失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("添加购物车项目成功");
    }

    /**
     * 删除购物车项目
     * @param id 待删除的购物车项目id
     * @throws DeleteException 删除异常
     */
    @Override
    public void delete(Serializable id) throws DeleteException
    {
        if(id == null)
        {
            throw new DeleteException("删除订单失败：订单为空");
        }

        int result;
        try
        {
            result = cartDao.delete(id);
        }catch(Exception e){
            System.out.println("删除购物车项目失败");
            e.printStackTrace();
            throw new DeleteException("删除购物车项目失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("删除购物车项目成功");

    }

    /**
     * 更新购物车项目
     * @param cart 待更新的购物车项目
     * @throws UpdateException 更新异常
     */
    @Override
    public void update(Cart cart) throws UpdateException
    {
        if(cart == null)
        {
            throw new UpdateException("更新购物车错误，购物车项目为空");
        }
        else if(StringFormatUtil.hasEmpty(cart.getUser()))
        {
            throw new UpdateException("更新购物车项目错误，书籍为空/用户为空");
        }
        else if(cart.getCount()<=0)
        {
            throw new UpdateException("更新购物车项目错误，数量错误");
        }

        int result;
        try
        {
            result = cartDao.update(cart);
        }catch (Exception e){
            System.out.println("更新购物车项目失败");
            e.printStackTrace();
            throw new UpdateException("更新购物车项目失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新购物车项目成功");
    }

    /**
     * 根据id查找购物车项目
     * @param id 待查找的购物车项目id
     * @return 待查找的购物车项目
     */
    @Override
    public Cart findOneById(Serializable id)
    {
        if(id == null)
            return null;
        return cartDao.findOneById(id);
    }

    /**
     * 查找所有购物车项目
     * @return 待查找的所有购物车项目集合
     */
    @Override
    public List<Cart> findAll()
    {
        return cartDao.findAll();
    }

    /**
     * 查找某一用户的购物车项目
     * @param user 该用户的id
     * @return 待查找的购物车项目集合
     */
    public List<Cart> findUserCart(Serializable user)
    {
        if(user == null)
            return null;
        return cartDao.findUserCart(user);
    }

    /**
     * 查找购物车项目详情
     * @param id 待查找的购物车项目id
     * @return 待查找的购物车项目集合
     */
    public Book findCartBookDetails(Serializable id)
    {
        if(id == null)
            return null;
        return cartDao.findCartBookDetails(id);
    }

    /**
     * 查找某一用户的所有购物车项目详情
     * @param user 待查找的用户id
     * @return 该用户的所有的购物车项目集合
     */
    public List<CartDetail> findCartDetailsByUser(Serializable user)
    {
        if(user == null)
            return null;
        List<Cart> cartList = findUserCart(user);
        List<CartDetail> cartDetailList = new ArrayList<>();
        for (Cart cart : cartList)
        {
            Book book = findCartBookDetails(cart.getId());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setId(cart.getId());
            cartDetail.setAuthor(book.getAuthor());
            cartDetail.setImage(book.getImage());
            cartDetail.setIntro(book.getIntro());
            cartDetail.setName(book.getName());
            cartDetail.setNation(book.getNation());
            cartDetail.setPrice(book.getPrice());
            cartDetail.setScore(book.getScore());
            cartDetail.setYear(book.getYear());
            cartDetail.setTotalPrice(book.getPrice() * cart.getCount());

            cartDetailList.add(cartDetail);
        }
        return cartDetailList;
    }
}