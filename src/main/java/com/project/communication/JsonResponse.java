package com.project.communication;

/**
 * Created by vganshin on 12.11.15.
 */
public class JsonResponse {

    public enum Status {
        OK, ERROR
    }

    private Status status;
    private Object message;

    public JsonResponse() {
    }

    public JsonResponse(Status status, Object message) {
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

    @Override
    public String toString() {
        return "jsonResponse{" +
                "status=" + status +
                ", message=" + message +
                '}';
    }
}
