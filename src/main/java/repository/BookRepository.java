package repository;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> dinfById(Long id);//optional ne ajuta sa scapam de null (ca rezultat de return)
    boolean save(Book book);
    boolean delete(Book book);
    void removeAll();
}
