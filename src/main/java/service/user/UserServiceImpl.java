package service.user;

import model.Book;
import model.User;
import model.builder.BookBuilder;
import model.builder.UserBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    private final Connection connection;
    public UserServiceImpl(Connection connection){
        this.connection=connection;
    }
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void generateReport(User user) {

    }
}
