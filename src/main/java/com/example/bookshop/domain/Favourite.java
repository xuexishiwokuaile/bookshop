package com.example.bookshop.domain;

import java.util.Date;

/**
 * @author Chen Anran
 * @date 2020/4/13
 */
public class Favourite {
    private String id;
    private String user;
    private String book;
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", book='" + book + '\'' +
                ", time=" + time +
                '}';
    }
}