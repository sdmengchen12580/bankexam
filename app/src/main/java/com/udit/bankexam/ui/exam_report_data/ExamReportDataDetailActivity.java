package com.udit.bankexam.ui.exam_report_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.ReportBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_report_data.ExamReportDataDetailPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_report_data.ExamReportDataDetailView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import org.w3c.dom.Text;

/**
 * Created by zb on 2017/5/19.
 */

public class ExamReportDataDetailActivity extends BaseActivity<ExamReportDataDetailPresenter> implements ExamReportDataDetailView.View, View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();

    public static void startExamReportDataDetailActivity(BaseActivity<?> mActivity, PurchBean bean) {
        Intent intent = new Intent(mActivity, ExamReportDataDetailActivity.class);
        intent.putExtra("purchbean", bean);
        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_reportdatadetail);
    }

    private PurchBean bean_purch;

    private ImageView img_top_return;

    private TextView text_top_centent, text_top_right;

    private TextView textview_fenshu, textview_total_fenshu, textview_max_fenshu;

    private TextView textview_tishu_yizuo, textview_tishu_zhenquelue;


    private TextView textview_tishu_zheque, textview_tishu_cuowu;

    private TextView textview_tishu_time;
    private TextView tv_time;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_right.setText("错题本");
        text_top_right.setVisibility(View.GONE);
    }

    @Override
    public void initListeners() {

        img_top_return.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new ExamReportDataDetailPresenter(this);

        bean_purch = (PurchBean) getIntent().getSerializableExtra("purchbean");

        text_top_centent.setText(bean_purch.getAbstract());

        UserBean bean_user = SaveUtils.getUser(this);
        mPresenter.getRep(bean_user.getUid(), bean_purch.getLinkID(), ExamReportDataDetailActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
        }
    }

    @Override
    public void setReport(ReportBean bean) {
        if (bean != null) {
            if (!MyCheckUtils.isEmpty(bean.getMyScore())) {
                textview_fenshu.setText(bean.getMyScore() + "");
            }
            if (!MyCheckUtils.isEmpty(bean.getAllScore())) {
                textview_total_fenshu.setText(bean.getAllScore() + "");
            }
            if (!MyCheckUtils.isEmpty(bean.getMaxScore())) {
                textview_max_fenshu.setText(bean.getMaxScore() + "");
            }

            if (!MyCheckUtils.isEmpty(bean.getAnswerCount())) {
                textview_tishu_yizuo.setText(bean.getAnswerCount() + "");


            }
            if (!MyCheckUtils.isEmpty(bean.getOkCount())) {
                String per = Utils.getPer(bean.getOkCount(), bean.getTitleCount());
                textview_tishu_zhenquelue.setText(per);

                textview_tishu_zheque.setText(bean.getOkCount());
            }
            if (!MyCheckUtils.isEmpty(bean.getErrCount())) {
                textview_tishu_cuowu.setText(bean.getErrCount());
            }
            if (!MyCheckUtils.isEmpty(bean.getAllTime())) {
                int time = Integer.parseInt(bean.getAllTime());
                MyLogUtils.e(TAG, "总用时：" + time);
                String times = MyDateUtil.formatLongTime2(time * 1000);
                textview_tishu_time.setText(times);
                this.tv_time.setVisibility(View.VISIBLE);
            }else {
                this.tv_time.setVisibility(View.GONE);
            }

        } else {
            showLongToast("您还未做过试卷，暂无错题本");
        }
    }
}
