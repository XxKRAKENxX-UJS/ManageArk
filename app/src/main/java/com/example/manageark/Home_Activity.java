package com.example.manageark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.manageark.Model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

public class Home_Activity extends AppCompatActivity {

    //temp array
    int images[] = {R.drawable.one,R.drawable.two,R.drawable.three};

//    FloatingActionButton QRscan;
    CardView Mess_menu;
    SliderView sliderView;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("id",UniqueRandomIdGenerator.getUniqueRandomId());

        sharedPrefManager = new SharedPrefManager(getApplicationContext());


        setContentView(R.layout.activity_home);

        //Hooks
        sliderView = findViewById(R.id.imageSlider);
//        QRscan = findViewById(R.id.QRscan);
        Mess_menu = (CardView) findViewById(R.id.icon_menu);
        getUserData();
        //Slider Adapter setup
        SliderAdapterClass adapter = new SliderAdapterClass(images);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);

        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();




        Mess_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Activity.this,Mess_menu.class);
                startActivity(i);

            }
        });

//        QRscan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });




    }

    private void getUserData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DocumentReference docRef = db.collection("users").document(Objects.requireNonNull(auth.getUid()));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    UserModel User = documentSnapshot.toObject(UserModel.class);
                    sharedPrefManager.saveUser(User);
                }

            }
        });
    }


}