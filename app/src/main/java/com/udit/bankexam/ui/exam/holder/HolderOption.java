package com.udit.bankexam.ui.exam.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.utils.FontUtils;
import com.udit.bankexam.utils.URLImageGetter;
import com.udit.frame.common.MyImageGetter.URLImageParser;
import com.udit.frame.common.texthtml.HtmlTextView;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.net.URL;

/**
 * Created by zb on 2017/5/11.
 */

public class HolderOption {

    private final String TAG = this.getClass().getSimpleName();

    public TextView text_options_index;

    public HtmlTextView text_options;

    public LinearLayout ll_options;

    private ExamOptionBean bean_options;

    private View mView_main;

    private Context mContext;

    private boolean isSelect = false;

    public HolderOption(ExamOptionBean bean_options, Context mContext) {
        this.bean_options = bean_options;
        this.mContext = mContext;
        initView();
    }

    public boolean getIsSelected() {
        return this.isSelect;
    }

    public void initView() {
        mView_main = LayoutInflater.from(mContext).inflate(R.layout.item_exam_option, null);
        ViewUtils.initHolderView(this, mView_main, R.id.class);
        text_options_index.setText(bean_options.getSingle());
        text_options.setHtmlFromString(bean_options.getSList(), false, false);
        // FontUtils.injectFont(mView_main);
        mView_main.setTag(this);
    }


    public void setIsSelected(boolean paramBoolean) {
        int i;
        this.isSelect = paramBoolean;
        TextView textView = this.text_options_index;
        Resources resources = this.mContext.getResources();
        if (paramBoolean) {
            i = R.drawable.shape_125_efefef_bg;
        } else {
            i = R.drawable.shape_125_stroke_558cf4_bg;
        }
        textView.setBackground(resources.getDrawable(i));
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
