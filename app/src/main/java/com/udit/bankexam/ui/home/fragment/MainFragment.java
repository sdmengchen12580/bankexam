package com.udit.bankexam.ui.home.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.adapter.HorVideoAdapter;
import com.udit.bankexam.adapter.WorkAdapter;
import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.ModuleBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.ZhaoPinInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.db.FavoriteRecordDao;
import com.udit.bankexam.presenter.home.fragment.MainPresenter;
import com.udit.bankexam.ui.MyWebUriActivity;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_collection.ExamCollectionActivity;
import com.udit.bankexam.ui.exam_data_history.ExamDataHistoryActivity;
import com.udit.bankexam.ui.exam_day.ExamDayActivity;
import com.udit.bankexam.ui.exam_day.QinDaoActivity;
import com.udit.bankexam.ui.exam_err.ExamErrActivity;
import com.udit.bankexam.ui.exam_mk.ExamMkActivity;
import com.udit.bankexam.ui.exam_notebook.ExamNoteBookActivity;
import com.udit.bankexam.ui.exam_report_data.ExamReportDataActvity;
import com.udit.bankexam.ui.exam_robot.ExamRobotActivity;
import com.udit.bankexam.ui.exam_robot_pract.ExamRobotPractActivity;
import com.udit.bankexam.ui.exam_sole.ExamSoleActivity;
import com.udit.bankexam.ui.exam_year.ExamYearActivity;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.home.fragment.adapter.ExamNode;
import com.udit.bankexam.ui.home.fragment.adapter.GridViewAdapter;
import com.udit.bankexam.ui.home.fragment.adapter.HomeBannerPagerAdapter;
import com.udit.bankexam.ui.home.fragment.adapter.HomeModulePagerAdapter;
import com.udit.bankexam.ui.home.fragment.adapter.MainAdapter_new;
import com.udit.bankexam.ui.sreach.ExamSreachActivity;
import com.udit.bankexam.ui.sreach.ExamSreachShouCangActivity;
import com.udit.bankexam.ui.user.MessageActivity;
import com.udit.bankexam.ui.video.AllVideoShowActivity;
import com.udit.bankexam.ui.zixun.ZiXunActivity;
import com.udit.bankexam.ui.zixun.ZiXunListActivity;
import com.udit.bankexam.utils.DBUtils;
import com.udit.bankexam.utils.ExamShouCang;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.home.fragment.MainView;
import com.udit.frame.common.imageview.MyImageView;
import com.udit.frame.common.mygridView.MyGridViewTwo;
import com.udit.frame.common.view.PullToRefreshView;
import com.udit.frame.common.view.PullToRefreshView.OnHeaderRefreshListener;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.viewpagerindicator.CirclePageIndicator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class MainFragment extends BaseFragment<MainPresenter>
        implements MainView.View, OnItemClickListener, OnClickListener,
        MainAdapter_new.ExamGroupOnClick {

    private final String TAG = this.getClass().getSimpleName();

    private static MainFragment MAINFRAGMENT;

    private UserBean bean_user;

    public static MainFragment getIntance() {
        if (MAINFRAGMENT == null) {
            MAINFRAGMENT = new MainFragment();
        }
        return MAINFRAGMENT;
    }

    private View mView;

    private View mView_top;

    private EditText edit_top_sreach;

    @SuppressWarnings("unused")
    private PullToRefreshView main_pull_list;

    private ListView fresh_listview;

    private List<ExamNodeBean> mlist_exam_node;

    // private MainAdapter adapter_main;
    private MainAdapter_new adapter_main;
    private int displayWidth;

    private int mPostion = 0;

    /*
     * *****************广告**********************
     */
    private MyImageView img_load;

    private HorVideoAdapter horVideoAdapter;

    private ViewPager pager_main_adv;

    private CirclePageIndicator indicator_main_adv;

    private List<AdvBean> mlist_adv;

    private HomeBannerPagerAdapter adapter_adv;

    private RelativeLayout.LayoutParams params_banner;

    //  private RelativeLayout rl_advs;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int postion = pager_main_adv.getCurrentItem() + 1;
                if (null != mlist_adv && mlist_adv.size() > 0) {
                    pager_main_adv.setCurrentItem(postion % mlist_adv.size(), true);
                    MyLogUtils.e(TAG, "ADV:" + (postion % mlist_adv.size()));
                }

                handler.sendEmptyMessageDelayed(0, Constant.SKIP_TIME);
            }
        }
    };


    /*
     * *****************功能****************************
     */
    //  private ViewPager pager_main_module;

    //  private CirclePageIndicator indicator_main_module;

    //private HomeModulePagerAdapter adapter_module;

    //private List<List<ModuleBean>> mlist_module_list;

    private List<ModuleBean> mlist_module_list;

    private RelativeLayout.LayoutParams params_module;

    private ExamNodeBean bean_node;

    private int outlineCnt, firstCnt;

    private boolean flag_home = false;

    private LinearLayout ll_message, ll_kefu;

    private MyGridViewTwo module_grid;

    private GridViewAdapter adapter_module;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        displayWidth = getResources().getDisplayMetrics().widthPixels;
        int displayheight = getResources().getDisplayMetrics().heightPixels;
        MyLogUtils.e(TAG, "width:" + displayWidth + "  height:" + displayheight);

        double bili = (double) displayheight / (double) displayWidth;
        String n = Utils.doubleOutPut(bili + "", 1);
        int init_ = 15;
        if (n.equals("1.5")) {
            init_ = 70;
        }
        int height_delete = Utils.dip2px(getActivity(), init_);

        int height = displayWidth / 2 - height_delete;
        MyLogUtils.e(TAG, "30:" + height_delete + "   delete:" + height);
        params_module = new RelativeLayout.LayoutParams(displayWidth, height);

        int height_delete_banner = Utils.dip2px(getActivity(), 40);
        int height_banner = displayWidth / 2 - height_delete_banner;
        params_banner = new RelativeLayout.LayoutParams(displayWidth, height_banner);
        if (params_banner instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params_banner).leftMargin = Utils.dip2px(getActivity(), 10);
            ((ViewGroup.MarginLayoutParams) params_banner).rightMargin = Utils.dip2px(getActivity(), 10);
        }
        mView = inflater.inflate(R.layout.fragment_main, null);
        return mView;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, mView, R.id.class);
            edit_top_sreach.setFocusable(false);

            main_pull_list = (PullToRefreshView) mView.findViewById(R.id.main_pull_list);
            fresh_listview = (ListView) mView.findViewById(R.id.fresh_listview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {
        main_pull_list.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(PullToRefreshView view) {
                if (MainFragment.this.bean_user != null) {
                    ((MainPresenter) MainFragment.this.mPresenter).getTypeOne(MainFragment.this.bean_user.getUid());
                }
                ((MainPresenter) MainFragment.this.mPresenter).getZpList();
                main_pull_list.postDelayed(new Runnable() {
                    public void run() {
                        MainFragment.this.main_pull_list.onHeaderRefreshComplete();
                    }
                }, 800L);
            }
        });

        edit_top_sreach.setOnClickListener(this);

        ll_message.setOnClickListener(this);

        ll_kefu.setOnClickListener(this);
    }

    protected void changeAct(Bundle paramBundle, Class<?> paramClass) {
        Intent intent = new Intent();
        if (paramBundle != null)
            intent.putExtras(paramBundle);
        intent.setClass(getActivity(), paramClass);
        getActivity().startActivity(intent);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        this.mPresenter = new MainPresenter(this);
        getListTopView(savedInstanceState);
        this.fresh_listview.addHeaderView(this.mView_top);
        this.mlist_exam_node = new ArrayList();
        this.bean_node = null;
        this.bean_user = SaveUtils.getUser(getActivity());
        this.outlineCnt = SaveUtils.getAppOutLineCnt(getActivity());
        this.firstCnt = SaveUtils.getAppFirstCnt(getActivity());
        this.flag_home = true;
        ((MainPresenter) this.mPresenter).getAdv();
        ((MainPresenter) this.mPresenter).getModule_net();
        ((MainPresenter) this.mPresenter).getExamNode(this.bean_user.getUid());
        ((MainPresenter) this.mPresenter).getHomeSJ(this.bean_user.getUid());
        ((MainPresenter) this.mPresenter).shoucang(this.bean_user.getUid());
        ((MainPresenter) this.mPresenter).getZpList();
        initVideo();
    }

    private void initVideo() {
        this.rl_new_people_arive = (RelativeLayout) this.mView_top.findViewById(R.id.rl_new_people_arive);
        this.rl_more_work_info = (RelativeLayout) this.mView_top.findViewById(R.id.rl_more_work_info);
        this.rl_more_work_info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
//                MyWebUriActivity.startMyWebUriActivity((BaseActivity) MAINFRAGMENT.getActivity(), "招聘信息", url);
                MyWebUriActivity.startMyWebUriActivity((BaseActivity) MAINFRAGMENT.getActivity(), "招聘信息", "http://m.yinhangzhaopin.com/");
            }
        });
        this.rl_more_video = (RelativeLayout) this.mView_top.findViewById(R.id.rl_more_video);
        this.rl_video = (RelativeLayout) this.mView_top.findViewById(R.id.rl_video);
        this.rv = (RecyclerView) this.mView_top.findViewById(R.id.rv);
        this.rl_kaodian = (RelativeLayout) this.mView_top.findViewById(R.id.rl_kaodian);
        this.rv.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        ((MainPresenter) this.mPresenter).getTypeOne(this.bean_user.getUid());
        initClick();
    }

    private void initClick() {
        this.rl_more_video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                MainFragment.this.changeAct(null, AllVideoShowActivity.class);
            }
        });
        this.rl_new_people_arive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                ZiXunListActivity.startZiXunListActivity((BaseActivity) MainFragment.this.getActivity(), "报考指南");
            }
        });
        this.rl_kaodian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                ((HomeActivity) MainFragment.this.getActivity()).changeToTiku();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (handler != null) {
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, Constant.SKIP_TIME);
        }

    }

    private WorkAdapter workAdapter;

    private RelativeLayout rl_kaodian;

    private RelativeLayout rl_more_video;

    private RelativeLayout rl_more_work_info;

    private RelativeLayout rl_new_people_arive;

    private RelativeLayout rl_video;

    private RecyclerView rv;

    int static_size = 0;

    @Override
    public void onResume() {
        super.onResume();
       /*   MyLogUtils.e(TAG, "onResume:" + (mlist_exam_node != null ? mlist_exam_node.size() : "is null"));
      if (mlist_exam_node != null && mlist_exam_node.size() > 0) {
            static_size = mlist_exam_node.size();
            for (int i = 0; mlist_exam_node != null && i < mlist_exam_node.size(); i++) {
                ExamNodeBean bean = mlist_exam_node.get(i);
                mPresenter.checkHomeNode(bean_user.getUid(), bean.getID());
            }
        }*/
        MyLogUtils.e(TAG, "onResume:");
        if (bean_node != null && bean_node != null && !bean_node.getID().isEmpty()) {
            mPresenter.checkHomeNode(bean_user.getUid(), bean_node.getID());

        }
        // handler.sendEmptyMessageDelayed(0, Constant.SKIP_TIME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);

        if (resultCode == Constant.RESULT.RESULT_EXAM_HOME) {
            String oid = data.getStringExtra(Constant.RESULT.OID);
            boolean flag_oid = data.getBooleanExtra(Constant.RESULT.TRUE_FLAG_OID, false);

            if (!MyCheckUtils.isEmpty(oid) && flag_oid)
                mPresenter.checkHomeNode(bean_user.getUid(), oid);
        }
    }


    private void getListTopView(Bundle savedInstanceState) {
        mView_top = LayoutInflater.from(getActivity()).inflate(R.layout.item_top_adv, null);
        initAdv();
        initModule();
    }

    private void initAdv() {
        pager_main_adv = (ViewPager) mView_top.findViewById(R.id.pager_main_adv);
        pager_main_adv.setVisibility(View.VISIBLE);
        img_load = (MyImageView) mView_top.findViewById(R.id.img_load);
        img_load.setVisibility(View.VISIBLE);
        //   rl_advs = (RelativeLayout) mView_top.findViewById(R.id.rl_adv);
        mlist_adv = new ArrayList<>();

        adapter_adv = new HomeBannerPagerAdapter(getActivity(), mlist_adv);
        pager_main_adv.setAdapter(adapter_adv);

        indicator_main_adv = (CirclePageIndicator) mView_top.findViewById(R.id.indicator_main_adv);
        indicator_main_adv.setViewPager(pager_main_adv);


        // rl_advs.setLayoutParams(params_banner);
        pager_main_adv.setLayoutParams(params_banner);

        img_load.setLayoutParams(params_banner);


    }


    private void initModule() {
       /* pager_main_module = (ViewPager) mView_top.findViewById(R.id.pager_main_module);
        pager_main_module.setVisibility(View.VISIBLE);
        mlist_module_list = new ArrayList<>();
        adapter_module = new HomeModulePagerAdapter(getActivity(), mlist_module_list);
        pager_main_module.setAdapter(adapter_module);

        indicator_main_module = (CirclePageIndicator) mView_top.findViewById(R.id.indicator_main_module);
        indicator_main_module.setViewPager(pager_main_module);

        pager_main_module.setLayoutParams(params_module);

        adapter_module.setmGridOnItemClickListener(this);
        adapter_module.notifyDataSetChanged();*/
        module_grid = (MyGridViewTwo) mView_top.findViewById(R.id.module_grid);
        this.module_grid.setSelector(new ColorDrawable(0));
        mlist_module_list = new ArrayList<>();
        adapter_module = new GridViewAdapter(mlist_module_list, getActivity());
        module_grid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModuleBean moduleBean = mlist_module_list.get(position);
                Log.e("onItemClick: ", "moduleBean.getUrl_type()=" + moduleBean.getUrl_type());
                Log.e("onItemClick: ", "moduleBean.getUrl()=" + moduleBean.getUrl());
                if (moduleBean.getUrl_type().equals(Constant.URL_APP)) {
                    if (moduleBean.getUrl().equals("zxxz") || moduleBean.getUrl().equals("bkzn")) {
                        ZiXunListActivity.startZiXunListActivity((BaseActivity) MainFragment.this.getActivity(), moduleBean.getTitle());
                        return;
                    }
                    if (moduleBean.getUrl().equals("mryl")) {
                        ExamDayActivity.startExamDayActivity((BaseActivity) MainFragment.this.getActivity());
                        return;
                    }
                    if (moduleBean.getUrl().equals("lnzt")) {
                        ExamYearActivity.startExamYearActivity((BaseActivity) MainFragment.this.getActivity());
                        return;
                    }
                    if (moduleBean.getUrl().equals("djmj")) {
                        ExamSoleActivity.startExamSoleActivity((BaseActivity) MainFragment.this.getActivity());
                        return;
                    }
                    if (moduleBean.getUrl().equals("mkds")) {
                        ExamMkActivity.startExamMkActivity((BaseActivity) MainFragment.this.getActivity());
                        return;
                    }
                    MainFragment.this.showLongToast("未知的跳转地址");
                } else if (moduleBean.getUrl_type().equals(Constant.URL_WEB)) {
                    //淘宝链接
                    if (!MyCheckUtils.isEmpty(moduleBean.getUrl()) && moduleBean.getUrl().contains("item.taobao.com")) {
                        //跳转淘宝
                        if (isAvilible(getContext(), "com.taobao.taobao")) {
                            Intent intent = new Intent();
                            intent.setAction("Android.intent.action.VIEW");
                            Uri uri = Uri.parse(moduleBean.getUrl()); // 商品地址
                            intent.setData(uri);
                            intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                            startActivity(intent);
                            return;
                        }
                        //系统浏览器
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(moduleBean.getUrl());
                        intent.setData(content_url);
                        startActivity(intent);
                        return;
                    }
                    if (!MyCheckUtils.isEmpty(moduleBean.getUrl())) {
                        MyWebUriActivity.startMyWebUriActivity((BaseActivity) MAINFRAGMENT.getActivity(), moduleBean.getTitle(), moduleBean.getUrl());
                    } else {
                        ((BaseActivity<?>) MAINFRAGMENT.getActivity()).showLongToast("后台未配置跳转地址");
                    }
                }
            }
        });


        module_grid.setAdapter(adapter_module);
        adapter_module.notifyDataSetChanged();

    }

    private boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    @Override
    public void setModule(List<ModuleBean> list_module) {
       /* mlist_module_list.clear();
        mlist_module_list.addAll(list_module);

        pager_main_module.setLayoutParams(params_module);

        indicator_main_module.setVisibility(View.VISIBLE);
        pager_main_module.setVisibility(View.VISIBLE);

        adapter_module.notifyDataSetChanged();
        pager_main_module.setCurrentItem(0);*/
        if (list_module != null) {
            if (list_module.size() == 0)
                return;
            this.mlist_module_list.clear();
            this.mlist_module_list.addAll(list_module);
            this.adapter_module.notifyDataSetChanged();
            return;
        }
    }

    @Override
    public void setTypeOne(final List<VideoTypeOneBean> param1List) {
        if (param1List != null && param1List.size() > 0)
            this.rv.post(new Runnable() {
                public void run() {
                    MainFragment.this.rl_video.setVisibility(View.VISIBLE);
                    MainFragment.this.rv.setVisibility(View.VISIBLE);
                    MainFragment.this.horVideoAdapter = new HorVideoAdapter(MainFragment.this.getContext(), param1List);
                    MainFragment.this.rv.setAdapter(MainFragment.this.horVideoAdapter);
                }
            });
    }

    @Override
    public void setWork(List<ZhaoPinInfo.DataBean.ResponseBean.ZhaopinsBean> param1List, final String url) {
        if (null == this.workAdapter) {
            this.workAdapter = new WorkAdapter(param1List, getContext());
            this.fresh_listview.setAdapter(this.workAdapter);
        } else {
            this.workAdapter.refreshData(param1List);
        }
        this.rl_more_work_info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                MyWebUriActivity.startMyWebUriActivity((BaseActivity) MAINFRAGMENT.getActivity(), "招聘信息", url);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         /*MyLogUtils.e(TAG, "当前第几页:" + pager_main_module.getCurrentItem());
       int n = pager_main_module.getCurrentItem();
        List<ModuleBean> list = mlist_module_list;
        ModuleBean bean = list.get(position);
        MyLogUtils.e(TAG, "功能模块:" + bean.getTitle());
        if (bean.getTitle().equals(Constant.MainModule.MODULE_1)) {//做题
            mPresenter.getHomeSJ(bean_user.getUid());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_2)) {//每日一练
            ExamDayActivity.startExamDayActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_3)) {//智能组卷
            ExamRobotActivity.startExamRobotActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_4)) {//智能练习
            ExamRobotPractActivity.startExamRobotPractActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_5)) {//历年真题
            ExamYearActivity.startExamYearActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_6)) {//独家密卷
            ExamSoleActivity.startExamSoleActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_7)) {//模考大赛
            ExamMkActivity.startExamMkActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_8)) {//数据报告
            ExamReportDataActvity.startExamReportDataActvity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_9)) {//错题本
            ExamErrActivity.startExamErrActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_10)) {//收藏 题目
            ExamCollectionActivity.startExamCollectionActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_11)) {//笔记本
            ExamNoteBookActivity.startExamNoteBookActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_12)) {//练习历史
            ExamDataHistoryActivity.startExamDataHistoryActivity((BaseActivity<?>) getActivity());
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_15)) {//招聘（咨询）
            //ZiXunActivity.startZiXunActivity((BaseActivity<?>) getActivity());
            MyWebUriActivity.startMyWebUriActivity((BaseActivity<?>) getActivity(), "最新招聘", IHTTP.ZIXUNIP);
        } else if (bean.getTitle().equals(Constant.MainModule.MODULE_16)) {//网申模拟
            MyWebUriActivity.startMyWebUriActivity((BaseActivity<?>) getActivity(), "网申模拟", IHTTP.WANGSHOU);
        }*/
       /* else if(bean.getTitle().equals(Constant.MainModule.MODULE_13))
        {//练习报告
            ExamReportActivity.startExamReportActivity((BaseActivity<?>) getActivity());
        }
        else if(bean.getTitle().equals(Constant.MainModule.MODULE_14))
        {//练习周报
            ExamReportWeekActivity.startExamReportWeekActivity((BaseActivity<?>)getActivity());
        }*/
    }


    @Override
    public void setAdv(List<AdvBean> list_adv) {
        mlist_adv.clear();
        if (list_adv != null) {
            mlist_adv.addAll(list_adv);
            pager_main_adv.setLayoutParams(params_banner);
            img_load.setLayoutParams(params_banner);
            indicator_main_adv.setVisibility(View.VISIBLE);
            pager_main_adv.setVisibility(View.VISIBLE);
            img_load.setVisibility(View.GONE);

            adapter_adv.notifyDataSetChanged();
            pager_main_adv.setCurrentItem(0);
            indicator_main_adv.notifyDataSetChanged();
            adapter_adv.notifyDataSetChanged();
        } else {
            AdvBean bean = new AdvBean();
            bean.setImg(R.mipmap.banner_demo + "");
            mlist_adv.add(bean);
            pager_main_adv.setLayoutParams(params_banner);
            indicator_main_adv.setVisibility(View.VISIBLE);
            pager_main_adv.setVisibility(View.VISIBLE);
            img_load.setVisibility(View.GONE);

            adapter_adv.notifyDataSetChanged();
            pager_main_adv.setCurrentItem(0);
            indicator_main_adv.notifyDataSetChanged();
            adapter_adv.notifyDataSetChanged();
        }


        handler.sendEmptyMessageDelayed(0, Constant.SKIP_TIME);
    }

    @Override
    public void setExamNode(List<ExamNodeBean> list_node) {
       /* if (list_node != null) {
            mlist_exam_node.clear();
            mlist_exam_node.addAll(list_node);
            adapter_main.notifyDataSetChanged();

        } else {
            mlist_exam_node.clear();
            adapter_main.notifyDataSetChanged();
        }
        main_pull_list.onHeaderRefreshComplete();*/
        this.main_pull_list.postDelayed(new Runnable() {
            public void run() {
                MainFragment.this.main_pull_list.onHeaderRefreshComplete();
            }
        }, 500L);
    }

    private List<ExamNodeBean> setExamList(List<ExamNodeBean> mlist_exam_node, List<ExamNodeBean> list_node) {
        for (int i = 0; list_node != null && i < list_node.size(); i++) {
            ExamNodeBean bean_node = list_node.get(i);
            if (mlist_exam_node.contains(bean_node)) {
                int postion = mlist_exam_node.indexOf(bean_node);

                ExamNodeBean bean_mlist = mlist_exam_node.get(postion);

                bean_mlist.setNoCount(bean_node.getNoCount());
                bean_mlist.setOkCount(bean_node.getOkCount());
                bean_mlist.setDoCount(bean_node.getDoCount());
                bean_mlist.setTCount(bean_node.getTCount());
                if (bean_mlist.getList_outlineinfo() != null
                        && bean_node.getList_outlineinfo() != null) {
                    return setExamList(bean_mlist.getList_outlineinfo(), bean_node.getList_outlineinfo());
                }
            } else
                return null;
        }
        return mlist_exam_node;

    }


    @Override
    public void setHomeExam(List<ExamTitleBean> list_title) {
        if (list_title != null && list_title.size() > 0) {
            outlineCnt = SaveUtils.getAppOutLineCnt(getActivity());
            List<ExamTitleBean> list = ExamUtils.getHomeExamTitleList(list_title, outlineCnt);
            if (list != null && list.size() > 0) {
                String titles = ExamUtils.getExamTtile(list);
                if (titles != null) {

                    ExamActivity.startExamActivity((BaseActivity<?>) getActivity(), bean_node.getID(), bean_node.getName(), titles, false, true, false, false);
                    //   ExamActivity.startExamActivity((BaseActivity<?>) getActivity(),bean_node,titles);
                } else {
                    showLongToast("题库暂时无题目");
                }

            } else {
                showLongToast("题库暂时无题目");
            }

        } else {
            showLongToast("题库暂时无题目");
        }
    }

    @Override
    public void setHomeExmaSJ(ExamInfoBean bean) {
        if (flag_home && bean != null && !MyCheckUtils.isEmpty(bean.getID())) {
            /**
             * ru362
             */
            mPresenter.saveShouYe(bean_user.getUid(), bean.getID(), bean.getName());
        } else {
            if (bean != null && !MyCheckUtils.isEmpty(bean.getID())) {
                mPresenter.getHomeSJDTK(bean_user.getUid(), bean.getID());
            } else {
                // showLongToast("未设置可用的试卷");
            }
        }
        flag_home = false;

    }

    @Override
    public void setHomeExamDTK(List<ExamTitleBean> list) {
        if (list != null && list.size() > 0) {
            firstCnt = SaveUtils.getAppFirstCnt(getActivity());
            List<ExamTitleBean> list_title = ExamUtils.getHomeExamTitleList(list, firstCnt);
            if (list_title != null && list_title.size() > 0) {

                String idlist = ExamUtils.getExamTtile(list_title);
                String shouYe = SaveUtils.getExamShouYe(getActivity());
                ExamActivity.startExamActivity((BaseActivity<?>) getActivity(), shouYe, "做题", idlist, false, true, true, false);
                //ExamActivity.startExamActivity((BaseActivity<?>) getActivity(),"做题",idlist,true);

            } else {
                showLongToast("未设置可用的试卷");
            }


        } else {
            showLongToast("未设置可用的试卷");
        }
    }

    /**
     * 保存首页试卷ID,后面用做分析和其他作用，如果首页更换了试卷，这里也会变化
     *
     * @param id
     */
    @Override
    public void saveExamShouYe(String id) {
        SaveUtils.saveExamShouYe(getActivity(), id);
    }

    @Override
    public void saveShoucang(List<FavoriteRecord> list) {

        ExamShouCang.insertAllExamShouCang(bean_user.getUid(), list);

    }


    @Override
    public void checkHomeNode(final List<ExamNodeBean> list) {
//        if (mlist_exam_node == null || mlist_exam_node.size() <= 0)
//            return;
//        if (list == null || list.size() <= 0)
//            return;
//           /* for(int i = 0 ;list!=null && i<list.size();i++)
//            {*/
//        ExamNodeBean bean_node = list.get(0);
//        if (mlist_exam_node.contains(bean_node)) {
//            int postion = mlist_exam_node.indexOf(bean_node);
//            ExamNodeBean bean_node_mlist = mlist_exam_node.get(postion);
//            bean_node_mlist.setDoCount(bean_node.getDoCount());
//            bean_node_mlist.setNoCount(bean_node.getNoCount());
//            bean_node_mlist.setTCount(bean_node.getTCount());
//            bean_node_mlist.setErrCount(bean_node.getErrCount());
//            bean_node_mlist.setOkCount(bean_node.getOkCount());
//        }
//       /* static_size--;
//        if (static_size == 0)*/
//        adapter_main.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_top_sreach:
                Intent intent = new Intent(getActivity(), ExamSreachActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_kefu:
                if (Utils.isQQAvailable(getActivity())) {
                    Utils.startQQ(getActivity(), Constant.QQ_ZIXUN);
                } else {
                    showLongToast("请先安装QQ");
                }
                break;
            case R.id.ll_message:
                MessageActivity.startMessageActivity((BaseActivity<?>) getActivity());
                break;

            default:
                break;
        }
    }


    /**
     * 点击节点，用来获取 节点的试题，先通过接口 获取到答题卡，在获取题目
     *
     * @param bean
     */
    @Override
    public void ExamGo(ExamNodeBean bean) {
        MyLogUtils.e(TAG, "父类：" + bean.getID() + "  " + bean.getName());
        bean_node = bean;
        mPresenter.getHomeExam(getActivity(), bean_user.getUid(), bean.getID());

    }

}
