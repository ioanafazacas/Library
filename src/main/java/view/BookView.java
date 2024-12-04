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
import view.model.BookDTO;

import java.util.List;

public class BookView {
    private TableView<BookDTO> bookTableView;
    private final ObservableList<BookDTO> booksObservableList;
    private TextField authorTextField;
    private TextField titleTextField;
    private TextField quantityTextField;
    private TextField priceTextField;
    private Label authorLable;
    private Label titleLabel;
    private Label quantityLable;
    private Label priceLabel;
    private Button saveButton;
    private Button deleteButton;
    private Button buyButton;
    public BookView(Stage primaryStage, List<BookDTO> books){
        primaryStage.setTitle("Library");

        GridPane gridPane= new GridPane();
        initializeGridPane(gridPane);

        Scene scene= new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);

        booksObservableList = FXCollections.observableArrayList(books);
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
        bookTableView = new TableView<BookDTO>();
        bookTableView.setPlaceholder(new Label("Nobooks to display"));
        TableColumn<BookDTO, String> titleColumn= new TableColumn<BookDTO,String>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<BookDTO, String> authorColumn = new TableColumn<BookDTO, String>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<BookDTO, String> priceColumn = new TableColumn<BookDTO, String>("Price");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<BookDTO, String> quantityColumn = new TableColumn<BookDTO, String>("Quantity");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        bookTableView.getColumns().addAll(titleColumn,authorColumn,priceColumn,quantityColumn);

        bookTableView.setItems(booksObservableList);
        gridPane.add(bookTableView,0,0,6,1);
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

        quantityLable = new Label("Quantity");
        gridPane.add(quantityLable,1,2);

        quantityTextField = new TextField();
        gridPane.add(quantityTextField,2,2);

        priceLabel = new Label("Price");
        gridPane.add(priceLabel,3,2);

        priceTextField = new TextField();
        gridPane.add(priceTextField,4,2);

        saveButton = new Button("Save");
        gridPane.add(saveButton,5,1);

        deleteButton= new Button("Delete");
        gridPane.add(deleteButton,5,2);
        buyButton = new Button("Buy");
        gridPane.add(buyButton,5,3);
    }
    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener){
        saveButton.setOnAction(saveButtonListener);
    }
    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener){
        deleteButton.setOnAction(deleteButtonListener);
    }
    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener){
        buyButton.setOnAction(buyButtonListener);
    }
    //mesaje pop-up
    public void addDisplayAlertMessage(String title, String header, String content){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();//il va afisa si va astepta pana il inchidem noi cu mana noastra
    }
    public String getTitle(){
        return titleTextField.getText();
    }
    public String getAuthor(){
        return authorTextField.getText();
    }
    public int getQuantity(){return Integer.parseInt(quantityTextField.getText());}
    public float getPrice(){return Float.parseFloat(priceTextField.getText());}
    public void addBookToObeservableList(BookDTO bookDTO){
        this.booksObservableList.add(bookDTO); //adaugam element la ObservableList-ul curent si
                                                // nu ii schimbam referinta

    }
    public void removeBookFromObservableList(BookDTO bookDTO){
        this.booksObservableList.remove(bookDTO);
    }
    public TableView getBookTableView(){
        return bookTableView;
    }
}
