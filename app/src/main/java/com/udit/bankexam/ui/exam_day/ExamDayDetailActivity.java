package com.udit.bankexam.ui.exam_day;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_day.ExamDayDetailPresenter;
import com.udit.bankexam.ui.pay.PaySelectActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_day.ExamDayDetailView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamDayDetailActivity extends BaseActivity<ExamDayDetailPresenter> implements ExamDayDetailView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private ExamInfoBean bean;

    public static void startExamDayDetailActivity(BaseActivity<?> mActivity, ExamInfoBean bean)
    {
        Intent intent = new Intent(mActivity,ExamDayDetailActivity.class);
        intent.putExtra("examinfo",bean);
        mActivity.startActivity(intent);

    }

    public static void startExamDayDetailActivity(BaseActivity<?> mActivity, ExamInfoBean bean,int result)
    {
        Intent intent = new Intent(mActivity,ExamDayDetailActivity.class);
        intent.putExtra("examinfo",bean);
        mActivity.startActivityForResult(intent,result);

    }

    public static void startExamDayDetailActivity(BaseActivity<?> mActivity, ExamInfoBean bean,String type)
    {
        Intent intent = new Intent(mActivity,ExamDayDetailActivity.class);
        intent.putExtra("examinfo",bean);
        intent.putExtra("examtype",type);
        mActivity.startActivity(intent);

    }

    public static void startExamDayDetailActivity(BaseActivity<?> mActivity, ExamInfoBean bean,String type,int result)
    {
        Intent intent = new Intent(mActivity,ExamDayDetailActivity.class);
        intent.putExtra("examinfo",bean);
        intent.putExtra("examtype",type);

        mActivity.startActivityForResult(intent,result);

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_examdaydetail);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private TextView textview_examdetail_name;

    private TextView textview_examdetail_time;

    private WebView webview_examinfo;

    private TextView textview_zhibo_price;

    private TextView textview_goumai;

    private UserBean bean_user;

    private LinearLayout ll_zhifu_price;

    private String type;
    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
        text_top_centent.setText("试卷详情");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);


        textview_goumai.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);

        if(requestCode== Constant.RESULT.RESULT_EXAM_PAY
                && resultCode==RESULT_OK)
        {

            mPresenter.isPay(bean_user.getUid(),bean.getID(),Constant.DataType.TYPE_SHIJUAN);
        }

    }

    @Override
    public void initData() {
        mPresenter = new ExamDayDetailPresenter(this);

        bean_user = SaveUtils.getUser(this);

        bean = (ExamInfoBean) getIntent().getSerializableExtra("examinfo");
        type = getIntent().getStringExtra("examtype");
        textview_examdetail_name.setText(bean.getName());
        if (!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate())) {
            String begin = MyDateUtil.chgFmt(bean.getBegDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            String end = MyDateUtil.chgFmt(bean.getEndDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            textview_examdetail_time.setText(begin + "-" + end);
        }


        //AllScreen
        WebUtil.WebInit(webview_examinfo);
        String content = bean.getAllScreen();
        content = content.replaceAll(Constant.IMAGE.IMG_OLD_BEGIN, Constant.IMAGE.IMG_NEW_BEGIN);

        WebUtil.WebInit(webview_examinfo);
        MyLogUtils.e(TAG, content);
        webview_examinfo.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);


        String price = Utils.doubleOutPut(bean.getPrice(),2);
        if (price.equals("0.00") || price.equals("0")
                || bean.getIsGet().equals("是")) {
            /*if(price.equals("0"))
            {
                mPresenter.pay(bean_user.getUid(),bean_zhibo.getLID(),bean_zhibo.getName());
            }*/
            ll_zhifu_price.setVisibility(View.GONE);
            textview_zhibo_price.setText("0.00");
        }
        else
        {
            ll_zhifu_price.setVisibility(View.VISIBLE);
            textview_zhibo_price.setText("" +price+ "");
        }
        /*if(!MyCheckUtils.isEmpty(bean.getPrice()))
        {
            String price =  Utils.doubleOutPut(bean.getPrice(),2);
            textview_zhibo_price.setText(price);
        }
        else
        {
            textview_zhibo_price.setText("0");
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_top_return:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.textview_goumai:
                String price = Utils.doubleOutPut(bean.getPrice(),2);
                MyLogUtils.e(TAG,"PRICE："+price);
                if("0".equals(price))
                {//不需要 进入价格选择
                    mPresenter.setPay(bean_user.getUid(),Constant.DataType.TYPE_SHIJUAN,bean.getID());
                }
                else
                {
                    PaySelectActivity.startPaySelectActivity(
                            this,bean.getID(),Constant.DataType.TYPE_SHIJUAN,bean.getName(),bean.getPrice(),Constant.RESULT.RESULT_EXAM_PAY);
                }
                /*PaySelectActivity.startPaySelectActivity(
                        this,bean.getLID(),Constant.DataType.TYPE_ZHIBO,bean_zhibo.getName(),bean_zhibo.getPrice());
*/
                break;
        }
    }

    @Override
    public void setPayOk() {
        showLongToast("购买成功");
        //进入到试卷
    }

    @Override
    public void setPayErr() {
        showLongToast("购买失败");
    }

    @Override
    public void payOk() {
        ll_zhifu_price.setVisibility(View.GONE);
    }

    @Override
    public void payErr() {
        String price = Utils.doubleOutPut(bean.getPrice(),2);
        if (price.equals("0.00") || price.equals("0")
                || bean.getIsGet().equals("是")) {

            ll_zhifu_price.setVisibility(View.GONE);
            textview_zhibo_price.setText("0.00");
        }
        else
        {
            ll_zhifu_price.setVisibility(View.VISIBLE);
            textview_zhibo_price.setText("" +price+ "");
        }

    }
}
