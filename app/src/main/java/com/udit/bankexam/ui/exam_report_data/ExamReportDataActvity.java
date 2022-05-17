package com.udit.bankexam.ui.exam_report_data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.ReportBean;
import com.udit.bankexam.bean.SolutionBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_report_data.ExamReportDataPresenter;
import com.udit.bankexam.ui.exam_report_data.adapter.SJAdapter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_report_data.ExamReportDataDetailView;
import com.udit.bankexam.view.exam_report_data.ExamReportDataView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 * <p>
 * 数据报告
 */
public class ExamReportDataActvity extends BaseActivity<ExamReportDataPresenter> implements ExamReportDataView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamReportDataActvity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamReportDataActvity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_report_data);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private SJAdapter adapter;

    private List<PurchBean> mlist_purch;

    private ListView listview_report_data;

    private UserBean bean_user;

    private Context mContext;

    private String shouye_id;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("数据报告");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        listview_report_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PurchBean bean = mlist_purch.get(position);
                ExamReportDataDetailActivity.startExamReportDataDetailActivity((BaseActivity<?>) mContext, bean);
            }
        });
    }

    @Override
    public void initData() {

        mContext = this;

        mPresenter = new ExamReportDataPresenter(this);

        mlist_purch = new ArrayList<>();

        adapter = new SJAdapter(mlist_purch, this);

        listview_report_data.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        bean_user = SaveUtils.getUser(this);
        shouye_id = SaveUtils.getExamShouYe(this);
        mPresenter.getSJ(bean_user.getUid());


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
    public void setReport(List<ReportBean> list) {

    }

    @Override
    public void setDTK(List<SolutionBean> list) {

    }

    @Override
    public void setSJ(List<PurchBean> list) {
        mlist_purch.clear();
        if (list != null && list.size() > 0) {
            mlist_purch.addAll(list);
            if (!MyCheckUtils.isEmpty(shouye_id)) {
                PurchBean bean = new PurchBean();
                bean.setUid(bean_user.getUid());
                bean.setLinkID(shouye_id);
                mlist_purch.remove(bean);
            }

        } else {
            showLongToast("您还未做过试卷，暂无数据报告");
        }

        adapter.notifyDataSetChanged();
    }
}
