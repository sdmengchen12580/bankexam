package com.udit.bankexam.ui.exam_mk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_mk.ExamMkPresenter;
import com.udit.bankexam.ui.exam_mk.adapter.MkAdapter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_mk.ExamMkView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamMkActivity extends BaseActivity<ExamMkPresenter> implements ExamMkView.View, View.OnClickListener {

    public static void startExamMkActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamMkActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_exammk);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("模考大赛 ");
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private UserBean bean_user;

    private ListView listview_exammk;

    private MkAdapter adapter;

    private List<MKBean> mlist;

    private Context mContext;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        listview_exammk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MKBean bean = mlist.get(position);
                ExamMkDetailActivity.startExamMKDetailActivity((BaseActivity<?>) mContext, bean);

            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new ExamMkPresenter(this);

        mContext = this;

        bean_user = SaveUtils.getUser(this);

        mlist = new ArrayList<>();

        adapter = new MkAdapter(mlist, this);

        listview_exammk.setAdapter(adapter);

        mPresenter.getMK(this, bean_user.getUid());


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
    public void setMkList(List<MKBean> list) {

        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist.addAll(list);

        }
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getMK(this, bean_user.getUid());
    }
}
