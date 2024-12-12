package service.user;

import model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void generateReport(User user);

}
