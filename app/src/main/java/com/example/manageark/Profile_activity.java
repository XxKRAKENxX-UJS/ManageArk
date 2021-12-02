package com.example.manageark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class Profile_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);

        //setting the title
        toolbar.setTitle("Profile");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        // Back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}