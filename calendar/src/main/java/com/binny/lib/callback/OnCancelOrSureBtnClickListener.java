package com.binny.lib.callback;

/**
 * author binny
 * date on 2018/3/16 15:08
 * describe 确定给  和 清除的回调接口
 */
public interface OnCancelOrSureBtnClickListener {
    /**
     * 清除
     */
    void onClearAll();

    /**
     * 确定
     */
    void onSure();
}
