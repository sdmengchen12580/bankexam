package com.udit.bankexam.ui.home.fragment.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ModuleBean;
import com.udit.frame.common.mygridView.MyGridViewTwo;
import com.udit.frame.utils.MyLogUtils;

import com.viewpagerindicator.IconPagerAdapter;

@SuppressLint("InflateParams")
public class HomeModulePagerAdapter extends PagerAdapter implements IconPagerAdapter {
    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    private GridViewAdapter adapter_grid;

    private List<List<ModuleBean>> mPaths;

    private List<MyGridViewTwo> mGrid;

    private OnItemClickListener mGridOnItemClickListener;

    public HomeModulePagerAdapter(Context context, List<List<ModuleBean>> paths) {
        mContext = context;

        mPaths = paths;

        mGrid = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View pageView = LayoutInflater.from(mContext).inflate(R.layout.home_loop_module_viewpager_item, null);

        final List<ModuleBean> mlist = mPaths.get(position);
        MyLogUtils.e(TAG, "功能模块：" + mlist.size() + "");
        MyGridViewTwo grid = (MyGridViewTwo) pageView.findViewById(R.id.module_grid);

        adapter_grid = new GridViewAdapter(mlist, mContext);

        grid.setAdapter(adapter_grid);

        adapter_grid.notifyDataSetChanged();

        grid.setOnItemClickListener(mGridOnItemClickListener);

        mGrid.add(grid);
        container.addView(pageView, 0);

        return pageView;
    }


    public void setmGridOnItemClickListener(OnItemClickListener mGridOnItemClickListener) {
        this.mGridOnItemClickListener = mGridOnItemClickListener;
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

    public List<List<ModuleBean>> getmPaths() {
        return mPaths;
    }

    public List<MyGridViewTwo> getmGrid() {
        return mGrid;
    }


}
