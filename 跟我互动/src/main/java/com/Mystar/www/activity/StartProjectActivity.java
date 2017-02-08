package com.Mystar.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.Mystar.www.R;


public class StartProjectActivity extends BaseActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqi);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        findViewById(R.id.tv_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText("发起项目");
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
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
