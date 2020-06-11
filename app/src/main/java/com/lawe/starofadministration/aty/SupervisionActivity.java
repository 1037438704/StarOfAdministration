package com.lawe.starofadministration.aty;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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
import com.lawe.starofadministration.adp.NoticeAdapter;
import com.lawe.starofadministration.adp.SupervisionAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/26 14:34
 * description : 督察督办
 **/
@Layout(R.layout.activity_supervision)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class SupervisionActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView superRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private SupervisionAdapter supervisionAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView superTop;
    private DrawerLayout superDrawer;
    private TextView titleRight;

    @Override
    public void initViews() {
        super.initViews();
        superDrawer = findViewById(R.id.super_drawer);
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        titleRight = findViewById(R.id.title_right);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        superRecycle = findViewById(R.id.super_recycle);
        superTop = findViewById(R.id.super_top);
        titleRight.setText("发布");
        //列表
        list = new ArrayList<>();
        //待办信息
        supervisionAdapter = new SupervisionAdapter(R.layout.item_supervision);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        layoutManager = new LinearLayoutManager(me);
        superRecycle.setLayoutManager(layoutManager);
        superRecycle.setAdapter(supervisionAdapter);
        titleNew.setVisibility(View.VISIBLE);
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("督察督办系统");

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        supervisionAdapter.setNewData(list);
    }

    @Override
    public void setEvents() {
        superRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    superTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    superTop.setVisibility(View.GONE);
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

        //发布
        titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupervisionActivity.this, SuperLookActivity.class);
                intent.putExtra("superFlag","2");
                startActivity(intent);
            }
        });

        //回到第一条item
        superTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                superRecycle.smoothScrollToPosition(0);
            }
        });

        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                superDrawer.openDrawer(Gravity.RIGHT);
            }
        });

    }
}
