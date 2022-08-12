package com.andikscript.simpleusermongodb.model.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Error {

    private HttpStatus http_status;

    private LocalDateTime timestamp;

    private String message;

    private List details;

    public Error(HttpStatus http_status, LocalDateTime timestamp, String message, List details) {
        this.http_status = http_status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttp_status() {
        return http_status;
    }

    public void setHttp_status(HttpStatus http_status) {
        this.http_status = http_status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getDetails() {
        return details;
    }

    public void setDetails(List details) {
        this.details = details;
    }
}
