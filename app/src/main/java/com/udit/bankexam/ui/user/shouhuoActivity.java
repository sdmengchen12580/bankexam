package com.udit.bankexam.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.AddressBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.user.shouhuoPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.user.shouhuoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

/**
 * Created by zb on 2017/7/3.
 */

public class shouhuoActivity extends BaseActivity<shouhuoPresenter> implements shouhuoView.View, View.OnClickListener {

    public static void startshouhuoActivity(BaseActivity<?> mActivity, AddressBean bean) {

        Intent intent = new Intent(mActivity, shouhuoActivity.class);
        if (bean != null) {
            intent.putExtra("address", bean);
        }

        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_shouhuo);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private EditText edit_address, edit_name, edit_phone;

    private Button btn_tj;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        this.bean_user = SaveUtils.getUser(this);
        text_top_centent.setText("编辑地址");
    }

    @Override
    public void initListeners() {

        img_top_return.setOnClickListener(this);


        btn_tj.setOnClickListener(this);
    }

    private UserBean bean_user;

    @Override
    public void initData() {
        mPresenter = new shouhuoPresenter(this);
        bean_user = SaveUtils.getUser(this);
        Object obj_bean = getIntent().getSerializableExtra("address");
        if (obj_bean != null && obj_bean instanceof AddressBean) {
            AddressBean bean = (AddressBean) obj_bean;
            if (!MyCheckUtils.isEmpty(bean.getName())) {
                edit_name.setText(bean.getName());
            } else
                edit_name.setText("");

            if (!MyCheckUtils.isEmpty(bean.getAddr())) {
                edit_address.setText(bean.getAddr());
            } else {
                edit_address.setText("");
            }

            if (!MyCheckUtils.isEmpty(bean.getTel())) {
                edit_phone.setText(bean.getTel());
            } else {
                edit_phone.setText("");
            }
        } else {
            edit_address.setText("");
            edit_name.setText("");
            edit_phone.setText("");
            ((shouhuoPresenter) this.mPresenter).doGetAddr(this, this.bean_user.getUid());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tj:
                String str1 = this.edit_address.getText().toString();
                String str2 = this.edit_name.getText().toString();
                String str3 = this.edit_phone.getText().toString();
                if (MyCheckUtils.isEmpty(str1.trim())) {
                    showLongToast("请输入地址");
                    return;
                }
                if (MyCheckUtils.isEmpty(str2.trim())) {
                    showLongToast("请输入姓名");
                    return;
                }
                if (str3.length() != 11) {
                    showLongToast("请输入正确位数的手机号");
                    return;
                }
                ((shouhuoPresenter) this.mPresenter).doPutAddr(this, this.bean_user.getUid(), str2, str3, "", "", "", str1);
                break;
            case R.id.img_top_return:
                finish();
                break;
        }
    }

    @Override
    public void putAddrOk() {
        finish();
    }

    @Override
    public void putAddErr() {
        showLongToast("收货地址保存失败");
    }


    public void getAddr(AddressBean paramAddressBean) {
        boolean bool;
        if (getIntent() != null && getIntent().getSerializableExtra("address") != null) {
            bool = true;
        } else {
            bool = false;
        }
        if (paramAddressBean != null && !bool) {
            if (!MyCheckUtils.isEmpty(paramAddressBean.getName())) {
                this.edit_name.setText(paramAddressBean.getName());
            } else {
                this.edit_name.setText("");
            }
            if (!MyCheckUtils.isEmpty(paramAddressBean.getAddr())) {
                this.edit_address.setText(paramAddressBean.getAddr());
            } else {
                this.edit_address.setText("");
            }
            if (!MyCheckUtils.isEmpty(paramAddressBean.getTel())) {
                this.edit_phone.setText(paramAddressBean.getTel());
                return;
            }
            this.edit_phone.setText("");
        }
    }
}
