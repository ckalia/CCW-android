package com.example.sampleapp.pojo;

import com.google.gson.annotations.SerializedName;

public class RequestWrapper {

    @SerializedName("employee")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
