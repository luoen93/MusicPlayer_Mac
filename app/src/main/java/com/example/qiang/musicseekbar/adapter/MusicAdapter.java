package com.example.qiang.musicseekbar.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qiang.musicseekbar.R;
import com.example.qiang.musicseekbar.beans.ListPos;

import java.util.List;
import java.util.Map;

/**
 * Created by Qiang on 2016/6/14.
 */
public class MusicAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    Bitmap bm = null;

    public MusicAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应list.xml中的控件
     *
     * @author Administrator
     */
    public final class Music {
        public TextView title;
        public TextView artist;
        public TextView time;
        public ImageView images;
        public Button de_button;
        public RelativeLayout rela_delete;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Music zujian = null;
        if (convertView == null) {
            zujian = new Music();
            //获得组件，实例化组件
            convertView = layoutInflater.inflate(R.layout.activity_music_item, null);
            zujian.title = (TextView) convertView.findViewById(R.id.music_title);
            zujian.artist = (TextView) convertView.findViewById(R.id.music_artist);
            zujian.time = (TextView) convertView.findViewById(R.id.music_time);
            zujian.images = (ImageView) convertView.findViewById(R.id.music_img);
            zujian.de_button = (Button) convertView.findViewById(R.id.list_item_delete_btn);
            zujian.rela_delete = (RelativeLayout) convertView.findViewById(R.id.list_item_delete);

            convertView.setTag(zujian);
        } else {
            zujian = (Music) convertView.getTag();
        }
        //绑定数据
        zujian.title.setText((String) data.get(position).get("title"));
        zujian.artist.setText((String) data.get(position).get("artist"));
        zujian.time.setText((String) data.get(position).get("time"));
        String mimgs = (String) data.get(position).get("images");

        if (mimgs == null) {
            zujian.images.setBackgroundResource(R.drawable.natoli);
        } else {
            bm = BitmapFactory.decodeFile(mimgs);
            BitmapDrawable bmpDraw = new BitmapDrawable(bm);
            zujian.images.setImageDrawable(bmpDraw);
        }

        int np = ListPos.getList_postion();
        if (position == np) {
            zujian.rela_delete.setVisibility(View.VISIBLE);
            zujian.de_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("=======", "||||" + position);
                }
            });
        } else {
            zujian.rela_delete.setVisibility(View.GONE);

        }

        return convertView;
    }
}
