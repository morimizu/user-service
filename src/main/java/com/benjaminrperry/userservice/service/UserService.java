package com.benjaminrperry.userservice.service;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
    UserDTO registerNewUser(@Valid CreateUserDTO createUserDTO);
}
