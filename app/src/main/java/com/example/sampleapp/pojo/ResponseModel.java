package com.example.sampleapp.pojo;

import com.google.gson.annotations.SerializedName;

public class ResponseModel extends ServiceResponse{

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
