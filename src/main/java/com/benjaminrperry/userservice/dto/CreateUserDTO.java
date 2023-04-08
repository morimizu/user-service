package com.benjaminrperry.userservice.dto;

import com.benjaminrperry.userservice.validation.ValidEmail;
import com.benjaminrperry.userservice.validation.ValidUsername;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDTO {
    @ValidUsername
    private String username;
    @ValidEmail
    private String email;
    private String password;
}
