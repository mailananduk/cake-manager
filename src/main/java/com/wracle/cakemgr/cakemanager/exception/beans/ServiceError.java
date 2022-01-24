package com.wracle.cakemgr.cakemanager.exception.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ServiceError implements Serializable {
    private static final long serialVersionUID =1L;
    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

}
