package com.binny.lib.bean;

import java.io.Serializable;

/**
 * author binny
 * date on 2018/3/15 12:15
 * describe 日期的辅助类 用于封装 某月 的第一天前需要几个占位符 几个有效的数值 总数
 */
public class CalendarDateHelperBean implements Serializable {
    private int mPlaceHolderNum;//占位符的个数
    private int mEffectiveNum;//实际数字的个数
    private int mTotalNum;//总数

    public int getDayOfWeek() {
        return mDayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        mDayOfWeek = dayOfWeek;
    }

    private int mDayOfWeek;//周几


    public int getPlaceHolderNum() {
        return mPlaceHolderNum;
    }

    public void setPlaceHolderNum(int placeHolderNum) {
        mPlaceHolderNum = placeHolderNum;
    }

    public int getEffectiveNum() {
        return mEffectiveNum;
    }

    public void setEffectiveNum(int effectiveNum) {
        mEffectiveNum = effectiveNum;
    }

    public int getTotalNum() {
        return mTotalNum;
    }

    public void setTotalNum(int totalNum) {
        mTotalNum = totalNum;
    }
}
