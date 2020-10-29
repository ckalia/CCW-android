package com.example.sampleapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ResponseInfo {

    @SerializedName("code")
    private int code;
    @SerializedName("type")
    private String type;
    @SerializedName("backendErrorCode")
    private String backendErrorCode;
    @SerializedName("additionalInfo")
    private List<Map<String, String>> additionalInfo;
    @SerializedName("token")
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackendErrorCode() {
        return backendErrorCode;
    }

    public void setBackendErrorCode(String backendErrorCode) {
        this.backendErrorCode = backendErrorCode;
    }

    public List<Map<String, String>> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<Map<String, String>> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
