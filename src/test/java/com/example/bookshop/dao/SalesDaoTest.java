package com.example.bookshop.dao;

import com.example.bookshop.domain.Book;
import com.example.bookshop.domain.Sales;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alu
 * @date 2020/4/8 22:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesDaoTest {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private SalesDao salesDao;

    @Test
    @Transactional
    public void add() {
        /*Book book = new Book();
        //book.setId(432);
        book.setName("test");
        book.setPrice(99);
        book.setAuthor("test");
        book.setType(1);
        bookDao.add(book);*/

        //findMaxId()在这里查找最大的id可以查询到刚刚添加的book的真实id，否则为0
        //Sales sales = salesDao.findOneById(bookDao.findMaxId());
        Sales sales = new Sales();
        sales.setBook(32);
        sales.setCount(20);
        sales.setState(2);
        sales.setHot(0);
        salesDao.update(sales);
        System.out.println("add: " + sales);
    }

    @Test
    @Transactional
    public void delete() {
    }

    @Test
    @Transactional
    public void update() {
        Book book = new Book();
        //book.setId(432);
        book.setName("test");
        book.setPrice(99);
        book.setAuthor("test");
        book.setType(1);
        bookDao.add(book);

        //findMaxId()在这里查找最大的id可以查询到刚刚添加的book的真实id，否则为0
        Sales sales = salesDao.findOneById(bookDao.findMaxId());
        //sales.setBook(book.getId());
        sales.setCount(20);
        sales.setState(2);
        sales.setHot(0);
        salesDao.update(sales);
        System.out.println("add: " + sales);
    }

    @Test
    public void findOneById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findStockById() {
    }

    @Test
    public void findHot() {
    }


}