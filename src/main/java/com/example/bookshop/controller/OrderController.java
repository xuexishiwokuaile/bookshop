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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/9
 */

@RestController
@RequestMapping("/order")
public class OrderController implements BaseController<Order>{
    @Autowired
    private OrderService orderService;
    @Autowired
    private HistoryService historyService;

    /**
     * 添加订单 URL: /order/add
     * @param order 待添加的订单
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReturnMsgUtil add(@RequestBody Order order) {
        order.setId(StringFormatUtil.randomString());
        order.setTime(new Date());
        try {
            orderService.add(order);
            return new ReturnMsgUtil(successCode, String.valueOf(order.getId()));
        } catch (AddException e) {
            return new ReturnMsgUtil(failCode, e.getMessage());
        }
    }

    /**
     * 添加订单项 URL: /order/addItem
     * @param orderitem 待添加的订单项
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/addItem",method = RequestMethod.POST)
    public ReturnMsgUtil addItem(@RequestBody Orderitem orderitem)
    {
        orderitem.setId(StringFormatUtil.randomString());
        try
        {
            //找到订单项对应的用户
            String user = orderService.findOneById(orderitem.getOrder()).getBuyer();
            History history = new History(StringFormatUtil.randomString(),new Date(),orderitem.getBook(),
                    user,orderitem.getCount());
            orderService.addItem(orderitem);
            historyService.add(history);
            return new ReturnMsgUtil(successCode,orderitem.getId());
        }catch (AddException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 添加订单和对应的订单项 URL: /order/addOrder
     * @param order 待添加的订单
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ReturnMsgUtil addOrder(@RequestBody Order order) {
        order.setId(StringFormatUtil.randomString());
        order.setTime(new Date());
        try {
            orderService.add(order);
            Orderitem[] orderitems = order.getOrderitems();
            for (Orderitem orderitem : orderitems)
            {
                orderitem.setId(StringFormatUtil.randomString());
                orderitem.setOrder(order.getId());
                orderService.addItem(orderitem);
                //找到订单项对应的用户
                String user = order.getBuyer();
                History history = new History(StringFormatUtil.randomString(),new Date(),orderitem.getBook(),
                        user,orderitem.getCount());
                historyService.add(history);
            }
            return new ReturnMsgUtil(successCode, String.valueOf(order.getId()));
        } catch (AddException e) {
            return new ReturnMsgUtil(failCode, e.getMessage());
        }
    }

    /**
     * 删除订单 URL: /order/delete
     * @param id 待删除的订单号
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ReturnMsgUtil delete(@RequestParam(value = "id")Serializable id)
    {
        try
        {
            orderService.delete(id);
            return new ReturnMsgUtil(successCode,"success");
        }catch(DeleteException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 删除订单项 URL: /order/deleteItem
     * @param id 待删除的订单项号
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/deleteItem",method = RequestMethod.DELETE)
    public ReturnMsgUtil deleteItem(@RequestParam(value = "id")Serializable id)
    {
        try
        {
            orderService.deleteItem(id);
            return new ReturnMsgUtil(successCode,"success");
        }catch (DeleteException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 更新订单 URL: /order/update
     * @param order 待更新的订单
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ReturnMsgUtil update(@RequestBody Order order)
    {
        try
        {
            orderService.update(order);
            return new ReturnMsgUtil(successCode,"success");
        }catch (UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 更新订单项 URL: /order/updateItem
     * @param orderitem 待更新的订单项
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/updateItem",method = RequestMethod.PUT)
    public ReturnMsgUtil updateItem(@RequestBody Orderitem orderitem)
    {
        try
        {
            orderService.updateItem(orderitem);
            return new ReturnMsgUtil(successCode,"success");
        }catch (UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 根据id查找订单 URL: /order/findOneById
     * @param id 待查找的订单id
     * @return 待查找的订单
     */
    @RequestMapping(value = "/findOneById",method = RequestMethod.GET)
    public Order findOneById(@RequestParam(value = "id")Serializable id)
    {
        return orderService.findOneById(id);
    }

    /**
     * 根据id查找订单项 URL: /order/findOneItemById
     * @param id 待查找的订单项id
     * @return 待查找的订单项
     */
    @RequestMapping(value = "/findOneItemById",method = RequestMethod.GET)
    public Orderitem findOneItemById(@RequestParam(value = "id")Serializable id)
    {
        return orderService.findOneItemById(id);
    }

    /**
     * 查找所有订单 URL: /order/findAll
     * @return 待查找的订单集合
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Order> findAll()
    {
        return orderService.findAll();
    }

    /**
     * 查找所有订单项 URL: /order/findAllItem
     * @return 待查找的订单项集合
     */
    @RequestMapping(value = "/findAllItem",method = RequestMethod.GET)
    public List<Orderitem> findAllItem()
    {
        return orderService.findAllItem();
    }

    /**
     * 查找订单包含的所有订单项 URL: /order/findOrderItem
     * @param order 待查找的订单id
     * @return 待查找的订单项集合
     */
    @RequestMapping(value = "/findOrderItem",method = RequestMethod.GET)
    public List<Orderitem> findOrderItem(@RequestParam(value = "order")Serializable order)
    {
        return orderService.findOrderItem(order);
    }

    /**
     * 查找购买者用户所有的订单 URL: /order/findBuyerOrder
     * @param buyer 待查找的购买者
     * @return 待查找的订单集合
     */
    @RequestMapping(value = "/findBuyerOrder",method = RequestMethod.GET)
    public List<Order> findBuyerOrder(@RequestParam(value = "buyer")Serializable buyer)
    {
        return orderService.findBuyerOrder(buyer);
    }

    /**
     * 查找订单项书籍详情 URL: /order.findItemBookDetails
     * @param id 待查找的订单项id
     * @return 待查找的书籍
     */
    @RequestMapping(value = "/findItemBookDetails",method = RequestMethod.GET)
    public Book findItemBookDetails(@RequestParam(value = "id")Serializable id)
    {
        return orderService.findItemBookDetails(id);
    }

    /**
     * 查找订单地址详情 URL: /order/findOrderAddressDetails
     * @param id 待查找的订单id
     * @return 待查找的地址
     */
    @RequestMapping(value = "/findOrderAddressDetails",method = RequestMethod.GET)
    public Address findOrderAddressDetails(@RequestParam(value = "id")Serializable id)
    {
        return orderService.findOrderAddressDetails(id);
    }

    /**
     * 查找订单购买者详情 URL: /order/findOrderBuyerDetails
     * @param id 待查找的订单id
     * @return 待查找的购买者
     */
    @RequestMapping(value = "/findOrderBuyerDetails",method = RequestMethod.GET)
    public User findOrderBuyerDetails(@RequestParam(value = "id")Serializable id)
    {
        return orderService.findOrderBuyerDetails(id);
    }

    /**
     * 查找订单详情（根据发送者）URL: /order/findOrderDetailsByBuyer
     * @param buyer 发送者用户id
     * @return 待查找的订单详情集合
     */
    @RequestMapping(value = "/findOrderDetailsByBuyer",method = RequestMethod.GET)
    public List<OrderDetail> findOrderDetailsByBuyer(@RequestParam(value = "buyer")Serializable buyer)
    {
        return orderService.findOrderDetailsByBuyer(buyer);
    }

    /**
     * 根据订单号查找其下的所有订单项详情 URL: /order/findItemDetailsByOrder
     * @param order 订单id
     * @return 订单项详情集合
     */
    @RequestMapping(value = "/findItemDetailsByOrder",method = RequestMethod.GET)
    public List<OrderDetail> findItemDetailsByOrder(@RequestParam(value = "order")Serializable order)
    {
        return orderService.findItemDetailsByOrder(order);
    }


}
