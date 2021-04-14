package com.example.bookshop.controller;

import com.example.bookshop.domain.Book;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alu
 * @date 2020/4/17 1:49
 */
@RestController
public class MallController {
    @Autowired
    private BookService bookService;
    @Autowired
    private TypeService typeService;

    /**
     * 获取商城界面推荐的5本书，直接返回高销量的5本 /mall
     * @return 5本热销书籍
     */
    @RequestMapping(value = "/mall", method = RequestMethod.GET)
    public List<Book> getRecommendBooks() {
        return bookService.getBestseller(5);
    }

    /**
     * 获取所有书籍的国家列表 /mall/countries，/manage/countries
     * @return 国家列表
     */
    @RequestMapping(value = {"/mall/countries", "/manage/countries"}, method = RequestMethod.GET)
    public List<String> getBooksCountries() {
        return bookService.getCountries();
    }


    /**
     * 商城界面的多条件查询 /mall/search
     * @param types 书籍种类 int[]
     * @param nations 国家 String[]
     * @param prices 价格区间 例如10a20, 100a200 表示[10,20],[100,200]
     * @param scores 分数区间 例如1a2, 3a4 表示[1,2],[3,4]
     * @param name 模糊查询 书籍名称
     * @return 查询的书籍结果
     */
    @RequestMapping(value = "/mall/search", method = RequestMethod.GET)
    /*public List<Book> findBooks(@RequestParam(name = "type[]") int[] types,
                                             @RequestParam(name = "nation[]") String[] nations,
                                             @RequestParam(name = "price[]") String[] prices,
                                             @RequestParam(name = "score[]") String[] scores,
                                             @RequestParam(name = "name") String name) {*/
    public List<Book> findBooks(int[] types, String[] nations, String[] prices, String[] scores, String name) {
        //Map<String, List<Book>> result = new HashMap<>();
        //Set<Book> books = new HashSet<>(bookService.findByTypes(types));
        //books.retainAll(bookService.findBooksByNation(nations));

        //books.retainAll(bookService.findRangeInDoubleArr(StringFormatUtil.splitWebStrArr(prices), "price"));
        //books.retainAll(bookService.findRangeInDoubleArr(StringFormatUtil.splitWebStrArr(scores), "score"));
        //books.retainAll(bookService.findByLikeName(name));

        return bookService.findSearchBooks(types, nations, prices, scores, name);
    }
}
