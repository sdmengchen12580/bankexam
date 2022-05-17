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
import com.udit.bankexam.bean.VideoType;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.home.fragment.VideoPresenter;
import com.udit.bankexam.presenter.video.VideoTypeOnePresenter;
import com.udit.bankexam.presenter.video.VideoTypeTwoPresenter;
import com.udit.bankexam.ui.home.fragment.adapter.VideoAdapter;
import com.udit.bankexam.ui.pay.PaySelectActivity;
import com.udit.bankexam.ui.video.adapter.TypeOneAdapter;
import com.udit.bankexam.ui.video.adapter.TypeOneAdapter_new;
import com.udit.bankexam.ui.video.adapter.TypeTwoAdapter_new;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.video.VideoTypeOneView;
import com.udit.bankexam.view.video.VideoTypeTwoViewNew;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class VideoTypeTwoActivityNew extends BaseActivity<VideoTypeTwoPresenter> implements VideoTypeTwoViewNew.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startVideoTypeTwoActivityNew(BaseActivity<?> mActivity,String typeOne)
    {
        Intent intent = new Intent(mActivity,VideoTypeTwoActivityNew.class);
        intent.putExtra("TypeOne",typeOne);
        mActivity.startActivity(intent);
    }

    public static void startVideoTypeTwoActivityNewByID(BaseActivity<?> mActivity,String typeID)
    {
        Intent intent = new Intent(mActivity,VideoTypeTwoActivityNew.class);
        intent.putExtra("TypeOneID",typeID);
        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_video);
    }

    private TextView text_top_centent;

    private List<VideoType> mlist;

    private ListView listview_video;

    private TypeTwoAdapter_new adapter;

    private UserBean bean_user;

    private ImageView img_top_return;

    private LinearLayout ll_kefu;

    private String typeOne;

    private Context mContext;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

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
        this.ll_kefu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (Utils.isQQAvailable(VideoTypeTwoActivityNew.this.getActivity())) {
                    Utils.startQQ(VideoTypeTwoActivityNew.this.getActivity(), "3004628600");
                    return;
                }
                VideoTypeTwoActivityNew.this.showLongToast("请先安装QQ");
            }
        });
    }

    @Override
    public void initData() {

        mContext = (BaseActivity<?>)this;

        mPresenter = new VideoTypeTwoPresenter(this);

        typeOne = getIntent().getStringExtra("TypeOne");



        mlist = new ArrayList<>();
        adapter = new TypeTwoAdapter_new(mlist, getActivity());
        listview_video.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        bean_user = SaveUtils.getUser(getActivity());

        if(typeOne!=null && !typeOne.isEmpty())
        {
            text_top_centent.setText(typeOne);
            mPresenter.getTypeTwo(bean_user.getUid(),typeOne);
        }
        else{
            String typeID = getIntent().getStringExtra("TypeOneID");
            mPresenter.getTypeTwoByID(bean_user.getUid(),typeID);

        }



        adapter.setExamGroupClick(new TypeTwoAdapter_new.ExamGroupOnClick() {
            @Override
            public void ExamGo(VideoType bean) {
                MyLogUtils.e(TAG,bean.getName()+"  "+bean.getId());

                if(bean.getCid()==null || bean.getCid().isEmpty())
                {//视频
                    String price_s = Utils.doubleOutPut(bean.getPrice(),2);
                    if(bean.getIsget().equals("是") ||
                            price_s.equals("0"))
                    {
                        VideoInfoActivity.startVideoInfoActivityByType((BaseActivity<?>) getActivity(),
                                bean.getList_file(),
                                0);
                    }
                    else
                    {
                        PaySelectActivity.startPaySelectActivity((BaseActivity<?>) getActivity(),
                                bean.getCid(), Constant.DataType.TYPE_SHIPIN,bean.getName(),price_s,
                                Constant.RESULT.RESULT_VIDEO_PAY);

                    }

                }
                else {
                    // 点击的是 视频 二类
                    for (int i = 0; mlist != null && i < mlist.size(); i++) {

                        VideoType videoType = mlist.get(i);

                        if (videoType.getId().equals(bean.getCid())) {
                            List<VideoType> list_child = videoType.getList_file();

                            for (int j = 0; list_child != null && j < list_child.size(); j++) {
                                VideoType videType_Child = list_child.get(j);

                                if (videType_Child.getId().equals(bean.getId())) {
                                    String price_s = Utils.doubleOutPut(videoType.getPrice(), 2);
                                    if (videoType.getIsget().equals("是") ||
                                            price_s.equals("0")) {
                                        VideoInfoActivity.startVideoInfoActivityByType((BaseActivity<?>) getActivity(),
                                                videoType.getList_file(),
                                                j);
                                        return ;

                                    }
                                    else{
                                        PaySelectActivity.startPaySelectActivity((BaseActivity<?>) getActivity(),
                                                videoType.getId(), Constant.DataType.TYPE_SHIPIN,videoType.getName(),price_s,
                                                Constant.RESULT.RESULT_VIDEO_PAY);
                                        return ;

                                    }

                                }
                            }

                        }
                    }
                }
            }
        });
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

    @Override
    public void setTypeTwo(List<VideoType> list) {
        mlist.clear();
        if(list!=null && list.size()>0)
        {
            if(typeOne==null || typeOne.isEmpty())
            {
                typeOne =  list.get(0).getName();
                text_top_centent.setText(typeOne);
            }
            mlist.addAll(list);
        }


        adapter.notifyDataSetChanged();

    }
}
