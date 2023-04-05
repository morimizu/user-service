package com.benjaminrperry.userservice.service;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;

public interface UserService {
    UserDTO registerNewUser(CreateUserDTO createUserDTO);

    boolean emailAlreadyExists(String email);

    boolean usernameAlreadyExists(String username);
}
