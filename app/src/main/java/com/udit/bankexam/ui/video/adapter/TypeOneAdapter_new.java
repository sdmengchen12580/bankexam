package com.udit.bankexam.ui.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.CustomFiveAngleImg;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class TypeOneAdapter_new extends BaseAdapter {

    List<VideoTypeOneBean> mlist;
    private ViewGroup.LayoutParams vpViewG;
    private Context mContext;

    public TypeOneAdapter_new(List<VideoTypeOneBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(mContext, 105.0F));
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
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_typeone_new, null);
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.layout.setLayoutParams(this.vpViewG);
        VideoTypeOneBean bean = mlist.get(position);

        if (!MyCheckUtils.isEmpty(bean.getPicture()))
            ImageLoader.getInstance().displayImage(IHTTP.IP_ + "/" + bean.getPicture(), holder.img_picture, BaseApplication.list_options);
        else {
            holder.img_picture.setImageResource(R.mipmap.pic_default);
        }
        if (!MyCheckUtils.isEmpty(bean.getVType()))
            holder.text_typeone.setText(bean.getVType());

        holder.video_type_num.setText(String.format("章节：%s", bean.getType_num()));
        holder.video_num.setText(String.format("视频：%s", bean.getVideo_num()));

        return convertView;
    }

    private class Holder {
        CustomFiveAngleImg img_picture;

        RelativeLayout layout;

        TextView text_typeone;

        TextView video_num;

        TextView video_type_num;
    }
}
