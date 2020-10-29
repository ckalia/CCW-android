package com.example.sampleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.pojo.RequestModel;
import com.example.sampleapp.pojo.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmountActivity extends AppCompatActivity {

    String[] sessionVariables;
    String atmId = null;
    String username = null;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);

        sessionVariables = getIntent().getStringArrayExtra("sessionVariables");
        if (sessionVariables != null) {
            atmId = sessionVariables[0];
            username = sessionVariables[1];
        } else {
            throw new IllegalArgumentException("atmId or username cannot be null");
        }

        EditText amountView = findViewById(R.id.editAmount);
        Button amountSubmitButton = findViewById(R.id.submitAmount);

        amountSubmitButton.setOnClickListener(view -> {
            if (null != amountView.getText()) {
                callForWithdrawal(amountView.getText().toString());
            }
        });

    }

    private void callForWithdrawal(String amount) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        RequestModel requestModel = new RequestModel();
        requestModel.setAtmId(Integer.parseInt(atmId));
        requestModel.setUsername(username);
        requestModel.setAmount(Integer.parseInt(amount));
        Call<ResponseModel> withdrawCall = apiInterface.withdraw(requestModel);
        withdrawCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getMessage().equalsIgnoreCase("Cash withdrawal initiated")) {
                    Toast.makeText(getApplicationContext(), "Cash withdrawal initiated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                call.cancel();
            }
        });

    }
}
