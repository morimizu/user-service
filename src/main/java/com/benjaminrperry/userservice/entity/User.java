package com.benjaminrperry.userservice.entity;

import java.time.Instant;

public interface User {
    Long getId();
    String getUsername();
    void setEmail(String email);
    String getEmail();
    String getPassword();
    void setPassword(String password);
    Instant getCreationDate();
}
