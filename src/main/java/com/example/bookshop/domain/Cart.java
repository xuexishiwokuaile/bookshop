package com.example.bookshop.domain;

/**
 * @author Chen Anran
 * @date 2020/4/10
 */
public class Cart {
    private String id;
    private int book;
    private int count;
    private String user;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", book='" + book + '\'' +
                ", count=" + count +
                ", user='" + user + '\'' +
                '}';
    }
}