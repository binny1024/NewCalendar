package com.binny.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.binny.lib.DialogCalenderChoose;
import com.binny.lib.bean.CalendarSelectResultBean;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.binny.lib.util.Logger;

public class MainActivity extends AppCompatActivity implements OnCalendarSelectResultCallback {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view) {
        new DialogCalenderChoose(this)
                .setOnCalendarResult(this)
                .setGregorianMonth(2015, 2029, 0).show();
    }


    @Override
    public void onSelectResult(CalendarSelectResultBean calendarSelectResultBean) {
        Log.i(TAG, "onSelectResult: " + calendarSelectResultBean.getStartDay().getDayLongValue() + "  ---  " + calendarSelectResultBean.getEndDay().getDayLongValue());

    }
}
