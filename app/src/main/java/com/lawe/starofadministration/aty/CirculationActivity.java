package com.lawe.starofadministration.aty;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.FictionAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/20 16:54
 * description : 收文传阅列表
 **/
@Layout(R.layout.activity_circulation)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class CirculationActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView circulatRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private FictionAdapter fictionAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView circulatTop;
    private DrawerLayout fictionDrawer;
    private TextView titleRight;
    private int flag = 7;

    @Override
    public void initViews() {
        fictionDrawer = findViewById(R.id.fiction_drawer);
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        titleRight = findViewById(R.id.title_right);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        circulatRecycle = findViewById(R.id.circulat_recycle);
        circulatTop = findViewById(R.id.circulat_top);
        //列表
        list = new ArrayList<>();
        layoutManager = new LinearLayoutManager(me);
        //待办信息
        fictionAdapter = new FictionAdapter(R.layout.item_circulation);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        circulatRecycle.setLayoutManager(layoutManager);
        circulatRecycle.setAdapter(fictionAdapter);
        titleNew.setVisibility(View.VISIBLE);
        titleRight.setText("录入");
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("收文传阅系统");

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        fictionAdapter.setNewData(list);
    }

    @Override
    public void setEvents() {
        circulatRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    circulatTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    circulatTop.setVisibility(View.GONE);
                }

            }

        });
        //返回
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //录入
        titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*// 办理人查看：1
                    创建人查看：2
                    办理 ：3
                    协办：4
                    批示：5
                    承办：6
                    拟办：7
                    收文录入：8
                    文件签收：9*/
                if (flag == 1){
                    jump(HandledLookActivity.class);
                }else if(flag == 2){
                    jump(EntryActivity.class);
                }else if(flag == 3){
                    jump(HandledActivity.class);
                } else if(flag == 4){
                    jump(SponsorActivity.class);
                }else if(flag == 5){
                    jump(InstructionsActivity.class);
                }else if(flag == 6){
                    jump(UndertakeActivity.class);
                }else if(flag == 7){
                    jump(PropsedActivity.class);
                }else if(flag == 8){
                    jump(EntryEntryActivity.class);
                }else if(flag == 9){
                    jump(SignActivity.class);
                }

            }
        });
        //回到第一条item
        circulatTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circulatRecycle.smoothScrollToPosition(0);
            }
        });
        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fictionDrawer.openDrawer(Gravity.RIGHT);
            }
        });

       /* //时间选择器
        fictionTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartDatePickerDialog(me,  2, fictionTimeStart, calendar);
            }
        });

        fictionTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndDatePickerDialog(me,  2, fictionTimeEnd, calendar);
            }
        });*/
    }


    /**
     * 日期选择
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
   /* public static void showStartDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void showEndDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }*/
}
