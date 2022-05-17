package com.udit.bankexam.ui.exam_err;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_err.ExamErrPresenter;
import com.udit.bankexam.ui.exam_report_data.adapter.SJAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_err.ExamErrView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 * 错题本
 */
public class ExamErrActivity extends BaseActivity<ExamErrPresenter> implements ExamErrView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamErrActivity(BaseActivity<?> mActivity)
    {
        mActivity.startActivity(new Intent(mActivity,ExamErrActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_err);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_err;

    private SJAdapter adapter;

    private List<PurchBean> mlist_purch;

    private Context mContext;

    private String shouye_id;

    private UserBean bean_user;

    private PurchBean bean_purch;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
        text_top_centent.setText("错题本");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        listview_err.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean_purch = mlist_purch.get(position);
                if(!MyCheckUtils.isEmpty(shouye_id))
                {
                    MyLogUtils.e(TAG,"shouye_id:"+shouye_id+"   purch:"+bean_purch.getLinkID());
                    if(shouye_id.equals(bean_purch.getLinkID()))
                    {
                        ExamHomeErrActivity.startExamHomeErrActivity((BaseActivity<?>) mContext,bean_purch);
                    }
                    else
                        mPresenter.getHomeSJDTK(mContext,bean_user.getUid(),bean_purch.getLinkID());
                }
                else
                {
                    mPresenter.getHomeSJDTK(mContext,bean_user.getUid(),bean_purch.getLinkID());
                }

            }
        });
    }

    @Override
    public void initData() {
        mContext = this;

        shouye_id = SaveUtils.getExamShouYe(this);

        mPresenter = new ExamErrPresenter(this);

        mlist_purch = new ArrayList<>();

        adapter = new SJAdapter(mlist_purch,this);

        listview_err.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        listview_err.setAdapter(adapter);

        bean_user = SaveUtils.getUser(this);
        mPresenter.getSJ(bean_user.getUid());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_top_return:
                finish();
                break;
        }
    }

    @Override
    public void setSJ(List<PurchBean> list) {
        mlist_purch.clear();

        if(list!=null && list.size()>0)
        {
            mlist_purch.addAll(list);
        }
        else
        {
            showLongToast("您还未做过试卷，暂无错题本");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setExamTitle(List<ExamTitleBean> list) {
        if(list!=null && list.size()>0)
        {
            String ids =  ExamUtils.getExamTtile(list);
            if(!MyCheckUtils.isEmpty(ids))
            {
                ExamErrDetailActivity.startExamErrDetailActivity(this,bean_purch.getLinkID(),bean_purch.getAbstract(),ids,
                        false,false,false,false,false);
            }
            else
            {
                showLongToast("您还未做题目，没有错题集");
            }
        }
        else
        {
            showLongToast("您还未做题目，没有错题集");
        }
    }
}
