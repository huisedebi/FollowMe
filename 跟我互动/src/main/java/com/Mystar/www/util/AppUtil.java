package com.Mystar.www.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.Toast;


import com.Mystar.www.R;
import com.Mystar.www.activity.LoginActivity;
import com.Mystar.www.bean.UserInfoBean;
import com.Mystar.www.manager.ModelManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/9.
 */

public class AppUtil {


    public static DisplayImageOptions getNormalImageOptions() {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img01)
                .showImageForEmptyUri(R.drawable.img01)
                .showImageOnFail(R.drawable.img01)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true).cacheOnDisk(true)
                .build();
    }


    public static String[] secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return null;
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return new String[]{"99", "99", "99"};
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                //timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return new String[]{unitFormat(hour), unitFormat(minute), unitFormat(second)};
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


    /**
     * 判断是不是11位手机号码
     *
     * @param inputText 输入的数字
     * @return
     */
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern
                .compile("^\\d{11}$");
//                .compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    /**
     * @param password 密码
     * @return String
     * 加密后的密码
     */
    public static String getMD5(String password) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuilder buf = new StringBuilder("");
            for (byte aB : b) {
                i = aB;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }


    public static boolean need_to_login(FragmentActivity mActivity) {
        UserInfoBean loginData = ShpfUtil.getObject(ShpfUtil.LOGIN);
        if (loginData == null) {
            Toast.makeText(mActivity, "请先登录", Toast.LENGTH_SHORT).show();
            mActivity.startActivity(new Intent(mActivity,
                    LoginActivity.class));
            return true;
        }return false;
    }
    // dip转像素
    public static int dipToPixels(float dip) {
        final float SCALE = ModelManager.getInstance().getContext().getResources()
                .getDisplayMetrics().density;
        float valueDips = dip;
        int valuePixels = (int) (valueDips * SCALE + 0.5f);
        return valuePixels;
    }

    // 像素转dip
    public static float pixelsToDip(int pixels) {

        final float SCALE = ModelManager.getInstance().getContext().getResources()
                .getDisplayMetrics().density;
        float dips = pixels / SCALE;
        return dips;
    }
    /**
     * 描述：判断网络是否有效.
     *
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) ModelManager.getInstance().getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public  static void showPic(String url, ImageView view){
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = AppUtil.getNormalImageOptions();
        imageLoader.displayImage(url,view,options);
    }



    public static String getUserId(){
        UserInfoBean loginData = ShpfUtil.getObject(ShpfUtil.LOGIN);
        return loginData.getUid();
    }

    //bitmap转为base64
    public static String bitmapToBase64(
            Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = android.util.Base64.encodeToString(bitmapBytes, android.util.Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            if (url.startsWith("http")) {
                URL iconUrl = new URL(url);
                URLConnection conn = iconUrl.openConnection();
                HttpURLConnection http = (HttpURLConnection) conn;
                int length = http.getContentLength();
                conn.connect();
                // 获得图像的字符流
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is, length);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();// 关闭流
            } else {
                File file = new File(url);
                if (file.exists()) {
                    bm = BitmapFactory.decodeFile(url);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    public static String getMD5Time(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
        return getMD5(time+"-ad");
    }

}