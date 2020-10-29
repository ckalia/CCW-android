package com.example.sampleapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.pojo.RequestModel;
import com.example.sampleapp.pojo.ResponseModel;

import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionCallActivity extends AppCompatActivity {

    String atmId = null;
    APIInterface apiInterface;
    ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);
        progressDialog = ProgressDialog.show(ConnectionCallActivity.this, "Loading", "Please wait");

        try {
            atmId = Optional.ofNullable(getIntent().getStringExtra("atmId"))
                    .orElseThrow(() -> new NullPointerException("atm id cannot be null here"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        apiInterface = APIClient.getClient().create(APIInterface.class);
        RequestModel requestModel = new RequestModel();
        requestModel.setAtmId(Integer.parseInt(atmId));
        requestModel.setUsername("User");
        Call<ResponseModel> connectionCall = apiInterface.connect(requestModel);
        /*login.enqueue(new Callback<ResponseWrapper>() {
            @Override
            public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                ResponseWrapper responseWrapper = response.body();
                Optional.ofNullable(responseWrapper.getResponseInfo())
                        .map(ResponseInfo::getCode)
                        .filter(code -> code == 200)
                        .ifPresent(code -> {
                            startActivity(new Intent(ConnectionCallActivity.this, CodeVerificationActivity.class).putExtra("atmId", atmId));
                        });
            }

            @Override
            public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                call.cancel();
            }
        });*/
        connectionCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (Optional.ofNullable(response.body().getMessage()).isPresent() &&
                        Optional.ofNullable(response.body().getMessage()).get().equalsIgnoreCase("Connection established successfully!")) {
                    progressDialog.dismiss();
                    startActivity(new Intent(ConnectionCallActivity.this, CodeVerificationActivity.class)
                            .putExtra("sessionVariables", new String[]{atmId, "User"}));
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "ATM unavailable, please try again later", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ConnectionCallActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                call.cancel();
            }
        });
    }
}
