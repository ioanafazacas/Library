package mapper;


import builder.OrderBuilder;

import model.Order;

import view.model.OrderDTO;

import view.model.builder.OrderDTOBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO convertOrderToOrderDTO(Order order){
        return new OrderDTOBuilder().setTitle(order.getTitle()).setAuthor(order.getAuthor()).setQuantity(order.getQuantity()).build();
    }
    public static Order convertOrderDTOToOrder(OrderDTO orderDTO){
        return new OrderBuilder().setTitle(orderDTO.getTitle())
                .setAuthor(orderDTO.getAuthor())
                .setSaleDate(LocalDate.now())
                .setQuantity(orderDTO.getQuantity()).build();
    }
    public static List<OrderDTO> converOrderListToOrderDTOList(List<Order> orders){
        return orders.parallelStream().map(OrderMapper::convertOrderToOrderDTO).collect(Collectors.toList());
    }
    public static List<Order> convertOrderDTOListToOrderList(List<OrderDTO> orders){
        return orders.parallelStream().map(OrderMapper::convertOrderDTOToOrder).collect(Collectors.toList());
    }
}
