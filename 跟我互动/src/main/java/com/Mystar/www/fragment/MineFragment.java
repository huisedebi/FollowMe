package com.Mystar.www.fragment;
import android.content.Intent;
import android.view.View;
import com.Mystar.www.R;
import com.Mystar.www.activity.FeedbackActivity;
import com.Mystar.www.activity.PersonInfoActivity;
import com.Mystar.www.activity.SettingActivity;
import com.Mystar.www.activity.WalletActivity;

/**
 * Created by Administrator on 2016/11/9.
 */

public class MineFragment extends BaseFragment {

    @Override
    public int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        view.findViewById(R.id.img_setting).setOnClickListener(this);
        view.findViewById(R.id.layout_personinfo).setOnClickListener(this);
        view.findViewById(R.id.layout_wallet).setOnClickListener(this);
        view.findViewById(R.id.layout_feedback).setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.img_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.layout_personinfo:
                intent = new Intent(getActivity(), PersonInfoActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.layout_wallet:
                intent = new Intent(getActivity(), WalletActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.layout_feedback:
                intent = new Intent(getActivity(), FeedbackActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
