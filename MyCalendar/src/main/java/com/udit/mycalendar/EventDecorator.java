package com.udit.mycalendar;

import android.util.Log;


import java.text.SimpleDateFormat;
import java.util.HashSet;

import com.udit.mycalendar.Interface.DayViewDecorator;
import com.udit.mycalendar.Util.CalendarDay;

public class EventDecorator implements DayViewDecorator {
    private final int color;
    private final HashSet<CalendarDay> calendarDays;

    public EventDecorator(int color, HashSet<CalendarDay> calendarDays) {
        this.color = color;
        this.calendarDays = new HashSet<>(calendarDays);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Log.d("Decorate: ", "Day " + new SimpleDateFormat("d/MM/yyyy").format(day.getDate()) + " " + calendarDays.contains(day));
        return calendarDays.contains(day);
    }

    @Override
    public void decorate(DayView view) {

    }
}
