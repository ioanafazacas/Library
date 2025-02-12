package controller;

import database.Constants;
import launcher.AdminComponentFactory;
import model.Role;
import service.user.AuthentificationService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import launcher.EmployeeComponentFactory;
import launcher.LoginComponentFactory;
import model.User;
import model.validator.Notification;
import model.validator.UserValidator;
import view.LoginView;
import java.util.EventListener;
import java.util.List;

import static database.Constants.Roles.CUSTOMER;

public class LoginController {
    private final LoginView loginView;
    private final AuthentificationService authenticationService;

    public LoginController(LoginView loginView, AuthentificationService authentificationService) {
        this.loginView=loginView;
        this.authenticationService=authentificationService;

        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()){
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            }else{
                loginView.setActionTargetText("LogIn Successfull!");
                User user = loginNotification.getResult();
                //filter(rol -> rol.getRole().equals(Constants.Roles.ADMINISTRATOR)).findFirst());
                //contains(Constants.Roles.ADMINISTRATOR))//probabil e problema ca compar un string cu un obiect Role
                if(user.getRoles().stream().anyMatch(rol -> rol.getRole().equals(Constants.Roles.ADMINISTRATOR)))
                {
                    AdminComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(),LoginComponentFactory.getStage());
                }else{
                    EmployeeComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(), LoginComponentFactory.getStage(), user);
                }
            }
        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password, CUSTOMER);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}