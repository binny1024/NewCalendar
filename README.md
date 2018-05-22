# NewCalendar
# 自定义 日历
##### 需求：
###### 1 满足UI效果
###### 2 选择日期，选中一个日期后，可以向前选择，也可以向后选择
###### 3 选择同一天，则需要在同一天上点击两下
###### 4 在任意日期上点击第三下时，取消选择

![](https://github.com/Xbean1024/NewCalendar/blob/master/gif/qq.gif)
###### 接口说明
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
###### 函数说明
###### 1设置起止年月
    /**
     * 2018年08月
     *
     * @param fromYear 起始年月
     * @param endYear 终止年月
     */
    public DialogCalenderChoose setGregorianMonth(int fromYear, int endYear) {

    }
###### 2 回调结果
    /** 回调结果
     * @param resultCallback 回调接口
     * @return dialog
     */
    public DialogCalenderChoose setResultCallback(OnCalendarSelectResultCallback resultCallback) {

    }
###### 使用案例
    public class MainActivity extends AppCompatActivity implements OnCalendarSelectResultCallback {
        private final String TAG = this.getClass().getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void show(View view) {
            new DialogCalenderChoose(this)
                    .setResultCallback(this)
                    .setGregorianMonth(2000, 2018).show();
        }


        @Override
        public void onSelectResult(CalendarDateBean.Day startDay, CalendarDateBean.Day endDay) {
            Log.i(TAG, "onSelectResult: MainActivity"+startDay.toString());
        }
    }


