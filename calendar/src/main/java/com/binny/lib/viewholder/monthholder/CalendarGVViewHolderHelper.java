package com.binny.lib.viewholder.monthholder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.binny.lib.adapter.CalendarRVAdapter;
import com.binny.lib.bean.CalendarSelectResultBean;
import com.binny.lib.callback.OnCancelOrSureBtnClickListener;
import com.binny.lib.callback.OnFromToDateCallback;
import com.binny.lib.util.CalendarUtil;
import com.binny.lib.R;
import com.binny.lib.bean.DateBean;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.binny.lib.util.Logger;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * author Binny
 * date on 2018/3/14 14:07
 * describe
 */
public class CalendarGVViewHolderHelper implements IViewHolderHelper<CalendarGVViewHolder, DateBean.Day>, OnCancelOrSureBtnClickListener {

    private OnCalendarSelectResultCallback mSelectResultCallback;
    private CalendarSelectResultBean mCalendarSelectResultBean;
    private int mClickedCount;
    private List<DateBean.Day> mAllDayList = new ArrayList<>();//缓存起始值
    /*
    * 缓存信息
    * */
    private List<DateBean.Day> mDayListChosenItemTemp = new ArrayList<>();//缓存被选中项，以待清除
    private DateBean.Day mFirstChosenDayTemp;//缓存起始值
    private DateBean.Day mStartDay;//缓存起始值
    private DateBean.Day mEndDay;//缓存起始值
    private CalendarRVAdapter mRVAdapter;
    private Context mContext;
    private int mChooseTemp;//缓存第一个被选中的数据,也可以作为起始时间
    private OnFromToDateCallback mOnFromToDateCallback;

    public CalendarGVViewHolderHelper(List<DateBean> dateBeanList, OnCalendarSelectResultCallback selectResultCallback, CalendarRVAdapter calendarRVAdapter, OnFromToDateCallback onFromToDateCallback) {
        mSelectResultCallback = selectResultCallback;
        mOnFromToDateCallback = onFromToDateCallback;
        int size = dateBeanList.size();
        for (int i = 0; i < size; i++) {
            List<DateBean.Day> dayList = dateBeanList.get(i).getDayList();
            int daySize = dayList.size();
            for (int j = 0; j < daySize; j++) {
                mAllDayList.add(dayList.get(j));
            }
        }
        mCalendarSelectResultBean = new CalendarSelectResultBean();
        mRVAdapter = calendarRVAdapter;
    }

    @Override
    public IViewHolder initItemViewHolder(CalendarGVViewHolder viewHolder, View convertView) {
        viewHolder = new CalendarGVViewHolder();
        viewHolder.mDay = convertView.findViewById(R.id.calender_gv_item_tv);
        return viewHolder;
    }


    @Override
    public void bindListDataToView(final Context context, final List<DateBean.Day> iBaseBeanList, final CalendarGVViewHolder viewHolder, final int position) {

        mContext = context;
        viewHolder.mDay.setText(iBaseBeanList.get(position).getDay());
        final DateBean.Day day = iBaseBeanList.get(position);

        if (day.isChosenStatus()) {
            viewHolder.mDay.setTextColor(mContext.getResources().getColor(R.color.white));
             /*
            * 如果被选中
            * */
//            mDayListChosenItemTemp.add(day);
            int week = day.getWeek();
            if (mClickedCount==1){
                CalendarUtil.changeWidthWrapContent(viewHolder.mDay);
                viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_circle));
                return;
            }

            switch (week) {
                case 0://周日
                    if (day.isStartPos() || day.isStartDay()) {
                       /*
                       * 如果周日第一天  设置为圆形背景
                       * */
                        CalendarUtil.changeWidthWrapContent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_circle));
                    } else {
                        CalendarUtil.changMatchParent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_right));
                    }
                    break;
                case 1://周一
                    if (day.isEndPos() || day.isEndDay()) {
                     /*
                   * 如果周一是中点  设置为圆形背景
                   * */
                        CalendarUtil.changeWidthWrapContent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_circle));
                    } else {
                        CalendarUtil.changMatchParent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_left));
                    }
                    break;
                case 2://周二
                case 3://周三
                case 4://周四
                case 5://周五
                case 6://周六
                    if ((day.isStartPos() && day.isEndDay()) || day.isStartDay() && day.isEndPos()) {
                    /* 圆形 背景  : 月末始点或者月初终点
                    * 28  29 30 31  或  1 设置为圆形
                    * */
                        CalendarUtil.changeWidthWrapContent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_circle));
                    } else if ((day.isStartDay()||day.isStartPos())) {
                    /* 左半圆：月初 但不是起始选择点
                    *  1
                    *  */
                        CalendarUtil.changMatchParent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_left));
                    } else if (day.isEndDay() || day.isEndPos()) {
                    /* 右半圆 ：月末
                    * */
                        CalendarUtil.changMatchParent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_right));
                    } else {
                        CalendarUtil.changMatchParent(viewHolder.mDay);
                        viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_rectangle));
                    }
                default:
                    break;
            }
        }
        viewHolder.mDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewHolder.mDay.getText())) {
                    return;
                }
                /*
                * 日期被点中
                * */
                if (mClickedCount > 1) {
                    clearAllChosenStatus();
                    Logger.logInfo("点击次数大于2次，清除");
                    return;
                }
                int newValue = day.getDayLongValue();
                if (mClickedCount == 0) {
                        /*
                        * 如果是第一次点击
                        * 设置成圆形背景
                        * */
                    mOnFromToDateCallback.clearBtnStatusBright();
                    mOnFromToDateCallback.hideHeaderHint();
                    mClickedCount++;
                    mChooseTemp = newValue;//缓存第一个被点击的值
                    mFirstChosenDayTemp = day;
                    viewHolder.mDay.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_circle));
                    CalendarUtil.changeWidthWrapContent(viewHolder.mDay);
                    day.setChosenStatus(true);
                    mDayListChosenItemTemp.add(day);
                    mOnFromToDateCallback.onFromDate(day.getYear(), day.getMonth(), day.getDay(), day.getWeek());
                } else {
                    mOnFromToDateCallback.clearBtnStatusBright();
                    mOnFromToDateCallback.sureBtnStatusBright();
                    mClickedCount++;
                    if (mChooseTemp < newValue) {
                        /*
                        * 向后选择
                        * */
                        Logger.logInfo("mChooseTemp = " + mChooseTemp);
                        mStartDay = mFirstChosenDayTemp;
                        mEndDay = day;
                        updateCalendarHeader();
                        rendererChooseItemStatus(mChooseTemp, newValue);
                    } else if (mChooseTemp > newValue) {
                         /*
                        * 向前选择
                        * */
                        mStartDay = day;
                        mEndDay = mFirstChosenDayTemp;
                        updateCalendarHeader();
                        rendererChooseItemStatus(newValue, mChooseTemp);
                    }else {
                         /*
                        * 同一天
                        * */
                        mStartDay = day;
                        mEndDay = mFirstChosenDayTemp;
                        updateCalendarHeader();
                    }
                }
            }
        });
    }

    /**
     * 渲染背景
     *
     * @param start 20170802
     * @param end   20180212
     */
    private void rendererChooseItemStatus(int start, int end) {
        int size = mAllDayList.size();
        for (int i = 0; i < size; i++) {
            DateBean.Day day = mAllDayList.get(i);
            int value = day.getDayLongValue();
            String date = day.getDay();
            int week = day.getWeek();
            if (value > start - 1 && value < end + 1) {
                 /*
                * 中间态 全部设置为 选中
                * */
                day.setChosenStatus(true);
                mDayListChosenItemTemp.add(day);
                 /*
                * 设置起始值
                * */
                if (value == start) {
                    day.setStartPos(true);
                } else if (value == end) {
                    day.setEndPos(true);
                }
                Logger.logInfo("   date= " + date + "   start =  " + start + "   end= " + end
                        + "   value = " + value + "   week = " + week + "  size = " + size
                        + "  中点 =" + day.isEndPos() + "  起点 =" + day.isStartPos() + "  月末 =" + day.isEndDay()
                        + "  月初 =" + day.isStartDay()
                );
            }


        }
        mRVAdapter.notifyDataSetChanged();
    }

    private void clearAllChosenStatus() {
        mOnFromToDateCallback.clearBtnStatusGrey();
        mOnFromToDateCallback.sureBtnStatusGrey();
        mOnFromToDateCallback.showHeaderHint();
        mClickedCount = 0;
        mChooseTemp = 0;
        mFirstChosenDayTemp = null;
        int size = mDayListChosenItemTemp.size();
        for (int i = 0; i < size; i++) {
            mDayListChosenItemTemp.get(i).setChosenStatus(false);
            mDayListChosenItemTemp.get(i).setStartPos(false);
            mDayListChosenItemTemp.get(i).setEndPos(false);
        }
        mDayListChosenItemTemp.clear();
        mFirstChosenDayTemp = null;
        mRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClearAll() {
        clearAllChosenStatus();
    }

    @Override
    public void onSure() {
        setResult();
    }

    /**
     * 设置最终的结果，回调使用者
     */
    private void setResult(){
        mCalendarSelectResultBean.setEndDay(mEndDay);
        mCalendarSelectResultBean.setStartDay(mStartDay);
        mSelectResultCallback.onSelectResult(mCalendarSelectResultBean);
        mOnFromToDateCallback.ontoDate(mEndDay.getYear(), mEndDay.getMonth(), mEndDay.getDay(), mEndDay.getWeek());
        mOnFromToDateCallback.onFromDate(mStartDay.getYear(), mStartDay.getMonth(), mStartDay.getDay(), mStartDay.getWeek());
    }

    /**
     * 更新日历头部
     */
    private void updateCalendarHeader(){
        mOnFromToDateCallback.ontoDate(mEndDay.getYear(), mEndDay.getMonth(), mEndDay.getDay(), mEndDay.getWeek());
        mOnFromToDateCallback.onFromDate(mStartDay.getYear(), mStartDay.getMonth(), mStartDay.getDay(), mStartDay.getWeek());
    }

    public void release() {
        clearAllChosenStatus();
        mAllDayList.clear();
        mAllDayList = null;
    }
}
