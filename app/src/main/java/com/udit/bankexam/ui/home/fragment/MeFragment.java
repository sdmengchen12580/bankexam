package com.udit.bankexam.ui.home.fragment;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.home.fragment.MePresenter;
import com.udit.bankexam.ui.exam_collection.ExamCollectionActivity;
import com.udit.bankexam.ui.exam_data_history.ExamDataHistoryActivity;
import com.udit.bankexam.ui.exam_data_history.ExamHomeHistoryActivity;
import com.udit.bankexam.ui.exam_err.ExamErrActivity;
import com.udit.bankexam.ui.exam_notebook.ExamNoteBookActivity;
import com.udit.bankexam.ui.exam_report_data.ExamReportDataActvity;
import com.udit.bankexam.ui.exam_robot.ExamRobotActivity;
import com.udit.bankexam.ui.exam_robot_pract.ExamRobotPractActivity;
import com.udit.bankexam.ui.newui.yaoqing.YaoQingActivity;
import com.udit.bankexam.ui.user.MessageActivity;
import com.udit.bankexam.ui.user.UserInfoActivity;
import com.udit.bankexam.ui.user.shouhuoActivity;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.SwitchButton;
import com.udit.bankexam.view.home.fragment.MeView;
import com.udit.frame.common.circleImageView.CircleImageView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MeFragment extends BaseFragment<MePresenter> implements MeView.View, OnClickListener,
        ShareBoardlistener, UMShareListener {

    private final String TAG = this.getClass().getSimpleName();

    private static MeFragment MEFRAGMENT;

    public static MeFragment getIntance() {
        if (MEFRAGMENT == null) {
            MEFRAGMENT = new MeFragment();
        }
        return MEFRAGMENT;
    }

    private View mView;
    private RelativeLayout rl_my_info;
    private ImageView img_top_return;
    private TextView text_top_centent;
    private UserBean bean_user;
    private LinearLayout ll_me_shoucang,
            ll_me_kefu, ll_me_fenxiang, ll_me_biji, ll_me_cuotiben, ll_me_lishijilu, ll_me_shujubaogao;
    private ImageView img_my_msg;
    private ImageView img_my_setting;
    private CircleImageView img_user;
    private LinearLayout ll_getshop_address;
    private SwitchButton switch_button;
    private TextView tv_name;
    private TextView tv_phone;
    private ShareAction shareAction;
    private SharedUtils shareUtils;
    private LinearLayout rl_lianxilishi, rl_zhinengzujuan, rl_zhuangxianlianxi, rl_shujubaogao, rl_cuotiben, ll_me_yaoqing;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, null);
        return mView;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, mView, R.id.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {
        this.ll_me_yaoqing.setOnClickListener(this);
        this.img_my_setting.setOnClickListener(this);
        this.img_my_msg.setOnClickListener(this);
        this.ll_getshop_address.setOnClickListener(this);
        this.ll_me_shoucang.setOnClickListener(this);
        this.ll_me_kefu.setOnClickListener(this);
        this.ll_me_fenxiang.setOnClickListener(this);
        this.ll_me_cuotiben.setOnClickListener(this);
        this.ll_me_lishijilu.setOnClickListener(this);
        this.ll_me_shujubaogao.setOnClickListener(this);
        this.ll_me_biji.setOnClickListener(this);
        this.rl_lianxilishi.setOnClickListener(this);
        this.rl_zhinengzujuan.setOnClickListener(this);
        this.rl_zhuangxianlianxi.setOnClickListener(this);
        this.rl_shujubaogao.setOnClickListener(this);
        this.rl_cuotiben.setOnClickListener(this);
        this.switch_button.setCheakCallBack(new SwitchButton.CheakCallBack() {
            public void isChecked(boolean param1Boolean) {
                SaveUtils.saveNotify(MeFragment.this.getActivity(), param1Boolean);
            }
        }, this.flag_notifty);

        rl_my_info.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                Intent n = new Intent();
                n.setClass(MeFragment.this.getActivity(), UserInfoActivity.class);
                startActivity(n);
            }
        });
    }

    private boolean flag_notifty;

    @Override
    public void initData(Bundle savedInstanceState) {
        shareAction = SharedUtils.getShareAction((BaseActivity<?>) getActivity(), this, this);
        mPresenter = new MePresenter(this);
        bean_user = SaveUtils.getUser(getActivity());
//        text_me_phone.setText(bean_user.getMobile());
        flag_notifty = SaveUtils.getNotify(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_zhuangxianlianxi:
                ExamRobotPractActivity.startExamRobotPractActivity((BaseActivity) getActivity());
                break;
            case R.id.rl_shujubaogao:
                ExamReportDataActvity.startExamReportDataActvity((BaseActivity) getActivity());
                break;
            case R.id.rl_lianxilishi:
                ExamHomeHistoryActivity.startExamHomeHistoryActivity((BaseActivity) getActivity());
                break;
            case R.id.rl_cuotiben:
                ExamErrActivity.startExamErrActivity((BaseActivity) getActivity());
                break;
            case R.id.ll_me_shujubaogao:
                ExamReportDataActvity.startExamReportDataActvity((BaseActivity) getActivity());
                break;
            case R.id.ll_me_shoucang:
                ExamCollectionActivity.startExamCollectionActivity((BaseActivity) getActivity());
                break;
            case R.id.ll_me_lishijilu:
                ExamDataHistoryActivity.startExamDataHistoryActivity((BaseActivity) getActivity());
                break;
            case R.id.ll_me_kefu:
                //咨询
                if (Utils.isQQAvailable(getActivity())) {
                    Utils.startQQ(getActivity(), Constant.QQ_ZIXUN);
                } else {
                    showLongToast("您还没有安装QQ");
                }
                break;
            case R.id.ll_me_fenxiang:
                ShareBoardConfig config = new ShareBoardConfig();
                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                shareAction.open(config);
                break;
            case R.id.ll_me_cuotiben:
                ExamErrActivity.startExamErrActivity((BaseActivity) getActivity());
                break;
            case R.id.ll_me_biji:
                ExamNoteBookActivity.startExamNoteBookActivity((BaseActivity) getActivity());
                break;
            case R.id.ll_getshop_address:
                shouhuoActivity.startshouhuoActivity((BaseActivity) getActivity(), null);
                break;
            case R.id.img_my_setting:
                changeAct(null, com.udit.bankexam.ui.other.SettingActivity.class);
                break;
            case R.id.img_my_msg:
                MessageActivity.startMessageActivity((BaseActivity) getActivity());
                break;
            //邀请功能
            case R.id.ll_me_yaoqing:
                changeAct(null, YaoQingActivity.class);
                break;
            //智能组券
            case R.id.rl_zhinengzujuan:
                ExamRobotActivity.startExamRobotActivity((BaseActivity) getActivity());
                break;
            default:
                break;
        }
    }

    protected void changeAct(Bundle paramBundle, Class<?> paramClass) {
        Intent intent = new Intent();
        if (paramBundle != null)
            intent.putExtras(paramBundle);
        intent.setClass(getActivity(), paramClass);
        getActivity().startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
        MyLogUtils.e(TAG, "onClick:" + share_media.name());
        if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE
                || share_media == SHARE_MEDIA.WEIXIN) {//微信朋友圈
        }
        UMWXHandler handler = new UMWXHandler();
        String s = handler.getSDKVersion();
        MyLogUtils.e(TAG, "handle:" + s);
        UMWeb web = new UMWeb(Constant.Shared.URL);
        web.setThumb(new UMImage(getActivity(), R.drawable.shared));
        web.setDescription(Constant.Shared.content);
        web.setTitle(Constant.Shared.title);
        shareAction.withMedia(web);
        shareAction.setPlatform(share_media).share();
    }

    @Override
    public void onResume() {
        super.onResume();
        //姓名
        this.bean_user = SaveUtils.getUser(getActivity());
        if (this.bean_user.getPet() != null && !this.bean_user.getPet().equals(""))
            this.tv_name.setText(this.bean_user.getPet());
        //手机号
        if (this.bean_user.getMobile() != null && !this.bean_user.getMobile().equals(""))
            this.tv_phone.setText(this.bean_user.getMobile());
        //头像
        String str = (String) SpUtil.get(getContext(), "imgurl", "");
        if (!str.equals("")) {
            ImageLoader.getInstance().displayImage(str, this.img_user, BaseApplication.list_options);
            return;
        }
        this.img_user.setImageResource(R.mipmap.img_user);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        MyLogUtils.e(TAG, "onStart:" + share_media.name());
        //api = WXAPIFactory.createWXAPI(getActivity(), Constant.PAY.PAY_WEIXIN_APPID,false);

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        MyLogUtils.e(TAG, "SHARE_MEDIA");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        MyLogUtils.e(TAG, "onError：" + throwable.getMessage() + ":" + share_media.name());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        MyLogUtils.e(TAG, "onCancel");
    }
}
   