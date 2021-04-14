package com.example.bookshop.controller;

import com.example.bookshop.domain.User;
import com.example.bookshop.util.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.UserService;
import com.example.bookshop.util.ReturnMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements BaseController<User> {
    @Autowired
    UserService userService;

    /**
     * 增加用户 URL: /user/add
     * @param user 待添加的对象
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReturnMsgUtil add(@RequestBody User user) {
        user.setId(StringFormatUtil.randomString());
        try {
            userService.add(user);
            return new ReturnMsgUtil(successCode, user.getId());
        } catch (AddException e) {
            return new ReturnMsgUtil(failCode, e.getMessage());
        }
    }

    /**
     * 删除用户 URL: /user/delete
     * @param id 待删除的对象的id
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ReturnMsgUtil delete(@RequestParam(value = "id") Serializable id) {
        try {
            userService.delete(id);
            return new ReturnMsgUtil(successCode, "success");
        } catch (DeleteException e) {
            return new ReturnMsgUtil(failCode, e.getMessage());
        }
    }

    /**
     * 更新用户 URL: /user/update
     * @param user 待更新的对象
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ReturnMsgUtil update(@RequestBody User user) {
        try {
            userService.update(user);
            return new ReturnMsgUtil(successCode, "success");
        } catch (UpdateException e) {
            return new ReturnMsgUtil(failCode, e.getMessage());
        }
    }

    /**
     * 通过ID查找用户 URL: /user/findOneById
     * @param id 待查找的用户ID
     * @return 待查找的用户
     */
    @Override
    @RequestMapping(value = "/findOneById", method = RequestMethod.GET)
    public User findOneById(@RequestParam(value = "id") Serializable id) {
        return userService.findOneById(id);
    }

    @RequestMapping(value = "/findOneByName",method = RequestMethod.GET)
    public User findOneByName(@RequestParam(value = "name")String name)
    {
        return userService.findOneByName(name);
    }

    /**
     * 查找所有的用户
     * @return 用户集合
     */
    @Override
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }
}
