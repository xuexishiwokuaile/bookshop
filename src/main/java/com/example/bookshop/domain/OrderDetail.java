package com.example.bookshop.domain;

/**
 * @author Chen Anran
 * @date 2020/4/9
 */
public class OrderDetail {
    private String id;
    //书籍信息
    private int count;
    private String name;
    private String author;
    private String image;
    private double price;
    private double score;
    private String intro;
    private String booknation;
    //地址信息
    private String ordernation;
    private String province;
    private String city;
    private String details;
    private String receiver;
    private String tel;
    //用户信息
    private String username;

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

    public String getBooknation() {
        return booknation;
    }

    public void setBooknation(String booknation) {
        this.booknation = booknation;
    }

    public String getOrdernation() {
        return ordernation;
    }

    public void setOrdernation(String ordernation) {
        this.ordernation = ordernation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id='" + id + '\'' +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", intro='" + intro + '\'' +
                ", booknation='" + booknation + '\'' +
                ", ordernation='" + ordernation + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", details='" + details + '\'' +
                ", receiver='" + receiver + '\'' +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}