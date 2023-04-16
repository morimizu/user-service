package com.benjaminrperry.userservice.dto;

import com.benjaminrperry.userservice.validation.ValidEmail;
import com.benjaminrperry.userservice.validation.ValidUsername;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @ValidUsername
    private String username;
    @ValidEmail
    private String email;
    private String password;
}
