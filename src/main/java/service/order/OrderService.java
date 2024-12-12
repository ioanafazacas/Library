package service.order;


import model.Book;
import model.Order;
import model.User;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    boolean save(User user, Book book);
    List<Integer> generateReport(int id);
}
