package com.example.sampleapp;


import com.example.sampleapp.pojo.RequestModel;
import com.example.sampleapp.pojo.RequestWrapper;
import com.example.sampleapp.pojo.ResponseModel;
import com.example.sampleapp.pojo.ResponseWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface APIInterface {

    @POST("/dcc-in-hsbc-n-minus-two-login-cert-proxy/v1/employees/login")
    Call<ResponseWrapper> login(@Body RequestWrapper requestWrapper);

    @POST("/mmf-contactless-cash-withdrawal-cert-proxy/v1/atm/connection")
    Call<ResponseModel> connect(@Body RequestModel requestModel);

    @POST("/mmf-contactless-cash-withdrawal-cert-proxy/v1/atm/code-verification")
    Call<ResponseModel> verifyCode(@Body RequestModel requestModel);

    @POST("/mmf-contactless-cash-withdrawal-cert-proxy/v1/atm/withdrawal")
    Call<ResponseModel> withdraw(@Body RequestModel requestModel);
}
