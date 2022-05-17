package com.udit.bankexam.ui.pay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.udit.bankexam.MainActivity;
import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.AddressBean;
import com.udit.bankexam.bean.PayInfo;
import com.udit.bankexam.bean.PayWeiXinInfo;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.WeixinPayInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.pay.PayPresenter;
import com.udit.bankexam.ui.user.shouhuoActivity;
import com.udit.bankexam.utils.AuthResult;
import com.udit.bankexam.utils.MD5;
import com.udit.bankexam.utils.MD5Util;
import com.udit.bankexam.utils.PayResult;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.pay.PaySelecteView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDataUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by zb on 2017/5/4.
 */

public class PaySelectActivity extends BaseActivity<PayPresenter> implements PaySelecteView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startPaySelectActivity(BaseActivity<?> mActivity, String id, String type,
                                              String name, String price) {
        Intent intent = new Intent(mActivity, PaySelectActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("name", name);
        intent.putExtra("price", price);
        mActivity.startActivity(intent);
    }

    public static void startPaySelectActivity(BaseActivity<?> mActivity, String id, String type,
                                              String name, String price, int result_pay) {
        Intent intent = new Intent(mActivity, PaySelectActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("name", name);
        intent.putExtra("price", price);
        mActivity.startActivityForResult(intent, result_pay);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_pay_selected);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("订单确认");
        text_new_shouhuo.setVisibility(View.VISIBLE);
        ll_shouhuo.setVisibility(View.GONE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.doGetAddr(this,bean_user.getUid());
    }

    private String id;
    private String type;
    private String name;
    private String price;
    private String type_pay = "";

    private ImageView img_top_return;

    private TextView text_top_centent;

    //商品名称，价格
    private TextView textview_pay_sp_name, textview_pay_sp_price;

    private LinearLayout ll_zhifubao, ll_weixin;

    private RadioButton radiobutton_pay_zhifubao, radiobutton_pay_weixin;
    //总价
    private TextView textview_total_price;
    //购买
    private TextView textview_goumai;

    private HashMap<RadioButton, Boolean> map_states = new HashMap<>();

    private UserBean bean_user;

    private PayInfo minfo;

    private WeixinPayInfo info_weixin;

    private RadioGroup radiogroup_pay;


    private TextView text_new_shouhuo;


    private LinearLayout ll_shouhuo;

    private TextView text_address,text_phone,text_address_username;

    private LinearLayout ll_kefu;

    @Override
    public void initData() {

        bean_user = SaveUtils.getUser(this);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");

        mPresenter = new PayPresenter(this);

        textview_pay_sp_name.setText(name);
        price = Utils.doubleOutPut(price, 2);
        textview_pay_sp_price.setText(price);

        textview_total_price.setText(price);
        radiobutton_pay_zhifubao.setEnabled(false);
        radiobutton_pay_weixin.setEnabled(false);
        radiogroup_pay.check(R.id.radiobutton_pay_zhifubao);

        mPresenter.doGetAddr(this,bean_user.getUid());

        //map_states.put(radiobutton_pay_zhifubao, true);
        //map_states.put(radiobutton_pay_weixin, false);


       // checkRadioButton();
    }

    private void checkRadioButton() {
        for (RadioButton radio : map_states.keySet()) {
            if (map_states.get(radio)) {
                radio.setChecked(true);
            } else
                radio.setChecked(false);
        }

    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        textview_goumai.setOnClickListener(this);

        ll_zhifubao.setOnClickListener(this);

        ll_weixin.setOnClickListener(this);


        ll_shouhuo.setOnClickListener(this);

        text_new_shouhuo.setOnClickListener(this);

        ll_kefu.setOnClickListener(this);
    }
    private AddressBean bean_address;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_kefu:
                if (Utils.isQQAvailable(getActivity())) {
                    Utils.startQQ(getActivity(), Constant.QQ_ZIXUN);
                } else {
                    showLongToast("请先安装QQ");
                }
                break;
            case R.id.ll_shouhuo:
                shouhuoActivity.startshouhuoActivity(this,bean_address);
                break;
            case R.id.text_new_shouhuo:
                shouhuoActivity.startshouhuoActivity(this,null);
                break;
            case R.id.ll_zhifubao:
                radiogroup_pay.check(R.id.radiobutton_pay_zhifubao);
              //  map_states.put(radiobutton_pay_zhifubao, true);
              //  map_states.put(radiobutton_pay_weixin, false);
             //   checkRadioButton();
                break;
            case R.id.ll_weixin:
                radiogroup_pay.check(R.id.radiobutton_pay_weixin);
             //   map_states.put(radiobutton_pay_zhifubao, false);
              //  map_states.put(radiobutton_pay_weixin, true);
             //   checkRadioButton();
                break;
            case R.id.img_top_return:
                setResult(RESULT_OK);
                finish();
                this.onLowMemory();
                break;
            case R.id.textview_goumai:
             /*   if (map_states.get(radiobutton_pay_zhifubao)) {
                    type_pay = Constant.PAY.PAY_ZHIFUBAO;
                }
                if (map_states.get(radiobutton_pay_weixin)) {
                    type_pay = Constant.PAY.PAY_WEIXIN;
                }*/
               int rid = radiogroup_pay.getCheckedRadioButtonId();
                   if (rid==R.id.radiobutton_pay_zhifubao) {
                    type_pay = Constant.PAY.PAY_ZHIFUBAO;
                }
                if (rid==R.id.radiobutton_pay_weixin) {
                    type_pay = Constant.PAY.PAY_WEIXIN;
                }
                if (MyCheckUtils.isEmpty(type_pay)) {
                    showLongToast("请先选择支付类型");
                    return;
                } else {
                    mPresenter.zhifu(bean_user.getUid(), id, type, type_pay);
                }
                break;
        }
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //  showLongToast("支付成功");
                        mPresenter.zhifuOk(minfo.getId(), bean_user.getUid(), id);
                        //  Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showLongToast("支付失败");
                        // Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showLongToast("授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
                        // Toast.makeText(PayDemoActivity.this,
                        //       "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                        //        .show();
                    } else {
                        // 其他状态值则为授权失败
                        showLongToast("授权失败" + String.format("authCode:%s", authResult.getAuthCode()));
                        // Toast.makeText(PayDemoActivity.this,
                        //    "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    public void getZhifuSign(final PayInfo info) {
        if (type_pay == Constant.PAY.PAY_ZHIFUBAO && info != null) {
            info_weixin = null;
            minfo = info;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(PaySelectActivity.this);
                    Map<String, String> result = alipay.payV2(info.getPayinfo(), true);
                    MyLogUtils.i("msp", result.toString());

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            Thread payThread = new Thread(runnable);
            payThread.start();

        } else {
           // minfo = null;
            //info_weixin = null;
            showLongToast("支付信息生成异常");
        }


    }

    @Override
    public void getWeiXinSign(WeixinPayInfo info) {
        if (type_pay.equals(Constant.PAY.PAY_WEIXIN) && info != null) {// showLongToast("微信支付暂未实现");
            minfo = null;
            info_weixin = info;
            MyApplication.weixinLink = null;
            MyApplication.PlayId = null;
            MyDataUtils.deleteData(getApplicationContext(),"payinfo");

            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
            MyApplication.api = WXAPIFactory.createWXAPI(this, Constant.PAY.PAY_WEIXIN_APPID);
            if (WXPlay()) {
                MyApplication.api.registerApp(Constant.PAY.PAY_WEIXIN_APPID);
                try {
                    PayWeiXinInfo info_wx = info.getWxPayInfo();
                    MyLogUtils.e(TAG, info_wx.toString());
                    PayReq  req = new PayReq();
                    req.appId = info_wx.getAppid();
                    req.partnerId = info_wx.getPartnerid();
                    req.prepayId = info_wx.getPrepayid();
                    req.nonceStr = info_wx.getNoncestr();
                    req.timeStamp = info_wx.getTimestamp();
                    req.packageValue =info_wx.getPackageValue();
                    req.sign = info_wx.getSign();
                    MyApplication.PlayId =info.getId();
                    MyApplication.weixinLink = id;
                    Map<String,Object> map_ = new HashMap<>();
                    map_.put("PlayId",info.getId());
                    map_.put("weixinLink",id);
                    MyDataUtils.putData(this,"payinfo",map_);

                    MyApplication.api.sendReq(req);
                } catch (Exception e) {
                    MyLogUtils.e(TAG, e.getMessage());
                    minfo = null;
                    info_weixin = null;
                    MyApplication.weixinLink = null;
                    MyApplication.PlayId = null;
                    MyDataUtils.deleteData(getApplicationContext(),"payinfo");
                    showLongToast("支付信息生成异常");
                }

            }


        } else {
            minfo = null;
            info_weixin = null;
            MyApplication.weixinLink = null;
            MyApplication.PlayId = null;
            showLongToast("支付信息生成异常");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.isPay(bean_user.getUid(),id,type);

    }

    private boolean WXPlay() {

        try {
            if (!MyApplication.api.isWXAppInstalled()) {
                showLongToast("请先安装微信");
                return false;
            }
            if (!MyApplication.api.isWXAppSupportAPI()) {
                showLongToast("微信版本不支持支付");
                return false;
            }
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
            showLongToast("请安装或升级微信版本");
            return false;
        }

        return true;

    }

    @Override
    public void zhifuOk() {
     //   minfo = null;
        info_weixin = null;
        showLongToast("支付成功");
        setResult(RESULT_OK);
        finish();
        this.onLowMemory();
    }

    @Override
    public void zhifuErr() {
        minfo = null;
        info_weixin = null;
        showLongToast("支付失败");
    }

    @Override
    public void payOk() {
       // minfo = null;
        info_weixin = null;
        setResult(RESULT_OK);
        finish();
        this.onLowMemory();
    }

    @Override
    public void payErr() {
       // minfo = null;
        info_weixin = null;
       // showLongToast("支付失败");
    }

    @Override
    public void getAddr(AddressBean bean) {
        if(bean!=null)
        {
            bean_address = bean;
            if(!MyCheckUtils.isEmpty(bean.getName()))
            {
                text_address_username.setText(bean.getName());
            }
            else
                text_address_username.setText("");

            if(!MyCheckUtils.isEmpty(bean.getAddr()))
            {
                text_address.setText(bean.getAddr());
            }
            else
            {
                text_address.setText("");
            }

            if(!MyCheckUtils.isEmpty(bean.getTel()))
            {
                text_phone.setText(bean.getTel());
            }
            else
            {
                text_phone.setText("");
            }
            text_new_shouhuo.setVisibility(View.GONE);
            ll_shouhuo.setVisibility(View.VISIBLE);
        }
        else
        {
            text_new_shouhuo.setVisibility(View.VISIBLE);
            ll_shouhuo.setVisibility(View.GONE);
        }

    }
}
