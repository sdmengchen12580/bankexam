package com.udit.bankexam.ui.exam_err.holder;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.ui.exam.holder.HolderSolutionItem;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.frame.common.flowlayout.FlowLayout;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
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
            ExamBean bean_title = mlist_exam.get(i);
            HolderSolutionItem holderSolutionItem = (HolderSolutionItem) mlist_solution.get(i).getTag();
            if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_OK)) {
                holderSolutionItem.text_solution_num.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

                holderSolutionItem.img_item_solution_back.setImageResource(R.mipmap.datika_circle_right);

                holderSolutionItem.rl_solution_item.setBackgroundColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));
            } else if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_ERR)) {
                holderSolutionItem.text_solution_num.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

                holderSolutionItem.img_item_solution_back.setImageResource(R.mipmap.datika_circle_wrong2);

                holderSolutionItem.rl_solution_item.setBackgroundColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));
            } else {
                //exam_err_count++;
                holderSolutionItem.text_solution_num.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_8c9fb0)));

                holderSolutionItem.img_item_solution_back.setImageResource(R.mipmap.datika_circle_none);


                holderSolutionItem.rl_solution_item.setBackgroundColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));
            }



/*

            holderSolutionItem.img_item_solution_back.setImageDrawable(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_solution)));


            holderSolutionItem.text_solution_num.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_solution)));


            holderSolutionItem.rl_solution_item.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_black)));*/

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
        MyLogUtils.e(TAG, mlist_exam.toString());
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
        MyLogUtils.e(TAG, mlist_exam.toString());
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
        MyLogUtils.e(TAG, mlist_exam.toString());
    }

    public void setMlist_exam(List<ExamBean> list) {
        this.mlist_exam = list;

        flow_solution.removeAllViews();
        for (int i = 0; mlist_exam != null && i < mlist_exam.size(); i++) {
            ExamBean bean = mlist_exam.get(i);

            //加载答题卡
            View view_solution = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_solution, null);


            HolderSolutionItem holder_item = new HolderSolutionItem();
            ViewUtils.initHolderView(holder_item, view_solution, R.id.class);

            view_solution.setOnClickListener(new MySolution(i, bean, bean, view_solution));
            holder_item.text_solution_num.setText((i + 1) + "");
            holder_item.img_solution_biaoji.setVisibility(View.INVISIBLE);
            ExamBean bean_title = mlist_exam.get(i);
            View view = flow_solution.getChildAt(i);
            if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_OK)) {
                holder_item.text_solution_num.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.white)));

                holder_item.img_item_solution_back.setImageResource(R.mipmap.datika_circle_right);
            } else if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_ERR)) {
                holder_item.text_solution_num.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.white)));

                holder_item.img_item_solution_back.setImageResource(R.mipmap.datika_circle_wrong2);
            } else {
                //exam_err_count++;
                holder_item.text_solution_num.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_8c9fb0)));

                holder_item.img_item_solution_back.setImageResource(R.mipmap.datika_circle_none);
            }
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
        text_top_solution_time.setVisibility(View.GONE);


        img_top_solution_return.setOnClickListener(this);
        text_solution_btn.setVisibility(View.GONE);

        dialog_solution.dismiss();
    }


    public interface MySolutionListener {
        public void clickSolution(int postion, View view_solution, ExamBean bean, ExamBean bean_solution);


        public void upSolution(List<ExamBean> list);

        public void upAllSolution(List<ExamBean> list);
    }

    private class MySolution implements View.OnClickListener {
        public ExamBean bean_exam;

        public ExamBean bean_solution;

        public View view_solution;

        public int postion;

        public MySolution(int postion, ExamBean bean_exam, ExamBean bean, View view_solution) {
            this.bean_exam = bean_exam;
            this.bean_solution = bean;
            this.view_solution = view_solution;
            this.postion = postion;

        }

        @Override
        public void onClick(View v) {
            listener.clickSolution(postion, view_solution, bean_exam, bean_solution);
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
