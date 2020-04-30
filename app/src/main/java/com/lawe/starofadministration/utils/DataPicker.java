package com.lawe.starofadministration.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lawe.starofadministration.R;

import java.util.Calendar;
import java.util.Date;

/**
 * author : fuke
 * date : 2020/4/30 14:10
 * description : 日期选择器
 */
public class DataPicker extends Dialog implements View.OnClickListener{
    private DatePicker datePicker;
    private OnDateClick clicker;
    private Context context;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;

    public DataPicker(@NonNull Context context, OnDateClick callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context);
        this.clicker = callBack;
        this.context = context;
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
        init();
    }

    private void init() {
//        //用来取消标题栏 在setcontentview之前调用 否则会报错 android.util.AndroidRuntimeException:requestFeature() must be called before adding content
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = this.getWindow();
        window.setContentView(R.layout.view_date_picker_dialog);
        View view = this.getWindow().getDecorView();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.75); // 宽度设置为屏幕的0.75
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        datePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {}
        });

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());

    }



    @Override
    public void onClick(View view) {

    }



    /**
     * 确认 按钮 点击 事件
     */
    private void onOkButtonClick() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, datePicker.getYear());
        int month =datePicker.getMonth();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        calendar.getTime();
        if (clicker != null) {
            clicker.onDataSelect(calendar.getTime());
        }
    }

    public DatePicker getDatePicker(){
        return  datePicker;
    }



    public  interface OnDateClick {
        void onDataSelect(Date date);
    }

}

