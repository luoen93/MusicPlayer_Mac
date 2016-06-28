package com.example.qiang.musicseekbar.data;

import android.app.Application;

/**
 * Created by Qiang on 2016/6/27.
 */
public class MusicStatus extends Application {
    private boolean ISLOOP;

    public boolean getState() {
        return this.ISLOOP;
    }

    public void setState(boolean loop) {
        if (loop == true) {
            this.ISLOOP = false;
        } else {
            this.ISLOOP = true;
        }
    }

//    @Override
//    public void onCreate() {
//        ISLOOP = false;
//        super.onCreate();
//    }
}
