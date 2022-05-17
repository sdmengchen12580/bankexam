package com.udit.frame.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyFlowUtils
{
    private static final String SAVE="FlowUtils";
    
    private static final String FLOWCOUNTS="FlowCounts";
    public static int getMyFlowCount(Context context)
    {
        SharedPreferences sharedPreferences = 
            context.getSharedPreferences(SAVE, Context.MODE_PRIVATE);
        String count = sharedPreferences.getString(FLOWCOUNTS, "");
        try
        {
            return Integer.parseInt(count);
        }
        catch (Exception e)
        {
           return 0;
        }
    }
    
    public static void SaveMyFlowCount(Context context,int num)
    {
        int count = getMyFlowCount(context);
        int n = count+num;
        SharedPreferences sharedPreferences = 
            context.getSharedPreferences(SAVE, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.putInt(FLOWCOUNTS, n);
        edit.commit();
    }
    
}
