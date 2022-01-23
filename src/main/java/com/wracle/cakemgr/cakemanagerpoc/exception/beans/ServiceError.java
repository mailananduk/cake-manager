package com.wracle.cakemgr.cakemanagerpoc.exception.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ServiceError implements Serializable {
    private static final long serialVersionUID =1L;
    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

}
