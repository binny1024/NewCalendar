package com.binny.lib.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.lib.R;
import com.binny.lib.adapter.CalendarRVAdapter;
import com.binny.lib.bean.CalendarDateBean;
import com.binny.lib.bean.WeekBean;
import com.binny.lib.util.CalendarUtil;
import com.binny.lib.viewholder.weekviewholder.WeekViewHolderHelper;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.binny.lib.constant.CalendarConstant.PLACE_HOLDER;

/**
 * author binny
 * date on 2018/3/14 17:14
 * describe
 */
public class NewCalendarView extends RelativeLayout implements CalendarRVAdapter.OnItemClickedListener {
    private android.widget.TextView mSetFromToDateHintTv;
    private android.widget.TextView mChooseYearFrom;
    private android.widget.TextView mChooseMonthFrom;
    private android.widget.TextView mChooseWeekFrom;
    private android.widget.TextView mChooseYearTo;
    private android.widget.TextView mChooseMonthto;
    private android.widget.TextView mChooseWeekTo;
    private android.widget.RelativeLayout mSetFromToDateRl;
    private android.support.v7.widget.RecyclerView mCalenderRv;
    private android.widget.TextView mCalenderClearChosen;
    private android.widget.TextView mCalenderSureBtn;

    private GridLayoutManager mGridLayoutManager;
    private List<CalendarDateBean> mCalendarDateBeanList = new ArrayList<>();
    private Activity mActivity;
    private CalendarRVAdapter mRVAdapter;
    private boolean mCalenderClearChosenClickable;
    private boolean mCalenderSureBtnClickable;

    private int mClickedCount;



    public NewCalendarView(Context context) {
        super(context);
        init(context);
    }

    @SuppressLint("SetTextI18n")
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_new_calendar_view, this, true);
//        RelativeLayout chooseDateRl = findViewById(R.id.choose_date_rl);
        this.mCalenderRv = findViewById(R.id.calender_rv);
        android.widget.GridView dateWeekGv = findViewById(R.id.date_week_gv);

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
        mGridLayoutManager.setAutoMeasureEnabled(true);
        mCalenderRv.setLayoutManager(mGridLayoutManager);
    }

    @SuppressLint("SetTextI18n")
    private void initHeader(TextView yf, TextView mf, TextView wf,
                            TextView yt, TextView mt, TextView wt) {
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

    public void setMonthBeanDataList(List<CalendarDateBean> monthBeanList, int date) {
        int pos = 0;
        int size = monthBeanList.size();
        for (int i = 0; i < size; i++) {
            int m = Integer.parseInt(monthBeanList.get(i).getDay().getMonthInt());
            if (m == date) {
                pos = i;
                break;
            }
        }
        mRVAdapter = new CalendarRVAdapter(mActivity, monthBeanList);
        mRVAdapter.setOnItemClickedListener(this);
        mCalenderRv.setAdapter(mRVAdapter);
        CalendarUtil.moveToPosition(mGridLayoutManager, mCalenderRv, pos);
    }

    public void setMonthBeanDataList(List<CalendarDateBean> monthBeanList) {
//        //使用Date
//        Date d = new Date();
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//        String date = sdf.format(d);
//        int pos = 0;
//        int dataNow = Integer.parseInt(date);//201820
//        int size = monthBeanList.size();
//        for (int i = 0; i < size; i++) {
//            int m = Integer.parseInt(monthBeanList.get(i).getDay().getMonthInt());
//            if (m == dataNow) {
//                pos = i;
//                Logger.logInfo("pos" + pos + "dataNow = " + dataNow + "m = " + m);
//                break;
//            }
//        }

        mCalendarDateBeanList.addAll(monthBeanList);
        mRVAdapter = new CalendarRVAdapter(mActivity, monthBeanList);
        mRVAdapter.setOnItemClickedListener(this);
        mCalenderRv.setAdapter(mRVAdapter);
//        CalendarUtil.moveToPosition(mGridLayoutManager, mCalenderRv, pos);
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

    @SuppressLint("SetTextI18n")


    public void release() {
        mRVAdapter.release();
    }

    public void setActivity(final Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onItemClickedListener( final TextView textView, final CalendarDateBean.Day day, final int[] clickedCount) {
        Log.i("[pos, textView, day]", "onItemClickedListener = " + day.getDayLongValue());

        if (TextUtils.isEmpty(textView.getText())) {
            return;
        }
        clickedCount[0]++;
        setCircle(textView);
        day.setChosenStatus(true);
    }

    /**
     * @param textView 设置此view 的背景色
     */
    private void setCircle(final TextView textView) {
        textView.setTextColor(mActivity.getResources().getColor(R.color.white));
        textView.setBackground(mActivity.getResources().getDrawable(R.drawable.shape_calender_circle));
        textView.setGravity(Gravity.CENTER);
        CalendarUtil.changeWidthWrapContent(textView);
    }
}
