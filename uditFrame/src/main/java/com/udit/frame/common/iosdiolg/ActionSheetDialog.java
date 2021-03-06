package com.udit.frame.common.iosdiolg;

import java.util.ArrayList;
import java.util.List;

import com.udit.frame.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ActionSheetDialog
{
    private Context context;
    
    private Dialog dialog;
    
    private TextView txt_title;
    
    private TextView txt_cancel;
    
    private LinearLayout lLayout_content;
    
    private ScrollView sLayout_content;
    
    private boolean showTitle = false;
    
    private List<SheetItem> sheetItemList;
    
    private List<SheetItemView> sheetItemViewList;
    
    private Display display;
    
    public ActionSheetDialog(Context context)
    {
        this.context = context;
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }
    
    @SuppressLint("RtlHardcoded")
    @SuppressWarnings("deprecation")
    public ActionSheetDialog builder()
    {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_actionsheet, null);
        
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());
        
        // 获取自定义Dialog布局中的控件
        sLayout_content = (ScrollView)view.findViewById(R.id.sLayout_content);
        lLayout_content = (LinearLayout)view.findViewById(R.id.lLayout_content);
        txt_title = (TextView)view.findViewById(R.id.txt_title);
        txt_cancel = (TextView)view.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        
        return this;
    }
    
    
    
    public ActionSheetDialog setTitle(String title)
    {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }
    
    public ActionSheetDialog setCancelable(boolean cancel)
    {
        dialog.setCancelable(cancel);
        return this;
    }
    
    public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel)
    {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }
    
    /**
     * 
     * @param strItem
     *            条目名称
     * @param color
     *            条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public ActionSheetDialog addSheetItem(String strItem, SheetItemColor color, OnSheetItemClickListener listener)
    {
        if (sheetItemList == null)
        {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }
    
    public ActionSheetDialog addSheetItem(View textview, SheetItemColor color, OnSheetItemClickListener listener)
    {
        if (sheetItemViewList == null)
        {
            sheetItemViewList = new ArrayList<SheetItemView>();
        }
        sheetItemViewList.add(new SheetItemView(textview, listener,color));
        return this;
    }
    
    /** 设置条目布局 */
    @SuppressWarnings("deprecation")
    private void setSheetItems()
    {
        if (sheetItemList == null || sheetItemList.size() <= 0)
        {
            return;
        }
        
        int size = sheetItemList.size();
        
        // TODO 高度控制，非最佳解决办法
        // 添加条目过多的时候控制高度
        if (size >= 7)
        {
            LayoutParams params = (LayoutParams)sLayout_content.getLayoutParams();
            params.height = display.getHeight() / 2;
            sLayout_content.setLayoutParams(params);
        }
        
        // 循环添加条目
        for (int i = 1; i <= size; i++)
        {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            SheetItemColor color = sheetItem.color;
            final OnSheetItemClickListener listener = (OnSheetItemClickListener)sheetItem.itemClickListener;
            
            TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER);
            
            // 背景图片
            if (size == 1)
            {
                if (showTitle)
                {
                    textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                }
                else
                {
                    textView.setBackgroundResource(R.drawable.actionsheet_single_selector);
                }
            }
            else
            {
                if (showTitle)
                {
                    if (i >= 1 && i < size)
                    {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    }
                    else
                    {
                        textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    }
                }
                else
                {
                    if (i == 1)
                    {
                        textView.setBackgroundResource(R.drawable.actionsheet_top_selector);
                    }
                    else if (i < size)
                    {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    }
                    else
                    {
                        textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    }
                }
            }
            
            // 字体颜色
            if (color == null)
            {
                textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
            }
            else
            {
                textView.setTextColor(Color.parseColor(color.getName()));
            }
            
            // 高度
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int)(45 * scale + 0.5f);
            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
            
            // 点击事件
            textView.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onClick(index);
                    dialog.dismiss();
                }
            });
            
            lLayout_content.addView(textView);
        }
    }
    
    
    
    @SuppressWarnings({"deprecation", "unused"})
    private void setSheetViewItems()
    {
        if (sheetItemViewList == null || sheetItemViewList.size() <= 0)
        {
            return;
        }
        
        int size = sheetItemViewList.size();
        
        // TODO 高度控制，非最佳解决办法
        // 添加条目过多的时候控制高度
        if (size >= 7)
        {
            LayoutParams params = (LayoutParams)sLayout_content.getLayoutParams();
            params.height = display.getHeight() / 2;
            sLayout_content.setLayoutParams(params);
        }
        
        // 循环添加条目
        for (int i = 1; i <= size; i++)
        {
            final int index = i;
            SheetItemView sheetItem = sheetItemViewList.get(i - 1);
            final View strItem = sheetItem.view;
            SheetItemColor color = sheetItem.color;
            final OnSheetItemClickListener listener = (OnSheetItemClickListener)sheetItem.itemClickListener;
            
          
            if (size == 1)
            {
                if (showTitle)
                {
                    strItem.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                }
                else
                {
                    strItem.setBackgroundResource(R.drawable.actionsheet_single_selector);
                }
            }
            else
            {
                if (showTitle)
                {
                    if(strItem instanceof Button)
                    {
                        strItem.setBackgroundResource(R.drawable.actionsheet_single_selector);
                    }
                    else
                    {
                        if (i >= 1 && i < size)
                        {
                            strItem.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                        }
                        else
                        {
                            strItem.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                        }
                    }
                }
                else
                {
                    if (i == 1)
                    {
                        strItem.setBackgroundResource(R.drawable.actionsheet_top_selector);
                    }
                    else if (i < size)
                    {
                        strItem.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    }
                    else
                    {
                        strItem.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    }
                }
            }
            // 高度
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int)(45 * scale + 0.5f);
            strItem.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
            
            // 点击事件
            strItem.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onClick(index);
                    if(strItem instanceof EditText)
                    {
                        
                    }
                    else
                    {
                        dialog.dismiss();
                    }
                }
            });
            
            lLayout_content.addView(strItem);
        }
    }
    
    
    public void show()
    {
        setSheetItems();
        dialog.show();
    }
    
    public void showUI()
    {
        dialog.show();
    }
    
    public void showView()
    {
        setSheetViewItems();
        dialog.show();
    }
    
    public interface OnSheetItemClickListener
    {
        void onClick(int which);
    }
    
    public class SheetItemView
    {
        View view;
        
        OnSheetItemClickListener itemClickListener;
        
        SheetItemColor color;

        public SheetItemView(View view, OnSheetItemClickListener itemClickListener, SheetItemColor color)
        {
            super();
            this.view = view;
            this.itemClickListener = itemClickListener;
            this.color = color;
        }
        
        
    }
    
    public class SheetItem
    {
        String name;
        
        OnSheetItemClickListener itemClickListener;
        
        SheetItemColor color;
        
        public SheetItem(String name, SheetItemColor color, OnSheetItemClickListener itemClickListener)
        {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }
    
    public enum SheetItemColor
    {
        Blue("#037BFF"), Red("#FD4A2E");
        
        private String name;
        
        private SheetItemColor(String name)
        {
            this.name = name;
        }
        
        public String getName()
        {
            return name;
        }
        
        public void setName(String name)
        {
            this.name = name;
        }
    }
}
