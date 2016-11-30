package com.shannon.xiaoci;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shannon.xiaoci.main.model.WordModel;

/**
 * Created by Shannon on 2016/4/10.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        init();

        System.out.println("sd卡的路径：" + WordModel.DATABASE_PATH);





    }

    private void init() {

        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(ilc);

    }
}
