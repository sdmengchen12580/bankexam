package com.udit.bankexam.ui.exam_day;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitle;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_day.ExamDayPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_day.adapter.AdapterExamDay;
import com.udit.bankexam.ui.exam_day.adapter.AdapterExamDays;
import com.udit.bankexam.ui.exam_year.ExamYearActivity;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.TimeUtils;
import com.udit.bankexam.view.exam_day.ExamDayView;
import com.udit.frame.common.dialog.MyAlertDialog;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamDayActivity extends BaseActivity<ExamDayPresenter> implements ExamDayView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startExamDayActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamDayActivity.class));
    }

    public String getCurrentFormatTime() {
        long l = System.currentTimeMillis();
        return this.format2.format(Long.valueOf(l));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_examday);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("每日一练");
    }


    private TextView tv1;

    private TextView tv2;

    private TextView tv3;

    private TextView tv4;

    private TextView tv5;

    private TextView tv6;

    private TextView tv7;

    private ImageView img_time;

    public SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月");

    private UserBean bean_user;

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_examday;

    private TextView tv_time;

    private ImageView img_top_right;

    private List<TextView> tvs = new ArrayList();


    private AdapterExamDays adapter;

    private List<ExamInfoBean> mlist;

    private ExamInfoBean bean_info;

    @Override
    public void initListeners() {
        img_time.setOnClickListener(this);
        img_top_return.setOnClickListener(this);
        img_top_right.setOnClickListener(this);
        listview_examday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean_info = mlist.get(position);
                String price_s = Utils.doubleOutPut(bean_info.getPrice(), 2);
                if ((bean_info.getIsGet() != null && bean_info.getIsGet().equals(Constant.ExamData.GOUMAI_OK)) ||
                        price_s.equals("0")) {
                    mPresenter.saveExam(bean_user.getUid(), bean_info.getID(), bean_info.getName());
                    mPresenter.getExamTitle(ExamDayActivity.this, bean_user.getUid(), bean_info.getID());
                } else {
                    ExamDayDetailActivity.startExamDayDetailActivity(ExamDayActivity.this, bean_info, Constant.RESULT.RESULT_EXAM_DAY_DETAIL);
                }
            }
        });
        this.tvs.add(this.tv1);
        this.tvs.add(this.tv2);
        this.tvs.add(this.tv3);
        this.tvs.add(this.tv4);
        this.tvs.add(this.tv5);
        this.tvs.add(this.tv6);
        this.tvs.add(this.tv7);
    }

    @Override
    public void initData() {
        mPresenter = new ExamDayPresenter(this);
        bean_user = SaveUtils.getUser(this);

        mlist = new ArrayList<>();
        adapter = new AdapterExamDays(mlist, this);
        listview_examday.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        mPresenter.doCard(bean_user.getUid());
        mPresenter.getExamDay(this, bean_user.getUid(), "每日一练");

        this.tv_time.setText(getCurrentFormatTime());
        int i = TimeUtils.getDayofWeek("");//排的位置
        List<String> list = TimeUtils.getDateList(i);
        Log.e("测试每日一练: ", "位置 i=" + i);
        for (int j = 0; j < list.size(); j++) {
            Log.e("测试每日一练: ", "数据=" + list.get(j));
        }
        for (int j = 0; j < list.size(); j++) {
            if (i == j) {
                ((TextView) this.tvs.get(i)).setText("今");
                ((TextView) this.tvs.get(i)).setTextColor(getResources().getColor(R.color.white));
                ((TextView) this.tvs.get(i)).setBackground(getResources().getDrawable(R.drawable.shape_12_stroke_558cf4_bg));
            } else {
                ((TextView) this.tvs.get(j)).setText(list.get(j));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        if (requestCode == Constant.RESULT.RESULT_EXAM_DAY_DETAIL
                && resultCode == RESULT_OK) {
            mPresenter.getExamDay(this, bean_user.getUid(), "每日一练");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.img_time:
                ExamDayCalenderActivity.startExamDayCalenderActivity(this);
                break;
        }
    }

    @Override
    public void setExamList(List<ExamInfoBean> list) {

        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            final MyAlertDialog.Builder alertDialog = new MyAlertDialog.Builder(this);
            alertDialog.setMessage("没有找到试卷，您可以点击右上角按钮，选择历史每日一练来练习");
            alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.create().show();

        }
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
