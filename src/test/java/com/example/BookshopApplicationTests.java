package com.example;

import com.example.bookshop.dao.UserDao;
import com.example.bookshop.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookshopApplicationTests {

    @Test
    public void contextLoads() {
    }

}
