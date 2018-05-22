package com.binny.lib.callback;

import com.binny.lib.bean.CalendarDateBean;

/**
 * 作者: binny
 * 时间: 5/21
 * 描述: 选择结果的回调接口
 */
public interface OnCalendarSelectResultCallback {
    /**
     * @param startDay  起始时间
     * @param endDay 终止时间
     */
    void onSelectResult(CalendarDateBean.Day startDay, CalendarDateBean.Day endDay);
}
