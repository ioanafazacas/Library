package controller;

import service.user.AuthentificationService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import launcher.EmployeeComponentFactory;
import launcher.LoginComponentFactory;
import model.User;
import model.validator.Notification;
import model.validator.UserValidator;
import service.user.AuthenticationService;
import view.LoginView;
import java.util.EventListener;
import java.util.List;
public class LoginController {
    private final LoginView loginView;
    private final AuthentificationService authenticationService;
    private final UserValidator userValidator;
/*
    public LoginController(LoginView loginView, AuthentificationService authenticationService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;

        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }*/
    public LoginController(LoginView loginView, AuthentificationService authentificationService, UserValidator userValidator) {
        this.loginView=loginView;
        this.authenticationService=authentificationService;
        this.userValidator=userValidator;

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
                EmployeeComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(), LoginComponentFactory.getStage());
            }
        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}