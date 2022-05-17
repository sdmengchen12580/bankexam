package com.udit.bankexam.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.ModuleBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.ZhaoPinInfo;
import com.udit.bankexam.presenter.home.fragment.MainPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.exam_data_history.ExamHomeHistoryActivity;
import com.udit.bankexam.ui.exam_err.ExamErrActivity;
import com.udit.bankexam.ui.exam_robot.ExamRobotActivity;
import com.udit.bankexam.ui.exam_sole.ExamSoleActivity;
import com.udit.bankexam.ui.home.fragment.adapter.MainAdapter_new;
import com.udit.bankexam.utils.ExamShouCang;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.home.fragment.MainView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class QuesDataFragment extends BaseFragment<MainPresenter> implements MainView.View,
        AdapterView.OnItemClickListener, View.OnClickListener, MainAdapter_new.ExamGroupOnClick {

    private MainAdapter_new adapter_main;
    private ExamNodeBean bean_node;
    private UserBean bean_user;
    private int firstCnt;
    private boolean flag_home = false;
    private ListView fresh_listview;
    private View mView;
    private View mView_top;
    private List<ExamNodeBean> mlist_exam_node;
    private int outlineCnt;

    private void getListTopView() {
        this.mView_top = LayoutInflater.from(getActivity()).inflate(R.layout.item_ques_top_layout, null);
        this.mView_top.findViewById(R.id.ll_djmj).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                ExamSoleActivity.startExamSoleActivity((BaseActivity) QuesDataFragment.this.getActivity());
            }
        });
        this.mView_top.findViewById(R.id.ll_ctb).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                ExamErrActivity.startExamErrActivity((BaseActivity) QuesDataFragment.this.getActivity());
            }
        });
        this.mView_top.findViewById(R.id.ll_llxs).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                ExamHomeHistoryActivity.startExamHomeHistoryActivity((BaseActivity) QuesDataFragment.this.getActivity());
            }
        });
        this.mView_top.findViewById(R.id.ll_znzj).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                ExamRobotActivity.startExamRobotActivity((BaseActivity) QuesDataFragment.this.getActivity());
            }
        });
    }

    public static QuesDataFragment newInstance() {
        return new QuesDataFragment();
    }

    @Override
    public void ExamGo(ExamNodeBean paramExamNodeBean) {
        this.bean_node = paramExamNodeBean;
        ((MainPresenter) this.mPresenter).getHomeExam(getActivity(), this.bean_user.getUid(), paramExamNodeBean.getID());
    }

    @Override
    public void checkHomeNode(List<ExamNodeBean> paramList) {
        if (this.mlist_exam_node != null) {
            if (this.mlist_exam_node.size() <= 0)
                return;
            if (paramList != null) {
                if (paramList.size() <= 0)
                    return;
                ExamNodeBean examNodeBean = (ExamNodeBean) paramList.get(0);
                if (this.mlist_exam_node.contains(examNodeBean)) {
                    int i = this.mlist_exam_node.indexOf(examNodeBean);
                    ExamNodeBean examNodeBean1 = (ExamNodeBean) this.mlist_exam_node.get(i);
                    examNodeBean1.setDoCount(examNodeBean.getDoCount());
                    examNodeBean1.setNoCount(examNodeBean.getNoCount());
                    examNodeBean1.setTCount(examNodeBean.getTCount());
                    examNodeBean1.setErrCount(examNodeBean.getErrCount());
                    examNodeBean1.setOkCount(examNodeBean.getOkCount());
                }
                this.adapter_main.notifyDataSetChanged();
                return;
            }
            return;
        }
    }

    @Override
    public void initData(Bundle paramBundle) {
        this.mPresenter = new MainPresenter(this);
        getListTopView();
        this.bean_user = SaveUtils.getUser(getActivity());
        this.fresh_listview.addHeaderView(this.mView_top);
        this.mlist_exam_node = new ArrayList();
        this.adapter_main = new MainAdapter_new(this.mlist_exam_node, getActivity());
        this.fresh_listview.setAdapter(this.adapter_main);
        this.adapter_main.setExamGroupClick(this);
        this.adapter_main.notifyDataSetChanged();
        this.bean_node = null;
        this.flag_home = true;
        ((MainPresenter) this.mPresenter).getHomeSJ(this.bean_user.getUid());
        ((MainPresenter) this.mPresenter).getExamNode(this.bean_user.getUid());
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, this.mView, com.udit.bankexam.R.id.class);
            this.fresh_listview = (ListView) this.mView.findViewById(R.id.fresh_listview);
            return;
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    @Override
    public void onClick(View paramView) {
        paramView.getId();
    }

    @Override
    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    }

    @Override
    public void saveExamShouYe(String paramString) {
        SaveUtils.saveExamShouYe(getActivity(), paramString);
    }

    @Override
    public void saveShoucang(List<FavoriteRecord> paramList) {
        ExamShouCang.insertAllExamShouCang(this.bean_user.getUid(), paramList);
    }

    @Override
    public void setAdv(List<AdvBean> paramList) {
    }

    @Override
    public View setContentView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.mView = paramLayoutInflater.inflate(R.layout.fragment_ques_data_list, null);
        return this.mView;
    }

    @Override
    public void setExamNode(List<ExamNodeBean> paramList) {
        if (paramList != null) {
            this.mlist_exam_node.clear();
            this.mlist_exam_node.addAll(paramList);
            this.adapter_main.notifyDataSetChanged();
            return;
        }
        this.mlist_exam_node.clear();
        this.adapter_main.notifyDataSetChanged();
    }

    @Override
    public void setHomeExam(List<ExamTitleBean> paramList) {
        if (paramList != null && paramList.size() > 0) {
            this.outlineCnt = SaveUtils.getAppOutLineCnt(getActivity());
            paramList = ExamUtils.getHomeExamTitleList(paramList, this.outlineCnt);
            if (paramList != null && paramList.size() > 0) {
                String str = ExamUtils.getExamTtile(paramList);
                if (str != null) {
                    ExamActivity.startExamActivity((BaseActivity) getActivity(), this.bean_node.getID(),
                            this.bean_node.getName(), str, false, true, false, false);
                    return;
                }
                showLongToast("题库暂时无题目");
                return;
            }
            showLongToast("题库暂时无题目");
            return;
        }
        showLongToast("题库暂时无题目");
    }

    @Override
    public void setHomeExamDTK(List<ExamTitleBean> paramList) {
        if (paramList != null && paramList.size() > 0) {
            this.firstCnt = SaveUtils.getAppFirstCnt(getActivity());
            paramList = ExamUtils.getHomeExamTitleList(paramList, this.firstCnt);
            if (paramList != null && paramList.size() > 0) {
                String str1 = ExamUtils.getExamTtile(paramList);
                String str2 = SaveUtils.getExamShouYe(getActivity());
                ExamActivity.startExamActivity((BaseActivity) getActivity(), str2, "做题", str1, false, true, true, false);
                return;
            }
            showLongToast("未设置可用的试卷");
            return;
        }
        showLongToast("未设置可用的试卷");
    }

    @Override
    public void setHomeExmaSJ(ExamInfoBean paramExamInfoBean) {
        if (this.flag_home && paramExamInfoBean != null && !MyCheckUtils.isEmpty(paramExamInfoBean.getID())) {
            ((MainPresenter) this.mPresenter).saveShouYe(this.bean_user.getUid(), paramExamInfoBean.getID(), paramExamInfoBean.getName());
        } else if (paramExamInfoBean != null && !MyCheckUtils.isEmpty(paramExamInfoBean.getID())) {
            ((MainPresenter) this.mPresenter).getHomeSJDTK(this.bean_user.getUid(), paramExamInfoBean.getID());
        }
        this.flag_home = false;
    }

    @Override
    public void setModule(List<ModuleBean> paramList) {
    }

    @Override
    public void setTypeOne(List<VideoTypeOneBean> paramList) {
    }

    @Override
    public void setWork(List<ZhaoPinInfo.DataBean.ResponseBean.ZhaopinsBean> paramList,String url) {
    }
}
