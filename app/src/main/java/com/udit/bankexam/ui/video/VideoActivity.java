package com.udit.bankexam.ui.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.VideoBean;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.video.VideoPresenter;
import com.udit.bankexam.ui.video.adapter.VideoAdapter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.video.VideoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/4/26.
 */

public class VideoActivity extends BaseActivity<VideoPresenter> implements VideoView.View, View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();

    public static void startVideoActivity(BaseActivity<?> mActivity, VideoTypeTwoBean bean) {
        Intent intent = new Intent(mActivity, VideoActivity.class);
        intent.putExtra("typetwo", bean);
        mActivity.startActivity(intent);
    }

    public static void startVideoActivity(BaseActivity<?> mActivity, String videoId, String videoName) {
        Intent intent = new Intent(mActivity, VideoActivity.class);
        intent.putExtra("videoId", videoId);
        intent.putExtra("videoName", videoName);
        mActivity.startActivity(intent);

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_video);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_video;


    private UserBean bean_user;

    private VideoTypeTwoBean bean_typetwo;

    private ArrayList<VideoBean> mlist;

    private VideoAdapter adapter;

    private BaseActivity<?> mContext;

    private LinearLayout ll_kefu;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        listview_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoInfoActivity.startVideoInfoActivity(mContext, mlist, position);
            }
        });
        ll_kefu.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mPresenter = new VideoPresenter(this);
        mContext = this;
        Object obj = getIntent().getSerializableExtra("typetwo");
        bean_user = SaveUtils.getUser(this);
        mlist = new ArrayList<>();
        adapter = new VideoAdapter(mlist, this);
        listview_video.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (obj != null && obj instanceof VideoTypeTwoBean) {
            bean_typetwo = (VideoTypeTwoBean) obj;
            text_top_centent.setText(bean_typetwo.getName());
            mPresenter.getVideList(bean_user.getUid(), bean_typetwo.getcID());
        } else {
            String videoId = getIntent().getStringExtra("videoId");
            String videoName = getIntent().getStringExtra("videoName");
            text_top_centent.setText(videoName);

            mPresenter.getVideList(bean_user.getUid(), videoId);
        }

    }

    @Override
    public void setVideo(List<VideoBean> list) {
        if (list != null) {
            mlist.clear();
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            mlist.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.ll_kefu:
                if (Utils.isQQAvailable(getActivity())) {
                    Utils.startQQ(getActivity(), Constant.QQ_ZIXUN);
                } else {
                    showLongToast("请先安装QQ");
                }
                break;
        }
    }


}
