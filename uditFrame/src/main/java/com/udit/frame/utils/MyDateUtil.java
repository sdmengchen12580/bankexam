package com.udit.frame.utils;

import android.annotation.SuppressLint;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * 时间工具类
 * 
 * @author 曾宝
 * @version [V1.00, 2016年2月14日]
 * @see [相关类/方法]
 * @since V1.00
 */
@SuppressLint("SimpleDateFormat")
public final class MyDateUtil
{
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
    
    public static final String DATE_FORMAT_3 = "yyyyMMdd";
    
    public static final String DATE_FORMAT_4 = "MMdd";
    
    public static final String DATE_FORMAT_5 = "yyyyMM";
    
    public static final String DATE_FORMAT_6 = "yyyyMMddHHmmss";
    
    public static final String DATE_FORMAT_7 = "HH:mm";
    
    public static final String DATE_FORMAT_8 = "yyyy 年 MM 月 dd 日";
    
    public static final String DATE_FORMAT_9 = "yyyyMMddHHmmssSSS";
    
    public static final String DATE_FORMAT_10 = "yyyy";
    
    public static final String DATE_FORMAT_11 = "yyyy-MM-dd HH:mm";




    public static final String DATE_FORMAT_12 = "yyyy年MM月";


    public static final String DATE_FORMAT_13 = "yyyy.MM.dd";


    public static final String DATE_FORMAT_14="mm:ss";

    //秒
    public static final long calc_s = 1000;
    //分
    public static final long calc_m = 1000*60;

    public static final long calc_h = 1000*60*60;

    /**
     * 按格式化字符把日期转换成字符串型
     *
     * @param date
     * @param format
     *            指定格式
     * @return String
     */
    public static String date2String(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);// 把时间类型转为String类型
        return s;
    }

    public static String getMonthFirst(String month,String formats)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formats);
            Date date = format.parse(month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH,0);
            cal.set(Calendar.DAY_OF_MONTH,1);
            String first = format.format(cal.getTime());
            return first;
        } catch (ParseException e) {
            return month;
        }
    }

    public static String getMonthLast(String month,String formats)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formats);
            Date date = format.parse(month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH,1);
            cal.set(Calendar.DAY_OF_MONTH,0);
            String first = format.format(cal.getTime());
            return first;
        } catch (ParseException e) {
            return month;
        }
    }



    public static String getMonthFirst(String formats)
    {
        SimpleDateFormat format = new SimpleDateFormat(formats);

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH,0);
        cal.set(Calendar.DAY_OF_MONTH,1);
        String first = format.format(cal.getTime());
        return first;
    }

    public static String getMonthLast(String formats)
    {
        SimpleDateFormat format = new SimpleDateFormat(formats);

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH,0);
        String last = format.format(cal.getTime());
        return last;
    }

    public static String getTimesBySs()
    {
        String time = getTimeMillis();
        long time_l = Long.parseLong(time);
        String timestamps=String.valueOf(time_l/1000);
        return timestamps;
    }

    public static String formatLongTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        if(day>0)
        {
            return strDay+":"+strHour+":"+strMinute+":"+strSecond;
        }
        else if(hour>0)
        {
            return strHour+":"+strMinute+":"+strSecond;
        }
        return strMinute + ":" + strSecond;
    }

    public static String formatLongTime2(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        if(day>0)
        {
            return strDay+"日"+strHour+"时"+strMinute+"分"+strSecond+"秒";
        }
        else if(hour>0)
        {
            return strHour+"时"+strMinute+"分"+strSecond+"秒";
        }
        return strMinute + "分" + strSecond+"秒";
    }

    public static int getBetween(String begin,String end,String format,long calc)
    {
        SimpleDateFormat myFormatter = new SimpleDateFormat(format);

        Date date_begin = null;
        Date date_end = null;

        try
        {
            date_begin = myFormatter.parse(begin);
            date_end = myFormatter.parse(end);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        long s= date_begin.getTime() - date_end.getTime();
        System.out.println(s);
        long day = (date_begin.getTime() - date_end.getTime()) / calc;
        return (int) day;
    }

    /**
     * @brief 两个时间之间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDays(String startDate, String endDate, String fmt)
    {
        if (startDate == null || startDate.equals(" "))
            return 0;
        if (endDate == null || endDate.equals(" "))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat(fmt);
        Date date = null;
        Date mydate = null;
        try
        {
            date = myFormatter.parse(startDate);
            mydate = myFormatter.parse(endDate);
        }
        catch (Exception e)
        {
        }
        long day = (mydate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
        return (int) day;
    }



    /**
     * 上个月 
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getLastMonth()
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        String time = format.format(c.getTime());
        return time;
    }
    
    public static String getNowMonth()
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        String time = format.format(c.getTime());
        return time;
    }
    /**
     * 修改成新的样式
     * <功能详细描述>
     * 
     * @param date
     * @param oldFmt
     * @param newFmt
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String chgFmt(String date, String oldFmt, String newFmt)
    {
        Date d = toDate(date, oldFmt);
        SimpleDateFormat fmt = new SimpleDateFormat(newFmt);
        return fmt.format(d);
    }
    
    /**
     * 格式化样式
     * <功能详细描述>
     * 
     * @param date
     * @param fmt
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String format(Date date, String fmt)
    {
        SimpleDateFormat fmter = new SimpleDateFormat(fmt);
        return fmter.format(date);
    }
    
    public static String getDate(String format, int offset)
    {
        return getTime(format, 5, offset);
    }
    
  public static String getDate(String format)
    {
        return getDate(format, 0);
    }



    public static String getWeekOfDate(Date dt)
    {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
            
        return weekDays[w];
    }
    
    public static Date toDate(String date, String format)
    {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try
        {
            return fmt.parse(date);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    public static String getTime(String format, int offsetType, int offset)
    {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(offsetType, offset);
        return fmt.format(cal.getTime());
    }
    
    public static Date getDate(int offsetType, int offset)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(offsetType, offset);
        return cal.getTime();
    }
    
    @SuppressWarnings("WrongConstant")
    public static Date getDateS(int offset)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(5, offset);
        return cal.getTime();
    }
    
    public static boolean parse(String date, String format)
    {
        try
        {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            @SuppressWarnings("unused")
            Date date_begin = fmt.parse(date);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static String advanceMinute(String times,String format,int time)
    {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            Date date = fmt.parse(times);
            Calendar nowTime = Calendar.getInstance();
            nowTime.setTime(date);
            nowTime.add(Calendar.MINUTE, time);

            return fmt.format(nowTime.getTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    public static boolean compareTimeAdvance(String begin, String end, String format,int time)
    {
        try
        {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            Date date_begin = fmt.parse(begin);
            Date date_end = fmt.parse(end);
            Calendar nowTime = Calendar.getInstance();
            nowTime.setTime(date_end);
            nowTime.add(Calendar.MINUTE, time);
            date_end = nowTime.getTime();

            int result = date_begin.compareTo(date_end);
            if (result < 0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static boolean compareTime2(String begin, String end, String format)
    {
        try
        {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            Date date_begin = fmt.parse(begin);
            Date date_end = fmt.parse(end);
            int result = date_begin.compareTo(date_end);
            if (result <= 0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }



    public static boolean compareTime(String begin, String end, String format)
    {
        try
        {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            Date date_begin = fmt.parse(begin);
            Date date_end = fmt.parse(end);
            int result = date_begin.compareTo(date_end);
            if (result < 0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    @SuppressWarnings("WrongConstant")
    public static boolean compareTime(long time, int dayoffset)
    {
        Calendar curtime = GregorianCalendar.getInstance(Locale.getDefault());
        curtime.add(5, dayoffset);
        
        return curtime.getTimeInMillis() >= time;
    }
    
    public static String getTimeMillis()
    {
        return Calendar.getInstance(Locale.CHINA).getTimeInMillis() + "";
    }
    
    public static Timestamp getCurrentTime()
    {
        return new Timestamp(Calendar.getInstance(Locale.CHINA).getTimeInMillis());
    }
    
    public static Date getCurrentDate()
    {
        return Calendar.getInstance(Locale.CHINA).getTime();
    }
    
    public static boolean isDateIn(String currentDate, String startDate, String endDate)
    {
        if ((currentDate.compareTo(startDate) >= 0) && (currentDate.compareTo(endDate) <= 0))
        {
            return true;
        }
        return false;
    }
    
    // 获取本月第一天
    public static String getMonthFirstDay()
    {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        return firstday;
    }

    // 获取本周第一天
    public static String getWeekDay()
    {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.WEEK_OF_MONTH, 0);
        cale.set(Calendar.DAY_OF_WEEK, 1);
        firstday = format.format(cale.getTime());
        return firstday;
    }
    
    public static Date  getLastMonth(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
        
    }
    
    public static Date  getNextMonth(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
        
    }
    
    public static String getNextDay(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return format.format(date);
    }
    public static String getDateForLong(String dateFormat2, long parseLong)
    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat2);
        return format.format(parseLong);
    }
    public static boolean compareTimeSame(String end, String begin, String format)
    {
        try
        {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            Date date_begin = fmt.parse(begin);
            Date date_end = fmt.parse(end);
            Calendar c_begin = Calendar.getInstance();
            Calendar c_end = Calendar.getInstance();
            c_begin.setTime(date_begin);
            c_end.setTime(date_end);
            if(c_begin.get(Calendar.YEAR)==c_end.get(Calendar.YEAR) 
                && c_begin.get(Calendar.MONTH)==c_end.get(Calendar.MONTH)
                && c_begin.get(Calendar.DAY_OF_MONTH)==c_end.get(Calendar.DAY_OF_MONTH))
            {
                return true;
            }
            else
                return false;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }



}
