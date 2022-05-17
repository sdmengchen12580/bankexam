package com.udit.bankexam.ui.video;

import android.content.Context;
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
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.home.fragment.VideoPresenter;
import com.udit.bankexam.ui.home.fragment.adapter.VideoAdapter;
import com.udit.bankexam.ui.pay.PaySelectActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.home.fragment.VideoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2018-06-25.
 */

public class VideoTypeTwoActivity extends BaseActivity<VideoPresenter> implements VideoView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private TextView text_top_centent;

    private List<VideoTypeTwoBean> mlist;

    private ListView listview_video;

    private VideoAdapter adapter;

    private UserBean bean_user;

    private ImageView img_top_return;

    private LinearLayout ll_kefu;

    public static void startVideoTypeTwoActivity(BaseActivity<?> mActivity, String typeOne)
    {
        Intent intent = new Intent(mActivity,VideoTypeTwoActivity.class);
        intent.putExtra("TypeOne",typeOne);
        mActivity.startActivity(intent);
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_video);
    }

    @Override
    public void initViews(Bundle bundle) {
        try
        {
            ViewUtils.initView(this,R.id.class);
            text_top_centent.setText("");
            text_top_centent.setTag("");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        ll_kefu.setOnClickListener(this);

        listview_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VideoTypeTwoBean bean =  mlist.get(position);

                String price_s = Utils.doubleOutPut(bean.getPrice(),2);
                if(bean.getIsGet().equals("是") ||
                        price_s.equals("0"))
                {
                    VideoActivity.startVideoActivity((BaseActivity<?>) getActivity(),bean);
                }
                else
                {
                    PaySelectActivity.startPaySelectActivity((BaseActivity<?>) getActivity(),bean.getcID(), Constant.DataType.TYPE_SHIPIN,bean.getName(),price_s,Constant.RESULT.RESULT_VIDEO_PAY);

                }

            }
        });
    }
    String typeOne;


    @Override
    protected void onResume() {
        super.onResume();
        bean_user = SaveUtils.getUser(getActivity());
        mPresenter.getTypeList(bean_user.getUid(),typeOne);
    }

    @Override
    public void initData() {
        mPresenter = new VideoPresenter(this);

        typeOne = getIntent().getStringExtra("TypeOne");
        text_top_centent.setText(typeOne);
        mlist = new ArrayList<>();
        adapter = new VideoAdapter(mlist, getActivity());
        listview_video.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        bean_user = SaveUtils.getUser(getActivity());
        mPresenter.getTypeList(bean_user.getUid(),typeOne);

    }


    @Override
    public void setTypeList(List<VideoTypeTwoBean> list) {
        if(list!=null && list.size()>0)
        {
            mlist.clear();
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        }
        else {
            showLongToast("当前类型没有找到视频内容哦");
            mlist.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setTypeOne(List<VideoTypeOneBean> list) {

    }

    @Override
    public void setTypeTwo(VideoTypeTwoBean bean) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_top_return:
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
