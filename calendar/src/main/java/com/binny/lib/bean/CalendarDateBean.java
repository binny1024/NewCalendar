package com.binny.lib.bean;

import java.io.Serializable;

/**
 * author  binny
 * date 5/18
 */
public class CalendarDateBean implements Serializable {
    private String mMonthTitle;//月份
    private MonthBean.Day mDay; //某一天

    public String getMonthTitle() {
        return mMonthTitle;
    }

    public void setMonthTitle(final String monthTitle) {
        mMonthTitle = monthTitle;
    }

    public MonthBean.Day getDay() {
        return mDay;
    }

    public void setDay(final MonthBean.Day day) {
        mDay = day;
    }
}
