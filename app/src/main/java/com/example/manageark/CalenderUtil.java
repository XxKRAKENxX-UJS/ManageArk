package com.example.manageark;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

public class CalenderUtil {
    LocalDate todayDate;
    Calendar startDate;
    Calendar endDate;

    CalenderUtil() {
        todayDate = LocalDate.now();

        Calendar.getInstance();

        startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_WEEK, getMondayInt());

        endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_WEEK, getSundayInt());
    }

    int getMondayInt() {
        LocalDate temp = todayDate;
        int count = 0;

        while (temp.getDayOfWeek() != DayOfWeek.MONDAY){
            temp = temp.minusDays(1);
            count--;
        }
        return count;
    }

    int getSundayInt() {
        LocalDate temp = todayDate;
        int count = 0;

        while (temp.getDayOfWeek() != DayOfWeek.SUNDAY){
            temp = temp.plusDays(1);
            count++;
        }
        return count + 7;
    }
}
