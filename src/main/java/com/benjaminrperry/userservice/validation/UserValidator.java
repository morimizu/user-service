package com.benjaminrperry.userservice.validation;

import com.benjaminrperry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    public boolean usernameValid(String username){
        return !userRepository.existsByUsername(username);
    }
    public boolean emailValid(String email){
        return !userRepository.existsByEmail(email);
    }

}
