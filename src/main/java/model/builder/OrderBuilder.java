package model.builder;


import model.Order;

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
    public OrderBuilder setTitle(String title){
        order.setTitle(title);
        return this;
    }
    public OrderBuilder setAuthor(String author){
        order.setAuthor(author);
        return this;
    }
    public OrderBuilder setSaleDate(LocalDate publishedDate) {
        order.setSaleDate(publishedDate);
        return this;
    }
    public OrderBuilder setQuantity(int quantity){
        order.setQuantity(quantity);
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
