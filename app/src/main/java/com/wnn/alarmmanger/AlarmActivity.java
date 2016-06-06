package com.wnn.alarmmanger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by 碎步灬流年 on 2016/5/21.
 */
public class AlarmActivity extends Activity{
    private MediaPlayer alarmMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载指定音乐，并为之创建MediaPlay对象
        alarmMusic = MediaPlayer.create(this,R.raw.pig);
        alarmMusic.setLooping(true);

        //播放音乐
        alarmMusic.start();

        new AlertDialog.Builder(AlarmActivity.this).setTitle("闹钟").
                setMessage("闹钟响了")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //停止播放音乐
                        alarmMusic.stop();
                        //结束该Activity
                        AlarmActivity.this.finish();
                    }
                }).show();
    }
}
