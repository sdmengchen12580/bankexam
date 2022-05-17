package com.udit.bankexam.ui.exam_err.holder;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.utils.DBUtils;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.bankexam.utils.URLImageGetter;
import com.udit.frame.common.flowlayout.FlowLayout;
import com.udit.frame.common.texthtml.HtmlTextView;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 练习题目，非 资料题目
 */
public class HolderExam {

    private final String TAG = this.getClass().getSimpleName();

    public HtmlTextView text_exam_material;

    public TextView text_num_current;

    public TextView text_num_;

    public TextView text_num_max;

    public TextView text_exam_qtype;

    public TextView text_exam_content;

    public HtmlTextView text_exam;

    public TextView text_exam_qpoint;
    //总体数
    public TextView text_all_dt;
    //答对数
    public TextView text_all_dd;
    //打错数
    public TextView text_all_dc;
    //选项
    public TextView text_all_xx;


    public LinearLayout ll_study_viewpager;

    public LinearLayout ll_exam_content;

    public LinearLayout flow_options;

    public HtmlTextView text_jiexi;

    public TextView text_biji;
    private View main_view;

    private List<View> mlist_options;

    private Context mContext;

    private int max;

    private ExamOptionsSelected examOptionsSelected;

    private ExamNoteListener examNoteListener;

    private LinearLayout.LayoutParams params_margin;

    public LinearLayout ll_jiexi;

    private boolean flag_zn;

    public TextView text_biji_bt, text_zhengquedan;

    public TextView text_num_qbzt, text_num_qbdd, text_num_qbdc, text_num_qbxx;

    public HolderExam(Context mContext,
                      int max,
                      ExamNoteListener examNoteListener, boolean flag_zn) {
        this.mContext = mContext;
        this.max = max;
        this.examNoteListener = examNoteListener;
        this.flag_zn = flag_zn;

        params_margin = new LinearLayout.LayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params_margin.topMargin = 20;
        params_margin.bottomMargin = 20;
        mlist_options = new ArrayList<>();
        init();
    }

    private void init() {
        main_view = LayoutInflater.from(mContext).inflate(R.layout.item_study_ziliao_viewpage, null);
        ViewUtils.initHolderView(this, main_view, R.id.class);

        main_view.setTag(this);
    }

    public List<View> getMlist_options() {
        return mlist_options;
    }


    public View getMain_view() {
        return main_view;
    }

    private int mPostion;

    public void initView(final int postion, final ExamBean bean_exam) {


        if (bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDUOXT)
                || bean_exam.getQType().equals(Constant.ExamType.TYPE_ZLDXT)) {

            text_exam_material.setHtmlFromString(bean_exam.getMaterial(), false, true);
            text_exam_material.setVisibility(View.VISIBLE);

            text_exam_material.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));
            text_exam_material.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));

        } else {
            text_exam_material.setVisibility(View.GONE);
        }


        text_exam_qtype.setText(bean_exam.getQType());
        if (!MyCheckUtils.isEmpty(bean_exam.getContent())) {
            text_exam_content.setText(bean_exam.getContent());
        }
        if (!MyCheckUtils.isEmpty(bean_exam.getQPoint())) {
            text_exam_qpoint.setText("(" + bean_exam.getQPoint() + ")");
        }
        this.mPostion = postion + 1;
        text_num_current.setText((postion + 1) + "");
        text_num_max.setText(max + "");

        text_exam.setHtmlFromString(bean_exam.getTitle(), false, true);

        List<ExamOptionBean> list_options = bean_exam.getList_TitleList();
        flow_options.removeAllViews();


        setFlow(bean_exam, postion, list_options);

        if (!MyCheckUtils.isEmpty(bean_exam.getAnalysis())) {
            text_jiexi.setHtmlFromString(bean_exam.getAnalysis(), false, false);
        }

        if (!MyCheckUtils.isEmpty(bean_exam.getTotalCount())) {
            text_all_dt.setText(bean_exam.getTotalCount());
        } else {
            text_all_dt.setText("0");
        }

        if (!MyCheckUtils.isEmpty(bean_exam.getRightCount())) {
            text_all_dd.setText(bean_exam.getRightCount());
        } else {
            text_all_dd.setText("0");
        }
        if (!MyCheckUtils.isEmpty(bean_exam.getErrCount())) {
            text_all_dc.setText(bean_exam.getErrCount());
        } else {
            text_all_dc.setText("0");
        }
        if (!MyCheckUtils.isEmpty(bean_exam.getBadAnswer())) {
            text_all_xx.setText(bean_exam.getBadAnswer());
        } else {
            text_all_xx.setText("");
        }

        text_num_qbzt.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        text_num_qbzt.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        text_num_qbdd.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        text_num_qbdd.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        text_num_qbdc.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        text_num_qbdc.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        text_num_qbxx.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        text_num_qbxx.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        text_all_dt.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));

        text_all_dd.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        text_all_dc.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        text_all_xx.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        if (!MyCheckUtils.isEmpty(bean_exam.getSolution())) {
            int time = 0;

            try {
                time = Integer.parseInt(bean_exam.getUserTime());
            } catch (NumberFormatException e) {
                time = 0;
            }

            String times = MyDateUtil.formatLongTime2(time * 1000);
            String daan = String.format(mContext.getString(R.string.str_exam_ok_daan), bean_exam.getSolution(), times);
            text_zhengquedan.setText(daan);

        }
        MyLogUtils.e(TAG, "flag_zn:" + flag_zn);
        if (flag_zn) {
            ll_jiexi.setVisibility(View.VISIBLE);
            text_biji.setVisibility(View.GONE);
            text_biji_bt.setVisibility(View.GONE);
        } else {
            ll_jiexi.setVisibility(View.VISIBLE);
            text_biji_bt.setVisibility(View.VISIBLE);
            text_biji.setVisibility(View.VISIBLE);
            String id = bean_exam.getID();
            if (MyCheckUtils.isEmpty(bean_exam.getID())) {
                id = bean_exam.getTitleID();
            }
            String text = ExamUtils.getExamNote(id);
            if (!MyCheckUtils.isEmpty(text)) {
                text_biji.setText(text);
            } else {
                text_biji.setText("添加笔记");
            }

            text_biji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = bean_exam.getID();
                    if (MyCheckUtils.isEmpty(bean_exam.getID())) {
                        id = bean_exam.getTitleID();
                    }
                    examNoteListener.startNote(id);
                }
            });

        }


    }

    public void setTextBj(String text, int postion) {
        if (postion == this.mPostion) {
            text_biji.setText(text);
        }

    }

    private void setFlow(final ExamBean bean_exam, final int position, List<ExamOptionBean> list_options) {
        for (int i = 0; i < list_options.size(); i++) {
            final ExamOptionBean bean_option = list_options.get(i);
            final HolderOption holder_option = new HolderOption(bean_option, mContext);
            final int postion_exam = i;
         /*   holder_option.getmView_main().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    examOptionsSelected.ExamOptionClick(holder_option.getmView_main(),position,postion_exam,bean_exam,bean_option);
                }
            });*/
            //错题解析部分
            String isOk = bean_exam.getIsOK();
            //标准答案
            String solution = bean_exam.getSolution();
            //提交答案
            String answer = bean_exam.getAnswer();

            holder_option.img_option_jg.setVisibility(View.INVISIBLE);
         /*   //正常的颜色
            holder_option.rl_options.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext,
                            R.drawable.drawable_exam_option_background_status)));*/


            if (!MyCheckUtils.isEmpty(isOk) && isOk.equals(Constant.ExamData.EXAM_OK)) {//答对

            } else if (!MyCheckUtils.isEmpty(isOk)
                    && isOk.equals(Constant.ExamData.EXAM_ERR)) {//答错
                //比较结果
                if (!MyCheckUtils.checkStr(solution, answer)) {
                    if (answer.contains(bean_option.getSingle()) && !solution.contains(bean_option.getSingle())) {
                      /*  holder_option.rl_options.setBackground(mContext.getResources()
                                .getDrawable(ThemeManager.getCurrentThemeRes(mContext,R.drawable.drawable_exam_option_background_err)));
*/

                        holder_option.rl_options.setBackground(mContext.getResources()
                                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));
                        holder_option.img_option_jg.setVisibility(View.VISIBLE);
                        holder_option.img_option_jg.setImageResource(R.mipmap.subject_icon_wrong);
                    }

                }

            }


            if (solution.contains(bean_option.getSingle())) {

                holder_option.rl_options.setBackground(mContext.getResources()
                        .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));

                holder_option.img_option_jg.setVisibility(View.VISIBLE);
                holder_option.img_option_jg.setImageResource(R.mipmap.subject_icon_right);
            }


            flow_options.addView(holder_option.getmView_main(), params_margin);
            mlist_options.add(holder_option.getmView_main());
        }
    }

    public interface ExamOptionsSelected {
        public void ExamOptionClick(View view_options, int postion_exam, int postions, ExamBean bean_exam, ExamOptionBean bean_option);

    }

    public interface ExamNoteListener {
        public void startNote(String titleId);


    }

}
