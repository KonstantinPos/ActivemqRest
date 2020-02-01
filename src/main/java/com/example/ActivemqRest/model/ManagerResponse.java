package com.example.ActivemqRest.model;

public class ManagerResponse {
    Success success;
    String message;

    public ManagerResponse(Success success, String message) {
        this.success = success;
        this.message = message;
    }

    public ManagerResponse() {
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
