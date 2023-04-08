package com.benjaminrperry.userservice.service;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;

import javax.validation.Valid;

public interface UserService {
    UserDTO registerNewUser(@Valid CreateUserDTO createUserDTO);

    boolean emailAlreadyExists(String email);

    boolean usernameAlreadyExists(String username);
}
