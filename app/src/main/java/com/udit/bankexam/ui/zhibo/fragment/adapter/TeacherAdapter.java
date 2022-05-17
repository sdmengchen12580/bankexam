package com.udit.bankexam.ui.zhibo.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.TeacherBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.frame.common.circleImageView.CircleImageView;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

import java.util.List;

/**
 * Created by zb on 2017/5/3.
 */

public class TeacherAdapter extends BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();

    private List<TeacherBean> mlist;

    private Context mContext;

    public TeacherAdapter(List<TeacherBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = null;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_teacher,null);
            holder = new Holder();
            ViewUtils.initHolderView(holder,convertView,R.id.class);
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
        TeacherBean bean = mlist.get(position);
        if(!MyCheckUtils.isEmpty(bean.getTecheAFile()))
        {
            ImageLoader.getInstance().displayImage(IHTTP.IP+"/"+bean.getTecheAFile(),holder.image_teacher_pic,MyApplication.user_options);
        }
        else
        {
            holder.image_teacher_pic.setImageResource(R.drawable.p_pic_user);
        }

        if(!MyCheckUtils.isEmpty(bean.getTecheName()))
        {
            holder.textview_teacher_name.setText(bean.getTecheName());
        }

        if(!MyCheckUtils.isEmpty(bean.getResume()))
        {
            String content = bean.getResume();
            content = content.replaceAll(Constant.IMAGE.IMG_OLD_BEGIN, Constant.IMAGE.IMG_NEW_BEGIN);

            WebUtil.WebInit(holder.webview_teacher_resume);
            MyLogUtils.e(TAG, content);
            holder.webview_teacher_resume.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        }


        return convertView;
    }

    private class Holder
    {
        CircleImageView image_teacher_pic;

        TextView textview_teacher_name;

        WebView webview_teacher_resume;
    }
}
