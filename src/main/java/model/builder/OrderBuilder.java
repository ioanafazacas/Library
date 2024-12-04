package model.builder;


import model.Order;

import java.sql.Timestamp;
import java.time.LocalDate;

public class OrderBuilder {
    private Order order;
    public OrderBuilder(){
        this.order = new Order();
    }
    public OrderBuilder setId(Long id){
        order.setId(id);
        return this;
    }
    public OrderBuilder setBookId(Long id){
        order.setBook_id(id);
        return this;
    }
    public OrderBuilder setTitle(String title){
        order.setTitle(title);
        return this;
    }
    public OrderBuilder setAuthor(String author){
        order.setAuthor(author);
        return this;
    }
    public OrderBuilder setSaleDate(Timestamp saleDate) {
        order.setSaleDate(saleDate);
        return this;
    }
    public OrderBuilder setUserId(Long id){
        order.setUser_id(id);
        return this;
    }
    public OrderBuilder setprice(float price){
        order.setPrice(price);
        return this;
    }
    public Order build(){
        return order;
    }
}
