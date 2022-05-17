package com.udit.bankexam.ui.home.fragment;

import java.util.ArrayList;
import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.home.fragment.ZhiboPresenter;
import com.udit.bankexam.ui.home.fragment.adapter.MyFragmentPagerAdapter;
import com.udit.bankexam.ui.zhibo.MeKeChengFragment;
import com.udit.bankexam.ui.zhibo.ZhiboZhongxinFragment;
import com.udit.bankexam.view.home.fragment.ZhiboView;
import com.udit.frame.common.scrollhuadong.ColumnHorizontalScrollView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyCommonUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ZhiboFragment extends BaseFragment<ZhiboPresenter> implements ZhiboView.View, OnClickListener {
    private final String TAG = this.getClass().getSimpleName();


    private static ZhiboFragment ZHIBOFRAGMENT;

    public static ZhiboFragment getIntance() {
        if (ZHIBOFRAGMENT == null) {
            ZHIBOFRAGMENT = new ZhiboFragment();
        }
        return ZHIBOFRAGMENT;
    }

    private View mView;

    private ImageView img_top_return;

    private TextView text_top_centent;

    private TextView text_top_right;

    private ColumnHorizontalScrollView scroll_zhibo_title;

    private LinearLayout ll_zhibo_title;


    /**
     * 当前选中的栏目
     */
    private int columnSelectIndex = 0;

    /**
     * 屏幕宽度
     */
    private int mScreenWidth = 0;

    /**
     * Item宽度
     */
    private int mItemWidth = 0;

    private ArrayList<BaseFragment<?>> mFragments;

    View view;

    TextView item_title, line;

    private List<String> mlist_title;

    private RelativeLayout.LayoutParams params_title;

    private ViewPager viewpager_zhibo;

    private BaseActivity<?> mContext;

    private LinearLayout ll_kefu;

    @SuppressLint({"InlinedApi", "InflateParams"})
    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_zhibo, null);
        mScreenWidth = MyCommonUtils.getscreen(getActivity()).widthPixels;
        mItemWidth = mScreenWidth / 2;// 一个Item宽度为屏幕的1/4
        params_title =
                new RelativeLayout.LayoutParams(mItemWidth, LayoutParams.MATCH_PARENT);
        params_title.leftMargin = 5;
        params_title.rightMargin = 5;
        return mView;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, mView, R.id.class);
            img_top_return.setVisibility(View.GONE);
            text_top_centent.setText("直播");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {
        ll_kefu.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter = new ZhiboPresenter(this);

        mContext = (BaseActivity<?>) getActivity();

        mlist_title = new ArrayList<>();
        mFragments = new ArrayList<>();

        mPresenter.getTitle();

    }

    @Override
    public void setTitle(List<String> list_title) {
        mlist_title.clear();
        mlist_title.addAll(list_title);
        initTable();
        initFragment();
    }

    @SuppressLint("InflateParams")
    private void initTable() {

        ll_zhibo_title.removeAllViews();
        int count = mlist_title.size();
        scroll_zhibo_title.setParam(getActivity(), mScreenWidth, ll_zhibo_title);
        for (int i = 0; i < count; i++) {
            String title = mlist_title.get(i);


            view = LayoutInflater.from(getActivity()).inflate(R.layout.item_title, null);
            item_title = (TextView) view.findViewById(R.id.text_title);
            line = (TextView) view.findViewById(R.id.text_line);
            line.setBackgroundResource(R.drawable.line);

            item_title.setText(title);
            item_title.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if (i == columnSelectIndex) {
                view.setSelected(true);
            }
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < ll_zhibo_title.getChildCount(); i++) {
                        View localView = ll_zhibo_title.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else {
                            localView.setSelected(true);
                            viewpager_zhibo.setCurrentItem(i);
                        }
                    }
                }
            });
            ll_zhibo_title.addView(view, i, params_title);
        }


    }

    private void initFragment() {
        mFragments.clear();
        int count = mlist_title.size();
        for (int i = 0; i < count; i++) {
            Bundle data = new Bundle();
            data.putString("text", mlist_title.get(i));
            if (i == 0) {
                ZhiboZhongxinFragment zhiboZhongxinFragment = ZhiboZhongxinFragment.getIntance();
                zhiboZhongxinFragment.setArguments(data);
                mFragments.add(zhiboZhongxinFragment);
            } else if (i == 1) {
                MeKeChengFragment meKeChengFragment = MeKeChengFragment.getIntance();
                meKeChengFragment.setArguments(data);
                mFragments.add(meKeChengFragment);
            }

        }
        //  @SuppressWarnings("unused")
        // FragmentManager manager = getChildFragmentManager();
        MyFragmentPagerAdapter mAdapetr = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments);
        // mViewPager.setOffscreenPageLimit(0);
        viewpager_zhibo.setAdapter(mAdapetr);
        viewpager_zhibo.setOnPageChangeListener(pageListener);

    }

    public OnPageChangeListener pageListener = new OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            Log.e("position==", "" + position);
            viewpager_zhibo.setCurrentItem(position);
            selectTab(position);
            initTable();
        }

    };


    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < ll_zhibo_title.getChildCount(); i++) {
            View checkView = ll_zhibo_title.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            scroll_zhibo_title.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        // 判断是否选中
        for (int j = 0; j < ll_zhibo_title.getChildCount(); j++) {
            View checkView = ll_zhibo_title.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        if (resultCode == mContext.RESULT_OK) {
            for (int i = 0; mFragments != null && i < mFragments.size(); i++) {
                Fragment frag = mFragments.get(i);
                if (frag == null) {
                    MyLogUtils.e(TAG, "Activity result no fragment exists for index: 0x"
                            + Integer.toHexString(requestCode));
                } else {
                    if (requestCode == Constant.RESULT.RESULT_ZHIBO_DETAIL) {
                        if (frag instanceof ZhiboZhongxinFragment) {
                            frag.onActivityResult(requestCode & 0xffff, resultCode, data);
                        }
                        if (frag instanceof MeKeChengFragment) {
                            frag.onActivityResult(requestCode & 0xffff, resultCode, data);
                        }
                    }
                }
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_kefu:
                if (Utils.isQQAvailable(getActivity())) {
                    Utils.startQQ(getActivity(), Constant.QQ_ZIXUN);
                } else {
                    showLongToast("请先安装QQ");
                }
                break;

        }
    }
}
