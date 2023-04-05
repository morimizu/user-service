package com.benjaminrperry.userservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDTO {
    private String username;
    private String email;
    private Instant creationDate;
}
