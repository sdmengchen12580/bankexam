package com.udit.bankexam.ui.exam_err.holder;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.utils.FontUtils;
import com.udit.bankexam.utils.URLImageGetter;
import com.udit.frame.common.texthtml.HtmlTextView;
import com.udit.frame.utils.ViewUtils;

/**
 * Created by zb on 2017/5/11.
 */

public class HolderOption {

    public   TextView text_options_index;

    public HtmlTextView text_options;

    public RelativeLayout rl_options;

    public LinearLayout ll_options;

    private ExamOptionBean bean_options;

    public ImageView img_option_jg;

    private View mView_main;

    private Context mContext;

    public HolderOption(ExamOptionBean bean_options, Context mContext) {
        this.bean_options = bean_options;
        this.mContext = mContext;

        initView();
    }

    public void initView()
    {
        mView_main = LayoutInflater.from(mContext).inflate(R.layout.item_exam_option_jx, null);
        ViewUtils.initHolderView(this, mView_main, R.id.class);
       // FontUtils.injectFont(mView_main);
        text_options_index.setText(bean_options.getSingle());
       // text_options.setText(Html.fromHtml(bean_options.getSList(),new URLImageGetter(bean_options.getSList(),mContext,text_options, MyApplication.user_options),null));
        text_options.setHtmlFromString(bean_options.getSList(),false,false);

        mView_main.setTag(this);
    }



    public ExamOptionBean getBean_options() {
        return bean_options;
    }

    public void setBean_options(ExamOptionBean bean_options) {
        this.bean_options = bean_options;
    }

    public View getmView_main() {
        return mView_main;
    }


}
