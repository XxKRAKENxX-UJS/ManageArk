package com.example.manageark;

import static com.example.manageark.CalendarUtils.daysInWeekArray;
import static com.example.manageark.CalendarUtils.monthYearFromDate;
import static com.example.manageark.CalendarUtils.selectedDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manageark.Model.UserModel;
import com.example.manageark.Model.WeekDayBoolModel;
import com.example.manageark.Model.WeekdayMessMenuModel;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;



public class Mess_menu extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private final String TAG = "MAIN Activity";

    private TextView breakfast;
    private TextView b_time;
    private TextView b_regular;

    private TextView lunch;
    private TextView l_time;
    private TextView l_regular;

    private TextView dinner;
    private TextView d_time;
    private TextView d_regular;

    private TextView snacks;
    private TextView s_time;
    private TextView s_regular;

    private LocalDate SelectedDate;

    private LinearLayout EventMenu;

    private LabeledSwitch Switch_bt;
    private LabeledSwitch Switch_lunch;
    private LabeledSwitch Switch_snack;
    private LabeledSwitch Switch_dinner;

    private SharedPrefManager sharedPrefManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_menu);
        CalendarUtils.selectedDate = LocalDate.now();
        SelectedDate = LocalDate.now();
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        initWidgets();
        setWeekView();
        SettingAttend();
        WeekdayMenuFirebaseData();
        getDocumentPath();
    }

    private String getDocumentPath() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        String uID = sharedPrefManager.getUser().getUID();
        String date = SelectedDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));

        String docRef =sharedPrefManager.getUser().getUniqueId()+"/DATE/"+date+"/students/"+uID+"/AttendingDATA"+"/D6349CK";
        return docRef;
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);


        breakfast = findViewById(R.id.et_bt_item_special);
        b_time = findViewById(R.id.et_bt_t);
        b_regular = findViewById(R.id.et_bt_item_regular);

        lunch = findViewById(R.id.et_lunch_item_special);
        l_time = findViewById(R.id.et_lunch_t);
        l_regular= findViewById(R.id.et_lunch_item_regular);

        dinner= findViewById(R.id.et_dinner_item_special);
        d_time= findViewById(R.id.et_dinner_t);
        d_regular= findViewById(R.id.et_dinner_item_regular);

        snacks = findViewById(R.id.et_snack_item_special);
        s_time = findViewById(R.id.et_snack_t);
        s_regular= findViewById(R.id.et_snack_item_regular);

        EventMenu = findViewById(R.id.Event_view);


        Switch_bt = findViewById(R.id.et_bt_switch);
        Switch_lunch = findViewById(R.id.et_lunch_switch);
        Switch_snack = findViewById(R.id.et_snack_switch);
        Switch_dinner = findViewById(R.id.et_dinner_switch);

    }

    private void WeekdayMenuFirebaseData() {

        String date = SelectedDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));

        System.out.println(SelectedDate);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("mess_weekday_info").document("L6B8CUD").collection("DATES").document(date);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if ( documentSnapshot.exists() ) {
                    WeekdayMessMenuModel MessMenu = documentSnapshot.toObject(WeekdayMessMenuModel.class);
                    EventMenu.setVisibility(View.VISIBLE);

                    breakfast.setText(MessMenu.getBreakfast());
                    b_time.setText(MessMenu.getB_time());
                    b_regular.setText(MessMenu.getB_regular());

                    dinner.setText(MessMenu.getDinner());
                    d_regular.setText(MessMenu.getD_regular());
                    d_time.setText(MessMenu.getD_time());

                    lunch.setText(MessMenu.getLunch());
                    l_regular.setText(MessMenu.getL_regular());
                    l_time.setText(MessMenu.getL_time());

                    snacks.setText(MessMenu.getSnacks());
                    s_regular.setText(MessMenu.getS_regular());
                    s_time.setText(MessMenu.getS_time());

                } else  {
                    Log.d("ff","ll");
                    EventMenu.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(), "NA", Toast.LENGTH_SHORT).show();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ff","ll");
                        EventMenu.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "NA", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private  void SettingAttend(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRefk = db.collection("attendance").document(getDocumentPath());
        docRefk.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    WeekDayBoolModel boolModel = documentSnapshot.toObject(WeekDayBoolModel.class);

                    if(boolModel.getBreakfast()){
                        Switch_bt.setOn(true);
                        Switch_bt.setColorOn(Color.parseColor("#00C4A6"));

                    }
                    else{
                        Switch_bt.setOn(false);
                        Switch_bt.setColorOn(Color.parseColor("#FF4081"));

                    }

                    if(boolModel.getDinner()){
                        Switch_dinner.setOn(true);
                        Switch_dinner.setColorOn(Color.parseColor("#00C4A6"));
                    }
                    else{
                        Switch_dinner.setOn(false);
                        Switch_dinner.setColorOn(Color.parseColor("#FF4081"));
                    }

                    if (boolModel.getLunch()){
                        Switch_lunch.setOn(true);
                        Switch_lunch.setColorOn(Color.parseColor("#00C4A6"));
                    }
                    else {
                        Switch_lunch.setOn(false);
                        Switch_lunch.setColorOn(Color.parseColor("#FF4081"));
                    }

                    if (boolModel.getSnacks()){
                        Switch_snack.setOn(true);
                        Switch_snack.setColorOn(Color.parseColor("#00C4A6"));
                    }
                    else {
                        Switch_snack.setOn(false);
                        Switch_snack.setColorOn(Color.parseColor("#FF4081"));
                    }

                }
            }
        });

        //       breakfast
        Switch_bt.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                if(isOn){
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                   docRef.update("breakfast", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override

                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               labeledSwitch.setColorOn(Color.parseColor("#00C4A6"));

                               Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
                }
                else{
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                    docRef.update("breakfast", false).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                labeledSwitch.setColorOn(Color.parseColor("#FF4081"));
                                Toast.makeText(getApplicationContext(),"Marked Not-Attending",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        //       lunch

        Switch_lunch.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                if(isOn){
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                    docRef.update("lunch", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                labeledSwitch.setColorOn(Color.parseColor("#00C4A6"));

                                Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                    docRef.update("lunch", false).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                labeledSwitch.setColorOn(Color.parseColor("#FF4081"));
                                Toast.makeText(getApplicationContext(),"Marked Not-Attending",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        //       snacks
        Switch_snack.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                if(isOn){
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                    docRef.update("snacks", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                labeledSwitch.setColorOn(Color.parseColor("#00C4A6"));

                                Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                    docRef.update("snacks", false).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                labeledSwitch.setColorOn(Color.parseColor("#FF4081"));
                                Toast.makeText(getApplicationContext(),"Marked Not-Attending",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        //       dinner
        Switch_dinner.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                if(isOn){
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                    docRef.update("dinner", true).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                labeledSwitch.setColorOn(Color.parseColor("#00C4A6"));

                                Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    // Update an existing document
                    DocumentReference docRef = db.collection("attendance").document(getDocumentPath());

                    docRef.update("dinner", false).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                labeledSwitch.setColorOn(Color.parseColor("#FF4081"));
                                Toast.makeText(getApplicationContext(),"Marked Not-Attending",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        layoutManager.smoothScrollToPosition(calendarRecyclerView, null, 0);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {

        CalendarUtils.selectedDate = date;
        setWeekView();

        SelectedDate = date;

        WeekdayMenuFirebaseData();
        SettingAttend();
    }


}