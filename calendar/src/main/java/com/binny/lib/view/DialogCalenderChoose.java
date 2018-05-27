package com.binny.lib.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.binny.lib.R;
import com.binny.lib.bean.CalendarDateBean;
import com.binny.lib.callback.OnCalendarSelectResultCallback;

import java.util.ArrayList;
import java.util.List;

import static com.binny.lib.util.CalendarUtil.loadGregorianCalendarMonth;
import static com.binny.lib.util.CalendarUtil.loadLunarCalendarMonth;

/**
 * author Binny
 * date on 2018/3/14 13:37
 * describe
 */
public class DialogCalenderChoose extends Dialog {

    private NewCalendarView mNewCalendarView;//日历视图
    private List<CalendarDateBean> mMonthBeans = new ArrayList<com.binny.lib.bean.CalendarDateBean>();

    private OnCalendarSelectResultCallback mResultCallback;

    /**
     * 回调结果
     *
     * @param resultCallback 回调接口
     * @return dialog
     */
    public DialogCalenderChoose setResultCallback(OnCalendarSelectResultCallback resultCallback) {
        mResultCallback = resultCallback;
        return this;
    }

    /**
     * 点击日期选择框 以外的区域 是否 消失
     *
     * @param clickedWhiteSpaceCancel 默认 true
     */
    public void setClickedWhiteSpaceCancel(boolean clickedWhiteSpaceCancel) {
        mClickedWhiteSpaceCancel = clickedWhiteSpaceCancel;
    }

    private boolean mClickedWhiteSpaceCancel = true;

    public DialogCalenderChoose(@NonNull Context context) {
        super(context, R.style.Theme_Light_NoTitle_Dialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_calendar_view_dialog);
        //触碰空白区域 消失
        setCanceledOnTouchOutside(mClickedWhiteSpaceCancel);
        //-------------
        initView();
        bindView();

    }

    private void bindView() {
        mNewCalendarView.setMonthBeanDataList(mMonthBeans);
    }

    private void initView() {
        mNewCalendarView = findViewById(R.id.new_calender_view_dialog);
        mNewCalendarView.setResultCallback(mResultCallback);
        mNewCalendarView.setDialogCalenderChoose(this);

    }

    /**
     * 2018年八月
     *
     * @param fromYear 起始年月
     * @param endYear  终止年月
     */
    public DialogCalenderChoose setLunarMonth(int fromYear, int endYear) {
        if (fromYear > endYear) {
            mMonthBeans.addAll(loadLunarCalendarMonth(endYear, fromYear));
        } else {
            mMonthBeans.addAll(loadLunarCalendarMonth(fromYear, endYear));
        }
        return this;
    }

    /**
     * 2018年08月
     *
     * @param fromYear 起始年月
     * @param endYear  终止年月
     */
    public DialogCalenderChoose setGregorianMonth(int fromYear, int endYear) {
        if (fromYear > endYear) {
            mMonthBeans.addAll(loadGregorianCalendarMonth(endYear, fromYear));
        } else {
            mMonthBeans.addAll(loadGregorianCalendarMonth(fromYear, endYear));
        }
        return this;
    }

}
