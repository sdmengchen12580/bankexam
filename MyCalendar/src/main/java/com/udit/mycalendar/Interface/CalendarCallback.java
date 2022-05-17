package com.udit.mycalendar.Interface;


import java.util.ArrayList;
import java.util.Date;

import com.udit.mycalendar.Util.CalendarDay;

public interface CalendarCallback {
    Date getDateSelected();

    ArrayList<CalendarDay> getEvents();

    boolean getIndicatorsVisible();
}
