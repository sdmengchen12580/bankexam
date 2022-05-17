package com.udit.bankexam.ui.exam_err;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitle;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_err.ExamErrDetailPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.home.fragment.adapter.HomeBannerPagerAdapter;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.bankexam.view.exam_err.ExamErrDetailView;
import com.udit.frame.common.flowlayout.FlowLayout;
import com.udit.frame.common.imageview.MyImageView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by zb on 2017/5/19.
 */

public class ExamErrDetailActivity extends BaseActivity<ExamErrDetailPresenter> implements ExamErrDetailView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private boolean flag_mk;


    public static void startExamErrDetailActivity(BaseActivity<?> mActivity, String oid_eid,
                                                  String titlename, String examIds,
                                                  boolean flag_zhineng, boolean flag_oid, boolean flag_main,
                                                  boolean flag_activity_finish, boolean flag_mk) {
        Intent intent = new Intent(mActivity, ExamErrDetailActivity.class);
        intent.putExtra("oid_eid", oid_eid);
        intent.putExtra("titlename", titlename);
        intent.putExtra("examIds", examIds);
        intent.putExtra("flag_zhineng", flag_zhineng);
        intent.putExtra("flag_oid", flag_oid);
        intent.putExtra("flag_main", flag_main);
        intent.putExtra("flag_mk", flag_mk);
        mActivity.startActivity(intent);
        if (flag_activity_finish) {
            mActivity.onLowMemory();
            mActivity.finish();
        }

    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_errdetail);
    }

    private ImageView img_top_return;

    private TextView text_top_centent, text_top_right;

    //  private PurchBean bean_purch;

    // private boolean flag_home;

    private TextView textview_total_num, textview_ok_num, textview_err_num;

    private FlowLayout flow_dtk;

    private TextView textview_jx_btn, textview_jx_all_btn, textview_jx_ctcz_btn;

    private ViewGroup.MarginLayoutParams params_margin;

    private int width_solution;

    private ArrayList<ExamTitleBean> mlist_titlebean;

    private TextView tv_noanswer;

    private TextView tv_rate;

    private PieChartView chartview_err;

    private int displayWidth;


    private TextView textview_qingkuang;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_right.setText("数据报告");
        text_top_right.setVisibility(View.GONE);
        textview_qingkuang.setVisibility(View.INVISIBLE);

        width_solution = getResources().getDisplayMetrics().widthPixels;

        width_solution = width_solution / 4;
        int height_delete_banner = Utils.dip2px(getActivity(), 40);
        int height_banner = displayWidth / 2 - height_delete_banner;
        params_banner = new RelativeLayout.LayoutParams(displayWidth, height_banner);
        params_linear = new LinearLayout.LayoutParams(displayWidth, height_banner);
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        textview_jx_btn.setOnClickListener(this);

        textview_jx_all_btn.setOnClickListener(this);

        textview_jx_ctcz_btn.setOnClickListener(this);
    }
    //private boolean flag_exam;

    private ArrayList<ExamBean> mlist_exam;

    private boolean flag_zhineng = false;
    private String examIds;

    private String oid_eid;

    private String title_name;

    private boolean flag_oid = false;

    private boolean flag_main = false;

    private RelativeLayout rl_pie;

    @Override
    public void initData() {

        params_margin = new ViewGroup.MarginLayoutParams(width_solution, width_solution);
        params_margin.topMargin = 20;
        params_margin.bottomMargin = 20;

        mlist_titlebean = new ArrayList<>();
        mPresenter = new ExamErrDetailPresenter(this);


        UserBean bean_user = SaveUtils.getUser(this);
        mlist_exam = new ArrayList<>();

//        rl_pie.setVisibility(View.INVISIBLE);
        //chartview_err.getParent().requestDisallowInterceptTouchEvent(true);
        Object obj_oid_eid = getIntent().getStringExtra("oid_eid");
        Object obj_titlename = getIntent().getStringExtra("titlename");
        Object obj_examIds = getIntent().getStringExtra("examIds");

        flag_zhineng = getIntent().getBooleanExtra("flag_zhineng", false);
        flag_oid = getIntent().getBooleanExtra("flag_oid", false);
        flag_main = getIntent().getBooleanExtra("flag_main", false);
        flag_mk = getIntent().getBooleanExtra("flag_mk", false);
        if (flag_mk) {
            textview_jx_ctcz_btn.setVisibility(View.GONE);
        } else {
            textview_jx_ctcz_btn.setVisibility(View.VISIBLE);
        }
        // initAdv();
        if (obj_oid_eid != null && !MyCheckUtils.isEmpty(obj_oid_eid.toString())) {
            oid_eid = obj_oid_eid.toString();
        } else {
            if (!flag_zhineng) {
                MyLogUtils.e(TAG, "试卷ID or 节点 ID 未传入？？");
                showLongToast("暂无答题卡内容");
                this.onLowMemory();
                finish();
                return;
            } else {
                MyLogUtils.e(TAG, "智能 相关，无需强制 传入ID");
            }
        }

        //检查 题目内容 ID
        if (obj_examIds == null
                || MyCheckUtils.isEmpty(obj_examIds.toString())) {

            MyLogUtils.e(TAG, "试卷 题目IDs 未传入？？");
            showLongToast("暂无答题卡内容");
            this.onLowMemory();
            finish();
            return;
        } else {
            examIds = obj_examIds.toString();
        }

        //检查标题
        if (obj_titlename != null && !MyCheckUtils.isEmpty(obj_titlename.toString())) {
            title_name = obj_titlename.toString();
            text_top_centent.setText(title_name);
        } else {
            MyLogUtils.e(TAG, "标题未传入？？");
            showLongToast("暂无答题卡内容");
            this.onLowMemory();
            finish();
            return;
        }

        if (flag_zhineng) {//智能练习
            mPresenter.getZhinengTitles(this, bean_user.getUid(), examIds);
        } else {//非智能练习
            mPresenter.getExamTtils(this, bean_user.getUid(), examIds);
        }


    }

    /*
     * *****************广告**********************
     */
    private RelativeLayout rl_advs;

    private MyImageView img_load;

    private ViewPager pager_main_adv;

    private CirclePageIndicator indicator_main_adv;

    private List<AdvBean> mlist_adv;

    private HomeBannerPagerAdapter adapter_adv;

    private RelativeLayout.LayoutParams params_banner;

    private LinearLayout.LayoutParams params_linear;
    //  private RelativeLayout rl_advs;

 /*   private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == 0)
            {
                int postion =pager_main_adv.getCurrentItem() + 1;
                if (null != mlist_adv && mlist_adv.size() > 0)
                {
                    pager_main_adv.setCurrentItem(postion % mlist_adv.size(), true);
                    MyLogUtils.e(TAG,"ADV:"+(postion % mlist_adv.size()));
                }

                handler.sendEmptyMessageDelayed(0, Constant.SKIP_TIME);
            }
        }
    };*/

    /*private void initAdv()
    {
       //
       //pager_main_adv = (ViewPager)mView_top.findViewById(R.id.pager_main_adv);
        pager_main_adv.setVisibility(View.VISIBLE);
       // img_load = (MyImageView)mView_top.findViewById(R.id.img_load);
        img_load.setVisibility(View.VISIBLE);
        //   rl_advs = (RelativeLayout) mView_top.findViewById(R.id.rl_adv);
        mlist_adv = new ArrayList<>();

        adapter_adv = new HomeBannerPagerAdapter(mlist_adv,getActivity());
        pager_main_adv.setAdapter(adapter_adv);
       // indicator_main_adv = (CirclePageIndicator)mView_top.findViewById(R.id.indicator_main_adv);
        indicator_main_adv.setViewPager(pager_main_adv);


        // rl_advs.setLayoutParams(params_banner);
         pager_main_adv.setLayoutParams(params_banner);

        img_load.setLayoutParams(params_banner);
        rl_advs.setLayoutParams(params_linear);

    }*/

    private PieChartData pieChardata;

    private List<SliceValue> values = new ArrayList<>();

    private void setSjDetailByExam(List<ExamBean> list_exam) {
        flow_dtk.removeAllViews();
        mlist_exam.clear();
        if (list_exam != null) {
            mlist_exam.addAll(list_exam);
            int exam_ok_count = 0;
            int exam_err_count = 0;
            int exam_count = 0;
            int exam_no = 0;
            int time = 0;

            exam_count = list_exam.size();
            for (int i = 0; list_exam != null && i < list_exam.size(); i++) {
                ExamBean bean_title = list_exam.get(i);
                View view = LayoutInflater.from(this).inflate(R.layout.item_solution, null);
                HolderExamTitle holderExamTitle = new HolderExamTitle();
                holderExamTitle.mView = view;
                ViewUtils.initHolderView(holderExamTitle, view, R.id.class);
                holderExamTitle.text_solution_num.setText((i + 1) + "");
                holderExamTitle.img_solution_biaoji.setVisibility(View.GONE);

                String time_nows = bean_title.getUserTime();
                int time_int = 0;
                try {
                    time_int = Integer.parseInt(time_nows);
                } catch (NumberFormatException e) {
                    time_int = 0;
                }
                time += time_int;
                if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_OK)) {
                    exam_ok_count++;
                    holderExamTitle.text_solution_num.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.white)));
                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.img_green_doll);
                } else if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_ERR)) {
                    exam_err_count++;
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.white)));

                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.img_yellow_doll);
                    //  MyLogUtils.e(TAG,"setSjDetailByExam装载错题");
                    holderExamTitle.mView.setOnClickListener(new MyErrOnClick(bean_title.getID()));
                } else {
                    exam_no++;
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_333333)));

                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.img_graw_doll);
                }
                flow_dtk.addView(view, params_margin);
            }

            textview_total_num.setText(exam_count + "");
            textview_ok_num.setText(exam_ok_count + "");
            textview_err_num.setText(exam_err_count + "");

            if (exam_err_count == 0) {
                textview_jx_btn.setEnabled(false);
            } else
                textview_jx_btn.setEnabled(true);

            initPie(exam_ok_count, exam_err_count, exam_no);


            initPieData(exam_ok_count, exam_err_count, exam_no, exam_count, time);

            // mPresenter.getAdv();
            textview_total_num.setText(exam_count + "");
            textview_ok_num.setText(exam_ok_count + "");
            textview_err_num.setText(exam_err_count + "");
            tv_noanswer.setText(exam_no + "");
        }
    }

    private class MyErrOnClick implements View.OnClickListener {

        private String id;

        public MyErrOnClick(String id) {
            this.id = id;
        }

        @Override
        public void onClick(View v) {

            String ids = ExamUtils.getExamTitle(id);
            ExamErrTitleActivity.startExamErrTitleActivity(ExamErrDetailActivity.this,
                    oid_eid, title_name, ids, flag_zhineng, flag_oid);
        }
    }

    private void initPie(int exam_ok_count, int exam_err_count, int exam_no) {

        if (exam_err_count > 0) {
            SliceValue value_err = new SliceValue(exam_err_count, Color.parseColor("#fb7656"));
            value_err.setLabel("答错：" + exam_err_count);
            values.add(value_err);

        }


        if (exam_no > 0) {
            SliceValue value_no = new SliceValue(exam_no, Color.parseColor("#8c9fb0"));
            value_no.setLabel("未答：" + exam_no);
            values.add(value_no);
        }


        if (exam_ok_count > 0) {
            SliceValue value_ok = new SliceValue(exam_ok_count, Color.parseColor("#4cc05f"));
            value_ok.setLabel("答对：" + exam_ok_count);
            values.add(value_ok);
        }


    }

    private void initPieData(int value_ok, int value_err, int value_no,
                             int value_total, int time) {

        String times = MyDateUtil.formatLongTime2(time * 1000);
//        String s = String.format(getString(R.string.str_exam_qingkuang), times);
        textview_qingkuang.setText(times);
        textview_qingkuang.setVisibility(View.VISIBLE);
        // ll_qingkuang.setVisibility(View.VISIBLE);

        pieChardata = new PieChartData();
        //显示详情
        pieChardata.setHasLabels(true);
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比  
        pieChardata.setHasLabelsOutside(true);//占的百分比是否显示在饼图外面  
        pieChardata.setHasCenterCircle(true);//是否是环形显示  


        pieChardata.setValues(values);//填充数据  
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色  
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别  
        String per = Utils.getPer(value_ok + "", value_total + "");
        pieChardata.setCenterText1("答对率：" + per);//环形中间的文字1  
        pieChardata.setCenterText1Color(Color.BLACK);//文字颜色  
        pieChardata.setCenterText1FontSize(10);//文字大小  

       /* pieChardata.setCenterText2("饼图测试");
        pieChardata.setCenterText2Color(Color.BLACK);
        pieChardata.setCenterText2FontSize(18);

        pieChardata.setC*/

/**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/
//      Typeface tf = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");  
//      data.setCenterText1Typeface(tf);  
        this.tv_rate.setText(per);
        chartview_err.setPieChartData(pieChardata);
        chartview_err.setValueSelectionEnabled(true);//选择饼图某一块变大  
        chartview_err.setAlpha(0.9f);//设置透明度  
        chartview_err.setCircleFillRatio(0.9f);//设置饼图大小

//        rl_pie.setVisibility(View.VISIBLE);

    }

    @Override
    public void setSJDetail(List<ExamTitleBean> list) {
        mlist_titlebean.clear();
        int exam_ok_count = 0;
        int exam_err_count = 0;
        int exam_count = 0;
        int exam_no = 0;
        int time = 0;
        if (list != null && list.size() > 0) {
            exam_count = list.size();
            mlist_titlebean.addAll(list);
            flow_dtk.removeAllViews();
            for (int i = 0; list != null && i < list.size(); i++) {
                ExamTitleBean bean_title = list.get(i);
                View view = LayoutInflater.from(this).inflate(R.layout.item_solution, null);
                HolderExamTitle holderExamTitle = new HolderExamTitle();
                holderExamTitle.mView = view;
                holderExamTitle.bean_title = bean_title;
                ViewUtils.initHolderView(holderExamTitle, view, R.id.class);
                holderExamTitle.text_solution_num.setText((i + 1) + "");

                String time_nows = bean_title.getUserTime();
                int time_int = 0;
                try {
                    time_int = Integer.parseInt(time_nows);
                } catch (NumberFormatException e) {
                    time_int = 0;
                }
                time += time_int;

                if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_OK)) {
                    exam_ok_count++;
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.white)));

                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.datika_circle_right);
                } else if (bean_title.getIsOK().equals(Constant.ExamData.EXAM_ERR)) {
                    exam_err_count++;
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_price)));

                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.datika_circle_wrong);

                    holderExamTitle.mView.setOnClickListener(new MyErrOnClick(bean_title.getID()));
                  /*  MyLogUtils.e(TAG,"setSJDetail装载错题");
                    holderExamTitle.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyLogUtils.e(TAG,"setSJDetail点击了错题");
                        }
                    });*/

                } else {
                    //exam_err_count++;
                    holderExamTitle.text_solution_num.setTextColor(getResources()
                            .getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_8c9fb0)));

                    holderExamTitle.img_item_solution_back.setImageResource(R.mipmap.datika_circle_none);
                }
                flow_dtk.addView(view, params_margin);

            }
            initPie(exam_ok_count, exam_err_count, exam_no);
            initPieData(exam_ok_count, exam_err_count, exam_no, exam_count, time);

            textview_total_num.setText(exam_count + "");
            textview_ok_num.setText(exam_ok_count + "");
            textview_err_num.setText(exam_err_count + "");


            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(0);
            stringBuilder.append("");
            tv_noanswer.setText(stringBuilder.toString());

            mPresenter.getAdv();
        } else {
            showLongToast("您还未做过试卷，暂无错题本");
        }
    }

    @Override
    public void setSJ(List<ExamBean> list) {
        mlist_exam.clear();
        if (list != null && list.size() > 0)
            mlist_exam.addAll(list);
        setSjDetailByExam(list);


        // ProgressUtils.stopProgressDlg();
    }

    @Override
    public void setAdv(List<AdvBean> list_adv) {

       /* mlist_adv.clear();
        if(list_adv!=null && list_adv.size()>0)
        {
            mlist_adv.addAll(list_adv);
            pager_main_adv.setLayoutParams(params_banner);
            img_load.setLayoutParams(params_banner);
            indicator_main_adv.setVisibility(View.VISIBLE);
            pager_main_adv.setVisibility(View.VISIBLE);
            img_load.setVisibility(View.GONE);

            adapter_adv.notifyDataSetChanged();
            pager_main_adv.setCurrentItem(0);
            indicator_main_adv.notifyDataSetChanged();
            adapter_adv.notifyDataSetChanged();
        }
        else
        {
            AdvBean bean = new AdvBean();
            bean.setImg(R.mipmap.banner_demo+"");
            mlist_adv.add(bean);
            pager_main_adv.setLayoutParams(params_banner);
            indicator_main_adv.setVisibility(View.VISIBLE);
            pager_main_adv.setVisibility(View.VISIBLE);
            img_load.setVisibility(View.GONE);

            adapter_adv.notifyDataSetChanged();
            pager_main_adv.setCurrentItem(0);
            indicator_main_adv.notifyDataSetChanged();
            adapter_adv.notifyDataSetChanged();
        }

        rl_advs.setVisibility(View.VISIBLE);
        handler.sendEmptyMessageDelayed(0, Constant.SKIP_TIME);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
      /*  if(handler!=null)
            handler.removeMessages(0);*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    /*    if(handler!=null)
        {
            handler.removeMessages(0);

            handler.sendEmptyMessageDelayed(0, Constant.SKIP_TIME);
        }*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       /* if(handler!=null)
            handler.removeMessages(0);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.textview_jx_btn:
                ArrayList<ExamBean> list_err = new ArrayList<>();
                for (int i = 0; mlist_exam != null && i < mlist_exam.size(); i++) {

                    ExamBean title = mlist_exam.get(i);
                    if (title.getIsOK().equals("否")) {
                        list_err.add(title);
                    }
                }
                if (list_err != null && list_err.size() > 0) {
                    String ids = ExamUtils.getExamBeanList(list_err);
                    ExamErrTitleActivity.startExamErrTitleActivity(this, oid_eid, title_name, ids, flag_zhineng, flag_oid);
                } else {
                    showLongToast("暂无错题，无法使用错题解析");
                }
                break;
            case R.id.textview_jx_all_btn:
                /*if(flag_zhineng)
                {*/
                String ids = ExamUtils.getExamBeanList(mlist_exam);
                ExamErrTitleActivity.startExamErrTitleActivity(this, oid_eid, title_name, ids, flag_zhineng, flag_oid);
                /*}
                else
                {
                    //解析
                    ExamErrTitleActivity.startExamErrTitleActivity(this,mlist_titlebean,flag_zhineng);

                }*/

                break;
            case R.id.textview_jx_ctcz_btn:
                //错题重练
                ArrayList<ExamBean> list_errs = new ArrayList<>();
                for (int i = 0; mlist_exam != null && i < mlist_exam.size(); i++) {

                    ExamBean title = mlist_exam.get(i);
                    if (title.getIsOK().equals("否")) {
                        list_errs.add(title);
                    }
                }
                if (list_errs != null && list_errs.size() > 0) {
                    String idss = ExamUtils.getExamBeanList(list_errs);
                    ExamActivity.startExamActivity(this, oid_eid, title_name, idss, flag_zhineng, flag_oid, flag_main, false);
                } else {
                    showLongToast("暂无错题，无法使用错题重练");
                }

                break;
        }
    }


}
