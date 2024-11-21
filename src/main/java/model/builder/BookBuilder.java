package model.builder;

import model.Book;

import java.time.LocalDate;

//Desig Pattern Creational
public class BookBuilder {
    private Book book;
    public BookBuilder(){
        book= new Book(); //ii pattern creational deci
                        // ni se permite sa folosim new

    }
    public BookBuilder setId(Long id){
        book.setId(id);
        return this;
    }
    public BookBuilder setTitle(String title){
        book.setTitle(title);
        return this;
    }
    public BookBuilder setAuthor(String author){
        book.setAutor(author);
        return this;
    }
    public BookBuilder setPublishedDate(LocalDate publishedDate) {
        book.setPublichedDate(publishedDate);
        return this;
    }
    public Book build(){
        return book;
    }
}
