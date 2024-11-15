package org.example;


import builder.BookBuilder;
import builder.OrderBuilder;
import database.DatabaseConnectionFactory;
import model.Book;
import repository.*;
import service.BookService;
import service.BookServiceImpl;
import service.OrderService;
import service.OrderServiceImpl;

import java.sql.Connection;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
        */


        Connection connection= DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        OrderRepository orderRepository= new OrderRepositoryMySQL(connection);
        OrderService orderService= new OrderServiceImpl(orderRepository);

        System.out.println(orderService.findAll());
        orderService.save(new OrderBuilder().setTitle("George Bacovia").setTitle("Plumb").setprice(20).setQuantity(2).build());
        System.out.println(orderService.findAll());


    }
}