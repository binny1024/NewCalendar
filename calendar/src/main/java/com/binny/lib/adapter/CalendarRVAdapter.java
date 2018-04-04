package com.binny.lib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.binny.lib.R;
import com.binny.lib.bean.DateBean;
import com.binny.lib.callback.OnFromToDateCallback;
import com.binny.lib.viewholder.monthholder.CalendarGVViewHolderHelper;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author Binny
 * date on 2018/3/14 13:47
 * describe
 */
public class CalendarRVAdapter extends RecyclerView.Adapter<CalendarRVAdapter.ViewHolder> {
    private Context mContext;
    private List<DateBean> mDateBeanList = new ArrayList<>(); //包装每一个月

    public CalendarGVViewHolderHelper getGVViewHolderHelper() {
        return mGVViewHolderHelper;
    }

    CalendarGVViewHolderHelper mGVViewHolderHelper;
    public CalendarRVAdapter(Context context, List<DateBean> dateBeanList, OnCalendarSelectResultCallback selectResultCallback, OnFromToDateCallback onFromToDateCallback) {
        mContext = context;
        mDateBeanList.addAll(dateBeanList);
        mGVViewHolderHelper = new CalendarGVViewHolderHelper(mDateBeanList,selectResultCallback,this, onFromToDateCallback);
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_new_calendar_rv_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String title = mDateBeanList.get(position).getMonthTitle();
        holder.mMonthTitle.setText(title);
        holder.setIsRecyclable(false);
        /*
        * 设置 月视图的 面板数据
        * */
        List<DateBean.Day> dayList = mDateBeanList.get(position).getDayList();
        holder.mGridViewMonthDays.setAdapter(new CommonAdapter<>(mContext, dayList, R.layout.layout_new_calendar_gv_item, mGVViewHolderHelper));
    }

    @Override
    public int getItemCount() {
        return mDateBeanList.size();
    }

    public void release() {
        mGVViewHolderHelper.release();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMonthTitle;
        private GridView mGridViewMonthDays;

        ViewHolder(View itemView) {
            super(itemView);
            mMonthTitle = itemView.findViewById(R.id.calender_rv_item_month_title);
            mGridViewMonthDays = itemView.findViewById(R.id.calender_rv_item_gv);
        }
    }
}
