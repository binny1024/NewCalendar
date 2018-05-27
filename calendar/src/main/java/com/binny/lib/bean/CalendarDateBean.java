package com.binny.lib.bean;

import java.io.Serializable;

/**
 * author  binny
 * date 5/18
 */
public class CalendarDateBean implements Serializable {
    private String mMonthTitle;//月份
    private Day mDay; //某一天

    public String getMonthTitle() {
        return mMonthTitle;
    }

    public void setMonthTitle(final String monthTitle) {
        mMonthTitle = monthTitle;
    }

    public Day getDay() {
        return mDay;
    }

    public void setDay(final Day day) {
        mDay = day;
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

        public void setStartSelectedPos(boolean startPos) {
            mStartPos = startPos;
        }

        public boolean isEndPos() {
            return mEndPos;
        }

        public void setEndSelectedPos(boolean endPos) {
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

        public void setSelectedStatus(boolean chosenStatus) {
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

        @Override
        public String toString() {
            return "Day{" +
                    "mWeek=" + mWeek +
                    ", mDay='" + mDay + '\'' +
                    ", mMonthTitle='" + mMonthTitle + '\'' +
                    ", mYear='" + mYear + '\'' +
                    ", mChosenStatus=" + mChosenStatus +
                    ", mDayLongValue=" + mDayLongValue +
                    ", mStartPos=" + mStartPos +
                    ", mInitStatus=" + mInitStatus +
                    ", mMonthInt='" + mMonthInt + '\'' +
                    ", mIsFirstDay=" + mIsFirstDay +
                    ", mIsEndDay=" + mIsEndDay +
                    ", mEndPos=" + mEndPos +
                    '}';
        }
    }
}
