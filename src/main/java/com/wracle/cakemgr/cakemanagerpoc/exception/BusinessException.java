package com.wracle.cakemgr.cakemanagerpoc.exception;

import com.wracle.cakemgr.cakemanagerpoc.exception.beans.ServiceError;
import lombok.Data;

public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }
}
