package com.example.qiang.musicseekbar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Qiang on 2016/6/21.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    //每次安装会自动执行一次，创建数据库
    private static final String DB_NAME = "music.db"; //数据库名称
    private static final int DB_VERSION = 1; //数据库版本

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists music_info("
                + "_id integer primary key,"
                + "m_title varchar,"           //歌曲名
                + "m_album varchar,"           //歌曲专辑信息
                + "m_artist varchar,"
                + "m_image varchar,"         //歌曲专辑的封面，以bit形式
                + "m_url varchar,"            //歌曲所在地址url
                + "m_duration int,"      //歌曲所用时长
                + "m_time varchar);");     //歌曲时长的string形式XX:XX
        Log.i("==================", "-----------------------");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        更新数据库的操作
    }
}