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
public interface HistoryDao extends BaseDao<History>{
    /**
     * 添加历史记录
     * @param history 待添加的历史记录
     * @return 受影响的行数
     */
    @Insert("INSERT INTO history(`id`,`time`,`book`,`user`,`count`) "+
            "VALUES"+
            "(#{id},#{time},#{book},#{user},#{count})")
    int add(History history);

    /**
     * 删除历史记录
     * @param id 待删除的历史记录id
     * @return 受影响的行数
     */
    @Delete("DELETE FROM `history` WHERE id=#{id}")
    int delete(Serializable id);

    /**
     * 更新历史记录
     * @param history 待更新的历史记录
     * @return 受影响的行数
     */
    @Update("Update `history` SET "+
            "time=#{time},book=#{book},user=#{user},count=#{count} "+
            "WHERE id=#{id}")
    int update(History history);

    /**
     * 根据id查找历史记录
     * @param id 待查找的历史记录id
     * @return 待查找的历史记录
     */
    @Select("SELECT * FROM `history` WHERE id=#{id}")
    History findOneById(Serializable id);

    /**
     * 查找所有历史记录
     * @return 待查找的历史记录集合
     */
    @Select("SELECT * FROM `history` ORDER BY time DESC")
    List<History> findAll();

    /**
     * 查找某一用户的历史记录
     * @param user 该用户的用户id
     * @return 待查找的历史记录集合
     */
    @Select("SELECT * FROM `history` WHERE user=#{user} ORDER BY time DESC")
    List<History> findUserHistory(Serializable user);

    /**
     * 查找历史记录详情
     * @param id 待查找的历史记录id
     * @return 待查找的详情
     */
    @Select("SELECT book.name,book.author,book.image,book.price,book.score,book.intro,book.nation,book.year "+
            "FROM `history`,`book` WHERE history.book=book.id AND history.id=#{id}")
    Book findHistoryBookDetails(Serializable id);

    /**
     * 找到最大id
     * @return 最大id
     */
    @Select("SELECT MAX(id) FROM `history`")
    int findMaxId();
}
