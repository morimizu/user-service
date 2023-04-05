package com.benjaminrperry.userservice.service;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;

public interface UserService {
    UserDTO createUser(CreateUserDTO createUserDTO);
}
