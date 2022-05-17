package com.udit.bankexam.ui.exam_robot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.HisPractBean;
import com.udit.bankexam.bean.TypeBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_robot.ExamRobotPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_data_history.adapter.ExamDataHistoryAdapter;
import com.udit.bankexam.ui.exam_robot.adapter.TypeAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_robot.ExamRobotView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 * 智能组卷
 */

public class ExamRobotActivity extends BaseActivity<ExamRobotPresenter> implements ExamRobotView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamRobotActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamRobotActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_robot);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_type;


    private List<HisPractBean> mlist;

    private HisPractBean hisPractBean;

    private ExamDataHistoryAdapter adapter;
    //private List<TypeBean> mlist;

    //private TypeAdapter adapter;

    private TextView textview_robot_btn;

    private UserBean bean_user;

    private Context mContext;
    private TextView tv_date;
    private RelativeLayout bottom_layout;
    private RelativeLayout rl_no_date;

    //  private TypeBean mTypeBean;
    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("智能组卷");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        listview_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hisPractBean = mlist.get(position);
                mPresenter.getHisExamList(mContext, bean_user.getUid(), hisPractBean.getPID());
            }
        });
        textview_robot_btn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new ExamRobotPresenter(this);
        mContext = this;
        bean_user = SaveUtils.getUser(this);
        mlist = new ArrayList<>();
        adapter = new ExamDataHistoryAdapter(mlist, this);
        listview_type.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    protected void onResume() {
        super.onResume();
        ((ExamRobotPresenter) this.mPresenter).getExamHis(this, this.bean_user.getUid());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.textview_robot_btn:
                /*int selected =adapter.getSelectedIndex();
                TypeBean bean_type = mlist.get(selected);*/
                // MyLogUtils.e(TAG,"选中的内容："+bean_type.getName());
                mPresenter.getExamList(this, bean_user.getUid(), "");
                break;
        }
    }

    @Override
    public void setData(List<TypeBean> list) {
      /*  mlist.clear();
        if(list!=null && list.size()>0)
        {
            mlist.addAll(list);
            mTypeBean = mlist.get(0);
        }
        adapter.notifyDataSetChanged();*/
    }

    @Override
    public void setExamList(List<ExamTitleBean> list) {

        if (list != null && list.size() > 0) {
            String title_str = ExamUtils.getExamTtile(list);
            if (!MyCheckUtils.isEmpty(title_str)) {
                String name = "智能组卷";
                if (hisPractBean != null) {
                    name = hisPractBean.getKeyWord();
                }
                ExamActivity.startExamActivity(this, null, name, title_str, true, false, false, false);
                hisPractBean = null;
            } else {
                if (hisPractBean != null) {
                    showLongToast("您选择的试卷不见了");
                } else
                    showLongToast("暂无新的智能组卷");
                hisPractBean = null;
            }

            //  ExamActivity.startExamActivity(this,"智能组卷",title_str,true);
        } else {
            if (hisPractBean != null) {
                showLongToast("您选择的试卷不见了");
            } else
                showLongToast("暂无新的智能组卷");
            hisPractBean = null;
        }

    }

    @Override
    public void setHisExam(List<HisPractBean> list) {
        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
        }
        adapter.notifyDataSetChanged();
        listview_type.setVisibility((mlist.size() > 0) ? View.VISIBLE : View.GONE);
        rl_no_date.setVisibility((mlist.size() > 0) ? View.GONE : View.VISIBLE);
        tv_date.setVisibility((mlist.size() > 0) ? View.VISIBLE : View.GONE);
//        bottom_layout.setVisibility((mlist.size() > 0) ? View.VISIBLE : View.GONE);
    }
}
