package com.lawe.starofadministration.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.lawe.starofadministration.R;
import com.lawe.starofadministration.adp.LookGroupAdapter;
import com.lawe.starofadministration.adp.OtherBumenAdapter;
import com.lawe.starofadministration.base.BaseAty;

import java.util.ArrayList;
import java.util.List;


/**
 * author : fuke
 * date : 2020/5/8 15:54
 * description : 常用组管理
 **/
@Layout(R.layout.activity_group_manger)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 255, r = 255, g = 255, b = 255)
public class GroupMangerActivity extends BaseAty {

    //空集合
    private List<String> list;
    private OtherBumenAdapter otherBumenAdapter;
    private android.widget.ImageView titleBack;
    private TextView titleText;
    private LinearLayout titleMore;
    private LinearLayout titleNew;
    private TextView titleRight;
    private LinearLayout titleButton1;
    private android.widget.Button titleButton2;
    private RecyclerView groupRecycle;
    private LinearLayout groupDelete;
    private TextView draftChatSetText;
    private LinearLayout groupNew;
    private TextView draftChatNewText;

    @Override
    public void initViews() {
        RecyclerView popChooseGroup = findViewById(R.id.pop_choose_group);

        LinearLayoutManager layoutManager = new LinearLayoutManager(me);
        popChooseGroup.setLayoutManager(layoutManager);

        //列表
        list = new ArrayList<>();
        //待办信息
        LookGroupAdapter lookGroupAdapter = new LookGroupAdapter(R.layout.item_group);
        popChooseGroup.setAdapter(lookGroupAdapter);

        for (int i = 0; i < 15; i++) {
            list.add("" + i);
        }
        lookGroupAdapter.setNewData(list);
        lookGroupAdapter.notifyDataSetChanged();
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {

    }

    private void initView() {
        titleBack = (ImageView) findViewById(R.id.title_back);
        titleText = (TextView) findViewById(R.id.title_text);
        titleMore = (LinearLayout) findViewById(R.id.title_more);
        titleNew = (LinearLayout) findViewById(R.id.title_new);
        titleRight = (TextView) findViewById(R.id.title_right);
        titleButton1 = (LinearLayout) findViewById(R.id.title_button1);
        titleButton2 = (Button) findViewById(R.id.title_button2);
        groupRecycle = (RecyclerView) findViewById(R.id.group_recycle);
        groupDelete = (LinearLayout) findViewById(R.id.group_delete);
        draftChatSetText = (TextView) findViewById(R.id.draft_chat_set_text);
        groupNew = (LinearLayout) findViewById(R.id.group_new);
        draftChatNewText = (TextView) findViewById(R.id.draft_chat_new_text);
    }
}
