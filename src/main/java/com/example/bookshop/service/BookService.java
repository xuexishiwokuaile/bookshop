package com.example.bookshop.service;

import com.example.bookshop.domain.Book;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BookService extends BaseService<Book> {
    void add(Book book) throws AddException;

    void delete(Serializable id) throws DeleteException;

    void update(Book book) throws UpdateException;

    Book findOneById(Serializable id);

    List<Book> findAll();

    Book findOneByName(String name);

    List<Book> findPriceRangeIn(double price1, double price2);

    //List<Book> findHot();

    List<Book> findBooksByType(int type);

    int findMaxId();

    List<Book> getCarouselBooks();

    List<Book> getBestseller(int count);

    List<Book> getHighScoreBooks(int index);

    int getBookCount();

    List<Book> getBooksByType();

    List<String> getCountries();

    List<Book> findBooksByNation(String[] nations);

    List<Book> findScoreRangeIn(double score1, double score2);

    List<Book> findByLikeName(String name);

    List<Book> findByTypes(int[] types);

    List<Book> findRangeInDoubleArr(double[][] arr, String attribute);

    List<Book> findByTypeOrderByScore(int type, int count);

    /**
     * 首页，获取不同类型的书籍
     * @param count 每个类型的限制数
     * @return String是类型的英文名，book列表对应此种类型
     */
    Map<String, List<Book>> getHomePageGroupByType(int count);

    List<Book> findSearchBooks(int[] type, String[] nations, String[] prices, String[] scores, String name);
}
