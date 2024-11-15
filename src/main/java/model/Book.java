package model;

import java.time.LocalDate;

public class Book {//trebuie facute modificari , adaugate campurile quantity si price
                    //de asemenea trebuie sa refac tabelul book din mysql
    private Long id;
    private String title;
    private String autor;
    private LocalDate publichedDate;


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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getPublichedDate() {
        return publichedDate;
    }

    public void setPublichedDate(LocalDate publichedDate) {
        this.publichedDate = publichedDate;
    }
    @Override
    public String toString(){
        return "Book id: " + id + " Title: " +
                title + " Author: " + autor +
                " publish date: " + publichedDate;
    }
}
