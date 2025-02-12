package mapper;

import model.User;
import model.builder.UserBuilder;
import repository.security.RightsRolesRepository;
import view.model.UserDTO;
import view.model.builder.UserDTOBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static database.Constants.Roles.CUSTOMER;

public class UserMapper {
    public static UserDTO convertUserToUserDTO(User user){
        return new UserDTOBuilder().setId(Math.toIntExact(user.getId()))
                .setUsername(user.getUsername())
                .setRole(user.getRoles())
                .build();
    }
    public static User convertUserDTOToUser(UserDTO userDTO){
        return new UserBuilder().setId(Long.valueOf(userDTO.getId()))
                .setUsername(userDTO.getUsername())
                .build();
    }
    public static List<UserDTO> convertUserListToUserDTOList(List<User> users){
        return users.parallelStream().map(UserMapper::convertUserToUserDTO).collect(Collectors.toList());
    }
    public static List<User> convertUserDTOListToUserList(List<UserDTO> users){
        return users.parallelStream().map(UserMapper::convertUserDTOToUser).collect(Collectors.toList());
    }
}
