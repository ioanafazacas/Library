package org.example;


import controller.LoginController;
import database.JDBConnectionWrapper;
import javafx.application.Application;
import javafx.stage.Stage;
import launcher.LoginComponentFactory;
import launcher.OrderFactory;
import model.builder.BookBuilder;
import model.Book;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthentificationService;
import service.user.AuthentificationServiceImpl;
import view.LoginView;

import java.sql.Connection;
import java.time.LocalDate;

import static database.Constants.Schemas.PRODUCTION;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Book book= new BookBuilder().setTitle("Ion")
                .setAuthor("Liviu Rebreanu")
                .setPublishedDate(LocalDate.of(1910,10,20))
                .build();
        System.out.println(book);
/*
        BookRepository bookRepository = new BookRepositoryMock();

        bookRepository.save(book);
        bookRepository.save(new BookBuilder().setAuthor("Ioan Creanga").setTitle("Harap Alb").setPublishedDate(LocalDate.of(1880,10,11)).build());
        System.out.println(bookRepository.findAll());
        bookRepository.removeAll();
        System.out.println(bookRepository.findAll());

         */
        //DatabaseConnectionFactory.getConnectionWrapper(false);
        /*Connection connection= DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        BookRepository bookRepository= new BookRepositoryMySQL(connection);
        BookService bookService= new BookServiceImpl(bookRepository);

        bookService.save(book);
        System.out.println(bookService.findAll());
        Book bookHarapAlb= new BookBuilder().setAuthor("Ioan Creanga").setTitle("Harap Alb").setPublishedDate(LocalDate.of(1880,10,11)).build();
        bookService.save(bookHarapAlb);
        System.out.println(bookService.findAll());
        bookService.delete(bookHarapAlb);
        bookService.delete(book);
        System.out.println(bookService.findAll());



        Connection connection= DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        OrderRepository orderRepository= new OrderRepositoryMySQL(connection);
        OrderService orderService= new OrderServiceImpl(orderRepository);

        System.out.println(orderService.findAll());
        orderService.save(new OrderBuilder().setTitle("George Bacovia").setTitle("Plumb").setprice(20).setQuantity(2).build());
        System.out.println(orderService.findAll());

        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();
        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connection), new Cache<>());
        //Connection connection= DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        AuthentificationService authentificationService= new AuthentificationServiceImpl(userRepository, rightsRolesRepository);

        if(userRepository.existsByUsername("Andrei"))
        {
            System.out.println("Avem deja acest utilizator");
        }else {
            authentificationService.register("Andrei", "parola04");
        }
        System.out.println("USER:");

        System.out.println(authentificationService.login("Andrei","parola04"));
*/
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        final Connection connection = new JDBConnectionWrapper(PRODUCTION).getConnection();

        final RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        final UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

        final AuthentificationService authentificationService= new AuthentificationServiceImpl(userRepository, rightsRolesRepository);

        final LoginView loginView = new LoginView(primaryStage);

        new LoginController(loginView, authentificationService);
    */
        LoginComponentFactory componentFactory = LoginComponentFactory.getInstance(false, primaryStage);
        //OrderFactory.getInstance(false,primaryStage);
    }

}