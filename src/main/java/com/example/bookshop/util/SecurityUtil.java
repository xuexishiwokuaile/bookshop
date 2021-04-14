package com.example.bookshop.util;

import com.example.bookshop.domain.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author Alu
 * @date 2020/4/8 13:05
 */
public class SecurityUtil {

    public static User getCurUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute("user");
    }

    public static int getAuthority() {
        return (int) SecurityUtils.getSubject().getSession().getAttribute("authority");
    }
}
