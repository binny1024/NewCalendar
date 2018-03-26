package com.binny.lib.bean;

/**
 * author binny
 * date on 2018/3/14 18:49
 * describe 选择结果
 */
public class CalendarSelectResultBean {

    private DateBean.Day mEndDay;
    private DateBean.Day mStartDay;

    public DateBean.Day getEndDay() {
        return mEndDay;
    }

    public void setEndDay(DateBean.Day endDay) {
        mEndDay = endDay;
    }

    public DateBean.Day getStartDay() {
        return mStartDay;
    }

    public void setStartDay(DateBean.Day startDay) {
        mStartDay = startDay;
    }
}
