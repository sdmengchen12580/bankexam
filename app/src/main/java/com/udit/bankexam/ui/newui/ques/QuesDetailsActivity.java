package com.udit.bankexam.ui.newui.ques;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.adapter.QuesCommtentAdapter;
import com.udit.bankexam.bean.CommentList;
import com.udit.bankexam.bean.Del;
import com.udit.bankexam.bean.Detail;
import com.udit.bankexam.bean.MyComment;
import com.udit.bankexam.bean.Zan;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.frame.common.circleImageView.CircleImageView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class QuesDetailsActivity extends BaseActivity {

    private ImageView img_top_return;
    private TextView text_top_centent;
    private QuesCommtentAdapter quesCommtentAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listview;
    private String id;
    //信息
    private CircleImageView img_user;
    private TextView tv_name;
    private TextView tv_title;
    private TextView tv_comment_num;
    private TextView tv_content;
    private TextView tv_commenmtnum;
    private TextView tv_looknum;
    private EditText et_content;
    private TextView tv_time;
    private int pageNo = 1;
    private String pageSize = "10";
    private boolean mIsRefreshing = false;//是否刷新中

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_ques_details);
    }

    @Override
    public void initViews(Bundle bundle) {
        id = getIntent().getExtras().getString("id");
        et_content = (EditText) findViewById(R.id.et_content);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        listview = (ListView) findViewById(R.id.listview);
        img_top_return = (ImageView) findViewById(R.id.img_top_return);
        text_top_centent = (TextView) findViewById(R.id.text_top_centent);
        text_top_centent.setText("详情");
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
        View viewHeader = LayoutInflater.from(getActivity()).inflate(R.layout.ques_header, null, false);
        listview.addHeaderView(viewHeader);
        tv_name = (TextView) viewHeader.findViewById(R.id.tv_name);
        img_user = (CircleImageView) viewHeader.findViewById(R.id.img_user);
        tv_time = (TextView) viewHeader.findViewById(R.id.tv_time);
        tv_title = (TextView) viewHeader.findViewById(R.id.tv_title);
        tv_content = (TextView) viewHeader.findViewById(R.id.tv_content);
        tv_commenmtnum = (TextView) viewHeader.findViewById(R.id.tv_commenmtnum);
        tv_looknum = (TextView) viewHeader.findViewById(R.id.tv_looknum);
        tv_comment_num = (TextView) viewHeader.findViewById(R.id.tv_comment_num);
        et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (et_content.getText().toString().trim().equals("")) {
                        showLongToast("请先输入评论内容");
                        return true;
                    }
                    addQuestionAnswer();
                    return true;
                }
                return false;
            }
        });
        detail();
        list(false);
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
                list(false);
            }
        });
    }

    //隐藏软键盘
    protected void hideKeyboard(EditText et_chat) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_chat.getWindowToken(), 0);
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
    private void isBottomLoad() {
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (!mIsRefreshing) {
                            //还有数据
                            pageNo++;
                            mIsRefreshing = true;
                            list(true);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    //--------------------------------接口--------------------------------
    public void detail() {
        try {
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("id", id);
            map_params.put("needIncrease", "1");
            setHttp(map_params, IHTTP.QUES_DETAIL, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {

                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    Detail bean = new Gson().fromJson(json, Detail.class);
                    if (bean != null) {
                        if (!bean.getData().getResponse().getRow().getUserAvatar().equals("")) {
                            ImageLoader.getInstance().displayImage(bean.getData().getResponse().getRow().getUserAvatar(), img_user);
                        }
                        tv_name.setText(bean.getData().getResponse().getRow().getUserNickName());
                        if (bean.getData().getResponse().getRow().getContent().equals("")) {
                            tv_content.setVisibility(View.GONE);
                        } else {
                            tv_content.setVisibility(View.VISIBLE);
                            tv_content.setText(bean.getData().getResponse().getRow().getContent());
                        }
                        if (bean.getData().getResponse().getRow().getTitle().equals("")) {
                            tv_title.setVisibility(View.GONE);
                        } else {
                            tv_title.setVisibility(View.VISIBLE);
                            tv_title.setText(bean.getData().getResponse().getRow().getTitle());
                        }
                        tv_commenmtnum.setText(bean.getData().getResponse().getRow().getAnswerNum() + "");
                        tv_looknum.setText(bean.getData().getResponse().getRow().getViewNum() + "");
                        tv_time.setText(bean.getData().getResponse().getRow().getAddTime());
                    }
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }


    public void list(final boolean isBottom) {
        try {
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("pageNo", pageNo + "");
            map_params.put("pageSize", pageSize);
            map_params.put("sessionKey", sessionKey);
            map_params.put("ykQuestionId", id);
            setHttp(map_params, IHTTP.QUES_COMMENT, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                    stopRefresh();
                }

                @Override
                public void doHttpResponse(String json) {
                    stopRefresh();
                    MyLogUtils.e("测试新接口", "json=" + json);
                    CommentList bean = new Gson().fromJson(json, CommentList.class);
                    if (bean != null) {
                        tv_comment_num.setText("（" + bean.getData().getResponse().getCount() + "条）");
                        if (bean.getData().getResponse().getCount() > 10) {
                            isBottomLoad();
                        }
                        if (quesCommtentAdapter == null) {
                            quesCommtentAdapter = new QuesCommtentAdapter(new ArrayList<CommentList.DataBean.ResponseBean.RowsBean>(),
                                    QuesDetailsActivity.this);
                            listview.setAdapter(quesCommtentAdapter);
                            quesCommtentAdapter.setClickCallBack(new QuesCommtentAdapter.ClickCallBack() {
                                @Override
                                public void zan(String id, int positon, boolean isZan) {
                                    if (!isZan) {
                                        praise(id, positon, isZan);
                                        return;
                                    }
                                    praiseCancel(id, positon, isZan);
                                }

                                @Override
                                public void img_delect(String id, int positon) {
                                    del(id, positon);
                                }
                            });
                        }
                        if (isBottom) {
                            quesCommtentAdapter.addDataBottom(bean.getData().getResponse().getRows());
                        } else {
                            quesCommtentAdapter.refresh(bean.getData().getResponse().getRows());
                        }
                    }
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }

    public void addQuestionAnswer() {
        try {
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("answerContent", et_content.getText().toString().trim());
            map_params.put("sessionKey", sessionKey);
            map_params.put("ykQuestionId", id);
            setHttp(map_params, IHTTP.QUES_COMMENT_MY, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    MyComment bean = new Gson().fromJson(json, MyComment.class);
                    if (bean != null) {
                        showLongToast("评论成功");
//                        int currentNum = Integer.parseInt(tv_commenmtnum.getText().toString());
//                        currentNum++;
//                        tv_commenmtnum.setText("" + currentNum);
//                        hideKeyboard(et_content);
//                        mIsRefreshing = true;
//                        pageNo = 1;
//                        list(false);
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }

    //赞
    public void praise(String idC, final int positon, final boolean isZan) {
        try {
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("sessionKey", sessionKey);
            map_params.put("ykQuestionAnswerId", idC);
            setHttp(map_params, IHTTP.QUES_ZAN, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {

                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    Zan bean = new Gson().fromJson(json, Zan.class);
                    if (bean != null && bean.getCode() == 200) {
//                        showLongToast("点赞成功");
                        quesCommtentAdapter.updateZan(positon, !isZan);
                        return;
                    }
                    showLongToast("点赞失败");
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }

    //取消赞
    private void praiseCancel(String idC, final int positon, final boolean isZan) {
        try {
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("sessionKey", sessionKey);
            map_params.put("ykQuestionAnswerId", idC);
            setHttp(map_params, IHTTP.QUES_ZAN_CANCEL, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {

                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    Zan bean = new Gson().fromJson(json, Zan.class);
                    if (bean != null && bean.getCode() == 200) {
//                        showLongToast("取消点赞成功");
                        quesCommtentAdapter.updateZan(positon, !isZan);
                        return;
                    }
                    showLongToast("取消点赞失败");
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }

    //删除
    private void del(String idC, final int positon) {
        try {
            hideKeyboard(et_content);
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("sessionKey", sessionKey);
            map_params.put("id", idC);
            setHttp(map_params, IHTTP.QUES_COMMENT_DELECT, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {

                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    Del bean = new Gson().fromJson(json, Del.class);
                    if (bean != null) {
                        if (bean.getCode() == 200) {
                            showLongToast("评论删除成功");
                            quesCommtentAdapter.delect(positon);
                            int currentNum = Integer.parseInt(tv_commenmtnum.getText().toString());
                            currentNum--;
                            tv_comment_num.setText("（" + currentNum + "条）");
                            tv_commenmtnum.setText("" + currentNum);
                        } else {
                            try {
                                Log.e("doHttpResponse: ", bean.getMessages().get(0).getMessage());
                                showLongToast(bean.getMessages().get(0).getMessage());
                            } catch (Exception e) {
                            }
                        }
                    }
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
