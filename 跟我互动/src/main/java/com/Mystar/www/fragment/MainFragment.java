package com.Mystar.www.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Mystar.www.R;
import com.Mystar.www.activity.LoginActivity;
import com.Mystar.www.adapter.MainAdapter;
import com.Mystar.www.bean.IndexBean;
import com.Mystar.www.bean.UserInfoBean;
import com.Mystar.www.url.MyUrl;
import com.Mystar.www.util.AppUtil;
import com.Mystar.www.util.ShpfUtil;
import com.Mystar.www.view.FullyLinearLayoutManager;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/11/9.
 */

public class MainFragment extends BaseFragment {
    private MainAdapter adapter;
    private RecyclerView recycler;
    String type = "";
    int pageNum = 1;

    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        adapter = new MainAdapter(getActivity());
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        post_main();
    }

    /*  请求的参数
      uid        用户id
      type       订单状态
      p          请求的页数*/
    private void post_main() {
      /*  if (AppUtil.need_to_login(getActivity())) {
            return;
        }*/
       // UserInfoBean user = ShpfUtil.getObject(ShpfUtil.LOGIN);
        JSONObject json = new JSONObject();
        try {
            json.put("uid", "");
            json.put("type", type);
            json.put("p", pageNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(MyUrl.INDEX)
                .content(json.toString())
                .build()
                .execute(new Callback<IndexBean>() {
                    @Override
                    public IndexBean parseNetworkResponse(Response response) throws IOException {
                        String json = response.body().string();
                        return new Gson().fromJson(json, IndexBean.class);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Log.i("onError", "onError: " + e.toString());
                    }

                    @Override
                    public void onResponse(IndexBean response) {
                        if(response.getStatus()==1){
                            adapter.setData(response);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }
}

