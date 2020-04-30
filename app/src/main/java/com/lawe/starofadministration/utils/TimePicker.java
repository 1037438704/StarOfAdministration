package com.lawe.starofadministration.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lawe.starofadministration.R;

/**
 * author : fuke
 * date : 2020/4/30 14:14
 * description : 时间选择器
 */
public class TimePicker extends Dialog implements View.OnClickListener{
    private android.widget.TimePicker timePicker;
    private OnTimeClick clicker;
    private TextView cancelButton,okButton;
    private Context context;
    private int hourOfDay;
    private int minute;
    private boolean is24HourView;

    public TimePicker(@NonNull Context context, OnTimeClick callBack, int hourOfDay, int minute, boolean is24HourView) {
        super(context);
        this.clicker = callBack;
        this.context = context;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.is24HourView = is24HourView;
        init();
    }

    private void init() {
//        //用来取消标题栏 在setcontentview之前调用 否则会报错 android.util.AndroidRuntimeException:requestFeature() must be called before adding content
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = this.getWindow();
        window.setContentView(R.layout.view_time_picker_dialog);
        View view = this.getWindow().getDecorView();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.75); // 宽度设置为屏幕的0.75
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        timePicker = (android.widget.TimePicker) view.findViewById(R.id.timePicker);
        timePicker.setCurrentHour(hourOfDay);
        timePicker.setCurrentMinute(minute);
        timePicker.setIs24HourView(is24HourView);
        cancelButton = (TextView) view.findViewById(R.id.btn_cancel);

        cancelButton.setOnClickListener(this);
        okButton.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }



    /**
     * 确认 按钮 点击 事件
     */
    @SuppressLint("NewApi")
    private void onOkButtonClick() {
        if (clicker != null) {
            clicker.onTimeSelect(timePicker.getCurrentHour(),timePicker.getCurrentMinute());
        }
    }

    public android.widget.TimePicker getDatePicker(){
        return  timePicker;
    }



    public  interface OnTimeClick {
        void onTimeSelect(int hour,int minute);
    }

}

