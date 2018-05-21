package com.binny.lib.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
    private Activity mActivity;

    private List<CalendarDateBean> mCalendarDateBeans = new ArrayList<>();//
    private int[] mClickedCount = new int[1];

    public interface OnItemClickedListener {
        void onItemClickedListener(TextView textView, CalendarDateBean.Day day, final int[] clickedCount);
    }

    private OnItemClickedListener mItemClickedListener;

    public void setOnItemClickedListener(final OnItemClickedListener itemClickedListener) {
        mItemClickedListener = itemClickedListener;
    }

    public CalendarRVAdapter(Activity activity, List<CalendarDateBean> dateBeanList) {
        mActivity = activity;
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
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_new_calendar_text_view_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        String title =mCalendarDateBeans.get(position).getMonthTitle();
        if (!PLACE_HOLDER.equals(title)) {
            holder.mDayTv.setText(title);
            holder.mDayTv.setPadding(20, 5, 5, 5);
            holder.mDayTv.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        }else {
            final CalendarDateBean.Day day = mCalendarDateBeans.get(position).getDay();
            if (day.isChosenStatus()) {
                setCircle(holder.mDayTv);
                Log.i("[holder, position]", "onBindViewHolder = " + day.getDayLongValue());
            }
            String string = day.getDayInMonth();
            holder.mDayTv.setText(string);
            holder.mDayTv.setGravity(Gravity.CENTER);
            if (!TextUtils.isEmpty(string)) {
                holder.mDayTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        mItemClickedListener.onItemClickedListener(holder.mDayTv, day, mClickedCount);
                    }
                });
            }

        }
    }

    private void setRight(final TextView textView) {
        textView.setBackground(mActivity.getResources().getDrawable(R.drawable.shape_calender_right));
    }

    /**
     * @param textView 设置此view 的背景色
     */
    private void setCircle(final TextView textView) {
        textView.setTextColor(mActivity.getResources().getColor(R.color.white));
        textView.setBackground(mActivity.getResources().getDrawable(R.drawable.shape_calender_circle));
        textView.setGravity(Gravity.CENTER);
        CalendarUtil.changeWrapContent(textView);
    }
    @Override
    public int getItemCount() {
        int size = mCalendarDateBeans.size();
        return size;
    }

    public void release() {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mDayTv;

        ViewHolder(View itemView) {
            super(itemView);
            mDayTv = itemView.findViewById(R.id.calender_day_tv);
        }
    }
}
