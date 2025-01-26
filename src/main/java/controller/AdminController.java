package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.Report;
import model.User;
import model.validator.Notification;
import service.book.BookService;
import service.order.OrderService;
import service.user.AuthentificationService;
import service.user.UserService;
import view.AdminView;
import view.model.UserDTO;
import view.model.builder.UserDTOBuilder;

import java.util.List;

public class AdminController {
    private final AdminView adminView;
    private final UserService userService;
    private final AuthentificationService authentificationService;
    private final OrderService orderService;
    private final BookService bookService;

    public AdminController(AdminView adminView, UserService userService,AuthentificationService authentificationService, OrderService orderService,BookService bookService){
        this.adminView = adminView;
        this.userService = userService;
        this.authentificationService=authentificationService;
        this.orderService= orderService;
        this.bookService=bookService;


        this.adminView.addSaveButtonListener(new AdminController.saveButtonListener());
        this.adminView.addReportButtonListener(new AdminController.reportButtonListener());
    }
    private class saveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            String username = adminView.getUsername();
            String password = adminView.getPassword();
            String rol = adminView.getRol();

            if(username.isEmpty() || password.isEmpty() || rol==null ){
                adminView.addDisplayAlertMessage("Save error","Problem at Username or Password fields", "Can not have an empty username or password fild");

            }else{
                UserDTO userDTO= new UserDTOBuilder().setUsername(username)
                        .setRole(rol)
                        .build();
                Notification<Boolean> addUser= authentificationService.register(username,password);

                if(!addUser.hasErrors()){
                    adminView.addDisplayAlertMessage("Save Successful","User added", "User was successfully added to database");
                    adminView.addUserToObeservableList(userDTO);

                }else{
                    adminView.addDisplayAlertMessage("Save error","Problem at adding Book", "There was a problem at adding the book to the database. Please try again");

                }
            }
        }
    }
    private class reportButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            int nr_carti=0;
            float incasari=0;
            UserDTO userDTO = (UserDTO) adminView.getAdminTableView().getSelectionModel().getSelectedItem();
            List<Integer> book_ids = orderService.generateReport(userDTO.getId());
            for(Integer id : book_ids){
                Book book = bookService.findById(Long.valueOf(id));
                nr_carti++;
                incasari= incasari + book.getPrice();
            }
            Report report= new Report(userDTO.getUsername(),nr_carti,incasari);
            //asta trebuie trimis undeva ca sa fie generat un pdf
        }
    }
}
