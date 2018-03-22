# NewCalendar
# 自定义 日历


![](https://github.com/Xbean1024/NewCalendar/blob/master/gif/qq.gif)
### 使用方法：
#### 1、   gradle dependency
    compile 'com.binny.lib:calendar:1.0.8'
#### 2、实现 OnSelectResultCallback 接口

##### 2.1、函数说明
###### 2.1.1 设置回调结果的接口

    public DialogCalenderChoose setOnResult(OnSelectResultCallback result){
        mOnSelectResultCallback = result;
        return this;
    }
###### 2.1.1 设置起始时间和显示默认位置 "201805"

        /**
         * 八月
         *
         * @param fromYear 开始年份
         * @param endYear  终止年份
         * @param whichMonth 默认显示年份 0 为默认年份
         * @return
         */
        public DialogCalenderChoose setModelM(int fromYear, int endYear, int whichMonth) {

        }
#### 3、创建日期选框
##### 3.1 农历月份（日期是公历的）,为例配合这种奇葩需求！
     new DialogCalenderChoose(this)
                        .setOnResult(this)
                        .setModelM(2016, 2017,0).show();
##### 3.2 公历月份（日期是公历的）
     new DialogCalenderChoose(this)
                        .setOnResult(this)
                        .setModelYM(2016, 2017,0).show();
#### 4、代码示例


     package com.binny.demo;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;

    import com.binny.calendar.DialogCalenderChoose;
    import com.binny.calendar.bean.CalendarSelectResultBean;
    import com.binny.calendar.bean.MonthBean;
    import com.binny.calendar.callback.OnSelectResultCallback;
    import com.binny.calendar.util.Logz;

    import java.util.ArrayList;
    import java.util.List;

    import static com.binny.calendar.util.CalendarUtil.loadCalendar;

    public class MainActivity extends AppCompatActivity implements OnSelectResultCallback{
        private final String TAG = this.getClass().getSimpleName();
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Logz.debug(true);
            }

            public void show(View view) {
                new DialogCalenderChoose(this)
                        .setOnResult(this)
                        .setModelYM(2016, 2019,0).show();
            }

        public void show1(View view) {
            new DialogCalenderChoose(this)
                    .setOnResult(this)
                    .setModelM(2016, 2017,0).show();
        }

        public void show3(View view) {
            new DialogCalenderChoose(this)
                    .setOnResult(this)
                    .setModelYM(2018, 2018,0).show();
        }

        @Override
        public void onSelectResult(CalendarSelectResultBean calendarSelectResultBean) {
            Logz.logInfo(TAG, String.valueOf(calendarSelectResultBean.getStartTime()));
            Logz.logInfo(TAG, String.valueOf(calendarSelectResultBean.getEndTime()));
        }

        @Override
        public void onSelectResult(String calendarSelectResultBean) {

        }
    }
#### 日期实体类
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

            public static class Day implements Serializable {
                private  int mWeek;//周期
                private String mDay;//日
                private String mMonthTitle;//月
                private String mYear;//年
                private boolean mChosenStatus;//是否被选中
                private int mDayLongValue;//用于比较的值
                private boolean mStartPos;
                private boolean mInitStatus;//为默选中值


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

                public  int getWeek() {
                    return mWeek;
                }

                public void setWeek(int week) {
                    mWeek = week;
                }

                public String getDay() {
                    return mDay;
                }

                public void setDay(String day) {
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
            }
        }
