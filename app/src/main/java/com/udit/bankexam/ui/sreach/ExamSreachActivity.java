package com.udit.bankexam.ui.sreach;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.sreach.ExamSreachPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_err.ExamErrDetailActivity;
import com.udit.bankexam.ui.exam_err.ExamErrTitleActivity;
import com.udit.bankexam.ui.sreach.adapter.ExamSreachAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.sreach.ExamSreachView;
import com.udit.frame.common.pullrefresh.PullToRefreshBase;
import com.udit.frame.common.pullrefresh.PullToRefreshListView;
import com.udit.frame.common.view.PullToRefreshView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/4/24.
 */

public class ExamSreachActivity extends BaseActivity<ExamSreachPresenter> implements ExamSreachView.View, View.OnClickListener, ExamSreachAdapter.ExamListener {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_eaxmsreach);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        pull_listview = pull_sreach.getRefreshableView();
    }

    private PullToRefreshListView pull_sreach;

    private ListView pull_listview;

    private EditText edit_top_sreach;

    private TextView text_top_sreach;

    private UserBean bean_user;

    private List<ExamBean> mlist_bean;

    private ExamSreachAdapter adapter;

    private ImageView img_delete;

    private int page = Constant.ExamData.INIT_PAGE;

    private Context mContext;

    @Override
    public void initListeners() {
        text_top_sreach.setOnClickListener(this);

        img_delete.setOnClickListener(this);

       /* pull_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyLogUtils.e(TAG,"setOnItemClickListener");
                ExamBean bean = mlist_bean.get(position);

                ExamActivity.startExamActivity((BaseActivity<?>) mContext,bean);


            }
        });*/

        pull_sreach.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyLogUtils.e(TAG, "onPullDownToRefresh-刷新数据");
                String text = edit_top_sreach.getText().toString();
                if (!MyCheckUtils.isEmpty(text)) {
                    mPresenter.sreach(bean_user.getUid(), text, Constant.ExamData.INIT_PAGE + "", Constant.ExamData.COUNT_PAGE + "");
                } else {
                    showLongToast("请填写搜索题目");
                    pull_sreach.onPullUpRefreshComplete();
                    pull_sreach.onPullDownRefreshComplete();
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyLogUtils.e(TAG, "onPullUpToRefresh-请求新数据");
                String text = edit_top_sreach.getText().toString();
                if (!MyCheckUtils.isEmpty(text)) {
                    mPresenter.sreach(bean_user.getUid(), text, page + "", Constant.ExamData.COUNT_PAGE + "");
                } else {
                    showLongToast("请填写搜索题目");
                    pull_sreach.onPullUpRefreshComplete();
                    pull_sreach.onPullDownRefreshComplete();
                }
            }
        });
    }

    @Override
    public void initData() {
        mContext = this;
        mPresenter = new ExamSreachPresenter(this);

        bean_user = SaveUtils.getUser(this);
        page = Constant.ExamData.INIT_PAGE;

        mlist_bean = new ArrayList<>();
        adapter = new ExamSreachAdapter(mlist_bean, this, this);
        pull_listview.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void setExam(List<ExamBean> list) {

        if (list != null) {
            if (page == 1) {
                mlist_bean.clear();
                mlist_bean.addAll(list);
                adapter.notifyDataSetChanged();
            } else {

                mlist_bean.removeAll(list);
                mlist_bean.addAll(list);
                adapter.notifyDataSetChanged();
            }
            if (page <= 1)
                page = 1;
            int num = mlist_bean.size();
            int total = num % ((page) * Constant.ExamData.COUNT_PAGE);
            if (total != 0) {
                page--;
            }
            if (page < Constant.ExamData.INIT_PAGE)
                page = Constant.ExamData.INIT_PAGE;
        } else {
            if (page == 1) {
                mlist_bean.clear();
                if (list == null) {
                    adapter.notifyDataSetChanged();
                } else {
                    mlist_bean.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            } else {

                int start = (page - 1) * Constant.ExamData.COUNT_PAGE;
                if (start <= 0)
                    start = 0;
                for (; mlist_bean != null && start < mlist_bean.size(); start++) {
                    mlist_bean.remove(start);
                }

                page--;
                if (page <= 1)
                    page = 1;
                adapter.notifyDataSetChanged();
            }

        }

        pull_sreach.onPullUpRefreshComplete();
        pull_sreach.onPullDownRefreshComplete();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_delete:
                edit_top_sreach.setText("");

                break;
            case R.id.text_top_sreach:
                String text = edit_top_sreach.getText().toString();


                if (!MyCheckUtils.isEmpty(text)) {
                    mPresenter.sreach(bean_user.getUid(), text, page + "", Constant.ExamData.COUNT_PAGE + "");
                } else {
                    showLongToast("请填写搜索题目");
                }
                break;
        }
    }

    @Override
    public void setExamStart(ExamBean bean) {

        MyLogUtils.e(TAG, "setOnItemClickListener");
        ArrayList<ExamBean> list = new ArrayList<>();
        list.add(bean);
        String ids = ExamUtils.getExamBeanList(list);
        if (!MyCheckUtils.isEmpty(ids)) {
            ExamErrTitleActivity.startExamErrTitleActivity((BaseActivity<?>) mContext, null, "搜索", ids, false, false);
        } else {
            showLongToast("暂无此题解析");
        }

    }
}
