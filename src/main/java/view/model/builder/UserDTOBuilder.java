package view.model.builder;

import model.Role;
import view.model.UserDTO;

import java.util.Collections;
import java.util.List;

public class UserDTOBuilder {
    private UserDTO userDTO;
    public UserDTOBuilder(){
        userDTO = new UserDTO();
    }
    public UserDTOBuilder setId(int id){
        userDTO.setId(id);
        return this;
    }
    public UserDTOBuilder setUsername(String username){
        userDTO.setUsername(username);
        return this;
    }
    public UserDTOBuilder setRole(List<Role> role){
        userDTO.setRole(role);
        return this;
    }
    public UserDTO build(){
        return userDTO;
    }
}
