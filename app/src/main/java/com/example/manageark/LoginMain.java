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

import org.w3c.dom.Text;

public class LoginMain extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        TextView Email = (TextView)findViewById(R.id.user_email_EditText);
        TextInputLayout EmailLayout = (TextInputLayout) findViewById(R.id.user_email_TextInputLayout);

        TextView Password = (TextView)findViewById(R.id.user_password_EditText);
        TextInputLayout PasswordLayout = (TextInputLayout) findViewById(R.id.user_password_TextInputLayout);

        Button Login = findViewById(R.id.user_login_bt);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoLogin(Email,Password);
            }
        });

    }

    private void DoLogin(TextView email, TextView password) {

        String Email = email.getText().toString();
        String Password = password.getText().toString();

        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}