package com.example.sampleapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("employeeId")
    private String employeeId;
    @SerializedName("employeePass")
    private String employeePass;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeePass() {
        return employeePass;
    }

    public void setEmployeePass(String employeePass) {
        this.employeePass = employeePass;
    }
}
