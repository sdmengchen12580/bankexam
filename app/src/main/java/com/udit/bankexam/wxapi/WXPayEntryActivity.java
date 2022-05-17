package com.udit.bankexam.wxapi;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.udit.bankexam.MyApplication;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.pay.WeixinPayPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.view.pay.weixinPlayView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyDataUtils;
import com.udit.frame.utils.MyLogUtils;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXPayEntryActivity extends BaseActivity<WeixinPayPresenter> implements IWXAPIEventHandler, weixinPlayView.View {
	
	private static final String TAG = WXPayEntryActivity.class.getSimpleName();
	private IWXAPI iwxapi;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mPresenter = new WeixinPayPresenter(this);
		iwxapi = WXAPIFactory.createWXAPI(this, Constant.PAY.PAY_WEIXIN_APPID);

		iwxapi.registerApp(Constant.PAY.PAY_WEIXIN_APPID);
		iwxapi.handleIntent(getIntent(), this);
	}

	@Override
	public void setContentView() {

	}

	@Override
	public void initViews(Bundle bundle) {

	}

	@Override
	public void initListeners() {

	}

	@Override
	public void initData() {

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		iwxapi.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq baseReq) {
		MyLogUtils.e(TAG, "onReq, errCode = " + baseReq.getType());

	}

	@Override
	public void onResp(BaseResp resp) {
		MyLogUtils.e(TAG, "onPayFinish, errCode = " + resp.errCode);

		if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX)
		{
			if(resp.errStr!=null)
			{
				MyLogUtils.e(TAG,"错误:"+resp.errStr);
			}
			else
			{
				MyLogUtils.e(TAG,"无错误打印");
			}
			switch (resp.errCode)
			{
				case 0:

					UserBean bean_user = SaveUtils.getUser(this);
					Object obj_PlayId =  MyDataUtils.getData(getApplicationContext(),"payinfo","PlayId",String.class);
					Object obj_weixinLink =  MyDataUtils.getData(getApplicationContext(),"payinfo","weixinLink",String.class);
					if(obj_PlayId!=null && obj_weixinLink!=null)
					{
						mPresenter.zhifuOk(obj_PlayId.toString(),bean_user.getUid(),obj_weixinLink.toString());
					}
					else{
						MyLogUtils.e(TAG,"支付失败，原有的支付记录 不存在了");
					}
					break;
				case -2:
					Toast.makeText(this,"支付取消",Toast.LENGTH_LONG).show();
					finish();
					break;
				case -1:
					Toast.makeText(this,"支付失败",Toast.LENGTH_LONG).show();
					finish();
					break;
				default:
					Toast.makeText(this,"支付错误",Toast.LENGTH_LONG).show();
					finish();
					break;
			}



		}
		else
		{
			finish();
		}

	}

	@Override
	public void zhifuOk() {
		Toast.makeText(this,"支付成功",Toast.LENGTH_LONG).show();
		MyDataUtils.deleteData(getApplicationContext(),"payinfo");
		finish();
	}

	@Override
	public void zhifuErr() {
		Toast.makeText(this,"支付失败",Toast.LENGTH_LONG).show();
		MyDataUtils.deleteData(getApplicationContext(),"payinfo");
	}
}