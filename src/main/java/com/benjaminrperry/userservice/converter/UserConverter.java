package com.benjaminrperry.userservice.converter;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;
import com.benjaminrperry.userservice.entity.User;
import com.benjaminrperry.userservice.entity.UserJpa;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {
    public static UserJpa createToUser(CreateUserDTO createUserDTO){
        UserJpa user = new UserJpa();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        return user;
    }

    public static UserDTO toDto(User user){
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .creationDate(user.getCreationDate())
                .build();
    }
}
