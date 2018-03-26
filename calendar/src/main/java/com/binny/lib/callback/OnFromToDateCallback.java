package com.binny.lib.callback;

/**
 * author binny
 * date on 2018/3/16 20:21
 * describe
 */
public interface OnFromToDateCallback {
    /** 设置起始时间
     * @param year 年
     * @param month 月
     * @param day 日
     * @param week 周
     */
    void onFromDate(String year,String month,String day,int week);

    /** 设置起始时间
     * @param year 年
     * @param month 月
     * @param day 日
     * @param week 周
     */
    void ontoDate(String year,String month,String day,int week);

    /**
     * 隐藏头部
     */
    void hideHeaderHint();

    /**
     * 显示头部
     */
    void showHeaderHint();

    /**
     * 清除按钮可点击的状态
     */
    void clearBtnStatusGrey();

    /**
     * 清除按钮不可点击的状态
     */
    void clearBtnStatusBright();

    /**
     * 确定按钮不可点击的状态
     */
    void sureBtnStatusGrey();
    /*
    * 确定按钮可以点击的状态
    * */
    void sureBtnStatusBright();
}
