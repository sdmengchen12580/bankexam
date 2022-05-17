package com.udit.bankexam.ui.video.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class VideoNodeHolder
{
    // 上线/下线
    public View view_top_line, view_bottom_line;

    // 节点
    public ImageView img_node;

    // 节点名称
    public TextView text_node_name;

    //价格 外部
    public LinearLayout ll_price;

    //价格
    public TextView textview_total_price,textview_price_type;

    public FrameLayout frame_video_node;

    public View view_top_line_,view_bottom_line_;

}
