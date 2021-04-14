package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.TypeDao;
import com.example.bookshop.domain.Book;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.BookService;
import com.example.bookshop.util.StringFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private TypeDao typeDao;

    /**
     * 添加书籍。检查书籍是否为空、id/书名/价格/作者是否为空，id是否已被注册，手机号格式、图片地址、价格范围等是否正确
     * @param book 待添加的书籍
     * @throws AddException 各类异常
     */
    @Override
    public void add(Book book) throws AddException {
        if (book == null) {
            throw new AddException("添加书籍错误：书籍为空");
        } else if (StringFormatUtil.hasEmpty(book.getName())
                || StringFormatUtil.hasEmpty(book.getAuthor())) {
            throw new AddException("添加书籍错误：书名含空/作者含空/价格含空");
        } else if (StringFormatUtil.isDouble(String.valueOf(book.getPrice())) || book.getPrice() < 0) {
            throw new AddException("添加书籍错误：价格格式/范围不正确");
        } else if (book.getImage() != null && !StringFormatUtil.isImage(book.getImage().toString())) {
            throw new AddException("添加书籍错误：图片地址/格式不正确，适用的图片格式为：bmp/gif/jpeg/jpg/png/raw/tif");
        } else if (book.getScore() < 0 || book.getScore() > 10) {
            throw new AddException("添加书籍错误：书籍评分不正确，应为0-10");
        }

        int result;
        try {
            result = bookDao.add(book);
        } catch (Exception e) {
            System.out.println("添加书籍失败");
            e.printStackTrace();
            throw new AddException("添加书籍失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("添加书籍成功");
    }

    @Override
    public void delete(Serializable id) throws DeleteException {
        if (id == null) {
            throw new DeleteException("删除书籍错误：书籍为空");
        }

        int result;
        try {
            result = bookDao.delete(id);
        } catch (Exception e) {
            System.out.println("删除书籍失败");
            e.printStackTrace();
            throw new DeleteException("删除书籍失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("删除书籍成功");
    }

    /**
     * 更新书籍。id不允许修改，其他的与添加书籍判断条件相同
     * @param book 待更新的书籍
     * @throws UpdateException 各类异常
     */
    @Override
    public void update(Book book) throws UpdateException {
        if (book == null) {
            throw new UpdateException("更新书籍错误：书籍为空");
        } else if (StringFormatUtil.hasEmpty(book.getName()) || StringFormatUtil.hasEmpty(book.getAuthor())) {
            throw new UpdateException("更新书籍错误：书名含空/作者含空/价格含空");
        } else if (StringFormatUtil.isDouble(String.valueOf(book.getPrice())) || book.getPrice() < 0) {
            throw new UpdateException("更新书籍错误：价格格式/范围不正确");
        } else if (book.getImage() != null && !StringFormatUtil.isImage(book.getImage().toString())) {
            throw new UpdateException("更新书籍错误：图片地址/格式不正确，适用的图片格式为：bmp/gif/jpeg/jpg/png/raw/tif");
        } else if (book.getScore() < 0 || book.getScore() > 10) {
            throw new UpdateException("更新书籍错误：书籍评分不正确，应为0-10");
        }

        int result;
        try {
            result = bookDao.update(book);
        } catch (Exception e) {
            System.out.println("更新书籍失败");
            e.printStackTrace();
            throw new UpdateException("更新书籍失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("更新书籍成功");
    }

    @Override
    public Book findOneById(Serializable id) {
        if (id == null) return null;
        return bookDao.findOneById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findOneByName(String name) {
        if (name == null) return null;

        return bookDao.findOneByName(name);
    }

    /**
     * 查找价格在[price1, price2]之间的书籍，需使price1小于price2且price1不小于0
     * @param price1 价格下限
     * @param price2 价格上限
     * @return 书籍集合
     */
    @Override
    public List<Book> findPriceRangeIn(double price1, double price2) {
        //if (price1 > price2 || price1 < 0)
        //    throw new SelectException("查找出错：请检查价格范围");

        return bookDao.findPriceRangeIn(price1, price2);
    }

    @Override
    public List<Book> findBooksByType(int type) {
        return bookDao.findBooksByType(type);
    }

    @Override
    public int findMaxId() {
        return bookDao.findMaxId();
    }

    @Override
    public List<Book> getCarouselBooks() {
        return bookDao.getCarouselBooks();
    }

    @Override
    public List<Book> getBestseller(int count) {
        return bookDao.getBestseller(count);
    }

    @Override
    public List<Book> getHighScoreBooks(int index) {
        if (index < 0) {
            return bookDao.getHighScoreBooks(0);
        }
        else if (4 * index + 4 < getBookCount()) {
            return bookDao.getHighScoreBooks(index);
        }
        else {
            return bookDao.getHighScoreBooks(index % (getBookCount() / 4));
        }
    }

    @Override
    public int getBookCount() {
        return bookDao.getBookCount();
    }

    @Override
    public List<Book> getBooksByType() {
        return bookDao.getBooksByType();
    }

    @Override
    public List<String> getCountries() {
        return bookDao.getCountries();
    }

    @Override
    public List<Book> findBooksByNation(String[] nations) {
        if (nations == null || nations.length == 0) return null;

        String nation = StringFormatUtil.StrArrToSqlIn(nations);
        return bookDao.findBooksByNation(nation);
    }

    @Override
    public List<Book> findScoreRangeIn(double score1, double score2) {
        return bookDao.findScoreRangeIn(score1, score2);
    }

    @Override
    public List<Book> findByLikeName(String name) {
        return bookDao.findByLikeName(StringFormatUtil.addLikeSymbol(name));
    }

    @Override
    public List<Book> findByTypes(int[] types) {
        String str = StringFormatUtil.intArrToSqlIn(types);
        return bookDao.findByTypes(str);
    }

    @Override
    public List<Book> findRangeInDoubleArr(double[][] arr, String attribute) {
        String str = StringFormatUtil.doubleArrToSqlRangeIn(arr, attribute);
        if (str == null) return null;
        return bookDao.findRangeInDoubleArr(str);
    }

    @Override
    public List<Book> findByTypeOrderByScore(int type, int count) {
        if (type <= 0 || count <= 0) return null;

        return bookDao.findByTypeOrderByScore(type, count);
    }

    @Override
    public Map<String, List<Book>> getHomePageGroupByType(int count) {
        Map<String, List<Book>> result = new HashMap<>();

        int typeCount = typeDao.getTypeCount();
        int[] typeIds = typeDao.getTypeIds();
        for (int i = 0; i < typeCount; i++) {
            int typeKey = typeIds[i];
            List<Book> books = findByTypeOrderByScore(typeKey, count);
            if (books.size() > 0) {
                String eName = typeDao.getENameById(typeKey);
                result.put(eName, books);
            }
        }
        return result;
    }

    @Override
    public List<Book> findSearchBooks(int[] types, String[] nations, String[] prices, String[] scores, String name) {
        List<Book> books = findByTypes(types);
        books.retainAll(findBooksByNation(nations));
        books.retainAll(findRangeInDoubleArr(StringFormatUtil.splitWebStrArr(prices), "price"));
        books.retainAll(findRangeInDoubleArr(StringFormatUtil.splitWebStrArr(scores), "score"));
        books.retainAll(findByLikeName(name));

        return books;
    }
}
