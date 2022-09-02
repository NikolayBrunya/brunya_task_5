package com.example.springrest;

import com.example.springrest.exceptions.UserNotFoundException;
import com.example.springrest.exceptions.UserNotRightsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//1
@ControllerAdvice
@ResponseBody
public class UserNotFoundAdvice {

    //2
    @ExceptionHandler
    String employeeNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler
    String employeeNotRightsHandler(UserNotRightsException ex) { return ex.getMessage(); }
}

