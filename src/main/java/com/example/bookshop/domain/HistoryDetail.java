package com.example.bookshop.domain;

import java.util.Date;

/**
 * @author Chen Anran
 * @date 2020/4/11
 */
public class HistoryDetail {
    private String id;
    private int count;
    private String time;
    private String name;
    private String author;
    private String image;
    private double price;
    private double score;
    private String intro;
    private String nation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "HistoryDetail{" +
                "id='" + id + '\'' +
                ", count=" + count +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", intro='" + intro + '\'' +
                ", nation='" + nation + '\'' +
                '}';
    }
}