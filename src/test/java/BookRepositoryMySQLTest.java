import model.builder.BookBuilder;
import database.DatabaseConnectionFactory;
import model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryMySQLTest {
    private static BookRepository bookRepository;
    private static final Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();;
    @BeforeAll
    public static void setup(){
        bookRepository = new BookRepositoryMySQL(connection);
    }
    /*@Test
    public void findAll(){
        List<Book> books= bookRepository.findAll();
        assertEquals(0, books.size());
    }
    public void findById(Long id){
        final Optional<Book> book= bookRepository.findById(1L);
        assertTrue(book.isEmpty());
    }
    public void save(Book book){
        assertTrue(bookRepository.save(new BookBuilder().setTitle("Poezii").setAuthor("Tudor Arghezii")
                .setPublishedDate(LocalDate.of(1920,8,8)).build()));
    }
    public void delete(Book book){
        assertFalse(bookRepository.delete(new BookBuilder().setTitle("Poezii").setAuthor("George Bacovia")
                .setPublishedDate(LocalDate.of(1920,8,8)).build()));
    }
    public void removeAll(){
        bookRepository.save(new BookBuilder().setTitle("Luceafarul").setAuthor("Mihai Eminescu")
                .setPublishedDate(LocalDate.of(1888,8,8)).build());
        bookRepository.removeAll();
        List<Book> books= bookRepository.findAll();
        assertEquals(0, books.size());
    }*/
}
