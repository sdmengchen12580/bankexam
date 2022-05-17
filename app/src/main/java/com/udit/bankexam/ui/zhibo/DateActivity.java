package com.udit.bankexam.ui.zhibo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.duobeiyun.def.controller.DefLiveActivity;
import com.duobeiyun.def.controller.DefPlaybackActivity;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.zhibo.DatelPresenter;
import com.udit.bankexam.ui.zhibo.adapter.Adapter_date;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.zhibo.DateView;

import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.udit.mycalendar.CalendarView;
import com.udit.mycalendar.Interface.OnDateChangedListener;
import com.udit.mycalendar.Interface.OnMonthChangedListener;
import com.udit.mycalendar.Util.CalendarDay;
import com.udit.mycalendar.Util.CalendarUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DateActivity extends BaseActivity<DatelPresenter> implements DateView.View, OnMonthChangedListener, OnClickListener, OnDateChangedListener {
    private final String TAG = this.getClass().getSimpleName();

    public static void startDateActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, DateActivity.class));
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private CalendarView calendarView;

    private ListView listview_date;

    HashSet<CalendarDay> calendarDays;

    private ImageView img_date_left, img_date_right;

    private TextView text_data_title;

    private Adapter_date adapter;

    private List<ZhiboKeChengBean> mlist;

    private HashMap<CalendarDay, List<ZhiboKeChengBean>> map_day = new HashMap<>();

    private UserBean bean_user;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_date);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("课程日历");
    }

    @Override
    public void initListeners() {
        listview_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZhiboKeChengBean bean = mlist.get(position);
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
        bean_user = SaveUtils.getUser(this);
        mPresenter = new DatelPresenter(this);
        mPresenter.getZhiboDate(bean_user.getUid());
        calendarDays = new HashSet<>();
        mlist = new ArrayList<>();
        adapter = new Adapter_date(mlist, this);
        listview_date.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        String month = MyDateUtil.format(new Date(), MyDateUtil.DATE_FORMAT_12);

        text_data_title.setText(month);

        calendarView.setOnMonthChangedListener(this);

        calendarView.setOnDateChangedListener(this);

        //   img_date_left.setOnClickListener(this);

        //  img_date_right.setOnClickListener(this);


        img_top_return.setOnClickListener(this);
    }

    @Override
    public void onMonthChanged(Date date) {
        MyLogUtils.e(TAG, "date:" + date.getDate());

        String month = MyDateUtil.format(date, MyDateUtil.DATE_FORMAT_12);

        text_data_title.setText(month);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                finish();
                break;
            case R.id.img_date_left:
                Date now_date = calendarView.getDateSelected();
                Date last_date = MyDateUtil.getLastMonth(now_date);
                MyLogUtils.e(TAG, "last_date:" + last_date);
                CalendarDay calendarDay = CalendarDay.from(last_date);
                calendarView.scrollToMonth(calendarDay);
                break;
            case R.id.img_date_right:
                Date now_date2 = calendarView.getDateSelected();
                Date next_date = MyDateUtil.getNextMonth(now_date2);
                MyLogUtils.e(TAG, "next_date:" + next_date);
                CalendarDay calendarDay2 = CalendarDay.from(next_date);
                calendarView.scrollToMonth(calendarDay2);
                //calendarView.onDateChanged(next_date);
                break;
            default:
                break;
        }
    }


    @Override
    public void setZhiboDate(List<ZhiboKeChengBean> list) {
        map_day.clear();
        if (list != null) {
            for (int i = 0; list != null && i < list.size(); i++) {
                ZhiboKeChengBean bean = list.get(i);
                Date date_now = MyDateUtil.toDate(bean.getBegDate(), MyDateUtil.DATE_FORMAT_11);
                CalendarDay calendarDay = CalendarDay
                        .from(date_now);
                // CalendarDay day = CalendarDay.from(i, calendarDay.getMonth() + 1, calendarDay.getYear());
                //  CalendarDay day = CalendarDay.from(i, calendarDay.getMonth() + 1, calendarDay.getYear());
                if (map_day.containsKey(calendarDay)) {
                    map_day.get(calendarDay).add(bean);
                } else {
                    List<ZhiboKeChengBean> mlist_bean = new ArrayList<>();
                    mlist_bean.add(bean);
                    map_day.put(calendarDay, mlist_bean);
                }
            }
            calendarDays.clear();
            for (CalendarDay day : map_day.keySet()) {
                calendarDays.add(day);
            }

            calendarView.addEvents(calendarDays);

            CalendarDay calendarDay_now = CalendarDay
                    .from(new Date());
            if (map_day.containsKey(calendarDay_now)) {
                mlist.clear();
                if (map_day.containsKey(calendarDay_now)) {
                    mlist.addAll(map_day.get(calendarDay_now));
                }

                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDateChanged(Date date) {
        MyLogUtils.e(TAG, "onDateChanged:" + date.getDate());
        CalendarDay mCurrent_day = CalendarDay.from(date);
        mlist.clear();
        if (map_day.containsKey(mCurrent_day)) {
            mlist.addAll(map_day.get(mCurrent_day));
        }

        adapter.notifyDataSetChanged();
    }
}
