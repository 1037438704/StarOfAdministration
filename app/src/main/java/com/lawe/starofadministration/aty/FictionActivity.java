package com.lawe.starofadministration.aty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.MainActivity;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.FictionAdapter;
import com.lawe.starofadministration.adp.MessageAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/4/29 15:04
 * description : 公文拟制列表
 **/
@Layout(R.layout.activity_fiction)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class FictionActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView factionRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private FictionAdapter fictionAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView factionTop;
    private DrawerLayout fictionDrawer;
    private TextView fictionTimeStart;
    private TextView fictionTimeEnd;

    @Override
    public void initViews() {
        fictionDrawer = findViewById(R.id.fiction_drawer);
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        factionRecycle = findViewById(R.id.faction_recycle);
        factionTop = findViewById(R.id.faction_top);
        fictionTimeStart = findViewById(R.id.fiction_time_start);
        fictionTimeEnd = findViewById(R.id.fiction_time_end);

        titleNew.setVisibility(View.VISIBLE);
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("公文拟制系统");

        //列表
        list = new ArrayList<>();
        //待办信息
        layoutManager = new LinearLayoutManager(me);
        factionRecycle.setLayoutManager(layoutManager);
        fictionAdapter = new FictionAdapter(R.layout.item_fiction);
        factionRecycle.setAdapter(fictionAdapter);
    }

    @Override
    public void initDatas(JumpParameter parameter) {

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        fictionAdapter.setNewData(list);
        fictionAdapter.notifyDataSetChanged();

        factionRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    factionTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    factionTop.setVisibility(View.GONE);
                }

            }

        });
    }

    @Override
    public void setEvents() {

        //返回
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //新建
        titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(DraftActivity.class);
            }
        });

        //回到第一条item
        factionTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factionRecycle.smoothScrollToPosition(0);
            }
        });

        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fictionDrawer.openDrawer(Gravity.RIGHT);
            }
        });

        //时间选择器
        fictionTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(me,  2, fictionTimeStart, calendar);
            }
        });

        fictionTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(me,  2, fictionTimeStart, calendar);
            }
        });
    }


    /**
     * 日期选择
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
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

}
