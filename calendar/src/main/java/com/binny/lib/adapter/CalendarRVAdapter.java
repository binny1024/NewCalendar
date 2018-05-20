package com.binny.lib.adapter;

import android.content.Context;
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
import com.binny.lib.bean.MonthBean;
import com.binny.lib.callback.OnFromToDateCallback;
import com.binny.lib.util.Logger;
import com.binny.lib.viewholder.monthholder.CalendarGVViewHolderHelper;
import com.binny.lib.callback.OnCalendarSelectResultCallback;
import com.smart.holder.CommonAdapter;

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
    public CalendarRVAdapter(Context context, List<CalendarDateBean> dateBeanList, OnCalendarSelectResultCallback selectResultCallback, OnFromToDateCallback onFromToDateCallback) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        String title =mCalendarDateBeans.get(position).getMonthTitle();
        if (!PLACE_HOLDER.equals(title)) {
            holder.mDay.setText(title);
            holder.mDay.setPadding(20,5,5,5);
            holder.mDay.setGravity(Gravity.CENTER_VERTICAL|Gravity.START);
        }else {
            String string = mCalendarDateBeans.get(position).getDay().getDayInMonth();
            holder.mDay.setText(string);
            holder.mDay.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public int getItemCount() {
        int size = mCalendarDateBeans.size();
        return size;
    }

    public void release() {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mDay;

        ViewHolder(View itemView) {
            super(itemView);
            mDay = itemView.findViewById(R.id.calender_day_tv);
        }
    }
}
