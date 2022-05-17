package com.udit.bankexam.ui.video.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easefun.polyvsdk.video.PolyvVideoView;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.VideoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.video.VideoInfoPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.video.VideoInfoActivity;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.ZxPop;
import com.udit.bankexam.view.video.VideoInfoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.ToastUtil;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/6/27.
 */

public class PolyvPlayContentFragment extends BaseFragment<VideoInfoPresenter> implements VideoInfoView.View, View.OnClickListener {

    private View mView_main;
    private VideoBean bean_video;
    private ArrayList<VideoBean> mlist_video;
    private final String TAG = this.getClass().getSimpleName();
    int curpostion = 0;
    //知识点
    private LinearLayout ll_line_ke;
    private LinearLayout flowlayout_zs, ll_zx;
    private TextView textview_exam, textview_next, textview_last, tv_line;
    private UserBean userBean;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView_main == null)
            mView_main = inflater.inflate(R.layout.item_video_content, container, false);
        return mView_main;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, mView_main, R.id.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {
        textview_next.setOnClickListener(this);
        textview_last.setOnClickListener(this);
        //客服
        ll_line_ke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                if (Utils.isQQAvailable(getActivity())) {
                    Utils.startQQ(getActivity(), Constant.QQ_ZIXUN);
                } else {
                    ToastUtil.showMessage(getContext(), "请先安装QQ");
                }
            }
        });

        //咨询
        ll_zx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                ZxPop zxPop = new ZxPop(getContext());
                zxPop.showPop(new ZxPop.ClickCallback() {
                    @Override
                    public void cliclPhone() {
                        showLongToast("暂无手机号");
                    }

                    @Override
                    public void cliclWx() {
                        showLongToast("暂无微信号");
                    }
                });
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter = new VideoInfoPresenter(this);
        userBean = SaveUtils.getUser(getActivity());

        Bundle bundle = getArguments();
        mlist_video = (ArrayList<VideoBean>) bundle.getSerializable("videolist");
        curpostion = bundle.getInt("postion");
        bean_video = mlist_video.get(curpostion);
        initZSD(curpostion);
    }

    private void initZSD(int curpostion) {
        flowlayout_zs.removeAllViews();
        setZSDNext(curpostion);
        if (curpostion >= mlist_video.size() - 1) {
            textview_next.setVisibility(View.GONE);
        }
        if (curpostion == 0) {
            textview_last.setVisibility(View.GONE);
        }
        showHide();
    }

    private void setZSDNext(int curpostion) {
        if (curpostion < mlist_video.size()) {
            bean_video = null;
            ProgressUtils.showProgressDlg("下一知识点", getActivity());
            try {
                bean_video = mlist_video.get(curpostion);
            } catch (Exception e) {
                ProgressUtils.stopProgressDlg();
                return;
            }
            PolyvPlayerTopFragment.text_top_centent.setText(bean_video.getName());
            if (MyCheckUtils.isEmpty(bean_video.getEID())) {
                textview_exam.setVisibility(View.GONE);
            } else {
                textview_exam.setVisibility(View.VISIBLE);
            }

            String title = bean_video.getName();
            String content = bean_video.getPoints();
            String vid = bean_video.getAFile();
            // text_top_centent.setText(title);

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_video_zsd, null);
            TextView textview_zsd_title = (TextView) view.findViewById(R.id.textview_zsd_title);
            WebView textview_zsd_content = (WebView) view.findViewById(R.id.textview_zsd_content);

            textview_zsd_title.setText(title);
            //textview_zsd_content.setText(content);

            //  textview_zsd_content.setMovementMethod(LinkMovementMethod.getInstance());//
            MyLogUtils.e(TAG, content);
           /* textview_zsd_content.setText(
                    Html.fromHtml(content,new URLImageParser(textview_zsd_content),null));
*/
            WebUtil.WebInit(textview_zsd_content);

            content = content.replace(Constant.IMAGE.IMG_OLD_BEGIN, Constant.IMAGE.IMG_NEW_BEGIN);
            textview_zsd_content.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            flowlayout_zs.removeAllViews();
            flowlayout_zs.addView(view, params);
            Log.e("测试程序: ", "bean_video.getVideoIdAli()=" + bean_video.getVideoIdAli());
            mPresenter.getVideoUrl(bean_video.getVideoIdAli());
        } else
            return;
    }

    @Override
    public void playVideo(boolean isSuccess, String playUrl0rErrorMsg) {
        ProgressUtils.stopProgressDlg();
        PolyvVideoView videoview = ((VideoInfoActivity) getActivity()).getPolyv_video_view();
        if (null != videoview) {
            if (isSuccess) {
          /*      videoview.setVid(vid);
                videoview.setVideoURI(Uri.parse("https://vd2.bdstatic.com/mda-jjds32wrbpi1wvpx/sc/mda-jjds32wrbpi1wvpx.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1651744386-0-0-fb2e28f003b3d720dec95193db4d41f8&bcevod_channel=searchbox_feed&pd=1&cd=0&pt=3&logid=1385945053&vid=16304399493415557989&abtest=100815_1-101454_5-101830_1-17451_1&klogid=1385945053"));
                videoview.setVideoPath();*/
                Log.e("测试程序: ", "playUrl0rErrorMsg=" + playUrl0rErrorMsg);
                videoview.setVideoURI(Uri.parse(playUrl0rErrorMsg));
                return;
            }
            showLongToast(playUrl0rErrorMsg);
            return;
        }
        showLongToast("抱歉，视频播放器异常，请退出重试~");
        getActivity().finish();
    }

    @Override
    public void getVidoeInfoNext(int postion) {

    }

    @Override
    public void playVideo(String video_url) {

    }

    @Override
    public void setExamInfo(List<ExamTitleBean> list) {
        if (list != null && list.size() > 0) {
            String ids = ExamUtils.getExamTtile(list);
            if (!MyCheckUtils.isEmpty(ids)) {
                ExamActivity.startExamActivity((BaseActivity<?>) getActivity(), bean_video.getEID(), bean_video.getName(), ids, false, false, false, false);
            } else {
                showLongToast("暂无试题");
            }


        } else {
            showLongToast("暂无试题");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview_next:
                curpostion++;
                setZSDNext(curpostion);
                textview_last.setVisibility(View.VISIBLE);
                if (curpostion >= mlist_video.size() - 1) {
                    textview_last.setVisibility(View.VISIBLE);
                    textview_next.setVisibility(View.GONE);
                }
                showHide();
                break;
            case R.id.textview_last:
                curpostion--;
                setZSDNext(curpostion);
                textview_next.setVisibility(View.VISIBLE);
                if (curpostion <= 0) {
                    textview_last.setVisibility(View.GONE);
                    textview_next.setVisibility(View.VISIBLE);
                }
                showHide();
                break;
            case R.id.textview_exam:
                if (bean_video != null) {
                    if (!MyCheckUtils.isEmpty(bean_video.getEID())) {
                        mPresenter.saveExam(bean_video.getEID(), userBean.getUid(), bean_video.getName());
                        mPresenter.getExamInfo(getActivity(), userBean.getUid(), bean_video.getEID());
                    } else {
                        showLongToast("暂无试题");
                    }
                } else {
                    showLongToast("暂无试题");
                }
                break;
        }
    }

    private void showHide() {
        boolean leftShow = textview_last.getVisibility() == View.VISIBLE;
        boolean rightShow = textview_next.getVisibility() == View.VISIBLE;
        if (leftShow && rightShow) {
            ll_line_ke.setVisibility(View.GONE);
            tv_line.setVisibility(View.GONE);
            ll_zx.setVisibility(View.VISIBLE);
        } else if (!leftShow && rightShow) {
            ll_line_ke.setVisibility(View.VISIBLE);
            tv_line.setVisibility(View.VISIBLE);
            ll_zx.setVisibility(View.VISIBLE);
        } else if (leftShow && !rightShow) {
            ll_line_ke.setVisibility(View.VISIBLE);
            tv_line.setVisibility(View.VISIBLE);
            ll_zx.setVisibility(View.VISIBLE);
        } else if (!leftShow && !rightShow) {
            ll_line_ke.setVisibility(View.VISIBLE);
            tv_line.setVisibility(View.VISIBLE);
            ll_zx.setVisibility(View.VISIBLE);
        }
    }
}
