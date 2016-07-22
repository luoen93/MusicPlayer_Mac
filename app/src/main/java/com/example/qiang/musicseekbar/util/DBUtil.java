package com.example.qiang.musicseekbar.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.qiang.musicseekbar.DataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Qiang on 2016/6/21.
 */
public class DBUtil extends Activity {
    //每次进入时候读取的音乐列表
    public static List<Map<String, Object>> BaseMusicList(Context context) {
        DataBaseHelper myHelper = new DataBaseHelper(context);
        SQLiteDatabase db = myHelper.getWritableDatabase();
        //搜索操作
        Cursor cursor = db.query("music_info", null, null, null, null, null, "_id asc");
        //初始化一个数组
        List<Map<String, Object>> mblist = new ArrayList<Map<String, Object>>();
        //moveToFirst：移动到第一位数据
        //isAfterLast：判断是否为最后一位
        //moveToNext：往后移动一位
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            //曲名
            String title = cursor.getString(cursor.getColumnIndex("m_title"));
            //歌手
            String artist = cursor.getString(cursor.getColumnIndex("m_artist"));
            //专辑ID号，用于获取专辑图片
            String image = cursor.getString(cursor.getColumnIndex("m_image"));
            //歌曲所在位置
            String url = cursor.getString(cursor.getColumnIndex("m_url"));
            //歌曲时长
            int duration = cursor.getInt(cursor.getColumnIndex("m_duration"));
            //通过专辑ID，获取图片
//            String image = getAlbumArt(album_id, context);
            //转换数据从int的毫秒转换成xx:xx的string形式
            String mtime = formatTimeFromProgress(duration);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", title);
            map.put("artist", artist);
            map.put("images", image);
            map.put("url", url);
            map.put("duration", duration);
            map.put("time", mtime);
            mblist.add(map);

        }

        return mblist;
    }

    public static boolean InsertMusic(DataBaseHelper myHelper, String mtetle) {
        boolean isdouble = false;
        //获取数据库对象
        SQLiteDatabase db = myHelper.getWritableDatabase();
        //搜索操作
        Cursor cursor = db.query("music_info", null, null, null, null, null, "_id asc");
        //获取书本数量
        int num = cursor.getCount();
        //初始化一个数组
        String res[] = new String[num];
        //moveToFirst：移动到第一位数据
        //isAfterLast：判断是否为最后一位
        //moveToNext：往后移动一位
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("m_title"));
            //getPosition：返回数据当前行数
            res[cursor.getPosition()] = title;
            if (res[cursor.getPosition()] == mtetle) {
                isdouble = true;
                break;
            }
        }
        //返回数组
        return isdouble;
    }

    //点击从sd卡获取所有音频文件
    public static List<Map<String, Object>> musicrs(Context context,Activity activity) {
        DataBaseHelper dbhelper = new DataBaseHelper(context);
        List<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
        int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 0;
        //判断是否有读取权限6.0新加特性
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//        }

        Cursor cr = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        cr.getCount();
        for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
            String title = cr.getString(cr.getColumnIndex(MediaStore.Audio.Media.TITLE));

            if (InsertMusic(dbhelper, title) == true) {
                continue;
            } else {

                String album = cr.getString(cr.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cr.getString(cr.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                int album_id = cr.getInt(cr.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                String url = cr.getString(cr.getColumnIndex(MediaStore.Audio.Media.DATA));
                int duration = cr.getInt(cr.getColumnIndex(MediaStore.Audio.Media.DURATION));

                String image = getAlbumArt(album_id, context);
                //
                if (image == null) {
                    continue;
                }
                String mtime = formatTimeFromProgress(duration);

                if (duration < 5000 || image == null) {
                    image = "default";
                }
                Log.i("=====", title + "||" + duration);


                Map<String, Object> map = new HashMap<String, Object>();
                map.put("title", title);
                map.put("album", album);
                map.put("artist", artist);
                map.put("images", image);
                map.put("url", url);
                map.put("duration", duration);
                map.put("time", mtime);
                mlist.add(map);
                //插入
                insertData(dbhelper, map);
            }
        }

        return mlist;
    }

    public static void insertData(DataBaseHelper myHelper, Map<String, Object> map) {
        String music_title = map.get("title").toString();
        String music_album = map.get("album").toString();
        String music_artist = map.get("artist").toString();
        String music_image = map.get("images").toString();
        String music_url = map.get("url").toString();
        String music_duration = map.get("duration").toString();
        String music_time = map.get("time").toString();
        //获取数据库对象
        SQLiteDatabase db = myHelper.getWritableDatabase();
        //从数据库查看已有图书数据
        Cursor cursor = db.query("music_info", null, "m_title = ?", new String[]{music_title}, null, null, "_id asc");
        //如果已有显示拥有此书
        if (cursor.getCount() > 0) {
            // do nothing
        } else {
            //使用execSQL方法向表中插入数据
            db.execSQL("insert into music_info(m_title,m_album,m_artist,m_image,m_url,m_duration,m_time) values('"

                    + music_title + "','"
                    + music_album + "','"
                    + music_artist + "','"
                    + music_image + "','"
                    + music_url + "','"
                    + music_duration + "','"
                    + music_time + "');");

            Log.i("Insert", "=========");
            db.close();

        }
    }

    //根据地址信息，获取专辑封面
    private static String getAlbumArt(int album_id, Context context) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = context.getContentResolver().query(Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)),
                projection, null, null, null);

        String album_art = null;

        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
//        cur = null;
        return album_art;
    }

    private static String formatTimeFromProgress(int progress) {
        // 总的秒数
        int msecTotal = progress / 1000;
        int min = msecTotal / 60;
        int msec = msecTotal % 60;
        String minStr = min < 10 ? "0" + min : "" + min;
        String msecStr = msec < 10 ? "0" + msec : "" + msec;
        return minStr + ":" + msecStr;
    }
}
