package com.udit.bankexam.ui.home.fragment.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.MyWebActivity;
import com.udit.bankexam.ui.exam.ExamListActivity;
import com.udit.bankexam.ui.exam_mk.ExamMkDetailActivity;
import com.udit.bankexam.ui.home.fragment.VideoFragment;
import com.udit.bankexam.ui.home.fragment.VideoFragment_new;
import com.udit.bankexam.ui.video.VideoTypeTwoActivityNew;
import com.udit.bankexam.ui.zhibo.KeChengDetailActivity;
import com.udit.bankexam.view.MyWebView;
import com.udit.bankexam.view.RoundImageView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyCheckUtils;

import com.viewpagerindicator.IconPagerAdapter;

public class HomeBannerPagerAdapter extends PagerAdapter implements IconPagerAdapter {

    private Context mContext;

    private ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     * 图片相对路径
     */
    private List<AdvBean> mPaths;


    public HomeBannerPagerAdapter(Context context, List<AdvBean> paths) {
        mContext = context;
        mPaths = paths;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
     /*   View pageView = LayoutInflater.from(mContext).inflate(R.layout.home_loop_advert_viewpager_item, null);

        final ImageView adImageView = (ImageView)pageView.findViewById(R.id.iv_banner);
        adImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);*/
        RoundImageView adImageView = new RoundImageView(mContext);
        adImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        adImageView.setAdjustViewBounds(true);
//        adImageView.setLayoutParams(params_banner);
        if (mPaths.get(position).getImg().contains("upload")) {
            imageLoader.displayImage(IHTTP.IP + "/" + mPaths.get(position).getImg(), adImageView,
                    BaseApplication.banner_options);
        } else {
            imageLoader.displayImage("drawable://" + mPaths.get(position).getImg(), adImageView,
                    BaseApplication.banner_options);
        }


        //imageLoader.displayImage("drawable://" + mPaths.get(position).getImg(), adImageView, BaseApplication.banner_options);


        adImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != mPaths && null != mPaths.get(position)
                        && mPaths.get(position).getPath() != null
                        && !MyCheckUtils.isEmpty(mPaths.get(position).getPath())) {
                    String title = mPaths.get(position).getTitle();
                    String path = mPaths.get(position).getPath();
                    if (title.equals("全员推送试卷")) {
                        ExamListActivity.startExamListActivity((BaseActivity<?>) mContext, path, title);
                    } else if (title.equals("全员推送模考")) {
                        ExamMkDetailActivity.startExamMkActivity((BaseActivity<?>) mContext, path, title);
                    } else if (title.equals("全员推送课程")) {
                        KeChengDetailActivity.startKeChengDetailActivity((BaseActivity<?>) mContext, path, title);
                    } else if (title.equals("全员推送视频")) {
                        VideoTypeTwoActivityNew.startVideoTypeTwoActivityNewByID((BaseActivity<?>) mContext, path);
                    } else if (title.equals("全员推送")) {
                        //  MyWebActivity.startMyWebActivity(mContext,);
                    } else if (title.equals("关联模考")) {
                        ExamMkDetailActivity.startExamMkActivity((BaseActivity<?>) mContext, path, title);
                    } else if (title.equals("关联课程")) {
                        KeChengDetailActivity.startKeChengDetailActivity((BaseActivity<?>) mContext, path, title);
                    } else if (title.equals("关联试卷")) {
                        ExamListActivity.startExamListActivity((BaseActivity<?>) mContext, path, title);
                    } else if (title.equals("关联视频")) {
                        VideoTypeTwoActivityNew.startVideoTypeTwoActivityNewByID((BaseActivity<?>) mContext, path);
                    }

                } else {

                }
            }
        });

        container.addView(adImageView, 0);

        return adImageView;
    }

    @Override
    public int getCount() {
        return null != mPaths ? mPaths.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getIconResId(int index) {
        return index;
    }

    /**
     * 解决list数据清空后，视图不销毁的bug
     */
    @Override
    public int getItemPosition(Object object) {
        if (count > 0)// null != mPaths && mPaths.size() == 0)
        {
            count--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    private int count;

    @Override
    public void notifyDataSetChanged() {
        count = getCount();
        super.notifyDataSetChanged();
    }
}
