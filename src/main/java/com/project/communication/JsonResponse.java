package com.project.communication;

/**
 * Created by vganshin on 12.11.15.
 */
public class JsonResponse<T> {

    public enum Status {
        OK, ERROR
    }

    private Status status;
    private T message;

    public JsonResponse() {
    }

    public JsonResponse(Status status, T message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
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
