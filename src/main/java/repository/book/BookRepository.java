package repository.book;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(Long id);//Optional<> ne ajuta sa scapam de null (ca rezultat de return)
    Optional<Book> findByTitleAndAuthor(String title, String auther);
    boolean save(Book book);
    boolean updateQuantity(Book book);
    boolean delete(Book book);
    void removeAll();
}
