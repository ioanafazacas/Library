package model;

import java.time.LocalDate;

public class Order {
    private Long id;
    private String title;
    private String author;
    private LocalDate saleDate;
    private int quantity;
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

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return "Order id: " + id + " Title: " +
                title + " Author: " + author +
                " sale date: " + saleDate +
                " quantity: " + quantity +
                " price: " + price;
    }
}
