package com.udit.bankexam.ui.newui.quessearch;

import android.opengl.ETC1;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.udit.bankexam.R;
import com.udit.bankexam.adapter.QuesInnerAdapter;
import com.udit.bankexam.adapter.QuesSearchAdapter;
import com.udit.bankexam.bean.QuesList;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.newui.ques.QuesDetailsActivity;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchQuesActivity extends BaseActivity {

    private LinearLayout rl_no_date;
    private ImageView img_delect;
    private EditText et_content;
    private TextView tv_cancel;
    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int pageNo = 1;
    private String pageSize = "10";
    private boolean mIsRefreshing = false;//是否刷新中
    private QuesSearchAdapter quesInnerAdapter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_search_ques);
    }

    @Override
    public void initViews(Bundle bundle) {
        img_delect = (ImageView) findViewById(R.id.img_delect);
        rl_no_date = (LinearLayout) findViewById(R.id.rl_no_date);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        initRefresh();
    }

    @Override
    public void initListeners() {
        //取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });

        //搜索
        et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (et_content.getText().toString().trim().equals("")) {
                        showLongToast("请输入内容");
                        return true;
                    }
                    quesList(false);
                    return true;
                }
                return false;
            }
        });

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = et_content.getText().toString();
                img_delect.setVisibility(content.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });

        //删除
        img_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                et_content.setText("");
            }
        });
    }

    @Override
    public void initData() {

    }

    //--------------------------------工具类--------------------------------
    private void initRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.color_ff558cf4);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mIsRefreshing) {
                    return;
                }
                mIsRefreshing = true;
                pageNo = 1;
                quesList(false);
            }
        });
    }

    private void stopRefresh() {
        mIsRefreshing = false;
        if (swipeRefreshLayout != null) {
            //关闭掉刷新的ui
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
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
                    quesList(true);
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

    //--------------------------------接口--------------------------------
    public void quesList(final boolean isBottom) {
        try {
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("pageNo", pageNo + "");
            map_params.put("pageSize", pageSize);
            map_params.put("q", et_content.getText().toString().trim());
            map_params.put("questionCatCode", "");
            setHttp(map_params, IHTTP.QUES_LIST, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                    stopRefresh();
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    QuesList bean = new Gson().fromJson(json, QuesList.class);
                    if (bean != null) {
                        if (quesInnerAdapter == null) {
                            quesInnerAdapter = new QuesSearchAdapter(SearchQuesActivity.this,
                                    new ArrayList<QuesList.DataBean.ResponseBean.RowsBean>(), new QuesSearchAdapter.ClickCall() {
                                @Override
                                public void clickItem(String id) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", id);
                                    changeAct(bundle, QuesDetailsActivity.class);
                                }
                            });
                            rv.setAdapter(quesInnerAdapter);
                        }
                        if (bean.getData().getResponse().getCount() > 10) {
                            rv.addOnScrollListener(OnLoadMoreListener());
                        }
                        if (isBottom) {
                            quesInnerAdapter.addBottomDate(bean.getData().getResponse().getRows());
                        } else {
                            quesInnerAdapter.refresh(bean.getData().getResponse().getRows());
                        }
                        rv.setVisibility(quesInnerAdapter.getItemCount() > 0 || bean.getData().getResponse().getCount() > 0 ? View.VISIBLE : View.GONE);
                        rl_no_date.setVisibility(quesInnerAdapter.getItemCount() > 0 || bean.getData().getResponse().getCount() > 0 ? View.GONE : View.VISIBLE);
                    }
                    stopRefresh();
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
