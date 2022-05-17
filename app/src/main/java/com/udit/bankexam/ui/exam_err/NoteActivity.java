package com.udit.bankexam.ui.exam_err;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam_err.NotePresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.utils.DBUtils;
import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam_err.NoteView;
import com.udit.frame.common.dialog.CustomDialog;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

/**
 * Created by zb on 2017/5/22.
 */

public class NoteActivity extends BaseActivity<NotePresenter> implements NoteView.View, View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private String mText;

    public static void startNoteActivity(BaseActivity<?> mActivity, String titleId, int result_ok) {
        Intent intent = new Intent(mActivity, NoteActivity.class);
        intent.putExtra("titleId", titleId);
        mActivity.startActivityForResult(intent, result_ok);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_note);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("笔记本");
        text_top_right.setText("提交");

        edit_note.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        // 文本显示的位置在 EditText的最上方
        edit_note.setGravity(Gravity.TOP);
        // 改变默认的单行模式
        edit_note.setSingleLine(false);
        // 水平滚动设置为False
        edit_note.setHorizontallyScrolling(false);

    }

    private ImageView img_top_return;

    private TextView text_top_centent, text_top_right;

    private EditText edit_note;

    private UserBean bean_user;

    private String titleId;

    private NoteBean mCurrentNote;

    private boolean flag_change = false;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        text_top_right.setOnClickListener(this);

        edit_note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                flag_change = true;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag_change = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                flag_change = true;
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new NotePresenter(this);
        bean_user = SaveUtils.getUser(this);
        titleId = getIntent().getStringExtra("titleId");
        String msg = ExamUtils.getExamNote(titleId);
        if (!MyCheckUtils.isEmpty(msg)) {
            edit_note.setText(msg);
        }
       /* mCurrentNote = DBUtils.getInstance().getNewSession().getNoteBeanDao()
                .queryBuilder().where(NoteBeanDao.Properties.TitleID.eq(titleId))
                .build().unique();
        if(mCurrentNote!=null && !MyCheckUtils.isEmpty(mCurrentNote.getNote()))
        {
            edit_note.setText(mCurrentNote.getNote());
        }
        else
        {
            edit_note.setText("");
        }*/

    }

    @Override
    public void setBJ(ExamNoteBean bean) {
        flag_change = false;
        //  DBUtils.getInstance().insertNote(bean);
        Intent intent = getIntent().putExtra("note", bean.getNote());

        ExamUtils.saveNote(bean);
        setResult(RESULT_OK, intent);
        showLongToast("笔记保存成功");
        finish();
    }

    @Override
    public void onBackPressed() {
        if (flag_change) {
            CustomDialog.Builder builder = new CustomDialog.Builder(this);
            builder.setMessage("笔记内容已经被修改，需要保存么？");
            builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                               /* dialog.dismiss();
                                finish();*/

                    dialog.dismiss();
                    String text = edit_note.getText().toString();
                    mPresenter.setBJ(bean_user.getUid(), titleId, text);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        final String text = edit_note.getText().toString();
        switch (v.getId()) {
            case R.id.img_top_return:
                if (flag_change) {
                    if (mCurrentNote != null) {
                        if (mCurrentNote.getNote() != null) {
                            if (!mCurrentNote.getNote().equals(text)) {
                                flag_change = true;
                            } else
                                flag_change = false;
                        } else {
                            if (!MyCheckUtils.isEmpty(text)) {
                                flag_change = true;
                            } else
                                flag_change = false;
                        }
                    } else {
                        if (!MyCheckUtils.isEmpty(text)) {
                            flag_change = true;
                        } else
                            flag_change = false;
                    }

                    if (flag_change) {
                        CustomDialog.Builder builder = new CustomDialog.Builder(this);
                        builder.setMessage("笔记内容已经被修改，需要保存么？");
                        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               /* dialog.dismiss();
                                finish();*/

                                dialog.dismiss();
                                mText = text;
                                mPresenter.setBJ(bean_user.getUid(), titleId, text);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    } else
                        finish();

                } else {
                    finish();
                }
                break;
            case R.id.text_top_right:

                if (MyCheckUtils.isEmpty(text)) {
                    showLongToast("笔记内容为空，无法进行提交");
                } else {
                    mPresenter.setBJ(bean_user.getUid(), titleId, text);
                }
                break;
        }
    }


}
