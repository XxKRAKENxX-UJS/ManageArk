package com.example.manageark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView Name = findViewById(R.id.signup_Username_EditText);
        TextInputLayout NameLayout =findViewById(R.id.signup_username_TextInputLayout);

        TextView Email = findViewById(R.id.signup_email_EditText);
        TextInputLayout EmailLayout =findViewById(R.id.signup_email_TextInputLayout);

        TextView Password = findViewById(R.id.signup_password_EditText);
        TextInputLayout PasswordLayout =findViewById(R.id.signup_password_TextInputLayout);

        Button signup = findViewById(R.id.user_signup_bt);
        TextView login = findViewById(R.id.txt_login);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerForAuth(Name,Email,Password);
            }
        });
    }

    private void PerForAuth(TextView name, TextView email, TextView password) {
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }


}