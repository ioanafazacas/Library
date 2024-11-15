package view.model.builder;


import view.model.OrderDTO;

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
    public OrderDTOBuilder setQuantity(int quantity){
        orderDTO.setQuantity(quantity);
        return this;
    }
    public OrderDTO build(){
        return orderDTO;
    }
}
