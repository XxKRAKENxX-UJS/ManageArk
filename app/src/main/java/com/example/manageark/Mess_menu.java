package com.example.manageark;

import static com.example.manageark.CalendarUtils.daysInWeekArray;
import static com.example.manageark.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;



public class Mess_menu extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private TextView bt_item;
    private final String TAG = "MAIN Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_menu);
        CalendarUtils.selectedDate = LocalDate.now();
        initWidgets();
        setWeekView();
        SettingAttend();






    }

    private  void SettingAttend(){

        //        hooks
        LabeledSwitch Switch_bt = findViewById(R.id.et_bt_switch);
        LabeledSwitch Switch_lunch = findViewById(R.id.et_lunch_switch);
        LabeledSwitch Switch_snack = findViewById(R.id.et_snack_switch);
        LabeledSwitch Switch_dinner = findViewById(R.id.et_dinner_switch);

        //       breakfast
        Switch_bt.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                if(isOn){
                    labeledSwitch.setColorOn(Color.parseColor("#00C4A6"));

                    Toast.makeText(getApplicationContext(),"Marked Attending",Toast.LENGTH_SHORT).show();
                }
                else{
                    labeledSwitch.setColorOn(Color.parseColor("#FF4081"));
                    Toast.makeText(getApplicationContext(),"Marked Not-Attending",Toast.LENGTH_SHORT).show();
                }

            }
        });





    }
    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);

        bt_item = findViewById(R.id.et_bt_item_special);
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
    public void nextWeekAction(View view)
    {
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
        Log.d("TT", String.valueOf(position));
        if(date.toString().equals("2021-12-01")){
            bt_item.setText("ho gaya");
        }

        if(date.toString().equals("2021-12-02")){
            bt_item.setText("fir se gaya");
        }

        if(date.toString().equals("2021-12-03")){
            bt_item.setText("hoooooooooo gaya");
        }
    }
}