package com.udit.bankexam.ui.exam_report_week;

import android.content.Intent;
import android.os.Bundle;

import com.udit.bankexam.presenter.exam_report_week.ExamReportWeekPresenter;
import com.udit.bankexam.view.exam_report_week.ExamReportWeekView;
import com.udit.frame.freamwork.activity.BaseActivity;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamReportWeekActivity extends BaseActivity<ExamReportWeekPresenter> implements ExamReportWeekView.View{

    public static void startExamReportWeekActivity(BaseActivity<?> mActivity)
    {
        mActivity.startActivity(new Intent(mActivity,ExamReportWeekActivity.class));
    }

    @Override
    public void setContentView() {

    }

    @Override
    public void initViews(Bundle bundle) {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }
}
