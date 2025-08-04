package com.Glebson.Tastee.Infra;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ResponseException {
    private HttpStatus httpStatus;
    private int httpCode;
    private String message;

    public ResponseException(HttpStatus httpStatus, int httpCode, String message) {
        this.httpStatus = httpStatus;
        this.httpCode = httpCode;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ResponseException that)) return false;
        return getHttpCode() == that.getHttpCode() && getHttpStatus() == that.getHttpStatus() && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHttpStatus(), getHttpCode(), getMessage());
    }
}
