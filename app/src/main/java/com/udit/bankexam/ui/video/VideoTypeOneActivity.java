package com.udit.bankexam.ui.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.video.VideoTypeOnePresenter;
import com.udit.bankexam.ui.video.adapter.TypeOneAdapter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.video.VideoTypeOneView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class VideoTypeOneActivity extends BaseActivity<VideoTypeOnePresenter> implements VideoTypeOneView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startVideoTypeOneActivity(BaseActivity<?> mActivity)
    {
        mActivity.startActivityForResult(new Intent(mActivity,VideoTypeOneActivity.class), Constant.RESULT.RESULT_VIDEO_TYPE_ONE);
    }
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_videotypeone);
    }

    private ListView listview_typeone;

    private TypeOneAdapter adapter;

    private List<String> mlist_type;

    private ImageView img_top_return;

    private TextView text_top_centent;

    private BaseActivity<?> mContext;


    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
        text_top_centent.setText("视频选择");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        listview_typeone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyLogUtils.e(TAG,"onItemClick:"+position);
                String type =  mlist_type.get(position);
                SaveUtils.saveVideoTypeOne(VideoTypeOneActivity.this,type);
                Intent intent = mContext.getIntent().putExtra("videoTypeOne",type);
                mContext.setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {

        mPresenter = new VideoTypeOnePresenter(this);

        mContext = this;

        mlist_type = new ArrayList<>();
        adapter = new TypeOneAdapter(mlist_type,this);
        listview_typeone.setAdapter(adapter);
        UserBean bean = SaveUtils.getUser(this);

        mPresenter.getTypeOne(bean.getUid());
    }

    @Override
    public void setTypeOne(List<String> list) {
        if(list!=null)
        {
            mlist_type.clear();
            mlist_type.addAll(list);
            adapter.notifyDataSetChanged();
        }
        else
        {
            mlist_type.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_top_return:
                finish();
                break;
        }
    }
}
