package com.example.bookshop.controller;

import com.example.bookshop.domain.Book;
import com.example.bookshop.domain.User;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.UserService;
import com.example.bookshop.util.ReturnMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alu
 * @date 2020/4/9 10:54
 */
@RestController
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    private static int successCode = 0;
    private static int failCode = 1;

    /**
     * 登录
     * @param user 用户
     * @return 成功返回0，失败返回1
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnMsgUtil login(@RequestBody User user) {
        try {
            userService.login(user.getName(), user.getPassword());
        } catch (Exception e) {
            return new ReturnMsgUtil(failCode, e.getMessage());
        }
        //return new ReturnMsgUtil(successCode, String.valueOf(SecurityUtil.getAuthority()));
        return new ReturnMsgUtil(successCode, String.valueOf(userService.getAuthorityByName(user.getName())));
    }

    /**
     * 首页书籍清单
     * @return 书籍清单
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Map<String, List<Book>> getHomeBookList() {
        //ModelAndView modelAndView = new ModelAndView("/index");
        //轮播视图 返回五本书
        //modelAndView.addObject("carousel", bookService.getCarouselBooks());
        //畅销排行
        //modelAndView.addObject("rank", bookService.getBestseller(10));
        //热门/猜你喜欢
        //modelAndView.addObject("hot", bookService.getHighScoreBooks(0));
        //各类别书籍
        //modelAndView.addObject("type", bookService.getBooksByType());

        //return modelAndView;

        Map<String, List<Book>> result = new HashMap<>();
        result.put("carousal", bookService.getCarouselBooks());
        result.put("rank", bookService.getBestseller(10));
        result.put("hot", bookService.getHighScoreBooks(0));
        result.putAll(bookService.getHomePageGroupByType(4));
        return result;
    }

    /**
     * 首页-换一批，获取另外四本书籍
     * @param index 批次，用来避免重复
     * @return 另外四本书籍
     */
    @RequestMapping(value = "/index/another", method = RequestMethod.GET)
    public List<Book> getAnotherFourBooks(@RequestParam("index") int index) {
        return bookService.getHighScoreBooks(index);
    }
}
