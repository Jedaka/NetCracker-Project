package com.project.some;

/**
 * Created by vganshin on 12.11.15.
 */
public class jsonResponse {
    public enum Status {OK, ERROR};
    Status status;
    Object message;

    public jsonResponse() {
    }

    public jsonResponse(Status status, Object message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
