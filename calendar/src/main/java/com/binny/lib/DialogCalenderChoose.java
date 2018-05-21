package com.binny.lib;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.binny.lib.bean.CalendarDateBean;
import com.binny.lib.view.NewCalendarView;

import java.util.ArrayList;
import java.util.List;

import static com.binny.lib.util.CalendarUtil.loadCalendarYM;

/**
 * author Binny
 * date on 2018/3/14 13:37
 * describe
 */
public class DialogCalenderChoose extends Dialog  {

    private NewCalendarView mNewCalendarView;//日历视图
    private List<CalendarDateBean> mMonthBeans = new ArrayList<com.binny.lib.bean.CalendarDateBean>();
    private int mWhichMonth = 0;
    private Activity mActivity;

    public DialogCalenderChoose(@NonNull Context context) {
        super(context, R.style.Theme_Light_NoTitle_Dialog);
        mActivity = (Activity) context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_calendar_view_dialog);
        //触碰空白区域 消失
        setCanceledOnTouchOutside(true);
        //-------------
        initView();
        bindView();

    }

    private void bindView() {
        if (mWhichMonth == 0) {
            mNewCalendarView.setMonthBeanDataList(mMonthBeans);
            return;
        }
        mNewCalendarView.setMonthBeanDataList(mMonthBeans, mWhichMonth);
    }

    private void initView() {
        mNewCalendarView = findViewById(R.id.new_calender_view_dialog);
        mNewCalendarView.setActivity(mActivity);
    }

    /**
     * 2018年08月
     *
     * @param fromYear
     * @param endYear
     * @param whichMonth
     * @return
     */
    public DialogCalenderChoose setGregorianMonth(int fromYear, int endYear, int whichMonth) {
        if (fromYear > endYear) {
            mMonthBeans.addAll(loadCalendarYM(endYear, fromYear));
        } else {
            mMonthBeans.addAll(loadCalendarYM(fromYear, endYear));
        }
        mWhichMonth = whichMonth;
        return this;
    }

    public DialogCalenderChoose setProperty(int x, int y, int w, int h) {
        Window window = getWindow();//得到对话框的窗口．
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = x;//设置对话框的位置．0为中间
        wl.y = y;
        wl.width = w;
        wl.height = h;
        wl.alpha = 1f;// 设置对话框的透明度,1f不透明
        wl.gravity = Gravity.CENTER;//设置显示在中间
        window.setAttributes(wl);
        return this;
    }
}
