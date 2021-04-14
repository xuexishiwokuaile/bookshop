package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.UserDao;
import com.example.bookshop.domain.User;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.exception.UserException;
import com.example.bookshop.service.UserService;
import com.example.bookshop.util.MD5Util;
import com.example.bookshop.util.StringFormatUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) throws AddException {
        if (user == null
        || StringFormatUtil.hasEmpty(user.getId())
        || userDao.findOneById(user.getId()) != null)
            throw new AddException("添加用户错误：id含空/id已被注册");
        else if (StringFormatUtil.hasEmpty(user.getName())
                || StringFormatUtil.hasEmpty(user.getPassword())
                || StringFormatUtil.isPhoneNum(user.getTel()))
            throw new AddException("添加用户错误：姓名含空/密码含空/手机号含空/手机号格式不正确");

        int result;
        try {
            //user.setPassword(MD5Util.md5Encryption(user.getPassword()));    //进行密码加密
            result = userDao.add(user);
        } catch (Exception e) {
            System.out.println("添加用户失败");
            e.printStackTrace();
            throw new AddException("添加用户失败" + e.getMessage());
        }
        if (result > 0) System.out.println("添加用户成功");
    }

    @Override
    public void delete(Serializable id) throws DeleteException {
        if (userDao.findOneById(id) == null) throw new DeleteException("删除用户错误：用户为空");

        int result;
        try {
            result = userDao.delete(id);
        } catch (Exception e) {
            System.out.println("删除用户失败");
            e.printStackTrace();
            throw new DeleteException("删除用户失败" + e.getMessage());
        }
        if (result > 0) System.out.println("删除用户成功");
    }

    @Override
    public void update(User user) throws UpdateException {
        if (user == null) throw new UpdateException("更新用户错误：用户为空");

        int result;
        try {
            result = userDao.update(user);
        } catch (Exception e) {
            System.out.println("更新用户失败");
            e.printStackTrace();
            throw new UpdateException("更新用户错误" + e.getMessage());
        }
        if (result > 0) System.out.println("更新用户成功");
    }

    @Override
    public User findOneById(Serializable id) {
        if (id == null) return null;

        return userDao.findOneById(id);
    }

    public User findOneByName(String name)
    {
        if(name == null)
            return null;
        return userDao.findOneByName(name);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void login(String name, String password) throws UserException {
        User user = findOneByName(name);

        if (user != null)  {
            //String tmpPsw = MD5Util.md5Encryption(password);
            //if (!tmpPsw.equals(user.getPassword())) {
            //    throw new UserException("登录错误：账号/密码不正确");
            //}
            if (!password.equals(user.getPassword())) {
                throw new UserException("登录错误：账号/密码不正确");
            }
        } else {
            throw new UserException("登录错误：账号/密码不正确");
        }

        //UsernamePasswordToken token = new UsernamePasswordToken((String) id, password);
        //Subject curUser = SecurityUtils.getSubject();
        //curUser.login(token);
        //curUser.getSession().setAttribute("curUser", user);
        //curUser.getSession().setAttribute("curAuthority", user.getAuthority());
    }

    @Override
    public int getAuthorityById(Serializable id) {
        return userDao.getAuthorityById(id);
    }

    public int getAuthorityByName(String name)
    {
        return userDao.getAuthorityByName(name);
    }

}
