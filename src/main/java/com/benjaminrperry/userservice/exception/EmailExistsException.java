package com.benjaminrperry.userservice.exception;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(){
        super("Email address already exists");
    }
}
