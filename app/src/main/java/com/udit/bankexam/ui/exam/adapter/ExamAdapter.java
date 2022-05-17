package com.udit.bankexam.ui.exam.adapter;

import java.util.ArrayList;
import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamHistoryBean;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.Constant.ExamType;
import com.udit.bankexam.ui.exam.holder.HolderExam;
import com.udit.bankexam.ui.exam.holder.HolderOption;
import com.udit.bankexam.ui.exam.holder.HolderWindow;
import com.udit.bankexam.utils.FontUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.frame.common.flowlayout.FlowLayout;
import com.udit.frame.common.texthtml.HtmlTextView;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExamAdapter extends PagerAdapter
{
    private final String TAG = this.getClass().getSimpleName();
    
    private List<ExamBean> mlist;
    
    private Context mContext;
    
    // 每个item的页面view
    private ArrayList<View> mlist_remove;

    private SparseArray<View> mlist_add;
    
    private View convertView;

    private ExamHistoryBean examHistoryBean;

    private HolderExam.ExamOptionsSelected examOptionsSelected;

    private HolderWindow.TextSize size;


    public void setSize(HolderWindow.TextSize size) {
        this.size = size;
    }

    /**
     * <默认构造函数>
     */
    public ExamAdapter(Context mContext, List<ExamBean> mlist, ExamHistoryBean bean,HolderExam.ExamOptionsSelected examOptionsSelected)
    {
        super();
        this.mlist = mlist;
        this.mContext = mContext;
        this.examHistoryBean = bean;
        this.examOptionsSelected = examOptionsSelected;


        this.mlist_remove = new ArrayList<>();
        this.mlist_add= new SparseArray<>();
      /*  for(int i = 0;i<5;i++)
        {
            HolderExam holderexam = new HolderExam(mContext,  mlist.size(),examOptionsSelected,examHistoryBean);
            View  view = holderexam.getMain_view();
            mlist_view.add(view);

        }*/



    }



    @Override
    public int getCount()
    {
        return mlist.size();
    }
    
    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }
    
    public long getItemId(int position)
    {
        return position;
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
      //  MyLogUtils.e(TAG,"正在销毁："+position);
        View view = (View)object;
       // View view =  mlist_view.get((position) % mlist_view.size());
        container.removeView(view);
      //  mlist_add.remove(position);//删除页面不使用的View  
        mlist_remove.add(view);//

    }
    
    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
     //   MyLogUtils.e(TAG,"正在创建："+position);

        View view = null;

        if(mlist_remove.size()>0)
        {
           // view = mlist_remove.get(0);
            view = mlist_remove.remove(0);
        }
        view =getView(container,view,position);
      //  mlist_add.put(position,view);
        container.addView(view);

        return view;
    }

    private View getView(ViewGroup container,View view, int position)
    {
        HolderExam holderexam = null;
        if(view==null)
        {
            holderexam = new HolderExam(mContext,mlist.size(),examOptionsSelected,examHistoryBean);
            view = holderexam.getMain_view();

        }
        else
        {
            holderexam = (HolderExam) view.getTag();
        }

        ExamBean bean = mlist.get(position);
        MyLogUtils.e(TAG,"POSTION:"+position+" bean:"+bean.toString());
        holderexam.initView(position,bean);
        initExamTheme(holderexam,view);
        initTextSize(size,view);
        FontUtils.injectFont(view);
        return view;
    }



    public void initTextSize(HolderWindow.TextSize size,View view)
    {
        HolderExam exam_holder = (HolderExam) view.getTag();
        exam_holder.text_exam_material.setTextSize(size.getSize());
        exam_holder.text_exam.setTextSize(size.getSize());
        for(int j= 0; exam_holder.getMlist_options()!=null
                && j<exam_holder.getMlist_options().size();j++)
        {
            HolderOption holderOption = (HolderOption) exam_holder.getMlist_options().get(j).getTag();
            holderOption.text_options_index.setTextSize(size.getSize());
            holderOption.text_options.setTextSize(size.getSize());
        }
    }


    public void initExamTheme(HolderExam holderExam,View view) {
        //for(int i = 0;i<viewpager_exam.getChildCount();i++)
        //{
            // / for (int i = 0; mlist_view != null && i < mlist_view.size(); i++) {
        //    View view = viewpager_exam.getChildAt(i);
            view.setBackgroundColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext,
                    R.color.color_exam_black)));
            HolderExam holder_exam = (HolderExam) view.getTag();

            holder_exam.text_exam_qtype.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_background_exam_tixing)));
            holder_exam.text_exam_qtype.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_point)));


            holder_exam.ll_exam_content.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));


            holder_exam.text_exam_content.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_exam_qpoint.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_current.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_max.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));

            holder_exam.text_exam.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_exam.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

            for (int j = 0; holder_exam.getMlist_options() != null
                    && j < holder_exam.getMlist_options().size(); j++) {
                View view_option = holder_exam.getMlist_options().get(j);
                HolderOption holderOption = (HolderOption) view_option.getTag();


                view_option.setBackgroundColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_back)));

                holderOption.ll_options.setBackground(mContext.getResources()
                        .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));
                holderOption.text_options_index.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_option)));
                holderOption.text_options.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_option)));

        }

    }



}
