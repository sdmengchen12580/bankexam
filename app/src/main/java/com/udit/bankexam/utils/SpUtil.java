package com.udit.bankexam.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Method;
import java.util.Map;

public class SpUtil {
  public static final String FILE_NAME = "share_data";
  
  public static void clear(Context paramContext) { clear(paramContext, "share_data"); }
  
  public static void clear(Context paramContext, String paramString) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(paramString, 0).edit();
    editor.clear();
    SharedPreferencesCompat.apply(editor);
  }
  
  public static boolean contains(Context paramContext, String paramString) { return contains(paramContext, "share_data", paramString); }
  
  public static boolean contains(Context paramContext, String paramString1, String paramString2) { return paramContext.getSharedPreferences(paramString1, 0).contains(paramString2); }
  
  public static Object get(Context paramContext, String paramString, Object paramObject) { return get(paramContext, "share_data", paramString, paramObject); }
  
  public static Object get(Context paramContext, String paramString1, String paramString2, Object paramObject) {
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences(paramString1, 0);
    return (paramObject instanceof String) ? sharedPreferences.getString(paramString2, (String)paramObject) : ((paramObject instanceof Integer) ? Integer.valueOf(sharedPreferences.getInt(paramString2, ((Integer)paramObject).intValue())) : ((paramObject instanceof Boolean) ? Boolean.valueOf(sharedPreferences.getBoolean(paramString2, ((Boolean)paramObject).booleanValue())) : ((paramObject instanceof Float) ? Float.valueOf(sharedPreferences.getFloat(paramString2, ((Float)paramObject).floatValue())) : ((paramObject instanceof Long) ? Long.valueOf(sharedPreferences.getLong(paramString2, ((Long)paramObject).longValue())) : null))));
  }
  
  public static Map<String, ?> getAll(Context paramContext) { return getAll(paramContext, "share_data"); }
  
  public static Map<String, ?> getAll(Context paramContext, String paramString) { return paramContext.getSharedPreferences(paramString, 0).getAll(); }
  
  public static String put(Context paramContext, String paramString1, String paramString2) { put(paramContext, "share_data", paramString1, paramString2);
      return paramString1;
  }
  
  public static void put(Context paramContext, String paramString1, String paramString2, Object paramObject) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(paramString1, 0).edit();
    if (paramObject instanceof String) {
      editor.putString(paramString2, (String)paramObject);
    } else if (paramObject instanceof Integer) {
      editor.putInt(paramString2, ((Integer)paramObject).intValue());
    } else if (paramObject instanceof Boolean) {
      editor.putBoolean(paramString2, ((Boolean)paramObject).booleanValue());
    } else if (paramObject instanceof Float) {
      editor.putFloat(paramString2, ((Float)paramObject).floatValue());
    } else if (paramObject instanceof Long) {
      editor.putLong(paramString2, ((Long)paramObject).longValue());
    } else {
      editor.putString(paramString2, paramObject.toString());
    } 
    SharedPreferencesCompat.apply(editor);
  }
  
  public static void remove(Context paramContext, String paramString) { remove(paramContext, "share_data", paramString); }
  
  public static void remove(Context paramContext, String paramString1, String paramString2) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences(paramString1, 0).edit();
    editor.remove(paramString2);
    SharedPreferencesCompat.apply(editor);
  }
  
  private static class SharedPreferencesCompat {
    private static final Method sApplyMethod = findApplyMethod();
    
    public static void apply(SharedPreferences.Editor param1Editor) {
      try {
        if (sApplyMethod != null) {
          sApplyMethod.invoke(param1Editor, new Object[0]);
          return;
        } 
      } catch (IllegalArgumentException|IllegalAccessException|java.lang.reflect.InvocationTargetException illegalArgumentException) {}
      param1Editor.commit();
    }
    
    private static Method findApplyMethod() {
      try {
        return SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
      } catch (NoSuchMethodException noSuchMethodException) {
        return null;
      } 
    }
  }
}
