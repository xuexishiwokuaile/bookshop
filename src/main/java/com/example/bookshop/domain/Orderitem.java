package com.example.bookshop.domain;

/**
 * @author Alu
 * @date 2020/4/13 20:56

 SET NAMES utf8mb4;
 SET FOREIGN_KEY_CHECKS = 0;

 -- ----------------------------
 -- Table structure for orderitem
 -- ----------------------------
 DROP TABLE IF EXISTS `orderitem`;
 CREATE TABLE `orderitem`  (
 `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
 `book` int(11) NOT NULL AUTO_INCREMENT COMMENT '书籍',
 `count` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
 `order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单',
 PRIMARY KEY (`id`) USING BTREE,
 INDEX `fk_oneorder_order`(`order`) USING BTREE,
 INDEX `fk_oneorder_book`(`book`) USING BTREE,
 CONSTRAINT `fk_oneorder_book` FOREIGN KEY (`book`) REFERENCES `book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
 CONSTRAINT `fk_oneorder_order` FOREIGN KEY (`order`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
 ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

 SET FOREIGN_KEY_CHECKS = 1;
 */
public class Orderitem {
    private String id;
    private int book;
    private int count;
    private String order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "id='" + id + '\'' +
                ", book=" + book +
                ", count=" + count +
                ", order='" + order + '\'' +
                '}';
    }
}
