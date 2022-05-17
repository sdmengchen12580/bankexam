package com.udit.bankexam.ui.sreach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.sreach.ExamSreachKeyPresenter;
import com.udit.bankexam.presenter.sreach.ExamSreachPresenter;
import com.udit.bankexam.ui.exam_err.ExamErrTitleActivity;
import com.udit.bankexam.ui.sreach.adapter.ExamSreachAdapter;
import com.udit.bankexam.ui.sreach.adapter.ExamSreachKeyAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.sreach.ExamSreachKeyView;
import com.udit.bankexam.view.sreach.ExamSreachView;
import com.udit.frame.common.pullrefresh.PullToRefreshBase;
import com.udit.frame.common.pullrefresh.PullToRefreshListView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDataUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/4/24.
 */

public class ExamSreachShouCangActivity extends BaseActivity<ExamSreachKeyPresenter> implements ExamSreachKeyView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_eaxmsreach_shoucang);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
    }

    private ListView sreach_key;

    private EditText edit_top_sreach;

    private TextView text_top_sreach;

    private UserBean bean_user;

    private List<String> mlist_key;

    private ExamSreachKeyAdapter adapter;

    private ImageView img_delete;

    private Context mContext;

    @Override
    public void initListeners() {
        text_top_sreach.setOnClickListener(this);

        img_delete.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mContext = this;
        mPresenter = new ExamSreachKeyPresenter(this);

        bean_user = SaveUtils.getUser(this);


        mlist_key = new ArrayList<>();
        List<String> list_key =  SaveUtils.getExamSreachKey(this);
        if(list_key!=null)
            mlist_key.addAll(list_key);


        adapter = new ExamSreachKeyAdapter(mlist_key,this);
        sreach_key.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_delete:
                edit_top_sreach.setText("");

                break;
            case R.id.text_top_sreach:
               String text =  edit_top_sreach.getText().toString();

                if(!MyCheckUtils.isEmpty(text))
                {
                   /* Intent intent = new Intent(this,ExamSreachActivity.class);
                    startActivity(intent);*/

                    mlist_key.add(text);
                    adapter.notifyDataSetChanged();
                    SaveUtils.saveSreachKey(mlist_key,this);


                }
                else
                {
                    showLongToast("请填写搜索题目");
                }
                break;
        }
    }


    @Override
    public void setExam(List<String> list) {

    }
}
