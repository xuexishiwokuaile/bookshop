package com.example.bookshop.domain;

import java.io.Serializable;

/**
 SET NAMES utf8mb4;
 SET FOREIGN_KEY_CHECKS = 0;

 -- ----------------------------
 -- Table structure for user
 -- ----------------------------
 DROP TABLE IF EXISTS `user`;
 CREATE TABLE `user`  (
 `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id：要求：不允许含任何空格',
 `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名，要求：不允许含任何空格，包括""，\\t， \\n'， \\r等',
 `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码，要求：不允许含任何空格',
 `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话，要求：11位手机号码',
 `authority` tinyint(4) NOT NULL DEFAULT 1 COMMENT '权限，0-管理员；1-顾客，默认1',
 PRIMARY KEY (`id`) USING BTREE
 ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

 SET FOREIGN_KEY_CHECKS = 1;
 */

public class User implements Serializable {
    private String id;
    private String name;
    private String password;
    private String tel;
    private int authority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", authority=" + authority +
                '}';
    }
}
