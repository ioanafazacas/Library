package view;

import database.Constants;

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
import model.Role;
import view.model.BookDTO;
import view.model.UserDTO;

import java.util.List;

public class AdminView {
    private TableView<UserDTO> userTableView;
    private final ObservableList<UserDTO> userObservableList;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label rolLable;
    private ComboBox<Role> roleComboBox;
    private final String[] rolList;
    private Button addButton;
    private Button reportButton;
    public AdminView(Stage primaryStage, List<UserDTO> users){
        primaryStage.setTitle("Administration");
        rolList = Constants.Roles.ROLES;
        roleComboBox = new ComboBox(FXCollections
                .observableArrayList(rolList));

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene= new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);

        userObservableList = FXCollections.observableArrayList(users);
        iniTableView(gridPane);

        initSaveOptions(gridPane);
        primaryStage.show();

    }

    private void initSaveOptions(GridPane gridPane) {
        usernameLabel = new Label("Username");
        gridPane.add(usernameLabel,1,1);

        usernameTextField = new TextField();
        gridPane.add(usernameTextField,2,1);

        passwordLabel = new Label("Password");
        gridPane.add(passwordLabel,3,1);

        passwordTextField = new TextField();
        gridPane.add(passwordTextField,4,1);

        gridPane.add(roleComboBox,5,1);

        addButton = new Button("Save");
        gridPane.add(addButton,1,2);

        reportButton = new Button("Generate report");
        gridPane.add(reportButton,2,2);
    }

    private void iniTableView(GridPane gridPane) {
        userTableView = new TableView<UserDTO>();
        userTableView.setPlaceholder(new Label("No users to display"));

        TableColumn<UserDTO,String> idColumn = new TableColumn<>("Id");
        idColumn.setCellFactory(new PropertyValueFactory<>("id"));
        TableColumn<UserDTO,String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellFactory(new PropertyValueFactory<>("username"));
        TableColumn<UserDTO,String> rolColumn = new TableColumn<>("Rol");
        rolColumn.setCellFactory(new PropertyValueFactory<>("roles"));

        userTableView.getColumns().addAll(idColumn,usernameColumn,rolColumn);

        userTableView.setItems(userObservableList);
        gridPane.add(userTableView,0,0,4,1);
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

    }

    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener){
        addButton.setOnAction(saveButtonListener);
    }
    public void addReportButtonListener(EventHandler<ActionEvent> deleteButtonListener){
        reportButton.setOnAction(deleteButtonListener);
    }

    public void addDisplayAlertMessage(String title, String header, String content){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();//il va afisa si va astepta pana il inchidem noi cu mana noastra
    }

    public String getUsername(){
        return usernameTextField.getText();
    }
    public String getPassword(){
        return passwordTextField.getText();
    }
    public String getRol(){
        Role selectedRole = roleComboBox.getValue();
        return selectedRole != null ? selectedRole.toString() : null;
    }
    public TableView getAdminTableView(){
        return userTableView;
    }
    public void addUserToObeservableList(UserDTO userDTO){
        this.userObservableList.add(userDTO);
    }
}
