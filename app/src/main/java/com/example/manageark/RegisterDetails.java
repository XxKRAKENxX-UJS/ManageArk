package com.example.manageark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manageark.Model.UserModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterDetails extends AppCompatActivity {
    TextView Name,RollNo,MessID;
    TextInputLayout NameLayput,RollNoLayout,UniversityLayout,MessIDLayout;
    AutoCompleteTextView autoCompleteTextView;
    Button signup;

    String[] UniData  =  {"JKLU","ABCD","EFGH"};
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        //hooks
        signup = findViewById(R.id.RegisterDetails_signup_bt);

        autoCompleteTextView = findViewById(R.id.RegisterDetails_University_EditText);
        UniversityLayout =findViewById(R.id.RegisterDetails_University_TextInputLayout);

        Name = findViewById(R.id.RegisterDetails_Name_EditText);
        NameLayput =findViewById(R.id.RegisterDetails_Name_TextInputLayout);

        MessID = findViewById(R.id.RegisterDetails_mess_id_EditText);
        MessIDLayout = findViewById(R.id.RegisterDetails_Name_TextInputLayout);

        RollNo = findViewById(R.id.RegisterDetails_Rollno_EditText);
        RollNoLayout =findViewById(R.id.RegisterDetails_Rollno_TextInputLayout);





        //Initialize adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,UniData);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();







        //
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getText().toString();
                String rollno = RollNo.getText().toString();
                String univeristy = autoCompleteTextView.getText().toString();
                String messID = MessID.getText().toString();
                String email = extras.getString("Email");
                String password = extras.getString("Password");
                SignUp(password, new UserModel(
                        "1234",
                        name,
                        email,
                        rollno,
                        univeristy,
                        "123",
                        messID
                ));

            }
        });


    }




        // -------------
        // Signup user
        public void SignUp(String password, UserModel userModel){

            auth.createUserWithEmailAndPassword(userModel.getEmail(), password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = auth.getCurrentUser();

                                assert user != null;
                                String uid = user.getUid();
                                userModel.setUID(uid);
                                userModel.setPhotoUrl("profilepic/"+uid+".png");

                                postSignup(userModel);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Failure","While signUp");
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public void postSignup(UserModel userModel){

        firestore
                .collection("users")
                .document(userModel.getUID())
                .set(userModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(RegisterDetails.this, Home_Activity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    }
