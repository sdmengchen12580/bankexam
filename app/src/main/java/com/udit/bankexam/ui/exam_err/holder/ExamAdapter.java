package com.udit.bankexam.ui.exam_err.holder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.ui.exam.holder.HolderWindow;
import com.udit.bankexam.utils.FontUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.frame.utils.MyLogUtils;

import java.util.ArrayList;
import java.util.List;

public class ExamAdapter extends PagerAdapter {
    private final String TAG = this.getClass().getSimpleName();

    private List<ExamBean> mlist;

    private Context mContext;

    // 每个item的页面view
    private ArrayList<View> mlist_remove;

    private View convertView;


    private HolderWindow.TextSize size;

    private HolderExam.ExamNoteListener examNoteListener;


    public void setSize(HolderWindow.TextSize size) {
        this.size = size;
    }


    private boolean flag_zhineng;

    /**
     * <默认构造函数>
     */
    public ExamAdapter(Context mContext, List<ExamBean> mlist, HolderExam.ExamNoteListener examNoteListener, boolean flag_zhineng) {
        super();
        this.mlist = mlist;
        this.mContext = mContext;
        this.examNoteListener = examNoteListener;
        this.flag_zhineng = flag_zhineng;
        this.mlist_remove = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //  MyLogUtils.e(TAG,"正在销毁："+position);
        View view = (View) object;
        // View view =  mlist_view.get((position) % mlist_view.size());
        container.removeView(view);
        //  mlist_add.remove(position);//删除页面不使用的View  
        mlist_remove.add(view);//

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //   MyLogUtils.e(TAG,"正在创建："+position);

        View view = null;

        if (mlist_remove.size() > 0) {
            view = mlist_remove.remove(0);
        }
        view = getView(container, view, position);
        container.addView(view);

        return view;
    }

    private View getView(ViewGroup container, View view, int position) {
        HolderExam holderexam = null;
        if (view == null) {
            holderexam = new HolderExam(mContext, mlist.size(), examNoteListener, flag_zhineng);
            view = holderexam.getMain_view();

        } else {
            holderexam = (HolderExam) view.getTag();
        }

        ExamBean bean = mlist.get(position);
        MyLogUtils.e(TAG, "POSTION:" + position + " bean:" + bean.toString());
        holderexam.initView(position, bean);
        initExamTheme(holderexam, view);
        initTextSize(size, view);
        FontUtils.injectFont(view);
        return view;
    }


    public void initTextSize(HolderWindow.TextSize size, View view) {
        HolderExam exam_holder = (HolderExam) view.getTag();
        exam_holder.text_exam_material.setTextSize(size.getSize());
        exam_holder.text_exam.setTextSize(size.getSize());
        for (int j = 0; exam_holder.getMlist_options() != null
                && j < exam_holder.getMlist_options().size(); j++) {
            HolderOption holderOption = (HolderOption) exam_holder.getMlist_options().get(j).getTag();
            holderOption.text_options_index.setTextSize(size.getSize());
            holderOption.text_options.setTextSize(size.getSize());
        }
    }


    public void initExamTheme(HolderExam holder_exam, View view) {
        //for(int i = 0;i<viewpager_exam.getChildCount();i++)
        //{
        // / for (int i = 0; mlist_view != null && i < mlist_view.size(); i++) {
        //    View view = viewpager_exam.getChildAt(i);

        int color = ThemeManager.getCurrentThemeRes(mContext,
                R.color.color_exam_black);

        view.setBackgroundColor(mContext.getResources().getColor(color));
        // HolderExam holder_exam = (HolderExam) view.getTag();

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

        //解析 相关


        holder_exam.ll_jiexi.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

        holder_exam.text_zhengquedan.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));


        holder_exam.text_num_qbzt.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        holder_exam.text_num_qbzt.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        holder_exam.text_num_qbdd.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        holder_exam.text_num_qbdd.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        holder_exam.text_num_qbdc.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        holder_exam.text_num_qbdc.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        holder_exam.text_num_qbxx.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        holder_exam.text_num_qbxx.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        holder_exam.text_all_dt.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));

        holder_exam.text_all_dd.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        holder_exam.text_all_dc.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        holder_exam.text_all_xx.setBackground(mContext.getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


        holder_exam.text_jiexi.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        holder_exam.text_jiexi.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

        holder_exam.text_biji.setTextColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
        holder_exam.text_biji.setBackgroundColor(mContext.getResources()
                .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));


        for (int j = 0; holder_exam.getMlist_options() != null
                && j < holder_exam.getMlist_options().size(); j++) {
            View view_option = holder_exam.getMlist_options().get(j);
            HolderOption holderOption = (HolderOption) view_option.getTag();


            view_option.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_back)));


            holderOption.rl_options.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));
            // holderOption.ll_options.setBackground(mContext.getResources()
            //       .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));
            holderOption.text_options_index.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_option)));
            holderOption.text_options.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_option)));


        }

    }


}
