package com.udit.bankexam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jauker.widget.BadgeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.user.LoginActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.Utils;
import com.viewpagerindicator.IconPagerAdapter;

public class GuidePagerAdapter extends PagerAdapter implements IconPagerAdapter {

    private Context mContext;

    private ImageLoader imageLoader = ImageLoader.getInstance();

    DisplayImageOptions options;

    private WelcomeActivity activity;

    /**
     * 图片相对路径
     */
    private SparseArray<Bitmap> mPaths;

    public GuidePagerAdapter(Context context, SparseArray<Bitmap> paths, WelcomeActivity activity) {
        mContext = context;
        mPaths = paths;
        this.activity = activity;
    }

    /**
     * {实例化条目}
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View pageView = LayoutInflater.from(mContext).inflate(R.layout.home_banner_item, null);
        ImageView adImageView = (ImageView) pageView.findViewById(R.id.iv_banner);
        BadgeView backgroundDrawableBadge = new BadgeView(mContext);
        backgroundDrawableBadge.setBadgeCount(position + 1);
        backgroundDrawableBadge.setTextColor(mContext.getResources().getColorStateList(R.color.transparent));
        backgroundDrawableBadge.setBackgroundResource(R.mipmap.skip);
        backgroundDrawableBadge.setTargetView(adImageView);
        backgroundDrawableBadge.setBadgeMargin(0, Utils.dip2px(this.mContext, 15.0F), Utils.dip2px(this.mContext, 5.0F), 0);
        adImageView.setBackground(new BitmapDrawable(mPaths.get(position)));
        if (position == mPaths.size() - 1) {
            adImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserBean bean = SaveUtils.getUser(mContext);
                    activity.unregisterReceiver(WelcomeActivity.pageLoadBroard);
                    if (bean != null && !MyCheckUtils.isEmpty(bean.getUid())
                            && !MyCheckUtils.isEmpty(bean.getMobile())
                            && !MyCheckUtils.isEmpty(bean.getPass())) {
                        HomeActivity.startHomeActivity((BaseActivity<?>) mContext);
                    } else
                        LoginActivity.startLoginActivity((BaseActivity<?>) mContext);

                }
            });
        }
        backgroundDrawableBadge.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean bean = SaveUtils.getUser(mContext);
                if (bean != null && !MyCheckUtils.isEmpty(bean.getUid())
                        && !MyCheckUtils.isEmpty(bean.getMobile())
                        && !MyCheckUtils.isEmpty(bean.getPass())) {
                    HomeActivity.startHomeActivity((BaseActivity<?>) mContext);
                } else
                    LoginActivity.startLoginActivity((BaseActivity<?>) mContext);
            }
        });
        container.addView(pageView, 0);
        return pageView;
    }

    /**
     * {获取条目数量}
     */
    @Override
    public int getCount() {
        return null != mPaths ? mPaths.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    /**
     * {销毁条目}
     */
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
        return null != mPaths && mPaths.size() == 0 ? POSITION_NONE : super.getItemPosition(object);
    }
}
