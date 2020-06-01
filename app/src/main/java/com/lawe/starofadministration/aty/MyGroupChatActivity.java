package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.MyGroupAdapter;
import com.lawe.starofadministration.adp.OtherBumenAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fuke
 * date : 2020/6/3 16:54
 * description : 我的群聊
 **/
@Layout(R.layout.activity_my_group_chat)
@DarkStatusBarTheme(false)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(false)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)

public class MyGroupChatActivity extends BaseAty {

    private ImageView titleBack;
    private TextView titleText;
    private RecyclerView myGroupRecycle;
    //空集合
    private List<String> list;
    private MyGroupAdapter myGroupAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void initViews() {
        titleBack = findViewById(R.id.title_back);
        titleText = findViewById(R.id.title_text);
        myGroupRecycle = findViewById(R.id.my_group_recycle);

        titleText.setTypeface(getTextMedium);
        titleText.setText("我的群聊");

        //其他部门列表
        list = new ArrayList<>();
        layoutManager = new LinearLayoutManager(me);
        myGroupRecycle.setLayoutManager(layoutManager);
        //禁用滑动事件
        myGroupRecycle.setNestedScrollingEnabled(false);
        myGroupAdapter = new MyGroupAdapter(R.layout.item_message_list);
        myGroupRecycle.setAdapter(myGroupAdapter);
    }

    @Override
    public void initDatas(JumpParameter parameter) {
        for (int i = 0; i < 15; i++) {
            list.add("" + i);
        }
        myGroupAdapter.setNewData(list);
        myGroupAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEvents() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
