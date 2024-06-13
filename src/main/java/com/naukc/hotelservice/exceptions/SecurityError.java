package com.naukc.hotelservice.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class SecurityError {

    private int status;
    private String message;
    private Date timestamp;

    public SecurityError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
