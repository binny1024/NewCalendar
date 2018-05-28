package com.binny.lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.lib.R;
import com.binny.lib.adapter.CalendarRVAdapter;
import com.binny.lib.bean.CalendarDateBean;
import com.binny.lib.bean.WeekBean;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.binny.lib.util.CalendarUtil;
import com.binny.lib.util.Logger;
import com.binny.lib.viewholder.monthholder.WeekViewHolderHelper;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.binny.lib.constant.CalendarConstant.PLACE_HOLDER;
import static com.binny.lib.util.CalendarUtil.mScrollPos;

/**
 * author binny
 * date on 2018/3/14 17:14
 * describe
 */
public final class NewCalendarView extends RelativeLayout implements CalendarRVAdapter.OnItemClickedListener {
    private android.widget.TextView mSetFromToDateHintTv;
    private android.widget.TextView mSetStartYear;
    private android.widget.TextView mSetStartMonthDay;//起始的月日 12月18号
    private android.widget.TextView mSetStartWeek;
    private android.widget.TextView mSetEndYear;
    private android.widget.TextView mSetEndMonthDay;
    private android.widget.TextView mSetEndWeek;
    private android.widget.RelativeLayout mSetFromToDateRl;
    private android.support.v7.widget.RecyclerView mCalenderRv;
    private android.widget.TextView mClearBtn;
    private android.widget.TextView mSureBtn;

    private GridLayoutManager mGridLayoutManager;
    private List<CalendarDateBean> mCalendarDateBeanList = new ArrayList<>();
    private CalendarRVAdapter mRVAdapter;

    private int mFirstSelecedPos;


    private List<CalendarDateBean.Day> mSelectedDayCacheList = new ArrayList<>();//选中日期的缓存
    private boolean mSureBtnClickable;//是否可点击啊
    private boolean mClearBtnClickable;//是否可点击
    private OnCalendarSelectResultCallback mResultCallback;
    private Context mContext;


    private CalendarDateBean.Day mStartDay;
    private CalendarDateBean.Day mEndDay;

    private DialogCalenderChoose mDialogCalenderChoose;

    void setDialogCalenderChoose(DialogCalenderChoose dialogCalenderChoose) {
        mDialogCalenderChoose = dialogCalenderChoose;
    }

    void setResultCallback(OnCalendarSelectResultCallback resultCallback) {
        mResultCallback = resultCallback;
    }

    public NewCalendarView(Context context) {
        super(context);
        init(context);
    }

    @SuppressLint("SetTextI18n")
    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_new_calendar_view, this, true);
        RelativeLayout chooseDateRl = findViewById(R.id.choose_date_rl);
        this.mCalenderRv = findViewById(R.id.calender_rv);
        android.widget.GridView dateWeekGv = findViewById(R.id.date_week_gv);

        this.mSetFromToDateHintTv = findViewById(R.id.set_from_to_date_hint_tv);//选择日期提示文字
        this.mSetFromToDateRl = findViewById(R.id.set_from_to_date_rl);//起止时间框

        this.mSetEndWeek = findViewById(R.id.end_week);
        this.mSetEndMonthDay = findViewById(R.id.end_month);
        this.mSetEndYear = findViewById(R.id.end_year);

        this.mSetStartWeek = findViewById(R.id.start_week);
        this.mSetStartMonthDay = findViewById(R.id.start_month);
        this.mSetStartYear = findViewById(R.id.start_year);

        this.mClearBtn = findViewById(R.id.calender_clear_btn);
        mClearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClearBtnClickable) {
                    clearRender();
                }
            }
        });
        this.mSureBtn = findViewById(R.id.calender_sure_btn);
        mSureBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSureBtnClickable) {
                    if (mResultCallback != null && mStartDay != null && mEndDay != null) {
                        mResultCallback.onSelectResult(mStartDay, mEndDay);
                        clearRender();
                        mCalendarDateBeanList.clear();
                        mDialogCalenderChoose.dismiss();
                    } else {
                        Logger.logError("null",
                                "mStartDay = " + mStartDay
                                        + "  mEndDay = " + mEndDay
                                        + "  mResultCallback = " + mResultCallback
                        );
                    }
                }
            }
        });
        List<WeekBean.Week> weekList = new ArrayList<>();
        WeekBean.Week week = new WeekBean.Week();
        week.setWeek("一");
        weekList.add(week);
        WeekBean.Week week2 = new WeekBean.Week();
        week2.setWeek("二");
        weekList.add(week2);
        WeekBean.Week week3 = new WeekBean.Week();
        week3.setWeek("三");
        weekList.add(week3);
        WeekBean.Week week4 = new WeekBean.Week();
        week4.setWeek("四");
        weekList.add(week4);
        WeekBean.Week week5 = new WeekBean.Week();
        week5.setWeek("五");
        weekList.add(week5);
        WeekBean.Week week6 = new WeekBean.Week();
        week6.setWeek("六");
        weekList.add(week6);
        WeekBean.Week week7 = new WeekBean.Week();
        week7.setWeek("日");
        weekList.add(week7);
        /*
         * 初始化头部
         * */
        initHeader(mSetStartYear, mSetStartMonthDay, mSetStartWeek, mSetEndYear, mSetEndMonthDay, mSetEndWeek);
        /*
         * 设置星期指示器  一 二 三 四 五 六 日
         * */
        dateWeekGv.setAdapter(new CommonAdapter<>(context, weekList, R.layout.layout_new_calendar_gv_title_item, new WeekViewHolderHelper()));
        mGridLayoutManager = new GridLayoutManager(context, 7);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (!PLACE_HOLDER.equals(mCalendarDateBeanList.get(position).getMonthTitle())) {
                    return 7;
                }
                return 1;
            }
        });
        /*
        * 禁止 rv 自动测量
        * */
        mGridLayoutManager.setAutoMeasureEnabled(false);
        mCalenderRv.setLayoutManager(mGridLayoutManager);
//        mCalenderRv.setItemAnimator(new DefaultItemAnimator());
    }

    @SuppressLint("SetTextI18n")
    private void initHeader(TextView yf, TextView mf, TextView wf,
                            TextView yt, TextView mt, TextView wt) {
        //使用Calendar
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int week = now.get(Calendar.DAY_OF_WEEK) + 1;
        yf.setText(year + "年");
        yt.setText(year + "年");
        mf.setText(month + "月" + day + "日");
        mt.setText(month + "月" + day + "日");
        switch (week) {
            case 0://
                wf.setText("周日");
                wt.setText("周日");
                break;
            case 1://周一
                wf.setText("周一");
                wt.setText("周一");
                break;
            case 2://周二
                wf.setText("周二");
                wt.setText("周二");
                break;
            case 3://周三
                wf.setText("周三");
                wt.setText("周三");
                break;
            case 4://周四
                wf.setText("周四");
                wt.setText("周四");
                break;
            case 5://周五
                wf.setText("周五");
                wt.setText("周五");
                break;
            case 6://周六
                wf.setText("周六");
                wt.setText("周六");
                break;
            default:
                break;
        }
    }

    public NewCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NewCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void setMonthBeanDataList(List<CalendarDateBean> monthBeanList) {
        mCalendarDateBeanList.addAll(monthBeanList);
        mRVAdapter = new CalendarRVAdapter(mContext, monthBeanList);
        mRVAdapter.setOnItemClickedListener(this);
        mCalenderRv.setAdapter(mRVAdapter);
        CalendarUtil.moveToPosition(mGridLayoutManager, mCalenderRv, mScrollPos);
    }

    private void setWeekDay(int week, TextView weekTv) {
        switch (week) {
            case 0://
                weekTv.setText(" 周日");
                break;
            case 1://周一
                weekTv.setText(" 周一");
                break;
            case 2://周二
                weekTv.setText(" 周二");
                break;
            case 3://周三
                weekTv.setText(" 周三");
                break;
            case 4://周四
                weekTv.setText(" 周四");
                break;
            case 5://周五
                weekTv.setText(" 周五");
                break;
            case 6://周六
                weekTv.setText(" 周六");
                break;
            default:
                break;
        }

    }


    @Override
    public void onItemClickedListener(int position, final TextView textView, final CalendarDateBean.Day day, final int[] clickedCount) {
        Log.i("pos", "onItemClickedListener: NewCalendarView" + position);
        switch (clickedCount[0]) {
            case 0:
                mFirstSelecedPos = position;
                setStartDay(day);
                setCircle(textView);
                clickedCount[0]++;
                mClearBtnClickable = true;
                break;
            case 1:
                //连续渲染
                clickedCount[0]++;
                if (position > mFirstSelecedPos) {
                    renderDay(mFirstSelecedPos, position);
                } else if (position < mFirstSelecedPos) {
                    renderDay(position, mFirstSelecedPos);
                } else {
                    Log.i("click", "同一日期");
                    return;
                }

                break;
            case 2:
                //清除 渲染
                clickedCount[0] = 0;
                clearRender();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setStartDay(CalendarDateBean.Day day) {
        mStartDay = day;
        setClearBtnTrue();
        mSetFromToDateRl.setVisibility(VISIBLE);
        mSetFromToDateHintTv.setVisibility(INVISIBLE);
        mSetStartYear.setText(day.getYear() + "年");
        mSetStartMonthDay.setText(day.getMonth() + "月" + day.getDayInMonth() + "日");
        setWeekDay(day.getDayInWeek(), mSetStartWeek);
    }

    /**
     * 设置清除按钮可以点击
     */
    private void setClearBtnTrue() {
        mClearBtnClickable = true;
        mClearBtn.setBackground(getResources().getDrawable(R.drawable.shape_calender_clear_btn_bright));
    }

    @SuppressLint("SetTextI18n")
    private void setEndDay(CalendarDateBean.Day day) {
        mEndDay = day;
        setSureBtnTrue();
        mSetEndYear.setText(day.getYear() + "年");
        mSetEndMonthDay.setText(day.getMonth() + "月" + day.getDayInMonth() + "日");
        setWeekDay(day.getDayInWeek(), mSetEndWeek);
    }

    /**
     * 设置 确定按钮可以点击
     */
    private void setSureBtnTrue() {
        mSureBtnClickable = true;
        mSureBtn.setBackground(getResources().getDrawable(R.drawable.shape_calender_sure_btn_bright));
    }

    /**
     * 清除渲染效果
     */
    private void clearRender() {
        /*
         * 设置 btn 不可点击
         * */

        setSureBtnFalse();
        setClearBtnFalse();

        mStartDay = null;
        mEndDay = null;
        /*
         * 设置 选择结果不可见
         * */
        mSetFromToDateRl.setVisibility(GONE);
        mSetFromToDateHintTv.setVisibility(VISIBLE);

        /*
         * 清除 day 的选中状态
         * */
        int count = mSelectedDayCacheList.size();
        for (int i = 0; i < count; i++) {
            mSelectedDayCacheList.get(i).setSelectedStatus(false);
            mSelectedDayCacheList.get(i).setEndSelectedPos(false);
            mSelectedDayCacheList.get(i).setStartSelectedPos(false);
        }
        /*
         * 刷新视图
         * */
        mSelectedDayCacheList.clear();
        mRVAdapter.notifyDataSetChanged();
    }

    /**
     * 设置确定按钮不可点击
     */
    private void setSureBtnFalse() {
        mSureBtnClickable = false;
        mSureBtn.setBackground(getResources().getDrawable(R.drawable.shape_calender_sure_btn_grey));
    }

    /**
     * 设置清除按钮不可点击
     */
    private void setClearBtnFalse() {
        mClearBtnClickable = false;
        mClearBtn.setBackground(getResources().getDrawable(R.drawable.shape_calender_sure_btn_grey));
    }

    /**
     * 渲染视图
     *
     * @param startPos 起始时间
     * @param endPos   终止时间
     */
    private void renderDay(int startPos, int endPos) {
        for (int i = startPos; i <= endPos; i++) {
            CalendarDateBean.Day day1 = mCalendarDateBeanList.get(i).getDay();
            if (day1 == null) {
                continue;
            }
            day1.setSelectedStatus(true);
            if (i == endPos) {
                day1.setEndSelectedPos(true);
                setEndDay(day1);
            }
            if (i == startPos) {
                day1.setStartSelectedPos(true);
                setStartDay(day1);
            }
            mSelectedDayCacheList.add(day1);
        }
        mSureBtnClickable = true;
        mRVAdapter.notifyDataSetChanged();
    }

    /**
     * @param textView 设置此view 的背景色
     */
    private void setCircle(final TextView textView) {
        textView.setTextColor(mContext.getResources().getColor(R.color.white));
        textView.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_circle));
        textView.setGravity(Gravity.CENTER);
        CalendarUtil.changeWidthWrapContent(textView);
    }
}
