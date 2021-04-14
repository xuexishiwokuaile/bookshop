package com.example.bookshop.controller;

import com.example.bookshop.domain.*;
import com.example.bookshop.util.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.*;
import com.example.bookshop.util.ReturnMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/21
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取至今为止之前某一段时间内各年/月/日的销售量/销售金额 URL: /statistics/findItemDetailsByOrder
     * @param dataType 0：销售量 1：销售金额
     * @param periodType 0：按年提供数据 1：按月提供数据 2：按日提供数据
     * @return 详情
     */
    @RequestMapping(value = "/findRecent",method = RequestMethod.GET)
    public List<List<String>> findRecent(@RequestParam(value = "dataType")int dataType,
                                         @RequestParam(value = "periodType")int periodType)
    {
        if(dataType == 0)
        {
            if(periodType == 0)
            {
                return orderService.findRecentYearCount();
            }
            else if(periodType == 1)
            {
                return orderService.findRecentMonthCount();
            }
            else if(periodType == 2)
            {
                return orderService.findRecentDayCount();
            }
            else
            {
                return null;
            }
        }
        else if(dataType == 1)
        {
            if(periodType == 0)
            {
                return orderService.findRecentYearPrice();
            }
            else if(periodType == 1)
            {
                return orderService.findRecentMonthPrice();
            }
            else if(periodType == 2)
            {
                return orderService.findRecentDayPrice();
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * 查询至今为止销售书籍总量 URL: /statistics/findTotalCount
     * @return 销售书籍总量
     */
    @RequestMapping(value = "/findTotalCount",method = RequestMethod.GET)
    public int findTotalCount()
    {
        return orderService.findTotalCount();
    }

    /**
     * 查询至今为止总销售额 URL: /statistics/findTotalPrice
     * @return 总销售额
     */
    @RequestMapping(value = "/findTotalPrice",method = RequestMethod.GET)
    public double findTotalPrice()
    {
        return orderService.findTotalPrice();
    }
}