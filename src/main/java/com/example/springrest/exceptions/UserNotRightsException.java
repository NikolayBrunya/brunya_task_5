package com.example.springrest.exceptions;

public class UserNotRightsException extends  Exception{
    public UserNotRightsException() {
        super("User no rights");
    }
}
