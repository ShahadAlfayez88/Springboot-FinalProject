package com.example.springbootfinalproject.Exception;

public class ApiException extends RuntimeException {
    public ApiException(String msg){
        super(msg);
    }
}