package com.Mystar.www.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.Mystar.www.impl.MyCallBack;
import com.Mystar.www.impl.MyItemClickListener;

/**
 * Created by Administrator on 2016/12/20.
 */

public class BaseActivity extends AppCompatActivity implements MyCallBack,MyItemClickListener,View.OnClickListener{
    @Override
    public void onCallBack(Object data) {

    }

    @Override
    public void onCallBack(String jump, Object data) {

    }

    @Override
    public void onMyItemClick() {

    }

    @Override
    public void onMyItemClick(Object data) {

    }

    @Override
    public void onMyItemClick(Object data, int position) {

    }

    @Override
    public void onMyItemClick(String jump, int position) {

    }

    @Override
    public void onMyItemClick(String jump, String id) {

    }

    @Override
    public void onMyItemClick(String jump, String store_id, String id) {

    }

    @Override
    public void onClick(View v) {

    }
}
