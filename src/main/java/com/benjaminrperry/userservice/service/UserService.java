package com.benjaminrperry.userservice.service;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
@Validated
public interface UserService {
    UserDTO registerNewUser(@Valid CreateUserDTO createUserDTO);
}
