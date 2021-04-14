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
 * @date 2020/4/10
 */

@Repository
public interface CartDao extends BaseDao<Cart>{
    /**
     * 添加购物车项目
     * @param cart 待添加的购物车项目
     * @return 受影响的行数
     */
    @Insert("INSERT INTO `cart`(`id`,`book`,`count`,`user`) "+
            "VALUES "+
            "(#{id},#{book},#{count},#{user})")
    int add(Cart cart);

    /**
     * 删除购物车项目
     * @param id 待删除的购物车项目id
     * @return 受影响的行数
     */
    @Delete("DELETE FROM `cart` WHERE id=#{id}")
    int delete(Serializable id);

    /**
     * 更新购物车项目
     * @param cart 待更新的购物车项目
     * @return 受影响的行数
     */
    @Update("UPDATE `cart` SET "+
            "book=#{book},count=#{count},user=#{user} "+
            "WHERE id=#{id}")
    int update(Cart cart);

    /**
     * 根据id查找购物车项目
     * @param id 待查找的购物车项目id
     * @return 待查找的购物车项目
     */
    @Select("SELECT * FROM `cart` WHERE id=#{id}")
    Cart findOneById(Serializable id);

    /**
     * 查找所有购物车项目
     * @return 待查找的所有购物车项目集合
     */
    @Select("SELECT * FROM `cart`")
    List<Cart> findAll();

    /**
     * 查找某一用户的购物车项目
     * @param user 该用户的id
     * @return 待查找的购物车项目集合
     */
    @Select("SELECT * FROM `cart` WHERE user=#{user}")
    List<Cart> findUserCart(Serializable user);

    /**
     * 查找购物车项目详情
     * @param id 待查找的购物车项目id
     * @return 待查找的购物车项目集合
     */
    @Select("SELECT book.name,book.author,book.image,book.price,book.score,book.intro,book.nation,book.year "+
            "FROM `book`,`cart` WHERE book.id=cart.book AND cart.id={id}")
    Book findCartBookDetails(Serializable id);

    /**
     * 找到最大id
     * @return 最大id
     */
    @Select("SELECT MAX(id) FROM `cart`")
    int findMaxId();
}
