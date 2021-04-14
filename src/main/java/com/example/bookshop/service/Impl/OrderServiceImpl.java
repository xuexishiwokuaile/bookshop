package com.example.bookshop.service.Impl;

import com.example.bookshop.controller.OrderController;
import com.example.bookshop.dao.*;
import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.OrderService;
import com.example.bookshop.util.StringFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Anran
 * @date 2020/4/9
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    /**
     * 添加订单
     * @param order 待添加的订单
     * @throws AddException 添加异常
     */
    @Override
    public void add(Order order) throws AddException
    {
        if(order == null)
        {
            throw new AddException("添加订单错误，订单为空");
        }
        else if(StringFormatUtil.hasEmpty(order.getBuyer())||
                StringFormatUtil.hasEmpty(order.getAddress())||order.getTime()==null)
        {
            throw new AddException("添加订单错误：时间为空/购买者为空/地址为空");
        }
        else if(order.getPrice()<=0)
        {
            throw new AddException("添加订单错误：价格为空/价格格式不正确");
        }

        int result;
        try
        {
            result = orderDao.add(order);
        }catch (Exception e){
            System.out.println("添加订单失败");
            e.printStackTrace();
            throw new AddException("添加订单失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("添加订单成功");
    }

    /**
     * 添加订单项
     * @param orderitem 待添加的订单项
     * @throws AddException 添加异常
     */
    public void addItem(Orderitem orderitem) throws AddException
    {
        if(orderitem == null)
        {
            throw new AddException("添加订单项失败，订单项为空");
        }
        else if(StringFormatUtil.hasEmpty(String.valueOf(orderitem.getBook()))||StringFormatUtil.hasEmpty(orderitem.getOrder()))
        {
            throw new AddException("添加订单项失败，书籍/订单为空");
        }
        else if(orderitem.getCount()<=0)
        {
            throw new AddException("添加订单项失败，格式错误");
        }

        int result;
        try
        {
            result = orderDao.addItem(orderitem);
        }catch (Exception e){
            System.out.println("添加订单项失败");
            e.printStackTrace();
            throw new AddException("添加订单项失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("添加订单项成功");
    }

    /**
     * 删除订单
     * @param id 待删除的订单号
     * @throws DeleteException 删除异常
     */
    @Override
    public void delete(Serializable id) throws DeleteException
    {
        if(id == null)
        {
            throw new DeleteException("删除订单错误：订单为空");
        }

        int result;
        try
        {
            result = orderDao.delete(id);
        }catch (Exception e){
            System.out.println("删除订单失败");
            e.printStackTrace();
            throw new DeleteException("删除订单失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("删除订单成功");
    }

    /**
     * 删除订单项
     * @param id 待删除的订单项号
     * @throws DeleteException 删除异常
     */
    public void deleteItem(Serializable id) throws DeleteException
    {
        if(id == null)
        {
            throw new DeleteException("删除订单项错误：订单为空");
        }

        int result;
        try
        {
            result = orderDao.deleteItem(id);
        }catch (Exception e){
            System.out.println("删除订单项失败");
            e.printStackTrace();
            throw new DeleteException("删除订单项失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("删除订单项成功");
    }

    /**
     * 更新订单
     * @param order 待更新的订单
     * @throws UpdateException 更新异常
     */
    @Override
    public void update(Order order) throws UpdateException
    {
        if(order == null)
        {
            throw new UpdateException("更新订单错误，订单为空");
        }
        else if(StringFormatUtil.hasEmpty(order.getBuyer())||
                StringFormatUtil.hasEmpty(order.getAddress())||order.getTime()==null)
        {
            throw new UpdateException("更新订单错误：时间为空/购买者为空/地址为空");
        }
        else if(order.getPrice()<=0)
        {
            throw new UpdateException("更新订单错误：价格为空/价格格式不正确");
        }

        int result;
        try
        {
            result = orderDao.update(order);
        }catch (Exception e){
            System.out.println("更新订单失败");
            e.printStackTrace();
            throw new UpdateException("更新订单失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新订单成功");
    }

    /**
     * 更新订单项
     * @param orderitem 待更新的订单
     * @throws UpdateException 更新异常
     */
    public void updateItem(Orderitem orderitem) throws UpdateException
    {
        if(orderitem == null)
        {
            throw new UpdateException("添加订单项失败，订单项为空");
        }
        else if(StringFormatUtil.hasEmpty(String.valueOf(orderitem.getBook()))||StringFormatUtil.hasEmpty(orderitem.getOrder()))
        {
            throw new UpdateException("添加订单项失败，书籍/订单为空");
        }
        else if(orderitem.getCount()<=0)
        {
            throw new UpdateException("添加订单项失败，格式错误");
        }

        int result;
        try
        {
            result = orderDao.updateItem(orderitem);
        }catch (Exception e){
            System.out.println("更新订单项失败");
            e.printStackTrace();
            throw new UpdateException("更新订单项失败：" + e.getMessage());
        }

        if(result>0)
            System.out.println("更新订单项成功");
    }

    /**
     * 根据id查找订单
     * @param id 待查找的订单id
     * @return 待查找的订单
     */
    @Override
    public Order findOneById(Serializable id)
    {
        if(id == null)
            return null;
        return orderDao.findOneById(id);
    }

    /**
     * 根据id查找订单项
     * @param id 待查找的订单项id
     * @return 待查找的订单项
     */
    public Orderitem findOneItemById(Serializable id)
    {
        if(id == null)
            return null;
        return orderDao.findOneItemById(id);
    }

    /**
     * 查找所有订单
     * @return 待查找的订单集合
     */
    @Override
    public List<Order> findAll()
    {
        return orderDao.findAll();
    }

    /**
     * 查找所有订单项
     * @return 待查找的订单项集合
     */
    public List<Orderitem> findAllItem()
    {
        return orderDao.findAllItem();
    }

    /**
     * 查找订单包含的所有订单项
     * @param order 待查找的订单id
     * @return 待查找的订单项集合
     */
    public List<Orderitem> findOrderItem(Serializable order)
    {
        if(order == null)
            return null;
        return orderDao.findOrderItem(order);
    }

    /**
     * 查找购买者用户所有的订单
     * @param user 待查找的购买者id
     * @return 待查找的订单集合
     */
    public List<Order> findBuyerOrder(Serializable user)
    {
        if(user == null)
            return null;
        return orderDao.findBuyerOrder(user);
    }

    /**
     * 查找订单项书籍详情
     * @param id 待查找的订单项id
     * @return 待查找的书籍
     */
    public Book findItemBookDetails(Serializable id)
    {
        if(id == null)
            return null;
        return orderDao.findItemBookDetails(id);
    }

    /**
     * 查找订单地址详情
     * @param id 待查找的订单id
     * @return 待查找的地址
     */
    public Address findOrderAddressDetails(Serializable id)
    {
        if(id == null)
            return null;
        return orderDao.findOrderAddressDetails(id);
    }

    /**
     * 查找订单购买者详情
     * @param id 待查找的订单id
     * @return 待查找的购买者
     */
    public User findOrderBuyerDetails(Serializable id)
    {
        if(id == null)
            return null;
        return orderDao.findOrderBuyerDetails(id);
    }

    /**
     * 查找订单详情（根据发送者）
     * @param buyer 发送者用户id
     * @return 待查找的订单详情集合
     */
    public List<OrderDetail> findOrderDetailsByBuyer(Serializable buyer)
    {
        if(buyer == null)
            return null;
        List<Order> orderList = findBuyerOrder(buyer);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Order order : orderList)
        {
            Address address = findOrderAddressDetails(order.getId());
            User user = findOrderBuyerDetails(order.getId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(order.getId());
            orderDetail.setOrdernation(address.getNation());
            orderDetail.setProvince(address.getProvince());
            orderDetail.setCity(address.getCity());
            orderDetail.setDetails(address.getDetails());
            orderDetail.setReceiver(address.getReceiver());
            orderDetail.setTel(address.getTel());
            orderDetail.setUsername(user.getName());
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }

    /**
     * 根据订单号查找其下的所有订单项详情
     * @param order 订单id
     * @return 订单项详情集合
     */
    public List<OrderDetail> findItemDetailsByOrder(Serializable order)
    {
        if(order == null)
            return null;
        List<Orderitem> orderitemList = findOrderItem(order);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Orderitem orderitem : orderitemList)
        {
            Book book = orderDao.findItemBookDetails(orderitem.getId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(orderitem.getId());
            orderDetail.setBooknation(book.getNation());
            orderDetail.setAuthor(book.getAuthor());
            orderDetail.setCount(orderitem.getCount());
            orderDetail.setImage(book.getImage());
            orderDetail.setIntro(book.getIntro());
            orderDetail.setName(book.getName());
            orderDetail.setPrice(book.getPrice());
            orderDetail.setScore(book.getScore());

            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }

    /**
     * 查找某一订单的总销售量
     * @param order 订单id
     * @return 该订单的总销售量
     */
    public int findOrderTotalCount(Serializable order)
    {
        List<Orderitem> orderitemList = findOrderItem(order);
        int sumCount = 0;
        for (Orderitem orderitem : orderitemList)
        {
            int itemCount = orderitem.getCount();
            sumCount += itemCount;
        }
        return sumCount;
    }

    /**
     * 查找某一年的总销售量
     * @param year 具体年份
     * @return 这一年的总销售量
     */
    public int findYearTotalCount(String year)
    {
        String year1 = year + "%";
        List<Order> orderList = orderDao.findOneByYear(year1);
        int sumCount = 0;
        for (Order order : orderList)
        {
            int orderCount = findOrderTotalCount(order.getId());
            sumCount += orderCount;
        }
        return sumCount;
    }

    /**
     * 查找某一月的总销售量
     * @param month 具体月份
     * @return 这一月的总销售量
     */
    public int findMonthTotalCount(String month)
    {
        String month1 = month + "%";
        List<Order> orderList = orderDao.findOneByMonth(month1);
        int sumCount = 0;
        for (Order order : orderList)
        {
            int orderCount = findOrderTotalCount(order.getId());
            sumCount += orderCount;
        }
        return sumCount;
    }

    /**
     * 查找某一天的总销售量
     * @param day 具体天
     * @return 这一天的总销售量
     */
    public int findDayTotalCount(String day)
    {
        String day1 = day + "%";
        List<Order> orderList = orderDao.findOneByDay(day1);
        int sumCount = 0;
        for (Order order : orderList)
        {
            int orderCount = findOrderTotalCount(order.getId());
            sumCount += orderCount;
        }
        return sumCount;
    }

    /**
     * 查找某一年的总销售额
     * @param year 具体年份
     * @return 这一年的总销售量额
     */
    public double findYearTotalPrice(String year)
    {
        String year1 = year + "%";
        List<Order> orderList = orderDao.findOneByYear(year1);
        double sumPrice = 0;
        for (Order order : orderList)
        {
            double orderPrice = order.getPrice();
            sumPrice += orderPrice;
        }
        return sumPrice;
    }

    /**
     * 查找某一月的总销售额
     * @param month 具体月份
     * @return 这一月的总销售量额
     */
    public double findMonthTotalPrice(String month)
    {
        String month1 = month + "%";
        List<Order> orderList = orderDao.findOneByMonth(month1);
        double sumPrice = 0;
        for (Order order : orderList)
        {
            double orderPrice = order.getPrice();
            sumPrice += orderPrice;
        }
        return sumPrice;
    }

    /**
     * 查找某一天的总销售额
     * @param day 具体天
     * @return 这一天的总销售量额
     */
    public double findDayTotalPrice(String day)
    {
        String day1 = day + "%";
        List<Order> orderList = orderDao.findOneByDay(day1);
        double sumPrice = 0;
        for (Order order : orderList)
        {
            double orderPrice = order.getPrice();
            sumPrice += orderPrice;
        }
        return sumPrice;
    }

    /**
     * 查找订单包含的最近7年年份
     * @return 这些年份的list
     */
    public List<String> findRecentYear()
    {
        List<Order> orderList = findAll();
        //存储所有非重复的年份
        List<String> yearList = new ArrayList<>();
        //规定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        for (Order order : orderList)
        {
            //获取订单的年份信息
            String year = sdf.format(order.getTime());
            //yearList中不包含该year，即该year不重复
            if (!yearList.contains(year)) {
                yearList.add(year);
            }
        }

        if(yearList.size()<8)
            return yearList;
        else
            return yearList.subList(yearList.size()-7,yearList.size());
    }

    /**
     * 查找订单包含的最近7月月份
     * @return 这些月份的list
     */
    public List<String> findRecentMonth()
    {
        List<Order> orderList = findAll();
        //存储所有非重复的月份
        List<String> monthList = new ArrayList<>();
        //规定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (Order order : orderList)
        {
            //获取订单的月份信息
            String month = sdf.format(order.getTime());
            //monthList中不包含该month，即该month不重复
            if (!monthList.contains(month)) {
                monthList.add(month);
            }
        }

        if(monthList.size()<8)
            return monthList;
        else
            return monthList.subList(monthList.size()-7,monthList.size());
    }

    /**
     * 查找订单包含的最近7天
     * @return 这些天的list
     */
    public List<String> findRecentDay()
    {
        List<Order> orderList = findAll();
        //存储所有非重复的天
        List<String> dayList = new ArrayList<>();
        //规定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Order order : orderList)
        {
            //获取订单的天信息
            String day = sdf.format(order.getTime());
            //dayMap中不包含该day，即该day不重复
            if (!dayList.contains(day)) {
                dayList.add(day);
            }
        }

        if(dayList.size()<8)
            return dayList;
        else
            return dayList.subList(dayList.size()-7,dayList.size());
    }

    /**
     * 查找最近年份的总销售量
     * @return 年份list+销售量list
     */
    public List<List<String>> findRecentYearCount()
    {
        List<String> yearList = findRecentYear();
        List<String> countList = new ArrayList<>();
        for (String s : yearList)
        {
            String count = String.valueOf(findYearTotalCount(s));
            countList.add(count);
        }
        List<List<String>> result = new ArrayList<>();
        result.add(yearList);
        result.add(countList);
        return result;
    }

    /**
     * 查找最近月份的总销售量
     * @return 月份list+销售量list
     */
    public List<List<String>> findRecentMonthCount()
    {
        List<String> monthList = findRecentMonth();
        List<String> countList = new ArrayList<>();
        for (String s : monthList)
        {
            String count = String.valueOf(findMonthTotalCount(s));
            countList.add(count);
        }
        List<List<String>> result = new ArrayList<>();
        result.add(monthList);
        result.add(countList);
        return result;
    }

    /**
     * 查找最近天的总销售量
     * @return 天list+销售量list
     */
    public List<List<String>> findRecentDayCount()
    {
        List<String> dayList = findRecentDay();
        List<String> countList = new ArrayList<>();
        for (String s : dayList)
        {
            String count = String.valueOf(findDayTotalCount(s));
            countList.add(count);
        }
        List<List<String>> result = new ArrayList<>();
        result.add(dayList);
        result.add(countList);
        return result;
    }

    /**
     * 查找最近年份的总销售额
     * @return 年份list+销售额list
     */
    public List<List<String>> findRecentYearPrice()
    {
        List<String> yearList = findRecentYear();
        List<String> priceList = new ArrayList<>();
        for (String s : yearList)
        {
            String price = String.valueOf(findYearTotalPrice(s));
            priceList.add(price);
        }
        List<List<String>> result = new ArrayList<>();
        result.add(yearList);
        result.add(priceList);
        return result;
    }

    /**
     * 查找最近月份的总销售额
     * @return 月份list+销售额list
     */
    public List<List<String>> findRecentMonthPrice()
    {
        List<String> monthList = findRecentMonth();
        List<String> priceList = new ArrayList<>();
        for (String s : monthList)
        {
            String price = String.valueOf(findMonthTotalPrice(s));
            priceList.add(price);
        }
        List<List<String>> result = new ArrayList<>();
        result.add(monthList);
        result.add(priceList);
        return result;
    }

    /**
     * 查找最近天的总销售额
     * @return 天list+销售额list
     */
    public List<List<String>> findRecentDayPrice()
    {
        List<String> dayList = findRecentDay();
        List<String> priceList = new ArrayList<>();
        for (String s : dayList)
        {
            String price = String.valueOf(findDayTotalPrice(s));
            priceList.add(price);
        }
        List<List<String>> result = new ArrayList<>();
        result.add(dayList);
        result.add(priceList);
        return result;
    }

    /**
     * 查询至今为止销售书籍总量
     * @return 销售总量
     */
    public int findTotalCount()
    {
        //订单包含的所有年份
        List<Order> orderList = findAll();
        //存储所有非重复的年份
        List<String> yearList = new ArrayList<>();
        //规定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        for (Order order : orderList)
        {
            //获取订单的年份信息
            String year = sdf.format(order.getTime());
            //yearList中不包含该year，即该year不重复
            if (!yearList.contains(year)) {
                yearList.add(year);
            }
        }

        int sumCount = 0;
        for (String s : yearList)
        {
            int yearCount = findYearTotalCount(s);
            sumCount += yearCount;
        }
        return sumCount;
    }

    /**
     * 查询至今为止总销售额
     * @return 总销售额
     */
    public double findTotalPrice()
    {
        //订单包含的所有年份
        List<Order> orderList = findAll();
        //存储所有非重复的年份
        List<String> yearList = new ArrayList<>();
        //规定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        for (Order order : orderList)
        {
            //获取订单的年份信息
            String year = sdf.format(order.getTime());
            //yearList中不包含该year，即该year不重复
            if (!yearList.contains(year)) {
                yearList.add(year);
            }
        }

        double sumPrice = 0;
        for (String s : yearList)
        {
            double yearPrice = findYearTotalPrice(s);
            sumPrice += yearPrice;
        }
        return sumPrice;
    }
}