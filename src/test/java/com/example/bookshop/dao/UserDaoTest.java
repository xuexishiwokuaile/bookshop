package com.example.bookshop.dao;

import com.example.bookshop.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void add() {
        User user = new User();
        user.setId("0404testAddUser");
        user.setName("testAddUser");
        user.setPassword("123456");
        user.setTel("17362447820");
        user.setAuthority(1);

        int result;
        result = userDao.add(user);
        if (result > 0) System.out.println("添加用户成功");
    }

    @Test
    @Transactional
    public void delete() {
        User user = new User();
        user.setId("0404testDeleteUser");
        user.setName("testDeleteUser");
        user.setPassword("123456");
        user.setTel("17362447820");
        user.setAuthority(1);

        int result;
        userDao.add(user);
        result = userDao.delete(user);
        if (result > 0) System.out.println("删除用户成功");
    }

    @Test
    @Transactional
    public void update() {
        User user = new User();
        user.setId("0404testUpdateUser");
        user.setName("testUpdateUser");
        user.setPassword("123456");
        user.setTel("17362447820");
        user.setAuthority(1);

        int result;
        userDao.add(user);

        user.setName("testUpdate");
        result = userDao.update(user);
        if (result > 0) System.out.println("更新用户成功");
    }

    @Test
    @Transactional
    public void findOneById() {
        User user = new User();
        user.setId("0404findOneById");
        user.setName("findOneById");
        user.setPassword("123456");
        user.setTel("17362447820");
        user.setAuthority(1);
        userDao.add(user);

        User result = userDao.findOneById("0404findOneById");
        System.out.println(result);
    }

    @Test
    @Transactional
    public void findAll() {
        User user = new User();
        user.setId("0404findAll1");
        user.setName("findOneById");
        user.setPassword("123456");
        user.setTel("17362447820");
        user.setAuthority(1);
        userDao.add(user);

        User user2 = new User();
        user2.setId("0404findAll2");
        user2.setName("findOneById");
        user2.setPassword("123456");
        user2.setTel("17362447820");
        user2.setAuthority(1);
        userDao.add(user2);

        List<User> result = userDao.findAll();
        System.out.println(result);
    }
}