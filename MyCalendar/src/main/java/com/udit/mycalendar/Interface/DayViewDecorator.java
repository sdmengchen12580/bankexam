package com.udit.mycalendar.Interface;

import com.udit.mycalendar.DayView;
import com.udit.mycalendar.Util.CalendarDay;

public interface DayViewDecorator {
    boolean shouldDecorate(CalendarDay day);

    void decorate(DayView view);
}
