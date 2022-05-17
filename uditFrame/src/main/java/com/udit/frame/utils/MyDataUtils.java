package com.udit.frame.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyDataUtils
{
    private static final String TAG= MyDataUtils.class.getSimpleName();
    
    public static void deleteData(Context context,String fileName)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.clear().commit();
    }
    
    @SuppressWarnings("rawtypes")
    public static <T>T myDataToMap(Context mContext,String FileName,Class<T> VoclassName)
    {
        if(FileName==null || VoclassName==null)
            throw new RuntimeException("myDataToMap is null");
       
        Field[] fs = VoclassName.getDeclaredFields();
        try
        {
            T voT = VoclassName.newInstance();
            
            for(int i = 0 ;fs!=null && i<fs.length;i++)
            {
                Field field = fs[i];
                
                String name = field.getName();
             
                Class fieldCls = field.getType();
                MyLogUtils.i(TAG, name+":"+fieldCls.toString());
                Object obj = getData(mContext, FileName, name, fieldCls);
                field.setAccessible(true);
                field.set(voT, obj);
                field.setAccessible(false);
            }
            return voT;
        }
        catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        
        return null;
    }
    /**
     * 保存对象
     * <功能详细描述>
     * 

     * @param obj
     * @see [类、类#方法、类#成员]
     */
    public static HashMap<String, Object> objectToMap(Object obj, HashMap<String, Object> map)
    {
        if (obj == null)
            return null;
        if(map==null)
            map = new HashMap<String, Object>();
        
        Class<? extends Object> cls = obj.getClass();
        Field[] fs = cls.getDeclaredFields();
        for(int i = 0 ;fs!=null && i<fs.length;i++)
        {
             Field field = fs[i];
             try
            {
                 field.setAccessible(true);
                 Object value =  field.get(obj);
                 if(value!=null)
                 {
                     map.put(field.getName(), value);
                 }
                 field.setAccessible(false);
            }
            catch (IllegalArgumentException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }
    
    /**
     * 保存sharedPreferences 数据
     * <功能详细描述>
     * 
     * @param context
     * @param fileName
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public static void putData(Context context, String fileName, Map<String, Object> map)
    {
        MyLogUtils.e(TAG, fileName);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        for (Entry<String, Object> set : map.entrySet())
        {
            String key = set.getKey();
            Object value = set.getValue();
            MyLogUtils.e(TAG, key+":"+value);
            if (value instanceof String)
            {
                edit.putString(key, (String)value);
            }
            else if (value instanceof Boolean)
            {
                edit.putBoolean(key, (Boolean)value);
            }
            else if (value instanceof Float)
            {
                edit.putFloat(key, (Float)value);
            }
            else if (value instanceof Long)
            {
                edit.putLong(key, (Long)value);
            }
            else
            {
                edit.putInt(key, (Integer)value);
            }
        }
        edit.commit();
    }
    
    /**
     * sharedPreference 中获取数据
     * <功能详细描述>
     * 
     * @param context
     * @param fileName
     * @param key
     * @param clazz
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static Object getData(Context context, String fileName, String key, Class clazz)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (clazz.getName().equals(String.class.getName()))
        {
            return sharedPreferences.getString(key, "");
        }
        else if (clazz.getName().equals(Integer.class.getName()) || clazz.getName().equals(int.class.getName()))
        {
            return sharedPreferences.getInt(key, 0);
        }
        else if (clazz.getName().equals(Float.class.getName()) || clazz.getName().equals(float.class.getName()))
        {
            return sharedPreferences.getFloat(key, 0);
        }
        else if (clazz.getName().equals(Long.class.getName())  || clazz.getName().equals(long.class.getName()))
        {
            return sharedPreferences.getLong(key, 0);
        }
        else if(clazz.getName().equals(boolean.class.getName())|| clazz.getName().equals(Boolean.class.getName()))
        {
            return sharedPreferences.getBoolean(key,false);
        }
        else
        {
            return sharedPreferences.getBoolean(key, false);
        }
    }


}
