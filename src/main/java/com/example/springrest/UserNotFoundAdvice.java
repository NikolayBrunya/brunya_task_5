package com.example.springrest;

import com.example.springrest.exceptions.UserNotFoundException;
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
}

