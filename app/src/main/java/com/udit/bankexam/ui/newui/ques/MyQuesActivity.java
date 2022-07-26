package com.udit.bankexam.ui.newui.ques;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.udit.bankexam.R;
import com.udit.bankexam.adapter.MyQuesAdapter;
import com.udit.bankexam.bean.AddMsg;
import com.udit.bankexam.bean.Myques;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class MyQuesActivity extends BaseActivity {

    private ImageView img_top_return;
    private TextView text_top_centent;
    private RecyclerView rv;
    private MyQuesAdapter adapter;
    private LinearLayout rl_no_date;
    private int pageNo = 1;
    private String pageSize = "10";
    private boolean mIsRefreshing = false;//是否刷新中
    private SwipeRefreshLayout refresh;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_ques);
    }

    @Override
    public void initViews(Bundle bundle) {
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rl_no_date = (LinearLayout) findViewById(R.id.rl_no_date);
        img_top_return = (ImageView) findViewById(R.id.img_top_return);
        text_top_centent = (TextView) findViewById(R.id.text_top_centent);
        text_top_centent.setText("我的问题");
        initRefresh();
    }

    @Override
    public void initListeners() {
        //返回
        img_top_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
    }

    @Override
    public void initData() {
        myQues(false);
    }

    //--------------------------------工具类--------------------------------
    private void initRefresh() {
        refresh.setColorSchemeResources(R.color.color_ff558cf4);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mIsRefreshing) {
                    return;
                }
                mIsRefreshing = true;
                pageNo = 1;
                myQues(false);
            }
        });
    }

    private void stopRefresh() {
        mIsRefreshing = false;
        if (refresh != null) {
            //关闭掉刷新的ui
            if (refresh.isRefreshing()) {
                refresh.setRefreshing(false);
            }
        }
    }

    //加载更多监听
    private RecyclerView.OnScrollListener OnLoadMoreListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Log.e("测试滑动", "滑动中");
                boolean isBottom = isVisBottom(recyclerView);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && isBottom && !mIsRefreshing) {
                    //还有数据
                    pageNo++;
                    mIsRefreshing = true;
                    myQues(true);
                }
            }
        };
    }

    protected boolean isVisBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recyclerView.SCROLL_STATE_IDLE) {
//            LogUtils.logE("测试刷新: ", "刷新");
            return true;
        } else {
//            LogUtils.logE("测试刷新: ", "不刷新");
            return false;
        }
    }

    //----------------------------接口----------------------------
    private void myQues(final boolean isBottom) {
        try {
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("pageNo", pageNo + "");
            map_params.put("pageSize", pageSize);
            map_params.put("sessionKey", sessionKey);
            setHttp(map_params, IHTTP.QUES_MY, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                    if (MyQuesActivity.this.isFinishing() || MyQuesActivity.this.isDestroyed()) {
                        return;
                    }
                    stopRefresh();
                }

                @Override
                public void doHttpResponse(String json) {
                    if (MyQuesActivity.this.isFinishing() || MyQuesActivity.this.isDestroyed()) {
                        return;
                    }
                    stopRefresh();
                    MyLogUtils.e("测试新接口", "json=" + json);
                    Myques bean = new Gson().fromJson(json, Myques.class);
                    if (bean.getCode() == 200) {
                        if (adapter == null) {
                            adapter = new MyQuesAdapter(MyQuesActivity.this, new ArrayList<Myques.DataBean.ResponseBean.RowsBean>(),
                                    new MyQuesAdapter.ClickCall() {
                                        @Override
                                        public void clickItem(String id) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("id", id);
                                            changeAct(bundle, QuesDetailsActivity.class);
                                        }
                                    });
                            rv.setAdapter(adapter);
                        }
                        rv.addOnScrollListener(OnLoadMoreListener());
                    }
                    if (bean.getData().getResponse().getCount() > 10) {
                        rv.addOnScrollListener(OnLoadMoreListener());
                    }
                    if (isBottom) {
                        adapter.addBottomDate(bean.getData().getResponse().getRows());
                    } else {
                        adapter.refresh(bean.getData().getResponse().getRows());
                    }
                    rv.setVisibility(adapter.getItemCount() > 0 || bean.getData().getResponse().getCount() > 0 ? View.VISIBLE : View.GONE);
                    rl_no_date.setVisibility(adapter.getItemCount() > 0 || bean.getData().getResponse().getCount() > 0 ? View.GONE : View.VISIBLE);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }

    protected void setHttp(HashMap<String, String> map, String address, IHttpResponseListener listener)
            throws Exception {
        try {
            RequestObject object = new RequestObject(address, map);
            new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, listener);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
