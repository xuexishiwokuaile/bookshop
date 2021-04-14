package com.example.bookshop.domain;

import java.util.function.BinaryOperator;

/**
 * @author Alu
 * @date 2020/4/8 17:44
 */
public class Sales {
    private int book;
    private int count;
    private int state;
    private int hot;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "book=" + book +
                ", count=" + count +
                ", state=" + state +
                ", hot=" + hot +
                '}';
    }
}
