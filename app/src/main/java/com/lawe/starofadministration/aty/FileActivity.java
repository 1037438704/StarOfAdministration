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
import com.lawe.starofadministration.adp.FictionAdapter;
import com.lawe.starofadministration.adp.FileAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * author : fuke
 * date : 2020/5/22 10:34
 * description : 公文归档页
 **/
@Layout(R.layout.activity_file)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class FileActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleNew;
    private EditText searchEdit;
    private LinearLayout searchChoose;
    private RecyclerView fileRecycle;
    private int maxRecycleCount = 3; //第三条item
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    //空集合
    private List<String> list;
    private FileAdapter fileAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView fileTop;
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
        fileRecycle = findViewById(R.id.file_recycle);
        fileTop = findViewById(R.id.file_top);
        //列表
        list = new ArrayList<>();
        layoutManager = new LinearLayoutManager(me);
        //待办信息
        fileAdapter = new FileAdapter(R.layout.item_file);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        fileRecycle.setLayoutManager(layoutManager);
        fileRecycle.setAdapter(fileAdapter);
        //设置字体
        titleText.setTypeface(getTextMedium);
        titleText.setText("收文归档系统");

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        fileAdapter.setNewData(list);
    }

    @Override
    public void setEvents() {
        fileRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.e("ssssss_itemCount",firstCompletelyVisibleItemPosition+"");
                if(firstCompletelyVisibleItemPosition >= maxRecycleCount){
                    fileTop.setVisibility(View.VISIBLE);
                }else if(firstCompletelyVisibleItemPosition == 0){
                    fileTop.setVisibility(View.GONE);
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
        //回到第一条item
        fileTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileRecycle.smoothScrollToPosition(0);
            }
        });
        //筛选
        searchChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fictionDrawer.openDrawer(Gravity.RIGHT);
            }
        });
    }
}
