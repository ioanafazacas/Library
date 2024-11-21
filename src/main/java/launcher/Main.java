package launcher;

import database.DatabaseConnectionFactory;
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

import java.sql.Connection;

public class Main {
    public static void main(String [] args){
        Launcher.main(args);
    }
    Connection connection = DatabaseConnectionFactory.getConnectionWrapper(false).getConnection();
    BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connection), new Cache<>());
    //Connection connection= DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

    RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
    UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
    AuthentificationService authentificationService= new AuthentificationServiceImpl(userRepository, rightsRolesRepository);

    authentificationService.
}
