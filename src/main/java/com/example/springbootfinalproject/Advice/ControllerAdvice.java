package com.example.springbootfinalproject.Advice;
import com.example.springbootfinalproject.Exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    // اي كراش عندي يجي هنا يعني ال ٤٠٠ تجي هنا
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException apiException) {
        String message = apiException.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException m) {
        String message = m.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
}