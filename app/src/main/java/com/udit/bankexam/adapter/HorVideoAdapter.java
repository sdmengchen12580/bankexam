package com.udit.bankexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.ui.video.VideoTypeTwoActivityNew;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.view.CustomFiveAngleImg;
import com.udit.bankexam.view.ShadowContainer;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyCommonUtils;
import com.udit.frame.utils.Utils;

import java.util.List;

public class HorVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    private List<VideoTypeOneBean> mList;

    private ViewGroup.LayoutParams vpViewG;

    public HorVideoAdapter(Context paramContext, List<VideoTypeOneBean> paramList) {
        this.context = paramContext;
        this.mList = paramList;
        this.vpViewG = new RelativeLayout.LayoutParams((int) (0.94D * (MyCommonUtils.getscreen(paramContext)).widthPixels), Utils.dip2px(paramContext, 120.0F));
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        paramViewHolder.setIsRecyclable(false);
        if (paramViewHolder instanceof HasViewHolder) {
            final VideoTypeOneBean bean = (VideoTypeOneBean) this.mList.get(paramInt);
            paramViewHolder = (HasViewHolder) paramViewHolder;
            ((HasViewHolder) paramViewHolder).container.setLayoutParams(this.vpViewG);
            if (!MyCheckUtils.isEmpty(bean.getPicture())) {
                ImageLoader imageLoader = ImageLoader.getInstance();
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("http://yk.yinhangzhaopin.com/");
                stringBuilder1.append(bean.getPicture());
                imageLoader.displayImage(stringBuilder1.toString(), ((HasViewHolder) paramViewHolder).img_video, BaseApplication.list_options);
            } else {
                ((HasViewHolder) paramViewHolder).img_video.setImageResource(R.mipmap.pic_default);
            }
            if (!MyCheckUtils.isEmpty(bean.getVType()))
                ((HasViewHolder) paramViewHolder).tv_video_name.setText(bean.getVType());
            TextView textView = ((HasViewHolder) paramViewHolder).tv_video_info;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("章节：");
            stringBuilder.append(bean.getType_num());
            stringBuilder.append("       视频：");
            stringBuilder.append(bean.getVideo_num());
            textView.setText(stringBuilder.toString());
            ((HasViewHolder) paramViewHolder).rl_play.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (FastClickUtils.isFastClick())
                        return;
                    VideoTypeTwoActivityNew.startVideoTypeTwoActivityNew((BaseActivity) HorVideoAdapter.this.context, bean.getVType());
                }
            });
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new HasViewHolder(LayoutInflater.from(this.context).inflate(R.layout.itemlayout_hor_video, null));
    }

    private class HasViewHolder extends RecyclerView.ViewHolder {
        public ShadowContainer container;

        private CustomFiveAngleImg img_video;

        public View item;

        private RelativeLayout rl_play;

        private TextView tv_video_info;

        private TextView tv_video_name;

        public HasViewHolder(View param1View) {
            super(param1View);
            this.item = param1View;
            this.container = (ShadowContainer) param1View.findViewById(R.id.container);
            this.tv_video_name = (TextView) param1View.findViewById(R.id.tv_video_name);
            this.img_video = (CustomFiveAngleImg) param1View.findViewById(R.id.img_video);
            this.tv_video_info = (TextView) param1View.findViewById(R.id.tv_video_info);
            this.rl_play = (RelativeLayout) param1View.findViewById(R.id.rl_play);
        }
    }
}
