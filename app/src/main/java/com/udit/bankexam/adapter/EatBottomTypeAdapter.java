package com.udit.bankexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.TypeQuesBean.DataBean.ResponseBean.RowsBean;
import com.udit.bankexam.utils.DensityUtil;
import java.util.List;

public class EatBottomTypeAdapter extends BaseAdapter {

    private List<RowsBean> mList;
    private Context mContext;
    private ViewGroup.LayoutParams vp;
    private int selectpo = 0;

    public EatBottomTypeAdapter(Context context, List<RowsBean> list) {
        this.mContext = context;
        this.mList = list;
        vp = new ViewGroup.LayoutParams(DensityUtil.dip2px(mContext, 85F), DensityUtil.dip2px(mContext, 50F));
    }

    public void updatePositon(int index) {
        selectpo = index;
        notifyDataSetChanged();
    }

    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    public Object getItem(int position) {
        return mList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_eat_bottom, null);
            holder = new ViewHolder();
            holder.tv_line = (TextView) convertView.findViewById(R.id.tv_line);
            holder.item = (RelativeLayout) convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item.setLayoutParams(vp);
        holder.tv_line.setBackground(mContext.getResources().getDrawable(
                selectpo == position ? R.drawable.shape_15_corner_558cf4_bg : R.drawable.shape_15_stroke_999999
        ));
        holder.tv_line.setTextColor(mContext.getResources().getColor(
                selectpo == position ? R.color.white : R.color.color_666
        ));
        holder.tv_line.setText(mList.get(position).getQuestionCatCodeName());
        return convertView;
    }

    /**
     * 每个应用显示的内容，包括图标和名称
     *
     * @author Amity
     */
    class ViewHolder {
        TextView tv_line;
        RelativeLayout item;
    }
}
