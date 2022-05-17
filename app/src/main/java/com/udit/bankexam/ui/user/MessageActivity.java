package com.udit.bankexam.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.MessageBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.user.MessagePresenter;
import com.udit.bankexam.ui.MyWebActivity;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam.ExamListActivity;
import com.udit.bankexam.ui.exam_mk.ExamMkDetailActivity;
import com.udit.bankexam.ui.user.adapter.MessageAdapter;
import com.udit.bankexam.ui.video.VideoTypeTwoActivityNew;
import com.udit.bankexam.ui.zhibo.KeChengDetailActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.PullToRefreshNoDiverListView;
import com.udit.bankexam.view.user.MessageView;
import com.udit.frame.common.pullrefresh.PullToRefreshBase;
import com.udit.frame.common.pullrefresh.PullToRefreshListView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/6/7.
 * 我的消息
 */

public class MessageActivity extends BaseActivity<MessagePresenter> implements MessageView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startMessageActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, MessageActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_message);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("我的消息");
        pull_listview = pull_message.getRefreshableView();
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private PullToRefreshNoDiverListView pull_message;

    private ListView pull_listview;

    private int page = Constant.ExamData.INIT_PAGE;

    private UserBean bean_user;

    private List<MessageBean> mlist_bean;

    private MessageAdapter adapter;

    @Override
    public void initListeners() {

        img_top_return.setOnClickListener(this);
        pull_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MessageBean bean = mlist_bean.get(position);
//            showLongToast(bean.getmType());
                String title = bean.getmType();
                String path = bean.getLinkID();
                if (title.equals("全员推送试卷")) {
                    ExamListActivity.startExamListActivity((BaseActivity<?>) getActivity(), path, title);
                } else if (title.equals("全员推送模考")) {
                    ExamMkDetailActivity.startExamMkActivity((BaseActivity<?>) getActivity(), path, title);
                } else if (title.equals("全员推送课程")) {
                    KeChengDetailActivity.startKeChengDetailActivity((BaseActivity<?>) getActivity(), path, title);
                } else if (title.equals("全员推送视频")) {
                    VideoTypeTwoActivityNew.startVideoTypeTwoActivityNewByID((BaseActivity<?>) getActivity(), path);
                } else if (title.equals("全员推送")) {
                    MyWebActivity.startMyWebActivity((BaseActivity<?>) getActivity(), bean.getName(), bean.getMsg());
                } else if (title.equals("关联模考")) {
                    ExamMkDetailActivity.startExamMkActivity((BaseActivity<?>) getActivity(), path, title);
                } else if (title.equals("关联课程")) {
                    KeChengDetailActivity.startKeChengDetailActivity((BaseActivity<?>) getActivity(), path, title);
                } else if (title.equals("关联试卷")) {
                    ExamListActivity.startExamListActivity((BaseActivity<?>) getActivity(), path, title);
                } else if (title.equals("关联视频")) {
                    VideoTypeTwoActivityNew.startVideoTypeTwoActivityNewByID((BaseActivity<?>) getActivity(), path);
                }

            }
        });

        pull_message.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyLogUtils.e(TAG, "onPullDownToRefresh-刷新数据");

                mPresenter.getMessage(bean_user.getUid(), Constant.ExamData.INIT_PAGE, Constant.ExamData.COUNT_PAGE);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyLogUtils.e(TAG, "onPullUpToRefresh-请求新数据");
                page++;
                mPresenter.getMessage(bean_user.getUid(), page, Constant.ExamData.COUNT_PAGE);

            }

        });
    }

    @Override
    public void initData() {
        mPresenter = new MessagePresenter(this);

        bean_user = SaveUtils.getUser(this);
        page = Constant.ExamData.INIT_PAGE;

        mlist_bean = new ArrayList<>();
        adapter = new MessageAdapter(mlist_bean, this);
        pull_listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mPresenter.getMessage(bean_user.getUid(), Constant.ExamData.INIT_PAGE, Constant.ExamData.COUNT_PAGE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;

        }
    }

    @Override
    public void setMessage(List<MessageBean> list) {
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

        pull_message.onPullUpRefreshComplete();
        pull_message.onPullDownRefreshComplete();
    }
}
