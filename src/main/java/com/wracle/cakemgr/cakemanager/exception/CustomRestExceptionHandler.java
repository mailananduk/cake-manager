package com.wracle.cakemgr.cakemanager.exception;

import com.wracle.cakemgr.cakemanager.exception.beans.ServiceError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessExceptions(BusinessException ex) {
        ServiceError serviceError = new ServiceError(HttpStatus.BAD_REQUEST, "REQ-0001", ex.getLocalizedMessage());
        return new ResponseEntity<>(serviceError, new HttpHeaders(), serviceError.getStatus());
    }
}
