package mapper;


import model.builder.OrderBuilder;

import model.Order;

import view.model.OrderDTO;

import view.model.builder.OrderDTOBuilder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO convertOrderToOrderDTO(Order order){
        return new OrderDTOBuilder().setTitle(order.getTitle())
                .setAuthor(order.getAuthor())
                .setSaleDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
    public static Order convertOrderDTOToOrder(OrderDTO orderDTO){
        return new OrderBuilder().setTitle(orderDTO.getTitle())
                .setAuthor(orderDTO.getAuthor())
                .setSaleDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
    public static List<OrderDTO> converOrderListToOrderDTOList(List<Order> orders){
        return orders.parallelStream().map(OrderMapper::convertOrderToOrderDTO).collect(Collectors.toList());
    }
    public static List<Order> convertOrderDTOListToOrderList(List<OrderDTO> orders){
        return orders.parallelStream().map(OrderMapper::convertOrderDTOToOrder).collect(Collectors.toList());
    }
}
