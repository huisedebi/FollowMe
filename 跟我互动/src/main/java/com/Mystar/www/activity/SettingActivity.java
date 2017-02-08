package com.Mystar.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.Mystar.www.R;


public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        findViewById(R.id.tv_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText("设置");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                finish();
                break;
        }
    }
}
