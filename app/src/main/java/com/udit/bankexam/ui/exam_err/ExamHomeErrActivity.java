package com.udit.bankexam.ui.exam_err;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_err.ExamHomeErrPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_err.holder.ExamHomeErrAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_err.ExamHomeErrView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/6/16.
 */

public class ExamHomeErrActivity extends BaseActivity<ExamHomeErrPresenter> implements ExamHomeErrView.View,ExamHomeErrAdapter.ExamGroupOnClick, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamHomeErrActivity(BaseActivity<?> mActivity, PurchBean bean)
    {
        Intent intent = new Intent(mActivity,ExamHomeErrActivity.class);
        intent.putExtra("purch",bean);
        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home_err);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
        text_top_centent.setText("");
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_home_err;

    private PurchBean bean_purch;

    private UserBean bean_user;

    private ArrayList<ExamNodeBean> mlist_node;

    private ExamHomeErrAdapter adapter;

    private ExamNodeBean bean_node;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
    }

    @Override
    public void initData() {

        mPresenter = new ExamHomeErrPresenter(this);
        bean_user = SaveUtils.getUser(this);
        Object obj =  getIntent().getSerializableExtra("purch");
        mlist_node = new ArrayList<>();
        adapter = new ExamHomeErrAdapter(mlist_node,this);
        listview_home_err.setAdapter(adapter);
        adapter.setExamGroupClick(this);
        adapter.notifyDataSetChanged();

        bean_node = null;
        if(obj!=null && obj instanceof  PurchBean)
        {
            bean_purch = (PurchBean) obj;
            mPresenter.getExamNode(this,bean_user.getUid());
        }
        else
        {
            text_top_centent.setText("");
            showLongToast("暂无错题记录");
        }
    }

    @Override
    public void setExamNode(List<ExamNodeBean> list_node) {
        mlist_node.clear();
        if(list_node!=null && list_node.size()>0)
        {
            text_top_centent.setText(bean_purch.getAbstract());
            mlist_node.addAll(list_node);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setExamNodeErr(ArrayList<ExamTitleBean> list_title) {

        if(list_title==null || list_title.size()<=0)
        {
            showLongToast("暂无试题");
            return;
        }
        if(flag_err)
        {
            String ids = ExamUtils.getExamTtile(list_title);
            if(!MyCheckUtils.isEmpty(ids))
            {
                ExamActivity.startExamActivity(this,bean_node.getEID(),bean_node.getName(),ids,false,true,false,false);
            }
            else
            {
                showLongToast("暂无试题");
            }
        }
        else
        {
            String ids = ExamUtils.getExamTtile(list_title);
            ExamErrTitleActivity.startExamErrTitleActivity(this,bean_node.getEID(),bean_node.getName(),ids,false,true);
        }

    }

    boolean flag_err = false;
    @Override
    public void ExamGo(ExamNodeBean bean) {
        MyLogUtils.e(TAG,"父类："+bean.getID()+"  "+bean.getName());
        bean_node = bean;
        flag_err = false;
        mPresenter.getExamNodeErr(getActivity(),bean_user.getUid(),bean.getID());
    }

    @Override
    public void ExamErrGo(ExamNodeBean bean) {
        MyLogUtils.e(TAG,"点击 重新练习");
        bean_node = bean;
        flag_err = true;
        mPresenter.getExamNodeErr(getActivity(),bean_user.getUid(),bean.getID());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
        }
    }
}
