package com.udit.bankexam.ui.newui.ques;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.AddMsg;
import com.udit.bankexam.bean.TypeQuesBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.utils.DensityUtil;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.ViewHisGroup;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

public class WantQuesActivity extends BaseActivity {

    private ImageView img_top_return;
    private TextView text_top_centent;
    private TextView tv_tiwen;
    private EditText et_ques;
    private EditText et_title;
    private ViewHisGroup group;
    private String selecdId = "无";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_want_ques);
    }

    @Override
    public void initViews(Bundle bundle) {
        et_title = (EditText) findViewById(R.id.et_title);
        et_ques = (EditText) findViewById(R.id.et_ques);
        group = (ViewHisGroup) findViewById(R.id.group);
        img_top_return = (ImageView) findViewById(R.id.img_top_return);
        text_top_centent = (TextView) findViewById(R.id.text_top_centent);
        tv_tiwen = (TextView) findViewById(R.id.tv_tiwen);
        text_top_centent.setText("我要提问");
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

        //提交
        tv_tiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                addQuestion();
            }
        });
    }

    @Override
    public void initData() {
        getQuestionCats();
    }

    private void initSelfHis(final List<TypeQuesBean.DataBean.ResponseBean.RowsBean> list) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                DensityUtil.dip2px(this, 30f));
        for (int i = 0; i < list.size(); i++) {
            final TextView textView = new TextView(this);
            textView.setText(list.get(i).getQuestionCatCodeName());
            textView.setMaxLines(1);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(DensityUtil.dip2px(this, 16.5f),
                    DensityUtil.dip2px(this, 0f),
                    DensityUtil.dip2px(this, 16.5f),
                    DensityUtil.dip2px(this, 0f));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setBackgroundResource(R.drawable.shape_15_stroke_999999);
            group.addView(textView, lp);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                for (int j = 0; j < group.getChildCount(); j++) {
                                                    TextView textView = (TextView) group.getChildAt(j);
                                                    textView.setTextColor(Color.parseColor("#666666"));
                                                    textView.setBackgroundResource(R.drawable.shape_15_stroke_999999);
                                                }
                                                ((TextView) group.getChildAt(finalI)).setTextColor(Color.parseColor("#ffffff"));
                                                ((TextView) group.getChildAt(finalI)).setBackgroundResource(R.drawable.shape_15_corner_558cf4_bg);
                                                selecdId = list.get(finalI).getQuestionCatCode();
                                            }
                                        }
            );
        }
    }

    //------------------------------接口------------------------------
    private void getQuestionCats() {
        try {
            HashMap<String, String> map_params = new HashMap<>();
            setHttp(map_params, IHTTP.QUES_TYPE, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    TypeQuesBean bean = new Gson().fromJson(json, TypeQuesBean.class);
                    initSelfHis(bean.getData().getResponse().getRows());
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }

    private void addQuestion() {
        if (selecdId.equals("无")) {
            showLongToast("请先选择您的问题分类");
            return;
        }
        if (et_title.getText().toString().trim().equals("")) {
            showLongToast("请输入您的提问标题");
            return;
        }
        if (et_ques.getText().toString().trim().equals("")) {
            showLongToast("请输入您的提问内容");
            return;
        }
        try {
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("content", et_ques.getText().toString().trim());
            map_params.put("questionCatCode", selecdId);
            map_params.put("sessionKey", sessionKey);
            map_params.put("title", et_title.getText().toString().trim());
            setHttp(map_params, IHTTP.QUES_ADD, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    AddMsg bean = new Gson().fromJson(json, AddMsg.class);
                    if (bean.getCode() == 200) {
                        showLongToast("问题添加成功");
                        finish();
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
