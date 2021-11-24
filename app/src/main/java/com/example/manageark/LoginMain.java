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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginMain extends AppCompatActivity {
    TextView Email,Password,Signup,forgetpass;
    TextInputLayout EmailLayout, PasswordLayout;
    Button Login;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
         Email = (TextView)findViewById(R.id.user_email_EditText);
         EmailLayout = (TextInputLayout) findViewById(R.id.user_email_TextInputLayout);

        Password = (TextView)findViewById(R.id.user_password_EditText);
        PasswordLayout = (TextInputLayout) findViewById(R.id.user_password_TextInputLayout);

        Signup = (TextView)findViewById(R.id.Signup_Login);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToRegister();
            }
        });
        forgetpass = findViewById(R.id.forget_pass);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginMain.this,Forgetpass.class);
                startActivity(intent);
            }
        });

        Login = findViewById(R.id.user_login_bt);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

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
                DoLogin();
            }
        });

    }

    private void DoLogin() {

        String email = Email.getText().toString();
        String password = Password.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void SwitchToRegister() {
        Intent intent = new Intent(LoginMain.this,register.class);
        startActivity(intent);
    }

}
