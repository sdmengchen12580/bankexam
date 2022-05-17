package com.udit.bankexam.ui.zhibo;


import com.udit.bankexam.R;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.constant.Constant;

import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.presenter.zhibo.KeChengDetailPresenter;
import com.udit.bankexam.ui.pay.PaySelectActivity;
import com.udit.bankexam.ui.video.VideoInfoActivity;
import com.udit.bankexam.ui.zhibo.fragment.KeChengBiaoFragment;
import com.udit.bankexam.ui.zhibo.fragment.KeChengJieShaoFragment;
import com.udit.bankexam.ui.zhibo.fragment.TeacherFragment;

import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.view.zhibo.KeChengDetailView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
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

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class KeChengDetailActivity extends BaseActivity<KeChengDetailPresenter> implements KeChengDetailView.View, View.OnClickListener, ShareBoardlistener, UMShareListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startKeChengDetailActivity(BaseActivity<?> mActivity, String id, String name, int result) {
        Intent intent = new Intent(mActivity, KeChengDetailActivity.class);
        intent.putExtra("zhibo_id", id);
        intent.putExtra("zhibo_name", name);
        mActivity.startActivityForResult(intent, result);
    }


    public static void startKeChengDetailActivity(BaseActivity<?> mActivity, ZhiBoBean bean, int result) {
        Intent intent = new Intent(mActivity, KeChengDetailActivity.class);
        intent.putExtra("zhibo", bean);
        mActivity.startActivityForResult(intent, result);
    }

    public static void startKeChengDetailActivity(BaseActivity<?> mActivity, String eid, String type) {
        Intent intent = new Intent(mActivity, KeChengDetailActivity.class);
        intent.putExtra("EID", eid);
        intent.putExtra("type", type);
        mActivity.startActivity(intent);
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_kechengdetail);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private TextView text_top_right;

    private LinearLayout ll_top_zixun;
    //直播名称
    private TextView textview_zhibo_name;
    //时间
    private TextView textview_zhibo_time;
    //课时
    private TextView textview_zhibo_kecheng, textview_zhibo_tinshou, textview_zhibo_goumairenshu;
    //收藏
    private LinearLayout ll_zhibi_shoucang;
    //收藏 img
    private ImageView image_shoucang;
    //收藏 文字
    private TextView textview_shoucang;

    private TextView textview_zhibo_price;

    private ZhiBoBean bean_zhibo;

    private UserBean bean_user;

//    private RadioGroup radiogroup_kechengdetail;

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;

    private Fragment mContent;

    private Bundle mBundle_ZhiboInfo;

    private ImageView share_video_kecheng;

    // private DBUtils dbUtils;

    private TextView textview_goumai;

    private ShareAction shareAction;

    private SharedUtils shareUtils;
    private String share_ip;

    private TextView tv_center;
    private TextView tv_left;
    private TextView tv_right;
    private LinearLayout ll_zhifu_price;
    private LinearLayout ll_kefu;

    private void replaceFragment(Fragment fragment) {
        if (mContent == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmen_zhibo_info, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (mContent != fragment) {
            fragmentTransaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            if (!fragment.isAdded()) {
                // 先判断是否被add过
                fragmentTransaction.hide(mContent).add(R.id.fragmen_zhibo_info, fragment).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                fragmentTransaction.hide(mContent).show(fragment).commit(); // 隐藏当前的fragment，显示下一个
                mContent.onPause();
                fragment.onResume();
            }
        }
        mContent = fragment;
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("课程详情");
        text_top_right.setText("咨询");

        ll_top_zixun.setVisibility(View.GONE);
        share_video_kecheng.setVisibility(View.VISIBLE);

        ll_zhibi_shoucang.setVisibility(View.GONE);
    }


    private void setTextSelectEd(int paramInt) {
        tv_left.setVisibility((paramInt == 1) ? View.VISIBLE : View.GONE);
        tv_left.setSelected((paramInt == 1) ? true : false);
        TextView textView1 = (TextView) findViewById(R.id.radiobutton_jieshao);
        textView1.setTextColor(Color.parseColor((paramInt == 1) ? "#333333" : "#666666"));

        tv_center.setVisibility((paramInt == 2) ? View.VISIBLE : View.GONE);
        tv_center.setSelected((paramInt == 2) ? true : false);
        TextView textView2 = (TextView) findViewById(R.id.radiobutton_kechengbiao);
        textView2.setTextColor(Color.parseColor((paramInt == 2) ? "#333333" : "#666666"));

        tv_right.setVisibility((paramInt == 3) ? View.VISIBLE : View.GONE);
        tv_right.setSelected((paramInt == 3) ? true : false);
        TextView textView3 = (TextView) findViewById(R.id.radiobutton_teacher);
        textView3.setTextColor(Color.parseColor((paramInt == 3) ? "#333333" : "#666666"));
    }

    @Override
    public void initListeners() {
        this.img_top_return.setOnClickListener(this);
        this.ll_top_zixun.setOnClickListener(this);
        this.ll_zhibi_shoucang.setOnClickListener(this);
        this.textview_goumai.setOnClickListener(this);
        findViewById(R.id.radiobutton_jieshao).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                KeChengDetailActivity.this.setTextSelectEd(1);
                KeChengDetailActivity.this.replaceFragment(KeChengJieShaoFragment.getIntance());
            }
        });
        findViewById(R.id.radiobutton_kechengbiao).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                KeChengDetailActivity.this.setTextSelectEd(2);
                KeChengDetailActivity.this.replaceFragment(KeChengBiaoFragment.getIntance());
            }
        });
        findViewById(R.id.radiobutton_teacher).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                KeChengDetailActivity.this.setTextSelectEd(3);
                KeChengDetailActivity.this.replaceFragment(TeacherFragment.getIntance());
            }
        });
        this.share_video_kecheng.setOnClickListener(this);
        this.ll_kefu.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        setResult(RESULT_OK);

        finish();
        this.onLowMemory();
    }



    @Override
    public void initData() {
        bean_user = SaveUtils.getUser(this);

        shareAction = SharedUtils.getShareAction(this, this, this);
        mPresenter = new KeChengDetailPresenter(this);
        bean_zhibo = (ZhiBoBean) getIntent().getSerializableExtra("zhibo");
        if (bean_zhibo != null && bean_zhibo instanceof ZhiBoBean) {
            setDetail();
        } else {
            String eid = getIntent().getStringExtra("EID");
            if (!MyCheckUtils.isEmpty(eid)) {
                String type = getIntent().getStringExtra("type");
                mPresenter.getZhiboDetail(this, bean_user.getUid(), eid, type);
            } else {

                String zhibo_id = getIntent().getStringExtra("zhibo_id");
                String zhibo_name = getIntent().getStringExtra("zhibo_name");
                text_top_centent.setText(zhibo_name);
                mPresenter.getZhiboDetail(getActivity(), bean_user.getUid(), zhibo_id, "全员推送课程");
            }
        }
    }


    public void setDetail() {
        textview_zhibo_name.setText(bean_zhibo.getName());
        if (!MyCheckUtils.isEmpty(bean_zhibo.getBegDate())
                && !MyCheckUtils.isEmpty(bean_zhibo.getEndDate())) {
            String begin = MyDateUtil.chgFmt(bean_zhibo.getBegDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            String end = MyDateUtil.chgFmt(bean_zhibo.getEndDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            textview_zhibo_time.setText(begin + "-" + end);
        }

        int day = MyDateUtil.getDays(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_2), bean_zhibo.getEndSale(), MyDateUtil.DATE_FORMAT_2);
        textview_zhibo_tinshou.setText(day + "天");

        if (!MyCheckUtils.isEmpty(bean_zhibo.getPurchCount())) {
            textview_zhibo_goumairenshu.setText(bean_zhibo.getPurchCount());
        }


        if (!MyCheckUtils.isEmpty(bean_zhibo.getLCount())) {
            textview_zhibo_kecheng.setText("  " + bean_zhibo.getLCount() + "课时");
        }
        String price = Utils.doubleOutPut(bean_zhibo.getPrice(), 2);
        if (price.equals("0.00") || price.equals("0") || bean_zhibo.getIsGet().equals("是")) {
            if (bean_zhibo.getIsGet().equals("是")) {
                // mPresenter.pay(bean_user.getUid(),bean_zhibo.getLID(),bean_zhibo.getName());
                ll_zhifu_price.setVisibility(View.GONE);
                textview_zhibo_price.setText("0.00");
            } else {
                ll_zhifu_price.setVisibility(View.VISIBLE);
                textview_zhibo_price.setText("0.00");
            }
        } else {
            ll_zhifu_price.setVisibility(View.VISIBLE);
            textview_zhibo_price.setText("" + saveTwo(price) + "");
        }
        fragmentManager = getSupportFragmentManager();

        //dbUtils = new DBUtils(this);


//        radiogroup_kechengdetail.check(R.id.radiobutton_jieshao);

        mBundle_ZhiboInfo = new Bundle();
        mBundle_ZhiboInfo.putSerializable("zhibo_bean", bean_zhibo);


        KeChengJieShaoFragment.getIntance().setArguments(mBundle_ZhiboInfo);
        KeChengBiaoFragment.getIntance().setArguments(mBundle_ZhiboInfo);
        TeacherFragment.getIntance().setArguments(mBundle_ZhiboInfo);

        replaceFragment(KeChengJieShaoFragment.getIntance());

        mPresenter.isPay(bean_user.getUid(), bean_zhibo.getLID(), Constant.DataType.TYPE_ZHIBO);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // mPresenter.isPay(bean_user.getUid(),bean_zhibo.getLID(),Constant.DataType.TYPE_ZHIBO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);

        if (requestCode == Constant.RESULT.RESULT_ZHIBO_PAY
                && resultCode == RESULT_OK) {
            mPresenter.isPay(bean_user.getUid(), bean_zhibo.getLID(), Constant.DataType.TYPE_ZHIBO);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_video_kecheng:
                ShareBoardConfig config = new ShareBoardConfig();

                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                shareAction.open(config);
                break;
            case R.id.textview_goumai:
                String text = textview_goumai.getText().toString();
                if (text.equals("立即购买"))
                    PaySelectActivity.startPaySelectActivity(this, this.bean_zhibo.getLID(), "直播课", this.bean_zhibo.getName(), this.bean_zhibo.getPrice(), 6);
                else
                    mPresenter.pay(bean_user.getUid(), bean_zhibo.getLID(), bean_zhibo.getName());
                break;
            case R.id.img_top_return:
                setResult(RESULT_OK);
                finish();
                this.onLowMemory();
                break;
            case R.id.ll_top_zixun:
            case R.id.ll_kefu:
                //咨询
                if (Utils.isQQAvailable(this)) {
                    Utils.startQQ(this, Constant.QQ_ZIXUN);
                } else {
                    showLongToast("您还没有安装QQ");
                }
                break;
            case R.id.ll_zhibi_shoucang:
                //收藏
                String texts = textview_shoucang.getText().toString();
                if (texts.equals("收藏")) {
                    mPresenter.doPutFavorite(bean_user.getUid(), Constant.DataType.TYPE_ZHIBO, bean_zhibo.getLID(), MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
                } else {
                    mPresenter.doDelFavorite(bean_user.getUid(), Constant.DataType.TYPE_ZHIBO, bean_zhibo.getLID());
                }
                break;


        }
    }

    @Override
    public void setFavorite(List<FavoriteRecord> list) {
      /*  if(list!=null)
        {
            for(int i = 0 ;list!=null && i<list.size();i++)
            {
                FavoriteRecord record = list.get(i);
                record.setUID(bean_user.getUid());
            }
            image_shoucang.setImageResource(R.mipmap.zhibo_detail_icon_collect_press);
            textview_shoucang.setText("已收藏");
            dbUtils.deleteAllFavorite(bean_user.getUid(),Constant.DataType.TYPE_ZHIBO,bean_zhibo.getLID());
            dbUtils.insertFavoriteList(list);
        }
        else
        {
            showLongToast("收藏失败");
        }*/

    }

    @Override
    public void setDelFavorite(List<FavoriteRecord> list) {
        /*image_shoucang.setImageResource(R.mipmap.zhibo_detail_icon_collect);
        textview_shoucang.setText("收藏");
        if(list!=null)
        {
            dbUtils.deleteAllFavorite(bean_user.getUid(),Constant.DataType.TYPE_ZHIBO,bean_zhibo.getLID());
            dbUtils.insertFavoriteList(list);
        }
        else
        {
            dbUtils.deleteAllFavorite(bean_user.getUid(),Constant.DataType.TYPE_ZHIBO,bean_zhibo.getLID());
        }*/
    }

    @Override
    public void payOk() {
        ll_zhifu_price.setVisibility(View.GONE);
    }

    @Override
    public void payErr() {
        String price = Utils.doubleOutPut(bean_zhibo.getPrice(), 2);
        if (price.equals("0.00") || price.equals("0") || bean_zhibo.getIsGet().equals("是")) {
            if ((price.equals("0.00") || price.equals("0")) && bean_zhibo.getIsGet().equals("否")) {
                ll_zhifu_price.setVisibility(View.VISIBLE);
                textview_zhibo_price.setText("0");
                textview_goumai.setText("立即购买");
            } else {
                ll_zhifu_price.setVisibility(View.GONE);
                textview_zhibo_price.setText("0.00");
            }
        } else {
            ll_zhifu_price.setVisibility(View.VISIBLE);
            textview_zhibo_price.setText("" + saveTwo(price) + "");
        }
    }

    public String saveTwo(String paramString) {
        double d = (new BigDecimal(Double.parseDouble(paramString))).setScale(2, 4).doubleValue();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d);
        stringBuilder.append("");
        return stringBuilder.toString();
    }

    @Override
    public void setZhiboDetail(ZhiBoBean zhiBoBean) {
        if (zhiBoBean != null) {
            bean_zhibo = zhiBoBean;
            setDetail();
        }
    }

    @Override
    public void baomingOk() {
        showLongToast("报名成功,请到我的课程中查看");
        ll_zhifu_price.setVisibility(View.GONE);
    }

    @Override
    public void baomingErr() {
        showLongToast("报名失败");
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
        UMWXHandler handler = new UMWXHandler();
        String s = handler.getSDKVersion();
        MyLogUtils.e(TAG, "handle:" + s);
        UMWeb web = new UMWeb(IHTTP.ZHIBO_SHARE_IP + bean_zhibo.getLID());
        web.setThumb(new UMImage(getActivity(), R.drawable.shared));
        web.setDescription(bean_zhibo.getName());
        web.setTitle(Constant.Shared.title);
        shareAction.withMedia(web);
        shareAction.setPlatform(share_media).share();
    }
}
