package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.*;
import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.HistoryService;
import com.example.bookshop.util.StringFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/11
 */

@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    HistoryDao historyDao;

    /**
     * 添加历史记录
     * @param history 待添加的历史记录
     * @throws AddException 添加异常
     */
    @Override
    public void add(History history) throws AddException
    {
        if(history == null)
        {
            throw new AddException("添加历史记录错误，购物车项目为空");
        }
        else if(StringFormatUtil.hasEmpty(history.getUser())
                ||history.getTime()==null)
        {
            throw new AddException("添加历史记录错误，书籍为空/用户为空/时间为空");
        }

        int result;
        try
        {
            result = historyDao.add(history);
        }catch (Exception e){
            System.out.println("添加历史记录失败");
            e.printStackTrace();
            throw new AddException("添加历史记录失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("添加历史记录成功");
    }

    /**
     * 删除历史记录
     * @param id 待删除的历史记录
     * @throws DeleteException 删除异常
     */
    @Override
    public void delete(Serializable id) throws DeleteException
    {
        if(id == null)
        {
            throw new DeleteException("删除历史记录错误，历史记录为空");
        }

        int result;
        try
        {
            result = historyDao.delete(id);
        }catch (Exception e){
            System.out.println("删除历史记录失败");
            e.printStackTrace();
            throw new DeleteException("删除历史记录失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("删除历史记录成功");
    }

    /**
     * 更新历史记录
     * @param history 待更新的历史记录
     * @throws UpdateException 更新异常
     */
    @Override
    public void update(History history) throws UpdateException
    {
        if(history == null)
        {
            throw new UpdateException("更新历史记录错误，购物车项目为空");
        }
        else if(StringFormatUtil.hasEmpty(history.getUser())
                ||history.getTime() == null)
        {
            throw new UpdateException("更新历史记录错误，书籍为空/用户为空/时间为空");
        }

        int result;
        try
        {
            result = historyDao.update(history);
        }catch (Exception e){
            System.out.println("更新历史记录失败");
            e.printStackTrace();
            throw new UpdateException("更新历史记录失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新历史记录成功");
    }

    /**
     * 根据id查找历史记录
     * @param id 待查找的历史记录id
     * @return 待查找的历史记录
     */
    @Override
    public History findOneById(Serializable id)
    {
        if(id == null)
            return null;
        return historyDao.findOneById(id);
    }

    /**
     * 查找所有历史记录
     * @return 待查找的历史记录集合
     */
    @Override
    public List<History> findAll()
    {
        return historyDao.findAll();
    }

    /**
     * 查找某一用户的历史记录
     * @param user 该用户的用户id
     * @return 待查找的历史记录集合
     */
    public List<History> findUserHistory(Serializable user)
    {
        if(user == null)
            return null;
        return historyDao.findUserHistory(user);
    }

    /**
     * 查找某一的历史记录详情
     * @param id 该历史记录的id
     * @return 待查找的历史记录详情
     */
    public Book findHistoryBookDetails(Serializable id)
    {
        if(id == null)
            return null;
        return historyDao.findHistoryBookDetails(id);
    }

    /**
     * 查找某一用户的历史记录详情
     * @param user 该用户的用户id
     * @return 待查找的历史记录详情集合
     */
    public List<HistoryDetail> findHistoryDetailsByUser(Serializable user)
    {
        if(user == null)
            return null;
        List<History> historyList = findUserHistory(user);
        List<HistoryDetail> historyDetailList = new ArrayList<>();
        for (History history : historyList)
        {
            Book book = findHistoryBookDetails(history.getId());
            HistoryDetail historyDetail = new HistoryDetail();
            historyDetail.setId(history.getId());
            historyDetail.setCount(history.getCount());
            historyDetail.setTime(history.getTime());
            historyDetail.setAuthor(book.getAuthor());
            historyDetail.setImage(book.getImage());
            historyDetail.setIntro(book.getIntro());
            historyDetail.setName(book.getName());
            historyDetail.setNation(book.getNation());
            historyDetail.setPrice(book.getPrice());
            historyDetail.setScore(book.getScore());

            historyDetailList.add(historyDetail);
        }
        return historyDetailList;
    }
}