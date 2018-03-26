package com.binny.lib.viewholder.weekviewholder;

import android.content.Context;
import android.view.View;

import com.binny.lib.R;
import com.binny.lib.bean.WeekBean;
import com.binny.lib.viewholder.monthholder.CalendarGVViewHolder;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * author binny
 * date on 2018/3/14 16:53
 * describe
 */
public class WeekViewHolderHelper implements IViewHolderHelper<CalendarGVViewHolder,WeekBean.Week> {
    @Override
    public IViewHolder initItemViewHolder(CalendarGVViewHolder viewHolder, View convertView) {
        viewHolder = new CalendarGVViewHolder();
        viewHolder.mDay = convertView.findViewById(R.id.calender_gv_item_tv);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<WeekBean.Week> iBaseBeanList, CalendarGVViewHolder viewHolder, int position) {
        viewHolder.mDay.setText(iBaseBeanList.get(position).getWeek());
    }
}
