package com.udit.bankexam.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimeUtils {

    public static List<String> getDateList(int paramInt) {
        String str = getTimeToday_();
        ArrayList arrayList1 = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int day = Integer.parseInt(str.split("-")[2]);
        int monthday = getMontyDay(str);
        for (int i = 0; i < paramInt; i++) {
            arrayList1.add(Integer.valueOf(day - paramInt + i));
        }
        arrayList1.add(Integer.valueOf(day));
        int i = 0;
        for (int j = 0; j < 7 - paramInt - 1; j++) {
            arrayList1.add(Integer.valueOf(day + i + 1));
            i++;
        }
        for (int j = 0; j < arrayList1.size(); j++) {
            if (((Integer) arrayList1.get(j)).intValue() > 0 && ((Integer) arrayList1.get(j)).intValue() <= monthday) {
                StringBuilder stringBuilder = new StringBuilder();
                arrayList2.add(arrayList1.get(j) + "");
            }
        }
        return arrayList2;
    }

    public static int getDayofWeek(String paramString) {
        Calendar calendar = Calendar.getInstance();
        if (paramString.equals("")) {
            calendar.setTime(new Date(System.currentTimeMillis()));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date date = simpleDateFormat.parse(paramString);
                calendar.setTime(new Date(date.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int getMontyDay(String paramString) {
        String[] arrayOfString = paramString.split("-");
        int i = 0;
        if (arrayOfString.length == 3) {
            i = Integer.parseInt(arrayOfString[0]);
            int j = Integer.parseInt(arrayOfString[1]);
            return (j == 4 || j == 6 || j == 9 || j == 11) ? 30 : ((j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12) ? 31 : ((i % 4 == 0) ? 29 : 28));
        }
        return i;
    }

    public static String getTimeToday() {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    public static String getTimeToday_() {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    public static List<String> listOrder(List<String> paramList) {
        ArrayList arrayList = new ArrayList();
        List<String> list = paramList;
        if (paramList.size() > 1) {
            for (int i = paramList.size() - 1; i >= 0; i--)
                arrayList.add(paramList.get(i));
            list = arrayList;
        }
        return list;
    }

    private static void log(String paramString) {
        Log.e("日期----------:", paramString);
    }
}
