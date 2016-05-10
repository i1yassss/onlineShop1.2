package ru.kpfu.itis.model;

import java.sql.Date;
import java.util.Objects;


public class Order {
    private Integer id;
    private Integer idUser;
    private Date date;
    private String address;
    private Integer idGood;
    private Integer amount;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getSum() {
        return price;
    }

    public void setSum(Double sum) {
        this.price = sum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.idGood);
        hash = 11 * hash + Objects.hashCode(this.amount);
        hash = 11 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        if (!Objects.equals(this.idGood, other.idGood)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    public Integer getIdGood() {
        return idGood;
    }

    public void setIdGood(Integer idGood) {
        this.idGood = idGood;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ",  good=" + idGood + ", amount=" + amount + ", sum=" + price + '}';
    }

    public Order(Integer good, Integer amount, Double sum) {
        this.idGood = good;
        this.amount = amount;
        this.price = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Order() {
    }   

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Order(Integer idUser, String address, Integer idGood, Integer amount, Double price) {
        this.idUser = idUser;
        this.address = address;
        this.idGood = idGood;
        this.amount = amount;
        this.price = price;
    }
    
}
