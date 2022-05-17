package com.udit.frame.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.annotation.SuppressLint;


/**
 * 
 * json 解析
 * 
 * @author 曾宝
 * @version [V1.00, 2015年12月29日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class JsonUtil
{
    public static Gson GSON = new Gson();
    
    private static final String TAG = JsonUtil.class.getSimpleName();


    public static <T>List<T> jsonToListByArrayNoSucess(String result,Class<T> cls)
    {
        try {
            JSONArray array = new JSONArray(result);
            List<T> list = new ArrayList<>();
            for(int i = 0;i<array.length();i++)
            {
                String json = array.getString(i);

                    T t = jsonToBean(json,cls);
                    if(t!=null)
                        list.add(t);

            }
            if(list!=null &&list.size()>0)
            {
                return list;
            }
            else
                return null;
        } catch (JSONException e) {
            MyLogUtils.e(TAG,e.getMessage());
            return null;
        }


    }



    public static <T>List<T> jsonToListByArray(String result,Class<T> cls)
    {
        try {
            JSONArray array = new JSONArray(result);
            List<T> list = new ArrayList<>();
            for(int i = 0;i<array.length();i++)
            {
                String json = array.getString(i);
                if(getJsonForOK(json))
                {
                    T t = jsonToBean(json,cls);
                    if(t!=null)
                        list.add(t);
                }

            }
            if(list!=null &&list.size()>0)
            {
                return list;
            }
            else
                return null;
        } catch (JSONException e) {
            MyLogUtils.e(TAG,e.getMessage());
            return null;
        }


    }

    public static List<String> jsonToListByValue(String result,String key)
    {
        try {
            JSONArray array = new JSONArray(result);
            List<String> list = new ArrayList<>();
            for(int i =0;array!=null &&i<array.length();i++)
            {
                JSONObject json = array.getJSONObject(i);
                if(json.has(key))
                {
                   String value =  json.getString(key);
                   if(!MyCheckUtils.isEmpty(value))
                   {
                       list.add(value);
                   }
                }
            }
            if(list!=null && list.size()>0)
            {
                return list;
            }
            else
                return  null;
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            return null;
        }


    }

    /**
     * {bean1} GJSON解析
     * <功能详细描述>
     * @param json
     * @param beanCls
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static <T> T JsonToObject(String json, Class<T> beanCls)
    {
        T t = GSON.fromJson(json, beanCls);
        return t;
    }
    
    /**
     * {bean1} 反射解析
     * <功能详细描述>
     * 
     * @param json
     * @param beanCls
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static <T> T jsonToBean(String json, Class<T> beanCls)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            T t = beanCls.newInstance();
            Field[] fs = beanCls.getDeclaredFields();
            for (int i = 0; fs != null && i < fs.length; i++)
            {
                Field field = fs[i];
                String name = field.getName();
                String type = field.getGenericType().toString();
                if (jsonObject.has(name))
                {// 如果存在
                    Object obj = jsonObject.get(name);
                    field.setAccessible(true);
                    setValue(field, t, obj, type);
                    field.setAccessible(false);
                }
            }
            return t;
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, "jsonToBean:" + e.getMessage());
            return null;
        }
    }
    
    /**
     * setValue 反射放置对应的值
     * <功能详细描述>
     * 
     * @param field
     * @param t
     * @param obj
     * @param type
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @see [类、类#方法、类#成员]
     */
    private static <T> void setValue(Field field, T t, Object obj, String type)
        throws IllegalArgumentException, IllegalAccessException
    {
        if (type.equals(String.class.toString()))
        {
            field.set(t, obj.toString());
        }
        else if (type.equals(Integer.class.toString()) || type.equals(int.class.toString()))
        {
            field.setInt(t, Integer.parseInt(obj.toString()));
        }
        else if (type.equals(Float.class.toString()) || type.equals(float.class.toString()))
        {
            field.setFloat(t, Float.parseFloat(obj.toString()));
        }
        else if (type.equals(Boolean.class.toString()) || type.equals(boolean.class.toString()))
        {
            field.setBoolean(t, Boolean.parseBoolean(obj.toString()));
        }
        else
        {
            field.set(t, obj);
        }
    }
    
    
    public static <T>List<T> jsonToList(String json,Class<T> cls)
    {
        try
        {
            JSONArray array = new JSONArray(json);
            List<T> list = new ArrayList<T>();
            for(int i = 0;array!=null && i<array.length();i++)
            {
               JSONObject obj =  (JSONObject)array.get(i);
               T t = GSON.fromJson(obj.toString(), cls);
               list.add(t);
            }
            return list.size()>0?list:null;
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            return null;
        }
            
    }
    /**
     * 
     * 根据key 获取jsonObject 对应的值
     * 
     * @param json
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Object valueToJson(String json, String key)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                return jsonObject.get(key);
            }
            else
                return null;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "valueToJson:" + e.getMessage());
            return null;
        }
    }
    
    /**
     * object 转json
     * <功能详细描述>
     * 
     * @param obj
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String objectToJson(Object obj)
    {
        String json = null;
        json = GSON.toJson(obj, obj.getClass());
        MyLogUtils.i(TAG, "json:" + json);
        return json;
    }
    
    /**
     * 解析内容{"key":[{cls},{cls},{cls}]}
     * 
     * @param json
     * @param key
     * @param cls
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static <T> List<T> jsonToList(String json, String key, Class<T> cls)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                JSONArray array = (JSONArray)jsonObject.get(key);
                List<T> list = new ArrayList<T>();
                for (int i = 0; array != null && i < array.length(); i++)
                {
                    String object = array.getString(i);
                    T t = GSON.fromJson(object, cls);
                    list.add(t);
                }
                if (list.size() == 0)
                    list = null;
                return list;
            }
            else
                return null;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "jsonToList:" + e.getMessage());
            return null;
        }
    }
    
    public static  List<String> jsonToListString(String json, String key)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                JSONArray array = (JSONArray)jsonObject.get(key);
                List<String> list = new ArrayList<String>();
                for (int i = 0; array != null && i < array.length(); i++)
                {
                    String object = array.getString(i);
                    list.add(object);
                }
                if (list.size() == 0)
                    list = null;
                return list;
            }
            else
                return null;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "jsonToList:" + e.getMessage());
            return null;
        }
    }
    
    /**
     * List<vo>
     * vo-> bean1,bean2...
     * {"key":[{bean1,bean2},{},{}]}
     * <功能详细描述>
     * @param json
     * @param key
     * @param clsVo
     * @param cls
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressLint("DefaultLocale")
    @SuppressWarnings("rawtypes")
    public static <T> List<T> jsonToList(String json, String key, Class<T> clsVo, Class... cls)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                JSONArray array = (JSONArray)jsonObject.get(key);
                List<T> list = new ArrayList<T>();
                
                for (int i = 0; array != null && i < array.length(); i++)
                {
                    JSONObject object = (JSONObject)array.get(i);
                    T objVo = (T)clsVo.newInstance();
                    for (int j = 0; cls != null && j < cls.length; j++)
                    {
                        @SuppressWarnings("unchecked")
                        T t = (T)GSON.fromJson(object.toString(), cls[j]);
                        Field f = clsVo.getDeclaredField(cls[j].getSimpleName().toLowerCase());
                        f.setAccessible(true);
                        f.set(objVo, t);
                        f.setAccessible(false);
                    }
                    list.add(objVo);
                }
                if (list.size() == 0)
                    list = null;
                return list;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, "jsonToList:" + e.getMessage());
            return null;
        }
    }
    
    public static <T> List<T> jsonToList(String json, Class<T> clsVo, String list, Class<?>... classes)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(list))
            {
                JSONArray json_vo = jsonObject.getJSONArray(list);
                List<T> mlist = new ArrayList<T>();
                for (int i = 0; json_vo != null && i < json_vo.length(); i++)
                {
                    JSONObject json_str = json_vo.getJSONObject(i);
                    T t = clsVo.newInstance();
                    for (int j = 0; classes != null && j < classes.length; j++)
                    {
                        Class<?> s = classes[j];
                        String name = s.getSimpleName();
                        name = Utils.toLowerCaseFirstOne(name);
                       if (json_str.has(name))
                        {
                            String str = json_str.getString(name);
                            Object obj = jsonToBean(str, s);
                            Field field = clsVo.getDeclaredField(name);
                            field.setAccessible(true);
                            field.set(t, obj);
                            field.setAccessible(false);
                            
                        }
                    }
                    Field[] fs = clsVo.getDeclaredFields();
                    for (int k = 0; fs != null && k < fs.length; k++)
                    {
                        Field field = fs[k];
                        String field_name = field.getName();
                        String type = field.getType().toString();
                        if (type.equals(String.class.toString()))
                        {
                            if (json_str.has(field_name))
                            {
                                Object json_field = json_str.get(field_name);
                                field.setAccessible(true);
                                field.set(t,json_field.toString());
                                field.setAccessible(false);
                            }
                            
                        }
                        else if (type.equals(Integer.class.toString()) || type.equals(int.class.toString()))
                        {
                            if (json_str.has(field_name))
                            {
                                Object json_field = json_str.get(field_name);
                                field.setAccessible(true);
                                field.set(t,Integer.parseInt(json_field.toString()));
                                field.setAccessible(false);
                            }
                        }
                        else if (type.equals(Float.class.toString()) || type.equals(float.class.toString()))
                        {
                            if (json_str.has(field_name))
                            {
                                Object json_field = json_str.get(field_name);
                                field.setAccessible(true);
                                field.set(t,Float.parseFloat(json_field.toString()));
                                field.setAccessible(false);
                            }
                        }
                        else if (type.equals(Boolean.class.toString()) || type.equals(boolean.class.toString()))
                        {
                            if (json_str.has(field_name))
                            {
                                Object json_field = json_str.get(field_name);
                                field.setAccessible(true);
                                field.set(t,Boolean.parseBoolean(json_field.toString()));
                                field.setAccessible(false);
                            }
                        }
                        
                    }
                    mlist.add(t);
                }
                return mlist.size() <= 0 ? null : mlist;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            MyLogUtils.i(TAG, e.getMessage());
            return null;
        }
    }
   
    /**
     * {"key":{bean1,bean2}}->vo
     * <功能详细描述>
     * @param json
     * @param classVo
     * @param key
     * @param beanCls
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressLint("DefaultLocale")
    @SuppressWarnings("rawtypes")
    public static <T> T jsonToVo(String json, Class<T> classVo, String key, Class... beanCls)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                JSONObject json_key = jsonObject.getJSONObject(key);
                T t = classVo.newInstance();
                for (int j = 0; beanCls != null && j < beanCls.length; j++)
                {
                    @SuppressWarnings("unchecked")
                    T t_bean = (T)jsonToBean(json_key.toString(), beanCls[j]);
                    Field f = classVo.getDeclaredField(beanCls[j].getSimpleName().toLowerCase());
                    f.setAccessible(true);
                    f.set(t, t_bean);
                    f.setAccessible(false);
                }
                return t;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, "jsonToVo:" + e.getMessage());
            return null;
        }
    }
    
    /**
     * {"key":{bean}} ->提取对应key的
     * <功能详细描述>
     * 
     * @param json
     * @param beanCls
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static <T> T jsonToObject(String json, Class<T> beanCls, String key)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                String jsonObject2 = jsonObject.get(key).toString();
                return JsonToObject(jsonObject2, beanCls);
            }
            else
                return null;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "jsonToObject is err", e);
            return null;
        }
    }
    
    
    /**
     * {"key":"","key1":""} 
     * <功能详细描述>
     * @param json
     * @param keys
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String[] getJsonforKey(String json, String... keys)
    {
        if (keys == null || keys.length == 0)
            return null;
            
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            String[] values = new String[keys.length];
            for (int i = 0; i < keys.length; i++)
            {
                String value = "";
                Object obj = jsonObject.get(keys[i]);
                if (obj == null)
                {
                    value = "";
                }
                else
                    value = obj + "";
                    
                values[i] = value;
            }
            
            return values;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "getJsonforKey is err", e);
            return null;
        }
    }
    
    /**
     * {"key":""}
     * <功能详细描述>
     * @param json
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Object getJsonforKey(String json, String key)
    {
        if (key == null || key.length() == 0)
            return null;
            
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                return jsonObject.get(key);
            }
            else
                return null;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "getJsonforKey is err", e);
            return null;
        }
    }


    public static Object getJsonArrayforKey(String json, String key)
    {
        if (key == null || key.length() == 0)
            return null;

        try
        {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject =  jsonArray.getJSONObject(0);
            if (jsonObject.has(key))
            {
                return jsonObject.get(key);
            }
            else
                return null;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "getJsonforKey is err", e);
            return null;
        }
    }
    
    /**
     * {"message":"信息"}
     * <功能详细描述>
     * @param json
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getJsonforMsg(String json)
    {
        JSONObject jsonObject;
        try
        {
            jsonObject = new JSONObject(json);
            if (jsonObject.has("message"))
            {
                String value = jsonObject.get("message").toString();
                return value;
            }
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, "getJsonforMsg IS ERR:", e);
            return null;
        }
        
        return null;
    }
    
    /**
     * {"result":"200"}->ok
     * <功能详细描述>
     * @param json
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean getJsonForOK(String json)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if(jsonObject.has("result"))
            {
                String value = jsonObject.get("result").toString();
                if (value.equals("success")|| value.equals("200"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
                return false;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            return false;
        }
        
    }
    
    /**
     * vo ->List<bean1> 这种包含关系
     * <功能详细描述>
     * @param json
     * @param key
     * @param clsVo
     * @param clsKey
     * @param cls
     * @param list_key
     * @param class_list
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static <T> T jsonToGroupVo(String json, String key, Class<T> clsVo, 
        String clsKey, Class cls, String list_key, Class class_list)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key))
            {
                JSONObject json_key = (JSONObject)jsonObject.get(key);
                T t = clsVo.newInstance();
                
                @SuppressWarnings("unchecked")
                T t_cls_key = (T)jsonToObject(json_key.toString(), cls, clsKey);
                
                @SuppressWarnings("unchecked")
                List<T> cls_list = jsonToList(json_key.toString(), list_key, class_list);
                
                Field[] fs = clsVo.getDeclaredFields();
                for (int i = 0; fs != null && i < fs.length; i++)
                {
                    Field field = fs[i];
                    field.setAccessible(true);
                    if (field.getName().equals(clsKey))
                    {
                        field.set(t, t_cls_key);
                    }
                    else if (field.getName().equals(list_key))
                    {
                        field.set(t, cls_list);
                    }
                    field.setAccessible(false);
                }
                return t;
            }
            else
                return null;
        }
        catch (Exception e)
        {
           MyLogUtils.e(TAG, e.getMessage());
           return null;
        }
    }
    
    
    /**
     * 
     * List<Vo> vo->bean
     * @param json
     * @param list_key
     * @param class_t
     * @param cls_skey
     * @param cls_s
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static <T> List<T> jsonToMapVo(String json, String list_key, Class<T> class_t, String[] cls_skey, Class<?>[] cls_s)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(list_key))
            {
                JSONArray array_list = jsonObject.getJSONArray(list_key);
                List<T> mlist = new ArrayList<T>();
                for (int i = 0; array_list != null && i < array_list.length(); i++)
                {
                    T t = class_t.newInstance();
                    mlist.add(t);
                    JSONObject json_info = (JSONObject)array_list.get(i);
                    for (int j = 0; j < cls_skey.length; j++)
                    {
                        if (json_info.has(cls_skey[j]))
                        {
                            JSONObject json_cls = null;
                            try
                            {
                                json_cls = json_info.getJSONObject(cls_skey[j]);
                            }
                            catch (Exception e)
                            {
                                continue;
                            }
                            Object t_cls = jsonToBean(json_cls.toString(), cls_s[j]);
                            Field field_t = class_t.getDeclaredField(cls_skey[j]);
                            field_t.setAccessible(true);
                            field_t.set(t, t_cls);
                            field_t.setAccessible(false);
                        }
                    }
                }
                return mlist.size() == 0 ? null : mlist;
            }
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.toString());
        }
        
        return null;
    }
    
    
    /**
     * 
     * bean ->list,bean1
     * @param json
     * @param cls_vo
     * @param key_vo
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static <T> T jsonToBean(String json, Class<T> cls_vo, String key_vo)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(key_vo))
            {
                JSONObject json_vo = jsonObject.getJSONObject(key_vo);
                T t = cls_vo.newInstance();
                Field[] fs = cls_vo.getDeclaredFields();
                for (int i = 0; fs != null && i < fs.length; i++)
                {
                    Field field = fs[i];
                    String field_name = field.getName();
                    if (json_vo.has(field_name))
                    {
                        Type gType = field.getGenericType();
                        if (gType instanceof ParameterizedType)
                        {
                            ParameterizedType pType = (ParameterizedType)gType;
                            String pTypeName = pType.getActualTypeArguments()[0].toString();
                            MyLogUtils.i(TAG, "--------pType:" + pTypeName);
                            String list_cls = pTypeName.replace("class ", "");
                            List<?> mlist = jsonToList(json_vo.toString(), field_name, Class.forName(list_cls));
                            field.setAccessible(true);
                            setValue(field, t, mlist, "list");
                            field.setAccessible(false);
                        }
                        else
                        {
                            MyLogUtils.i(TAG, String.class.toString());
                            String type_str = field.getGenericType().toString();
                            if (type_str.equals(String.class.toString()))
                            {
                                Object value = valueToJson(json_vo.toString(), field_name);
                                field.setAccessible(true);
                                setValue(field, t, value, field.getGenericType().toString());
                                field.setAccessible(false);
                            }
                            else if (type_str.equals(Integer.class.toString()) || type_str.equals(int.class.toString()))
                            {
                                Object value = valueToJson(json_vo.toString(), field_name);
                                field.setAccessible(true);
                                setValue(field, t, value, field.getGenericType().toString());
                                field.setAccessible(false);
                            }
                            else if (type_str.equals(Float.class.toString()) || type_str.equals(float.class.toString()))
                            {
                                Object value = valueToJson(json_vo.toString(), field_name);
                                field.setAccessible(true);
                                setValue(field, t, value, field.getGenericType().toString());
                                field.setAccessible(false);
                            }
                            else if (type_str.equals(Boolean.class.toString()) || type_str.equals(boolean.class.toString()))
                            {
                                Object value = valueToJson(json_vo.toString(), field_name);
                                field.setAccessible(true);
                                setValue(field, t, value, field.getGenericType().toString());
                                field.setAccessible(false);
                            }
                            else
                            {
                                String json_bean = json_vo.get(field_name).toString();
                                String cls = gType.toString().replace("class ", "");
                                Class<?> c = Class.forName(cls);
                                Object value = jsonToBean(json_bean, c);
                                field.setAccessible(true);
                                field.set(t, value);
                                field.setAccessible(false);
                            }
                        }
                        
                    }
                }
                return t;
            }
            else
                return null;
                
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
        }
        return null;
    }
    
    public static <T>List<T> jsonArrayToApply(String json, Class<T> cls)
    {
        try
        {
            List<T> mlist = new ArrayList<T>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0 ;jsonArray!=null && i<jsonArray.length();i++)
            {
                String json_str = jsonArray.getString(i);
//                T t =  jsonToBean(json_str, cls);
                T t = JsonToObject(json_str, cls);
                if(t!=null)
                {
                    mlist.add(t);
                }
            }
            if(mlist!=null && mlist.size()>0)
                return mlist;
            else
                return null;
            
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T>List<T> jsonArrayToList(String json, Class<T> cls)
    {
        try
        {
            List<T> mlist = new ArrayList<T>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0 ;jsonArray!=null && i<jsonArray.length();i++)
            {
                String json_str = jsonArray.getString(i);
//                T t =  jsonToBean(json_str, cls);
                T t = JsonToObject(json_str, cls);
                if(t!=null)
                    mlist.add(t);
            }
            if(mlist!=null && mlist.size()>0)
                return mlist;
            else
                return null;
            
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getJsonArrayOk(String json)
    {
         try
        {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String result = jsonObject.getString("result");
            if(result.equals("faile"))
            {
                return false;
            }
            else if(result.equals("success"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (JSONException e)
        {
            return false;
        }
         
        
    }

    public static String[] getJsonForOpenfire(String content)
    {
        try
        {
            JSONObject json = new JSONObject(content);
            String[] str = new String[3];
            String title =  (String)json.get("title");
            String cont = json.getString("content");
            String type = json.getString("type");
            str[0] = title;
            str[1] = cont;
            str[2] = type;
            return str;
        }
        catch (JSONException e)
        {
           return null;
        }
    }

    
}
