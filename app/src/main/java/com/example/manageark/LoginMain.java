package com.example.manageark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMain extends AppCompatActivity {
    TextView Email,Password,Signup;
    TextInputLayout EmailLayout, PasswordLayout;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_main);

        // hooks
        Email = (TextView)findViewById(R.id.user_email_EditText);
        EmailLayout = (TextInputLayout) findViewById(R.id.user_email_TextInputLayout);

        Password = (TextView)findViewById(R.id.user_password_EditText);
        PasswordLayout = (TextInputLayout) findViewById(R.id.user_password_TextInputLayout);

        Signup = (TextView)findViewById(R.id.Signup_Login);

        Login = findViewById(R.id.user_login_bt);

        // sign up button click
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToRegister();
            }
        });

        // added text changed to remove set error of layout
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EmailLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EmailLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                EmailLayout.setError(null);
            }
        });

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                PasswordLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                PasswordLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                PasswordLayout.setError(null);
            }
        });

        // login button click
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                if(email.isEmpty()){
                    EmailLayout.requestFocus();
                    EmailLayout.setError("Please enter your Email");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    EmailLayout.requestFocus();
                    EmailLayout.setError("Please enter correct email");
                    return;
                }
                if(password.isEmpty()){
                    PasswordLayout.requestFocus();
                    PasswordLayout.setError("Please enter your password");
                    return;
                }
                Login(email,password);



            }
        });

    }

    private void SwitchToRegister() {
        Intent intent = new Intent(LoginMain.this,register.class);
        startActivity(intent);
    }

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private void Login(String email, String password) {


        System.out.println(email + "\t " + password);
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        if (authResult.getUser() != null) {
                            Intent intent = new Intent(LoginMain.this,Home_Activity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
