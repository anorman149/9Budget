package com.ninebudget.model;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception{
    private HttpStatus status;
    private String severity;
    private String description;

    public ServiceException() {
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(HttpStatus status, String severity, String description) {
        this.status = status;
        this.severity = severity;
        this.description = description;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
