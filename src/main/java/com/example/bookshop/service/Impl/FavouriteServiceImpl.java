package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.*;
import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.FavouriteService;
import com.example.bookshop.util.StringFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/13
 */

@Service
public class FavouriteServiceImpl implements FavouriteService{
    @Autowired
    FavouriteDao favouriteDao;

    /**
     * 添加收藏
     * @param favourite 待添加的收藏
     * @throws AddException 添加异常
     */
    @Override
    public void add(Favourite favourite) throws AddException
    {
        if(favourite == null)
        {
            throw new AddException("添加收藏错误，收藏项目为空");
        }
        else if(StringFormatUtil.hasEmpty(favourite.getUser())||StringFormatUtil.hasEmpty(favourite.getBook())
                ||StringFormatUtil.hasEmpty(String.valueOf(favourite.getTime())))
        {
            throw new AddException("添加收藏错误，书籍为空/用户为空/时间为空");
        }

        int result;
        try
        {
            result = favouriteDao.add(favourite);
        }catch (Exception e){
            System.out.println("添加收藏失败");
            e.printStackTrace();
            throw new AddException("添加收藏失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("添加收藏成功");
    }

    /**
     * 删除收藏
     * @param id 待删除的收藏项目id
     * @throws DeleteException 删除异常
     */
    @Override
    public void delete(Serializable id) throws DeleteException
    {
        if(id == null)
        {
            throw new DeleteException("删除收藏错误，收藏为空");
        }

        int result;
        try
        {
            result = favouriteDao.delete(id);
        }catch (Exception e){
            System.out.println("删除收藏失败");
            e.printStackTrace();
            throw new DeleteException("删除收藏失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("删除收藏成功");
    }

    /**
     * 更新收藏
     * @param favourite 待更新的收藏项目
     * @throws UpdateException 更新异常
     */
    @Override
    public void update(Favourite favourite) throws UpdateException
    {
        if(favourite == null)
        {
            throw new UpdateException("更新收藏错误，收藏项目为空");
        }
        else if(StringFormatUtil.hasEmpty(favourite.getUser())||StringFormatUtil.hasEmpty(favourite.getBook())
                ||StringFormatUtil.hasEmpty(String.valueOf(favourite.getTime())))
        {
            throw new UpdateException("更新收藏错误，书籍为空/用户为空/时间为空");
        }

        int result;
        try
        {
            result = favouriteDao.update(favourite);
        }catch (Exception e){
            System.out.println("更新收藏失败");
            e.printStackTrace();
            throw new UpdateException("更新收藏失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新收藏成功");
    }

    /**
     * 将书籍收藏状态设置为已收藏
     * @param id 待设置的书籍id
     * @return 受影响的行数
     */
    public void updateFavouriteTrue(Serializable id) throws UpdateException
    {
        if(id == null)
        {
            throw new UpdateException("更新收藏状态错误，书籍为空");
        }

        int result;
        try
        {
            result = favouriteDao.updateFavouriteTrue(id);
        }catch (Exception e){
            System.out.println("更新收藏状态失败");
            e.printStackTrace();
            throw new UpdateException("更新收藏状态失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新收藏状态成功");
    }

    /**
     * 将书籍收藏状态设置为未收藏
     * @param id 待设置的书籍id
     * @return 受影响的行数
     */
    public void updateFavouriteFalse(Serializable id) throws UpdateException
    {
        if(id == null)
        {
            throw new UpdateException("更新收藏状态错误，书籍为空");
        }

        int result;
        try
        {
            result = favouriteDao.updateFavouriteFalse(id);
        }catch (Exception e){
            System.out.println("更新收藏状态失败");
            e.printStackTrace();
            throw new UpdateException("更新收藏状态失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新收藏状态成功");
    }

    /**
     * 根据id查找收藏项目
     * @param id 待查找的收藏项目id
     * @return 待查找的收藏项目
     */
    @Override
    public Favourite findOneById(Serializable id)
    {
        return favouriteDao.findOneById(id);
    }

    /**
     * 查找所有收藏项目
     * @return 待查找的收藏项目集合
     */
    @Override
    public List<Favourite> findAll()
    {
        return favouriteDao.findAll();
    }

    /**
     * 查找某一用户的收藏项目
     * @param user 待查找的用户id
     * @return 待查找的收藏项目集合
     */
    public List<Favourite> findUserFavourite(Serializable user)
    {
        return favouriteDao.findUserFavourite(user);
    }

    /**
     * 查找收藏项目详情
     * @param id 待查找的收藏项目id
     * @return 待查找的收藏项目集合
     */
    public Book findFavouriteBookDetails(Serializable id)
    {
        return favouriteDao.findFavouriteBookDetails(id);
    }

    /**
     * 查找某一用户的收藏详情
     * @param user 待查找的用户id
     * @return 待查找的收藏详情集合
     */
    public List<FavouriteDetail> findFavouriteDetailsByUser(Serializable user)
    {
        List<Favourite> favouriteList = favouriteDao.findUserFavourite(user);
        List<FavouriteDetail> favouriteDetailList = new ArrayList<>();
        for (Favourite favourite : favouriteList)
        {
            Book book = favouriteDao.findFavouriteBookDetails(favourite.getId());
            FavouriteDetail favouriteDetail = new FavouriteDetail();
            favouriteDetail.setId(favourite.getId());
            favouriteDetail.setAuthor(book.getAuthor());
            favouriteDetail.setImage(book.getImage());
            favouriteDetail.setIntro(book.getIntro());
            favouriteDetail.setName(book.getName());
            favouriteDetail.setNation(book.getNation());
            favouriteDetail.setPrice(book.getPrice());
            favouriteDetail.setScore(book.getScore());

            favouriteDetailList.add(favouriteDetail);
        }
        return favouriteDetailList;
    }

    public void find(String imageUrl,String image)
    {
        String image1 = "%"+image;
        favouriteDao.find(imageUrl,image1);
    }
}