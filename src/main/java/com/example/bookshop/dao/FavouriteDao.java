package com.example.bookshop.dao;

import com.example.bookshop.domain.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Anran
 * @date 2020/4/13
 */

@Repository
public interface FavouriteDao extends BaseDao<Favourite>{
    /**
     * 添加收藏项目
     * @param favourite 待添加的收藏项目
     * @return 受影响的行数
     */
    @Insert("INSERT INTO favourite(`id`,`user`,`book`) "+
            "VALUES "+
            "#{id},#{user},#{book}")
    int add(Favourite favourite);

    /**
     * 删除收藏
     * @param id 待删除的收藏项目id
     * @return 受影响的行数
     */
    @Delete("DELETE FROM `favourite` WHERE id=#{id}")
    int delete(Serializable id);

    /**
     * 更新收藏项目
     * @param favourite 待更新的收藏项目
     * @return 受影响的行数
     */
    @Update("UPDATE `favourite` SET "+
            "book=#{book},user=#{user} "+
            "WHERE id=#{id}")
    int update(Favourite favourite);
    
    /** 
     * 将书籍收藏状态设置为已收藏
     * @param id 待设置的书籍id
     * @return 受影响的行数
     */
    @Update("UPDATE `book` SET isFavourite=1 WHERE id=#{id}")
    int updateFavouriteTrue(Serializable id);

    /**
     * 将书籍收藏状态设置为未收藏
     * @param id 待设置的书籍id
     * @return 受影响的行数
     */
    @Update("UPDATE `book` SET isFavourite=0 WHERE id=#{id}")
    int updateFavouriteFalse(Serializable id);

    /**
     * 根据id查找收藏项目
     * @param id 待查找的收藏项目id
     * @return 待查找的收藏项目
     */
    @Select("SELECT * FROM `favourite` WHERE id=#{id}")
    Favourite findOneById(Serializable id);

    /**
     * 查找所有收藏项目
     * @return 待查找的收藏项目集合
     */
    @Select("SELECT * FROM `favourite`")
    List<Favourite> findAll();

    /**
     * 查找某一用户的收藏项目
     * @param user 待查找的用户id
     * @return 待查找的收藏项目集合
     */
    @Select("SELECT * FROM `favourite` WHERE user=#{user} ORDER BY time DESC")
    List<Favourite> findUserFavourite(Serializable user);

    /**
     * 查找收藏项目详情
     * @param id 待查找的收藏项目id
     * @return 待查找的收藏项目集合
     */
    @Select("SELECT book.name,book.author,book.image,book.price,book.score,book.intro,book.nation,book.year "+
            "FROM `favourite`,`book` WHERE book.id=favourite.book AND favourite.id=#{id}")
    Book findFavouriteBookDetails(Serializable id);

    /**
     * 搜索最大id
     * @return 最大id
     */
    @Select("SELECT MAX(id) FROM `favourite`")
    int findMaxId();

    @Update("UPDATE `book` SET image=#{imageUrl} WHERE image LIKE #{image} ")
    //@Select("SELECT * FROM `book` WHERE image LIKE #{image}")
    int find(String imageUrl,String image);
}
