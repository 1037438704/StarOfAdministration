package com.lawe.starofadministration.aty;

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
import com.lawe.starofadministration.adp.ReportAdapter;
import com.lawe.starofadministration.adp.ShareAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/25 10:34
 * description : 共享文件库
 **/
@Layout(R.layout.activity_share)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class ShareActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView shareRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private ShareAdapter shareAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView shareTop;
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
        shareRecycle = findViewById(R.id.share_recycle);
        shareTop = findViewById(R.id.share_top);
        //列表
        list = new ArrayList<>();
        //待办信息
        shareAdapter = new ShareAdapter(R.layout.item_file);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        layoutManager = new LinearLayoutManager(me);
        shareRecycle.setLayoutManager(layoutManager);
        shareRecycle.setAdapter(shareAdapter);
        titleNew.setVisibility(View.VISIBLE);
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("共享文件库");
        titleRight.setText("添加");

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        shareAdapter.setNewData(list);
    }

    @Override
    public void setEvents() {
        shareRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    shareTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    shareTop.setVisibility(View.GONE);
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
        //添加
        titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ShareLookActivity.class);
            }
        });
        //回到第一条item
        shareTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareRecycle.smoothScrollToPosition(0);
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
