package view.model;


import javafx.beans.property.*;

public class BookDTO {
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

    private IntegerProperty quantity;
    public void setQuantity(int quantity){
        quantityProperty().set(quantity);
    }
    public int getQuantity(){
        return quantityProperty().get();
    }
    public IntegerProperty quantityProperty(){
        if(quantity == null){
            quantity= new SimpleIntegerProperty(this, "quantity");
        }
        return quantity;
    }

    private FloatProperty price;
    public void setPrice(float price){
        priceProperty().set(price);
    }
    public float getPrice(){
        return priceProperty().get();
    }
    public FloatProperty priceProperty(){
        if(price == null){
            price= new SimpleFloatProperty(this, "price");
        }
        return price;
    }

}
