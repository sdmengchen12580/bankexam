package com.udit.bankexam.ui.newui.ques;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lidroid.xutils.util.LogUtils;
import com.udit.bankexam.R;
import com.udit.bankexam.adapter.QuesInnerAdapter;
import com.udit.bankexam.bean.LoginOutBean;
import com.udit.bankexam.bean.QuesList;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.other.SettingActivity;
import com.udit.bankexam.ui.user.LoginActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuesInnerFragment extends Fragment {

    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private QuesInnerAdapter quesInnerAdapter;
    private LinearLayout rl_no_date;
    private String code;
    private int pageNo = 1;
    private String pageSize = "10";
    private boolean mIsRefreshing = false;//是否刷新中

    public QuesInnerFragment() {
        // Required empty public constructor
    }

    public static QuesInnerFragment newInstance(String code) {
        QuesInnerFragment fragment = new QuesInnerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ques_inner, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rl_no_date = (LinearLayout) view.findViewById(R.id.rl_no_date);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
        code = getArguments().getString("code");
        initRefresh();
        quesList(false);
    }

    protected void changeAct(Bundle paramBundle, Class<?> paramClass) {
        Intent intent = new Intent();
        if (paramBundle != null)
            intent.putExtras(paramBundle);
        intent.setClass(getActivity(), paramClass);
        getActivity().startActivity(intent);
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
            map_params.put("q", "");
            map_params.put("questionCatCode", code);
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
                            quesInnerAdapter = new QuesInnerAdapter(getContext(), new ArrayList<QuesList.DataBean.ResponseBean.RowsBean>(), new QuesInnerAdapter.ClickCall() {
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
                        if (quesInnerAdapter.getItemCount() > 0) {
                            rv.setVisibility(View.VISIBLE);
                            rl_no_date.setVisibility(View.GONE);
                        } else {
                            rv.setVisibility(View.GONE);
                            rl_no_date.setVisibility(View.VISIBLE);
                        }
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
