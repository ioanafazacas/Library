package repository.book;

import model.Book;

import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator{
    private Cache<Book> cache;
    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache){
        super(bookRepository);
        this.cache=cache;
    }
    @Override
    public List<Book> findAll() {
        if(cache.hasResult()){
            return cache.load();
        }
        List<Book> books= decorateBookRepository.findAll();
        cache.save(books);
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        if(cache.hasResult()){
            return cache.load().stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }
        return decorateBookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByTitleAndAuthor(String title, String auther) {
        if(cache.hasResult()){
            return cache.load().stream()
                    .filter(it -> it.getTitle().equals(title) & it.getAutor().equals(auther))
                    .findFirst();
        }
        return decorateBookRepository.findByTitleAndAuthor(title,auther);
    }

    @Override
    public boolean save(Book book) {
        cache.invalidateCache();

        return decorateBookRepository.save(book);
    }

    @Override
    public boolean updateQuantity(Book book) {
        return decorateBookRepository.updateQuantity(book);
    }

    @Override
    public boolean delete(Book book) {
        cache.invalidateCache();
        return decorateBookRepository.delete(book);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decorateBookRepository.removeAll();
    }
}
