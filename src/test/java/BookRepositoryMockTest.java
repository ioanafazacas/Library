import model.builder.BookBuilder;
import model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.book.BookRepository;
import repository.book.BookRepositoryMock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMockTest {
    private static BookRepository bookRepository;
    @BeforeAll
    public static void setup(){
        bookRepository = new BookRepositoryMock();
    }
    @Test
    public void findAll(){
        List<Book> books= bookRepository.findAll();
        assertEquals(0, books.size());
    }
    @Test
    public void FindById(){
        final Optional<Book> book= bookRepository.findById(1L);
        assertTrue(book.isEmpty());
    }
    @Test
    public void save(){
        assertTrue(bookRepository.save(new BookBuilder().setTitle("Poezii").setAuthor("Tudor Arghezii").setPublishedDate(LocalDate.of(1920,8,8)).build()));
    }
}
