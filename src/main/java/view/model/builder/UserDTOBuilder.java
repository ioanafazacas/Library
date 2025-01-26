package view.model.builder;

import view.model.UserDTO;

import java.util.Collections;

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
    public UserDTOBuilder setRole(String role){
        userDTO.setRole(Collections.singletonList(role));
        return this;
    }
    public UserDTO build(){
        return userDTO;
    }
}
