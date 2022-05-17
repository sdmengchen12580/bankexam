package com.udit.bankexam.ui.newui.yaoqing;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.adapter.YqDetailAdapter;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.frame.freamwork.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class YaoqingDetailActivity extends BaseActivity {

    private ImageView img_top_return;
    private RecyclerView rv;
    private YqDetailAdapter yqDetailAdapter;
    private TextView text_top_centent;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_yaoqing_detail);
    }

    @Override
    public void initViews(Bundle bundle) {
        text_top_centent = (TextView) findViewById(R.id.text_top_centent);
        text_top_centent.setText("邀请记录");
        img_top_return = (ImageView) findViewById(R.id.img_top_return);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
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
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        yqDetailAdapter = new YqDetailAdapter(this, list);
        rv.setAdapter(yqDetailAdapter);
    }
}
