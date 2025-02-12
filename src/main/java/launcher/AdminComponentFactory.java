package launcher;

import controller.AdminController;
import controller.BookController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.BookMapper;
import mapper.UserMapper;
import model.User;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.user.AuthentificationService;
import service.user.AuthentificationServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.AdminView;
import view.BookView;
import view.model.BookDTO;
import view.model.UserDTO;

import java.sql.Connection;
import java.util.List;

public class AdminComponentFactory {
    private final AdminView adminView;
    private final AdminController adminController;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final AuthentificationService authentificationService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private static volatile AdminComponentFactory instance;

    public static AdminComponentFactory getInstance(Boolean componentsForTest, Stage primaryStage) {
        //TO DO
        //sa fie thread-safe + lazy -> variabile volatile , syncronize, ...
        if (instance == null) {
            {
                synchronized (EmployeeComponentFactory.class) {
                    if (instance == null) {
                        instance = new AdminComponentFactory(componentsForTest, primaryStage);
                    }
                }
            }
        }
        return instance;
    }

    //contructor - ar putea cauza o problema fiindca e public
    private AdminComponentFactory(Boolean componentsFortest, Stage primaryStage) {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsFortest).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection,rightsRolesRepository);
        this.authentificationService = new AuthentificationServiceImpl(userRepository, rightsRolesRepository);
        this.userService = new UserServiceImpl(userRepository);
        List<UserDTO> users = UserMapper.convertUserListToUserDTOList(userService.findAll());
        this.adminView = new AdminView(primaryStage, users);

        this.orderRepository = new OrderRepositoryMySQL(connection);
        this.orderService = new OrderServiceImpl(orderRepository);

        this.bookRepository = new BookRepositoryMySQL(connection);
        this.bookService = new BookServiceImpl(bookRepository);

        this.adminController = new AdminController(adminView, userService, authentificationService ,orderService ,bookService );


    }

    public AdminView getAdminView() {
        return adminView;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public AuthentificationService getAuthentificationService() {
        return authentificationService;
    }

    public UserService getUserService() {
        return userService;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public static AdminComponentFactory getInstance() {
        return instance;
    }
}
