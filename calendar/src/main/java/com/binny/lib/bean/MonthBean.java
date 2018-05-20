package com.binny.lib.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author Binny
 * date on 2018/3/14 12:46
 * describe 日期实体类
 */
public class MonthBean implements Serializable {
    private String mMonthTitle;//月
    private String mYear;//年
    private List<Day> mDayList;//一个月的日子
    private String mMonthInt;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getMonthInt() {
        return mMonthInt;
    }

    public void setMonthInt(String monthInt) {
        mMonthInt = monthInt;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }


    public String getMonthTitle() {
        return mMonthTitle;
    }

    public void setMonthTitle(String monthTitle) {
        mMonthTitle = monthTitle;
    }

    public List<Day> getDayList() {
        return mDayList;
    }

    public void setDayList(List<Day> dayList) {
        mDayList = dayList;
    }

    /**
     * 月份中的  某一天
     */
    public static class Day implements Serializable {
        private  int mWeek;//周期
        private String mDay;//日
        private String mMonthTitle;//月
        private String mYear;//年
        private boolean mChosenStatus;//是否被选中
        private int mDayLongValue;//用于比较的值
        private boolean mStartPos;
        private boolean mInitStatus;//为默选中值
        private String mMonthInt;


        public String getMonth() {
            return mMonthTitle;
        }

        public void setMonth(String monthTitle) {
            mMonthTitle = monthTitle;
        }

        public String getYear() {
            return mYear;
        }

        public void setYear(String year) {
            mYear = year;
        }

        public boolean isStartDay() {
            return mIsFirstDay;
        }

        public void setFirstDay(boolean firstDay) {
            mIsFirstDay = firstDay;
        }

        private boolean mIsFirstDay;

        public boolean isEndDay() {
            return mIsEndDay;
        }

        public void setEndDay(boolean endDay) {
            mIsEndDay = endDay;
        }

        private boolean mIsEndDay;

        public boolean isStartPos() {
            return mStartPos;
        }

        public void setStartPos(boolean startPos) {
            mStartPos = startPos;
        }

        public boolean isEndPos() {
            return mEndPos;
        }

        public void setEndPos(boolean endPos) {
            mEndPos = endPos;
        }

        private boolean mEndPos;

        public int getDayLongValue() {
            return mDayLongValue;
        }

        public void setDayLongValue(int longValue) {
            mDayLongValue = longValue;
        }

        public  int getDayInWeek() {
            return mWeek;
        }

        public void setDayInWeek(int week) {
            mWeek = week;
        }

        public String getDayInMonth() {
            return mDay;
        }

        public void setDayInMonth(String day) {
            mDay = day;
        }

        public boolean isChosenStatus() {
            return mChosenStatus;
        }

        public void setChosenStatus(boolean chosenStatus) {
            mChosenStatus = chosenStatus;
        }

        public void setInitStatus(boolean initStatus) {
            mInitStatus = initStatus;
        }

        public boolean isInitStatus() {
            return mInitStatus;
        }

        public void setMonthInt(final String monthInt) {
            mMonthInt = monthInt;
        }

        public String getMonthInt() {
            return mMonthInt;
        }
    }
}
