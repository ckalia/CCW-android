package com.example.sampleapp.pojo;

import com.google.gson.annotations.SerializedName;

public class ResponseWrapper {

    @SerializedName("responseInfo")
    private ResponseInfo responseInfo;
    @SerializedName("response")
    private String response;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
