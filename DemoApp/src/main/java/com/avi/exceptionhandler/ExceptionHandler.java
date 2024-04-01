package com.avi.exceptionhandler;

import com.avi.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorCode = String.valueOf(new Date().getTime());
        log.error("====>>>> " + ex.getMessage() + " ===>>> " + errorCode + "==>>" + ex.getStackTrace()[0]);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            errors.put("errorCode", errorCode);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        String errorCode = String.valueOf(new Date().getTime());
        log.error("====>>>> " + ex.getMessage() + " ===>>> " + errorCode + "==>>" + ex.getStackTrace()[0]);
        Map<String, String> errors = new HashMap<>();
        errors.put("ErrorMessage", ex.getMessage());
        errors.put("errorCode", errorCode);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        String errorCode = String.valueOf(new Date().getTime());
        log.error("====>>>> " + ex.getMessage() + " ===>>> " + errorCode + "==>>" + ex.getStackTrace()[0]);
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("status", "");
        errors.put("data", "");
        errors.put("errorCode", errorCode);
        errors.put("error", "Exception");
        return new ResponseEntity<Object>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(TokenException.class)
    public ResponseEntity<Map<String, String>> handleTokenException(TokenException ex) {
        String errorCode = String.valueOf(new Date().getTime());
        log.error("====>>>> " + ex.getMessage() + " ===>>> " + errorCode + "==>>" + ex.getStackTrace()[0]);
        Map<String, String> errors = new HashMap<>();
        errors.put("ErrorMessage", ex.getMessage());
        errors.put("errorCode", errorCode);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(FileNotSupportedException.class)
    public ResponseEntity<Object> handleFileNotSupportedException(FileNotSupportedException ex) {
        String errorCode = String.valueOf(new Date().getTime());
        log.error("====>>>> " + ex.getMessage() + " ===>>> " + errorCode + "==>>" + ex.getStackTrace()[0]);
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("status", "");
        errors.put("data", "");
        errors.put("errorCode", errorCode);
        errors.put("error", "Exception");
        return new ResponseEntity<Object>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(EmailException.class)
    public ResponseEntity<Object> handleEmailException(EmailException ex) {
        String errorCode = String.valueOf(new Date().getTime());
        log.error("====>>>> " + ex.getMessage() + " ===>>> " + errorCode + "==>>" + ex.getStackTrace()[0]);
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("status", "");
        errors.put("data", "");
        errors.put("errorCode", errorCode);
        errors.put("error", "Exception");
        return new ResponseEntity<Object>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProjectNotFoundException(ProjectNotFoundException ex) {
        String errorCode = String.valueOf(new Date().getTime());
        log.error("====>>>> " + ex.getMessage() + " ===>>> " + errorCode + "==>>" + ex.getStackTrace()[0]);
        Map<String, String> errors = new HashMap<>();
        errors.put("ErrorMessage", ex.getMessage());
        errors.put("errorCode", errorCode);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
