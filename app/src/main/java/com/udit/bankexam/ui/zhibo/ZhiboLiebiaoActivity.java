package com.udit.bankexam.ui.zhibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.duobeiyun.def.controller.DefLiveActivity;
import com.duobeiyun.def.controller.DefPlaybackActivity;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.zhibo.ZhiboLiebiaoPresenter;
import com.udit.bankexam.ui.video.VideoActivity;
import com.udit.bankexam.ui.zhibo.adapter.Adapter_date;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.zhibo.ZhiboLiebiaoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/6/7.
 * 直播课 内 的视频课程
 */

public class ZhiboLiebiaoActivity extends BaseActivity<ZhiboLiebiaoPresenter> implements ZhiboLiebiaoView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startZhiboLiebiaoActivity(BaseActivity<?> mActivity, ZhiBoBean bean) {
        Intent intent = new Intent(mActivity, ZhiboLiebiaoActivity.class);
        intent.putExtra("zhibobean", bean);
        mActivity.startActivity(intent);
    }

    public static void startZhiboLiebiaoActivity(BaseActivity<?> mActivity, String id, String name) {
        Intent intent = new Intent(mActivity, ZhiboLiebiaoActivity.class);
        intent.putExtra("zhiboid", id);
        intent.putExtra("zhiboname", name);
        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_zhiboliebiao);
    }

    @Override
    public void initViews(Bundle bundle) {

        ViewUtils.initView(this, R.id.class);

    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ZhiBoBean bean_zhibo;

    private Adapter_date adapter_date;

    private List<ZhiboKeChengBean> mlist_bean;

    private ListView listview_zhiboliebiao;

    private UserBean bean_user;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        listview_zhiboliebiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZhiboKeChengBean bean = mlist_bean.get(position);

                String uid = bean_user.getUid();
                String roomId = bean.getURL();

                String nickname = "来自火星的你";
                if (!MyCheckUtils.isEmpty(bean_user.getPet())) {
                    nickname = bean_user.getPet();
                }

                String title = bean.getIntro();
                Intent intent = null;
                String date_now = MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_11);
                MyLogUtils.e(TAG, "NOW:" + date_now + "  begin_time:" + bean.getBegDate() + "end_time:" + bean.getEndDate() + " roomId:" + roomId);
                String begdate_advance = bean.getBegDate();
                //MyDateUtil.advanceMinute(bean.getBegDate(),MyDateUtil.DATE_FORMAT_11,-5);

                if (MyDateUtil.compareTime(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_11), begdate_advance, MyDateUtil.DATE_FORMAT_11)) {
                    showLongToast("直播未开始,请于" + bean.getBegDate() + " 进入直播间");
                    return;
                } else if (MyDateUtil.compareTime2(begdate_advance,
                        MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_11),
                        MyDateUtil.DATE_FORMAT_11)
                        && MyDateUtil.compareTime2(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_11),
                        bean.getEndDate(), MyDateUtil.DATE_FORMAT_11)) {
                    showLongToast("进入直播");
                    intent = new Intent(getActivity(), DefLiveActivity.class);
                } else if (MyDateUtil.compareTime(bean.getEndDate(), MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_11), MyDateUtil.DATE_FORMAT_11)) {
                    showLongToast("进入回看");
                    intent = new Intent(getActivity(), DefPlaybackActivity.class);

                    intent.putExtra("appKey", Constant.ZHIBO.APPKEY);
                    intent.putExtra("nickname", nickname);
                    intent.putExtra("partner", Constant.ZHIBO.PID);
                    intent.putExtra("roomId", roomId);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                    return;
                }
                if (intent != null) {

                    if (MyCheckUtils.isEmpty(roomId) || roomId.length() < 15) {
                        showLongToast("还未开通直播间");
                        return;
                    }
                    intent.putExtra("pid", Constant.ZHIBO.PID);
                    intent.putExtra("appKey", Constant.ZHIBO.APPKEY);
                    intent.putExtra("uid", uid);
                    intent.putExtra("roomId", roomId);
                    intent.putExtra("nickname", nickname);
                    intent.putExtra("title", title);
                    startActivity(intent);

                } else {
                    showLongToast("直播未开始,请于" + bean.getBegDate() + " 进入直播间");
                }
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new ZhiboLiebiaoPresenter(this);

        Object obj = (ZhiBoBean) getIntent().getSerializableExtra("zhibobean");
        bean_user = SaveUtils.getUser(this);
        mlist_bean = new ArrayList<>();

        adapter_date = new Adapter_date(mlist_bean, this);

        listview_zhiboliebiao.setAdapter(adapter_date);

        adapter_date.notifyDataSetChanged();
        if (obj != null && obj instanceof ZhiBoBean) {
            bean_zhibo = (ZhiBoBean) obj;
            text_top_centent.setText(bean_zhibo.getName());

            mPresenter.getZhiboList(this, bean_user.getUid(), bean_zhibo.getLID());
        } else {
            String zhiboid = getIntent().getStringExtra("zhiboid");

            String zhiboname = getIntent().getStringExtra("zhiboname");
            text_top_centent.setText(zhiboname);


            mPresenter.getZhiboList(this, bean_user.getUid(), zhiboid);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                //this.onLowMemory();
                finish();

                break;
        }
    }

    @Override
    public void setZhiboList(List<ZhiboKeChengBean> list) {
        mlist_bean.clear();
        if (list != null && list.size() > 0)
            mlist_bean.addAll(list);

        adapter_date.notifyDataSetChanged();
    }
}
