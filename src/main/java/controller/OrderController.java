package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import mapper.OrderMapper;

import service.OrderService;

import view.model.OrderDTO;
import view.OrderView;

import view.model.builder.OrderDTOBuilder;

public class OrderController {
    private final OrderView orderView;
    private final OrderService orderService;
    public OrderController(OrderView orderView, OrderService orderService){
        this.orderService = orderService;
        this.orderView = orderView;

        this.orderView.addBuyButtonListener(new OrderController.buyButtonListener());
    }
    private class buyButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            String title= orderView.getTitle();
            String author= orderView.getAuthor();
            Integer quantity= orderView.getQuantity();

            if(title.isEmpty() || author.isEmpty()){//mai trebuie sa adaug verificarea pentru campul de cantitate
                orderView.addDisplayAlertMessage("Save error","Problem at Author, Title or Quantity fields", "Can not have an empty title, auther or quantity fild");

            }else{
                OrderDTO orderDTO= new OrderDTOBuilder().setTitle(title).setAuthor(author).setQuantity(quantity).build();
                boolean saveOrder= orderService.save(OrderMapper.convertOrderDTOToOrder(orderDTO));

                if(saveOrder){
                    orderView.addDisplayAlertMessage("Save Successful","Order added", "Order was successfully added to database");
                    orderView.addOrderToObeservableList(orderDTO);

                }else{
                    orderView.addDisplayAlertMessage("Save error","Problem at adding Order", "There was a problem at adding the order to the database. Please try again");

                }
            }
        }
    }

}
