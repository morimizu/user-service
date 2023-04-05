package com.benjaminrperry.userservice.controller;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;
import com.benjaminrperry.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO registerNewUser(@RequestBody CreateUserDTO createUserDTO){
        return userService.registerNewUser(createUserDTO);
    }

}
