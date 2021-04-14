package com.example.bookshop.dao;

import com.example.bookshop.domain.Book;
import com.example.bookshop.domain.Sales;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alu
 * @date 2020/4/8 21:14
 */
@Repository
public interface SalesDao extends BaseDao<Sales> {

    @Insert("INSERT INTO sales (`book`, `count`, `state`, `hot`) VALUES (#{book}, #{count}, #{state}, #{hot})")
    int add(Sales sales);

    @Delete("DELETE FROM sales WHERE book = #{book}")
    int delete(Serializable book);

    @Update("UPDATE sales SET count = #{count}, state = #{state}, hot = #{hot} WHERE book = #{book}")
    int update(Sales sales);

    @Select("SELECT * FROM sales WHERE book = #{book}")
    Sales findOneById(Serializable book);

    @Select("SELECT * FROM sales")
    List<Sales> findAll();

    @Select("SELECT count FROM sales WHERE book = #{book}")
    int findStockById(Serializable book);

    /**
     * 查找所有的热门书籍
     * @return 所有热门书籍
     */
    @Select("SELECT * FROM book WHERE id = " +
            "(SELECT book from sales WHERE hot = 2)")
    List<Book> findHot();


}
