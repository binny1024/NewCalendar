package com.binny.lib.callback;

import com.binny.lib.bean.CalendarSelectResultBean;

/**
 * author binny
 * date on 2018/3/14 18:50
 * describe 选择结果的回调
 */
public interface OnCalendarSelectResultCallback {
    void onSelectResult(CalendarSelectResultBean calendarSelectResultBean);
}
