package model;

import java.sql.Timestamp;

public class Order {
    private Long id;
    private Long book_id;
    private String title;
    private String author;
    private Timestamp saleDate;
    private Long user_id;
    private float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString(){
        return "Order id: " + id + " Book id: "+
                book_id + " Title: " +
                title + " Author: " + author +
                " sale date: " + saleDate +
                " user_id: " + user_id+
                " price: " + price;
    }
}
