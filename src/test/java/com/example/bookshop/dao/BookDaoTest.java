package com.example.bookshop.dao;

import com.example.bookshop.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    @Transactional
    public void add() {
        Book book = new Book();
        //book.setId(bookDao.findMaxId() + 1);
        book.setName("testAddBook");
        book.setAuthor("AddBook");
        book.setPrice(9999.9);
        book.setType(1);

        int result = bookDao.add(book);
        System.out.println(book.getId());
        System.out.println(book);
        System.out.println(bookDao.findMaxId());
        if (result > 0) System.out.println("添加书籍成功");
    }

    @Test
    @Transactional
    public void delete() {
        Book book = new Book();
        //book.setId(3);
        book.setName("testDeleteBook");
        book.setAuthor("DeleteBook");
        book.setPrice(9999.9);
        book.setType(1);
        bookDao.add(book);

        int result = bookDao.delete(book.getId());
        if (result > 0) System.out.println("删除书籍成功");
    }

    @Test
    @Transactional
    public void update() {
        Book book = new Book();
        //book.setId(4);
        book.setName("testUpdateBook");
        book.setAuthor("UpdateBook");
        book.setPrice(9999.9);
        book.setType(1);
        bookDao.add(book);

        book.setIntro("此人很懒，没有简介");
        book.setYear(Year.of(2020));
        book.setNation("中国");
        int result = bookDao.update(book);
        if (result > 0) System.out.println("更新书籍成功");
    }

    @Test
    @Transactional
    public void findOneById() {
        Book book = new Book();
        //book.setId(5);
        book.setName("testfindOneById");
        book.setAuthor("findOneById");
        book.setPrice(9999.9);
        book.setType(1);
        bookDao.add(book);

        System.out.println(bookDao.findOneById(book.getId()));
    }

    @Test
    @Transactional
    public void findAll() {
        Book book = new Book();
        book.setId(6);
        book.setName("testfindAll");
        book.setAuthor("findAll");
        book.setPrice(9999.9);
        book.setType(1);
        bookDao.add(book);

        Book book2 = new Book();
        book2.setId(7);
        book2.setName("testfindAll2");
        book2.setAuthor("findAll2");
        book2.setPrice(9999.9);
        book2.setType(1);
        bookDao.add(book2);

        System.out.println(bookDao.findAll());
    }

    @Test
    @Transactional
    public void findOneByName() {
        Book book = new Book();
        //book.setId(8);
        book.setName("testfindOneByName");
        book.setAuthor("findOneByName");
        book.setPrice(9999.9);
        book.setType(1);
        bookDao.add(book);

        Book book2 = new Book();
        //book2.setId(9);
        book2.setName("findOneByName2");
        book2.setAuthor("findOneByName2");
        book2.setPrice(9999.9);
        book2.setType(1);
        bookDao.add(book2);

        System.out.println(bookDao.findOneByName("testfindAll"));
    }

    @Test
    @Transactional
    public void findPriceRangeIn() {
        Book book = new Book();
        //book.setId(10);
        book.setName("testfindPriceRangeIn");
        book.setAuthor("findPriceRangeIn");
        book.setPrice(999.9);
        book.setType(1);
        bookDao.add(book);

        Book book2 = new Book();
        //book2.setId(11);
        book2.setName("testfindPriceRangeIn2");
        book2.setAuthor("findPriceRangeIn2");
        book2.setPrice(9999.9);
        book2.setType(1);
        bookDao.add(book2);

        System.out.println(bookDao.findPriceRangeIn(999,9999));
    }

    @Test
    @Transactional
    public void findMaxId() {
        System.out.println(bookDao.findMaxId());
    }
}