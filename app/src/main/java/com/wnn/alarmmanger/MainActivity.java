package com.wnn.alarmmanger;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button setTime;
    private PendingIntent pi;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTime = (Button) findViewById(R.id.setTime);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                //创建一个TimePickerDialog实例，并把它显示出来
                new TimePickerDialog(MainActivity.this, 0,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                                //创建PendingIntent对象
                                pi = PendingIntent.getActivity(MainActivity.this, 0,
                                       intent, 0);
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(System.currentTimeMillis());
                                //根据用户选择时间来设置Calender对象
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                //获取AlarmManger对象
                                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                                Toast.makeText(MainActivity.this,"设置成功",Toast.LENGTH_LONG).show();
                            }
                        },currentTime.get(Calendar.HOUR_OF_DAY),currentTime.get(Calendar.MINUTE),true).show();
            }
        });
    }
}
