package com.udit.frame.common.mygridView;


import com.udit.frame.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MyGridView extends TableLayout
{
    // private static final String tag = "AdvancedGridView";
    private int rowNum = 0; // row number
    
    private int colNum = 0; // col number
    
    private BaseAdapter adapter = null;
    
    private Context context = null;
    
    public MyGridView(Context context)
    {
        super(context);
        initThis(context, null);
    }
    
    public MyGridView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initThis(context, attrs);
    }
    
    @SuppressLint("ResourceAsColor")
    @SuppressWarnings("deprecation")
    private void initThis(Context context, AttributeSet attrs)
    {
        this.context = context;
        if (this.getTag() != null)
        {
            String atb = (String)this.getTag();
            int ix = atb.indexOf(',');
            if (ix > 0)
            {
                rowNum = Integer.parseInt(atb.substring(0, ix));
                colNum = Integer.parseInt(atb.substring(ix + 1, atb.length()));
            }
        }
        if (rowNum <= 0)
            rowNum = 3;
        if (colNum <= 0)
            colNum = 3;
        if (this.isInEditMode())
        {
            this.removeAllViews();
            for (int y = 0; y < rowNum; ++y)
            {
                TableRow row = new TableRow(context);
                row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 1.0f));
                for (int x = 0; x < colNum; ++x)
                {
                    View button = new Button(context);
                    row.addView(button, new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f));
                }
                this.addView(row);
            }
        }
    }
    
    public BaseAdapter getAdapter()
    {
        return adapter;
    }
    
    @SuppressLint("ResourceAsColor")
    @SuppressWarnings("deprecation")
    public void setAdapter(BaseAdapter adapter)
    {
        if (adapter != null)
        {
            if (adapter.getCount() < this.rowNum * this.colNum)
            {
                throw new IllegalArgumentException("The view count of adapter is less than this gridview's items");
            }
            this.removeAllViews();
            for (int y = 0; y < rowNum; ++y)
            {
                TableRow row = new TableRow(context);
                row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 1.0f));
                for (int x = 0; x < colNum; ++x)
                {
                    View view = adapter.getView(y * colNum + x, this, row);
                    row.addView(view, new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f));
                    
                    if (x != colNum - 1)
                    {
                        View view1 = new View(context);
                        view1.setBackgroundColor(R.color.app_bg);
                        row.addView(view1, new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.01f));
                    }
                }
                this.addView(row);
              /*  if (y != rowNum - 1)
                {
                    TableRow row1 = new TableRow(context);
                    row1.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.01f));
                    View view1 = new View(context);
                    view1.setBackgroundColor(R.color.home_back);
                    row1.addView(view1, new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.01f));
                    View view1 = new View(context);
                    view1.setBackgroundColor(R.color.home_back);
                    row1.addView(view1, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));
                    this.addView(row1);
                }*/
              
            }
        }
        this.adapter = adapter;
    }
    
    public int getRowNum()
    {
        return rowNum;
    }
    
    public void setRowNum(int rowNum)
    {
        this.rowNum = rowNum;
    }
    
    public int getColNum()
    {
        return colNum;
    }
    
    public void setColNum(int colNum)
    {
        this.colNum = colNum;
    }
}