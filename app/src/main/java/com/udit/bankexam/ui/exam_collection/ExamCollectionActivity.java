package com.udit.bankexam.ui.exam_collection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.exam_collection.ExamCollectionPresenter;
import com.udit.bankexam.ui.exam_err.ExamErrTitleActivity;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_collection.ExamCollectionView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 * 收藏题目
 */

public class ExamCollectionActivity extends BaseActivity<ExamCollectionPresenter> implements ExamCollectionView.View, View.OnClickListener, ExamCollectionAdapter.ExamListener {

    public static void startExamCollectionActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamCollectionActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_collection);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);

        text_top_centent.setText("收藏题目");
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_collection;

    private UserBean bean_user;

    private List<ExamBean> mlist;

    private ExamCollectionAdapter adapter;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new ExamCollectionPresenter(this);


        bean_user = SaveUtils.getUser(this);

        mlist = new ArrayList<>();
        adapter = new ExamCollectionAdapter(mlist, this, this);
        listview_collection.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    protected void onResume() {
        super.onResume();
        this.mlist.clear();
        this.adapter.notifyDataSetChanged();
        ((ExamCollectionPresenter) this.mPresenter).getCollection(this, this.bean_user.getUid());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();

                break;
        }
    }


    @Override
    public void setFavorite(List<FavoriteRecord> list) {
        if (list != null && list.size() > 0) {
            ArrayList<FavoriteRecord> list_zhineng = new ArrayList<>();
            ArrayList<FavoriteRecord> list_shiti = new ArrayList<>();

            for (FavoriteRecord record : list) {
                if (record.getFType().equals(Constant.DataType.TYPE_SHITI)) {
                    list_shiti.add(record);
                } else if (record.getFType().equals(Constant.DataType.TYPE_ZHINENG)) {
                    list_zhineng.add(record);
                }

            }
            String ids = ExamUtils.getExamIds(list_shiti);
            String ids_zhineng = ExamUtils.getExamIds(list_zhineng);
            if (!MyCheckUtils.isEmpty(ids)) {
                mPresenter.getExam(this, bean_user.getUid(), ids);
            }
            if (!MyCheckUtils.isEmpty(ids_zhineng)) {
                mPresenter.getExamZhineng(this, bean_user.getUid(), ids_zhineng);
            }

        } else
            showLongToast("暂无收藏题目");
    }

    @Override
    public void setExams(List<ExamBean> list, String type) {

        //  mlist.clear();
        if (list != null && list.size() > 0) {
            for (int i = 0; list != null && i < list.size(); i++) {
                ExamBean bean = list.get(i);
                boolean flag = false;
                if (type.equals(Constant.DataType.TYPE_ZHINENG)) {
                    flag = true;
                }
                bean.setFlag_zhineng(flag);
            }
            mlist.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setExamStart(ExamBean bean) {

        ArrayList<ExamBean> list = new ArrayList<>();
        list.add(bean);
        String ids = ExamUtils.getExamBeanList(list);
        if (!MyCheckUtils.isEmpty(ids)) {


            ExamErrTitleActivity.startExamErrTitleActivity(this, null, "收藏题目", ids, bean.getFlag_zhineng(), false);
        } else {
            showLongToast("暂无此题解析");
        }

    }
}
