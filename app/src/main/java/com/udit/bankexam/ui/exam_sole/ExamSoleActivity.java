package com.udit.bankexam.ui.exam_sole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_day.ExamDayPresenter;
import com.udit.bankexam.presenter.exam_sole.ExamSolePresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_day.ExamDayActivity;
import com.udit.bankexam.ui.exam_day.ExamDayDetailActivity;
import com.udit.bankexam.ui.exam_day.adapter.AdapterExamDay;
import com.udit.bankexam.ui.exam_year.ExamYearActivity;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_sole.ExamSoleView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 * 独家密卷
 */

public class ExamSoleActivity extends BaseActivity<ExamSolePresenter> implements ExamSoleView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamSoleActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamSoleActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_examsole);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("独家密卷");
    }

    private UserBean bean_user;

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_exam_sole;

    private AdapterExamDay adapter;

    private List<ExamInfoBean> mlist;

    private ExamInfoBean bean_info;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        listview_exam_sole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean_info = mlist.get(position);
                String price_s = Utils.doubleOutPut(bean_info.getPrice(), 2);
                if ((bean_info.getIsGet() != null && bean_info.getIsGet().equals(Constant.ExamData.GOUMAI_OK)) ||
                        price_s.equals("0")) {//已购买
                    mPresenter.saveExam(bean_user.getUid(), bean_info.getID(), bean_info.getName());
                    mPresenter.getExamTitle(ExamSoleActivity.this, bean_user.getUid(), bean_info.getID());
                } else {

                    ExamDayDetailActivity.startExamDayDetailActivity(ExamSoleActivity.this, bean_info, Constant.RESULT.RESULT_EXAM_SOLE_DETAIL);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        if (requestCode == Constant.RESULT.RESULT_EXAM_SOLE_DETAIL
                && resultCode == RESULT_OK) {
            mPresenter.getExamSole(this, bean_user.getUid(), "独家密卷");
        }


    }

    @Override
    public void initData() {
        mPresenter = new ExamSolePresenter(this);
        bean_user = SaveUtils.getUser(this);

        mlist = new ArrayList<>();
        adapter = new AdapterExamDay(mlist, this);
        listview_exam_sole.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mPresenter.getExamSole(this, bean_user.getUid(), "独家密卷");
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
    public void setExamList(List<ExamInfoBean> list) {
        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        } else
            showLongToast("暂无试卷");
    }

    @Override
    public void setExamTitleList(List<ExamTitleBean> list) {
        String list_title = ExamUtils.getExamTtile(list);
        if (!MyCheckUtils.isEmpty(list_title)) {
            ExamActivity.startExamActivity(this, bean_info.getID(), bean_info.getName(), list_title, false, false, false, false);
            // ExamActivity.startExamActivity(this,bean_info,list_title);
        } else {
            bean_info = null;
            showLongToast("暂无题目");
        }
    }
}
