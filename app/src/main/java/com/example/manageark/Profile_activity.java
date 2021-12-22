package com.example.manageark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Profile_activity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    EditText email,university,mess,roll_no,name;
    TextInputLayout email_l,university_l,mess_l,rollno_l;

    boolean editing = false;
    Button editprofile;
    Button changepassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.user_name);

        email = findViewById(R.id.user_email_EditText);
        email_l = findViewById(R.id.user_email_TextInputLayout);


        mess = findViewById(R.id.user_mess_id_EditText);
        mess_l = findViewById(R.id.user_mess_id_TextInputLayout);


        university = findViewById(R.id.user_university_EditText);
        university_l = findViewById(R.id.user_university_TextInputLayout);


        roll_no = findViewById(R.id.user_roll_no_EditText);
        rollno_l = findViewById(R.id.user_roll_no_TextInputLayout);

        editprofile = findViewById(R.id.edit_profile);
        changepassword = findViewById(R.id.change_password);


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

        mess.setText(sharedPrefManager.getUser().getUniqueId());
        email.setText(sharedPrefManager.getUser().getFullName());
        roll_no.setText(sharedPrefManager.getUser().getMessID());
        name.setText(sharedPrefManager.getUser().getEmail());
        university.setText(sharedPrefManager.getUser().getUniversity());



        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile_activity.this,change_password.class);
                startActivity(i);
            }
        });


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editprofile.getText().toString().equals("SAVE")){
                    editing = false;
                }
                else {
                    editing = true;
                    editprofile.setText("Save");
                    String s_email = email.getText().toString();
                    String s_university = university.getText().toString();
                    String s_roll_no = roll_no.getText().toString();
                    String s_mess_id = mess.getText().toString();


                    if (editing) {
                        email.setClickable(true);
                        email.setFocusable(true);
                        university.setFocusable(true);
                        roll_no.setFocusable(true);
                        mess.setFocusable(true);
                        university.setClickable(true);
                        roll_no.setClickable(true);
                        mess.setClickable(true);
                    }
                }


            }
        });

    }
}