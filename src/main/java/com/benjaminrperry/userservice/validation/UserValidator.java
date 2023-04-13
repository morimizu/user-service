package com.benjaminrperry.userservice.validation;

import com.benjaminrperry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserValidator {

    private final UserRepository userRepository;
    public boolean usernameValid(String username){
        boolean valid = userRepository.findByUsername(username) == null;
        log.debug("username "+username + " valid:"+ valid);
        return valid;
    }
    public boolean emailValid(String email){
        boolean valid = userRepository.findByEmail(email) == null ;
        log.debug("email "+email + " valid:"+ valid);
        return valid;
    }

}
