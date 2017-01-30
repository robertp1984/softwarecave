package com.example.periodictableweb;

import com.example.periodictable.ws.admin.ElementAlreadyExists_Exception;
import com.example.periodictable.ws.admin.ElementNotFound_Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
 
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @ExceptionHandler(value = { ElementNotFound_Exception.class, com.example.periodictable.ws.access.ElementNotFound_Exception.class })
    public ResponseEntity<?> handleNotFound(Exception ex, WebRequest request) {
        logger.error("Error: Element not found, reason: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 - Not Found");
    }
    
    @ExceptionHandler(value = { ElementAlreadyExists_Exception.class })
    public ResponseEntity<?> handleAlreadyExists(Exception ex, WebRequest request) {
        logger.error("Error: Element already exists, reason: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict");
    }
    
}
