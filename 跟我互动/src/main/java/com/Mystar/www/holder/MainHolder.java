package com.Mystar.www.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Mystar.www.R;
import com.Mystar.www.view.MyGallery;

/**
 * Created by Administrator on 2016/11/12.
 */
public class MainHolder extends RecyclerView.ViewHolder {

    public MyGallery mygallery;
    public ImageView [] menu_imgs;
    public TextView [] menu_tvs;
    public MainHolder(View itemView, int viewType) {
        super(itemView);
        if(viewType==0){
            mygallery = (MyGallery) itemView.findViewById(R.id.mygallery);
            menu_imgs =new ImageView[]{(ImageView) itemView.findViewById(R.id.menu_img1),(ImageView) itemView.findViewById(R.id.menu_img2),(ImageView) itemView.findViewById(R.id.menu_img3),(ImageView) itemView.findViewById(R.id.menu_img4)};
            menu_tvs =new TextView[]{(TextView) itemView.findViewById(R.id.menu_tv1),(TextView) itemView.findViewById(R.id.menu_tv2),(TextView) itemView.findViewById(R.id.menu_tv3),(TextView) itemView.findViewById(R.id.menu_tv4)};
        }



    }
}
