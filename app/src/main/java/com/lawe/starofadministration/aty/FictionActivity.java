package com.lawe.starofadministration.aty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.FictionAdapter;
import com.lawe.starofadministration.adp.MessageAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.List;

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
    private int maxRecycleCount = 3;

    //空集合
    private List<String> list;
    private FictionAdapter fictionAdapter;
    private LinearLayoutManager layoutManager;
    private ImageView factionTop;

    @Override
    public void initViews() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        titleNew = findViewById(R.id.title_new);
        searchEdit = findViewById(R.id.search_edit);
        searchChoose = findViewById(R.id.search_choose);
        factionRecycle = findViewById(R.id.faction_recycle);
        factionTop = findViewById(R.id.faction_top);

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

            }
        });

        //回到第一条item
        factionTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factionRecycle.smoothScrollToPosition(0);
            }
        });
    }

}
