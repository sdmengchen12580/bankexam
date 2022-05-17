package com.udit.bankexam.ui.exam_notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_notebook.ExamNoteBookPresenter;
import com.udit.bankexam.ui.exam_err.ExamErrTitleActivity;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_notebook.ExamNoteBookView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamNoteBookActivity extends BaseActivity<ExamNoteBookPresenter> implements ExamNoteBookView.View, View.OnClickListener, NoteBookAdapter.ExamListener {

    public static void startExamNoteBookActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ExamNoteBookActivity.class));
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_notebook);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("笔记题目");
    }


    private ImageView img_top_return;

    private TextView text_top_centent;

    private ListView listview_note_book;

    private List<ExamBean> mlist;

    private NoteBookAdapter adapter;

    @Override
    public void initListeners() {

        img_top_return.setOnClickListener(this);
    }

    private UserBean bean_user;

    @Override
    public void initData() {
        mPresenter = new ExamNoteBookPresenter(this);
        bean_user = SaveUtils.getUser(this);
        mlist = new ArrayList<>();
        adapter = new NoteBookAdapter(mlist, this, this);
        listview_note_book.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        UserBean bean_user = SaveUtils.getUser(this);
        mPresenter.getNotes(this, bean_user.getUid());
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
    public void setNotes(List<ExamBean> list) {
        mlist.clear();
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
        }
        adapter.notifyDataSetChanged();


    }

    @Override
    public void setNote(ExamNoteBean bean) {
        if (bean != null) {
            ExamUtils.saveNote(bean);
        }
    }

    @Override
    public void setExamStart(ExamBean bean) {
        mPresenter.getNote(this, bean_user.getUid(), bean.getID());
        // MyLogUtils.e(TAG,"setOnItemClickListener");
        ArrayList<ExamBean> list = new ArrayList<>();
        list.add(bean);
        String ids = ExamUtils.getExamBeanList(list);
        if (!MyCheckUtils.isEmpty(ids)) {
            ExamErrTitleActivity.startExamErrTitleActivity(this, null, "搜索", ids, false, false);
        } else {
            showLongToast("暂无此题解析");
        }
    }
}
