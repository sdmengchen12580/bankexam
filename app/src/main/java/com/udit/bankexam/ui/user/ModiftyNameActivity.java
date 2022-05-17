package com.udit.bankexam.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.user.ModiftyNamePresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.user.ModiftyNameView;
import com.udit.bankexam.view.user.ModiftyPwdView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDataUtils;
import com.udit.frame.utils.ViewUtils;

/**
 * Created by zb on 2017/6/6.
 */

public class ModiftyNameActivity extends BaseActivity<ModiftyNamePresenter> implements ModiftyNameView.View, View.OnClickListener {

    public static void startModiftyNameActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ModiftyNameActivity.class));

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_modiftyname);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("昵称");
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private EditText edit_modifty_name;

    private Button btn_cz;

    private UserBean bean_user;

    private ImageView img_delect;

    @Override
    public void initListeners() {
        this.img_top_return.setOnClickListener(this);
        this.btn_cz.setOnClickListener(this);
        this.img_delect.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new ModiftyNamePresenter(this);

        bean_user = SaveUtils.getUser(this);
        if (!MyCheckUtils.isEmpty(bean_user.getPet())) {
            edit_modifty_name.setText(bean_user.getPet());
            this.img_delect.setVisibility(View.VISIBLE);
        } else {
            edit_modifty_name.setText("");
        }
        this.edit_modifty_name.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
            }

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            }

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
                param1CharSequence = ModiftyNameActivity.this.edit_modifty_name.getText().toString().trim();
                ImageView imageView = ModiftyNameActivity.this.img_delect;
                if (param1CharSequence.equals("")) {
                    param1Int1 = 8;
                } else {
                    param1Int1 = 0;
                }
                imageView.setVisibility(param1Int1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.img_delect:
                this.edit_modifty_name.setText("");
                break;
            case R.id.btn_cz:
                String name = edit_modifty_name.getText().toString();
                if (!MyCheckUtils.isEmpty(name)) {
                    mPresenter.modiftyName(this, bean_user.getUid(), name);
                } else {
                    showLongToast("昵称不能为空");
                }
                break;
        }
    }

    @Override
    public void modiftySuccess(String pet) {
        SaveUtils.deleteUser(this);
        SaveUtils.saveUser(this, this.bean_user.getUid(), this.bean_user.getMobile(), this.bean_user.getPass(), pet, "", "", "");
        showLongToast("修改昵称成功");
        if (!((String) SpUtil.get(this, "imgurl", "")).equals("")) {
            SpUtil.put(this, "name", pet);
        }
        onLowMemory();
        finish();
    }

    @Override
    public void modiftyErr(String msg) {
        if (!MyCheckUtils.isEmpty(msg)) {
            showLongToast(msg);
        } else
            showLongToast("修改昵称失败");
    }
}
