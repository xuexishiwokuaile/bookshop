package com.example.bookshop.dao;

import com.example.bookshop.domain.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alu
 * @date 2020/4/17 0:20
 */
@Repository
public interface TypeDao extends BaseDao<Type> {
    @Insert("INSERT INTO type (`id`, `cname`, `ename`) VALUES (#{id}, #{cname}, #{ename}")
    int add(Type type);

    @Delete("DELETE FROM type WHERE id = #{id}")
    int delete(Serializable id);

    @Update("UPDATE type SET cname = #{cname}, ename = #{ename} WHERE id = #{id}")
    int update(Type type);

    @Select("SELECT FROM type WHERE id = #{id}")
    Type findOneById(Serializable id);

    @Select("SELECT * FROM type")
    List<Type> findAll();

    /**
     * 获取类别数量
     * @return 类别数量
     */
    @Select("SELECT COUNT(*) FROM type")
    int getTypeCount();

    /**
     * 通过中文名或英文名获取类别
     * @param name 中文名或英文名
     * @return 待查找的书籍
     */
    @Select("SELECT FROM type WHERE cname = #{name} OR ename = #{name}")
    Type findOneByName(String name);

    /**
     * 获取所有的type的键
     * @return type的所有键
     */
    @Select("SELECT id FROM type")
    int[] getTypeIds();


    @Select("SELECT ename FROM type WHERE id = #{id}")
    String getENameById(Serializable id);

    @Select("SELECT cname FROM type WHERE id = #{id}")
    String getCNameById(Serializable id);
}
