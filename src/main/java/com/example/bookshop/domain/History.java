package com.example.bookshop.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chen Anran
 * @date 2020/4/11
 */
public class History {
    private String id;
    private Date time;
    private int book;
    private String user;
    private int count;

    public History(String id, Date time, int book, String user, int count) {
        this.id = id;
        this.time = time;
        this.book = book;
        this.user = user;
        this.count = count;
    }

    public History() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "History{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", book=" + book +
                ", user='" + user + '\'' +
                ", count=" + count +
                '}';
    }
}