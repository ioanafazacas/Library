package view.model.builder;


import view.model.OrderDTO;

import java.sql.Timestamp;

public class OrderDTOBuilder {
    private OrderDTO orderDTO;
    public OrderDTOBuilder(){
        orderDTO= new OrderDTO();
    }
    public OrderDTOBuilder setAuthor(String author){
        orderDTO.setAuthor(author);
        return this;
    }
    public OrderDTOBuilder setTitle(String title){
        orderDTO.setTitle(title);
        return this;
    }
    public OrderDTOBuilder setSaleDate(Timestamp saleDate){
        orderDTO.setSaleDate(saleDate);
        return this;
    }
    public OrderDTO build(){
        return orderDTO;
    }
}
