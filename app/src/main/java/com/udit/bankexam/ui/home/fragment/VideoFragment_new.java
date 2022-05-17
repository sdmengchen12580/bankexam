package com.udit.bankexam.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.home.fragment.VideoPresenter;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.home.fragment.adapter.VideoAdapter;
import com.udit.bankexam.ui.pay.PaySelectActivity;
import com.udit.bankexam.ui.video.VideoActivity;
import com.udit.bankexam.ui.video.VideoTypeOneActivity;
import com.udit.bankexam.ui.video.VideoTypeTwoActivity;
import com.udit.bankexam.ui.video.VideoTypeTwoActivityNew;
import com.udit.bankexam.ui.video.adapter.TypeOneAdapter;
import com.udit.bankexam.ui.video.adapter.TypeOneAdapter_new;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.home.fragment.VideoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment_new extends BaseFragment<VideoPresenter> implements VideoView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static VideoFragment_new getIntance() {
        VideoFragment_new videoFragment_new = new VideoFragment_new();
        return videoFragment_new;
    }

    public static VideoFragment_new getIntance(boolean paramBoolean) {
        VideoFragment_new videoFragment_new = new VideoFragment_new();
        if (paramBoolean) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("needtitle", true);
            videoFragment_new.setArguments(bundle);
        }
        return videoFragment_new;
    }

    private View mView;

    private TextView text_top_centent;

    private ListView listview_video;

    private TypeOneAdapter_new adapter;

    private UserBean bean_user;

    private BaseActivity<?> mContext;
    private ImageView img_top_return;

    private LinearLayout ll_kefu;
    private RelativeLayout rl_title;

    private List<VideoTypeOneBean> mlist_type;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_video, null);
        return mView;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, this.mView, com.udit.bankexam.R.id.class);
            if (getArguments() != null && getArguments().containsKey("needtitle") && getArguments().getBoolean("needtitle")) {
                this.rl_title.setVisibility(View.VISIBLE);
                this.img_top_return.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        if (FastClickUtils.isFastClick()) {
                            VideoFragment_new.this.getActivity().finish();
                            return;
                        }
                    }
                });
            }
            this.text_top_centent.setText("视频");
            this.text_top_centent.setTag("");
            return;
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    @Override
    public void initListeners() {
        // ll_select_type.setOnClickListener(this);
        listview_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoTypeOneBean bean = mlist_type.get(position);
                VideoTypeTwoActivityNew.startVideoTypeTwoActivityNew(mContext, bean.getVType());
            }
        });
        ll_kefu.setOnClickListener(this);

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter = new VideoPresenter(this);


        mContext = (BaseActivity<?>) this.getActivity();
        mlist_type = new ArrayList<>();
        adapter = new TypeOneAdapter_new(mlist_type, getActivity());
        listview_video.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        bean_user = SaveUtils.getUser(getActivity());
        mPresenter.getTypeOne(bean_user.getUid());
        if (mlist_type != null && mlist_type.size() > 0)
            dismissProgressDialog();

    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getTypeOne(bean_user.getUid());
        if (mlist_type != null && mlist_type.size() > 0)
            dismissProgressDialog();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_select_type:

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
    }

    @Override
    public void setTypeList(List<VideoTypeTwoBean> list) {

    }

    @Override
    public void setTypeOne(List<VideoTypeOneBean> list) {
        if (list != null) {
            mlist_type.clear();
            mlist_type.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            mlist_type.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setTypeTwo(VideoTypeTwoBean bean) {

    }
}
