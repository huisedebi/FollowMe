package com.Mystar.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Mystar.www.R;
import com.Mystar.www.bean.UserInfoBean;
import com.Mystar.www.url.MyUrl;
import com.Mystar.www.util.AppUtil;
import com.Mystar.www.util.ShpfUtil;
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

public class LoginActivity extends BaseActivity {
    TextView tv_right;
    EditText ed_phone, ed_psw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        findViewById(R.id.bt_login).setOnClickListener(this);
    }

    private void initView() {
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setText("注册");
        tv_right.setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText("登录");
        findViewById(R.id.tv_left).setOnClickListener(this);
        ed_psw = (EditText) findViewById(R.id.ed_psw);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
    }

    private void initData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_left:
                finish();
                break;
            case R.id.bt_login:
                login();
                break;
        }

    }
   /* jpushId    极光id
    userName    用户名
    userPwd     用户密码  加密后的传上来 采用二重加密   示例:md5(md5(用户密码)+"ad")
    lng lat*/

    private void login() {
        String phone = ed_phone.getText().toString().trim();
        if (!AppUtil.isPhone(phone)) {
            Toast.makeText(this, "请输入正确的手机号码！", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject json = new JSONObject();
        try {
            json.put("userName", ed_phone.getText().toString().trim());
            json.put("jpushId", "");
            json.put("userPwd", AppUtil.getMD5(AppUtil.getMD5(ed_psw.getText().toString().trim() + "ad")));
            json.put("lng", "");
            json.put("lat", "");
            Log.i("password", "register: " + AppUtil.getMD5(AppUtil.getMD5(ed_psw.getText().toString().trim() + "ad")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(MyUrl.LOGIN)
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
                        Log.i("onError", "onError: " + e.toString());
                    }

                    @Override
                    public void onResponse(UserInfoBean response) {
                        Toast.makeText(LoginActivity.this, response.getInfo(), Toast.LENGTH_SHORT).show();
                        ShpfUtil.setObject(ShpfUtil.LOGIN, response);
                        finish();
                        if (response.getStatus() == 1) {
                            finish();
                        }
                    }
                });
    }
}
