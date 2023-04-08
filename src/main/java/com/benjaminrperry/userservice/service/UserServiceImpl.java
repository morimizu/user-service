package com.benjaminrperry.userservice.service;

import com.benjaminrperry.userservice.dto.CreateUserDTO;
import com.benjaminrperry.userservice.dto.UserDTO;
import com.benjaminrperry.userservice.entity.UserJpa;
import com.benjaminrperry.userservice.exception.EmailExistsException;
import com.benjaminrperry.userservice.exception.UsernameExistsException;
import com.benjaminrperry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import static com.benjaminrperry.userservice.converter.UserConverter.toDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO registerNewUser(@Valid CreateUserDTO createUserDTO) {
        UserJpa user = new UserJpa();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        return toDto(userRepository.save(user));
    }

    private void validateNewUser(CreateUserDTO createUserDTO) {
        if (emailAlreadyExists(createUserDTO.getEmail())) {
            throw new EmailExistsException();
        }
        if (usernameAlreadyExists(createUserDTO.getUsername())) {
            throw new UsernameExistsException();
        }
    }

    @Override
    public boolean emailAlreadyExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean usernameAlreadyExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
