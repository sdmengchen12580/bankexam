package com.udit.bankexam.ui.exam_data_history;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.HisPractBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_data_history.ExamDataHistoryPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_data_history.adapter.ExamDataHistoryAdapter;
import com.udit.bankexam.ui.exam_report_data.adapter.SJAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_data_history.ExamDataHistoryView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamDataHistoryActivity extends BaseActivity<ExamDataHistoryPresenter> implements ExamDataHistoryView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamDataHistoryActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamDataHistoryActivity.class));

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_data_history);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
    }

    private ImageView img_top_return;


    private ListView listview_data_history;

    private List<HisPractBean> mlist;

    private ExamDataHistoryAdapter adapter;

    private SJAdapter adapter_sj;

    private List<PurchBean> mlist_purch;

    private UserBean bean_user;

    private String name;

    private boolean flag_zhineng = false;

    private HisPractBean hisPractBean;

    private PurchBean purchBean;

    private TextView radiobutton_lx;

    private TextView radiobutton_sj;

    private int selectId = 0;

    private TextView tv_line_left;

    private TextView tv_line_right;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        findViewById(R.id.radiobutton_lx).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ExamDataHistoryActivity.this.radiobutton_lx.setTextColor(Color.parseColor("#558CF4"));
                ExamDataHistoryActivity.this.radiobutton_sj.setTextColor(Color.parseColor("#333333"));
                ExamDataHistoryActivity.this.tv_line_left.setVisibility(View.VISIBLE);
                ExamDataHistoryActivity.this.tv_line_right.setVisibility(View.GONE);
                ExamDataHistoryActivity.this.selectId = 0;
                ((ExamDataHistoryPresenter) ExamDataHistoryActivity.this.mPresenter).getHisPract(ExamDataHistoryActivity.this.bean_user.getUid());
            }
        });
        findViewById(R.id.radiobutton_sj).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ExamDataHistoryActivity.this.radiobutton_lx.setTextColor(Color.parseColor("#333333"));
                ExamDataHistoryActivity.this.radiobutton_sj.setTextColor(Color.parseColor("#558CF4"));
                ExamDataHistoryActivity.this.tv_line_left.setVisibility(View.GONE);
                ExamDataHistoryActivity.this.tv_line_right.setVisibility(View.VISIBLE);
                ExamDataHistoryActivity.this.selectId = 1;
                ((ExamDataHistoryPresenter) ExamDataHistoryActivity.this.mPresenter).getPurch(ExamDataHistoryActivity.this.bean_user.getUid());
            }
        });


        listview_data_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExamDataHistoryActivity.this.selectId == 0) {//练习
                    hisPractBean = mlist.get(position);
                    name = hisPractBean.getKeyWord();
                    flag_zhineng = true;
                    mPresenter.getHisExam(bean_user.getUid(), hisPractBean.getPID());
                } else {//试卷
                    purchBean = mlist_purch.get(position);
                    name = purchBean.getAbstract();
                    flag_zhineng = false;
                    mPresenter.getExam(bean_user.getUid(), purchBean.getLinkID());

                }
            }
        });
    }

    private String shouye_id;

    @Override
    public void initData() {

        mPresenter = new ExamDataHistoryPresenter(this);

        mlist_purch = new ArrayList<>();

        mlist = new ArrayList<>();

        adapter_sj = new SJAdapter(mlist_purch, this);

        adapter = new ExamDataHistoryAdapter(mlist, this);

        bean_user = SaveUtils.getUser(this);
        shouye_id = SaveUtils.getExamShouYe(this);

        mPresenter.getHisPract(bean_user.getUid());
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
    public void setHisPract(List<HisPractBean> list) {

        mlist.clear();
        mlist_purch.clear();
        if (list != null && list.size() > 0) {
            for (int i = 0; list != null && i < list.size(); i++) {
                HisPractBean bean = list.get(i);
                String key = bean.getKeyWord();
                String time = bean.getPDate();
                if (bean.getPType().equals("智能练习")) {
                    bean.setKeyWord(bean.getPType() + "（考点类型）：" + key);
                } else
                    bean.setKeyWord(bean.getPType() + "（考试类型）：" + key);

                bean.setPDate("组卷时间：" + time);
            }
            mlist.addAll(list);
        } else {
            showLongToast("暂无智能练习/组卷记录");
        }
        if (listview_data_history.getAdapter() instanceof ExamDataHistoryAdapter) {

        } else
            listview_data_history.setAdapter(adapter);


        adapter.notifyDataSetChanged();
        // mPresenter.getHisExPract(bean_user.getUid());
    }

    @Override
    public void setHisExPract(List<HisPractBean> list) {

        if (list != null && list.size() > 0) {

            for (int i = 0; list != null && i < list.size(); i++) {
                HisPractBean bean = list.get(i);
                String key = bean.getKeyWord();
                String time = bean.getPDate();
                bean.setKeyWord("智能组卷（考试类型）：" + key);
                // bean.setPDate("组卷时间："+time);
            }


            mlist.addAll(list);


        }
        if (listview_data_history.getAdapter() instanceof ExamDataHistoryAdapter) {

        } else {
            listview_data_history.setAdapter(adapter);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void setPurch(List<PurchBean> list) {


        mlist_purch.clear();
        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist_purch.addAll(list);
            if (!MyCheckUtils.isEmpty(shouye_id)) {
                PurchBean bean = new PurchBean();
                bean.setUid(bean_user.getUid());
                bean.setLinkID(shouye_id);
                mlist_purch.remove(bean);
            }
            listview_data_history.setAdapter(adapter_sj);
        } else {
            showLongToast("暂无试卷记录");
        }
        if (listview_data_history.getAdapter() instanceof SJAdapter) {

        } else {
            listview_data_history.setAdapter(adapter_sj);
        }

        adapter_sj.notifyDataSetChanged();

    }

    @Override
    public void setExam(List<ExamTitleBean> list) {
        if (list != null && list.size() > 0) {
            String ids = ExamUtils.getExamTtile(list);
            if (!MyCheckUtils.isEmpty(ids)) {
                if (flag_zhineng) {
                    ExamActivity.startExamActivity(this, hisPractBean.getPID(), hisPractBean.getKeyWord(), ids, true, false, false, false);
                } else {
                    ExamActivity.startExamActivity(this, purchBean.getLinkID(), purchBean.getAbstract(), ids, false, false, false, false);
                }

            } else {
                showLongToast("暂无试题");
            }


        } else
            showLongToast("暂无试题");


    }
}
