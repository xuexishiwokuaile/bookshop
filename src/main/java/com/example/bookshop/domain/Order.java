package com.example.bookshop.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Chen Anran
 * @date 2020/4/9
 */
public class Order {
    private String id;
    private Date time;
    private String buyer;
    private String address;
    private double price;
    private Orderitem[] orderitems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orderitem[] getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(Orderitem[] orderitems) {
        this.orderitems = orderitems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", buyer='" + buyer + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", orderitems=" + Arrays.toString(orderitems) +
                '}';
    }
}
