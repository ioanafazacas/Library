package launcher;

import controller.LoginController;
import database.DatabaseConnectionFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import mapper.UserMapper;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthentificationService;
import service.user.AuthentificationServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.AdminView;
import view.LoginView;
import view.model.UserDTO;

import java.sql.Connection;
import java.util.List;

public class Main extends Application {
    public static void main(String [] args){
        Launcher.main(args);
    }
    /*Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
    BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connection), new Cache<>());
    //Connection connection= DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

    RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
    UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
    AuthentificationService authentificationService= new AuthentificationServiceImpl(userRepository, rightsRolesRepository);
*/


    @Override
    public void start(Stage stage) throws Exception {
        /*Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        AuthentificationService authenticationService = new AuthentificationServiceImpl(userRepository, rightsRolesRepository);
        //LoginView loginView = new LoginView(stage);
        //LoginController loginController = new LoginController(loginView, authenticationService);
        //BookRepository bookRepository = new BookRepositoryMySQL(connection);
        UserService userService= new UserServiceImpl(connection);
        List<UserDTO> users= UserMapper.convertUserListToUserDTOList(userService.findAll());
        AdminView adminView = new AdminView(stage,users);*/
        AdminComponentFactory.getInstance(false,stage);
    }
}
