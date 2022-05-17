package com.udit.bankexam.ui.exam_mk;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_mk.ExamMkDetailPresneter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_day.ExamDayDetailActivity;
import com.udit.bankexam.ui.home.fragment.VideoFragment;
import com.udit.bankexam.ui.pay.PaySelectActivity;
import com.udit.bankexam.ui.video.VideoActivity;
import com.udit.bankexam.ui.zhibo.KeChengDetailActivity;
import com.udit.bankexam.ui.zhibo.ZhiboDetailActivity;
import com.udit.bankexam.ui.zhibo.ZhiboLiebiaoActivity;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_mk.ExamMkDetailView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDataUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by zb on 2017/6/5.
 */

public class ExamMkDetailActivity extends BaseActivity<ExamMkDetailPresneter> implements ExamMkDetailView.View, View.OnClickListener {

    public static void startExamMKDetailActivity(BaseActivity<?> mActivity, MKBean bean) {
        Intent intent = new Intent(mActivity, ExamMkDetailActivity.class);

        intent.putExtra("mkbean", bean);

        mActivity.startActivity(intent);
    }

    public static void startExamMkActivity(BaseActivity<?> mActivity, String eid, String type) {
        Intent intent = new Intent(mActivity, ExamMkDetailActivity.class);
        intent.putExtra("EID", eid);
        intent.putExtra("type", type);
        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_mkdetail);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("模考大赛详情");
        text_baogao_btn.setVisibility(View.GONE);
    }

    private final String TAG = this.getClass().getSimpleName();

    private ImageView img_top_return;

    private TextView text_top_centent;

    private TextView text_mk_name;

    private TextView text_mk_num;

    private TextView text_mk_time;

    private LinearLayout ll_mk_zb, ll_mk_video, ll_mk_sj;

    private TextView text_mk_zb_name, text_mk_video_name, text_mk_sj_name;

    private TextView text_sign_btn, text_baogao_btn;

    private WebView webview_mkdetail;

    private MKBean bean_mk;

    private UserBean bean_user;


    private TextView text_mk_video_goumai, text_mk_zb_goumai, text_mk_sj_goumai;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        text_sign_btn.setOnClickListener(this);

        text_mk_video_goumai.setOnClickListener(this);

        text_mk_zb_goumai.setOnClickListener(this);

        text_mk_sj_goumai.setOnClickListener(this);

        text_baogao_btn.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mPresenter = new ExamMkDetailPresneter(this);
        bean_user = SaveUtils.getUser(this);
        String eid = getIntent().getStringExtra("EID");
        if (!MyCheckUtils.isEmpty(eid)) {
            String type = getIntent().getStringExtra("type");
            mPresenter.getMKDetail(this, bean_user.getUid(), eid, type);
        } else {
            bean_mk = (MKBean) getIntent().getSerializableExtra("mkbean");
            setMkInfo(bean_mk);
        }
    }

    private void setMkInfo(MKBean bean_mk) {

        if (bean_mk != null) {
            if (!MyCheckUtils.isEmpty(bean_mk.getName())) {
                text_mk_name.setText(bean_mk.getName());
            }
            if (!MyCheckUtils.isEmpty(bean_mk.getiCount())) {
                text_mk_num.setText(bean_mk.getiCount());
            } else {
                text_mk_num.setText("0");
            }
        /*    if(!MyCheckUtils.isEmpty(bean_mk.getMDate()))
            {
                text_mk_time.setText(bean_mk.getMDate());
            }*/


            if (!MyCheckUtils.isEmpty(bean_mk.getBegDate())
                    && !MyCheckUtils.isEmpty(bean_mk.getEndDate())) {
                String begin = MyDateUtil.chgFmt(bean_mk.getBegDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
                String end = MyDateUtil.chgFmt(bean_mk.getEndDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
                text_mk_time.setText(begin + "-" + end);
            }

            if (!MyCheckUtils.isEmpty(bean_mk.getLiveID())) {
                ll_mk_zb.setVisibility(View.VISIBLE);
                if (!MyCheckUtils.isEmpty(bean_mk.getLivName())) {
                    text_mk_zb_name.setText(bean_mk.getLivName());
                }
                String livePrice = Utils.doubleOutPut(bean_mk.getLivPirce(), 2);
                if (livePrice.equals("0.00") || livePrice.equals("0") || bean_mk.getZbIsGet().equals("是")) {
                    text_mk_zb_goumai.setText("观看直播课");
                } else {
                    text_mk_zb_goumai.setText("点击购买");
                }

            } else {
                ll_mk_zb.setVisibility(View.GONE);
            }


            if (!MyCheckUtils.isEmpty(bean_mk.getVideoID())) {
                ll_mk_video.setVisibility(View.VISIBLE);
                if (!MyCheckUtils.isEmpty(bean_mk.getVidName())) {
                    text_mk_video_name.setText(bean_mk.getVidName());
                }

                String videoPrice = Utils.doubleOutPut(bean_mk.getVidPirce(), 2);

                if (videoPrice.equals("0.00") || videoPrice.equals("0")  || bean_mk.getSpIsGet().equals("是")) {
                    text_mk_video_goumai.setText("观看视频");
                } else {
                    text_mk_video_goumai.setText("点击购买");
                }

            } else {
                ll_mk_video.setVisibility(View.GONE);
            }
            if (!MyCheckUtils.isEmpty(bean_mk.getExaminID())) {

                if (!MyCheckUtils.isEmpty(bean_mk.getExName())) {
                    text_mk_sj_name.setText(bean_mk.getExName());
                }
                String examPrice = Utils.doubleOutPut(bean_mk.getExPrice(), 2);

                if (!MyCheckUtils.isEmpty(bean_mk.getExBegDate())
                        && !MyCheckUtils.isEmpty(bean_mk.getExEndDate())) {
                    ll_mk_sj.setVisibility(View.VISIBLE);

                    text_mk_sj_goumai.setTag(examPrice);
                    if (examPrice.equals("0.00") || examPrice.equals("0") || bean_mk.getSjIsGet().equals("是")) {

                        text_mk_sj_goumai.setText("开始考试");
                    } else {
                        text_mk_sj_goumai.setText("点击购买");
                    }

                    //在试卷 考试时间 内
                    if (MyDateUtil.compareTime2(
                            bean_mk.getExBegDate(),
                            MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_2),
                            MyDateUtil.DATE_FORMAT_2)
                            && MyDateUtil.compareTime2(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_2),
                            bean_mk.getExEndDate(), MyDateUtil.DATE_FORMAT_2)) {
                        text_mk_sj_goumai.setTag(examPrice);
                        if (examPrice.equals("0.00") || examPrice.equals("0")|| bean_mk.getSjIsGet().equals("是")) {

                            text_mk_sj_goumai.setText("开始考试");
                        } else {
                            text_mk_sj_goumai.setText("点击购买");
                        }
                        text_mk_sj_goumai.setVisibility(View.VISIBLE);
                    } else {
                        text_mk_sj_goumai.setVisibility(View.GONE);

                    }
                    if (MyDateUtil.compareTime(
                            bean_mk.getExEndDate(),
                            MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_2), MyDateUtil.DATE_FORMAT_2)
                            && bean_mk.getSjIsGet().equals("是")) {
                        //可以 查看报告
                        text_baogao_btn.setVisibility(View.VISIBLE);
                    } else {
                        text_baogao_btn.setVisibility(View.GONE);
                    }
                } else {//没有 开始 和结束时间
                    ll_mk_sj.setVisibility(View.GONE);
                }
            } else {
                ll_mk_zb.setVisibility(View.GONE);
            }


            if (bean_mk.getIsGet().equals("是")) {
                setSignBtn(true);
            } else {
                setSignBtn(false);
            }
            //内容
            if (!MyCheckUtils.isEmpty(bean_mk.getAllScreen())) {
                WebUtil.WebInit(webview_mkdetail);

                String content = bean_mk.getAllScreen();
                content = content.replaceAll(Constant.IMAGE.IMG_OLD_BEGIN, Constant.IMAGE.IMG_NEW_BEGIN);
                MyLogUtils.e(TAG, content);
                webview_mkdetail.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                webview_mkdetail.setVisibility(View.GONE);
            } else {
                webview_mkdetail.setVisibility(View.GONE);
            }


            if (MyDateUtil.compareTime2(
                    bean_mk.getBegDate(),
                    MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_2),
                    MyDateUtil.DATE_FORMAT_2)
                    && MyDateUtil.compareTime2(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_2),
                    bean_mk.getExEndDate(), MyDateUtil.DATE_FORMAT_2)) {
                text_sign_btn.setVisibility(View.VISIBLE);
            } else
                text_sign_btn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;

            case R.id.text_sign_btn:
                mPresenter.MkSign(this, bean_user.getUid(), bean_mk.getID());
                break;
            case R.id.text_mk_zb_goumai:
                if (ll_mk_zb.getVisibility() == View.VISIBLE) {
                    String text_zb = text_mk_zb_goumai.getText().toString();
                    if (text_zb.equals("观看直播课")) {// 进入到对应视频详情
                        MyLogUtils.e(TAG, "直播课观看直播课");
                        ZhiboLiebiaoActivity.startZhiboLiebiaoActivity(this, bean_mk.getLiveID(), bean_mk.getLivName());
                    } else if (text_zb.equals("点击购买")) {//进入到直播详情
                        MyLogUtils.e(TAG, "直播额点击购买");
                        KeChengDetailActivity.startKeChengDetailActivity(this, bean_mk.getLiveID(), bean_mk.getLivName(), Constant.RESULT.RESULT_MK_ZB_DETAIL);
                    }
                }
                break;
            case R.id.text_baogao_btn:
                ExamMkReportDataActivity.startExamMkReportDataActivity(getActivity(), bean_mk);
                break;

            case R.id.text_mk_sj_goumai:
                if (ll_mk_sj.getVisibility() == View.VISIBLE) {
                    String text_sj = text_mk_sj_goumai.getText().toString();
                    if (text_sj.equals("开始考试")) {// 进入到对应视频详情
                        MyLogUtils.e(TAG, "试卷开始考试");
                        String price_exam = (String) text_mk_sj_goumai.getTag();
                        if (price_exam.equals("0")) {
                            mPresenter.saveExam(bean_user.getUid(), bean_mk.getExaminID(), bean_mk.getExName());
                        }
                        mPresenter.getExam(this, bean_user.getUid(), bean_mk.getExaminID());
                        //  ExamMkReportDataActivity.startExamMkReportDataActivity(getActivity(),bean_mk);
                    } else if (text_sj.equals("点击购买")) {//进入到直播详情
                        MyLogUtils.e(TAG, "试卷点击购买");
                        // ExamDayDetailActivity.startExamDayDetailActivity();
                        PaySelectActivity.startPaySelectActivity((BaseActivity<?>) getActivity(),
                                bean_mk.getExaminID(), Constant.DataType.TYPE_SHIJUAN_MK,
                                bean_mk.getExName(), bean_mk.getExPrice(), Constant.RESULT.RESULT_MK_EXAM_DETAIL);
                    }
                }
                break;
            case R.id.text_mk_video_goumai:
                if (ll_mk_video.getVisibility() == View.VISIBLE) {
                    String text_video = text_mk_video_goumai.getText().toString();
                    if (text_video.equals("观看视频")) {// 进入到对应视频详情
                        MyLogUtils.e(TAG, "观看视频");
                        VideoActivity.startVideoActivity(this, bean_mk.getVideoID(), bean_mk.getVidName());
                        //  VideoFragment.getIntance().startData(bean_mk.getVideoID(),"全员推送视频");

                    } else if (text_video.equals("点击购买")) {//进入到直播详情
                        MyLogUtils.e(TAG, "视频点击购买");
                        PaySelectActivity.startPaySelectActivity((BaseActivity<?>) getActivity(),
                                bean_mk.getVideoID(), Constant.DataType.TYPE_SHIPIN,
                                bean_mk.getVidName(), bean_mk.getVidPirce(), Constant.RESULT.RESULT_MK_VIDEO_DETAIL);

                    }
                }
                break;
        }
    }

    public void setSignBtn(boolean flag) {
        if (flag) {
            text_sign_btn.setBackground(getResources().getDrawable(R.drawable.shape_20_corner_baoming_ok_bg));
            text_sign_btn.setText("报名成功");
            text_sign_btn.setTextColor(getResources().getColorStateList(R.color.color_ff9800));
            text_sign_btn.setEnabled(false);
        } else {
            text_sign_btn.setBackground(getResources().getDrawable(R.drawable.shape_20_corner_558cf4_bg));
            text_sign_btn.setText("点击报名");

            text_sign_btn.setTextColor(getResources().getColorStateList(R.color.white));
            text_sign_btn.setEnabled(true);
        }

    }

    @Override
    public void setMkSignOk() {
        showLongToast("报名成功");
        setSignBtn(true);
    }

    @Override
    public void setMkSignErr() {
        setSignBtn(false);
    }

    @Override
    public void setMKDetail(MKBean bean) {
        if (bean != null) {
            setMkInfo(bean);
        } else {
            showLongToast("暂无模考详情");
        }
    }

    @Override
    public void setExamTitleList(List<ExamTitleBean> list) {
        String list_title = ExamUtils.getExamTtile(list);
        if (!MyCheckUtils.isEmpty(list_title)) {
            ExamActivity.startExamActivity(this, bean_mk.getExaminID(), bean_mk.getExName(),
                    list_title, false, false, false, true);
            // ExamActivity.startExamActivity(this,bean_mk.getExName(),list_title,false,bean_mk.getExaminID());
        } else {
            // bean_info = null;
            showLongToast("暂无题目");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);

        if ((requestCode == Constant.RESULT.RESULT_MK_EXAM_DETAIL
                || requestCode == Constant.RESULT.RESULT_MK_VIDEO_DETAIL
                || requestCode == Constant.RESULT.RESULT_MK_ZB_DETAIL)
                && resultCode == RESULT_OK) {
            mPresenter.getMKDetail(this, bean_user.getUid(), bean_mk.getID(), "全员推送模考");
        }

    }
}
