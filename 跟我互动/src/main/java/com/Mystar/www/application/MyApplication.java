package com.Mystar.www.application;

import android.app.Application;
import android.content.Context;

import com.Mystar.www.manager.ModelManager;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 初始化
 * Created by Administrator on 2015/12/31.
 */
public class MyApplication extends Application {
    static MyApplication application;
    private static MyApplication mApplication = null;
    /**
     * 保存所有Activity页面
     */
    /**
     * 通知数量
     **/
    private static Context appContext;

    public static MyApplication getAppplication() {
        if (application != null) {
            return application;
        } else {
            application = new MyApplication();
            return application;
        }

    }

    public static MyApplication getInstance() {
        if (mApplication == null) {
            mApplication = new MyApplication();
        }
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ModelManager
        ModelManager.getInstance().initialize(this);
        initImageLoader(this);
    }


    private void initImageLoader(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .threadPoolSize(5).build();
        ImageLoader.getInstance().init(config);
    }
}
