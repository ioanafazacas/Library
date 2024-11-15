package repository;


import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryMock implements OrderRepository{
    private final List<Order> orders;

    public OrderRepositoryMock()
    {
        orders = new ArrayList<>();
    }
    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean save(Order order) {
        return orders.add(order);
    }

}
