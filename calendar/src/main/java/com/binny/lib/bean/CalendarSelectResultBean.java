package com.binny.lib.bean;

/**
 * author binny
 * date on 2018/3/14 18:49
 * describe 选择结果
 */
public class CalendarSelectResultBean {

    private MonthBean.Day mEndDay;
    private MonthBean.Day mStartDay;

    public MonthBean.Day getEndDay() {
        return mEndDay;
    }

    public void setEndDay(MonthBean.Day endDay) {
        mEndDay = endDay;
    }

    public MonthBean.Day getStartDay() {
        return mStartDay;
    }

    public void setStartDay(MonthBean.Day startDay) {
        mStartDay = startDay;
    }
}
