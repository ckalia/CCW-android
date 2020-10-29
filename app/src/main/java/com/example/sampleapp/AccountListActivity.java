package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountListActivity extends AppCompatActivity {

    String[] sessionVariables;
    String atmId = null;
    String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        sessionVariables = getIntent().getStringArrayExtra("sessionVariables");
        if (sessionVariables != null) {
            atmId = sessionVariables[0];
            username = sessionVariables[1];
        } else {
            throw new IllegalArgumentException("atmId or username cannot be null");
        }

        final ListView list = findViewById(R.id.list);
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Account 1");
        arrayList.add("Account 2");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener((parent, view, position, id) -> {
            String clickedItem=(String) list.getItemAtPosition(position);
            //Toast.makeText(AccountListActivity.this,clickedItem,Toast.LENGTH_LONG).show();
            startActivity(new Intent(AccountListActivity.this, AmountActivity.class)
                    .putExtra("sessionVariables", sessionVariables));
        });
    }

}
