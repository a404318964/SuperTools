package com.zwj.supertools.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.zwj.customview.ClearWriteEditText;
import com.zwj.supertools.MyApplication;
import com.zwj.supertools.R;
import com.zwj.supertools.constant.Constant;
import com.zwj.supertools.constant.UrlConstant;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;
import com.zwj.zwjutils.SPUtil;
import com.zwj.zwjutils.ToastUtil;
import com.zwj.zwjutils.net.bean.RequestBean;
import com.zwj.zwjutils.net.bean.ResponseBean;
import com.zwj.zwjutils.net.callback.SimpleCallBack;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseAutoLayoutCommonActivity {
    private ClearWriteEditText etAccount, etPassword;
    private Button btnLogin;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void findViews() {
        etAccount = getView(R.id.et_account);
        etPassword = getView(R.id.et_password);
        btnLogin = getView(R.id.btn_login);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if(!TextUtils.isEmpty(MyApplication.getToken())) {
            startActivity(new Intent(mContext,
                    MainActivity.class));
            finish();
        }
    }

    @Override
    protected void setListener() {

        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(account)) {
            ToastUtil.toast(MyApplication.getGlobalContext(), "用户名不能为空");
            return;
        }

        if(TextUtils.isEmpty(password)) {
            ToastUtil.toast(MyApplication.getGlobalContext(), "密码不能为空");
            return;
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RequestBean(UrlConstant.URL_LOGIN, RequestBean.METHOD_POST)
                        .addParam("username", account)
                        .addParam("password", password)
                        .setCallback(new SimpleCallBack() {
                            @Override
                            public void onSuccess(ResponseBean responseBean) {
                                try {
                                    JSONObject jsonObject = new JSONObject(responseBean.getResult());
                                    MyApplication.setToken(jsonObject.optString(Constant.TOKEN));

                                    SPUtil.putString(MyApplication.getGlobalContext(), Constant.SP_KEY_ACCOUNT, account);
                                    SPUtil.putString(MyApplication.getGlobalContext(), Constant.SP_KEY_PWD, password);

                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .request(MyApplication.getGlobalContext());
            }
        });
    }
}
