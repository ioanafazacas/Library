package view.model;

import javafx.beans.property.*;

import java.sql.Timestamp;

public class OrderDTO {
    private StringProperty author;
    public void setAuthor(String author){
        authorProperty().set(author);
    }
    public String getAuthor(){
        return authorProperty().get();
    }
    public StringProperty authorProperty(){
        if(author == null){
            author = new SimpleStringProperty(this, "author");
        }
        return author;
    }

    private StringProperty title;
    public void setTitle(String title){
        titleProperty().set(title);
    }
    public String getTitle(){
        return titleProperty().get();
    }
    public StringProperty titleProperty(){
        if(title == null){
            title= new SimpleStringProperty(this, "title");
        }
        return title;
    }

    private FloatProperty price;
    public void setPrice(float price){
        priceProperty().set(price);
    }
    public float getPrice(){
        return (float)priceProperty().get();
    }
    public FloatProperty priceProperty(){
        if(price == null){
            price= new SimpleFloatProperty(this, "price");
        }
        return price;
    }

    private StringProperty saleDate;
    public void setSaleDate(Timestamp saleDate){
        saleDateProperty().set(String.valueOf(saleDate));
    }
    public Timestamp getSaleDate(){
        return Timestamp.valueOf(saleDateProperty().get());
    }
    public StringProperty saleDateProperty(){
        if(saleDate == null){
            saleDate= new SimpleStringProperty(this, "saleDate");
        }
        return saleDate;
    }
}
