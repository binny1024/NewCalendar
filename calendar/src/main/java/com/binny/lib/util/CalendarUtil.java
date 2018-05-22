package com.binny.lib.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.binny.lib.bean.CalendarDateBean;
import com.binny.lib.bean.CalendarDateHelperBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.binny.lib.constant.CalendarConstant.CALENDAR_DAY_31;
import static com.binny.lib.constant.CalendarConstant.CHINESE_CALENDAR_MONTH;
import static com.binny.lib.constant.CalendarConstant.GREGORIAN_CALENDAR_MONTH;
import static com.binny.lib.constant.CalendarConstant.PLACE_HOLDER;

/**
 * author binny
 * date on 2018/3/14 19:16
 * describe 日历用到的工具类
 */
public final class CalendarUtil {
//    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//    String c=sdf.format(current);
//    int date = Integer.parseInt(c);

    public static int mScrollPos;
    private static int mScrollPosCache;

    public static void changeWidthWrapContent(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        p.width = WRAP_CONTENT;
        view.setLayoutParams(p);
    }

    public static void changeHeightWrapContent(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        p.height = WRAP_CONTENT;
        view.setLayoutParams(p);
    }

    public static void changeWrapContent(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        p.height = WRAP_CONTENT;
        p.width = WRAP_CONTENT;
        view.setLayoutParams(p);
    }

    public static void changeWidthMatchParent(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        p.width = MATCH_PARENT;
        view.setLayoutParams(p);
    }

    public static void changeHeightMatchParent(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        p.height = MATCH_PARENT;
        view.setLayoutParams(p);
    }

    public static void changMatchParent(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        p.height = MATCH_PARENT;
        p.width = MATCH_PARENT;
        view.setLayoutParams(p);
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static int getDaysInMonth(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);//设定日历未具体的某年某月某日
//        calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));//获取指定具体某天的属性值，在一周中的第几天
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//获取指定具体某天的属性值，在一月中的第几天
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 用于计算 指定的某一月的 某天 在日历表中有几个占位符，总天数
     *
     * @param year  指定的年份
     * @param month 指定的月份
     * @param date  指定的 date
     * @return CalendarDateHelperBean 辅助类
     */
    public static CalendarDateHelperBean getDayHelperBean(int year, int month, int date) {
        CalendarDateHelperBean helperBean = new CalendarDateHelperBean();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);//设定日历未具体的某年某月某日
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        int value = Integer.parseInt(dateFormat.format(calendar.getTime()));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int placeHolder = 0;
        switch (dayOfWeek) {
            case 1://周日 六个占位符
                placeHolder = 6;
                helperBean.setDayOfWeek(0);
                break;
            case 2://周一
                placeHolder = 0;
                helperBean.setDayOfWeek(1);
                break;
            case 3://周二
                placeHolder = 1;
                helperBean.setDayOfWeek(2);
                break;
            case 4://周三
                placeHolder = 2;
                helperBean.setDayOfWeek(3);
                break;
            case 5://周四
                placeHolder = 3;
                helperBean.setDayOfWeek(4);
                break;
            case 6://周五
                placeHolder = 4;
                helperBean.setDayOfWeek(5);
                break;
            case 7://周六
                placeHolder = 5;
                helperBean.setDayOfWeek(6);
                break;
            default:
                Logger.logInfo("000");
                break;
        }
        int effectiveNum = calendar.getActualMaximum(Calendar.DATE);
        helperBean.setPlaceHolderNum(placeHolder);
        helperBean.setEffectiveNum(effectiveNum);//设置有效数字
        helperBean.setTotalNum(placeHolder + effectiveNum);
        return helperBean;
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }


    /**
     * 封装年月日信息  2018年05月6日
     *
     * @param fromYear 起始月份
     * @param endYear  结束年份
     * @return 日历集合
     */
    public static List<CalendarDateBean> loadCalendarYM(int fromYear, int endYear) {
        List<CalendarDateBean> calendarDateBeanList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        int value = Integer.parseInt(dateFormat.format(calendar.getTime()));
        Log.i("calendar", "loadCalendarYM: CalendarUtil"+value);
        for (int year = fromYear; year <= endYear; year++) {

            for (int whichMonth = 0; whichMonth < 12; whichMonth++) {
                //2015 年 02月30日

                String monthTitle = year + "年" + GREGORIAN_CALENDAR_MONTH[whichMonth];//2015 年 02月30日
                int month = whichMonth + 1;

                CalendarDateHelperBean helperBean = CalendarUtil.getDayHelperBean(year, month, 1);
                int count = helperBean.getTotalNum();//36
                int placeHolder = helperBean.getPlaceHolderNum();// 5
                for (int whichDay = -1; whichDay <count; whichDay++) {
                    CalendarDateBean calendarDateBean = new CalendarDateBean();
                    if (whichDay == -1) {
                        calendarDateBean.setMonthTitle(monthTitle);//设置月份
                        mScrollPosCache = calendarDateBeanList.size();
                        calendarDateBeanList.add(calendarDateBean);
                        continue;
                    }
                    calendarDateBean.setMonthTitle(PLACE_HOLDER);//设置月份
                    CalendarDateBean.Day day = new CalendarDateBean.Day();
                    if (whichDay < placeHolder) {// 0 1 2 3 4
                        day.setDayInMonth(PLACE_HOLDER);//设置占位为符
                    } else {
                        if (whichDay == placeHolder) {// 5
                            day.setFirstDay(true);//是每月的第一天
                        }
                        if (whichDay == count - 1) {
                            day.setEndDay(true);//每月最后一天
                        }
//                        if (whichDay + placeHolder < 10) {
//                            String m = year + "0" + month;
//                            day.setMonthInt(m);
//                        } else {
//                            day.setMonthInt(String.valueOf(month));
//                        }
                        day.setDayInMonth(CALENDAR_DAY_31[(whichDay - placeHolder)]);// 设置占实际的某一天
                        int longValue = convertValueToInt(year, month, CALENDAR_DAY_31[(whichDay - placeHolder)]);
                        day.setDayLongValue(longValue);
                        if (longValue == value) {
                            mScrollPos = mScrollPosCache;
                        }
                        day.setDayInWeek((whichDay + 1) % 7);// day 在一周中 属于哪一天
                        day.setYear(String.valueOf(year));
                        day.setMonth(String.valueOf(month));
                    }
                    calendarDateBean.setDay(day);
                    calendarDateBeanList.add(calendarDateBean);
                }
            }
        }
        return calendarDateBeanList;
    }

    private static int convertValueToInt(int year, int month, String day) {
        String monthStr = "";
        String dayStr = "";
        if (month < 10) {
            monthStr = "0" + month;
        }else {
            monthStr = String.valueOf(month);
        }
        if (Integer.parseInt(day) < 10) {
            dayStr = "0" + day;
        }else {
            dayStr = day;
        }

        String dayValue = year + monthStr + dayStr;
        return Integer.parseInt(dayValue);
    }
}
