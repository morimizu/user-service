package com.benjaminrperry.userservice.service;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;
import com.benjaminrperry.userservice.entity.UserJpa;
import com.benjaminrperry.userservice.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static com.benjaminrperry.userservice.converter.UserConverter.toDto;

@Service
@RequiredArgsConstructor
@Validated
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private final Validator validator;

    @Override
    public UserDTO registerNewUser(@Valid CreateUserDTO createUserDTO) {
//        validateNewUser(createUserDTO);
        UserJpa user = new UserJpa();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        return toDto(userRepository.save(user));
    }

//    private void validateNewUser(CreateUserDTO createUserDTO) {
//        var violations = validator.validate(createUserDTO);
//        if (!violations.isEmpty()) {
//            throw new ConstraintViolationException(violations);
//        }
//    }

}
