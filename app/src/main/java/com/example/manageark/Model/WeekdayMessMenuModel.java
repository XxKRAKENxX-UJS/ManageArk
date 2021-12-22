package com.example.manageark.Model;

import com.google.firebase.firestore.ServerTimestamp;

public class WeekdayMessMenuModel {
    String b_time_start;
    String d_time_start;
    String l_time_start;
    String s_time_start;

    String l_time_end;
    String b_time_end;
    String d_time_end;
    String s_time_end;


    String breakfast;
    String b_time;
    String b_regular;
    String lunch;
    String l_time;
    String l_regular;
    String snacks;
    String s_time;
    String s_regular;
    String dinner;
    String d_time;
    String d_regular;

    public WeekdayMessMenuModel() {

    }

    public WeekdayMessMenuModel(String b_time_start, String d_time_start, String l_time_start, String s_time_start, String l_time_end, String b_time_end, String d_time_end, String s_time_end) {
        this.b_time_start = b_time_start;
        this.d_time_start = d_time_start;
        this.l_time_start = l_time_start;
        this.s_time_start = s_time_start;
        this.l_time_end = l_time_end;
        this.b_time_end = b_time_end;
        this.d_time_end = d_time_end;
        this.s_time_end = s_time_end;
    }

    public WeekdayMessMenuModel(String b_time_start, String d_time_start, String l_time_start, String s_time_start, String l_time_end, String b_time_end, String d_time_end, String s_time_end, String breakfast, String b_time, String b_regular, String lunch, String l_time, String l_regular, String snacks, String s_time, String s_regular, String dinner, String d_time, String d_regular) {
        this.b_time_start = b_time_start;
        this.d_time_start = d_time_start;
        this.l_time_start = l_time_start;
        this.s_time_start = s_time_start;
        this.l_time_end = l_time_end;
        this.b_time_end = b_time_end;
        this.d_time_end = d_time_end;
        this.s_time_end = s_time_end;
        this.breakfast = breakfast;
        this.b_time = b_time;
        this.b_regular = b_regular;
        this.lunch = lunch;
        this.l_time = l_time;
        this.l_regular = l_regular;
        this.snacks = snacks;
        this.s_time = s_time;
        this.s_regular = s_regular;
        this.dinner = dinner;
        this.d_time = d_time;
        this.d_regular = d_regular;
    }

    public String getB_time_start() {
        return b_time_start;
    }

    public void setB_time_start(String b_time_start) {
        this.b_time_start = b_time_start;
    }

    public String getD_time_start() {
        return d_time_start;
    }

    public void setD_time_start(String d_time_start) {
        this.d_time_start = d_time_start;
    }

    public String getL_time_start() {
        return l_time_start;
    }

    public void setL_time_start(String l_time_start) {
        this.l_time_start = l_time_start;
    }

    public String getS_time_start() {
        return s_time_start;
    }

    public void setS_time_start(String s_time_start) {
        this.s_time_start = s_time_start;
    }

    public String getL_time_end() {
        return l_time_end;
    }

    public void setL_time_end(String l_time_end) {
        this.l_time_end = l_time_end;
    }

    public String getB_time_end() {
        return b_time_end;
    }

    public void setB_time_end(String b_time_end) {
        this.b_time_end = b_time_end;
    }

    public String getD_time_end() {
        return d_time_end;
    }

    public void setD_time_end(String d_time_end) {
        this.d_time_end = d_time_end;
    }

    public String getS_time_end() {
        return s_time_end;
    }

    public void setS_time_end(String s_time_end) {
        this.s_time_end = s_time_end;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getB_time() {
        return b_time;
    }

    public void setB_time(String b_time) {
        this.b_time = b_time;
    }

    public String getB_regular() {
        return b_regular;
    }

    public void setB_regular(String b_regular) {
        this.b_regular = b_regular;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getL_time() {
        return l_time;
    }

    public void setL_time(String l_time) {
        this.l_time = l_time;
    }

    public String getL_regular() {
        return l_regular;
    }

    public void setL_regular(String l_regular) {
        this.l_regular = l_regular;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getS_regular() {
        return s_regular;
    }

    public void setS_regular(String s_regular) {
        this.s_regular = s_regular;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getD_time() {
        return d_time;
    }

    public void setD_time(String d_time) {
        this.d_time = d_time;
    }

    public String getD_regular() {
        return d_regular;
    }

    public void setD_regular(String d_regular) {
        this.d_regular = d_regular;
    }
}

