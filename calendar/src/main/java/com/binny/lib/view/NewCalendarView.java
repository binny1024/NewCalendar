package com.binny.lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.lib.R;
import com.binny.lib.bean.WeekBean;
import com.binny.lib.callback.OnFromToDateCallback;
import com.binny.lib.util.CalendarUtil;
import com.binny.lib.util.Logger;
import com.binny.lib.viewholder.weekviewholder.WeekViewHolderHelper;
import com.binny.lib.adapter.CalendarRVAdapter;
import com.binny.lib.bean.DateBean;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.smart.holder.CommonAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * author binny
 * date on 2018/3/14 17:14
 * describe
 */
public class NewCalendarView extends RelativeLayout implements OnFromToDateCallback {
    private android.widget.TextView mSetFromToDateHintTv;
    private android.widget.TextView mChooseYearFrom;
    private android.widget.TextView mChooseMonthFrom;
    private android.widget.TextView mChooseWeekFrom;
    private android.widget.TextView mChooseYearTo;
    private android.widget.TextView mChooseMonthto;
    private android.widget.TextView mChooseWeekTo;
    private android.widget.RelativeLayout mSetFromToDateRl;
    private android.widget.GridView mDateWeekGv;
    private android.support.v7.widget.RecyclerView mCalenderRv;
    private android.widget.RelativeLayout mChooseDateRl;
    private android.widget.TextView mCalenderClearChosen;
    private android.widget.TextView mCalenderSureBtn;

    private LinearLayoutManager mLinearLayoutManager;
    private List<DateBean> mDateBeanList = new ArrayList<>();
    private Context mContext;
    private int mOrientation;
    private CalendarRVAdapter mRVAdapter;
    private boolean mCalenderClearChosenClickable;
    private boolean mCalenderSureBtnClickable;

    public void setResultCallback(OnCalendarSelectResultCallback resultCallback) {
        mResultCallback = resultCallback;
    }

    private OnCalendarSelectResultCallback mResultCallback;

    public NewCalendarView(Context context) {
        super(context);
        init(context);
    }

    @SuppressLint("SetTextI18n")
    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_new_calendar_view, this, true);
        this.mChooseDateRl = findViewById(R.id.choose_date_rl);
        this.mCalenderRv = findViewById(R.id.calender_rv);
        this.mDateWeekGv = findViewById(R.id.date_week_gv);

        this.mSetFromToDateHintTv = findViewById(R.id.set_from_to_date_hint_tv);//选择日期提示文字
        this.mSetFromToDateRl = findViewById(R.id.set_from_to_date_rl);//起止时间框

        this.mChooseWeekTo = findViewById(R.id.choose_week_to);
        this.mChooseMonthto = findViewById(R.id.choose_month_to);
        this.mChooseYearTo = findViewById(R.id.choose_year_to);
        this.mChooseWeekFrom = findViewById(R.id.choose_week_from);

        this.mChooseMonthFrom = findViewById(R.id.choose_month_from);
        this.mChooseYearFrom = findViewById(R.id.choose_year_from);

        this.mCalenderClearChosen = findViewById(R.id.calender_clear_chosen);
        this.mCalenderSureBtn = findViewById(R.id.calender_sure_btn);
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
        initHeader(mChooseYearFrom,mChooseMonthFrom,mChooseWeekFrom,mChooseYearTo,mChooseMonthto,mChooseWeekTo);
        /*
        * 设置星期指示器  一 二 三 四 五 六 日
        * */
        mDateWeekGv.setAdapter(new CommonAdapter<>(context, weekList, R.layout.layout_new_calendar_gv_title_item, new WeekViewHolderHelper()));
        /*
        * 默认竖直方向滚动
        * */
        mLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        mCalenderRv.setLayoutManager(mLinearLayoutManager);
    }

    private void initHeader(TextView yf,TextView mf,TextView wf,
                            TextView yt,TextView mt,TextView wt) {
        //使用Calendar
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int week = now.get(Calendar.DAY_OF_WEEK)+1;
        yf.setText(year + "年");
        yt.setText(year + "年");
        mf.setText(month + "月"+day + "日");
        mt.setText(month + "月"+day + "日");
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

    /**
     * 设置日历的滚动方向
     *
     * @param orientation 滚动方向 0 水平方向  1 竖直方向
     * @return
     */
    public NewCalendarView setOrientation(int orientation) {
        mLinearLayoutManager = new LinearLayoutManager(mContext, orientation, false);
        mCalenderRv.setLayoutManager(mLinearLayoutManager);
        return this;
    }

    public void setMonthBeanDataList(List<DateBean> dateBeanList, int date) {
        int pos = 0;
        int size = dateBeanList.size();
        for (int i = 0; i < size; i++) {
            int m = Integer.parseInt(dateBeanList.get(i).getMonthInt());
            if (m == date) {
                pos = i;
                Logger.logInfo("pos" + pos + "dataNow = " + date + "m = " + m);
                break;
            }
        }

        mDateBeanList.addAll(dateBeanList);
        mRVAdapter = new CalendarRVAdapter(mContext, mDateBeanList, mResultCallback, this);
        mCalenderClearChosen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCalenderClearChosenClickable) {
                    mRVAdapter.getGVViewHolderHelper().onClearAll();
                }
            }
        });
        mCalenderSureBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCalenderSureBtnClickable) {
                    Logger.logInfo("mCalenderSureBtnClickable = "+mCalenderSureBtnClickable);
                    mRVAdapter.getGVViewHolderHelper().onSure();
                }
            }
        });
        mCalenderRv.setAdapter(mRVAdapter);
        CalendarUtil.moveToPosition(mLinearLayoutManager, mCalenderRv, pos);
    }

    public void setMonthBeanDataList(List<DateBean> dateBeanList) {
        //使用Date
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(d);
        int pos = 0;
        int dataNow = Integer.parseInt(date);//201820
        int size = dateBeanList.size();
        for (int i = 0; i < size; i++) {
            int m = Integer.parseInt(dateBeanList.get(i).getMonthInt());
            if (m == dataNow) {
                pos = i;
                Logger.logInfo("pos" + pos + "dataNow = " + dataNow + "m = " + m);
                break;
            }
        }

        mDateBeanList.addAll(dateBeanList);
        mRVAdapter = new CalendarRVAdapter(mContext, mDateBeanList, mResultCallback, this);
        mCalenderClearChosen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCalenderClearChosenClickable) {
                    mRVAdapter.getGVViewHolderHelper().onClearAll();
                }
            }
        });
        mCalenderSureBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCalenderSureBtnClickable) {
                    Logger.logInfo("mCalenderSureBtnClickable = "+mCalenderSureBtnClickable);
                    mRVAdapter.getGVViewHolderHelper().onSure();
                }
            }
        });
        mCalenderRv.setAdapter(mRVAdapter);
        CalendarUtil.moveToPosition(mLinearLayoutManager, mCalenderRv, pos);
    }


    @Override
    public void onFromDate(String year, String month, String day, int week) {
        mChooseYearFrom.setText(year + "年");
        mChooseMonthFrom.setText(month + "月" + day + "日");
        switch (week) {
            case 0://
                mChooseWeekFrom.setText("周日");
                break;
            case 1://周一
                mChooseWeekFrom.setText("周一");
                break;
            case 2://周二
                mChooseWeekFrom.setText("周二");
                break;
            case 3://周三
                mChooseWeekFrom.setText("周三");
                break;
            case 4://周四
                mChooseWeekFrom.setText("周四");
                break;
            case 5://周五
                mChooseWeekFrom.setText("周五");
                break;
            case 6://周六
                mChooseWeekFrom.setText("周六");
                break;
            default:
                break;
        }

    }

    @Override
    public void ontoDate(String year, String month, String day, int week) {
        mChooseYearTo.setText(year + "年");
        mChooseMonthto.setText(month + "月" + day + "日");
        switch (week) {
            case 0://
                mChooseWeekTo.setText("周日");
                break;
            case 1://周一
                mChooseWeekTo.setText("周一");
                break;
            case 2://周二
                mChooseWeekTo.setText("周二");
                break;
            case 3://周三
                mChooseWeekTo.setText("周三");
                break;
            case 4://周四
                mChooseWeekTo.setText("周四");
                break;
            case 5://周五
                mChooseWeekTo.setText("周五");
                break;
            case 6://周六
                mChooseWeekTo.setText("周六");
                break;
            default:
                break;
        }
    }

    @Override
    public void hideHeaderHint() {
        this.mSetFromToDateHintTv.setVisibility(GONE);
        this.mSetFromToDateRl.setVisibility(VISIBLE);
    }

    @Override
    public void showHeaderHint() {
        this.mSetFromToDateHintTv.setVisibility(VISIBLE);
        this.mSetFromToDateRl.setVisibility(GONE);
    }

    @Override
    public void clearBtnStatusGrey() {
        mCalenderClearChosenClickable = false;
        mCalenderClearChosen.setBackground(getResources().getDrawable(R.drawable.shape_calender_clear_btn_grey));
    }

    @Override
    public void clearBtnStatusBright() {
        mCalenderClearChosenClickable = true;
        mCalenderClearChosen.setBackground(getResources().getDrawable(R.drawable.shape_calender_clear_btn_bright));
    }

    @Override
    public void sureBtnStatusGrey() {
        mCalenderSureBtnClickable = false;
        mCalenderSureBtn.setBackground(getResources().getDrawable(R.drawable.shape_calender_sure_btn_grey));
    }

    @Override
    public void sureBtnStatusBright() {
        mCalenderSureBtnClickable = true;
        mCalenderSureBtn.setBackground(getResources().getDrawable(R.drawable.shape_calender_sure_btn_bright));
    }
}
