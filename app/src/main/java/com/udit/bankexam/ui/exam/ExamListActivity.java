package com.udit.bankexam.ui.exam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam.ExamListPresenter;
import com.udit.bankexam.ui.exam_day.ExamDayDetailActivity;
import com.udit.bankexam.ui.exam_day.adapter.AdapterExamDay;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.view.exam.ExamListView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;

/**
 * Created by zb on 2017/6/13.
 */

public class ExamListActivity extends BaseActivity<ExamListPresenter> implements ExamListView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private String eid;
    private String type;

    public static void startExamListActivity(BaseActivity<?> mActivity, String eid, String type) {
        Intent intent = new Intent(mActivity, ExamListActivity.class);
        intent.putExtra("EID", eid);
        intent.putExtra("type", type);
        mActivity.startActivity(intent);

    }

    private LinearLayout ll_date;

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_examday;

    private AdapterExamDay adapter;

    private ImageView img_top_right;

    private List<ExamInfoBean> mlist;

    private Context mContext;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_examday);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        img_top_right.setVisibility(View.INVISIBLE);
        this.ll_date.setVisibility(View.GONE);
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        listview_examday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExamDayDetailActivity.startExamDayDetailActivity((BaseActivity<?>) mContext, mlist.get(position), Constant.RESULT.RESULT_EXAM_PAY);
            }
        });
    }

    UserBean bean_user;

    @Override
    public void initData() {
        mPresenter = new ExamListPresenter(this);

        bean_user = SaveUtils.getUser(this);
        eid = getIntent().getStringExtra("EID");
        type = getIntent().getStringExtra("type");

        mlist = new ArrayList<>();
        adapter = new AdapterExamDay(mlist, this);
        listview_examday.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        text_top_centent.setText(type);
        mContext = this;
        mPresenter.getExamList(bean_user.getUid(), eid, type);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        if (requestCode == Constant.RESULT.RESULT_EXAM_YEAR_DETAIL
                && resultCode == RESULT_OK) {
            mPresenter.getExamList(bean_user.getUid(), eid, type);
        }


    }

    @Override
    public void setExamList(List<ExamInfoBean> list) {
        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                finish();
                break;
        }
    }
}
