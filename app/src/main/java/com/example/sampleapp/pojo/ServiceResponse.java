package com.example.sampleapp.pojo;

import com.google.gson.annotations.SerializedName;

public class ServiceResponse {

    @SerializedName("errorResponse")
    private String errorResponse;

    public String getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(String errorResponse) {
        this.errorResponse = errorResponse;
    }
}
