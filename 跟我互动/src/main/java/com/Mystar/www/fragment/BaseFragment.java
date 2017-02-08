package com.Mystar.www.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Mystar.www.impl.MyCallBack;
import com.Mystar.www.impl.MyItemClickListener;


/**
 * Created by Administrator on 2016/11/9.
 */

public abstract class BaseFragment  extends Fragment implements MyItemClickListener,MyCallBack,View.OnClickListener {
    public Activity mActivity;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        View view = inflater.inflate(getLayout(), container, false);
        initView(view);
        initData();
        return view;
    }
    /**
     * 得到布局
     */
    public abstract int getLayout();

    /**
     * 初始化控件
     *
     * @param view 布局对象
     */
    public abstract void initView(View view);

    /**
     * 初始化数据
     */
    public abstract void initData();


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
}
