package com.lawe.starofadministration.aty;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.lawe.starofadministration.adp.ReportAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/25 9:34
 * description : 文件报发列表
 **/
@Layout(R.layout.activity_report)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ReportActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView reportRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private ReportAdapter reportAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView reportTop;
    private DrawerLayout reportDrawer;

    @Override
    public void initViews() {
        reportDrawer = findViewById(R.id.report_drawer);
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        reportRecycle = findViewById(R.id.report_recycle);
        reportTop = findViewById(R.id.report_top);
        //列表
        list = new ArrayList<>();
        //待办信息
        reportAdapter = new ReportAdapter(R.layout.item_report);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        layoutManager = new LinearLayoutManager(me);
        reportRecycle.setLayoutManager(layoutManager);
        reportRecycle.setAdapter(reportAdapter);
        titleNew.setVisibility(View.VISIBLE);
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("文件报发系统");

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        reportAdapter.setNewData(list);
    }

    @Override
    public void setEvents() {
        reportRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    reportTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    reportTop.setVisibility(View.GONE);
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

        //新建
        titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, IssueActivity.class);
                intent.putExtra("flagSpeed","1");
                startActivity(intent);
            }
        });
        //回到第一条item
        reportTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportRecycle.smoothScrollToPosition(0);
            }
        });
        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportDrawer.openDrawer(Gravity.RIGHT);
            }
        });

    }
}
