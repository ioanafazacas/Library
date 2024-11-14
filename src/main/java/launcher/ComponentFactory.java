package launcher;

import controller.BookController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.BookMapper;
import model.Book;
import repository.BookRepository;
import repository.BookRepositoryMySQL;
import service.BookService;
import service.BookServiceImpl;
import view.BookView;
import view.model.BookDTO;

import java.sql.Connection;
import java.util.List;

//SINGLETON
public class ComponentFactory {
    private final BookView bookView;
    private final BookController bookController;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private static volatile ComponentFactory instance;
    public static ComponentFactory getInstance(Boolean componentsForTest, Stage primaryStage){
        //TO DO
        //sa fie thread-safe + lazy -> variabile volatile , syncronize, ...
        if(instance == null){
            {
                synchronized (ComponentFactory.class){
                    if(instance == null){
                        instance= new ComponentFactory(componentsForTest, primaryStage);
                    }
                }
            }
        }
        return instance;
    }
    //contructor - ar putea cauza o problema fiindca e public
    private ComponentFactory(Boolean componentsFortest, Stage primaryStage){
        Connection connection= DatabaseConnectionFactory.getConnectionWrapper(componentsFortest).getConnection();
        this.bookRepository = new BookRepositoryMySQL(connection);
        this.bookService = new BookServiceImpl(bookRepository);
        List<BookDTO> books = BookMapper.converBookListToBookDTOList(bookService.findAll());
        this.bookView = new BookView(primaryStage,books);
        this.bookController= new BookController(bookView, bookService);

    }

    public BookView getBookView() {
        return bookView;
    }

    public BookController getBookController() {
        return bookController;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BookService getBookService() {
        return bookService;
    }
}
