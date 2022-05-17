package com.udit.bankexam.ui.exam_data_history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_data_history.ExamHomeHistoryPresenter;
import com.udit.bankexam.presenter.exam_err.ExamHomeErrPresenter;
import com.udit.bankexam.ui.exam_data_history.adapter.ExamHomeHistoryAdapter;
import com.udit.bankexam.ui.exam_err.ExamErrDetailActivity;
import com.udit.bankexam.ui.exam_err.holder.ExamHomeErrAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_data_history.ExamHomeHistoryView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/6/30.
 */

public class ExamHomeHistoryActivity extends BaseActivity<ExamHomeHistoryPresenter> implements
        ExamHomeHistoryView.View, View.OnClickListener, ExamHomeHistoryAdapter.ExamGroupOnClick {


    private final String TAG = this.getClass().getSimpleName();

    public static void startExamHomeHistoryActivity(BaseActivity<?> mActivity) {
        Intent intent = new Intent(mActivity, ExamHomeHistoryActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home_err);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;


    private ListView listview_home_err;


    private ArrayList<ExamNodeBean> mlist_node;

    private ExamHomeHistoryAdapter adapter;

    private ExamNodeBean bean_node;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("练习历史");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
    }

    private UserBean bean_user;

    @Override
    public void initData() {

        mPresenter = new ExamHomeHistoryPresenter(this);
        bean_user = SaveUtils.getUser(this);
        mlist_node = new ArrayList<>();
        adapter = new ExamHomeHistoryAdapter(mlist_node, this);
        listview_home_err.setAdapter(adapter);
        adapter.setExamGroupClick(this);
        adapter.notifyDataSetChanged();

        bean_node = null;

        mPresenter.getExamNode(this, bean_user.getUid());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                finish();
                break;
        }
    }


    @Override
    public void setExamNode(List<ExamNodeBean> list_node) {
        mlist_node.clear();
        if (list_node != null && list_node.size() > 0) {
            mlist_node.addAll(list_node);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setExamNodeIsOk(List<ExamTitleBean> list) {
        if (list != null && list.size() > 0) {
            String ids = ExamUtils.getExamTtile(list);
            if (!MyCheckUtils.isEmpty(ids)) {
                ExamErrDetailActivity.startExamErrDetailActivity(this, bean_node.getID(),
                        bean_node.getName(), ids, false, true, false, false, false);
            } else
                showLongToast("暂无练习记录");
        } else {
            showLongToast("暂无练习记录");
        }
        bean_node = null;
    }

    @Override
    public void ExamGo(ExamNodeBean bean) {
        bean_node = bean;
        mPresenter.getExamNodeISOk(this, bean_user.getUid(), bean_node.getID());
    }

    @Override
    public void ExamErrGo(ExamNodeBean bean) {

    }
}
