package com.udit.frame.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.udit.frame.R;
import com.udit.frame.R.id;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * View Content 用到的工具类
 *
 * @author 曾宝
 * @version [V1.00, 2015-4-27]
 * @see [相关类/方法]
 * @since V1.00
 */
public class ViewUtils {
    private static final String TAG = ViewUtils.class.getSimpleName();


    /**
     * 初始化dialog
     * <功能详细描述>
     *
     * @param
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void initDialog(Dialog view, Object holder, Class ridClass) {
        // 获取holder 的class
        Class holderClass = holder.getClass();
        // 获取holder定义的属性
        Field[] objFields = holderClass.getDeclaredFields();
        // R.ID
        //  Class ridClass = R.id.class;

        Class viewClass = view.getClass();

        Method findViewByIdMethod = null;
        try {
            findViewByIdMethod = viewClass.getMethod("findViewById", int.class);
        } catch (SecurityException e) {// 权限
            e.printStackTrace();
        } catch (NoSuchMethodException e) {// 没有取到对应的方法
            e.printStackTrace();
        }

        for (Field objField : objFields) {
            Class fieldType = objField.getType();
            // 控件必须是view的子类
            if (View.class.isAssignableFrom(fieldType)) {
                try {
                    // 获取到R.ID 对应的属性 并获取ID
                    Field ridField = ridClass.getField(objField.getName());
                    int id = ridField.getInt("");
                    Object obj = findViewByIdMethod.invoke(view, id);
                    objField.setAccessible(true);
                    objField.set(holder, obj);
                } catch (SecurityException e) {// 权限
                    //  e.printStackTrace();
                    continue;
                } catch (NoSuchFieldException e) {// 没有获取到属性
                    //  e.printStackTrace();
                    continue;
                } catch (IllegalArgumentException e) {// 参数
                    //   e.printStackTrace();
                    continue;
                } catch (IllegalAccessException e) {// 权限
                    //  e.printStackTrace();
                    continue;
                } catch (InvocationTargetException e) {// 实例化的类不存在
                    // e.printStackTrace();
                    continue;
                }
            }
        }


    }

    /**
     * 根据图片名称 获取 图片Rid
     * <功能详细描述>
     *
     * @param pic
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static int getImage(String pic) {
        if (pic == null || pic.trim().equals("")) {
            return 0;
        }
        Class draw = R.drawable.class;
        try {
            Field field = draw.getDeclaredField(pic);
            return field.getInt(pic);
        } catch (SecurityException e) {
            return 0;
        } catch (NoSuchFieldException e) {
            return 0;
        } catch (IllegalArgumentException e) {
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }
    }

    /**
     * 初始化fragment 层的view
     * <功能详细描述>
     *
     * @param fragment
     * @param view
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void initView(Fragment fragment, View view, Class clsr)
            throws Exception {
        Class<? extends Fragment> fragmentClass = fragment.getClass();

        Class<id> rClass = clsr;

        Class viewClass = view.getClass();

        Method findViewId = null;

        try {
            findViewId = viewClass.getMethod("findViewById", int.class);
        } catch (NoSuchMethodException e) {
            throw new Exception("INITVIEW-findViewByIdMethod is err:" + e);
        }

        Field[] fragmentFields = fragmentClass.getDeclaredFields();

        for (int i = 0; fragmentFields != null && i < fragmentFields.length; i++) {
            // 获取类型
            Class fragmentType = fragmentFields[i].getType();
            if (View.class.isAssignableFrom(fragmentType)) {
                try {
                    // 根据属性名称 查找R.id 里面的属性
                    Field idField = rClass.getDeclaredField(fragmentFields[i].getName());
                    // MyLogUtils.i("TAG", fragmentFields[i].getName());
                    // 获取属性的值
                    int id = idField.getInt("");
                    // 将找到到的值 与activity的findViewById 方法绑定
                    Object obj = findViewId.invoke(view, id);
                    // 打开私有化赋值
                    fragmentFields[i].setAccessible(true);
                    // 绑定后的对象 跟对应的属性 绑定
                    fragmentFields[i].set(fragment, obj);
                    // 绑定后将私有化赋值关闭
                    fragmentFields[i].setAccessible(false);
                } catch (NoSuchFieldException e) {
                    continue;
                } catch (Exception e) {// 权限问题
                    throw new Exception("INITVIEW-fragmentFields is err:" + e);
                }
            }
        }

    }

    /**
     * 初始化 list 加载页面上的控件
     * <功能详细描述>
     *
     * @param --holder
     * @param view     ---list 对应的view
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void initHolderView(Object holder, View view, Class rid) {
        // 获取holder 的class
        Class holderClass = holder.getClass();
        // 获取holder定义的属性
        Field[] objFields = holderClass.getDeclaredFields();
        // R.ID
        Class ridClass = rid;//R.id.class;

        Class viewClass = view.getClass();

        Method findViewByIdMethod = null;
        try {
            findViewByIdMethod = viewClass.getMethod("findViewById", int.class);
        } catch (SecurityException e) {// 权限
            e.printStackTrace();
            MyLogUtils.e(TAG, "权限有问题" + e.getMessage());
        } catch (NoSuchMethodException e) {// 没有取到对应的方法
            e.printStackTrace();
            MyLogUtils.e(TAG, "没有取到对应的方法" + e.getMessage());
        }

        for (Field objField : objFields) {
            Class fieldType = objField.getType();
            // 控件必须是view的子类
            if (View.class.isAssignableFrom(fieldType)) {
                try {
                    // 获取到R.ID 对应的属性 并获取ID
                    Field ridField = ridClass.getField(objField.getName());
                    int id = ridField.getInt("");
                    Object obj = findViewByIdMethod.invoke(view, id);
                    objField.setAccessible(true);
                    objField.set(holder, obj);
                } catch (SecurityException e) {// 权限
                    //e.printStackTrace();
                    // MyLogUtils.e(TAG," 权限"+e.getMessage());
                    continue;
                } catch (NoSuchFieldException e) {// 没有获取到属性
                    // e.printStackTrace();
                    // MyLogUtils.e(TAG,"没有获取到属性"+e.getMessage());
                    continue;
                } catch (IllegalArgumentException e) {// 参数
                    //e.printStackTrace();
                    // MyLogUtils.e(TAG,"参数"+e.getMessage());
                    continue;
                } catch (IllegalAccessException e) {// 权限
                    // e.printStackTrace();
                    //  MyLogUtils.e(TAG,"权限"+e.getMessage());
                    continue;
                } catch (InvocationTargetException e) {// 实例化的类不存在
                    // e.printStackTrace();
                    // MyLogUtils.e(TAG,"实例化的类不存在"+e.getMessage());
                    continue;
                }
            }
        }

    }

    /**
     * 初始化界面Activity
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public static void initView(Activity activity, Class cls_r) {
        // 获取Activity 的类
        Class<? extends Activity> activityClass = activity.getClass();
        // r.id 类
        Class<id> idClass = cls_r;//R.id.class;

        Method findViewByIdMethod = null;
        try {
            findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
        } catch (Exception e) {
            MyLogUtils.e(TAG, "INITVIEW-findViewByIdMethod is err:" + e);
        }

        Field[] activityFields = activityClass.getDeclaredFields();

        for (Field activityField : activityFields) {
            // 获取类型
            Class<?> activityType = activityField.getType();
            // 继承View的类
            if (View.class.isAssignableFrom(activityType)) {
                try {
                    // 根据属性名称 查找R.id 里面的属性
                    Field idField = idClass.getDeclaredField(activityField.getName());
                    // 获取属性的值
                    int id = idField.getInt("");
                    // 将找到到的值 与activity的findViewById 方法绑定
                    Object obj = findViewByIdMethod.invoke(activity, id);
                    // 打开私有化赋值
                    activityField.setAccessible(true);
                    // 绑定后的对象 跟对应的属性 绑定
                    activityField.set(activity, obj);
                    // 绑定后将私有化赋值关闭
                    activityField.setAccessible(false);
                } catch (NoSuchFieldException e) {
                    continue;
                } catch (Exception e) {// 权限问题
                    MyLogUtils.e(TAG, "INITVIEW-activityFields is err:" + e);
                }

            }

        }

    }


    /**
     * 展开
     * <功能详细描述>
     *
     * @param adapter
     * @param listView
     * @see [类、类#方法、类#成员]
     */
    public static void expandGroup(BaseExpandableListAdapter adapter, ExpandableListView listView) {
        for (int i = 0; adapter != null && i < adapter.getGroupTypeCount(); i++) {
            listView.expandGroup(i);
        }
    }

    /**
     * 设置ExpandablelistView 在scrollView 适配大小
     * <功能详细描述>
     *
     * @param fragment_home_business_info_layout2
     * @see [类、类#方法、类#成员]
     */
    public static void setListViewHeightBasedOnChildren(ExpandableListView fragment_home_business_info_layout2) {
        ListAdapter adapter = fragment_home_business_info_layout2.getAdapter();
        if (adapter == null)
            return;

        int totalHeight = 0;

        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, fragment_home_business_info_layout2);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();

        }
        ViewGroup.LayoutParams params = fragment_home_business_info_layout2.getLayoutParams();
        params.height = totalHeight + (fragment_home_business_info_layout2.getDividerHeight() * (fragment_home_business_info_layout2.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        fragment_home_business_info_layout2.setLayoutParams(params);

    }

    public static void setListViewHeightBasedOnChildrenAndParent(ListView list) {
        ListAdapter adapter = list.getAdapter();
        if (adapter == null)
            return;

        int totalHeight = 0;

        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, list);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();

            if (listItem instanceof ListView) {
                setListViewHeightBasedOnChildrenAndParent((ListView) listItem);
            }

        }
        ViewGroup.LayoutParams params = list.getLayoutParams();
        params.height = totalHeight + (list.getDividerHeight() * (list.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        list.setLayoutParams(params);

    }

    public static void setListViewHeightBasedOnChildren(ListView list) {
        ListAdapter adapter = list.getAdapter();
        if (adapter == null)
            return;

        int totalHeight = 0;

        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, list);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();

        }
        ViewGroup.LayoutParams params = list.getLayoutParams();
        params.height = totalHeight + (list.getDividerHeight() * (list.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        list.setLayoutParams(params);

    }
}
