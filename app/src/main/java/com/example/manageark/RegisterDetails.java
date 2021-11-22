package com.example.manageark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class RegisterDetails extends AppCompatActivity {
    Button signup;
    AutoCompleteTextView autoCompleteTextView;
    String[] UniData  =  {"JKLU","ABCD","EFGH"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        signup = findViewById(R.id.RegisterDetails_signup_bt);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterDetails.this, LoginMain.class);
                startActivity(intent);
            }
        });
        autoCompleteTextView = findViewById(R.id.RegisterDetails_University_EditText);
        //Initialize adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,UniData);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }
}