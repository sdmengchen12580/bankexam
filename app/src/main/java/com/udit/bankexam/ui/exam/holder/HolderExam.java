package com.udit.bankexam.ui.exam.holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamHistoryBean;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.utils.FontUtils;
import com.udit.bankexam.utils.URLImageGetter;
import com.udit.frame.common.flowlayout.FlowLayout;
import com.udit.frame.common.texthtml.HtmlTextView;
import com.udit.frame.common.view.MTextView;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.NoDoubleClick;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 练习题目，非 资料题目
 */
public class HolderExam {

    public HtmlTextView text_exam_material;

    public TextView text_num_current;

    public TextView text_num_;

    public TextView text_num_max;

    public TextView text_exam_qtype;

    public TextView text_exam_content;

    public HtmlTextView text_exam;

    public TextView text_exam_qpoint;

    public LinearLayout ll_study_viewpager;

    public  LinearLayout ll_exam_content;

    public  LinearLayout flow_options;

    private View main_view;

    //private ExamBean bean_exam;

    private List<View> mlist_options;

    private Context mContext;

   // private int postion;

    private int max;

    private LinearLayout ll_jiexi;

    private ExamOptionsSelected examOptionsSelected;

    private LinearLayout.LayoutParams params_margin;

    private ExamHistoryBean beanHistory;


    public HolderExam( Context mContext,
                      int max, ExamOptionsSelected examOptionsSelected,
                      ExamHistoryBean examHistoryBean) {
        //this.bean_exam = bean_exam;
        this.mContext = mContext;
      //  this.postion = postion;
        this.max = max;
        this.examOptionsSelected = examOptionsSelected;
        this.beanHistory = examHistoryBean;
        params_margin = new LinearLayout.LayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params_margin.topMargin = 20;
        params_margin.bottomMargin = 20;
        mlist_options = new ArrayList<>();
        init();
    }

    public boolean flag_show = false;
    public List<View> getMlist_options() {
        return mlist_options;
    }


    public View getMain_view() {
        return main_view;
    }

    private void init()
    {
        main_view = LayoutInflater.from(mContext).inflate(R.layout.item_study_ziliao_viewpage, null);

        ViewUtils.initHolderView(this, main_view, R.id.class);

        //FontUtils.injectFont(main_view);
        flag_show = false;
        main_view.setTag(this);
    }

    public void initView(int postion,ExamBean bean_exam) {

        flag_show = true;
        if (bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDUOXT)
                || bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDXT)) {

            String material = bean_exam.getMaterial();
            text_exam_material.setHtmlFromString(material,false,true);
           // text_exam_material.setText(Html.fromHtml(material,new URLImageGetter(material,mContext,text_exam_material,MyApplication.user_options),null));
            text_exam_material.setVisibility(View.VISIBLE);
        } else {
            text_exam_material.setVisibility(View.GONE);
        }

        text_exam_qtype.setText(bean_exam.getQType());
        text_exam_qtype.setVisibility(View.VISIBLE);
        if (!MyCheckUtils.isEmpty(bean_exam.getContent())) {
            text_exam_content.setText(bean_exam.getContent());
        }
        if (!MyCheckUtils.isEmpty(bean_exam.getQPoint())) {
            text_exam_qpoint.setText("(" + bean_exam.getQPoint() + ")");
        }

        text_num_current.setText((postion + 1) + "");
        text_num_max.setText(max + "");
        String title =bean_exam.getTitle();
       // text_exam.setText(Html.fromHtml(title,new URLImageGetter(title,mContext,text_exam, MyApplication.user_options),null));

        text_exam.setHtmlFromString(title,false,true);
        List<ExamOptionBean> list_options = bean_exam.getList_TitleList();
        flow_options.removeAllViews();

        setFlow(bean_exam,postion,list_options);

        ll_jiexi.setVisibility(View.GONE);


    }





    private void setFlow(final ExamBean bean_exam,final int position,List<ExamOptionBean> list_options)
    {
        for (int i = 0; i < list_options.size(); i++)
        {
            final ExamOptionBean bean_option = list_options.get(i);
            final HolderOption holder_option = new HolderOption(bean_option,mContext);
            final int postion_exam = i;

            holder_option.getmView_main().setOnClickListener(new NoDoubleClick() {
                @Override
                protected void onNoDoubleClick(View v) {
                    {
                        if(holder_option.getmView_main().isSelected())
                        {
                            holder_option.getmView_main().setSelected(false);
                        }
                        else
                        {

                            if(bean_exam.getQType().equals(Constant.ExamType.TYPE_DXT)
                                    || bean_exam.getQType().equals(Constant.ExamType.TYPE_XLDXT)
                                    || bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDXT))
                            {
                                for(int j = 0;j<flow_options.getChildCount();j++)
                                {
                                    flow_options.getChildAt(j).setSelected(false);
                                }
                                //   main_view.setEnabled(false);
                            }
                            else if(bean_exam.getQType().equals(Constant.ExamType.TYPE_DUOXT)
                                    || bean_exam.getQType().equals(Constant.ExamType.TYPE_XLDUOXT)
                                    || bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDUOXT))
                            {

                            }
                            holder_option.getmView_main().setSelected(true);
                        }

                        examOptionsSelected.ExamOptionClick(position,postion_exam);
                    }
                }
            });

           /* holder_option.getmView_main().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder_option.getmView_main().isSelected())
                    {
                        holder_option.getmView_main().setSelected(false);
                    }
                    else
                    {

                        if(bean_exam.getQType().equals(Constant.ExamType.TYPE_DXT)
                                || bean_exam.getQType().equals(Constant.ExamType.TYPE_XLDXT)
                                || bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDXT))
                        {
                            for(int j = 0;j<flow_options.getChildCount();j++)
                            {
                                flow_options.getChildAt(j).setSelected(false);
                            }
                         //   main_view.setEnabled(false);
                        }
                        else if(bean_exam.getQType().equals(Constant.ExamType.TYPE_DUOXT)
                                || bean_exam.getQType().equals(Constant.ExamType.TYPE_XLDUOXT)
                                || bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDUOXT))
                        {

                        }
                        holder_option.getmView_main().setSelected(true);
                    }

                    examOptionsSelected.ExamOptionClick(position,postion_exam);
                }
            });*/
            /*if(beanHistory.isFlag_cl())
            {*/
                String answer = bean_exam.getAnswer();
            String type = bean_exam.getQType();
            if(type.equals(Constant.ExamType.TYPE_DXT)
                    || type.equals(Constant.ExamType.TYPE_XLDXT)
                    || type.equals(Constant.ExamType.TYPE_ZLDXT))
            {
                if(answer.equals(bean_option.getSingle()) )
                {
                    holder_option.getmView_main().setSelected(true);
                    // examOptionsSelected.ExamOptionClick(holder_option.getmView_main(),position,postion_exam,bean_exam,bean_option);
                }
            }
            else if(type.equals(Constant.ExamType.TYPE_DUOXT)
                    || type.equals(Constant.ExamType.TYPE_XLDUOXT)
                    || type.equals(Constant.ExamType.TYPE_ZLDUOXT))
            {
                if(answer.contains(bean_option.getSingle()))
                {
                    holder_option.getmView_main().setSelected(true);
                    // examOptionsSelected.ExamOptionClick(holder_option.getmView_main(),position,postion_exam,bean_exam,bean_option);
                }
            }


            /*}*/
            flow_options.addView(holder_option.getmView_main(), params_margin);
            mlist_options.add(holder_option.getmView_main());
        }
    }

    public interface ExamOptionsSelected
    {
        public  void ExamOptionClick(int postion_exam,int postions);

    }

}
