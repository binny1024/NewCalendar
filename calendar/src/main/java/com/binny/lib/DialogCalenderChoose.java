package com.binny.lib;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.binny.lib.bean.CalendarSelectResultBean;
import com.binny.lib.bean.DateBean;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.binny.lib.view.NewCalendarView;

import java.util.ArrayList;
import java.util.List;

import static com.binny.lib.util.CalendarUtil.loadCalendarYM;
import static com.binny.lib.util.CalendarUtil.loadCalendar;

/**
 * author Binny
 * date on 2018/3/14 13:37
 * describe
 */
public class DialogCalenderChoose extends Dialog implements OnCalendarSelectResultCallback {
    private Activity mContext;

    private NewCalendarView mNewCalendarView;//日历视图
    private List<DateBean> mDateBeans = new ArrayList<>();
    private Float mScaleHeight = 0.9f;//相对于屏幕的宽度的缩放大小
    private Float mScaleWidth = 0.9f;

    private Float mOffPosX = null;
    private Float mOffPosY = null;
    private int mWhichMonth = 0;

    private boolean mConfig;
    OnCalendarSelectResultCallback mOnCalendarSelectResultCallback;

    public DialogCalenderChoose(@NonNull Context context) {
        super(context, R.style.Theme_Light_NoTitle_Dialog);
        mContext = (Activity) context;
    }

    public DialogCalenderChoose(@NonNull Context context, int themeResId) {
        super(context, R.style.Theme_Light_NoTitle_Dialog);
        mContext = (Activity) context;
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
            mNewCalendarView.setMonthBeanDataList(mDateBeans);
            return;
        }
        mNewCalendarView.setMonthBeanDataList(mDateBeans,mWhichMonth);
    }
    public DialogCalenderChoose setOnCalendarResult(OnCalendarSelectResultCallback result){
        mOnCalendarSelectResultCallback = result;
        return this;
    }
    private void initView() {
        mNewCalendarView = findViewById(R.id.new_calender_view_dialog);
        mNewCalendarView.setResultCallback(this);

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
            mDateBeans.addAll(loadCalendarYM(endYear, fromYear));
        }else {
            mDateBeans.addAll(loadCalendarYM(fromYear, endYear));
        }
        mWhichMonth = whichMonth;
        return this;
    }

    /**
     * 八月
     *
     * @param fromYear
     * @param endYear
     * @param whichMonth
     * @return
     */
    public DialogCalenderChoose setLunarMonth(int fromYear, int endYear, int whichMonth) {
        if (fromYear > endYear) {
            mDateBeans.addAll(loadCalendar(endYear, fromYear));
        }else {
            mDateBeans.addAll(loadCalendar(fromYear, endYear));
        }
        mWhichMonth = whichMonth;
        return this;
    }
    /**
     * 2018年08月
     *
     * @param fromYear
     * @param endYear
     * @return
     */
    public DialogCalenderChoose setGregorianMonth(int fromYear, int endYear) {
        if (fromYear > endYear) {
            mDateBeans.addAll(loadCalendarYM(endYear, fromYear));
        }else {
            mDateBeans.addAll(loadCalendarYM(fromYear, endYear));
        }
        mWhichMonth = 0;
        return this;
    }

    /**
     * 八月
     *
     * @param fromYear
     * @param endYear
     * @return
     */
    public DialogCalenderChoose setLunarMonth(int fromYear, int endYear) {
        if (fromYear > endYear) {
            mDateBeans.addAll(loadCalendar(endYear, fromYear));
        }else {
            mDateBeans.addAll(loadCalendar(fromYear, endYear));
        }
        mWhichMonth = 0;
        return this;
    }

    public DialogCalenderChoose configScaleHeight(Float scaleHeight) {
        this.mScaleHeight = scaleHeight;
        return this;
    }

    public DialogCalenderChoose configScaleWidth(Float scaleWidth) {
        this.mScaleWidth = scaleWidth;
        return this;
    }

    public DialogCalenderChoose configOffPosX(Float offPosX) {
        this.mOffPosX = offPosX;
        return this;
    }

    public DialogCalenderChoose configOffPosY(Float offPosY) {
        this.mOffPosY = offPosY;
        return this;
    }

    /**
     * 初始化界面控件的显示数据
     */
    public DialogCalenderChoose applyConfig() {
        mConfig = true;
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

    @Override
    public void onSelectResult(CalendarSelectResultBean calendarSelectResultBean) {
        dismiss();
        mOnCalendarSelectResultCallback.onSelectResult(calendarSelectResultBean);
    }
}
