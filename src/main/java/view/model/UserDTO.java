package view.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Arrays;
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

    private List<StringProperty> role;
    public void setRole(List<String> role){
        for(int i=0; i< role.size(); i++)
        {
            roleProperty().set(i,role.get(i));
        }
    }
    public List<String> getRole(){
        List<String> roles= new ArrayList<>();
        for(int i=0; i<roleProperty().size(); i++)
            roles.add(String.valueOf(roleProperty().get(i)));
        return roles;
    }
    public List<StringProperty> roleProperty(){
        if(role == null){
            role = new List<StringProperty>(this, "roles");
        }
        return role;
    }
}
