package com.flexpag.paymentscheduler.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.zip.DataFormatException;

@Slf4j
@ControllerAdvice
public class SchedulingExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DataFormatException.class)
    public ResponseEntity dateFormatException(DataFormatException e){
        log.info("Error in: " + e.getCause() + ". Because: " + e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundEntityException(EntityNotFoundException e){
        log.info("Error in: " + e.getCause() + ". Because: " + e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e){
        log.info("Error in: " + e.getCause() + ". Because: " + e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
