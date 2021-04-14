package com.example.bookshop.controller;

import com.example.bookshop.domain.*;
import com.example.bookshop.util.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.HistoryService;
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
 * @date 2020/4/11
 */

@RestController
@RequestMapping("/history")
public class HistoryController implements BaseController<History>{
    @Autowired
    private HistoryService historyService;

    /**
     * 添加历史记录 URL: /history/add
     * 不存在相同的历史记录->新建 存在相同的历史记录->更新
     * @param history 待添加的历史记录
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public ReturnMsgUtil add(@RequestBody History history)
//    {
//        //生成5位随机id
//        history.setId(StringFormatUtil.randomString());
//        //找到该用户的所有历史记录
//        List<History> historyList = historyService.findUserHistory(history.getUser());
//        //取出这些历史记录包含的所有书籍
//        Map<Integer,String> bookMap = new HashMap<>();
//        for (History value : historyList)
//        {
//            bookMap.put(value.getBook(),value.getId());
//        }
//
//        try
//        {
//            //历史记录中已经包含该书籍
//            if(bookMap.containsKey(history.getBook()))
//            {
//                //将id设置为之前存在项目的id
//                history.setId(bookMap.get(history.getBook()));
//                historyService.update(history);
//            }
//            //历史记录中不包含该书籍
//            else
//            {
//                historyService.add(history);
//            }
//            return new ReturnMsgUtil(successCode,history.getId());
//        }catch (AddException | UpdateException e){
//            return new ReturnMsgUtil(failCode,e.getMessage());
//        }
//    }

    public ReturnMsgUtil add(@RequestBody History history)
    {
        history.setId(StringFormatUtil.randomString());
        try
        {
            historyService.add(history);
            return new ReturnMsgUtil(successCode,history.getId());
        }catch (AddException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 删除历史记录 URL: /history/delete
     * @param id 待删除的历史记录id
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ReturnMsgUtil delete(@RequestParam(value = "id")Serializable id)
    {
        try
        {
            historyService.delete(id);
            return new ReturnMsgUtil(successCode,"success");
        }catch (DeleteException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 更新历史记录 URL: /history/update
     * @param history 待更新的历史记录
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ReturnMsgUtil update(@RequestBody History history)
    {
        try
        {
            historyService.update(history);
            return new ReturnMsgUtil(successCode,"success");
        }catch (UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 根据id查找历史记录 URL: /history/findOneById
     * @param id 待查找的历史记录id
     * @return 待查找的历史记录
     */
    @RequestMapping(value = "/findOneById",method = RequestMethod.GET)
    public History findOneById(@RequestParam(value = "id")Serializable id)
    {
        return historyService.findOneById(id);
    }

    /**
     * 查找所有历史记录 URL: /history/findAll
     * @return 待查找的历史记录集合
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<History> findAll()
    {
        return historyService.findAll();
    }

    /**
     * 查找某一用户的历史记录 URL: /history/findUserHistory
     * @param user 该用户的用户id
     * @return 待查找的历史记录集合
     */
    @RequestMapping(value = "/findUserHistory",method = RequestMethod.GET)
    public List<History> findUserHistory(@RequestParam(value = "user")Serializable user)
    {
        return historyService.findUserHistory(user);
    }

    /**
     * 查找某一历史记录详情 URL: /history/findHistoryBookDetails
     * @param id 该历史记录的id
     * @return 待查找的历史记录详情
     */
    @RequestMapping(value = "/findHistoryBookDetails",method = RequestMethod.GET)
    public Book findHistoryBookDetails(@RequestParam(value = "id")Serializable id)
    {
        return historyService.findHistoryBookDetails(id);
    }

    /**
     * 查找某一用户的所有历史记录详情 URL: /history/findHistoryDetailsByUser
     * @param user 该用户的用户id
     * @return 待查找的历史记录详情集合
     */
    @RequestMapping(value = "/findHistoryDetailsByUser",method = RequestMethod.GET)
    public List<HistoryDetail> findHistoryDetailsByUser(@RequestParam(value = "user")Serializable user)
    {
        return historyService.findHistoryDetailsByUser(user);
    }
}