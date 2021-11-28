package com.example.manageark.Model;

public class WeekDayBoolModel {
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
    private Boolean snacks;

    public  WeekDayBoolModel(){

    }

    public WeekDayBoolModel(Boolean breakfast, Boolean lunch, Boolean dinner, Boolean snacks) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snacks = snacks;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getLunch() {
        return lunch;
    }

    public void setLunch(Boolean lunch) {
        this.lunch = lunch;
    }

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }

    public Boolean getSnacks() {
        return snacks;
    }

    public void setSnacks(Boolean snacks) {
        this.snacks = snacks;
    }
}
