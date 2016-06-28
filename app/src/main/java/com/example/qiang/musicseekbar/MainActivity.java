package com.example.qiang.musicseekbar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.qiang.musicseekbar.adapter.MusicAdapter;
import com.example.qiang.musicseekbar.util.AlbumDealUtil;
import com.example.qiang.musicseekbar.util.DBUtil;

public class MainActivity extends AppCompatActivity {
//
//
//
//    //横向滚动条
//    private ImageView mTabline;
//    private int mScreendeliver;
//
//    private ViewPager mViewPager;
//    private ArrayList fragments;
//    private ImageView view1, view2, view3;
//    private int currIndex;
//
//    private static int bmpW;//横线图片宽度
//    private static int offset;//图片移动的偏移量
//
//    //定义是否退出
//    private boolean isExit;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        mTabline = (ImageView) findViewById(R.id.id_tab_line);
//        Display display = getWindow().getWindowManager().getDefaultDisplay();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        display.getMetrics(outMetrics);
//        mScreendeliver = outMetrics.widthPixels / 3;
//        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
//        lp.width = mScreendeliver;
//        mTabline.setLayoutParams(lp);
//
//        initViewPager();
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    //内部类 重写TextView点击事件
//    public class txtListener implements View.OnClickListener {
//        private int index = 0;
//
//        public txtListener(int i) {
//            index = i;
//        }
//
//        @Override
//        public void onClick(View v) {
//            mViewPager.setCurrentItem(index);
//        }
//    }
//
//    private void initViewPager() {
//        //fragments页面定义、绑定等操作
////        mViewPager = (ViewPager) findViewById(R.id.myViewPager);
//
//        fragments = new ArrayList<Fragment>();
//        Fragment Fragment_Music_List = new Fragment_Music_List();
//        Fragment Fragment_Music_Detail = new Fragment_Music_Detail();
//
//        fragments.add(Fragment_Music_List);
//        fragments.add(Fragment_Music_Detail);
//
////        mViewPager.setAdapter(new MusicAdapter(getSupportFragmentManager(), fragments));
//        mViewPager.setCurrentItem(0);
//        mViewPager.setOnPageChangeListener(new myOnPageChangeListener());
//    }
//
//    public class myOnPageChangeListener implements ViewPager.OnPageChangeListener {
//        private int one = offset * 2 + bmpW;//两个相邻页面的偏移量
//
//        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            //position 页面位置 ;positionOffsetPixels 像素位置
//            mTabline.setTranslationX((positionOffset + position) * mScreendeliver);
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//            // TODO Auto-generated method stub
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            // TODO Auto-generated method stub
//            Animation animation = new TranslateAnimation(currIndex * one, position * one, 0, 0);//平移动画
//            currIndex = position;
//            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
//            animation.setDuration(500);//动画持续时间0.2秒
//            //重置所欲TextView
//            resetTextView();
//            //给所选TextView增加颜色
////            switch (position) {
////                case 0:
////                    view1.setBackgroundColor(Color.parseColor("#E0E0E0"));
////                    break;
////                case 1:
////                    view2.setBackgroundColor(Color.parseColor("#E0E0E0"));
////                    break;
////                case 2:
////                    view3.setBackgroundColor(Color.parseColor("#E0E0E0"));
////                    break;
////            }
//
//        }
//    }
//
//    //重置颜色
//    private void resetTextView() {
//
//    }
//
//    //重写onkeydown方法
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//    }
//
//    //定义exit退出方法
//    public void exit() {
//        if (!isExit) {
//            isExit = true;
//            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            mHandler.sendEmptyMessageDelayed(0, 2000);
//        } else {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
//            System.exit(0);
//        }
//    }
//
//    //配置handler
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            super.handleMessage(msg);
//            isExit = false;
//        }
//
//    };


    private Button button1;
    private Button button2;
    private Button buttonnext, buttonlast;

    private ImageButton buttonstart;
    private TextView opTime, edTime, bottom_title;
    private ImageView bottom_img;


    private ListView mlistview;
    private SeekBar seekBar1;
    private MediaPlayer player;
    private boolean ISPLAY = false;
    private Bitmap bm = null;
    private RelativeLayout main_layout;

    public static int currentListItme = 0;


    List<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();

    private File sdDir;
    private String spath;

    private int songIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = new MediaPlayer();

        init();
        findView();
        setListener();

    }


    private void init() {
//        player = MediaPlayer.create(this, R.raw.hello);
        try {
            player.reset();

            player.setDataSource("/sdcard/hello.mp3");
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void findView() {

        buttonstart = (ImageButton) findViewById(R.id.play_button);
        opTime = (TextView) findViewById(R.id.opTime);
        edTime = (TextView) findViewById(R.id.edTime);
        main_layout = (RelativeLayout) findViewById(R.id.background_main);
        bottom_img = (ImageView) findViewById(R.id.bottom_bar_img);
        bottom_title = (TextView) findViewById(R.id.bottom_bar_title);


        seekBar1 = (SeekBar) findViewById(R.id.seekbar1);
        //获得歌曲的长度并设置成播放进度条的最大值


//        mlistview = new ListView(this);
//        mlistview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, musicrs()));
//        setContentView(mlistview);


        mlistview = (ListView) findViewById(R.id.music_list);

        mlist = DBUtil.BaseMusicList(this);
        mlistview.setAdapter(new MusicAdapter(this, mlist));

    }

    Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
        public void run() {
            //获得歌曲现在播放位置并设置成播放进度条的值
            seekBar1.setProgress(player.getCurrentPosition());
            //开始时间控制
            int some = player.getCurrentPosition();
            String smthings = formatTimeFromProgress(some);
            opTime.setText(smthings);
            //每次延迟100毫秒再启动线程
            handler.postDelayed(updateThread, 100);

        }
    };

    private void setListener() {
//        button1.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                player.start();
//                //启动
//                handler.post(updateThread);
//            }
//        });
//        button2.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                player.pause();
//                //取消线程
////                handler.removeCallbacks(updateThread);
//            }
//        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//播放完成后，自动切换下一首
                if (songIndex < mlist.size() - 1) {
                    songIndex = songIndex + 1;
                    songplay(songIndex);
                } else {
                    mlist.clear();
                    songIndex = 0;

                }

            }
        });

        //item点击事件
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                songplay(position);
            }
        });

        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ISPLAY == false) {

                    player.start();
                    //启动
                    handler.post(updateThread);
                    buttonstart.setBackgroundResource(R.drawable.music_pause);
                    ISPLAY = true;
                } else if (ISPLAY == true) {
                    player.pause();
                    ISPLAY = false;
                    buttonstart.setBackgroundResource(R.drawable.music_play);
                }

            }
        });


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // fromUser判断是用户改变的滑块的值
//                if (fromUser == true) {
//                    player.seekTo(progress);
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //放下滑块之后的操作
                int dest = seekBar.getProgress();
                player.seekTo(dest);
            }
        });
    }

    private void songplay(int position) {

        songIndex = position;
        HashMap<String, Object> geturl = (HashMap<String, Object>) mlist.get(position);
        String murl = geturl.get("url").toString();
        String back_img = geturl.get("images").toString();
        String mtitle = geturl.get("title").toString();
        //重置MediaPlayer状态
        player.reset();
        try {
            player.setDataSource(murl);
            player.prepare();
            bottom_title.setText(mtitle);
            if (back_img == null) {
                main_layout.setBackgroundResource(R.drawable.natoli);
                bottom_img.setImageResource(R.drawable.natoli);
            } else {
                AlbumDealUtil adu = new AlbumDealUtil();
                bm = BitmapFactory.decodeFile(back_img);
                Bitmap abm = bm;
                Bitmap nbm = adu.blurBitmap(bm, MainActivity.this);
                BitmapDrawable bmpDraw = new BitmapDrawable(nbm);
                main_layout.setBackgroundDrawable(bmpDraw);
                bottom_img.setImageBitmap(abm);
            }


            //
            int time = player.getDuration();
            String edtime = formatTimeFromProgress(time);
            edTime.setText(edtime);

            player.start();
            //
            handler.post(updateThread);
            buttonstart.setBackgroundResource(R.drawable.music_pause);
            ISPLAY = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        seekBar1.setMax(player.getDuration());

    }

    private String formatTimeFromProgress(int progress) {
        // 总的秒数
        int msecTotal = progress / 1000;
        int min = msecTotal / 60;
        int msec = msecTotal % 60;
        String minStr = min < 10 ? "0" + min : "" + min;
        String msecStr = msec < 10 ? "0" + msec : "" + msec;
        return minStr + ":" + msecStr;
    }

    //右上角小menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.info, menu);

        return true;
    }

    //右上角小menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.quit1:
                //设置listview数据源，并自定义ListView
                mlist = DBUtil.musicrs(this);
                mlistview.setAdapter(new MusicAdapter(this, mlist));
                //数据库操作
                return true;
            default:
                return false;
        }
    }


}
