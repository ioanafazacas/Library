package view.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserDTO {
    private StringProperty username;
    public void setUsername(String username){
        usernameProperty().set(username);
    }
    public String getUsername(){
        return usernameProperty().get();
    }
    public StringProperty usernameProperty(){
        if(username == null){
            username = new SimpleStringProperty(this, "username");
        }
        return username;
    }

    private IntegerProperty id;
    public void setId(int id){
        idProperty().set(id);
    }
    public int getId(){
        return idProperty().get();
    }
    public IntegerProperty idProperty(){
        if(id == null){
            id = new SimpleIntegerProperty(this, "id");
        }
        return id;
    }

    private StringProperty roles;
    public void setRole(List<Role> roles){
        StringBuilder sb= new StringBuilder();
        for(Role role: roles){
            sb.append(role.getRole()).append(" , ");
        }
        rolesProperty().set(sb.toString());
    }
    public String getRole(){
        return rolesProperty().get();
    }
    public StringProperty rolesProperty(){
        if(roles == null){
            roles = new SimpleStringProperty(this, "role");
        }
        return roles;
    }
}
