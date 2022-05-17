package com.udit.bankexam.ui.exam;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.exam.ExamJiuCuoPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.exam.ExamJiuCuoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

/**
 * Created by zb on 2017/6/26.
 */

public class JiuCuoActivity extends BaseActivity<ExamJiuCuoPresenter> implements ExamJiuCuoView.View, View.OnClickListener {

    public static void startJiuCuoActivity(BaseActivity<?> mActivity,String ID)
    {
        Intent intent = new Intent(mActivity,JiuCuoActivity.class);
        intent.putExtra("ID",ID);

        mActivity.startActivity(intent);

    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private EditText edit_jiucuo;

    private TextView textview_jiucuo_btn;

    private UserBean bean_user;

    private String ID;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_jiucuo);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
        text_top_centent.setText("纠错");
        edit_jiucuo.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        // 文本显示的位置在 EditText的最上方
        edit_jiucuo.setGravity(Gravity.TOP);
        // 改变默认的单行模式
        edit_jiucuo.setSingleLine(false);
        // 水平滚动设置为False
        edit_jiucuo.setHorizontallyScrolling(false);
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        textview_jiucuo_btn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter= new ExamJiuCuoPresenter(this);

        bean_user = SaveUtils.getUser(this);

        ID =  getIntent().getStringExtra("ID");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_top_return:
                finish();
                break;
            case R.id.textview_jiucuo_btn:
                String text = edit_jiucuo.getText().toString();
                if(!MyCheckUtils.isEmpty(text))
                {
                    mPresenter.jiucuo(this,bean_user.getUid(),ID,text);
                }
                else
                {
                    showLongToast("纠错内容不能为空");
                }

                break;
        }
    }

    @Override
    public void setJiCuo(boolean flag) {
        if(flag)
        {
            showLongToast("纠错提交成功");
            finish();
        }
        else
        {
            showLongToast("纠错提交失败");
        }
    }
}
