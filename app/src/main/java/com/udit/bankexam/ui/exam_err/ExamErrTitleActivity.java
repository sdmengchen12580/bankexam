package com.udit.bankexam.ui.exam_err;

import android.app.Dialog;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_err.ExamErrTitlePresenter;

import com.udit.bankexam.ui.exam.JiuCuoActivity;
import com.udit.bankexam.ui.exam_err.holder.ExamAdapter;
import com.udit.bankexam.ui.exam.holder.HolderWindow;
import com.udit.bankexam.ui.exam_err.holder.HolderExam;
import com.udit.bankexam.ui.exam_err.holder.HolderOption;
import com.udit.bankexam.ui.exam_err.holder.HolderSolution;
import com.udit.bankexam.utils.ExamShouCang;
import com.udit.bankexam.utils.PagerUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.bankexam.view.exam_err.ExamErrTitleView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/19.
 * 解析
 */

public class ExamErrTitleActivity extends BaseActivity<ExamErrTitlePresenter> implements ExamErrTitleView.View,
        View.OnClickListener, HolderWindow.ChangeSizeType, HolderWindow.ChangeTheme, HolderExam.ExamOptionsSelected, HolderExam.ExamNoteListener, HolderWindow.FenXiangListener, UMShareListener, ShareBoardlistener,
        GestureOverlayView.OnGesturePerformedListener, GestureOverlayView.OnGesturingListener,
        ThemeManager.OnThemeChangeListener, HolderSolution.MySolutionListener, HolderWindow.JiCuoListener {

    private final String TAG = this.getClass().getSimpleName();
    private boolean flag_zhineng = false;

    public static void startExamErrTitleActivity(BaseActivity<?> mActivity,
                                                 String oid_eid, String title_name, String examIds,
                                                 boolean flag_zhineng, boolean flag_oid) {
        Intent intent = new Intent(mActivity, ExamErrTitleActivity.class);
        intent.putExtra("oid_eid", oid_eid);
        intent.putExtra("title_name", title_name);
        intent.putExtra("examIds", examIds);
        intent.putExtra("flag_zhineng", flag_zhineng);
        intent.putExtra("flag_oid", flag_oid);
        mActivity.startActivity(intent);


    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_exam_err_title);

    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);

        text_top_centent.setText("错题本");
        img_exam_time.setVisibility(View.GONE);
        text_exam_centent.setVisibility(View.VISIBLE);
        img_exam_biaoji.setVisibility(View.GONE);

    }

    private ImageView img_top_return;


    /**
     * 草稿纸，答题卡，收藏，分享
     */
    private ImageView img_exam_cgz, img_exam_dtk, img_exam_sc, img_exam_biaoji;
    private LinearLayout img_exam_gengduo;
    /**
     * 计时器
     */
    private TextView img_exam_time;

    /**
     * *********************更多功能***********
     */

    private HolderWindow holderWindow;

    /**
     * 题目
     */
    private ViewPager viewpager_exam;

    private TextView text_exam_last, text_exam_next, text_exam_centent;


    private List<ExamBean> mlist_exam;

    private UserBean bean_user;

    private ExamAdapter adapter;

    private RelativeLayout rl_item_top_exam;

    private TextView text_top_centent;

    private LinearLayout ll_exam;

    private ShareAction shareAction;

    private View mViewCurrent;

    @Override
    public void initListeners() {

        ThemeManager.setThemeMode(ThemeManager.ThemeMode.DAY);
        ThemeManager.registerThemeChangeListener(this);
        img_top_return.setOnClickListener(this);

        img_exam_gengduo.setOnClickListener(this);

        text_exam_last.setOnClickListener(this);

        text_exam_next.setOnClickListener(this);

        img_exam_dtk.setOnClickListener(this);

        img_exam_cgz.setOnClickListener(this);

        img_exam_sc.setOnClickListener(this);

        viewpager_exam.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ExamBean bean = mlist_exam.get(position);

                mViewCurrent = viewpager_exam.getChildAt(viewpager_exam.getCurrentItem());
                String id = bean.getID();
                if (MyCheckUtils.isEmpty(bean.getTitleID()))
                    id = bean.getTitleID();
                getShCang(id);

                text_exam_centent.setText((position + 1) + "/" + mlist_exam.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private boolean flag_oid;

    private String title_name;

    private String exam_oid_id;

    private String examIds;

    private HolderSolution holderSolution;

    private BaseActivity<?> mContext;

    @Override
    public void initData() {

        mPresenter = new ExamErrTitlePresenter(this);
        mContext = this;
        shareAction = SharedUtils.getShareAction((BaseActivity<?>) getActivity(), this, this);
        //练习题
        mlist_exam = new ArrayList<>();

        bean_user = SaveUtils.getUser(this);

        //答题卡
        Object obj_exam_oid_id = getIntent().getStringExtra("exam_oid_id");
        Object obj_title_name = getIntent().getStringExtra("title_name");
        Object obj_examIds = getIntent().getStringExtra("examIds");
        flag_zhineng = getIntent().getBooleanExtra("flag_zhineng", false);
        flag_oid = getIntent().getBooleanExtra("flag_oid", false);
        MyLogUtils.e(TAG, "flag_zhineng:" + flag_zhineng);
        //检查标题
        if (obj_title_name != null && !MyCheckUtils.isEmpty(obj_title_name.toString())) {
            title_name = obj_title_name.toString();
            text_top_centent.setText(title_name);
            holderSolution = new HolderSolution(this, title_name, this);

            if (title_name != null && title_name.equals("搜索")) {
                text_exam_last.setVisibility(View.GONE);
                text_exam_next.setVisibility(View.GONE);

            }

        } else {
            MyLogUtils.e(TAG, "标题未传入？？");
            showLongToast("暂无试题");
            mContext.onLowMemory();
            finish();
            return;
        }
        //检查id
        if (obj_exam_oid_id == null
                || MyCheckUtils.isEmpty(obj_exam_oid_id.toString())) {
/*
            if(!flag_zhineng)
            {
                MyLogUtils.e(TAG,"试卷ID or 节点 ID 未传入？？");
                showLongToast("暂无试题");
                mContext.onLowMemory();
                finish();
                return;
            }
            else
            {
                MyLogUtils.e(TAG,"智能 相关，无需强制 传入ID");
            }*/

        } else {
            exam_oid_id = obj_exam_oid_id.toString();
        }

        //检查 题目内容 ID
        if (obj_examIds == null
                || MyCheckUtils.isEmpty(obj_examIds.toString())) {

          /*  MyLogUtils.e(TAG,"试卷 题目ID 未传入？？");
            showLongToast("暂无试题");
            mContext.onLowMemory();
            finish();
            return;*/
        } else {
            examIds = obj_examIds.toString();
        }

        //更多
        holderWindow = new HolderWindow(this, this, this);
        //分享
        holderWindow.setFenXiangListener(this);
        holderWindow.setJiuCuoListener(this);
        if (flag_zhineng) {//智能的取题目
            mPresenter.getRobotExamList(this, bean_user.getUid(), examIds);
        } else {//非智能的取题目
            mPresenter.getExam(this, bean_user.getUid(), examIds);
        }

    }

    private Dialog dialog_canse;

    private void showDialog_cgz(int show) {
        if (dialog_canse != null) {
            if (show == Constant.DIALOG_SHOW)
                dialog_canse.show();
            else
                dialog_canse.dismiss();
        } else {
            if (show == Constant.DIALOG_CANCAL)
                return;
            dialog_canse = new Dialog(this, R.style.AlertDialog);
            dialog_canse.show();
            dialog_canse.getWindow().setContentView(R.layout.layout_cancas);
            holder_canse = new Holder_canse();
            ViewUtils.initDialog(dialog_canse, holder_canse, R.id.class);
            holder_canse.img_cancas_cancle.setOnClickListener(this);
            holder_canse.img_cancas_delete.setOnClickListener(this);
            holder_canse.img_cancas_back.setOnClickListener(this);
            holder_canse.img_cancas_return.setOnClickListener(this);
            //设置手势可多笔画绘制，默认情况为单笔画绘制
            holder_canse.cancas_gesture.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
            //设置手势的颜色(蓝色)
            holder_canse.cancas_gesture.setGestureColor(getMyColor(R.color.back));
            //设置还没未能形成手势绘制是的颜色(红色)
            holder_canse.cancas_gesture.setUncertainGestureColor(getMyColor(R.color.back));

            //设置手势的粗细
            holder_canse.cancas_gesture.setGestureStrokeWidth(4);
            /*手势绘制完成后淡出屏幕的时间间隔，即绘制完手指离开屏幕后相隔多长时间手势从屏幕上消失；
             * 可以理解为手势绘制完成手指离开屏幕后到调用onGesturePerformed的时间间隔
             * 默认值为420毫秒，这里设置为2秒
             */
            holder_canse.cancas_gesture.setFadeOffset(1 * 60 * 60 * 1000);

            //绑定监听器
            holder_canse.cancas_gesture.addOnGesturePerformedListener(this);
            holder_canse.cancas_gesture.addOnGesturingListener(this);

            holder_canse.img_cancas_delete.setEnabled(false);
            holder_canse.img_cancas_back.setEnabled(false);
            holder_canse.img_cancas_return.setEnabled(false);

            holder_canse.gestureLib = GestureLibraries.fromPrivateFile(this, "mygesture");

        }

    }


    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

    }

    @Override
    public void onGesturingStarted(GestureOverlayView overlay) {

    }

    @Override
    public void onGesturingEnded(GestureOverlayView overlay) {
        holder_canse.img_cancas_delete.setEnabled(true);
        holder_canse.img_cancas_back.setEnabled(true);
        holder_canse.img_cancas_return.setEnabled(true);
        holder_canse.gestureLib.addGesture("text", overlay.getGesture());
    }


    @Override
    public void clickSolution(int postion, View view_solution, ExamBean bean, ExamBean bean_solution) {
        viewpager_exam.setCurrentItem(postion);
        holderSolution.getDialog_solution().dismiss();
    }

    @Override
    public void upSolution(List<ExamBean> list) {

    }

    @Override
    public void upAllSolution(List<ExamBean> list) {

    }

    @Override
    public void jiucuo() {
        int current = viewpager_exam.getCurrentItem();
        ExamBean bean_current = mlist_exam.get(current);
        JiuCuoActivity.startJiuCuoActivity(this, bean_current.getID());

    }


    private class Holder_canse {
        private GestureOverlayView cancas_gesture;

        private GestureLibrary gestureLib;

        private ImageView img_cancas_back;

        private ImageView img_cancas_cancle;

        private ImageView img_cancas_delete;

        private ImageView img_cancas_return;
    }

    private Holder_canse holder_canse;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (holder_canse != null) {
            //移除绑定的监听器
            holder_canse.cancas_gesture.removeOnGesturePerformedListener(this);
            holder_canse.cancas_gesture.removeOnGesturingListener(this);
            holder_canse = null;
        }

        ThemeManager.unregisterThemeChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_exam_dtk:
                try {
                    holderSolution.getDialog_solution().show();
                } catch (Exception e) {

                }
                break;
            case R.id.img_exam_cgz:
                //草稿
                showDialog_cgz(Constant.DIALOG_SHOW);
                break;
            case R.id.img_cancas_cancle:
                showDialog_cgz(Constant.DIALOG_CANCAL);
                break;
            case R.id.img_cancas_delete:

                break;
            case R.id.img_cancas_back:

                break;
            case R.id.img_cancas_return:

                break;
            case R.id.img_exam_sc:
                int postion = viewpager_exam.getCurrentItem();
                ExamBean bean = mlist_exam.get(postion);
                String id = bean.getID();
                if (MyCheckUtils.isEmpty(id))
                    id = bean.getTitleID();
                String type = Constant.DataType.TYPE_SHITI;
                if (flag_zhineng) {
                    type = Constant.DataType.TYPE_ZHINENG;
                }
                boolean flag = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), id, type);
                if (flag) {
                    mPresenter.quxiaoshoucang(bean_user.getUid(), id, Constant.DataType.TYPE_SHITI);
                } else {
                    mPresenter.shoucang(bean_user.getUid(),
                            Constant.DataType.TYPE_SHITI,
                            id,
                            MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));


                }

                break;
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.img_exam_gengduo:
                holderWindow.getWindow_gengduo().showAsDropDown(img_exam_gengduo);
                break;
            case R.id.text_exam_last:
                int index = viewpager_exam.getCurrentItem();
                MyLogUtils.e(TAG, "上一题" + index);
                int postion1 = PagerUtils.last(index, mlist_exam.size(), text_exam_next);
                if (postion1 <= -1) {
                    showLongToast("已经到第一题");
                    return;
                }

                setScImage(postion1);
                viewpager_exam.setCurrentItem(postion1);
                break;
            case R.id.text_exam_next:


                int index2 = viewpager_exam.getCurrentItem();
                int postion2 = PagerUtils.next(index2, mlist_exam.size(), text_exam_next, false);
                setScImage(postion2);
                viewpager_exam.setCurrentItem(postion2);


                break;
        }
    }

    public void setScImage(int postion) {
        ExamBean bean2 = mlist_exam.get(postion);
        String id2 = bean2.getID();
        if (MyCheckUtils.isEmpty(id2))
            id2 = bean2.getTitleID();

        String type = Constant.DataType.TYPE_SHITI;
        if (flag_zhineng) {
            type = Constant.DataType.TYPE_ZHINENG;
        }
        boolean flag2 = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), id2, type);
        if (flag2) {
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(R.mipmap.img_shoucang_sel));
        } else
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(R.mipmap.img_shoucang));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.e
                (TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);

        if (resultCode == RESULT_OK && requestCode == Constant.RESULT.RESULT_NOTE_START) {
            String biji = data.getStringExtra("note");
            if (!MyCheckUtils.isEmpty(biji)) {
                int postion = viewpager_exam.getCurrentItem();
                MyLogUtils.e(TAG, "POSTION:" + postion);
                for (int i = 0; i < viewpager_exam.getChildCount(); i++) {
                    View view = viewpager_exam.getChildAt(i);
                    HolderExam holderExam = (HolderExam) view.getTag();
                    if (holderExam != null)
                        holderExam.setTextBj(biji, postion + 1);

                }
                //mlist_exam.get()

//                HolderExam exam_holder= (HolderExam) mViewCurrent.getTag();
                //exam_holder.text_biji.setText(biji);
            }

        }
    }

    public void getShCang(String id) {

        String type = Constant.DataType.TYPE_SHITI;
        if (flag_zhineng) {
            type = Constant.DataType.TYPE_ZHINENG;
        }
        boolean flag = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), id, type);
        if (flag) {
            // img_exam_sc.setImageResource(R.mipmap.zhibo_detail_icon_collect_press);
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(R.mipmap.img_shoucang_sel));
        } else
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(R.mipmap.img_shoucang));

    }

    @Override
    public void setExam(List<ExamBean> list) {
        if (list != null && list.size() > 0) {
            mlist_exam.addAll(list);
            holderSolution.setMlist_exam(mlist_exam);
            ExamBean bean_one = mlist_exam.get(0);
            getShCang(bean_one.getID());
            // mlist_view = new ArrayList<>();
            adapter = new ExamAdapter(this, list, this, flag_zhineng);
            //初始化 字体
            adapter.setSize(holderWindow.getmCurrent_TextSize());

            viewpager_exam.setOffscreenPageLimit(1);
            viewpager_exam.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            viewpager_exam.getParent().requestDisallowInterceptTouchEvent(false);
            text_exam_centent.setText(1 + "/" + mlist_exam.size());

            if (list.size() == 1) {
                text_exam_next.setVisibility(View.GONE);
                text_exam_last.setVisibility(View.GONE);

            }


        } else
            showLongToast("获取解析失败");
    }


    @Override
    public void setBJOK(NoteBean bean) {

        // DBUtils.getInstance().insertNote(bean);
    }

    @Override
    public void setBJERR() {

    }

    @Override
    public void getBJ(int postion, NoteBean bean) {
        HolderExam exam_holder = (HolderExam) mViewCurrent.getTag();
        if (bean != null) {

            exam_holder.text_biji.setText(bean.getNote() + "");
            //exam_holder.text_biji.invalidate();
        } else {
            exam_holder.text_biji.setText("添加笔记");
            //    exam_holder.text_biji.invalidate();
        }


        // DBUtils.getInstance().insertNote(bean);

    }

    @Override
    public void shoucangOK(List<FavoriteRecord> list, boolean ok) {

        ExamShouCang.insertAllExamShouCang(bean_user.getUid(), list);


        int postion = viewpager_exam.getCurrentItem();

        ExamBean examBean = mlist_exam.get(postion);
        String str2 = examBean.getID();
        String str1 = str2;
        if (MyCheckUtils.isEmpty(str2))
            str1 = examBean.getTitleID();
        str2 = "试题";
        if (this.flag_zhineng)
            str2 = "专项智能";
        boolean bool = ExamShouCang.getLocalExamShouCang(this.bean_user.getUid(), str1, str2);
        if (bool) {
            this.img_exam_sc.setImageDrawable(getResources().getDrawable(R.mipmap.img_shoucang_sel));
        } else {
            this.img_exam_sc.setImageDrawable(getResources().getDrawable(R.mipmap.img_shoucang));
        }
        if (ok) {
            if (bool) {
                showLongToast("收藏成功");
                return;
            }
            showLongToast("收藏失败");
            return;
        }
        if (!bool) {
            showLongToast("取消收藏成功");
            return;
        }
        showLongToast("取消收藏失败");
    }

    @Override
    public void shoucangErr() {
        img_exam_sc.setImageDrawable(getResources()
                .getDrawable(R.mipmap.shiti_icon_collect));
    }

    @Override
    public void ExamOptionClick(View view_options, int postion_exam, int postions, ExamBean bean_exam, ExamOptionBean bean_option) {

    }

    @Override
    public void startNote(String titleId) {
        NoteActivity.startNoteActivity(this, titleId, Constant.RESULT.RESULT_NOTE_START);
    }

    @Override
    public void fenxiang() {
        MyLogUtils.e(TAG, "启用分享:" + viewpager_exam.getCurrentItem());

        ShareBoardConfig config = new ShareBoardConfig();

        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.open(config);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }

    @Override
    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
        MyLogUtils.e(TAG, "onClick:" + share_media.name());
        if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE
                || share_media == SHARE_MEDIA.WEIXIN) {//微信朋友圈


        }
        ExamBean bean_fenxiang = mlist_exam.get(viewpager_exam.getCurrentItem());

        UMWXHandler handler = new UMWXHandler();
        String s = handler.getSDKVersion();
        MyLogUtils.e(TAG, "handle:" + s);
        String id = bean_fenxiang.getID();
        if (MyCheckUtils.isEmpty(id)) {
            id = bean_fenxiang.getTitleID();
        }
        String url = Constant.Shared.URL_TIMU + "" + id;
        if (flag_zhineng) {
            url += "&" + Constant.Shared.URL_EXAM_FLAG + Constant.Shared.URL_ZN;

        } else {
            url += "&" + Constant.Shared.URL_EXAM_FLAG + Constant.Shared.URL_EXAM;
        }
        UMWeb web = new UMWeb(url);
        web.setThumb(new UMImage(getActivity(), R.drawable.shared));
        web.setDescription(Constant.Shared.content_st);
        web.setTitle(Constant.Shared.title);
        shareAction.withMedia(web);
        shareAction.setPlatform(share_media).share();
    }


    /**
     * *******************************白天、黑夜*************************
     */


    @Override
    public void onThemeChanged() {
        initTheme();
        initExamTheme();
        holderWindow.initTheme();
        holderSolution.initThme();
       /* if(adapter!=null)
            adapter.setSize(holderWindow.getmCurrent_TextSize());
*/
    }


    public void initExamTheme() {
        for (int i = 0; i < viewpager_exam.getChildCount(); i++) {
            View view = viewpager_exam.getChildAt(i);

            int color = ThemeManager.getCurrentThemeRes(mContext,
                    R.color.color_exam_black);

            view.setBackgroundColor(mContext.getResources().getColor(color));
            HolderExam holder_exam = (HolderExam) view.getTag();


            holder_exam.text_exam_material.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));
            holder_exam.text_exam_material.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));


            holder_exam.text_exam_qtype.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_background_exam_tixing)));
            holder_exam.text_exam_qtype.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_point)));


            holder_exam.ll_exam_content.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));


            holder_exam.text_exam_content.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_exam_qpoint.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_current.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_max.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));

            holder_exam.text_exam.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_exam.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

            //解析 相关


            holder_exam.ll_jiexi.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

            holder_exam.text_zhengquedan.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));

            holder_exam.text_num_qbzt.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));

            holder_exam.text_num_qbzt.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));
            holder_exam.text_num_qbdd.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_qbdd.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));

            holder_exam.text_num_qbdc.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_num_qbdc.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));

            holder_exam.text_num_qbxx.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));


            holder_exam.text_num_qbxx.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));
            holder_exam.text_all_dt.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


            holder_exam.text_all_dd.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


            holder_exam.text_all_dc.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


            holder_exam.text_all_xx.setBackground(mContext.getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_jieda)));


            holder_exam.text_jiexi.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_jiexi.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));

            holder_exam.text_biji.setTextColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_text)));
            holder_exam.text_biji.setBackgroundColor(mContext.getResources()
                    .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_content_ll)));


            for (int j = 0; holder_exam.getMlist_options() != null
                    && j < holder_exam.getMlist_options().size(); j++) {
                View view_option = holder_exam.getMlist_options().get(j);
                HolderOption holderOption = (HolderOption) view_option.getTag();


                view_option.setBackgroundColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_back)));


                holderOption.rl_options.setBackground(mContext.getResources()
                        .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));
                // holderOption.ll_options.setBackground(mContext.getResources()
                //       .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));
                holderOption.text_options_index.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_option)));
                holderOption.text_options.setTextColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_option)));

            }
        }

    }


    public void initTheme() {
        rl_item_top_exam.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this,
                R.color.color_top_rl)));

        img_top_return.setImageDrawable(getResources().getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.backhevron)));
        text_top_centent.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_top_text)));
        //img_top_right.setImageDrawable(getResources().getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.day_btn_history)));

        img_exam_cgz.setImageDrawable(getResources().getDrawable(R.mipmap.img_timu_caozuo));

        img_exam_dtk.setImageDrawable(getResources().getDrawable(R.mipmap.img_datika));

        int current = viewpager_exam.getCurrentItem();

        getShCang(mlist_exam.get(current).getID());
      /*  boolean flag =  ExamShouCang.getLocalExamShouCang(bean_user.getUid(),mlist_exam.get(current).getID());
        if(flag)
        {
            // img_exam_sc.setImageResource(R.mipmap.zhibo_detail_icon_collect_press);
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.zhibo_detail_icon_collect_press)));
        }
        else
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.shiti_icon_collect)));

*/


        // img_exam_time.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_top_text)));
        //总体
        ll_exam.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_exam_black)));

        //练习
        viewpager_exam.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_exam_black)));

    }


    @Override
    public void ChangeTextSize(HolderWindow.TextSize size) {

        changePageViewTextSize(size);
        //  adapter.setSize(size);
        if (adapter != null)
            adapter.setSize(size);
        MyLogUtils.e(TAG, "修改字号大小" + size.name());


    }

    private void changePageViewTextSize(HolderWindow.TextSize size) {
        for (int i = 0; i < viewpager_exam.getChildCount(); i++) {
            View view = viewpager_exam.getChildAt(i);
            HolderExam exam_holder = (HolderExam) view.getTag();
            exam_holder.text_exam_material.setTextSize(size.getSize());
            exam_holder.text_exam.setTextSize(size.getSize());
            for (int j = 0; exam_holder.getMlist_options() != null
                    && j < exam_holder.getMlist_options().size(); j++) {
                HolderOption holderOption = (HolderOption) exam_holder.getMlist_options().get(j).getTag();
                holderOption.text_options_index.setTextSize(size.getSize());
                holderOption.text_options.setTextSize(size.getSize());
            }
        }

    }

    @Override
    public void ChangeTheme(ThemeManager.ThemeMode mode) {
        ThemeManager.setThemeMode(mode);
    }

}
