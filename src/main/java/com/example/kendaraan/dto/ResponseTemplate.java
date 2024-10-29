package com.example.kendaraan.dto;

public class ResponseTemplate<T> {
    private int status;
    private String message;
    private T data;

    public ResponseTemplate(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getter dan Setter
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}