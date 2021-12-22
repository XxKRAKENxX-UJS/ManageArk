package com.example.manageark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.manageark.Model.UserModel;
import com.example.manageark.Model.WeekdayMessMenuModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;

public class Home_Activity extends AppCompatActivity {

    //temp array
    int images[] = {R.drawable.ngo_ad,R.drawable.ngo_ad,R.drawable.ngo_ad};

    ExtendedFloatingActionButton QRscan;
    CardView Mess_menu;
    CardView feedback;
    CardView profile;

    SliderView sliderView;
    boolean isDinner;
    boolean isBreakfast;
    boolean isSnacks;
    boolean isLunch;
    String b_time_start;
    String b_time_end;
    String d_time_start;
    String d_time_end;
    String l_time_start;
    String l_time_end;
    String s_time_start;
    String s_time_end;
    String current_time;



    private LocalDate SelectedDate;

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalTime time = LocalTime.now();

        current_time = time.toString();
        Log.d("fdsf",current_time);
        CalendarUtils.selectedDate = LocalDate.now();
        SelectedDate = LocalDate.now();
        profile = findViewById(R.id.icon_complain);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        setContentView(R.layout.activity_home);

        //Hooks
        sliderView = findViewById(R.id.imageSlider);
        QRscan = findViewById(R.id.QRscan);
        Mess_menu = (CardView) findViewById(R.id.icon_menu);
        feedback = findViewById(R.id.icon_complain);
        getUserData();
        getTime();
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



        Intent intent = getIntent();
        String id = intent.getStringExtra("Attend");
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);

        //setting the title
        toolbar.setTitle("Home");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        // Back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(Home_Activity.this,Profile_activity.class);
                startActivity(i);
            }
        });
        Mess_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Activity.this,Mess_menu.class);
                startActivity(i);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Activity.this,Profile_activity.class);
                startActivity(i);
            }
        });


//        initliaze();

//        Log.d("fdsf",b_time_end);


        QRscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),scannerView.class));

                Intent intent = getIntent();
                String id = intent.getStringExtra("Attend");
                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();


                try {
                    checkTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                updateAttend();

            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Activity.this,feedback_activity.class));
            }
        });








    }

//    private void initliaze() {
//        b_time_start = sharedPrefManager.gettime().getB_time_start();
//        b_time_end = sharedPrefManager.gettime().getB_time_end();
//        l_time_start = sharedPrefManager.gettime().getL_time_start();
//        d_time_start = sharedPrefManager.gettime().getD_time_start();
//        s_time_start = sharedPrefManager.gettime().getS_time_start();
//        d_time_end = sharedPrefManager.gettime().getD_time_end();
//        s_time_end = sharedPrefManager.gettime().getS_time_end();
//        l_time_end = sharedPrefManager.gettime().getL_time_end();
//    }

    private void checkTime() throws ParseException {

        if(isTimeBetweenTwoTime(b_time_start,b_time_end,current_time))
        {
            isBreakfast = true;
        }
        else{
            isBreakfast = false;
        }

        if(isTimeBetweenTwoTime(d_time_start,d_time_end,current_time))
        {
            isDinner = true;
        }
        else{
            isDinner = false;
        }


        if(isTimeBetweenTwoTime(s_time_start,s_time_end,current_time))
        {
            isSnacks = true;
        }
        else{
            isSnacks = false;
        }

        if(isTimeBetweenTwoTime(l_time_start,l_time_end,current_time))
        {
            isLunch = true;
        }
        else{
            isLunch = false;
        }

        Toast.makeText(getApplicationContext(), "fgf", Toast.LENGTH_SHORT).show();



    }


    public static boolean isTimeBetweenTwoTime(String argStartTime,
                                               String argEndTime, String argCurrentTime) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        //

        if (argStartTime.matches(reg) && argEndTime.matches(reg)
                && argCurrentTime.matches(reg)) {
            boolean valid = false;
            // Start Time
            java.util.Date startTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argStartTime);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);

            // Current Time
            java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argCurrentTime);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentTime);

            // End Time
            java.util.Date endTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argEndTime);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);

            //
            if (currentTime.compareTo(endTime) < 0) {

                currentCalendar.add(Calendar.DATE, 1);
                currentTime = currentCalendar.getTime();

            }

            if (startTime.compareTo(endTime) < 0) {

                startCalendar.add(Calendar.DATE, 1);
                startTime = startCalendar.getTime();

            }
            //
            if (currentTime.before(startTime)) {

                System.out.println(" Time is Lesser ");

                valid = false;
            } else {

                if (currentTime.after(endTime)) {
                    endCalendar.add(Calendar.DATE, 1);
                    endTime = endCalendar.getTime();

                }

                if (currentTime.before(endTime)) {
                    System.out.println("RESULT, Time lies b/w");
                    valid = true;
                } else {
                    valid = false;
                    System.out.println("RESULT, Time does not lies b/w");
                }

            }
            return valid;

        } else {
            throw new IllegalArgumentException(
                    "Not a valid time, expecting HH:MM:SS format");
        }

    }

    private String getDocumentPath() {
        String uID = sharedPrefManager.getUser().getUID();
        String date = SelectedDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));

        String docRef =sharedPrefManager.getUser().getUniqueId()+"/DATE/"+date+"/students/"+uID+"/QRscanDATA"+"/bLyOTOD7vVRrEmAGdtHM";
        return docRef;
    }


    private void updateAttend() {
        scannerView s = new scannerView();
        String att = s.Attend;

        if(att.equals(sharedPrefManager.getUser().getMessID())){
            if(isBreakfast){
                // Update an existing document
                DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                docRef.update("breakfast", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            if(isSnacks){
                DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                docRef.update("snacks", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            if(isDinner){
                DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                docRef.update("dinner", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            if(isLunch){
                DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                docRef.update("lunch", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }


        }


    }








    private  void getTime(){
//        String date = SelectedDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
        String date = "24_11_2021";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DocumentReference docRef = db.collection("mess_weekday_info").document("L6B8CUD").collection("DATES").document(date);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    WeekdayMessMenuModel T = documentSnapshot.toObject(WeekdayMessMenuModel.class);
                    b_time_start = T.getB_time_start();
                    b_time_end = T.getB_time_end();
                    d_time_start = T.getD_time_start();
                    d_time_end = T.getD_time_end();
                    l_time_start = T.getL_time_start();
                    l_time_end = T.getL_time_end();
                    s_time_start = T.getS_time_start();
                    s_time_end = T.getS_time_end();
//                    sharedPrefManager.saveTime(T);
//                    Log.d("sf",sharedPrefManager.gettime().get());

                }

            }
        });
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