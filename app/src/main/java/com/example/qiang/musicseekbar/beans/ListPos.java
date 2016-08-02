package com.example.qiang.musicseekbar.beans;

/**
 * Created by Eliot on 2016/7/21.
 */
public class ListPos {
    private static int list_postion = -1;
    private static String list_position_title = null;

    public static String getList_position_title() {
        return list_position_title;
    }

    public static void setList_position_title(String list_position_title) {
        ListPos.list_position_title = list_position_title;
    }

    public static int getList_postion() {
        return list_postion;
    }

    public void setList_postion(int list_postion) {
        this.list_postion = list_postion;
    }
}