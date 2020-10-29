package com.example.sampleapp.pojo;

import com.google.gson.annotations.SerializedName;

public class RequestModel {

    @SerializedName("atmId")
    private int atmId;
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("verificationCode")
    private String verificationCode;
    @SerializedName("amount")
    private int amount;

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
