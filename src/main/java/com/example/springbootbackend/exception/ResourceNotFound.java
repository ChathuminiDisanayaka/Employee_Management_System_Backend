package com.example.springbootbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    public static final long serialVersionID = 1L;

    public ResourceNotFound(String message){
        super(message);
    }

}
