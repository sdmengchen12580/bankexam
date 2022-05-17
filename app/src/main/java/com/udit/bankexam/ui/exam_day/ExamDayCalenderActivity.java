package com.udit.bankexam.ui.exam_day;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_day.ExamDayCalenderPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_day.adapter.AdapterExamCalenderDay;
import com.udit.bankexam.ui.exam_day.adapter.AdapterExamDay;
import com.udit.bankexam.ui.zhibo.adapter.Adapter_date;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_day.ExamDayCalenderView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.udit.mycalendar.CalendarView;
import com.udit.mycalendar.Interface.OnDateChangedListener;
import com.udit.mycalendar.Interface.OnMonthChangedListener;
import com.udit.mycalendar.Util.CalendarDay;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zb on 2017/6/17.
 */

public class ExamDayCalenderActivity extends BaseActivity<ExamDayCalenderPresenter> implements ExamDayCalenderView.View, View.OnClickListener, OnMonthChangedListener, OnDateChangedListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamDayCalenderActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamDayCalenderActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_examdaycalender);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);

        text_top_centent.setText("每日一练");
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private CalendarView calendarView;

    private ListView listview_date;

    HashSet<CalendarDay> calendarDays;

    private ImageView img_date_left, img_date_right;

    private TextView text_data_title;

    private AdapterExamCalenderDay adapter;


    private List<ExamInfoBean> mlist;

    private HashMap<CalendarDay, List<ExamInfoBean>> map_day = new HashMap<>();

    private UserBean bean_user;

    private ExamInfoBean bean_info;
    private String begin, end;

    @Override
    public void initListeners() {
        this.img_top_return.setOnClickListener(this);
        this.calendarView.setOnMonthChangedListener(this);
        this.calendarView.setOnDateChangedListener(this);
        this.img_date_left.setOnClickListener(this);
        this.img_date_right.setOnClickListener(this);

        listview_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean_info = mlist.get(position);
                String price_s = Utils.doubleOutPut(bean_info.getPrice(), 2);
                if ((bean_info.getIsGet() != null && bean_info.getIsGet().equals(Constant.ExamData.GOUMAI_OK)) ||
                        price_s.equals("0")) {
                    mPresenter.saveExam(bean_user.getUid(), bean_info.getID(), bean_info.getName());
                    mPresenter.getExamTitle(ExamDayCalenderActivity.this, bean_user.getUid(), bean_info.getID());
                } else {
                    ExamDayDetailActivity.startExamDayDetailActivity(ExamDayCalenderActivity.this, bean_info, Constant.RESULT.RESULT_EXAM_DAY_DETAIL);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        if (requestCode == Constant.RESULT.RESULT_EXAM_DAY_DETAIL
                && resultCode == RESULT_OK) {


            mPresenter.getExamDayByTime(this, bean_user.getUid(), begin, end);

        }
    }

    @Override
    public void initData() {
        mPresenter = new ExamDayCalenderPresenter(this);

        bean_user = SaveUtils.getUser(this);
        mlist = new ArrayList<>();
        adapter = new AdapterExamCalenderDay(mlist, this);
        listview_date.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        calendarDays = new HashSet<>();
        String month = MyDateUtil.format(new Date(), MyDateUtil.DATE_FORMAT_12);

        text_data_title.setText(month);
        begin = MyDateUtil.getMonthFirst(MyDateUtil.DATE_FORMAT_2);
        end = MyDateUtil.getMonthLast(MyDateUtil.DATE_FORMAT_2);

        mPresenter.getExamDayByTime(this, bean_user.getUid(), begin, end);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.img_date_left:
                //    calendarView.getDateSelected()
                Date now_date = calendarView.getDateSelected();

                // MyDateUtil.format(begin,MyDateUtil.DATE_FORMAT_1);
                //  String last_date_s =  MyDateUtil.getMonthLast(begin,MyDateUtil.DATE_FORMAT_2);
                Date last_date = MyDateUtil.getLastMonth(now_date);
                //   Date last_date =MyDateUtil.toDate(last_date_s,MyDateUtil.DATE_FORMAT_2);

                //  String last =   MyDateUtil.format(last_date,MyDateUtil.DATE_FORMAT_1);
                //MyLogUtils.e(TAG, "last_date:"+last);
                CalendarDay calendarDay = CalendarDay.from(last_date);
                calendarView.scrollToMonth(calendarDay);
                break;
            case R.id.img_date_right:
                Date now_date2 = calendarView.getDateSelected();
                Date next_date = MyDateUtil.getNextMonth(now_date2);
                String next = MyDateUtil.format(next_date, MyDateUtil.DATE_FORMAT_1);
                MyLogUtils.e(TAG, "next_date:" + next);
                CalendarDay calendarDay2 = CalendarDay.from(next_date);
                calendarView.scrollToMonth(calendarDay2);
                //calendarView.onDateChanged(next_date);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMonthChanged(Date date) {

        MyLogUtils.e(TAG, "date:" + date.getDate());

        String month = MyDateUtil.format(date, MyDateUtil.DATE_FORMAT_12);
        String month2 = MyDateUtil.format(date, MyDateUtil.DATE_FORMAT_2);

        text_data_title.setText(month);
        begin = MyDateUtil.getMonthFirst(month2, MyDateUtil.DATE_FORMAT_2);
        end = MyDateUtil.getMonthLast(month2, MyDateUtil.DATE_FORMAT_2);

        mPresenter.getExamDayByTime(this, bean_user.getUid(), begin, end);

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

    @Override
    public void setExamInfo(List<ExamInfoBean> list) {
        map_day.clear();
        if (list != null && list.size() > 0) {
            for (int i = 0; list != null && i < list.size(); i++) {
                ExamInfoBean bean = list.get(i);
                Date date_now = MyDateUtil.toDate(bean.getBegDate(), MyDateUtil.DATE_FORMAT_2);
                CalendarDay calendarDay = CalendarDay
                        .from(date_now);
                // CalendarDay day = CalendarDay.from(i, calendarDay.getMonth() + 1, calendarDay.getYear());
                //  CalendarDay day = CalendarDay.from(i, calendarDay.getMonth() + 1, calendarDay.getYear());
                if (map_day.containsKey(calendarDay)) {
                    map_day.get(calendarDay).add(bean);
                } else {
                    List<ExamInfoBean> mlist_bean = new ArrayList<>();
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
    public void setExamList(List<ExamInfoBean> list) {
        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        } else
            showLongToast("暂无试卷");
    }

    @Override
    public void setExamTitleList(List<ExamTitleBean> list) {
        String list_title = ExamUtils.getExamTtile(list);
        if (!MyCheckUtils.isEmpty(list_title)) {
            ExamActivity.startExamActivity(this, bean_info.getID(), bean_info.getName(), list_title, false, false, false, false);
        } else {
            bean_info = null;
            showLongToast("暂无题目");
        }

    }
}
