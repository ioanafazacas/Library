package repository.order;


import model.Book;
import model.Order;
import model.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAll();
    Optional<Order> findById(Long id);
    boolean save(User user, Book book);


}
