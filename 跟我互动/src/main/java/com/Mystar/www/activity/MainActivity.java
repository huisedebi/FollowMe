package com.Mystar.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Mystar.www.R;
import com.Mystar.www.fragment.MainFragment;
import com.Mystar.www.fragment.MineFragment;
import com.Mystar.www.util.AppUtil;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    private int index;//fragmentList的下标，当前片段的位置
    public TextView tv_tab;
    public TextView tv_ta01;
    private TextView mine_tab;
    private TextView dna;
    private TextView od_tab;
    private TextView cz_tab;
    public List<String > tag_list = new ArrayList<>();
    public LinearLayout ly;


    private static MainFragment mainFragment;
    private static MainFragment dnaTest1Fragment1;
    private static MainFragment orderFragment;
    private static MineFragment mineFragment;
    public LinearLayout ly_bottom_bar;
    private boolean isSelect;
    private FragmentManager fManager;
    public boolean isSelect() {
        return isSelect;
    }
    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
    private boolean close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fManager = getSupportFragmentManager();
        super.onCreate(savedInstanceState);
       /* getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);
        ly = (LinearLayout) findViewById(R.id.ly_bottom_bar);
        if (savedInstanceState != null) {
            inItViewFragment(true);
        }else{
            inItViewFragment(false);
        }
        inItView();


    }

    private void inItViewFragment(boolean b) {
        tag_list.add("Main");
        tag_list.add("classify");
        tag_list.add("cart");
        tag_list.add("mine");
        if(b){
            mainFragment = (MainFragment) fManager.findFragmentByTag("main");
            fragmentList.add(mainFragment);
            dnaTest1Fragment1 = (MainFragment) fManager.findFragmentByTag("classify");
            fragmentList.add(dnaTest1Fragment1);
            orderFragment = (MainFragment) fManager.findFragmentByTag("order");
            fragmentList.add(orderFragment);
            mineFragment = (MineFragment) fManager.findFragmentByTag("mine");
            fragmentList.add(mineFragment);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(dnaTest1Fragment1).hide(mineFragment).hide(orderFragment).show(mainFragment);
        }else{
            // 首页
            mainFragment = new MainFragment();
            // 定制(改成DNA)
            dnaTest1Fragment1 = new MainFragment();
            // 订单
            orderFragment = new MainFragment();
            // 我的
            mineFragment = new MineFragment();

            fragmentList.add(mainFragment);
            fragmentList.add(dnaTest1Fragment1);
            fragmentList.add(orderFragment);
            fragmentList.add(mineFragment);
            /**
             * 默认显示首页片段
             */

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.content, mainFragment, "Main");
            fragmentTransaction.commitAllowingStateLoss();
            index = 0;//初始化选中0
        }
    }


    public void inItView() {

        tv_tab = (TextView) findViewById(R.id.tv_tab01);
        tv_ta01 = (TextView) findViewById(R.id.tv_tab01);
        od_tab = (TextView) findViewById(R.id.tv_tab03);
        tv_tab.setSelected(true);
        dna = (TextView) findViewById(R.id.tv_tab02);
        cz_tab = (TextView) findViewById(R.id.tv_tab04);
        ly_bottom_bar =(LinearLayout)findViewById(R.id.ly_bottom_bar);
        findViewById(R.id.img_startproject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StartProjectActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }


    /**
     * 切换首页片段
     * 在布局里面设置了onClick
     *
     * @param v 点击到的tab
     */
    @SuppressWarnings("unused")
    public void changeFragment(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        String fragmentTag = "";
        //点击前的tab设置selected为false
        tv_tab.setSelected(false);
        //当前tab
        v.setSelected(true);
        //把当前tab存到tv里面
        tv_tab = (TextView) v;
        switch (v.getId()) {
            case R.id.tv_tab01:
                if (index == 0) {
                    return;
                }
                index = 0;
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    fragmentList.set(0, mainFragment);
                }
                fragmentTag = "main";
                tag_list.set(0,fragmentTag);
                break;
            case R.id.tv_tab02:
                if (index == 1) {
                    return;
                }
                index = 1;
                if (dnaTest1Fragment1 == null) {
                    dnaTest1Fragment1 = new MainFragment();
                    fragmentList.set(1, dnaTest1Fragment1);
                }
                fragmentTag = "dna";
                tag_list.set(1,fragmentTag);
                break;
            case R.id.tv_tab03:
                if (index == 2) {
                    return;
                }
                index = 2;
                if (orderFragment == null) {
                    orderFragment = new MainFragment();
                    fragmentList.set(2, orderFragment);
                }
                fragmentTag = "order";
                tag_list.set(2,fragmentTag);
                break;
            case R.id.tv_tab04:
                if(AppUtil.need_to_login(MainActivity.this)){
                    return;
                }
                if (index == 3) {
                    return;
                }
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    fragmentList.set(3, mineFragment);
                }
                fragmentTag = "mine";
                index = 3;
                tag_list.set(3,fragmentTag);
                break;
            default:
                break;
        }

        //0首页mainFragment、1专属limitFragment、2定制dnaTest1Fragment1、3订单orderFragment、4我的mineFragment
        if (!fragmentList.get(index).isAdded()) { // 先判断是否被add过
            transaction.add(R.id.content, fragmentList.get(index),tag_list.get(index)); // 隐藏当前的fragment，add下一个到Activity中
        }
        if (index == 0) {//首页
            transaction.hide(dnaTest1Fragment1).hide(mineFragment).hide(orderFragment).show(mainFragment);
        } else if (index == 1) {//DNA
            transaction.hide(mainFragment).hide(mineFragment).hide(orderFragment).show(dnaTest1Fragment1);
        } else if (index == 2) {//订单
            transaction.hide(mainFragment).hide(mineFragment).hide(dnaTest1Fragment1).show(orderFragment);
        } else if (index == 3) {//充值
            transaction.hide(mainFragment).hide(dnaTest1Fragment1).hide(orderFragment).show(mineFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //【关于性能优化】后期可以拓展低内存或者应用重启的状态反馈
        //到时候参考：http://www.cnblogs.com/android-joker/p/4414891.html
        if (index == 0) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //JPushInterface.onResume(this);
        if (getIntent().getStringExtra("from") != null && getIntent().getStringExtra("from").equals("DNATEST")) {
            changeFragment(findViewById(R.id.tv_tab01));
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // JPushInterface.onPause(this);
    }

 /*   @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (close) {
                finish();
            } else {
                Toast.makeText(this, "再按一次返回键退出喔~", Toast.LENGTH_SHORT).show();
            }
            close = true;
            tv_tab.postDelayed(new Runnable() {
                @Override
                public void run() {
                    close = false;
                }
            }, 2000);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }*/




}
