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
import com.lawe.starofadministration.adp.ShareAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/25 13:34
 * description : 公告通知
 **/
@Layout(R.layout.activity_notice)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class NoticeActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView noticeRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private NoticeAdapter noticeAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView noticeTop;
    private DrawerLayout shareDrawer;
    private TextView titleRight;

    @Override
    public void initViews() {
        shareDrawer = findViewById(R.id.share_drawer);
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        titleRight = findViewById(R.id.title_right);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        noticeRecycle = findViewById(R.id.notice_recycle);
        noticeTop = findViewById(R.id.notice_top);
        //列表
        list = new ArrayList<>();
        //待办信息
        noticeAdapter = new NoticeAdapter(R.layout.item_file);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        layoutManager = new LinearLayoutManager(me);
        noticeRecycle.setLayoutManager(layoutManager);
        noticeRecycle.setAdapter(noticeAdapter);
        titleNew.setVisibility(View.VISIBLE);
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("公告通知");

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        noticeAdapter.setNewData(list);
    }

    @Override
    public void setEvents() {
        noticeRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    noticeTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    noticeTop.setVisibility(View.GONE);
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
                Intent intent = new Intent(NoticeActivity.this, NoticeLookActivity.class);
                intent.putExtra("noticeFlag","2");
                startActivity(intent);
            }
        });
        //回到第一条item
        noticeTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeRecycle.smoothScrollToPosition(0);
            }
        });
        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDrawer.openDrawer(Gravity.RIGHT);
            }
        });

    }
}
