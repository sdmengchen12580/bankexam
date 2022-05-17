package com.udit.bankexam.ui.exam_report;

import android.content.Intent;
import android.os.Bundle;

import com.udit.bankexam.presenter.exam_report.ExamReportPresenter;
import com.udit.bankexam.view.exam_report.ExamReportView;
import com.udit.frame.freamwork.activity.BaseActivity;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamReportActivity extends BaseActivity<ExamReportPresenter> implements ExamReportView.View {

    public static void startExamReportActivity(BaseActivity<?> mActivity)
    {
        mActivity.startActivity(new Intent(mActivity,ExamReportActivity.class));
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
