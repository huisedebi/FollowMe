package com.Mystar.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Mystar.www.R;
import com.Mystar.www.fragment.MainFragment;
import com.Mystar.www.fragment.MineFragment;

public class MainReActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] tabsItem = new String[]{
            "首页",
            "发现",
            "发现",
            "购物车",
            "我的"
    };
    private Class[] fragment = new Class[]{
            MainFragment.class,
            MainFragment.class,
            MainFragment.class,
            MainFragment.class,
            MineFragment.class
    };
    private int[] imgRes = new int[]{
            R.drawable.ic_home_selector,
            R.drawable.ic_classify_selector,
            R.drawable.ic_cart_selector,
            R.drawable.ic_cart_selector,
            R.drawable.ic_mime_selector
    };
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_re);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtab);
        for (int i = 0; i < tabsItem.length; i++) {
            View inflate = null;
            if (i==2){
                inflate = getLayoutInflater().inflate(R.layout.tabs_item_3, null);
                inflate.findViewById(R.id.img_startproject).setOnClickListener(this);
            }else {
                inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
                TextView tabs_text = (TextView) inflate.findViewById(R.id.tabs_text);
                ImageView tabs_img = (ImageView) inflate.findViewById(R.id.tabs_img);
                tabs_text.setText(tabsItem[i]);
                tabs_img.setImageResource(imgRes[i]);
            }
            mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_startproject:
                Intent intent = new Intent(MainReActivity.this,StartProjectActivity.class);
                startActivity(intent);
                break;
        }
    }
}
