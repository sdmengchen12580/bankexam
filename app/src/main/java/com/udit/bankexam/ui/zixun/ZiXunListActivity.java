package com.udit.bankexam.ui.zixun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.NewBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZiXunBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.zixun.ZiXunListPresenter;
import com.udit.bankexam.presenter.zixun.ZiXunPresenter;
import com.udit.bankexam.ui.MyWebUriActivity;
import com.udit.bankexam.ui.zixun.adapter.NewsAdapter;
import com.udit.bankexam.ui.zixun.adapter.ZiXunAdapter;
import com.udit.bankexam.utils.PhotoUtils;
import com.udit.bankexam.view.zixun.ZiXunListView;
import com.udit.frame.common.pullrefresh.PullToRefreshBase;
import com.udit.frame.common.pullrefresh.PullToRefreshListView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.PhonetUtil;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2018-06-08.
 */

public class ZiXunListActivity extends BaseActivity<ZiXunListPresenter> implements ZiXunListView.View, View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();

    public static void startZiXunListActivity(BaseActivity<?> mActivity, String name) {
        Intent intent = new Intent(mActivity, ZiXunListActivity.class);
        intent.putExtra("name", name);
        mActivity.startActivity(intent);
    }


    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView pull_listview;

    private int page = Constant.ExamData.INIT_PAGE;

    private UserBean bean_user;
    private PullToRefreshListView pull_zixun;

    private List<NewBean> mlist_zx;

    private NewsAdapter adapter;


    private Context mContext;

    private String name;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_zixun);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        name = getIntent().getStringExtra("name");
        if (name == null)
            name = "咨询";
        text_top_centent.setText(name);
        pull_listview = pull_zixun.getRefreshableView();
        pull_listview.setDividerHeight(50);
        pull_listview.setDivider(new ColorDrawable(getResources().getColor(R.color.back)));
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        pull_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewBean bean = mlist_zx.get(position);
                ZiXunDetailActivity.startZiXunDetailActivity((BaseActivity<?>) mContext, bean);
                //  MyWebUriActivity.startMyWebUriActivity((BaseActivity<?>) mContext,"详情",bean.getLinkurl());
            }
        });

        pull_zixun.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                try {
                    Log.e("设置时间", "1111111");
                    pull_zixun.setTime(PhotoUtils.getNetTimeA());
                } catch (Exception e){}
                MyLogUtils.e(TAG, "onPullDownToRefresh-刷新数据");
                Log.e("测试数据加载", "page=" + page);
                page = 1;
                mPresenter.getNews(mContext, name, Constant.ExamData.INIT_PAGE, Constant.ExamData.COUNT_PAGE);
                // mPresenter.getMessage(bean_user.getUid(), Constant.ExamData.INIT_PAGE,Constant.ExamData.COUNT_PAGE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyLogUtils.e(TAG, "onPullUpToRefresh-请求新数据");
                page++;
                mPresenter.getNews(mContext, name, page, Constant.ExamData.COUNT_PAGE);
                //mPresenter.getMessage(bean_user.getUid(),page,Constant.ExamData.COUNT_PAGE);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new ZiXunListPresenter(this);
        mContext = this;
        mlist_zx = new ArrayList<>();
        adapter = new NewsAdapter(mlist_zx, this);
        pull_listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mPresenter.getNews(mContext, name, Constant.ExamData.INIT_PAGE, Constant.ExamData.COUNT_PAGE);
    }


    @Override
    public void setNews(List<NewBean> list) {
        if (list != null) {
            if (page == 1) {
                Log.e("测试数据加载", "page == 1时候=" + list.get(0).getScreen());
                mlist_zx.clear();
                mlist_zx.addAll(list);
                adapter.notifyDataSetChanged();
            } else {
                mlist_zx.removeAll(list);
                mlist_zx.addAll(list);
                adapter.notifyDataSetChanged();
            }
            if (page <= 1)
                page = 1;
            int num = mlist_zx.size();
            int total = num % ((page) * Constant.ExamData.COUNT_PAGE);
            if (total != 0) {
                page--;
            }
            if (page < Constant.ExamData.INIT_PAGE)
                page = Constant.ExamData.INIT_PAGE;
        } else {
            if (page == 1) {
                mlist_zx.clear();
                if (list == null) {
                    adapter.notifyDataSetChanged();
                } else {
                    mlist_zx.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            } else {

                int start = (page - 1) * Constant.ExamData.COUNT_PAGE;
                if (start <= 0)
                    start = 0;
                for (; mlist_zx != null && start < mlist_zx.size(); start++) {
                    mlist_zx.remove(start);
                }

                page--;
                if (page <= 1)
                    page = 1;
                adapter.notifyDataSetChanged();
            }

        }


        pull_zixun.onPullUpRefreshComplete();
        pull_zixun.onPullDownRefreshComplete();
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
}
