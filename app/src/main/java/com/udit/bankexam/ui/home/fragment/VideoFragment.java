package com.udit.bankexam.ui.home.fragment;

import java.util.ArrayList;
import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.VideoBean;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.home.fragment.VideoPresenter;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.home.fragment.adapter.VideoAdapter;
import com.udit.bankexam.ui.pay.PaySelectActivity;
import com.udit.bankexam.ui.video.VideoActivity;
import com.udit.bankexam.ui.video.VideoTypeOneActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.home.fragment.VideoView;
import com.udit.bankexam.view.video.VideoTypeOneView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import lecho.lib.hellocharts.model.Line;

public class VideoFragment extends BaseFragment<VideoPresenter> implements VideoView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private static VideoFragment VIDEOFRAGMENT;

    public static VideoFragment getIntance() {
        if (VIDEOFRAGMENT == null) {
            VIDEOFRAGMENT = new VideoFragment();
        }
        return VIDEOFRAGMENT;
    }

    private View mView;

    private TextView text_top_centent;

    private List<VideoTypeTwoBean> mlist;

    private ListView listview_video;

    private VideoAdapter adapter;

    private UserBean bean_user;

    private BaseActivity<?> mContext;

    private LinearLayout ll_select_type;

    private LinearLayout ll_kefu;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_video, null);

        return mView;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, mView, R.id.class);
            text_top_centent.setText("");
            text_top_centent.setTag("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {
        ll_select_type.setOnClickListener(this);
        listview_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoTypeTwoBean bean = mlist.get(position);
                String price_s = Utils.doubleOutPut(bean.getPrice(), 2);
                if (bean.getIsGet().equals("是") ||
                        price_s.equals("0")) {
                    VideoActivity.startVideoActivity(mContext, bean);
                } else {
                    PaySelectActivity.startPaySelectActivity((BaseActivity<?>) getActivity(), bean.getcID(), Constant.DataType.TYPE_SHIPIN, bean.getName(), price_s, Constant.RESULT.RESULT_VIDEO_PAY);

                }

            }
        });
        ll_kefu.setOnClickListener(this);

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter = new VideoPresenter(this);
        mContext = (BaseActivity<?>) this.getActivity();

        mlist = new ArrayList<>();
        adapter = new VideoAdapter(mlist, getActivity());
        listview_video.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        bean_user = SaveUtils.getUser(getActivity());
        if (!flag)
            VideoTypeOneActivity.startVideoTypeOneActivity((BaseActivity<?>) getActivity());
      /* if(!flag)
        {
            String type =  SaveUtils.getVideoTypeOne(mContext);


            if(MyCheckUtils.isEmpty(type))
            {
                mPresenter.getTypeOne(bean_user.getUid());
            }
            else
            {
                text_top_centent.setText("视频选择："+type);
                mPresenter.getTypeList(bean_user.getUid(),type);
            }

        }*/


    }

    public boolean flag = false;

    public void startData(String eid, String type) {
        flag = true;
        HomeActivity.group_main.check(R.id.radio_video);

        if (bean_user == null)
            bean_user = SaveUtils.getUser(HomeActivity.HOMEACTIVITY);
        if (mPresenter == null)
            mPresenter = new VideoPresenter(this);
        mPresenter.getTypeList(bean_user.getUid(), eid, type);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_select_type:
                VideoTypeOneActivity.startVideoTypeOneActivity((BaseActivity<?>) getActivity());
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

        if (requestCode == Constant.RESULT.RESULT_VIDEO_TYPE_ONE
                && resultCode == mContext.RESULT_OK) {
            String type = data.getStringExtra("videoTypeOne");
            text_top_centent.setTag(type);
            text_top_centent.setText("视频选择：" + type);
            mPresenter.getTypeList(bean_user.getUid(), type);
        } else if (requestCode == Constant.RESULT.RESULT_VIDEO_PAY
                && resultCode == mContext.RESULT_OK) {

            //String type = text_top_centent.getText().toString();
            String type = (String) text_top_centent.getTag();
            // type = type.substring(type.lastIndexOf("视频选择："));
            mPresenter.getTypeList(bean_user.getUid(), type);
        }

    }

    @Override
    public void setTypeList(List<VideoTypeTwoBean> list) {
        if (list != null && list.size() > 0) {
            mlist.clear();
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            showLongToast("当前类型没有找到视频内容哦");
            mlist.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setTypeOne(List<VideoTypeOneBean> list) {
      /*  if(list!=null && list.size()>0)
        {
            text_top_centent.setText("视频选择："+list.get(0));
            mPresenter.getTypeList(bean_user.getUid(),list.get(0));
        }*/
    }

    @Override
    public void setTypeTwo(VideoTypeTwoBean bean) {
        mlist.clear();
        if (bean != null) {
            text_top_centent.setText("视频选择：" + bean.getVType());
            mlist.add(bean);
        }
        adapter.notifyDataSetChanged();
        flag = false;
    }
}
