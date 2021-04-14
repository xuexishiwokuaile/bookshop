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
 * @date 2020/4/17
 */

@Repository
public interface AddressDao {
    /**
     * 添加地址
     * @param address 待添加的地址
     * @return 受影响的行数
     */
    @Insert("INSERT INTO address(`id`,`nation`,`province`,`city`,`details`,`user`,`receiver`,`tel`) "+
            "VALUES "+
            "(#{id},#{nation},#{province},#{city},#{details},#{user},#{receiver},#{tel})")
    int add(Address address);

    /**
     * 删除地址
     * @param id 待删除的地址id
     * @return 受影响的行数
     */
    @Delete("DELETE FROM `address` WHERE id=#{id}")
    int delete(Serializable id);

    /**
     * 更新地址
     * @param address 待更新的地址
     * @return 受影响的行数
     */
    @Update("UPDATE `address` SET "+
            "nation=#{nation},province=#{province},city=#{city},details=#{details},receiver=#{receiver},tel=#{tel} "+
            "WHERE id=#{id}")
    int update(Address address);

    /**
     * 根据id查找地址
     * @param id 待查找的地址id
     * @return 待查找的地址
     */
    @Select("SELECT * FROM `address` WHERE id=#{id}")
    Address findOneById(Serializable id);

    /**
     * 查找所有地址
     * @return 待查找的地址集合
     */
    @Select("SELECT * FROM `address`")
    List<Address> findAll();

    /**
     * 设置为非默认地址
     * @param id 地址id
     * @return 受影响的行数
     */
    @Update("UPDATE `address` SET isDefault=0 WHERE id=#{id}")
    int updateIsDefaultFalse(Serializable id);

    /**
     * 设置为默认地址
     * @param id 地址id
     * @return 受影响的行数
     */
    @Update("UPDATE `address` SET isDefault=1 WHERE id=#{id}")
    int updateIsDefaultTrue(Serializable id);

    /**
     * 查找某一用户所有的地址
     * @param user 用户id
     * @return 地址集合
     */
    @Select("SELECT * FROM `address` WHERE user=#{user}")
    List<Address> findUserAddress(Serializable user);
}
