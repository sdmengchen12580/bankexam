package com.udit.bankexam.ui.exam_robot_pract;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.TypeBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_robot_pract.ExamRobotPractPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_robot.adapter.TypeAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_robot_pract.ExamRobotPractView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 * 智能练习
 */

public class ExamRobotPractActivity extends BaseActivity<ExamRobotPractPresenter> implements ExamRobotPractView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamRobotPractActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamRobotPractActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_robot_pract);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("专项练习");
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_type_pract;

    private TypeAdapter adapter;

    private List<TypeBean> mlist;

    private TextView textview_robot_pract_btn;

    private UserBean bean_user;


    private TypeBean mTypeBean;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        listview_type_pract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedIndex(position);
                mTypeBean = mlist.get(position);
            }
        });

        textview_robot_pract_btn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new ExamRobotPractPresenter(this);

        bean_user = SaveUtils.getUser(this);
        mlist = new ArrayList<>();
        adapter = new TypeAdapter(this, mlist);
        listview_type_pract.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mPresenter.getData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                finish();
                break;
            case R.id.textview_robot_pract_btn:
                int selected = adapter.getSelectedIndex();
                TypeBean bean_type = mlist.get(selected);

                MyLogUtils.e(TAG, "选中的内容：" + bean_type.getName());
                mPresenter.getExamList(bean_user.getUid(), bean_type.getName());
                break;
        }
    }

    @Override
    public void setData(List<TypeBean> list) {
        mlist.clear();
        if (list != null) {
            mlist.addAll(list);
            mTypeBean = mlist.get(0);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setExamList(List<ExamTitleBean> list) {
        if (list != null) {
            String title_str = ExamUtils.getExamTtile(list);
            if (!MyCheckUtils.isEmpty(title_str)) {
                ExamActivity.startExamActivity(this, null, mTypeBean.getName(), title_str, true, false, false, false);
                // ExamActivity.startExamActivity(this,"智能练习",title_str,true);
            } else {
                showLongToast("选中的考点暂无试题");
            }

        } else {
            showLongToast("选中的考点暂无试题");
        }
    }
}
