package com.example.bookshop.domain;

/**
 * @author Alu
 * @date 2020/4/17 0:17

 SET NAMES utf8mb4;
 SET FOREIGN_KEY_CHECKS = 0;

 -- ----------------------------
 -- Table structure for type
 -- ----------------------------
 DROP TABLE IF EXISTS `type`;
 CREATE TABLE `type`  (
 `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT 'id，非负整数',
 `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '中文类型名',
 `ename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '英文类型名',
 PRIMARY KEY (`id`) USING BTREE
 ) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '书籍类型' ROW_FORMAT = Dynamic;

 -- ----------------------------
 -- Records of type
 -- ----------------------------
 INSERT INTO `type` VALUES (1, '未分类', 'none');
 INSERT INTO `type` VALUES (2, '其他', 'others');
 INSERT INTO `type` VALUES (3, '文学', 'literature');
 INSERT INTO `type` VALUES (4, '政治', 'politics');
 INSERT INTO `type` VALUES (5, '军事', 'military');
 INSERT INTO `type` VALUES (6, '艺术', 'art');
 INSERT INTO `type` VALUES (7, '地理', 'geography');
 INSERT INTO `type` VALUES (8, '科幻', 'fiction');
 INSERT INTO `type` VALUES (9, '悬疑', 'suspense');
 INSERT INTO `type` VALUES (10, '科学', 'science');
 INSERT INTO `type` VALUES (11, '教育', 'education');
 INSERT INTO `type` VALUES (12, '历史', 'history');
 INSERT INTO `type` VALUES (13, '哲学', 'philosophy');

 SET FOREIGN_KEY_CHECKS = 1;
 */
public class Type {
    private int id;
    private String cname;
    private String ename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", ename='" + ename + '\'' +
                '}';
    }
}
