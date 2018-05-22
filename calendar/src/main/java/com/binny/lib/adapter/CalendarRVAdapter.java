package com.binny.lib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binny.lib.R;
import com.binny.lib.bean.CalendarDateBean;
import com.binny.lib.util.CalendarUtil;

import java.util.ArrayList;
import java.util.List;

import static com.binny.lib.constant.CalendarConstant.PLACE_HOLDER;

/**
 * author Binny
 * date on 2018/3/14 13:47
 * describe
 */
public class CalendarRVAdapter extends RecyclerView.Adapter<CalendarRVAdapter.ViewHolder> {
    private Context mContext;

    private List<CalendarDateBean> mCalendarDateBeans = new ArrayList<>();//
    private int[] mClickedCount = new int[1];

    public interface OnItemClickedListener {
        void onItemClickedListener(int position, TextView textView, CalendarDateBean.Day day, int[] clickedCount);
    }

    private OnItemClickedListener mItemClickedListener;

    public void setOnItemClickedListener(final OnItemClickedListener itemClickedListener) {
        mItemClickedListener = itemClickedListener;
    }

    public CalendarRVAdapter(Context context, List<CalendarDateBean> dateBeanList) {
        mContext = context;
        mCalendarDateBeans.clear();
        mCalendarDateBeans.addAll(dateBeanList);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_new_calendar_text_view_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        String title = mCalendarDateBeans.get(position).getMonthTitle();
        if (!PLACE_HOLDER.equals(title)) {
            holder.mDayTv.setText(title);
            holder.mDayTv.getPaint().setFakeBoldText(true);
            holder.mDayTv.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
            CalendarUtil.changeWidthMatchParent(holder.mDayTv);
        } else {
            final CalendarDateBean.Day day = mCalendarDateBeans.get(position).getDay();

            String string = day.getDayInMonth();

            if (!TextUtils.isEmpty(string)) {
                holder.mDayTv.setText(string);
                if (day.isChosenStatus()) {
                    setCircle(holder.mDayTv);
                    int week = day.getDayInWeek();
                    switch (week) {
                        case 0://周日
                            if (day.isStartPos() || day.isStartDay()) {
                                /*
                                 * 如果周日第一天  设置为圆形背景
                                 * */
                                CalendarUtil.changeWidthWrapContent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_circle));
                            } else {
                                CalendarUtil.changMatchParent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_right));
                            }
                            break;
                        case 1://周一
                            if (day.isEndPos() || day.isEndDay()) {
                                /*
                                 * 如果周一是中点  设置为圆形背景
                                 * */
                                CalendarUtil.changeWidthWrapContent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_circle));
                            } else {
                                CalendarUtil.changMatchParent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_left));
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
                                CalendarUtil.changeWidthWrapContent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_circle));
                            } else if ((day.isStartDay() || day.isStartPos())) {
                                /* 左半圆：月初 但不是起始选择点
                                 *  1
                                 *  */
                                CalendarUtil.changMatchParent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_left));
                            } else if (day.isEndDay() || day.isEndPos()) {
                                /* 右半圆 ：月末
                                 * */
                                CalendarUtil.changMatchParent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_right));
                            } else {
                                CalendarUtil.changMatchParent(holder.mDayTv);
                                holder.mDayTv.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_rectangle));
                            }
                        default:
                            break;
                    }
                }
                holder.mDayTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        mItemClickedListener.onItemClickedListener(holder.getAdapterPosition(),holder.mDayTv, day, mClickedCount);
                    }
                });
            }

        }
    }

    private void setRight(final TextView textView, Context context) {
        textView.setBackground(context.getResources().getDrawable(R.drawable.shape_calender_right));
    }

    /**
     * @param textView 设置此view 的背景色
     */
    private void setCircle(final TextView textView) {
        textView.setTextColor(mContext.getResources().getColor(R.color.white));
        textView.setBackground(mContext.getResources().getDrawable(R.drawable.shape_calender_circle));
        CalendarUtil.changeWrapContent(textView);
    }

    @Override
    public int getItemCount() {
        return mCalendarDateBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mDayTv;

        ViewHolder(View itemView) {
            super(itemView);
            mDayTv = itemView.findViewById(R.id.calender_day_tv);
        }
    }
}
