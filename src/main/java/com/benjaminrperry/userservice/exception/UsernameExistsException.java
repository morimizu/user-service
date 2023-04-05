package com.benjaminrperry.userservice.exception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(){
        super("username already exists");
    }
}
