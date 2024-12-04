package launcher;


import controller.OrderController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;

import mapper.OrderMapper;

import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;

import service.order.OrderService;
import service.order.OrderServiceImpl;

import view.OrderView;

import view.model.OrderDTO;

import java.sql.Connection;
import java.util.List;

public class OrderFactory {
    private final OrderView orderView;
    private final OrderController orderController;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private static volatile OrderFactory instance;
    public static OrderFactory getInstance(Boolean componentsForTest, Stage primaryStage){
        if(instance == null){
            {
                synchronized (OrderFactory.class){
                    if(instance == null){
                        instance= new OrderFactory(componentsForTest, primaryStage);
                    }
                }
            }
        }
        return instance;
    }

    private OrderFactory(Boolean componentsFortest, Stage primaryStage){
        Connection connection= DatabaseConnectionFactory.getConnectionWrapper(componentsFortest).getConnection();
        this.orderRepository = new OrderRepositoryMySQL(connection);
        this.orderService = new OrderServiceImpl(orderRepository);
        List<OrderDTO> orders = OrderMapper.converOrderListToOrderDTOList(orderService.findAll());
        this.orderView = new OrderView(primaryStage,orders);
        this.orderController= new OrderController(orderView, orderService);

    }

    public OrderView getBookView() {
        return orderView;
    }

    public OrderController getBookController() {
        return orderController;
    }

    public OrderRepository getBookRepository() {
        return orderRepository;
    }

    public OrderService getBookService() {
        return orderService;
    }
}
