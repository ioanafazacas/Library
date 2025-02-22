package service.book;

import model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book findByTitleAndAuthor(String title, String auther);
    boolean save(Book book);
    boolean updateQuantity(Book book);
    boolean delete(Book book);
    int getAgeOfBook(Long id);

}
