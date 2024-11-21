package service;

import model.Order;
import repository.OrderRepository;

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
    public boolean save(Order order) {
        return orderRepository.save(order);
    }

    //ar trebui implementat o metoda sell sau asta ii deja metoda save modificata

}
