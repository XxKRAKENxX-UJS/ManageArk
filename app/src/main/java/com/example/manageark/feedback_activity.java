package com.example.manageark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class feedback_activity extends AppCompatActivity {
    EditText meal_type;
    EditText Discripition;
    Button send;
    LocalDate SelectedDate;
    SharedPrefManager sharedPrefManager;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        SelectedDate = LocalDate.now();
        meal_type = findViewById(R.id.meal_type);
        Discripition = findViewById(R.id.descp);
        send=findViewById(R.id.feedback_bt);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        db = FirebaseFirestore.getInstance();

        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.feed_toolbar);

        //setting the title
        toolbar.setTitle("Complain");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        // Back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFeedback();
            }
        });

    }

    private void sendFeedback() {
        String date = SelectedDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
        String Meal_type = meal_type.getText().toString().toLowerCase();
        String descp = Discripition.getText().toString();
        String uid = sharedPrefManager.getUser().getUID();
        String mess_id = sharedPrefManager.getUser().getUniqueId();
        Log.d("fdf",uid);
        Log.d("fdf",mess_id);



        Map< String, Object > newFeedback = new HashMap< >();
        newFeedback.put("date", date);
        newFeedback.put("feedback_string", descp);
        newFeedback.put("uid", uid);

        db.collection("feedback").document(mess_id).collection(Meal_type).document().set(newFeedback)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void unused) {
                Toast.makeText(getApplicationContext(), "feedback added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "feedback fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}