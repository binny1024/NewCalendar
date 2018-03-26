package com.binny.lib.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.binny.lib.bean.CalendarDateHelperBean;
import com.binny.lib.bean.DateBean;

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

/**
 * author binny
 * date on 2018/3/14 19:16
 * describe 日历用到的工具类
 */
public final class CalendarUtil {
//    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//    String c=sdf.format(current);
//    int date = Integer.parseInt(c);

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int value = Integer.parseInt(dateFormat.format(calendar.getTime()));
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
    public static List<DateBean> loadCalendarYM(int fromYear, int endYear) {
        List<DateBean> dateBeanList = new ArrayList<>();
        for (int year = fromYear; year <= endYear; year++) {
            for (int i = 0; i < 12; i++) {
                DateBean dateBean = new DateBean();
                dateBean.setYear(String.valueOf(year));//2018
                dateBean.setMonthTitle(year + "年" + GREGORIAN_CALENDAR_MONTH[i]);//2018 年 02月30日
                int month = i + 1;
                if (i < 10) {
                    String m = year + "0" + month;
                    dateBean.setMonthInt(m);
                } else {
                    dateBean.setMonthInt(String.valueOf(month));
                }

                createDayList(year, month, dateBean);//2018年05月6日
                dateBeanList.add(dateBean);
            }
        }
        return dateBeanList;
    }

    /**
     * 封装年月日信息 八月
     *
     * @param fromYear 起始月份
     * @param endYear  结束年份
     * @return 日历集合
     */
    public static List<DateBean> loadCalendar(int fromYear, int endYear) {
        List<DateBean> dateBeanList = new ArrayList<>();
        for (int year = fromYear; year <= endYear; year++) {
            for (int i = 0; i < 12; i++) {
                DateBean dateBean = new DateBean();
                dateBean.setYear(String.valueOf(year));
                dateBean.setMonthTitle(CHINESE_CALENDAR_MONTH[i]);
                int month = i + 1;
                if (i < 10) {
                    String m = year + "0" + month;
                    dateBean.setMonthInt(m);
                } else {
                    dateBean.setMonthInt(String.valueOf(month));
                }
                createDayList(year, month, dateBean);//八月
                dateBeanList.add(dateBean);
            }
        }
        return dateBeanList;
    }

    private static void createDayList(int year, int month, DateBean dateBean) {
        CalendarDateHelperBean helperBean = CalendarUtil.getDayHelperBean(year, month, 1);
        List<DateBean.Day> dayList;
        int total = helperBean.getTotalNum();
        int placeHolder = helperBean.getPlaceHolderNum();
        dayList = new ArrayList<>();
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        int nowDay = Integer.parseInt(dateNowStr);
        for (int j = 0; j < total; j++) {
            DateBean.Day day = new DateBean.Day();
            if (j < placeHolder) {
                day.setDay("");
            } else {
                if (j == placeHolder) {
                    day.setFirstDay(true);
                }
                if (j == total - 1) {
                    day.setEndDay(true);
                }
                day.setDay(CALENDAR_DAY_31[(j - placeHolder)]);
                int longValue = convertValueToInt(year, month, CALENDAR_DAY_31[(j - placeHolder)]);
                day.setDayLongValue(longValue);
                if (longValue == nowDay) {
                    day.setInitStatus(true);
                }
                day.setWeek((j + 1) % 7);
                day.setYear(String.valueOf(year));
                day.setMonth(String.valueOf(month));
            }
            dayList.add(day);
        }
        dateBean.setDayList(dayList);
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
