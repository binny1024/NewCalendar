package com.binny.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.binny.lib.bean.CalendarDateBean;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.binny.lib.view.DialogCalenderChoose;

public class MainActivity extends AppCompatActivity implements OnCalendarSelectResultCallback {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view) {
        new DialogCalenderChoose(this)
                .setResultCallback(this)
                .setLunarMonth(2000, 2018).show();
    }


    @Override
    public void onSelectResult(CalendarDateBean.Day startDay, CalendarDateBean.Day endDay) {
        Log.i(TAG, "onSelectResult: MainActivity"+startDay.toString());
    }
}
