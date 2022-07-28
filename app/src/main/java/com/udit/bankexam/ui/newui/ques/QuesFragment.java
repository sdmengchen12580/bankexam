package com.udit.bankexam.ui.newui.ques;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.udit.bankexam.R;
import com.udit.bankexam.adapter.EatBottomTypeAdapter;
import com.udit.bankexam.bean.LoginOutBean;
import com.udit.bankexam.bean.TypeQuesBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.home.fragment.KeChengFragment;
import com.udit.bankexam.ui.newui.quessearch.SearchQuesActivity;
import com.udit.bankexam.ui.other.SettingActivity;
import com.udit.bankexam.ui.user.LoginActivity;
import com.udit.bankexam.utils.DensityUtil;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.MyPageChnageListtener;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.AmityHorizontalEatScrollView;
import com.udit.bankexam.view.MyGridView;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;
import com.umeng.commonsdk.debug.D;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class QuesFragment extends Fragment {

    public QuesFragment() {
    }

    public static QuesFragment newInstance() {
        return new QuesFragment();
    }

    private ImageView img_tiwen;
    private TextView tv_my_ques;
    private TextView et_content;
    private RelativeLayout rl_ques_layout;
    private AmityHorizontalEatScrollView hsl_nearshow;
    private MyGridView mgv_nearshow;
    private ViewPager vp_shops;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int pagerIndex = 0;//记录当前页面的下标
    private EatBottomTypeAdapter eatBottomTypeAdapter;

    //格式时间转long
    public static long getTimeStamp(String strTime, String strFormat) {
        strTime = strTime.trim();
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        Date theDate = null;
        try {
            theDate = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return theDate.getTime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ques, container, false);
        tv_my_ques = (TextView) view.findViewById(R.id.tv_my_ques);
        et_content = (TextView) view.findViewById(R.id.et_content);
        img_tiwen = (ImageView) view.findViewById(R.id.img_tiwen);
        hsl_nearshow = (AmityHorizontalEatScrollView) view.findViewById(R.id.hsl_nearshow);
        mgv_nearshow = (MyGridView) view.findViewById(R.id.mgv_nearshow);
        rl_ques_layout = (RelativeLayout) view.findViewById(R.id.rl_ques_layout);
        //逻辑
        Date date = new Date();
        long longTime = date.getTime();
        String dateNew = "2022-08-01";
        long longTimeNew = getTimeStamp(dateNew, "yyyy-MM-dd");
        Log.e("onCreateView: ", "longTime=" + longTime);
        Log.e("onCreateView: ", "longTimeNew=" + longTimeNew);
        if (longTime > longTimeNew) {
            rl_ques_layout.setVisibility(View.VISIBLE);
        }
        vp_shops = (ViewPager) view.findViewById(R.id.vp_shops);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
        hsl_nearshow.setHomeData();//初始化参数
        //ITEM点击
        mgv_nearshow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (eatBottomTypeAdapter != null) {
                    pagerIndex = position;
                    eatBottomTypeAdapter.updatePositon(pagerIndex);
                    vp_shops.setCurrentItem(position);
                    hsl_nearshow.onHomeClicked(view);
                }
            }
        });

        //提问
        img_tiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                changeAct(null, WantQuesActivity.class);
            }
        });

        //我得问题
        tv_my_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                changeAct(null, MyQuesActivity.class);
            }
        });

        //搜索
        et_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                changeAct(null, SearchQuesActivity.class);
            }
        });
        getQuestionCats();
    }

    protected void changeAct(Bundle paramBundle, Class<?> paramClass) {
        Intent intent = new Intent();
        if (paramBundle != null)
            intent.putExtras(paramBundle);
        intent.setClass(getActivity(), paramClass);
        getActivity().startActivity(intent);
    }

    //底部店铺分类
    private void initBottomShopType(final List<TypeQuesBean.DataBean.ResponseBean.RowsBean> listData) {
        //左右滑动的分类
        pagerIndex = 0;
        initFlmgv2(listData.size());//计算分类横向宽度
        if (eatBottomTypeAdapter == null) {
            eatBottomTypeAdapter = new EatBottomTypeAdapter(getContext(), listData);
            mgv_nearshow.setAdapter(eatBottomTypeAdapter);
        } else {
            eatBottomTypeAdapter.notifyDataSetChanged();
        }
        hsl_nearshow.post(new Runnable() {
            @Override
            public void run() {
                hsl_nearshow.initHomeLeft(pagerIndex);
            }
        });
        //底部分类左右滑动fragment
        if (fragmentList.size() == 0) {
            for (int i = 0; i < listData.size(); i++) {
                fragmentList.add(QuesInnerFragment.newInstance(listData.get(i).getQuestionCatCode()));
            }
        }
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        vp_shops.setAdapter(pagerAdapter);
        vp_shops.setOffscreenPageLimit(fragmentList.size());
        vp_shops.addOnPageChangeListener(new MyPageChnageListtener() {
            @Override
            protected void select(int position) {
                pagerIndex = position;
                hsl_nearshow.initHomeLeft(position);
                eatBottomTypeAdapter.updatePositon(pagerIndex);
            }
        });
    }

    //计算底部分类列表的宽度并布局  fixme params
    private void initFlmgv2(int size) {
//        int allwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
        int itemWidth = DensityUtil.dip2px(getActivity(), 83.5F);// item宽度
        int gridviewWidth = (DensityUtil.dip2px(getActivity(), 83 * size));// 计算GridView宽度
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        mgv_nearshow.setLayoutParams(params);
        mgv_nearshow.setColumnWidth(itemWidth);
        mgv_nearshow.setHorizontalSpacing(0);
        mgv_nearshow.setStretchMode(GridView.NO_STRETCH);
        mgv_nearshow.setNumColumns(size);
    }

    //------------------------------接口------------------------------
    private void getQuestionCats() {
        try {
            HashMap<String, String> map_params = new HashMap<>();
            setHttp(map_params, IHTTP.QUES_TYPE, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    TypeQuesBean bean = new Gson().fromJson(json, TypeQuesBean.class);
                    initBottomShopType(bean.getData().getResponse().getRows());
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
    }

    protected void setHttp(HashMap<String, String> map, String address, IHttpResponseListener listener)
            throws Exception {
        try {
            RequestObject object = new RequestObject(address, map);
            new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, listener);
        } catch (Exception e) {
            throw new Exception();
        }
    }


}
