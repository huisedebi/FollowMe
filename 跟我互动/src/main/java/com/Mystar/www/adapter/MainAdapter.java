package com.Mystar.www.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import com.Mystar.www.R;
import com.Mystar.www.bean.IndexBean;
import com.Mystar.www.holder.MainHolder;
import com.Mystar.www.util.AppUtil;
import com.Mystar.www.util.ImageUtil;
import com.Mystar.www.view.MyGallery;

import java.util.TimerTask;


/**
 * Created by Administrator on 2016/11/11.
 */
public class MainAdapter extends RecyclerView.Adapter<MainHolder> {
    FragmentActivity mActivity;
    private int[] imageResIDs;
    private int index = 0;
    private final int AUTOPLAY = 2;
    private MyGallery gallery;
    IndexBean data;


    public MainAdapter(FragmentActivity activity) {
        mActivity = activity;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View view;
        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.item_fragment_star_one, parent, false);
        } else {
            view = layoutInflater.inflate(R.layout.item_fragment_star_two, parent, false);
        }
        return new MainHolder(view, viewType);

    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        if (position == 0) {
            gallery = holder.mygallery;
            setGallery();
            for (int i = 0; i < data.getMenu().size()&&i != 4; i++) {

              AppUtil.showPic(data.getMenu().get(i).getImg(),holder.menu_imgs[i]);
                holder.menu_tvs[i].setText(data.getMenu().get(i).getName());
                holder.menu_imgs[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        } else {

        }
    }

    private void setGallery() {
        imageResIDs = new int[]{R.drawable.banner, R.drawable.img01, R.drawable.banner, R.drawable.img01,
                R.drawable.banner, R.drawable.img01,};
        ImageAdapter adapter = new ImageAdapter();
        gallery.setAdapter(adapter);
        gallery.setSpacing(10); //图片之间的间距
        gallery.setSelection((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % imageResIDs.length);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 设置点击事件监听
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mActivity, "当前位置position:" + position + "的图片被选中了", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (data == null || data.getData() == null) {
            return 0;
        }
        return data.getData().size() + 1;
    }


    /**
     * 定时器，实现自动播放
     */
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = AUTOPLAY;
            index = gallery.getSelectedItemPosition();
            index++;
            handler.sendMessage(message);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTOPLAY:
                    gallery.setSelection(index);
                    break;
                default:
                    break;
            }
        }
    };

    public void setData(IndexBean response) {
        data = response;
        notifyDataSetChanged();
    }

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//用于循环滚动
        }

        @Override
        public Object getItem(int position) {
            if (position >= imageResIDs.length) {
                position = position % imageResIDs.length;
            }

            return position;
        }

        @Override
        public long getItemId(int position) {
            if (position >= imageResIDs.length) {
                position = position % imageResIDs.length;
            }

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView;
            if (convertView != null) {
                imageView = (ImageView) convertView;
            } else {
                imageView = new ImageView(mActivity);
            }

            if (position >= imageResIDs.length) {
                position = position % imageResIDs.length;
            }

            Bitmap bitmap = ImageUtil.getImageBitmap(mActivity.getResources(),
                    imageResIDs[position]);
            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            drawable.setAntiAlias(true); // 消除锯齿
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            LayoutParams params = new LayoutParams(240, 320);
            imageView.setLayoutParams(params);
            return imageView;
        }
    }
}