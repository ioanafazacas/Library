package service.order;

import model.Book;
import model.Order;
import model.User;
import repository.order.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Order with id %d was not found ".formatted(id)));
    }

    @Override
    public boolean save(User user, Book book) {
        return orderRepository.save(user,book);
    }

    //ar trebui implementat o metoda sell sau asta ii deja metoda save modificata

}
