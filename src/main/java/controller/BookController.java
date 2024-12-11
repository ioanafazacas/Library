package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mapper.BookMapper;
import mapper.OrderMapper;
import model.Book;
import model.User;
import repository.order.OrderRepositoryMySQL;
import service.book.BookService;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.user.AuthentificationService;
import view.BookView;
import view.LoginView;
import view.model.BookDTO;
import view.model.OrderDTO;
import view.model.builder.BookDTOBuilder;
import view.model.builder.OrderDTOBuilder;

public class BookController {
    private final BookView bookView;
    private final BookService bookService;
    private final OrderService orderService;
    private final User user;
    public BookController(BookView bookView, BookService bookService, OrderService orderService, User user){
        this.bookService = bookService;
        this.bookView = bookView;
        this.orderService= orderService;
        this.user = user;

        this.bookView.addSaveButtonListener(new saveButtonListener());
        this.bookView.addDeleteButtonListener(new deleteButtonListener());
        this.bookView.addBuyButtonListener(new buyButtonListener());
    }
    private class saveButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            String title= bookView.getTitle();
            String author= bookView.getAuthor();
            int quantity= bookView.getQuantity();
            float price= bookView.getPrice();

            if(title.isEmpty() || author.isEmpty() || quantity==0 || price==0.0){
                bookView.addDisplayAlertMessage("Save error","Problem at Author or Title fields", "Can not have an empty title or auther fild");

            }else{
                BookDTO bookDTO= new BookDTOBuilder().setTitle(title)
                        .setAuthor(author).setQuantity(quantity)
                        .setPrice(price).build();
                boolean saveBook= bookService.save(BookMapper.convertBookDTOToBook(bookDTO));

                if(saveBook){
                    bookView.addDisplayAlertMessage("Save Successful","Book added", "Book was successfully added to database");
                    bookView.addBookToObeservableList(bookDTO);

                }else{
                    bookView.addDisplayAlertMessage("Save error","Problem at adding Book", "There was a problem at adding the book to the database. Please try again");

                }
            }
        }
    }
    private class deleteButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            BookDTO bookDTO= (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();
            if(bookDTO != null){
                boolean deleteSuccesful = bookService.delete(BookMapper.convertBookDTOToBook(bookDTO));

                if(deleteSuccesful){
                    bookView.addDisplayAlertMessage("Delete Successful","Book deleted", "Book was successfully deleted to database");
                    bookView.removeBookFromObservableList(bookDTO);

                }else{
                    bookView.addDisplayAlertMessage("Delete error","Problem at deleting Book", "There was a problem at deleting the book to the database. Please try again");

                }
            }else{
                bookView.addDisplayAlertMessage("Delete error","Problem at deleting Book", "You must select a book before presing the Delete button.");

            }
        }
    }

    private class buyButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            BookDTO bookDTO= (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();
            Book book = BookMapper.convertBookDTOToBook(bookDTO);
            String title= book.getTitle();
            String author= book.getAutor();
            int quantity= book.getQuantity();
            float price= book.getPrice();
            /*
            String title= bookView.getTitle();
            String author= bookView.getAuthor();
            int quantity= bookView.getQuantity();
            float price= bookView.getPrice();
            */
            if(bookDTO == null){//mai trebuie sa adaug verificarea pentru campul de cantitate
                bookView.addDisplayAlertMessage("Save error","Problem at Author or Title  fields", "Can not have an empty title or auther  fild");

            }else{/*
                BookDTO bookDTO= new BookDTOBuilder().setTitle(title)
                        .setAuthor(author).setQuantity(quantity)
                        .setPrice(price).build();
                        */
                OrderDTO orderDTO= new OrderDTOBuilder().setTitle(title).setAuthor(author).build();

                boolean saveOrder;
                if(bookService.findByTitleAndAuthor(title,author).getQuantity()-1<0){
                    saveOrder=false;
                    bookView.addDisplayAlertMessage("Sale error","Problem at adding Order", "The book is not available. Out of stock.");
                }
                else{
                    saveOrder= orderService.save(user, bookService.findByTitleAndAuthor(title,author));
                    bookDTO.setQuantity(bookService.findByTitleAndAuthor(title,author).getQuantity()-1);
                    bookView.updateSellBookFromObservableList(bookDTO);
                    bookService.updateQuantity(bookService.findByTitleAndAuthor(title,author));
                }
                if(saveOrder){
                    bookView.addDisplayAlertMessage("Sale Successful","Order added", "Order was successfully added to database");
                    //orderView.addOrderToObeservableList(orderDTO);

                }else{
                    bookView.addDisplayAlertMessage("Sale error","Problem at adding Order", "There was a problem at adding the order to the database. Please try again");

                }
            }
        }
    }

}
