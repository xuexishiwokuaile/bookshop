package com.example.bookshop.domain;

/**
 * @author Chen Anran
 * @date 2020/4/9
 */
public class Address {
    private String id;
    private String nation;
    private String province;
    private String city;
    private String details;
    private String user;
    private int isDefault;
    private String receiver;
    private String tel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
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

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", nation='" + nation + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", details='" + details + '\'' +
                ", user='" + user + '\'' +
                ", isDefault=" + isDefault +
                ", receiver='" + receiver + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}