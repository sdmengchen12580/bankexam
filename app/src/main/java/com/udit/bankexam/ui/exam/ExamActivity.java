package com.udit.bankexam.ui.exam;

import java.util.ArrayList;
import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamHistoryBean;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamOptionBean;

import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.Constant.ExamType;
import com.udit.bankexam.presenter.exam.ExamPresenter;
import com.udit.bankexam.ui.exam.adapter.ExamAdapter;
import com.udit.bankexam.ui.exam.holder.HolderExam;
import com.udit.bankexam.ui.exam.holder.HolderOption;
import com.udit.bankexam.ui.exam.holder.HolderSolution;
import com.udit.bankexam.ui.exam.holder.HolderWindow;
import com.udit.bankexam.ui.exam_err.ExamErrDetailActivity;
import com.udit.bankexam.utils.ExamShouCang;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.PagerUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.utils.ThemeManager;
import com.udit.bankexam.view.exam.ExamView;
import com.udit.frame.common.dialog.CustomDialog;
import com.udit.frame.common.dialog.MessageDialog;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
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

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.GestureOverlayView.OnGesturingListener;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;


public class ExamActivity extends BaseActivity<ExamPresenter> implements ExamView.View, OnClickListener,
        OnGesturePerformedListener, OnGesturingListener, ThemeManager.OnThemeChangeListener, HolderWindow.ChangeSizeType, HolderWindow.ChangeTheme, HolderSolution.MySolutionListener, HolderExam.ExamOptionsSelected, UMShareListener, ShareBoardlistener, HolderWindow.FenXiangListener, HolderWindow.JiCuoListener {
    private final String TAG = this.getClass().getSimpleName();

    private UserBean bean_user;
    private boolean flag_mk;

    /**
     * @param mActivity
     * @param exam_oid_id  试卷/节点ID
     * @param title_name   标题 name
     * @param examIds      为试卷的题目的IDS ，用来向服务器请求题目，如果IDS 为null，并且为试卷，则使用试卷ID 请求试卷
     * @param flag_zhineng 是/否为智能  true 为 智能  false 为普通试卷
     * @param flag_oid     是/否 为 节点ID，是 为节点ID，否为试卷ID
     */
    public static void startExamActivity(BaseActivity<?> mActivity, String exam_oid_id, String title_name,
                                         String examIds, boolean flag_zhineng, boolean flag_oid, boolean flag_main, boolean flag_mk

    ) {
        Intent intent = new Intent(mActivity, ExamActivity.class);
        intent.putExtra("exam_oid_id", exam_oid_id);
        intent.putExtra("title_name", title_name);
        intent.putExtra("examIds", examIds);
        intent.putExtra("flag_zhineng", flag_zhineng);
        intent.putExtra("flag_oid", flag_oid);
        intent.putExtra("flag_main", flag_main);
        intent.putExtra("flag_mk", flag_mk);
        mActivity.startActivityForResult(intent, Constant.RESULT.RESULT_EXAM_HOME);
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_exam);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
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

    private ViewPager viewpager_exam;

    private TextView text_exam_last, text_exam_next, text_exam_centent;

    // private boolean flag_begin_time = false;


    private int count_time = 0;

    private Handler handler_time = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count_time++;
            String s = MyDateUtil.formatLongTime(count_time * 1000);
            img_exam_time.setText(s + "");
            holderSolution.getText_top_solution_time().setText(s + "");
            handler_time.postDelayed(this, 1000);

        }
    };

    private String current_date = "";


    /**
     * *********************更多功能***********
     */

    private HolderWindow holderWindow;
    /**
     * ****************练习题目******************
     */

    // private List<View> mlist_view;


    private ArrayList<ExamBean> mlist_exam;

    private ExamAdapter adapter;

    private int index = 0;

    /**
     * **************手写板*******************
     */
    private Dialog dialog_canse;

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
        String url = Constant.Shared.URL_TIMU + "" + bean_fenxiang.getID();
        if (flag_zhineng) {
            url += "&" + Constant.Shared.URL_EXAM_FLAG + Constant.Shared.URL_ZN;

        } else {
            url += "&" + Constant.Shared.URL_EXAM_FLAG + Constant.Shared.URL_EXAM;
        }
        UMWeb web = new UMWeb(url);
        //UMWeb web = new UMWeb(Constant.Shared.URL_TIMU+""+bean_fenxiang.getID());
        web.setThumb(new UMImage(getActivity(), R.drawable.shared));
        //web.setDescription(Constant.Shared.content_st);
        String content = bean_fenxiang.getTitle();
        if (content != null && !content.isEmpty()) {
            int index_img = content.indexOf("<");
            if (index_img >= 0 && index_img <= 10) {
                web.setDescription(bean_fenxiang.getContent());
            } else {
                if (index_img < 0) {
                    if (content.length() > 30) {

                        String contents = content.substring(0, 30);
                        web.setDescription(contents);
                    } else {
                        web.setDescription(content);
                    }

                } else {
                    String contents = content.substring(0, index_img - 1);
                    web.setDescription(contents);
                }

            }
        } else {
            web.setDescription(Constant.Shared.content_st);

        }
        if (title_name != null && !title_name.isEmpty())
            web.setTitle(title_name);
        else
            web.setTitle(Constant.Shared.title);
        shareAction.withMedia(web);
        shareAction.setPlatform(share_media).share();
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
    public void jiucuo() {
        int current = viewpager_exam.getCurrentItem();

        ExamBean bean_current = mlist_exam.get(current);

        JiuCuoActivity.startJiuCuoActivity(mContext, bean_current.getID());

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


    /**
     * ****************答题卡*********************
     */
    private HolderSolution holderSolution;


    /*
     * ******************白天/黑夜模式*******************
     * */
    private RelativeLayout rl_item_top_exam;

    private TextView text_top_centent;

    // private ImageView img_top_right;

    private LinearLayout ll_exam_caozuo;

    private LinearLayout ll_exam;

    private ShareAction shareAction;

    private BaseActivity mContext;


    @Override
    public void clickSolution(int postion, View view_solution, ExamBean bean, ExamBean bean_solution) {
        MyLogUtils.e(TAG, "点击答题卡" + bean.getContent());

        viewpager_exam.setCurrentItem(postion);
        holderSolution.getDialog_solution().dismiss();

    }


    public void finishs() {
        Intent intent = new Intent();
        intent.putExtra(Constant.RESULT.OID, exam_oid_id);
        intent.putExtra(Constant.RESULT.TRUE_FLAG_OID, flag_oid);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 检查 并提交答题卡的部分
     *
     * @param list
     */
    @Override
    public void upSolution(final List<ExamBean> list) {
        if (list == null || list.size() <= 0) {
            MyLogUtils.e(TAG, "错误提交引发的报错现象");
            finishs();
        }


        int count_no = 0;
        for (int i = 0; list != null && i < list.size(); i++) {
            if (list.get(i) == null || MyCheckUtils.isEmpty(list.get(i).getIsOK()))
                count_no++;
            else {

            }
        }

        if (count_no != list.size()) {
            final CustomDialog.Builder builder = new CustomDialog.Builder(this);
            builder.setMessage("确定要退出练习？退出后未完成的练习将被保存");
            builder.setTitle("退出");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                               /* dialog.dismiss();
                                finish();*/
                    bean_history.setSelected_exam(viewpager_exam.getCurrentItem());
                    bean_history.setFlag_cl(true);
                    ExamUtils.saveHisory(bean_history, list, flag_zhineng, flag_oid, bean_history.get_id(),
                            bean_user.getUid());
                    dialog.dismiss();
                    // mContext.onLowMemory();
                    finishs();

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    bean_history.setFlag_cl(false);
                    ExamUtils.saveHisory(bean_history, list, flag_zhineng, flag_oid, bean_history.get_id(),
                            bean_user.getUid());
                    //  mContext.onLowMemory();
                    dialog.dismiss();
                    finishs();
                }
            });
            builder.create().show();
        } else {
            bean_history.setFlag_cl(false);
            ExamUtils.saveHisory(bean_history, list, flag_zhineng, flag_oid, bean_history.get_id(), bean_user.getUid());
            mContext.onLowMemory();
            finishs();
        }

    }

    @Override
    public void upAllSolution(final List<ExamBean> list) {
        int count_no = 0;
        m = 0;
        n = 0;
        for (int i = 0; list != null && i < list.size(); i++) {
            if (list.get(i) == null || MyCheckUtils.isEmpty(list.get(i).getIsOK()))
                count_no++;
            else {
            }
        }

        if (count_no == list.size()) {//没有 一题答
            showLongToast("没有做题，无法提交答案");
        } else {
            if (count_no > 0) {
                final CustomDialog.Builder builder = new CustomDialog.Builder(this);
                builder.setMessage("您还有题目未做完，确定交卷");
                builder.setTitle("退出");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                               /* dialog.dismiss();
                                finish();*/
                        String ids = ExamUtils.getExamAnswer(list);

                        if (!MyCheckUtils.isEmpty(ids)) {
                            //MyLogUtils.e(TAG,"SIZE:"+list.size());
                            if (flag_zhineng) {
                                bean_history.setFlag_cl(false);

                                if (list.size() > 50) {
                                    mPresenter.setSolutionZhiNeng(ExamActivity.this, bean_user.getUid(), list);
                                } else
                                    mPresenter.setSolutionZhiNeng(ExamActivity.this, bean_user.getUid(), ids);
                            } else {
                                if (list.size() > 50) {
                                    mPresenter.setSolutionList(ExamActivity.this, bean_user.getUid(), list);
                                } else
                                    mPresenter.setSolution(ExamActivity.this, bean_user.getUid(), ids);
                            }

                        } else {
                            showLongToast("答题卡提交失败");
                        }
                        dialog.dismiss();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
                builder.create().show();
            } else if (count_no == 0) {//全部答过
                String ids = ExamUtils.getExamAnswer(list);
                if (!MyCheckUtils.isEmpty(ids)) {
                   /* if(flag_zhineng)
                    {
                        bean_history.setFlag_cl(false);

                        mPresenter.setSolutionZhiNeng(ExamActivity.this,bean_user.getUid(),ids);
                    }
                    else
                    {
                        mPresenter.setSolution(ExamActivity.this,bean_user.getUid(),ids);
                    }*/
                    if (flag_zhineng) {
                        bean_history.setFlag_cl(false);

                        if (list.size() > 50) {
                            mPresenter.setSolutionZhiNeng(ExamActivity.this, bean_user.getUid(), list);
                        } else
                            mPresenter.setSolutionZhiNeng(ExamActivity.this, bean_user.getUid(), ids);
                    } else {
                        if (list.size() > 50) {
                            mPresenter.setSolutionList(ExamActivity.this, bean_user.getUid(), list);
                        } else
                            mPresenter.setSolution(ExamActivity.this, bean_user.getUid(), ids);
                    }

                } else {
                    showLongToast("答题卡提交失败");
                }
            }
        }


    }

    //View main_view,View view_option, ExamOptionBean bean_option , ExamBean bean_exam,
    @Override
    public void ExamOptionClick(int postion_exam, int postions
    ) {
        MyLogUtils.e(TAG, postion_exam + "_" + postions + "点击答案");

        ExamBean bean_exam = mlist_exam.get(postion_exam);
        List<ExamOptionBean> list_option = bean_exam.getList_TitleList();
        ExamOptionBean bean_option = list_option.get(postions);
        String now_date = MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1);
        String type = bean_exam.getQType();
        if (type.equals(ExamType.TYPE_DXT)
                || type.equals(ExamType.TYPE_XLDXT)
                || type.equals(ExamType.TYPE_ZLDXT)) {
            if (bean_exam.getAnswer().equals(bean_option.getSingle())) {//答案已经确定，则删除
                holderSolution.deleteSolution(postion_exam, bean_exam.getID());
            } else {//答案 没有确定，则加入


                MyLogUtils.e(TAG, "BETWEEN:" + now_date + " ———— " + current_date);
                long between = MyDateUtil.getBetween(now_date, current_date, MyDateUtil.DATE_FORMAT_1,
                        MyDateUtil.calc_s);
                current_date = now_date;
                MyLogUtils.e(TAG, "BETWEEN:" + between);
                holderSolution.setDXSolution(
                        postion_exam, bean_exam.getID(),
                        bean_exam.getScore(), between + "", bean_option.getSingle(), bean_exam.getSolution());

            }
            if (text_exam_next.getText().toString().equals("提交答案")) {
                holderSolution.getDialog_solution().show();
                return;
            }
            int index2 = viewpager_exam.getCurrentItem();
            MyLogUtils.e(TAG, "下一题" + index2 + "  " + (mlist_exam.size() - 1));

            if (index2 == mlist_exam.size() - 2) {
                MyLogUtils.e(TAG, "下一题 提交答案" + (mlist_exam.size() - 1));
                text_exam_next.setText("提交答案");
            } else {

                text_exam_next.setText("下一题");

            }
            index2++;
            MyLogUtils.e(TAG, "index2:" + index2);
            Message msg = myHandler.obtainMessage();
            msg.what = 0x10000;
            msg.obj = index2;
            myHandler.sendMessage(msg);
            //   viewpager_exam.setCurrentItem(index2);

        } else if (type.equals(ExamType.TYPE_DUOXT)
                || type.equals(ExamType.TYPE_XLDUOXT)
                || type.equals(ExamType.TYPE_ZLDUOXT)) {
            if (bean_exam.getAnswer().contains(bean_option.getSingle())) {
                holderSolution.setDuoXSolution(false, postion_exam, bean_exam.getID(),
                        bean_exam.getScore(), "0", bean_option.getSingle(), bean_exam.getSolution());

            } else {
                long between = MyDateUtil.getBetween(now_date, current_date, MyDateUtil.DATE_FORMAT_1, MyDateUtil.calc_s);

                MyLogUtils.e(TAG, "BETWEEN:" + between);
                current_date = now_date;
                holderSolution.setDuoXSolution(true, postion_exam, bean_exam.getID(),
                        bean_exam.getScore(), between + "", bean_option.getSingle(), bean_exam.getSolution());

            }


        }

    }


    Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x10000) {
                int n = (int) msg.obj;
                viewpager_exam.setCurrentItem(n);
            }
        }
    };


    @Override
    public void initListeners() {
        ThemeManager.setThemeMode(ThemeManager.ThemeMode.DAY);
        ThemeManager.registerThemeChangeListener(this);
        img_top_return.setOnClickListener(this);

        img_exam_cgz.setOnClickListener(this);

        img_exam_sc.setOnClickListener(this);

        img_exam_biaoji.setOnClickListener(this);

        img_exam_dtk.setOnClickListener(this);

        img_exam_gengduo.setOnClickListener(this);

        text_exam_last.setOnClickListener(this);

        text_exam_next.setOnClickListener(this);

        viewpager_exam.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                text_exam_centent.setText((position + 1) + "/" + mlist_exam.size());
                ExamBean bean = mlist_exam.get(position);

                String id = bean.getID();
                if (MyCheckUtils.isEmpty(bean.getTitleID()))
                    id = bean.getTitleID();
                getBiaoji(bean);


                String type = Constant.DataType.TYPE_SHITI;
                if (flag_zhineng) {
                    type = Constant.DataType.TYPE_ZHINENG;
                }
                boolean flag = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), id, type);
                if (flag) {
                    // img_exam_sc.setImageResource(R.mipmap.zhibo_detail_icon_collect_press);
                    img_exam_sc.setImageDrawable(getResources()
                            .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.zhibo_detail_icon_collect_press)));
                } else
                    img_exam_sc.setImageDrawable(getResources()
                            .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.mipmap.shiti_icon_collect)));

                if (position == mlist_exam.size() - 1) {
                    MyLogUtils.e(TAG, "下一题 提交答案" + (mlist_exam.size() - 1));
                    text_exam_next.setText("提交答案");
                } else {
                    text_exam_next.setText("下一题");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private String mlist_title;

    private ExamNodeBean bean_node;

    private ExamInfoBean bean_info;

    private boolean flag_oid = false;

    private boolean flag_zhineng = false;

    private boolean flag_main = false;

    private String examIds;

    private String exam_oid_id;

    private String title_name;

    private ExamHistoryBean bean_history;


    @Override
    public void initData() {
        mPresenter = new ExamPresenter(this);
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
        flag_main = getIntent().getBooleanExtra("flag_main", false);
        flag_mk = getIntent().getBooleanExtra("flag_mk", false);
        //  flag_begin_time = false;

        //检查标题
        if (obj_title_name != null && !MyCheckUtils.isEmpty(obj_title_name.toString())) {
            title_name = obj_title_name.toString();
            text_top_centent.setText(title_name);
            holderSolution = new HolderSolution(this, title_name, this);
        } else {
            MyLogUtils.e(TAG, "标题未传入？？");
            showLongToast("暂无试题");
            mContext.onLowMemory();
            finishs();
            return;
        }
        //检查id
        if (obj_exam_oid_id == null
                || MyCheckUtils.isEmpty(obj_exam_oid_id.toString())) {
            if (!flag_zhineng) {
                MyLogUtils.e(TAG, "试卷ID or 节点 ID 未传入？？");
                showLongToast("暂无试题");
                mContext.onLowMemory();
                finishs();
                return;
            } else {
                MyLogUtils.e(TAG, "智能 相关，无需强制 传入ID");
            }

        } else {
            exam_oid_id = obj_exam_oid_id.toString();
        }

        //检查 题目内容 ID
        if (obj_examIds == null
                || MyCheckUtils.isEmpty(obj_examIds.toString())) {

            MyLogUtils.e(TAG, "试卷 题目ID 未传入？？");
            showLongToast("暂无试题");
            mContext.onLowMemory();
            finishs();
            return;
        } else {
            examIds = obj_examIds.toString();
        }

        //检查本地 存储，是/否 存在本次练习？


        String local_id = "";
        if (exam_oid_id == null || MyCheckUtils.isEmpty(exam_oid_id)) {
            local_id = "智能";
            exam_oid_id = "智能";
        } else {
            local_id = exam_oid_id;
        }

        bean_history = ExamUtils.getLocalExamHistory(local_id + "_" + flag_oid + "_" + flag_zhineng, bean_user.getUid());

        //更多
        holderWindow = new HolderWindow(this, this, this);
        //分享
        holderWindow.setFenXiangListener(this);
        //纠错
        holderWindow.setJiuCuoListener(this);

        if (bean_history != null) {//存在本地题目，从本地拉取题目信息
            List<ExamBean> list_exam = ExamUtils.getLocalExamList(local_id + "_" + flag_oid + "_" + flag_zhineng, bean_user.getUid());
            if (list_exam != null && list_exam.size() > 0)
                setExamList(list_exam);
            else {//本地库 出现题目/答题卡不全 或者数据库出错的情况下，请求服务器 下载
                if (flag_zhineng) {//智能的取题目
                    mPresenter.getRobotExamList(this, examIds, bean_user.getUid());
                } else {//非智能的取题目
                    mPresenter.getExamList(this, examIds, bean_user.getUid());
                }

            }
        } else {   //没有此项试卷的信息，从服务器请求题目
            //TODO 异步分批次请求操作。。。。。。。。。。。
            if (flag_zhineng) {//智能的取题目
                mPresenter.getRobotExamList(this, examIds, bean_user.getUid());
            } else {//非智能的取题目
                mPresenter.getExamList(this, examIds, bean_user.getUid());
            }

        }


    }

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
            case R.id.img_exam_sc:
                int postion = viewpager_exam.getCurrentItem();
                ExamBean bean = mlist_exam.get(postion);

                String type = Constant.DataType.TYPE_SHITI;
                if (flag_zhineng) {
                    type = Constant.DataType.TYPE_ZHINENG;
                }
                boolean flag = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), bean.getID(), type);
                if (flag) {
                    mPresenter.quxiaoshoucang(this, bean_user.getUid(), bean.getID(), type);
                } else {


                    mPresenter.shoucang(this, bean_user.getUid(),
                            type,
                            bean.getID(),
                            MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
                }
                break;
            case R.id.img_top_return:
                if (holderSolution != null && holderSolution.getMlist_exam() != null && holderSolution.getMlist_exam().size() > 0)
                    upSolution(holderSolution.getMlist_exam());
                else
                    finish();
                break;
            case R.id.img_exam_cgz:
                //草稿
                showDialog_cgz(Constant.DIALOG_SHOW);
                break;
            case R.id.img_cancas_cancle:
                showDialog_cgz(Constant.DIALOG_CANCAL);
                break;
            case R.id.img_cancas_delete:
                MyLogUtils.e(TAG, "笔画删除img_cancas_delete");
//                holder_canse.cancas_gesture.from
                holder_canse.gestureLib.removeEntry("size");
                break;
            case R.id.img_cancas_back:
                MyLogUtils.e(TAG, "笔画撤销 img_cancas_back");
                ArrayList<Gesture> list_gss = holder_canse.gestureLib.getGestures("size");
                holder_canse.cancas_gesture.getCurrentStroke().remove(list_gss.get(list_gss.size() - 1));

                break;
            case R.id.img_cancas_return:
                MyLogUtils.e(TAG, "笔画回退 img_cancas_return");
                break;
            case R.id.img_exam_dtk:
                try {
                    holderSolution.getDialog_solution().show();
                } catch (Exception e) {

                }
                break;
            case R.id.img_exam_gengduo:
                try {
                    holderWindow.getWindow_gengduo().showAsDropDown(img_exam_gengduo);
                } catch (Exception e) {

                }
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
                getBiaoji(mlist_exam.get(postion1));
                viewpager_exam.setCurrentItem(postion1);

                break;
            case R.id.text_exam_next:
                if (text_exam_next.getText().toString().equals("提交答案")) {
                    holderSolution.getDialog_solution().show();
                    return;
                }
                int index2 = viewpager_exam.getCurrentItem();
                try {
                    int postion2 = PagerUtils.next(index2, mlist_exam.size(), text_exam_next, true);
                    setScImage(postion2);
                    getBiaoji(mlist_exam.get(postion2));
                    viewpager_exam.setCurrentItem(postion2);
                } catch (Exception e) {
                    MyLogUtils.e(TAG, e.getMessage());
                    try {
                        index2++;
                        viewpager_exam.setCurrentItem(index2);
                    } catch (Exception es) {
                        MyLogUtils.e(TAG, e.getMessage());
                    }

                }


                break;
            case R.id.img_exam_biaoji:
                //点击 标记
                int index3 = viewpager_exam.getCurrentItem();
                ExamBean bean_biaoji = mlist_exam.get(index3);
                biaoji(bean_biaoji);
                holderSolution.biaoji(index3);
                break;
            default:
                break;
        }
    }

    public void getBiaoji(ExamBean bean_biaoji) {
        boolean flag_biaoji = ExamUtils.getExamBiaoji(bean_biaoji.getID(), flag_zhineng, flag_oid, bean_history, bean_user.getUid());
        if (flag_biaoji) {//已经标记了   取消标记
            img_exam_biaoji.setImageDrawable(getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.icon_biaozhu_selected)));

        } else {//没有标记
            img_exam_biaoji.setImageDrawable(getResources()
                    .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.icon_biaozhu)));
        }

    }

    public void biaoji(ExamBean bean_biaoji) {
        boolean flag_biaoji = ExamUtils.getExamBiaoji(bean_biaoji.getID(), flag_zhineng, flag_oid, bean_history, bean_user.getUid());
        if (flag_biaoji) {//已经标记了   取消标记
            ExamUtils.insertExamBiaoji(bean_biaoji, flag_zhineng, flag_oid, false, bean_history, bean_user.getUid());
            img_exam_biaoji.setImageResource(R.mipmap.icon_biaozhu);
            showLongToast("取消标记成功");
        } else {//没有标记
            ExamUtils.insertExamBiaoji(bean_biaoji, flag_zhineng, flag_oid, true, bean_history, bean_user.getUid());
            img_exam_biaoji.setImageResource(R.mipmap.icon_biaozhu_selected);
            showLongToast("标记题目成功");
        }
    }

    @Override
    public void setExamList(List<ExamBean> list) {
        if (list == null || list.size() == 0) {
            showLongToast("题目获取失败，请稍后再试");
            return;
        }

        if (bean_history == null) {//从服务器 down 下来的数据 进行本地保存
            bean_history = ExamUtils.insertExamHistory(bean_history, title_name,
                    exam_oid_id, flag_zhineng, flag_oid, flag_main, bean_user.getUid());
            ExamUtils.insertExamList(list, flag_zhineng, flag_oid, bean_history.get_id(), bean_user.getUid());
        }


        current_date = MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1);


        mlist_exam.addAll(list);

        holderSolution.setMlist_exam(mlist_exam);
        ExamBean bean_one = mlist_exam.get(0);
        String type = Constant.DataType.TYPE_SHITI;
        if (flag_zhineng) {
            type = Constant.DataType.TYPE_ZHINENG;
        }
        boolean flag = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), bean_one.getID(), type);
//        if (flag) {
//            // img_exam_sc.setImageResource(R.mipmap.zhibo_detail_icon_collect_press);
//            img_exam_sc.setImageDrawable(getResources()
//                    .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.zhibo_detail_icon_collect_press)));
//        } else
//            img_exam_sc.setImageDrawable(getResources()
//                    .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.shiti_icon_collect)));
        if (ExamShouCang.getLocalExamShouCang(this.bean_user.getUid(), bean_one.getID(), type)) {
            this.img_exam_sc.setImageDrawable(getResources().getDrawable(R.mipmap.img_shoucang_sel));
        } else {
            this.img_exam_sc.setImageDrawable(getResources().getDrawable(R.mipmap.img_shoucang));
        }

        getBiaoji(bean_one);
        // mlist_view = new ArrayList<>();
        adapter = new ExamAdapter(this, list, bean_history, this);
        //初始化 字体
        adapter.setSize(holderWindow.getmCurrent_TextSize());

        viewpager_exam.setOffscreenPageLimit(1);
        viewpager_exam.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        viewpager_exam.getParent().requestDisallowInterceptTouchEvent(false);
        text_exam_centent.setText(1 + "/" + mlist_exam.size());
        if (bean_history.getFlag_cl()) {//  如果 有 记录 就进行跳转
            viewpager_exam.setCurrentItem(bean_history.getSelected_exam());
        }

        if (mlist_exam.size() == 1) {
            text_exam_next.setText("提交答案");
        }
        //开始计时
        handler_time.postDelayed(runnable, 1000);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                MyLogUtils.e(TAG, "后退");
                if (holderSolution != null && holderSolution.getMlist_exam() != null && holderSolution.getMlist_exam().size() > 0)
                    upSolution(holderSolution.getMlist_exam());
                else
                    finish();
                // upSolution(holderSolution.getMlist_exam());
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }


    /**
     * 提交成功。。。。。。。。。。进入错题本
     */
    @Override
    public void setSolutionOK() {

        if (flag_mk) {
            ProgressUtils.stopProgressDlg();
            MessageDialog.Builder builder = new MessageDialog.Builder(this);
            builder.setTitle("你的试卷已提交成功");
            builder.setMessage("模考结束后银行易考才能综合考试情况给出评分及分析报告。请耐心等待！");
            builder.setPositiveButton("我知道了!", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    holderSolution.getDialog_solution().dismiss();
                    bean_history.setFlag_cl(false);
                    ExamUtils.insertExamHistory(bean_history, title_name, exam_oid_id, flag_zhineng, flag_oid, flag_main, bean_user.getUid());
                    ExamErrDetailActivity.startExamErrDetailActivity(mContext, exam_oid_id, title_name, examIds,
                            flag_zhineng, flag_oid, flag_main, true, flag_mk);
                    dialog.dismiss();
                }
            });

            builder.create().show();
        } else {
            showLongToast("提交成功");
            ProgressUtils.stopProgressDlg();
            holderSolution.getDialog_solution().dismiss();
            bean_history.setFlag_cl(false);
            ExamUtils.insertExamHistory(bean_history, title_name, exam_oid_id, flag_zhineng, flag_oid, flag_main, bean_user.getUid());
            ExamErrDetailActivity.startExamErrDetailActivity(this, exam_oid_id, title_name, examIds,
                    flag_zhineng, flag_oid, flag_main, true, flag_mk);

        }


    }

    private int n = 0;

    private int m = 0;

    @Override
    public void setSolutionOK(int max, int s) {
        m = max;
        n += s;
        if (m == n)
            setSolutionOK();
    }

    @Override
    public void setSolutionErr() {
        ProgressUtils.stopProgressDlg();
        showLongToast("提交失败");
    }

    @Override
    public void shoucangOK(List<FavoriteRecord> list, boolean ok) {
        if (list != null && list.size() > 0) {
            ExamShouCang.insertAllExamShouCang(bean_user.getUid(), list);
            int postion = viewpager_exam.getCurrentItem();

            ExamBean bean_exam = mlist_exam.get(postion);
            String id = bean_exam.getID();
            if (MyCheckUtils.isEmpty(id))
                id = bean_exam.getTitleID();

            String type = Constant.DataType.TYPE_SHITI;
            if (flag_zhineng) {
                type = Constant.DataType.TYPE_ZHINENG;
            }

            boolean bool = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), id, type);
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
    }

    public void setScImage(int postion) {
        if (mlist_exam == null || mlist_exam.size() == 0)
            return;
        if (postion > mlist_exam.size())
            return;

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
            this.img_exam_sc.setImageDrawable(getResources().getDrawable(R.mipmap.img_shoucang_sel));
        } else {
            this.img_exam_sc.setImageDrawable(getResources().getDrawable(R.mipmap.img_shoucang));
        }
//        if (flag2) {
//            // img_exam_sc.setImageResource(R.mipmap.zhibo_detail_icon_collect_press);
//            img_exam_sc.setImageDrawable(getResources()
//                    .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.zhibo_detail_icon_collect_press)));
//
//        } else
//            img_exam_sc.setImageDrawable(getResources()
//                    .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.shiti_icon_collect)));

    }

    @Override
    public void shoucangErr() {
        img_exam_sc.setImageDrawable(getResources()
                .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.img_shoucang)));

        showLongToast("收藏失败");
    }

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

            holder_canse.gestureLib = GestureLibraries.fromPrivateFile(this, "size");
//            holder_canse.gestureLib.load();

        }

    }

    // private List<Gesture> mlist = new ArrayList<>();
    int ns = 0;

    @Override
    public void onGesturingEnded(GestureOverlayView arg0) {//结束正在绘制手势
        MyLogUtils.e(TAG, "onGesturingEnded");
    /*    holder_canse.img_cancas_delete.setEnabled(true);
        holder_canse.img_cancas_back.setEnabled(true);
        holder_canse.img_cancas_return.setEnabled(true);
       // mlist.add(arg0.getGesture());
        n++;*/

/*
        holder_canse.gestureLib.addGesture("size", arg0.getGesture());
        MyLogUtils.e(TAG,holder_canse.gestureLib.getGestures("size").size()+"");
*/

    }

    @Override
    public void onGesturingStarted(GestureOverlayView arg0) {//正在绘制手势
        MyLogUtils.e(TAG, "onGesturingStarted");
    }

    @Override
    public void onGesturePerformed(GestureOverlayView arg0, Gesture arg1) {   //手势绘制完成
        MyLogUtils.e(TAG, "onGesturePerformed");
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
            view.setBackgroundColor(mContext.getResources().getColor(ThemeManager.getCurrentThemeRes(mContext,
                    R.color.color_exam_black)));
            HolderExam holder_exam = (HolderExam) view.getTag();

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

            for (int j = 0; holder_exam.getMlist_options() != null
                    && j < holder_exam.getMlist_options().size(); j++) {
                View view_option = holder_exam.getMlist_options().get(j);
                HolderOption holderOption = (HolderOption) view_option.getTag();


                view_option.setBackgroundColor(mContext.getResources()
                        .getColor(ThemeManager.getCurrentThemeRes(mContext, R.color.color_exam_back)));
                holderOption.ll_options.setBackground(mContext.getResources()
                        .getDrawable(ThemeManager.getCurrentThemeRes(mContext, R.drawable.drawable_exam_option)));
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
        //  img_top_right.setImageDrawable(getResources().getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.day_btn_history)));

        //操作
        ll_exam_caozuo.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_exam_caozuo)));

        img_exam_cgz.setImageDrawable(getResources().getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.img_timu_caozuo)));

        img_exam_dtk.setImageDrawable(getResources().getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.img_datika)));

        int current = viewpager_exam.getCurrentItem();


        String type = Constant.DataType.TYPE_SHITI;
        if (flag_zhineng) {
            type = Constant.DataType.TYPE_ZHINENG;
        }
        boolean flag = ExamShouCang.getLocalExamShouCang(bean_user.getUid(), mlist_exam.get(current).getID(), type);
        if (flag) {
            // img_exam_sc.setImageResource(R.mipmap.zhibo_detail_icon_collect_press);
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(R.mipmap.img_shoucang_sel));
        } else
            img_exam_sc.setImageDrawable(getResources()
                    .getDrawable(R.mipmap.img_shoucang));


        getBiaoji(mlist_exam.get(current));
        //  img_exam_biaoji.setImageDrawable(getResources()
        //        .getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.icon_biaozhu)));


        img_exam_time.setTextColor(getResources().getColor(ThemeManager.getCurrentThemeRes(this, R.color.color_top_text)));
//        img_exam_gengduo.setImageDrawable(getResources().getDrawable(ThemeManager.getCurrentThemeRes(this, R.mipmap.day_shiti_icon_more)));
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
