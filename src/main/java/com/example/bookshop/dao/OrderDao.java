package com.example.bookshop.dao;

import com.example.bookshop.domain.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/9
 */

@Repository
public interface OrderDao extends BaseDao<Order>{
    /**
     * 添加订单
     * @param order 待添加的订单
     * @return 受影响的行数
     */
    @Insert("INSERT INTO `order`(`id`,`time`,`buyer`,`address`,`price`) " +
            "VALUES " +
            "(#{id},#{time},#{buyer},#{address},#{price})")
    int add(Order order);

    /**
     * 添加订单项
     * @param orderitem 待添加的订单项
     * @return 受影响的行数
     */
    @Insert("INSERT INTO `orderitem`(`id`,`book`,`count`,`order`) "+
            "VALUES "+
            "(#{id},#{book},#{count},#{order})")
    int addItem(Orderitem orderitem);

    /**
     * 删除订单
     * @param id 待删除的订单
     * @return  受影响的行数
     */
    @Delete("DELETE FROM `order` WHERE id=#{id}")
    int delete(Serializable id);

    /**
     * 删除订单项
     * @param id 待删除的订单项
     * @return  受影响的行数
     */
    @Delete("DELETE FROM `orderitem` WHERE id=#{id}")
    int deleteItem(Serializable id);

    /**
     * 更新订单
     * @param order 待更新的订单
     * @return 受影响的行数
     */
    @Update("UPDATE `order` SET "+
            "time=#{time}, buyer=#{buyer}, address=#{address}, price=#{price} "+
            "WHERE id=#{id}")
    int update(Order order);

    /**
     * 更新订单项
     * @param orderitem 待更新的订单
     * @return 受影响的行数
     */
    @Update("UPDATE `orderitem` SET "+
            "book=#{book},count=#{count},order=#{order} "+
            "WHERE id+#{id}")
    int updateItem(Orderitem orderitem);

    /**
     * 根据id查找订单
     * @param id 待查找的订单id
     * @return 待查找的订单
     */
    @Select("SELECT * FROM `order` WHERE id=#{id}")
    Order findOneById(Serializable id);

    /**
     * 根据id查找订单项
     * @param id 待查找的订单项id
     * @return 待查找的订单项
     */
    @Select("SELECT * FROM `orderitem` WHERE id=#{id}")
    Orderitem findOneItemById(Serializable id);

    /**
     * 查找所有订单
     * @return 待查找的订单集合
     */
    @Select("SELECT * FROM `order` ORDER BY TIME ASC")
    List<Order> findAll();

    /**
     * 查找所有订单
     * @return 待查找的订单集合
     */
    @Select("SELECT * FROM `orderitem`")
    List<Orderitem> findAllItem();

    /**
     * 查找订单包含的所有订单项
     * @param order 待查找的订单id
     * @return 待查找的订单项集合
     */
    @Select("SELECT * FROM `orderitem` WHERE `order`=#{order}")
    List<Orderitem> findOrderItem(Serializable order);

    /**
     * 查找购买者用户所有的订单
     * @param buyer 待查找的购买者id
     * @return 待查找的订单集合
     */
    @Select("SELECT * FROM `order` WHERE buyer=#{buyer} ORDER BY time DESC")
    List<Order> findBuyerOrder(Serializable buyer);

    /**
     * 查找订单项书籍详情
     * @param id 待查找的订单项id
     * @return 待查找的书籍
     */
    @Select("SELECT book.name,book.author,book.image,book.price,book.score,book.intro,book.nation,book.year "+
            "FROM `orderitem`,`book` WHERE orderitem.book=book.id AND orderitem.id=#{id}")
    Book findItemBookDetails(Serializable id);

    /**
     * 查找订单地址详情
     * @param id 待查找的订单id
     * @return 待查找的地址
     */
    @Select("SELECT address.nation,address.province,address.city,address.details FROM `order`,`address` "+
            "WHERE order.id=#{id} AND address.id=order.address")
    Address findOrderAddressDetails(Serializable id);

    /**
     * 查找订单购买者详情
     * @param id 待查找的订单id
     * @return 待查找的购买者
     */
    @Select("SELECT user.name FROM `user`,`order` "+
            "WHERE order.id=#{id} AND user.id=order.buyer")
    User findOrderBuyerDetails(Serializable id);

    /**
     * 找到最大id
     * @return 最大id
     */
    @Select("SELECT MAX(id) FROM `order`")
    int findMaxId();

    /**
     * 查找某一年的订单
     * @param year 具体年份
     * @return 待查找的订单集合
     */
    @Select("SELECT * FROM `order` WHERE time LIKE #{year}")
    List<Order> findOneByYear(String year);

    @Select("SELECT * FROM `order` WHERE time LIKE #{month}")
    List<Order> findOneByMonth(String month);

    @Select("SELECT * FROM `order` WHERE time LIKE #{day}")
    List<Order> findOneByDay(String day);
}
