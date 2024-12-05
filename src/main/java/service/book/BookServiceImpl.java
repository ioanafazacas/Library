package service.book;

import model.Book;
import repository.book.BookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Book with id %d was not found ".formatted(id)));
    }

    @Override
    public Book findByTitleAndAuthor(String title, String auther) {
        return bookRepository.findByTitleAndAuthor(title,auther).orElseThrow(() ->new IllegalArgumentException("Book with title %s and auther %s was not found ".formatted(title,auther)));
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean updateQuantity(Book book) {
        return bookRepository.updateQuantity(book);
    }

    @Override
    public boolean delete(Book book) {
        return bookRepository.delete(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        Book book = this.findById(id);
        LocalDate now= LocalDate.now();

        return (int) ChronoUnit.YEARS.between(book.getPublichedDate(),now);
    }
}
