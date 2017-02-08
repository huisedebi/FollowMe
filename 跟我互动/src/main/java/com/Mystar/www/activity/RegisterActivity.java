package com.Mystar.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Mystar.www.R;
import com.Mystar.www.bean.UserInfoBean;
import com.Mystar.www.url.MyUrl;
import com.Mystar.www.util.AppUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/20.
 */

public class RegisterActivity extends BaseActivity {
    EditText ed_psw1, ed_psw2, ed_phone, ed_num;
    String num;//验证码
    Button bt_send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);
        initView();
        initData();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("注册");
        findViewById(R.id.tv_left).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
        ed_psw1 = (EditText) findViewById(R.id.ed_psw1);
        ed_psw2 = (EditText) findViewById(R.id.ed_psw2);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_num = (EditText) findViewById(R.id.ed_num);
        bt_send = (Button) findViewById(R.id.bt_send);
    }

    private void initData() {
    }

    /*  mobile	登录手机号码
       password 登录密码   //加密后的传上来  加密方式  md5(密码+‘huudon’)
       repassword 确认密码*/
    private void register() {
        String phone = ed_phone.getText().toString().trim();
        final String password1 = ed_psw1.getText().toString().trim();
        final String password2 = ed_psw2.getText().toString().trim();
        String code = ed_num.getText().toString().trim();

        if (!AppUtil.isPhone(phone)) {
            Toast.makeText(this, "请输入正确的手机号码！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password1.equals(password2)) {
            Toast.makeText(this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!code.equals("1234")) {
            Toast.makeText(this, "验证码不正确！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password1.equals("")) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject json = new JSONObject();
        try {
            json.put("userName", phone);
            json.put("jpushId", "");
            json.put("userPwd", AppUtil.getMD5(password1 + "ad"));
            json.put("code", "1234");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(MyUrl.REGISTER)
                .content(json.toString())
                .build()
                .execute(new Callback<UserInfoBean>() {
                    @Override
                    public UserInfoBean parseNetworkResponse(Response response) throws IOException {
                        String json = response.body().string();
                        return new Gson().fromJson(json, UserInfoBean.class);
                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(UserInfoBean response) {
                        if (response.getStatus() == 1) {
                            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "服务器繁忙！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.bt_register:
                register();
                break;
        }

    }
}
