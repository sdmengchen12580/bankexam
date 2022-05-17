package com.udit.bankexam.ui.exam.holder;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.SolutionBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.frame.common.circleImageView.CircleImageView;
import com.udit.frame.common.flowlayout.FlowLayout;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zb on 2017/5/10.
 */

public class HolderSolution implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private RelativeLayout rl_solution;

    private RelativeLayout rl_solution_bottom;

    private ImageView img_top_solution_return;

    private TextView text_top_solution_centent;

    private TextView text_top_solution_time;

    private LinearLayout ll_solution_title;

    private TextView text_exam_content, text_exam_qpoint;

    private View view_solution_item;

    private ScrollView scroll_solution;

    private FlowLayout flow_solution;

    private TextView text_solution_btn;

    private Dialog dialog_solution;

    private int width_solution;

    private ViewGroup.MarginLayoutParams params_margin_solution;

    private List<View> mlist_solution;

    // private List<SolutionBean> mlist_bean;

    private List<ExamBean> mlist_exam;


    private Context mContext;

    private View mView_main;

    private String title;

    private MySolutionListener listener;


    public TextView getText_top_solution_time() {
        return text_top_solution_time;
    }

    public HolderSolution(Context mContext, String title, MySolutionListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        this.title = title;
        //  this.mlist_bean = new ArrayList<>();
        this.mlist_solution = new ArrayList<>();
        width_solution = mContext.getResources().getDisplayMetrics().widthPixels;

        width_solution = width_solution / 5;

        params_margin_solution = new ViewGroup.MarginLayoutParams(width_solution, width_solution);

        init();

    }

    public List<ExamBean> getMlist_exam() {
        return mlist_exam;
    }

    public void initThme() {
        rl_solution.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_top_rl)));

        rl_solution_bottom.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_bottom_rl)));


        text_solution_btn.setBackgroundDrawable(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.shape_20_corner_558cf4_bg)));//fixme 临时修改


        text_solution_btn.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_solution_btn)));


        img_top_solution_return.setImageDrawable(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.backhevron)));

        text_top_solution_centent.setTextColor(mContext.getResources().
                getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_top_text)));

        text_top_solution_time.setTextColor(mContext.getResources().
                getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_top_text)));

        view_solution_item.setBackgroundColor(mContext.getResources().
                getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_top_text)));


        ll_solution_title.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));

        text_exam_content.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_point)));

        scroll_solution.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));

        //总体
        flow_solution.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));


        for (int i = 0; mlist_solution != null && i < mlist_solution.size(); i++) {
            HolderSolutionItem holderSolutionItem = (HolderSolutionItem) mlist_solution.get(i).getTag();

//            holderSolutionItem.img_item_solution_back.setImageDrawable(
//                    mContext.getResources().getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_solution)));
            ImageView imageView = holderSolutionItem.img_item_solution_back;
            Resources resources = this.mContext.getResources();
            if (holderSolutionItem.text_solution_num.isSelected()) {
                i = R.mipmap.img_blue;
            } else {
                i = R.mipmap.img_efefef;
            }
            imageView.setImageDrawable(resources.getDrawable(i));

            holderSolutionItem.text_solution_num.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.drawable.shape_text_select)));
            holderSolutionItem.rl_solution_item.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));

        }


    }

    public Dialog getDialog_solution() {
        return dialog_solution;
    }

    public void setDXSolution(int postion, String id, String score, String usetime, String answer, String sigle) {

        View view = mlist_solution.get(postion);
//        SolutionBean bean_cureent = mlist_bean.get(postion);
        ExamBean bean_current = mlist_exam.get(postion);
       /* if(bean_current==null)
        {
            bean_current = new SolutionBean();
            mlist_bean.set(postion,bean_cureent);
        }*/
//        bean_cureent.setID(id);
      /*  bean_cureent.setAnswer(answer);
        bean_cureent.setATime(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
        bean_cureent.setUserTime(usetime);*/

        bean_current.setAnswer(answer);
        bean_current.setATime(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
        bean_current.setUserTime(usetime);

        if (MyCheckUtils.checkStr(sigle, answer)) {
            bean_current.setIsOK(Constant.ExamData.EXAM_OK);
            bean_current.setGetScore(score);
          /*  bean_cureent.setIsOK(Constant.ExamData.EXAM_OK);
            bean_cureent.setGetScore(score);*/
        } else {
            bean_current.setIsOK(Constant.ExamData.EXAM_ERR);
            bean_current.setGetScore("0");
            /*bean_cureent.setIsOK(Constant.ExamData.EXAM_ERR);
            bean_cureent.setGetScore("0");*/
        }
        ExamUtils.insertExam(bean_current);
        view.setSelected(true);
        MyLogUtils.e(TAG, mlist_exam.size() + " " + bean_current.toString());
    }

    public void biaoji(int postion) {
        ExamBean bean = mlist_exam.get(postion);

        if (bean.isFlag_biaoji()) {
            View mView = mlist_solution.get(postion);
            HolderSolutionItem item = (HolderSolutionItem) mView.getTag();
            item.img_solution_biaoji.setVisibility(View.VISIBLE);
        }
    }

    public void setDuoXSolution(boolean flag_add, int postion, String id, String score, String usetime,
                                String answer, String sigle) {
        ExamBean bean_current = mlist_exam.get(postion);
//        SolutionBean bean_current = mlist_bean.get(postion);
        View view = mlist_solution.get(postion);
        /*if(bean_current==null)
        {
            bean_current = new SolutionBean();
            mlist_bean.set(postion,bean_current);
        }*/

        bean_current.setID(id);

        bean_current.setATime(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
        bean_current.setUserTime(usetime);

        bean_current.setIsOK(Constant.ExamData.EXAM_ERR);
        if (flag_add) {//如果是添加
            if (MyCheckUtils.isEmpty(bean_current.getAnswer())) {
                bean_current.setAnswer(answer);
            } else {
                bean_current.setAnswer(answer + bean_current.getAnswer());
            }
            view.setSelected(true);
            if (MyCheckUtils.checkStr(sigle, bean_current.getAnswer())) {
                bean_current.setIsOK(Constant.ExamData.EXAM_OK);
                bean_current.setGetScore(score);
            } else {
                bean_current.setIsOK(Constant.ExamData.EXAM_ERR);
                bean_current.setGetScore("0");
            }
        } else {//删除
            String answer_all = bean_current.getAnswer();
            String answer_delete = answer_all.replace(answer, "");
          /*  if(MyCheckUtils.isEmpty(answer_delete))
            {
                mlist_exam.set(postion)
                mlist_bean.set(postion,new SolutionBean());
                view.setSelected(false);
            }
            else
            {*/
            bean_current.setAnswer(answer_delete);
            if (MyCheckUtils.checkStr(sigle, bean_current.getAnswer())) {
                bean_current.setIsOK(Constant.ExamData.EXAM_OK);
                bean_current.setGetScore(score);
            } else {
                bean_current.setIsOK(Constant.ExamData.EXAM_ERR);
                bean_current.setGetScore("0");
            }
            view.setSelected(true);
            /*}*/
        }
        ExamUtils.insertExam(bean_current);
        MyLogUtils.e(TAG, mlist_exam.size() + ":" + bean_current.toString());
    }

    public void deleteSolution(int postion, String id) {

        View view = mlist_solution.get(postion);
//        mlist_bean.set(postion,new SolutionBean());
        ExamBean bean = mlist_exam.get(postion);
        bean.setATime("");
        bean.setUserTime("");
        bean.setIsOK("");
        bean.setAnswer("");
        view.setSelected(false);
        ExamUtils.insertExam(bean);
        MyLogUtils.e(TAG, mlist_exam.size() + " " + bean.toString());
    }

    public void setMlist_exam(List<ExamBean> list) {
        try {
            this.mlist_exam = list;
            if (this.flow_solution.getChildCount() > 0)
                this.flow_solution.removeAllViews();
        } catch (Exception paramList) {
            return;
        }
        for (int i = 0; mlist_exam != null && i < mlist_exam.size(); i++) {
            ExamBean bean = mlist_exam.get(i);

            //加载答题卡
            View view_solution = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_solution, null);

           /* SolutionBean bean_solution = new SolutionBean();
            mlist_bean.add(bean_solution);*/

            HolderSolutionItem holder_item = new HolderSolutionItem();
            ViewUtils.initHolderView(holder_item, view_solution, R.id.class);

            view_solution.setOnClickListener(new MySolution(i, bean, bean, view_solution, (CircleImageView) view_solution.findViewById(R.id.img_item_solution_back)));
            holder_item.text_solution_num.setText((i + 1) + "");
            holder_item.img_solution_biaoji.setVisibility(View.INVISIBLE);
            if (!MyCheckUtils.isEmpty(bean.getAnswer())) {
                view_solution.setSelected(true);
                ((CircleImageView) view_solution.findViewById(R.id.img_item_solution_back)).setImageResource(R.mipmap.img_blue);
            } else
                ((CircleImageView) view_solution.findViewById(R.id.img_item_solution_back)).setImageResource(R.mipmap.img_efefef);
            view_solution.setSelected(false);
            view_solution.setTag(holder_item);
            mlist_solution.add(view_solution);
            flow_solution.addView(view_solution, params_margin_solution);

        }
    }

    public void setListener(MySolutionListener listener) {
        this.listener = listener;
    }

    public void init() {
        dialog_solution = new Dialog(mContext, R.style.AlertDialog);
        dialog_solution.show();
        dialog_solution.getWindow().setContentView(R.layout.layout_solution);

        ViewUtils.initDialog(dialog_solution, this, R.id.class);

        if (!MyCheckUtils.isEmpty(title)) {
            text_exam_content.setText(title);
        } else {
            text_exam_content.setVisibility(View.GONE);
        }

        img_top_solution_return.setOnClickListener(this);

        text_solution_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.upAllSolution(mlist_exam);
            }
        });
        dialog_solution.dismiss();
    }


    public interface MySolutionListener {
        void clickSolution(int param1Int, View param1View, ExamBean param1ExamBean1, ExamBean param1ExamBean2);

        void upAllSolution(List<ExamBean> param1List);

        void upSolution(List<ExamBean> param1List);
    }

    private class MySolution implements View.OnClickListener {
        public ExamBean bean_exam;

        public ExamBean bean_solution;

        public CircleImageView imageView;

        public int postion;

        public View view_solution;

        public MySolution(int param1Int, ExamBean param1ExamBean1, ExamBean param1ExamBean2, View param1View, CircleImageView param1CircleImageView) {
            this.bean_exam = param1ExamBean1;
            this.bean_solution = param1ExamBean2;
            this.view_solution = param1View;
            this.imageView = param1CircleImageView;
            this.postion = param1Int;
        }

        @Override
        public void onClick(View param1View) {
            HolderSolution.this.listener.clickSolution(this.postion, this.view_solution, this.bean_exam, this.bean_solution);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_solution_return:
                dialog_solution.dismiss();
                break;
        }
    }
}
