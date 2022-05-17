package com.udit.bankexam.ui.exam_mk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.bean.ReportBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_mk.ExamMkReportDataPresenter;
import com.udit.bankexam.ui.exam_err.ExamErrTitleActivity;
import com.udit.bankexam.ui.exam_err.HolderExamTitle;
import com.udit.bankexam.ui.exam_mk.adapter.MkExamNodeAdapter;
import com.udit.bankexam.ui.home.fragment.adapter.MainAdapter_new;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.bankexam.view.exam_mk.ExamMkReportDataView;
import com.udit.frame.common.flowlayout.FlowLayout;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017-9-19.
 */

public class ExamMkReportDataActivity extends BaseActivity<ExamMkReportDataPresenter> implements ExamMkReportDataView.View, View.OnClickListener, MkExamNodeAdapter.ExamGroupOnClick {


    public static void startExamMkReportDataActivity(BaseActivity<?> mActivity, MKBean bean_mk) {
        Intent intent = new Intent(mActivity, ExamMkReportDataActivity.class);
        intent.putExtra("mk", bean_mk);
        mActivity.startActivity(intent);
    }

    private final String TAG = this.getClass().getSimpleName();

    private ImageView img_top_return;

    private TextView text_top_centent, text_top_right;

    /*
     * **********************个人成绩***********************
     * */
    //个人分数  ， 最高分  , 平均分 ，  击败
    private TextView textview_fenshu, textview_max_fenshu, textview_avg_fenshu, textview_jipai;
    //考试情况  用时
    private TextView textview_kaoshi_qingkuang, textview_kaoshi_time;

    /**
     * *********************试卷****************************
     */
    //题型
    public static ListView tixing_listview;

    //答题卡
    private FlowLayout flow_dtk;
    //模考基本情况
    private MKBean bean_Mk;

    private UserBean bean_user;

    private List<ExamNodeBean> mlist_exam_node;

    private MkExamNodeAdapter adapter_main;

    private List<ExamTitleBean> mlist_title;

    private ViewGroup.MarginLayoutParams params_margin;

    private int width_solution;

    /**
     * ***************解析*************
     */
    private TextView textview_jx_btn, textview_jx_all_btn;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_mkreport);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);

        text_top_right.setVisibility(View.GONE);

        textview_kaoshi_qingkuang.setVisibility(View.GONE);

        textview_kaoshi_time.setVisibility(View.GONE);

        width_solution = getResources().getDisplayMetrics().widthPixels;

        width_solution = width_solution / 5;

    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        textview_jx_btn.setOnClickListener(this);

        textview_jx_all_btn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new ExamMkReportDataPresenter(this);

        params_margin = new ViewGroup.MarginLayoutParams(width_solution, width_solution);
        params_margin.topMargin = 20;
        params_margin.bottomMargin = 20;

        Object obj_mk = getIntent().getSerializableExtra("mk");

        if (obj_mk != null && obj_mk instanceof MKBean) {
            bean_Mk = (MKBean) obj_mk;
            bean_user = SaveUtils.getUser(this);
            text_top_centent.setText(bean_Mk.getExName());

            mlist_exam_node = new ArrayList<>();
            mlist_title = new ArrayList<>();

            adapter_main = new MkExamNodeAdapter(mlist_exam_node, getActivity());
            tixing_listview.setAdapter(adapter_main);
            adapter_main.setExamGroupClick(this);
            adapter_main.notifyDataSetChanged();

            mPresenter.getMkRepPractice(bean_user.getUid(), bean_Mk.getExaminID());
            mPresenter.getMkExamNode(bean_user.getUid(), bean_Mk.getExaminID());
            mPresenter.getMKDTK(bean_user.getUid(), bean_Mk.getExaminID());
        } else {

        }

    }

    @Override
    public void setMkRep(ReportBean bean_report) {
        if (bean_report != null) {
            if (!MyCheckUtils.isEmpty(bean_report.getMyScore())) {
                textview_fenshu.setText(bean_report.getMyScore());
            } else
                textview_fenshu.setText("");

            if (!MyCheckUtils.isEmpty(bean_report.getMaxScore())) {
                textview_max_fenshu.setText(bean_report.getMaxScore());
            } else
                textview_max_fenshu.setText("");

            if (!MyCheckUtils.isEmpty(bean_report.getAvgScore())) {
                textview_avg_fenshu.setText(bean_report.getAvgScore());
            } else
                textview_avg_fenshu.setText("");

            if (!MyCheckUtils.isEmpty(bean_report.getWinCount())) {
                int winCount = 0;
                try {
                    winCount = Integer.parseInt(bean_report.getWinCount());
                } catch (Exception e) {
                    winCount = 0;
                }
                if (winCount == 0)
                    textview_jipai.setText("0%");
                else {
                    int totalCount = 0;
                    try {
                        totalCount = Integer.parseInt(bean_report.getAllCount());
                    } catch (Exception e) {
                        totalCount = 0;
                    }

                    String per = Utils.getPer(winCount + "", totalCount + "");
                    textview_jipai.setText(per);

                }
            } else {
                textview_jipai.setText("0%");
            }

            if (!MyCheckUtils.isEmpty(bean_report.getTitleCount())
                    && !MyCheckUtils.isEmpty(bean_report.getOkCount())
                    && !MyCheckUtils.isEmpty(bean_report.getErrCount())) {
                String per = Utils.getPer(bean_report.getOkCount(), bean_report.getTitleCount());

                String titleCount = bean_report.getTitleCount();
                String okCount = bean_report.getOkCount();
                String errCount = bean_report.getErrCount();

                String s = String.format(getString(R.string.string_mk_ksqingkuang), titleCount, okCount, errCount, per);

                textview_kaoshi_qingkuang.setText(s);
            } else {
                String s = String.format(getString(R.string.string_mk_ksqingkuang), "0", "0", "0", "0%");

                textview_kaoshi_qingkuang.setText(s);
            }
            textview_kaoshi_qingkuang.setVisibility(View.VISIBLE);

            if (!MyCheckUtils.isEmpty(bean_report.getAllTime())) {
                int time = Integer.parseInt(bean_report.getAllTime());
                MyLogUtils.e(TAG, "总用时：" + time);
                String times = MyDateUtil.formatLongTime2(time * 1000);
                textview_kaoshi_time.setText("总用时：" + times);
            } else {
                textview_kaoshi_time.setText("总用时：");
            }
            textview_kaoshi_time.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public void setMkExamNode(List<ExamNodeBean> list_node) {
        if (list_node != null) {

            mlist_exam_node.clear();
            mlist_exam_node.addAll(list_node);
            adapter_main.notifyDataSetChanged();
            ViewUtils.setListViewHeightBasedOnChildrenAndParent(ExamMkReportDataActivity.tixing_listview);
        } else {
            mlist_exam_node.clear();
            adapter_main.notifyDataSetChanged();
        }


    }

    @Override
    public void setMkExamTdk(List<ExamTitleBean> list) {
        mlist_title.clear();
        if (list != null && list.size() > 0)
            mlist_title.addAll(list);
        setSjDetailByExam(list);

    }

    private void setSjDetailByExam(List<ExamTitleBean> list_exam) {
        flow_dtk.removeAllViews();
        mlist_title.clear();
        if (list_exam != null) {
            mlist_title.addAll(list_exam);

            for (int i = 0; list_exam != null && i < list_exam.size(); i++) {
                ExamTitleBean bean_title = list_exam.get(i);
                View view = LayoutInflater.from(this).inflate(R.layout.item_solution, null);
                HolderExamTitle holderExamTitle = new HolderExamTitle();
                holderExamTitle.mView = view;
                ViewUtils.initHolderView(holderExamTitle, view, R.id.class);
                holderExamTitle.text_solution_num.setText((i + 1) + "");
                holderExamTitle.img_solution_biaoji.setVisibility(View.GONE);


                if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_OK)) {
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.white)));
                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.datika_circle_right);
                } else if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_ERR)) {
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.white)));
                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.datika_circle_wrong2);
                } else {
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_8c9fb0)));

                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.datika_circle_none);
                }
                flow_dtk.addView(view, params_margin);

            }

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                finish();
                break;
            case R.id.textview_jx_btn:
                ArrayList<ExamTitleBean> list_err = new ArrayList<>();
                for (int i = 0; mlist_title != null && i < mlist_title.size(); i++) {

                    ExamTitleBean title = mlist_title.get(i);
                    if (title.getIsOK().equals("否")) {
                        list_err.add(title);
                    }
                }
                if (list_err != null && list_err.size() > 0) {
                    String ids = ExamUtils.getExamTtile(list_err);
                    ExamErrTitleActivity.startExamErrTitleActivity
                            (this, bean_Mk.getExaminID(), bean_Mk.getExName(), ids, false, false);
                } else {
                    showLongToast("暂无错题，无法使用错题解析");
                }
                break;
            case R.id.textview_jx_all_btn:
                /*if(flag_zhineng)
                {*/
                String ids = ExamUtils.getExamTtile(mlist_title);
                ExamErrTitleActivity.startExamErrTitleActivity
                        (this, bean_Mk.getExaminID(), bean_Mk.getExName(), ids, false, false);
                /*}
                else
                {
                    //解析
                    ExamErrTitleActivity.startExamErrTitleActivity(this,mlist_titlebean,flag_zhineng);

                }*/

                break;
        }
    }

    @Override
    public void ExamGo(ExamNodeBean bean) {

    }
}
