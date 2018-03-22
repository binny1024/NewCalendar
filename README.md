# NewCalendar
# 自定义 日历


![](https://github.com/Xbean1024/NewCalendar/blob/master/gif/qq.gif)
### 使用方法：
#### 1、   gradle dependency
    compile 'com.binny.lib:calendar:1.1.0'
#### 2、实现 OnSelectResultCallback 接口

##### 2.1、函数说明
###### 2.1.1 设置回调结果的接口

    public DialogCalenderChoose setOnCalendarResult(OnSelectResultCallback result){

    }
###### 2.1.1 设置起始时间和显示默认位置 "201805"

        /**
         * 八月
         *
         * @param fromYear 开始年份
         * @param endYear  终止年份
         * @param whichMonth 默认显示年份 0 为默认年份
         * @return
         */
        public DialogCalenderChoose setModelM(int fromYear, int endYear, int whichMonth) {

        }
#### 3、创建日期选框
##### 3.1 农历月份（日期是公历的）,为例配合这种奇葩需求！
     new DialogCalenderChoose(this)
                         .setOnCalendarResult(this)
                         .setLunarMonth(2018, 2018).show();
##### 3.2 公历月份（日期是公历的）
     new DialogCalenderChoose(this)
                        .setLunarMonth(this)
                        .setGregorianMonth(2016, 2017).show();