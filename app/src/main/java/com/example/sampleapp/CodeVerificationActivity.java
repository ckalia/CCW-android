package com.example.sampleapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.pojo.RequestModel;
import com.example.sampleapp.pojo.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeVerificationActivity extends AppCompatActivity {

    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four;
    Button verify_otp;
    EditText[] edit;
    String[] sessionVariables;
    String atmId = null;
    String username = null;
    APIInterface apiInterface;
    ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_code_verification);

        initViews();

        sessionVariables = getIntent().getStringArrayExtra("sessionVariables");
        if (sessionVariables != null) {
            atmId = sessionVariables[0];
            username = sessionVariables[1];
        } else {
            throw new IllegalArgumentException("atmId or username cannot be null");
        }

        otp_textbox_one.addTextChangedListener(new GenericTextWatcher(otp_textbox_one, edit));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher(otp_textbox_two, edit));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher(otp_textbox_three, edit));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher(otp_textbox_four, edit));


        verify_otp.setOnClickListener(view -> {
            progressDialog = ProgressDialog.show(CodeVerificationActivity.this, "Loading", "Please wait");
            // Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
            String otp = otp_textbox_one.getText().toString() +
                    otp_textbox_two.getText().toString() +
                    otp_textbox_three.getText().toString() +
                    otp_textbox_four.getText().toString();

            callForCodeVerification(otp);
        });
    }

    private void callForCodeVerification(String otp) {
        Toast.makeText(getApplicationContext(), "OPT entered" + otp, Toast.LENGTH_LONG).show();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        RequestModel requestModel = new RequestModel();
        requestModel.setAtmId(Integer.parseInt(atmId));
        requestModel.setUsername(username);
        requestModel.setVerificationCode(otp);
        Call<ResponseModel> verifyCodeCall = apiInterface.verifyCode(requestModel);
        verifyCodeCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getMessage().contains("verified successfully")) {
                    progressDialog.dismiss();
                    startActivity(new Intent(CodeVerificationActivity.this, AccountListActivity.class)
                    .putExtra("sessionVariables", sessionVariables));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
        verify_otp = findViewById(R.id.verify_otp_btn);

        edit = new EditText[]{otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four};
    }
}
