package com.binny.lib.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author binny
 * date on 2018/3/14 16:53
 * describe
 */
public class WeekBean implements Serializable {
    private List<Week> mWeekList;

    public List<Week> getWeekList() {
        return mWeekList;
    }

    public void setWeekList(List<Week> weekList) {
        mWeekList = weekList;
    }

    public static class Week implements Serializable {
        private String mWeek;

        public String getWeek() {
            return mWeek;
        }

        public void setWeek(String week) {
            mWeek = week;
        }
    }
}
