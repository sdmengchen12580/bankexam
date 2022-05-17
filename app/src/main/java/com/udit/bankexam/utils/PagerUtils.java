package com.udit.bankexam.utils;

import android.widget.TextView;

import com.udit.frame.utils.MyLogUtils;

/**
 * Created by zb on 2017/6/22.
 */

public class PagerUtils {

    public static int last(int postion, int max_length, TextView text_exam_next)
    {
        postion--;
        if(postion<max_length-1)
        {
            text_exam_next.setText("下一题");
        }

        if(postion<=-1)
        {
            //showLongToast("已经到第一题");
            return 0;
        }
        return postion;
    }

    public static int next(int postion,int max_length,TextView text_exam_next,boolean flag)
    {
        if(postion==max_length-2)
        {
            if(flag)
                 text_exam_next.setText("提交答案");
        }
        else
        {

            text_exam_next.setText("下一题");

        }
        if(postion>=max_length)
            return max_length-1;

        postion++;
        if(postion>=max_length)
            return max_length-1;

        return postion;


    }

}
