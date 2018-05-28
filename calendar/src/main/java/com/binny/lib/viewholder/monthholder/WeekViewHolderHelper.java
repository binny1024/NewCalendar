package com.binny.lib.viewholder.monthholder;

import android.content.Context;
import android.view.View;

import com.binny.lib.R;
import com.binny.lib.bean.WeekBean;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * author binny
 * date on 2018/3/14 16:53
 * describe
 */
public class WeekViewHolderHelper implements IViewHolderHelper<WeekViewViewHolder,WeekBean.Week> {
    @Override
    public IViewHolder initItemViewHolder(WeekViewViewHolder viewHolder, View convertView) {
        viewHolder = new WeekViewViewHolder();
        viewHolder.mDay = convertView.findViewById(R.id.calender_day_tv);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<WeekBean.Week> iBaseBeanList, WeekViewViewHolder viewHolder, int position) {
        viewHolder.mDay.setText(iBaseBeanList.get(position).getWeek());
    }
}
