package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.model.OrderDTO;

import java.sql.Timestamp;
import java.util.List;

public class OrderView {
    private TableView<OrderDTO> orderTableView;
    private final ObservableList<OrderDTO> ordersObservableList;
    private TextField authorTextField;
    private TextField titleTextField;
    private Label authorLable;
    private Label titleLabel;
    private Button buyButton;
    public OrderView(Stage primaryStage, List<OrderDTO> orders){
        primaryStage.setTitle("Order");

        GridPane gridPane= new GridPane();
        initializeGridPane(gridPane);

        Scene scene= new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);

        ordersObservableList = FXCollections.observableArrayList(orders);
        initTableView(gridPane);

        initSaveOprions(gridPane);
        primaryStage.show();
    }
    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

    }
    private void initTableView(GridPane gridPane){
        orderTableView = new TableView<OrderDTO>();
        orderTableView.setPlaceholder(new Label("No orders to display"));
        TableColumn<OrderDTO, String> titleColumn= new TableColumn<OrderDTO,String>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<OrderDTO, String> authorColumn = new TableColumn<OrderDTO, String>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<OrderDTO, Timestamp> dateColumn = new TableColumn<OrderDTO, Timestamp>("SaleDate");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        TableColumn<OrderDTO, String> priceColumn = new TableColumn<OrderDTO, String>("Price");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderTableView.getColumns().addAll(titleColumn,authorColumn,dateColumn,priceColumn);

        orderTableView.setItems(ordersObservableList);
        gridPane.add(orderTableView,0,0,7,1);
    }
    private void initSaveOprions(GridPane gridPane){
        titleLabel= new Label("Title");
        gridPane.add(titleLabel,1,1);

        titleTextField= new TextField();
        gridPane.add(titleTextField,2,1);

        authorLable = new Label("Author");
        gridPane.add(authorLable,3,1);

        authorTextField = new TextField();
        gridPane.add(authorTextField,4,1);

        buyButton = new Button("Save");
        gridPane.add(buyButton,5,1);

    }
    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener){
        buyButton.setOnAction(buyButtonListener);
    }

    public void addDisplayAlertMessage(String title, String header, String content){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
    public String getTitle(){
        return titleTextField.getText();
    }
    public String getAuthor(){
        return authorTextField.getText();
    }
    public void addOrderToObeservableList(OrderDTO orderDTO){
        this.ordersObservableList.add(orderDTO);
    }
    public TableView getOrderTableView(){
        return orderTableView;
    }
    
}
